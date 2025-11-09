package com.cfyusacapraz.agiletool.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.validation.annotation.Validated;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Base64;

@ConfigurationProperties(prefix = PropertiesPrefixConstants.JWT_PREFIX)
@Data
@Validated
public class JwtProperties {

    @NotBlank
    private String secret = generateJwtSecret(256);

    @DurationFormat(DurationStyle.ISO8601)
    @DurationMin(minutes = 1)
    @DurationMax(hours = 2)
    private Duration expiration = Duration.ofMinutes(15);

    @DurationFormat(DurationStyle.ISO8601)
    @DurationMin(hours = 1)
    @DurationMax(days = 30)
    private Duration refreshExpiration = Duration.ofDays(7);

    private static String generateJwtSecret(int bits) {
        if (bits <= 0 || bits % 8 != 0) {
            throw new IllegalArgumentException("bits must be a positive multiple of 8");
        }
        int bytes = bits / 8;
        byte[] secret = new byte[bytes];

        SecureRandom sr;
        try {
            sr = SecureRandom.getInstanceStrong();
        } catch (Exception ex) {
            sr = new SecureRandom(); // fallback
        }

        sr.nextBytes(secret);
        return Base64.getEncoder().withoutPadding().encodeToString(secret);
    }
}
