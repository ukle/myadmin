package me.star.modules.security.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 在线用户
 * @author Zheng Jie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUserDto {

    @ApiModelProperty(value = "Token编号")
    private String uid;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "岗位")
    private String dept;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "token")
    private String key;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;
}
