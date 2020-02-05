package com.zrz.chapter11;

import java.util.HashSet;
import java.util.Set;

public class ServerStatus {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatus(){
        this.users=new HashSet<String>();
        this.queries=new HashSet<String>();
    }

    public synchronized void addUser(String user){this.users.add(user);}
    public synchronized void addQuery(String query){this.queries.add(query);}

    public synchronized void removeUser(String user){this.users.remove(user);}
    public synchronized void removeQuery(String query){this.queries.remove(query);}
}
