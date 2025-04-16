package com.example.bysj.entity;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    /**
     * id
     */
    public Integer id;
    /**
     * 名称
     */
    public String name;
    /**
     * 路径
     */
    public String path;
    /**
     * 父id ，根节点为0
     */
    public Integer parentId;
    /**
     * 子节点信息
     */
    public List<Menu> childList;

    public Menu() {
    }

    public Menu(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Menu(Integer id, String name, Integer parentId, List<Menu> childList) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.childList = childList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildList() {
        return childList;
    }

    public void setChildList(List<Menu> childList) {
        this.childList = childList;
    }

    /**
     * 递归查询子节点
     *
     * @param root 根节点
     * @param all  所有节点
     * @return 根节点信息
     */
    private static List<Menu> getChildrens(Menu root, List<Menu> all) {
        List<Menu> children = all.stream().filter(m -> {
            return Objects.equals(m.getParentId(), root.getId());
        }).map((m) -> {
                    m.setChildList(getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }

    public static List<Menu> getFilePathTree(List<String> paths) {
        Map<String, Integer> map = new LinkedHashMap<>();
        Integer id = 1;
        int nonNullCount = 0;
        for (String path : paths){
            if (path != null) {
                nonNullCount++;
            }
            else {
                break;
            }
        }
        for (int i = 0; i < nonNullCount; i++) {
            String[] path = paths.get(i).split("\\\\");
            String p = "";
            for (int j = 0; j < path.length; j++) {
                p += path[j] + "/";
                if (!map.containsKey(p.substring(0, p.length() - 1))) {
                    map.put(p.substring(0, p.length() - 1), id++);
                }
            }
        }

        List<Menu> menus = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Menu menu = new Menu();
            Integer values = entry.getValue();
            String[] keys = entry.getKey().split("/");
            menu.setId(values);
            if (keys.length == 1) {
                menu.setParentId(0);
                menu.setName(keys[0]);
                menu.setPath(keys[0]);
            } else {
                String path = "";
                for (int i = 0; i < keys.length - 1; i++) {
                    path += keys[i] + "/";
                }
                menu.setName(keys[keys.length - 1]);
                menu.setPath(String.join("/", keys));
                path = path.substring(0, path.length() - 1);
                menu.setParentId(map.get(path));
            }
            menus.add(menu);
        }
        //获取父节点
        List<Menu> collect = menus.stream().filter(m -> m.getParentId() == 0).map(
                (m) -> {
                    m.setChildList(getChildrens(m, menus));
                    return m;
                }
        ).collect(Collectors.toList());

        return collect;
    }


    // 文件目录相对路径列子
    private static String[] fileList = new String[]{
            "dist/favicon.ico",
            "dist/index.html",
            "dist/static/css/app.46c00deb.css",
            "dist/static/css/chunk-vendors.b80cec6e.css",

            "dist/static/css/dashboard.65e4cda8.css",
            "dist/static/css/home.c7bb3066.css",
            "dist/static/css/login.89e00d4a.css",
            "dist/static/css/table.cf6aa91f.css",
            "dist/static/fonts/element-icons.535877f5.woff",
            "dist/static/fonts/element-icons.732389de.ttf",
            "dist/static/img/img.146655c9.jpg",
            "dist/static/img/login-bg.e2134055.jpg",
            "dist/static/js/app.db875c52.js",
            "dist/static/js/chunk-vendors.31d72191.js",
            "dist/static/js/dashboard.988c89e7.js",
            "dist/static/js/home.482bfd24.js",
            "dist/static/js/login.78ac76b0.js",
            "dist/static/js/table.eac1b16a.js",
            "dist/table.json"
    };

    public static void main(String args[]) throws IOException {
        List<Menu> collect = getFilePathTree(Arrays.asList(fileList));
        System.out.println("-------转json输出结果-------");
        System.out.println(JSON.toJSON(collect));
    }
}