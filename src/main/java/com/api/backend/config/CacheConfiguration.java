package com.api.backend.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.api.backend.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.api.backend.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.api.backend.domain.User.class.getName());
            createCache(cm, com.api.backend.domain.Authority.class.getName());
            createCache(cm, com.api.backend.domain.User.class.getName() + ".authorities");
            createCache(cm, com.api.backend.domain.Danhmucnghenghiep.class.getName());
            createCache(cm, com.api.backend.domain.Danhmucnhompttt.class.getName());
            createCache(cm, com.api.backend.domain.Danhmucnhompttt.class.getName() + ".danhmucpttts");
            createCache(cm, com.api.backend.domain.Danhmucpttt.class.getName());
            createCache(cm, com.api.backend.domain.Dmnhomxetnghiem.class.getName());
            createCache(cm, com.api.backend.domain.Dmnhomxetnghiem.class.getName() + ".dmxetnghiems");
            createCache(cm, com.api.backend.domain.Dmxetnghiem.class.getName());
            createCache(cm, com.api.backend.domain.DmnhomCDHA.class.getName());
            createCache(cm, com.api.backend.domain.DmnhomCDHA.class.getName() + ".dmCDHAS");
            createCache(cm, com.api.backend.domain.DmCDHA.class.getName());
            createCache(cm, com.api.backend.domain.DmnhomTDCN.class.getName());
            createCache(cm, com.api.backend.domain.DmnhomTDCN.class.getName() + ".dmTDCNS");
            createCache(cm, com.api.backend.domain.DmTDCN.class.getName());
            createCache(cm, com.api.backend.domain.Dmnhomgiaiphaubenh.class.getName());
            createCache(cm, com.api.backend.domain.Dmnhomgiaiphaubenh.class.getName() + ".dmgiaiphaubenhs");
            createCache(cm, com.api.backend.domain.Dmgiaiphaubenh.class.getName());
            createCache(cm, com.api.backend.domain.Dmloaibenhly.class.getName());
            createCache(cm, com.api.backend.domain.Dmloaibenhly.class.getName() + ".dmnhombenhlies");
            createCache(cm, com.api.backend.domain.Dmloaibenhly.class.getName() + ".dmbenhlies");
            createCache(cm, com.api.backend.domain.Dmnhombenhly.class.getName());
            createCache(cm, com.api.backend.domain.Dmnhombenhly.class.getName() + ".dmbenhlies");
            createCache(cm, com.api.backend.domain.Dmbenhly.class.getName());
            createCache(cm, com.api.backend.domain.Tinh.class.getName());
            createCache(cm, com.api.backend.domain.Tinh.class.getName() + ".huyens");
            createCache(cm, com.api.backend.domain.Tinh.class.getName() + ".cosokhambenhs");
            createCache(cm, com.api.backend.domain.Huyen.class.getName());
            createCache(cm, com.api.backend.domain.Huyen.class.getName() + ".xas");
            createCache(cm, com.api.backend.domain.Huyen.class.getName() + ".cosokhambenhs");
            createCache(cm, com.api.backend.domain.Xa.class.getName());
            createCache(cm, com.api.backend.domain.Xa.class.getName() + ".cosokhambenhs");
            createCache(cm, com.api.backend.domain.Cosokhambenh.class.getName());
            createCache(cm, com.api.backend.domain.DanToc.class.getName());
            createCache(cm, com.api.backend.domain.Danhmuchanhchinh.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
