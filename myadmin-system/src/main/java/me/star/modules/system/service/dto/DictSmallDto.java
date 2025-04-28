package me.star.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Getter
@Setter
public class DictSmallDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;
}
