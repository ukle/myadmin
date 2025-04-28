package me.star.modules.system.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import lombok.RequiredArgsConstructor;
import me.star.domain.vo.EmailVo;
import me.star.exception.BadRequestException;
import me.star.modules.system.service.VerifyService;
import me.star.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

/**
 * @author Zheng Jie
 * @date 2018-12-26
 */
@Service
@RequiredArgsConstructor
public class VerifyServiceImpl implements VerifyService {

    @Value("${code.expiration}")
    private Long expiration;
    private final RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EmailVo sendEmail(String email, String key) {
        EmailVo emailVo;
        String content;
        String redisKey = key + email;
        // 如果不存在有效的验证码，就创建一个新的
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email.ftl");
        String oldCode =  redisUtils.get(redisKey, String.class);
        if(oldCode == null){
            String code = RandomUtil.randomNumbers (6);
            // 存入缓存
            if(!redisUtils.set(redisKey, code, expiration)){
                throw new BadRequestException("服务异常，请联系网站负责人");
            }
            content = template.render(Dict.create().set("code",code));
            // 存在就再次发送原来的验证码
        } else {
            content = template.render(Dict.create().set("code",oldCode));
        }
        emailVo = new EmailVo(Collections.singletonList(email),"墨逸扇艺后台管理系统",content);
        return emailVo;
    }

    @Override
    public void validated(String key, String code) {
        String value = redisUtils.get(key, String.class);
        if(!code.equals(value)){
            throw new BadRequestException("无效验证码");
        } else {
            redisUtils.del(key);
        }
    }
}
