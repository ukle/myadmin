package me.star.modules.maint.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Data
public class DeployHistoryDto implements Serializable {

	@ApiModelProperty(value = "ID")
    private String id;

	@ApiModelProperty(value = "应用名称")
    private String appName;

	@ApiModelProperty(value = "部署IP")
    private String ip;

	@ApiModelProperty(value = "部署时间")
	private Timestamp deployDate;

	@ApiModelProperty(value = "部署人员")
	private String deployUser;

	@ApiModelProperty(value = "部署编号")
	private Long deployId;
}
