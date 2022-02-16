package com.ylv.modules.menu.service;

import com.ylv.modules.menu.vo.TreeMenu;

import java.util.List;

public interface MenuService {

    public TreeMenu getUserMenu(Long userId);

    public List<TreeMenu> getUserMenus(Long userId);
}
