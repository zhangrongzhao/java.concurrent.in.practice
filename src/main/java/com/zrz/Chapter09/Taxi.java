package com.zrz.Chapter09;

import java.util.HashSet;
import java.util.Set;

class Point{}
public class Taxi {
    private Point location,destination;
    private final Dispatcher dispatcher;
    public Taxi(Dispatcher dispatcher){
        this.dispatcher=dispatcher;
    }
    public synchronized Point getLocation(){
        return this.location;
    }

    public void setLocation(Point location){
        boolean reachedDestination;
        synchronized(this){
            this.location=location;
            reachedDestination=location.equals(destination);
        }
        if(reachedDestination){
            dispatcher.notifyAvailable(this);
        }
    }
}

class Dispatcher{
    private final Set<Taxi> taxis;
    private final Set<Taxi> avaliableTaxis;
    public Dispatcher(){
        this.taxis=new HashSet<Taxi>();
        this.avaliableTaxis=new HashSet<Taxi>();
    }
    public synchronized void notifyAvailable(Taxi taxi){
        avaliableTaxis.add(taxi);
    }
    public Image getImage(){
        Set<Taxi> copy;
        synchronized(this){
            copy=new HashSet<Taxi>(taxis);
        }
        Image image=new Image();
        for(Taxi t:taxis){
            image.drawMarker(t.getLocation());
        }
        return image;
    }
}

class Image{
    public void drawMarker(Point point){}
}
