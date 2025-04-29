package me.star.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.star.constant.enums.CodeEnum;

/**
 * 业务异常
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {
    private String code;

    private String message;

    public BizException() {
    }

    public BizException(String message) {
        this("1", message);
    }

    public BizException(CodeEnum codeEnum) {
        this(codeEnum.code, codeEnum.message);
    }

    public BizException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
