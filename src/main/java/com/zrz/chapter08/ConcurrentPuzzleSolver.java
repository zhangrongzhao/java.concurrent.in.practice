//package com.zrz.chapter08;
//
//import java.util.List;
//import java.util.concurrent.ConcurrentMap;
//import java.util.concurrent.ExecutorService;
//
//public class ConcurrentPuzzleSolver<P,M> {
//    private final Puzzle<P,M> puzzle;
//    private final ExecutorService exec;
//    private final ConcurrentMap<P,Boolean> seen;
//    final ValueLatch<Node<P,M>> solution =new ValueLatch<Node<P,M>>();
//
//    public List<M> solve()throws InterruptedException{
//        try{
//            P p=puzzle.initialPosition();
//            exec.execute(newTask(p,null,null));
//            //阻塞直到找到答案
//            Node<P,M> solnNode=solution.getValue();
//            return (solnNode==null)?null:solnNode.asMoveList();
//        }finally{
//            exec.shutdown();
//        }
//    }
//
//    protected Runnable newTask(P p,M m,Node<P,M> n){
//        return SolverTask(p,m,n);
//    }
//
//    class SolverTask extends Node<P,M> implements Runnable{
//        private P p;
//        private M m;
//        private Node<P,M> n;
//        public SolverTask(P p,M m,Node<P,M> n){
//            this.p=p;
//            this.m=m;
//            this.n=n;
//        }
//        public void run(){
//            if(solution.isSet()||seen.putIfAbsent(pos,true)!=null){
//                return;//已经找到了解答或者已经遍历了这个位置
//            }
//            if(puzzle.isGoal(pos)){
//                solution.setValue(this);
//            }else{
//                for(M m:puzzle.legalMoves(pos)){
//                    exec.execute(newTask(puzzle.move(pos,m),m,this));
//                }
//            }
//        }
//    }
//}
