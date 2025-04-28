package me.star.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2019-5-22
 */
@Data
public class SysLogSmallDto implements Serializable {

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "请求IP")
    private String requestIp;

    @ApiModelProperty(value = "耗时")
    private Long time;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;
}
