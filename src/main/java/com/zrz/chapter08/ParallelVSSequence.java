package com.zrz.chapter08;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;


public class ParallelVSSequence {

    static class Element{ }
    static class Node<T>{
        public T compute(){return null;}
        public List<Node<T>> getChildren(){ return null;}
    }
    private void process(Element element){ }

    public void ProcessSequentiallly(List<Element> elements){
        for(Element e:elements){
            process(e);
        }
    }

    public void processInParallel(Executor exec,List<Element> elements){
        for(final Element e:elements){
            exec.execute(new Runnable() {
                public void run() {
                    process(e);
                }
            });
        }
    }

    public <T> void sequentialRecursive(List<Node<T>> nodes,Collection<T> results){
        for(Node<T> node:nodes){
            results.add(node.compute());
            sequentialRecursive(node.getChildren(),results);
        }
    }

    public <T> void parallelRecursive(final Executor exec,List<Node<T>> nodes,final Collection<T> results){
        for(final Node<T> node:nodes){
            exec.execute(new Runnable() {
                public void run() {
                    results.add(node.compute());
                }
            });
            parallelRecursive(exec,node.getChildren(),results);
        }
    }
}


