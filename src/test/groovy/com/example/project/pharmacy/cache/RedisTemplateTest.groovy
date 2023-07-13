package com.example.project.pharmacy.cache

import com.example.project.AbstractIntegrationBaseTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate

class RedisTemplateTest extends AbstractIntegrationBaseTest{

    @Autowired
    private RedisTemplate redisTemplate

    def "RedisTemplate String Operations"() {
        given:
        def valueOperation = redisTemplate.opsForValue()
        def key = "StringKey"
        def value = "hello"

        when:
        valueOperation.set(key, value)

        then:
        def result = valueOperation.get(key)
        result == value
    }

    def "RedisTemplate set operations"() {
        given:
        def setOperation = redisTemplate.opsForSet()
        def key = "setKey"

        when:
        setOperation.add(key, "h", "e", "l", "l", "o")

        then:
        def size = setOperation.size(key)
        size == 4
    }

    def "RedisTemplate hash operations"() {
        given:
        def hashOperation = redisTemplate.opsForHash()
        def key = "hahshkey"

        when:
        hashOperation.put(key, "subkey", "value")

        then:
        def result = hashOperation.get(key, "subkey")
        result == "value"

        def entries = hashOperation.entries(key)
        entries.keySet().contains("subkey")
        entries.values().contains("value")

        def size = hashOperation.size(key)
        size == entries.size()
    }
}
