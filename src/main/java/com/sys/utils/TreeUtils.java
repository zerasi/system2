package com.sys.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sys.entity.SysPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeUtils {

    /**
     * 菜单树
     *
     * @param parentId
     * @param permissionsAll
     * @param array
     */
    public static void setPermissionsTree(Integer parentId, List<SysPermission> permissionsAll, JSONArray array) {
        for (SysPermission per : permissionsAll) {
            if (per.getParentId()!=null && per.getParentId().equals(parentId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getParentId() !=null && p.getParentId().equals(per.getId())).findAny() != null) {
                    //如果存在有父节点为 per.id 则增加节点children 将所有父节点为 per.id 放入children中
                    JSONArray child = new JSONArray();
                    parent.put("children", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("s");
        list.add("s");
        list.add("c");
        list.add("f");
        list.add("r");
        list.add(null);
        //List<String> list1 = list.stream().filter(p -> p!=null && p.equals("s")).collect(Collectors.toList());
        if(list.stream().filter(p -> p!=null && p.equals("s")).findAny()!=null){
            System.out.println(1);
        }
        list.forEach(p ->{
            System.out.println(p);
        });
    }
}
