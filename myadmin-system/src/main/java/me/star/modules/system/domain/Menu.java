package me.star.modules.system.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.star.base.BaseEntity;
import me.star.modules.system.domain.vo.MenuVo;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-12-17
 */
@Entity
@Getter
@Setter
@Table(name = "sys_menu")
public class Menu extends BaseEntity implements Serializable {

    @Id
    @Column(name = "menu_id")
    @NotNull(groups = {Update.class})
    @ApiModelProperty(value = "ID", hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JSONField(serialize = false)
    @ManyToMany(mappedBy = "menus")
    @ApiModelProperty(value = "菜单角色")
    private Set<Role> roles;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @Column(name = "name")
    @ApiModelProperty(value = "菜单组件名称")
    private String componentName;

    @ApiModelProperty(value = "排序")
    private Integer orderNum = 999;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "菜单类型，目录、菜单、按钮")
    private Integer type;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @Column(columnDefinition = "bit(1) default 0")
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hideInMenu;

    @ApiModelProperty(value = "上级菜单")
    private Long parentId;

    @ApiModelProperty(value = "父菜单重定向地址(默认第一个子菜单)")
    private String redirect;

    @ApiModelProperty(value = "是否是固定页签")
    private Boolean affix;

    @ApiModelProperty(value = "是否存在于面包屑")
    private Boolean hideInBreadcrumb;

    @ApiModelProperty(value = "是否需要显示所有的子菜单")
    private Boolean hideChildrenInMenu;

    @ApiModelProperty(value = "是否保活")
    private Boolean keepAlive;

    @ApiModelProperty(value = "全连接跳转模式('_blank' | '_self' | '_parent')")
    private String target;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public MenuVo convertToVO() {
        MenuVo menuVo = new MenuVo();
        menuVo.setId(id);
        menuVo.setTitle(title);
        menuVo.setIcon(icon);
        menuVo.setPath(path);
        menuVo.setComponent(component);
        menuVo.setRedirect(redirect);
        menuVo.setAffix(affix);
        menuVo.setHideInMenu(hideInMenu);
        menuVo.setHideInBreadcrumb(hideInBreadcrumb);
        menuVo.setHideChildrenInMenu(hideChildrenInMenu);
        menuVo.setKeepAlive(keepAlive);
        menuVo.setTarget(target);
        menuVo.setOrderNum(orderNum);
        menuVo.setType(type);
        menuVo.setPermission(permission);
        menuVo.setName(componentName);
        menuVo.setParentId(parentId);
        return menuVo;
    }
}
