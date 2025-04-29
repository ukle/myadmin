package me.star.handler;

import cn.hutool.core.io.resource.InputStreamResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.star.annotation.rest.UnifiedRestResponse;
import me.star.base.ResultVO;
import me.star.exception.BizException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 返回数据处理格式化
 **/
@RestControllerAdvice(basePackages = {"me.star"})
public class ResponseObjectHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getGenericParameterType().equals(ResultVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object res = bodyFormat(body, returnType);
        //返回字符串
        if (returnType.getGenericParameterType().equals(String.class)) {
            if (res instanceof String) {
                return body;
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    response.getHeaders().set("Content-Type", "application/json;charset=UTF-8");
                    return objectMapper.writeValueAsString(ResultVO.success(body));
                } catch (JsonProcessingException e) {
                    throw new BizException("Json转换异常");
                }
            }
            //返回文件对象
        } else if (body instanceof InputStreamResource) {
            return body;
        } else {
            if (selectedContentType.toString().equals("application/json")) {
                response.getHeaders().set("Content-Type", "application/json;charset=UTF-8");
            }
            return res;
        }
    }

    /**
     * 返回数据格式化
     *
     * @param body       返回数据
     * @param returnType 返回类型
     * @return
     */
    public Object bodyFormat(Object body, MethodParameter returnType) {
        if (!isFormat(returnType)) {
            return body;
        } else {
            return body instanceof ResultVO ? body : ResultVO.success(body);
        }
    }

    /**
     * 判断是否需要格式化，配置了UnifiedRestResponse注解的类格式化返回数据格式
     *
     * @param returnType 返回类型
     * @return
     */
    public boolean isFormat(MethodParameter returnType) {
        UnifiedRestResponse typeAnnotation = returnType.getContainingClass().getAnnotation(UnifiedRestResponse.class);
        UnifiedRestResponse MethodAnnotation = returnType.getMethodAnnotation(UnifiedRestResponse.class);
        if (typeAnnotation != null) {
            return typeAnnotation.formatResult();
        }
        if (MethodAnnotation != null) {
            return MethodAnnotation.formatResult();
        }
        return false;
    }
}
