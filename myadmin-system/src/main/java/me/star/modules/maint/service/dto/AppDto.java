package me.star.modules.maint.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.star.base.BaseDTO;
import java.io.Serializable;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Getter
@Setter
public class AppDto extends BaseDTO implements Serializable {

	@ApiModelProperty(value = "ID")
    private Long id;

	@ApiModelProperty(value = "应用名称")
	private String name;

	@ApiModelProperty(value = "端口")
	private Integer port;

	@ApiModelProperty(value = "上传目录")
	private String uploadPath;

	@ApiModelProperty(value = "部署目录")
	private String deployPath;

	@ApiModelProperty(value = "备份目录")
	private String backupPath;

	@ApiModelProperty(value = "启动脚本")
	private String startScript;

	@ApiModelProperty(value = "部署脚本")
	private String deployScript;
}
