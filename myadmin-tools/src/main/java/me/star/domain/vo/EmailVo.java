package me.star.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 发送邮件时，接收参数的类
 * @author 郑杰
 * @date 2018/09/28 12:02:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVo {

    @NotEmpty
    @ApiModelProperty(value = "收件人")
    private List<String> tos;

    @NotBlank
    @ApiModelProperty(value = "主题")
    private String subject;

    @NotBlank
    @ApiModelProperty(value = "内容")
    private String content;
}
