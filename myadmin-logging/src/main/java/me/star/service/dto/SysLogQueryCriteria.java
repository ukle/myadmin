package me.star.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.Query;
import java.sql.Timestamp;
import java.util.List;

/**
 * 日志查询类
 * @author Zheng Jie
 * @date 2019-6-4 09:23:07
 */
@Data
public class SysLogQueryCriteria {

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    @ApiModelProperty(value = "用户名")
    private String username;

    @Query
    @ApiModelProperty(value = "日志类型")
    private String logType;

    @ApiModelProperty(value = "创建时间")
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
