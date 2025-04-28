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
public class DatabaseQueryCriteria{

	@ApiModelProperty(value = "模糊")
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
	@ApiModelProperty(value = "数据库连接地址")
    private String jdbcUrl;

	@ApiModelProperty(value = "创建时间")
	@Query(type = Query.Type.BETWEEN)
	private List<Timestamp> createTime;
}
