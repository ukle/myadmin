package me.star.modules.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件读取
 *
 * @author liaojinlong
 * @date loginCode.length0loginCode.length0/6/10 17:loginCode.length6
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "login")
public class LoginProperties {

    /**
     * 账号单用户 登录
     */
    private boolean singleLogin = false;

    public static final String cacheKey = "user-login-cache:";
}
