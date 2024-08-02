package org.example.auth.repository;

import org.example.auth.config.AuthLibAutoConfiguration;
import org.example.auth.role.RoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = AuthLibAutoConfiguration.class)
@Testcontainers
@ActiveProfiles("test")
class RoleRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("DB_URL", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("DB_USERNAME", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("DB_PASSWORD", POSTGRE_SQL_CONTAINER::getPassword);
    }

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByIdAndIsActiveTrueTest() {
        Assertions.assertTrue(roleRepository.findByIdAndIsActiveTrue(RoleEnum.USER).isPresent());
        Assertions.assertTrue(roleRepository.findByIdAndIsActiveTrue(RoleEnum.CONTRACTOR_RUS).isPresent());
    }

}
