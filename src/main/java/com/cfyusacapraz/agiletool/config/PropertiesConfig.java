package com.cfyusacapraz.agiletool.config;

import com.cfyusacapraz.agiletool.properties.JwtProperties;
import com.cfyusacapraz.agiletool.properties.SeedAdminProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SeedAdminProperties.class, JwtProperties.class})
public class PropertiesConfig {
}
