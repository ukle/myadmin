package me.star.modules.system.service;

import me.star.modules.system.domain.Menu;
import me.star.modules.system.service.dto.MenuDto;
import me.star.modules.system.service.dto.MenuQueryCriteria;

import java.util.List;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-12-17
 */
public interface MenuService {

    /**
     * 查询全部数据
     * @param criteria 条件
     * @param isQuery /
     * @throws Exception /
     * @return /
     */
    List<MenuDto> queryAll(MenuQueryCriteria criteria, Boolean isQuery) throws Exception;

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    MenuDto findById(long id);

    /**
     * 创建
     * @param menuDto /
     */
    void create(MenuDto menuDto);

    /**
     * 编辑
     * @param resources /
     */
    void update(MenuDto resources);

    /**
     * 获取所有子节点，包含自身ID
     * @param menuList /
     * @param menuSet /
     * @return /
     */
    Set<Menu> getChildMenus(List<Menu> menuList, Set<Menu> menuSet);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    Menu findOne(Long id);

    /**
     * 删除
     * @param menuSet /
     */
    void delete(Set<Menu> menuSet);

    /**
     * 懒加载菜单数据
     * @param pid /
     * @return /
     */
    List<MenuDto> getMenus(Long pid);

    /**
     * 根据当前用户获取菜单
     * @param currentUserId /
     * @return /
     */
    List<Menu> findByUser(Long currentUserId);
}
