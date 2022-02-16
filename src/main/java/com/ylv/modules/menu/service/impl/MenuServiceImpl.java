package com.ylv.modules.menu.service.impl;

import com.ylv.modules.menu.service.MenuService;
import com.ylv.modules.menu.vo.TreeMenu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public TreeMenu getUserMenu(Long userId) {

        TreeMenu root = new TreeMenu("功能菜单","main");

        TreeMenu a = new TreeMenu("试剂库存管理","reagent_manager","/reagent/list");

        if(root.getChilds() == null){
            root.setChilds(new ArrayList<>());
        }

        TreeMenu b = new TreeMenu("系统管理","system_manager");

        TreeMenu b1 = new TreeMenu("字典管理","dict_manager","dict/list");

        TreeMenu b2 = new TreeMenu("用户管理","system_user","user/list");
        b.setChilds(new ArrayList<>());

        b.getChilds().add(b1);
        b.getChilds().add(b2);

        root.getChilds().add(a);
        root.getChilds().add(b);

        return root;
    }

    @Override
    public List<TreeMenu> getUserMenus(Long userId) {

        List<TreeMenu> list = new ArrayList<>();

        TreeMenu a = new TreeMenu("试剂库存管理","reagent_manager","stock/list");
        a.setId("1");
        a.setIcon("el-icon-coin");

        TreeMenu b = new TreeMenu("试剂信息管理","reagent_manager","/reagent/list");
        b.setId("2");
        b.setIcon("el-icon-s-tools");


        TreeMenu c = new TreeMenu("用户管理","system_user","user/list");
        c.setId("3");
        c.setIcon("el-icon-user");

        list.add(a);
        list.add(b);
        list.add(c);

        return list;
    }
}
