package com.krishna.coding.mf.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazlecastCacheConfig {
    @Bean
    public Config configure() {
        return new Config().setInstanceName("hazlecast-insatnce")
                .addMapConfig(new MapConfig().setName("MFScheme")
                        //.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        //.setEvictionPolicy(EvictionPolicy.LRU)
                        .setTimeToLiveSeconds(2000));
    }
}
