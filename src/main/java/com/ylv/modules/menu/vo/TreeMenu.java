package com.ylv.modules.menu.vo;

import java.util.List;

/**
 * 树形菜单
 */
public class TreeMenu {

    private String id;

    private String name;

    private String menuKey;

    private String url;

    private String icon;

    private Long parentId;

    private List<TreeMenu> childs;

    public TreeMenu(){

    }

    public TreeMenu(String name,String key){
        this.name = name;
        this.menuKey = key;
    }

    public TreeMenu(String name,String key,String url){
        this.name = name;
        this.menuKey = key;
        this.url = url;
    }

    public boolean hasChild(){

        if(getChilds() != null && !getChilds().isEmpty()){
            return true;
        }

        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TreeMenu> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeMenu> childs) {
        this.childs = childs;
    }
}
