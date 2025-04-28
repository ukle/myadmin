package me.star.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.Query;

/**
 * @author Zheng Jie
 * 公共查询类
 */
@Data
public class DictQueryCriteria {

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "name,description")
    private String blurry;
}
