package me.star.modules.system.service.dto;

import lombok.Getter;
import lombok.Setter;
import me.star.base.BaseData;
import me.star.base.BaseEntity;
import me.star.modules.system.domain.Menu;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author star chou
 * @date 2018-12-17
 */
@Getter
@Setter
public class MenuDto implements BaseData {

    @NotNull(groups = {BaseEntity.Update.class})
    private Long id;

    private Long parentId;
    private String title;
    private List<Long> roleId;
    private Integer orderNum;
    private String icon;
    private Integer routerType;
    private String component;
    private String redirect;
    private String path;
    private String url;
    private String target;
    private Boolean affix;
    private Boolean keepAlive;
    private Boolean hideInMenu;
    private Boolean isDisable;

    public void mergeToEntity(Menu menu) {
        menu.setTitle(this.getTitle());
        menu.setComponent(this.getComponent());
        menu.setPath(this.getPath());
        menu.setIcon(this.getIcon());
        menu.setParentId(this.getParentId());
        menu.setOrderNum(this.getOrderNum());
        menu.setHideInMenu(this.getHideInMenu());
//        menu.setComponentName(this.getComponentName());
//        menu.setPermission(this.getPermission());
//        menu.setType(this.getType());
    }
}
