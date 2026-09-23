/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.uno;

/**
 *
 * @author torre
 */
public enum Color {
    // Natural ordering according to online UNO game.
    RED, GREEN, YELLOW, BLUE, BLACK;
    public Color flipColor(){
        return switch(this){
            case RED -> BLACK;
            case GREEN -> BLUE;
            case YELLOW -> YELLOW;
            case BLUE -> GREEN;
            case BLACK -> RED;
        };
    }
}
