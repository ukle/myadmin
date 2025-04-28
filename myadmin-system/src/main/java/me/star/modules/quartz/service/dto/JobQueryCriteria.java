package me.star.modules.quartz.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.Query;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-6-4 10:33:02
 */
@Data
public class JobQueryCriteria {

    @ApiModelProperty(value = "任务名称")
    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    @Query
    @ApiModelProperty(value = "是否成功")
    private Boolean isSuccess;

    @ApiModelProperty(value = "创建时间")
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
