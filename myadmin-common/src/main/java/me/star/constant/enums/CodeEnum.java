package me.star.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * 验证码业务场景对应的 Redis 中的 key
 * </p>
 * @author Zheng Jie
 * @date 2020-05-02
 */
@ToString
@AllArgsConstructor
@Getter
public enum CodeEnum {

    SUCCESS("0", "成功"),

    FAIL("1", "失败"),
    EXCEPTION("999", "接口异常"),

    TOKEN_EXPIRED("1000", "token过期"),
    TOKEN_INVALID("1001", "token无效"),

    NO_AUTH("2000", "访问此资源需要身份验证"),
    NO_RIGHT("2001", "无权限访问此资源"),


    /* 通过手机号码重置邮箱 */
    PHONE_RESET_EMAIL_CODE("phone_reset_email_code_", "通过手机号码重置邮箱"),

    /* 通过旧邮箱重置邮箱 */
    EMAIL_RESET_EMAIL_CODE("email_reset_email_code_", "通过旧邮箱重置邮箱"),

    /* 通过手机号码重置密码 */
    PHONE_RESET_PWD_CODE("phone_reset_pwd_code_", "通过手机号码重置密码"),

    /* 通过邮箱重置密码 */
    EMAIL_RESET_PWD_CODE("email_reset_pwd_code_", "通过邮箱重置密码");

    public final String code;
    public final String message;
}
