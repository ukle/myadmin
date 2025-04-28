package me.star.modules.maint.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.Query;
import java.sql.Timestamp;
import java.util.List;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Data
public class DeployHistoryQueryCriteria{

	@ApiModelProperty(value = "模糊查询")
	@Query(blurry = "appName,ip,deployUser")
	private String blurry;

	@Query
	@ApiModelProperty(value = "部署编号")
	private Long deployId;

	@ApiModelProperty(value = "部署时间")
	@Query(type = Query.Type.BETWEEN)
	private List<Timestamp> deployDate;
}
