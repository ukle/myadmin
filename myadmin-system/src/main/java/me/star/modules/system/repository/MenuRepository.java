package me.star.modules.system.repository;

import me.star.base.BaseRepository;
import me.star.modules.system.domain.Menu;

import java.util.List;

/**
 * @author star chou
 * @date 2025-04-29
 */
public interface MenuRepository extends BaseRepository<Menu, Long> {

    /**
     * 根据菜单标题查询
     * @param title 菜单标题
     * @return /
     */
    Menu findByTitle(String title);

    /**
     * 根据菜单的 PID 查询
     * @param pid /
     * @return /
     */
    List<Menu> findByParentIdOrderByOrderNum(long pid);

    /**
     * 查询顶级菜单
     * @return /
     */
    List<Menu> findByParentIdIsNullOrderByOrderNum();

}
