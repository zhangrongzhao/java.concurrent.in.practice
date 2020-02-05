package com.zrz.chapter11;

import java.util.HashSet;
import java.util.Set;

/**锁分解：减小锁粒度***/
public class ServerStatus2 {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatus2(){
        this.users=new HashSet<String>();
        this.queries=new HashSet<String>();
    }

    public  void addUser(String user){
        synchronized(users){
            this.users.add(user);
        }
    }
    public void addQuery(String query){
        synchronized(queries){
            this.queries.add(query);
        }
    }

    public  void removeUser(String user){
        synchronized(users){
            this.users.remove(user);
        }
    }
    public  void removeQuery(String query){
        synchronized(queries){
            this.queries.remove(query);
        }
    }
}
