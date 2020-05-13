package com.nh.server.web;

import com.nh.server.entity.Entity;
import com.nh.server.mapping.Mapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname WebHandler
 * @Description TODO
 * @Date 2020/5/13 11:12 AM
 * @Created by nihui
 */
public class WebHandler extends DefaultHandler {
    private List<Entity> entitys;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;
    private String tag;
    private boolean isMapping = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String msg = new String(ch,start,length).trim();
        if (isMapping){
            if (msg.length()>0){
                if (tag.equals("servlet-name")){
                    mapping.setName(msg);
                }else if (tag.equals("url-pattern")){
                    mapping.addPattern(msg);
                }
            }
        }else {
            if (msg.length()>0){
                if (tag.equals("servlet-name")){
                    entity.setName(msg);

                }else if (tag.equals("servlet-class")){
                    entity.setClz(msg);
                }
            }
        }
    }

    @Override
    public void startDocument() throws SAXException {
        entitys = new ArrayList<Entity>();
        mappings = new ArrayList<Mapping>();
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName!=null){
            tag = qName;
            if (tag.equals("servlet")){
                entity = new Entity();
                isMapping = false;
            }else if (tag.equals("servlet-mapping")){
                mapping = new Mapping();
                isMapping = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("servlet")){
            entitys.add(entity);
        }else if (qName.equals("servlet-mapping")){
            mappings.add(mapping);
        }
        tag = null;
    }

    public List<Entity> getEntitys(){
        return entitys;
    }

    public List<Mapping> getMappings(){
        return mappings;
    }
}
