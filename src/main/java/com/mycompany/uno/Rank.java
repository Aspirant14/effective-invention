/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.uno;

/**
 *
 * @author torre
 */
public enum Rank {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE,
    DRAW_TWO, WILD, WILD_4;
    public Rank flipRank(){
        return switch(this){
            case ZERO ->       WILD_4;
            case ONE ->        WILD;
            case TWO ->        DRAW_TWO;
            case THREE ->      REVERSE;
            case FOUR ->       SKIP;
            case FIVE ->       NINE;
            case SIX ->        EIGHT;
            case SEVEN ->      SEVEN;
            case EIGHT ->      SIX;
            case NINE ->       FIVE;
            case SKIP ->       FOUR;
            case REVERSE ->    THREE;
            case DRAW_TWO ->   TWO;
            case WILD ->       ONE;
            case WILD_4 ->     ZERO;
        };
    }
}
