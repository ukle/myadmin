package me.star.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.star.annotation.Query;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Data
public class DictDetailQueryCriteria {

    @ApiModelProperty(value = "字典标签")
    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @ApiModelProperty(value = "字典名称")
    @Query(propName = "name",joinName = "dict")
    private String dictName;
}
