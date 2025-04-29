package me.star.modules.security.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.star.modules.security.service.dto.JwtUserDto;

/**
 * @author star chou
 * @date 2025/04/29 11:26
 * @description /
 */
@Data
@AllArgsConstructor
public class AuthInfo {

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "用户信息")
    private JwtUserDto jwtUser;
}
