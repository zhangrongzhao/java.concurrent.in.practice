package com.zrz.chapter11;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class AttributeStore {
    private final Map<String,String> attributes = new HashMap<String,String>();
    private final Map<String,String> attributes2 = Collections.synchronizedMap(attributes);
    private final Map<String,String> attributes3 = new ConcurrentHashMap<String, String>();

    /** 同步方法：锁范围比较大，锁持有时间长**/
    public synchronized boolean userLocationMatches(String name,String regexp){
        String key="users."+name+".location";
        String location=attributes.get(key);
        if(location==null){
            return false;
        }else{
            return Pattern.matches(regexp,location);
        }
    }

    /***缩小锁范围：减少锁持有时间***/
    public boolean userLocationMatches2(String name,String regexp){
        String key="users."+name+".location";
        String location=null;
        synchronized (this){
            location=attributes.get(key);
        }
        if(location==null){
            return false;
        }else{
            return Pattern.matches(regexp,location);
        }
    }

    /****同步容器***/
    public boolean userLocationMatches3(String name,String regexp){
        String key="users."+name+".location";
        String location=attributes2.get(key);
        if(location==null){
            return false;
        }else{
            return Pattern.matches(regexp,location);
        }
    }

    /****并发容器***/
    public boolean userLocationMatches4(String name,String regexp){
        String key="users."+name+".location";
        String location=attributes3.get(key);
        if(location==null){
            return false;
        }else{
            return Pattern.matches(regexp,location);
        }
    }
}
