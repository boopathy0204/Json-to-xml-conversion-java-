package com.securin.converters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class conversion implements XMLJSONConverterI{


        ObjectMapper mapper =new ObjectMapper();
    @Override
    public void convertJSONtoXML(File json, File xml) throws IOException{
        String readfile ="";
        try{
            readfile = Files.readString(json.toPath());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try{
            String js = convert(readfile);
            Files.writeString(xml.toPath(),js);
        }
        catch(IOException e){
            throw new RuntimeException("Error converting JSON to XML",e);
        }
    }
    public String convert(Object data){
        StringBuilder sp= new StringBuilder();
        try{
            if(data instanceof String){
                String str = (String) data;
                str=str.trim();
                if(str.startsWith("{")) {
                    Map<String, Object> mp = mapper.readValue(str, new TypeReference<LinkedHashMap<String, Object>>() {
                    });
                    data =mp;
                }
                else if(str.startsWith("[")){
                    List<Object> mp=mapper.readValue(str,new TypeReference<ArrayList<Object>>(){});
                }
            }
        }
        catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
        if(data instanceof Map){
            sp.append("<object>");
            Map<String,Object> obj = (Map<String,Object>) data;
            for(String key : obj.keySet()){
                sp.append(inner_object(key,obj.get(key)));
            }
            sp.append("</object>");
        }
        else if(data instanceof  List){
            sp.append("<array>");
            List<Object> list = (List<Object>) data;
            for(int i=0;i<list.size();i++){
                sp.append(inner_list(list.get(i)));
            }
            sp.append("</array>");
        }
        else if(data instanceof Number){
            sp.append("<number>").append(data).append("</number>");
        }
        else if(data instanceof String){
            sp.append("<string>").append(data).append("</string>");
        }
        else if(data instanceof Boolean){
            sp.append("<boolean>").append(data).append("</boolean>");
        }
        else{
            sp.append("<null/>");
        }
        return sp.toString();
    }
    public String inner_object(String key,Object value){
        StringBuilder sp=new StringBuilder();
        if(value instanceof Map){
            sp.append("<object name = \"").append(key+"\">");
            Map<String,Object> obj =(Map<String,Object>) value;
            for(String key1 : obj.keySet()){
                sp.append(inner_object(key1,obj.get(key1)));
            }
            sp.append("</object>");
        }
        else if(value instanceof List){
            sp.append("<array name = \"").append(key+"\">");
            List<Object> list=(List<Object>) value;
            for(int i=0;i<list.size();i++){
                sp.append(inner_list(list.get(i)));
            }
            sp.append("</array>");
        }
        else if(value instanceof Number){
            sp.append("<number name=\"").append(key+"\">").append(value).append("</number>");
        }
        else if(value instanceof String){
            sp.append("<string name=\"").append(key+"\">").append(value).append("</string>");
        }
        else if(value instanceof Boolean) {
            sp.append("<boolean name=\"").append(key+"\">").append(value).append("</boolean>");
        }
        return sp.toString();
    }
    public String inner_list(Object value){
        StringBuilder sp=new StringBuilder();
        if(value instanceof Map){
            sp.append(convert(value));
        }
        else if(value instanceof List){
            sp.append(convert(value));
        }
        else if(value instanceof Number){
            sp.append("<number>").append(value).append("</number>");
        }
        else if(value instanceof String){
            sp.append("<string>").append(value).append("</string>");
        }
        else if(value instanceof Boolean) {
            sp.append("<boolean>").append(value).append("</boolean>");
        }
        return sp.toString();
    }
}
