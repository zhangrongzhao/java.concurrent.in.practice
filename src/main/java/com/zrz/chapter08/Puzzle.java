package com.zrz.chapter08;

import java.util.Set;

/**
 * 谜题抽象类
 * P:Position 位置类
 * M:Move 移动类
 * **/
public interface Puzzle<P,M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position,M move);
}
