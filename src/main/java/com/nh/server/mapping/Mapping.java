package com.nh.server.mapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @Classname Mapping
 * @Description TODO
 * @Date 2020/5/13 11:36 AM
 * @Created by nihui
 */
public class Mapping {
    private String name;
    private Set<String> patterns;




    public Mapping() {
        this.patterns = new HashSet<>();
    }

    public Mapping(String name, Set<String> patterns) {
        this.name = name;
        this.patterns = patterns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<String> patterns) {
        this.patterns = patterns;
    }

    public void addPattern(String pattern){
        this.patterns.add(pattern);
    }
}
