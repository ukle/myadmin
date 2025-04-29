package me.star.modules.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 构建前端路由时用到
 * @author Zheng Jie
 * @date 2018-12-20
 */
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class MenuVo implements Serializable {

    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "绑定的哪个组件，默认自带的组件类型分别是：Iframe、RouteView和ComponentError")
    private String component;

    @ApiModelProperty(value = "父菜单重定向地址(默认第一个子菜单)")
    private String redirect;

    @ApiModelProperty(value = "是否是固定页签")
    private Boolean affix;

    @ApiModelProperty(value = "上级菜单")
    private Long parentId;

    @ApiModelProperty(value = "同路由中的name，主要是用于保活的左右")
    private String name;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hideInMenu;

    @ApiModelProperty(value = "是否存在于面包屑")
    private Boolean hideInBreadcrumb;

    @ApiModelProperty(value = "是否需要显示所有的子菜单")
    private Boolean hideChildrenInMenu;

    @ApiModelProperty(value = "是否保活")
    private Boolean keepAlive;

    @ApiModelProperty(value = "全连接跳转模式('_blank' | '_self' | '_parent')")
    private String target;

    @ApiModelProperty(value = "排序")
    private Integer orderNum = 999;

    @ApiModelProperty(value = "菜单类型，目录、菜单、按钮")
    private Integer type;

    @ApiModelProperty(value = "权限标识")
    private String permission;

}
