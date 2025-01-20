package com.salesianos.cache.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("pcs") {
            protected ConcurrentMap<Object, Object> createNativeCache() {
                return new java.util.concurrent.ConcurrentHashMap<>() {
                    private final long expirationTime = TimeUnit.MINUTES.toMillis(25);
                    private final java.util.Map<Object, Long> expirationMap = new java.util.HashMap<>();

                    @Override
                    public Object put(Object key, Object value) {
                        expirationMap.put(key, System.currentTimeMillis() + expirationTime);
                        return super.put(key, value);
                    }

                    @Override
                    public Object get(Object key) {
                        if (expirationMap.containsKey(key) && expirationMap.get(key) < System.currentTimeMillis()) {
                            super.remove(key);
                            expirationMap.remove(key);
                        }
                        return super.get(key);
                    }
                };
            }
        };
    }
}
