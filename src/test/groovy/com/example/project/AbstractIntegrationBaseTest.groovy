package com.example.project

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification

@SpringBootTest
abstract class AbstractIntegrationBaseTest extends Specification{
    static final GenericContainer MY_REDIS_CONATINER

    static {
        MY_REDIS_CONATINER = new GenericContainer("redis:6")
            .withExposedPorts(6379)

        MY_REDIS_CONATINER.start()

        System.setProperty("spring.redis.host",MY_REDIS_CONATINER.getHost())
        System.setProperty("spring.redis.port",MY_REDIS_CONATINER.getMappedPort(6379).toString())
    }
}
