package me.star.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.star.base.BaseDTO;

import java.io.Serializable;

/**
* @author Zheng Jie
* @date 2019-03-29
*/
@Getter
@Setter
@NoArgsConstructor
public class JobDto extends BaseDTO implements Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "岗位排序")
    private Integer jobSort;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    public JobDto(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
