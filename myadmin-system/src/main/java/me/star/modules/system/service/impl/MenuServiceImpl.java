package me.star.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import me.star.base.BaseServiceImpl;
import me.star.exception.BadRequestException;
import me.star.exception.EntityExistException;
import me.star.modules.system.domain.Menu;
import me.star.modules.system.domain.Role;
import me.star.modules.system.domain.User;
import me.star.modules.system.repository.MenuRepository;
import me.star.modules.system.repository.UserRepository;
import me.star.modules.system.service.MenuService;
import me.star.modules.system.service.RoleService;
import me.star.modules.system.service.dto.MenuDto;
import me.star.modules.system.service.dto.MenuQueryCriteria;
import me.star.modules.system.service.mapstruct.MenuMapper;
import me.star.utils.CacheKey;
import me.star.utils.QueryHelp;
import me.star.utils.RedisUtils;
import me.star.utils.ValidationUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<MenuRepository, Menu, Long> implements MenuService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final MenuMapper menuMapper;
    private final RoleService roleService;
    private final RedisUtils redisUtils;

    @Override
    public List<MenuDto> queryAll(MenuQueryCriteria criteria, Boolean isQuery) throws Exception {
        Sort sort = Sort.by(Sort.Direction.ASC, "menuSort");
        if (Boolean.TRUE.equals(isQuery)) {
            criteria.setPidIsNull(true);
            List<Field> fields = QueryHelp.getAllFields(criteria.getClass(), new ArrayList<>());
            for (Field field : fields) {
                //设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                Object val = field.get(criteria);
                if ("pidIsNull".equals(field.getName())) {
                    continue;
                }
                if (ObjectUtil.isNotNull(val)) {
                    criteria.setPidIsNull(null);
                    break;
                }
            }
        }
        return menuMapper.toDto(menuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), sort));
    }

    @Override
    public MenuDto findById(long id) {
        String key = CacheKey.MENU_ID + id;
        Menu menu = redisUtils.get(key, Menu.class);
        if (menu == null) {
            menu = menuRepository.findById(id).orElseGet(Menu::new);
            ValidationUtil.isNull(menu.getId(), "Menu", "id", id);
            redisUtils.set(key, menu, 1, TimeUnit.DAYS);
        }
        return menuMapper.toDto(menu);
    }

    /**
     * 用户角色改变时需清理缓存
     *
     * @param currentUserId /
     * @return /
     */
    @Override
    public List<Menu> findByUser(Long currentUserId) {
        String key = CacheKey.MENU_USER + currentUserId;
//        List<Menu> menus = redisUtils.getList(key, Menu.class);
        return menuRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(MenuDto menuDto) {
        if (menuDto.getRouterType() == null) {
            menuDto.setRouterType(0);
        }
        switch (menuDto.getRouterType()) {
            // 普通组件
            case 0 -> {
                if (Objects.equals(menuDto.getComponent(), "")) {
                    menuDto.setComponent("RouteView");
                }
            }
            case 1 -> menuDto.setComponent("Iframe");
            case 2 -> menuDto.setComponent(null);
        }

        Menu menu = menuDto.asViewObject(Menu.class);
        save(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MenuDto menuDto) {
        if (menuDto.getId().equals(menuDto.getParentId())) {
            throw new BadRequestException("上级不能为自己");
        }
        Menu menu = menuRepository.findById(menuDto.getId()).orElseGet(Menu::new);
        ValidationUtil.isNull(menu.getId(), "Permission", "id", menuDto.getId());

        Menu menu1 = menuRepository.findByTitle(menuDto.getTitle());
        if (menu1 != null && !menu1.getId().equals(menu.getId())) {
            throw new EntityExistException(Menu.class, "title", menuDto.getTitle());
        }

        if (menuDto.getParentId().equals(0L)) {
            menuDto.setParentId(null);
        }

        menuDto.mergeToEntity(menu);
        menuRepository.save(menu);
    }

    @Override
    public Set<Menu> getChildMenus(List<Menu> menuList, Set<Menu> menuSet) {
        for (Menu menu : menuList) {
            menuSet.add(menu);
            List<Menu> menus = menuRepository.findByParentIdOrderByOrderNum(menu.getId());
            if (CollUtil.isNotEmpty(menus)) {
                getChildMenus(menus, menuSet);
            }
        }
        return menuSet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Menu> menuSet) {
        for (Menu menu : menuSet) {
            // 清理缓存
            delCaches(menu.getId());
            roleService.untiedMenu(menu.getId());
            menuRepository.deleteById(menu.getId());
        }
    }

    @Override
    public List<MenuDto> getMenus(Long pid) {
        List<Menu> menus;
        if (pid != null && !pid.equals(0L)) {
            menus = menuRepository.findByParentIdOrderByOrderNum(pid);
        } else {
            menus = menuRepository.findByParentIdIsNullOrderByOrderNum();
        }
        return menuMapper.toDto(menus);
    }

    @Override
    public Menu findOne(Long id) {
        Menu menu = menuRepository.findById(id).orElseGet(Menu::new);
        ValidationUtil.isNull(menu.getId(), "Menu", "id", id);
        return menu;
    }

    /**
     * 清理缓存
     *
     * @param id 菜单ID
     */
    public void delCaches(Long id) {
        List<User> users = userRepository.findByMenuId(id);
        redisUtils.del(CacheKey.MENU_ID + id);
        redisUtils.delByKeys(CacheKey.MENU_USER, users.stream().map(User::getId).collect(Collectors.toSet()));
        // 清除 Role 缓存
        List<Role> roles = roleService.findInMenuId(new ArrayList<Long>() {{
            add(id);
        }});
        redisUtils.delByKeys(CacheKey.ROLE_ID, roles.stream().map(Role::getId).collect(Collectors.toSet()));
    }

}
