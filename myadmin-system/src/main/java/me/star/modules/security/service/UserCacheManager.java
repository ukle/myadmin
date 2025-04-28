package me.star.modules.security.service;

import cn.hutool.core.util.RandomUtil;
import me.star.modules.security.config.LoginProperties;
import me.star.modules.security.service.dto.JwtUserDto;
import me.star.utils.RedisUtils;
import me.star.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author Zheng Jie
 * @description 用户缓存管理
 * @date 2022-05-26
 **/
@Component
public class UserCacheManager {

    @Resource
    private RedisUtils redisUtils;
    @Value("${login.user-cache.idle-time}")
    private long idleTime;

    /**
     * 返回用户缓存
     * @param userName 用户名
     * @return JwtUserDto
     */
    public JwtUserDto getUserCache(String userName) {
        // 转小写
        userName = StringUtils.lowerCase(userName);
        if (StringUtils.isNotEmpty(userName)) {
            // 获取数据
            return redisUtils.get(LoginProperties.cacheKey + userName, JwtUserDto.class);
        }
        return null;
    }

    /**
     *  添加缓存到Redis
     * @param userName 用户名
     */
    @Async
    public void addUserCache(String userName, JwtUserDto user) {
        // 转小写
        userName = StringUtils.lowerCase(userName);
        if (StringUtils.isNotEmpty(userName)) {
            // 添加数据, 避免数据同时过期
            long time = idleTime + RandomUtil.randomInt(900, 1800);
            redisUtils.set(LoginProperties.cacheKey + userName, user, time);
        }
    }

    /**
     * 清理用户缓存信息
     * 用户信息变更时
     * @param userName 用户名
     */
    @Async
    public void cleanUserCache(String userName) {
        // 转小写
        userName = StringUtils.lowerCase(userName);
        if (StringUtils.isNotEmpty(userName)) {
            // 清除数据
            redisUtils.del(LoginProperties.cacheKey + userName);
        }
    }
}
