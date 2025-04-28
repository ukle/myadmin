package me.star.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.Query;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Zheng Jie
 * 公共查询类
 */
@Data
public class MenuQueryCriteria {

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "title,component,permission")
    private String blurry;

    @ApiModelProperty(value = "创建时间")
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @ApiModelProperty(value = "PID空查询", hidden = true)
    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    @Query
    @ApiModelProperty(value = "PID")
    private Long pid;
}
