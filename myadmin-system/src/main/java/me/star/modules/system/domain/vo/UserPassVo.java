package me.star.modules.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改密码的 Vo 类
 * @author Zheng Jie
 * @date 2019年7月11日13:59:49
 */
@Data
public class UserPassVo {

    @ApiModelProperty(value = "旧密码")
    private String oldPass;

    @ApiModelProperty(value = "新密码")
    private String newPass;
}
