package me.star.modules.system.service;

import me.star.domain.vo.EmailVo;

/**
 * @author Zheng Jie
 * @date 2018-12-26
 */
public interface VerifyService {

    /**
     * 发送验证码
     * @param email /
     * @param key /
     * @return /
     */
    EmailVo sendEmail(String email, String key);


    /**
     * 验证
     * @param code /
     * @param key /
     */
    void validated(String key, String code);
}
