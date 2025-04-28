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
public class ServerDeployQueryCriteria{

	@ApiModelProperty(value = "模糊查询")
	@Query(blurry = "name,ip,account")
    private String blurry;

	@ApiModelProperty(value = "创建时间")
	@Query(type = Query.Type.BETWEEN)
	private List<Timestamp> createTime;
}
