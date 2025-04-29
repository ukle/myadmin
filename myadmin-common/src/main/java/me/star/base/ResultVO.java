package me.star.base;

import cn.hutool.core.util.ObjectUtil;
import lombok.*;
import me.star.constant.enums.CodeEnum;

/**
 * Description 统一返回结果
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ResultVO<T> {
    /**
     * 请求状态
     */
    private Integer status;

    /**
     * 请求响应代码，如：0-成功，1-失败
     */
    private String code;

    /**
     * 请求返回信息描述或异常信息
     */
    private String message;

    /**
     * 请求返回时间戳
     */
    private long ts;

    /**
     * 接口请求返回业务对象数据
     */
    private T data;

    /**
     * 请求成功，对返回结果进行封装
     */
    public static <T> ResultVO<T> success(T data) {
        return build(CodeEnum.SUCCESS.code, CodeEnum.SUCCESS.message, data);
    }

    /**
     * 请求失败，对返回结果进行封装
     */
    public static ResultVO<?> failed(String message) {
        return build(CodeEnum.FAIL.code, message, null);
    }

    /**
     * 请求失败，对返回结果进行封装
     */
    public static <T> ResultVO<T> failed(CodeEnum codeEnum, String message) {
        return build(codeEnum.getCode(), message, null);
    }

    /**
     * 请求失败，对返回结果进行封装
     */
    public static <T> ResultVO<T> failed(String code, String message) {
        return build(ObjectUtil.isEmpty(code) ? CodeEnum.FAIL.code : code, message, null);
    }

    private static <T> ResultVO<T> build(String code, String message, T data) {
        ResultVO<T> vo = new ResultVO<>();
        vo.setStatus(200);
        vo.setCode(code);
        vo.setMessage(message);
        vo.setData(data);
        vo.setTs(System.currentTimeMillis());
        return vo;
    }

    /**
     * 请求成功，对返回结果进行封装
     */
    public static <T> ResultVO<T> success() {
        return build(CodeEnum.SUCCESS.code, CodeEnum.SUCCESS.message, null);
    }

    public static <T> ResultVO<T> failed(String message, T data) {
        return build(CodeEnum.FAIL.code, message, data);
    }
}
