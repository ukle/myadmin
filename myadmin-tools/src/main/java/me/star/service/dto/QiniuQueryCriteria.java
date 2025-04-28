package me.star.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-6-4 09:54:37
 */
@Data
public class QiniuQueryCriteria{

    @ApiModelProperty(value = "名称查询")
    @Query(type = Query.Type.INNER_LIKE)
    private String key;

    @ApiModelProperty(value = "创建时间")
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
