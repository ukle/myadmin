package me.star.modules.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 构建前端路由时用到
 * @author Zheng Jie
 * @date 2018-12-20
 */
@Data
public class MenuVo implements Serializable {

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "隐藏状态")
    private Boolean hidden;

    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "总是显示")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "元数据")
    private MenuMetaVo meta;

    @ApiModelProperty(value = "子路由")
    private List<MenuVo> children;
}
