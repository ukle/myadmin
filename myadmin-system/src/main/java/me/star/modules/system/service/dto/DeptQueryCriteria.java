package me.star.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.DataPermission;
import me.star.annotation.Query;
import java.sql.Timestamp;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Data
@DataPermission(fieldName = "id")
public class DeptQueryCriteria{

    @ApiModelProperty(value = "名称")
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @Query
    @ApiModelProperty(value = "上级部门")
    private Long pid;

    @ApiModelProperty(value = "PID空查询", hidden = true)
    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    @ApiModelProperty(value = "创建时间")
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
