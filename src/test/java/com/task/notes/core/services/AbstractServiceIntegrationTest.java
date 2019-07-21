package com.task.notes.core.services;

import com.task.notes.core.services.config.CoreServicesTestConfiguration;
import org.h2.tools.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = CoreServicesTestConfiguration.class)
@TestPropertySource("classpath:application-test.properties")
@Transactional
public abstract class AbstractServiceIntegrationTest {

    private static final String WEB_ARG = "-web";
    private static final String WEB_ALLOW_OTHERS_ARG = "-webAllowOthers";
    private static final String WEB_PORT_ARG = "-webPort";

    private Server server;

    @Value("${spring.h2.console.port}")
    private String port;

    @PersistenceContext
    private EntityManager entityManager;

    protected void clear() {
        entityManager.clear();
    }

    protected void flush() {
        entityManager.flush();
    }

    protected void flushAndClear() {
        flush();
        clear();
    }

    @Before
    public void init() throws SQLException {
        server = Server.createWebServer(WEB_ARG, WEB_ALLOW_OTHERS_ARG, WEB_PORT_ARG, port).start();
    }

    @After
    public void tearDown() {
        server.stop();
    }
}
