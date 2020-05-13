package com.nh.server.web;

import com.nh.server.entity.Entity;
import com.nh.server.mapping.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Classname WebContext
 * @Description TODO
 * @Date 2020/5/13 11:10 AM
 * @Created by nihui
 */
public class WebContext {

    private List<Entity> entitys = null;
    private List<Mapping> mappings = null;

    private Map<String,String> entityMap = new HashMap<>();
    private Map<String,String> mappingMap = new HashMap<>();
    public WebContext(List<Entity> entitys,List<Mapping> mappings){
        this.entitys = entitys;
        this.mappings = mappings;

        for (Entity entity:entitys) {
            entityMap.put(entity.getName(),entity.getClz());
        }

        for (Mapping mapping: mappings) {
            Set<String> pattrens = mapping.getPatterns();
            for (String pattern: pattrens) {
                mappingMap.put(pattern,mapping.getName());
            }
        }
    }

    public String getClz(String pattern){
        String name = entityMap.get(mappingMap.get(pattern));
        return name == null?null:name;
    }
}
