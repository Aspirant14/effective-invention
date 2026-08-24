/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uno;

/**
 *
 * @author torre
 */
public class Card {
    private Color color;
    private Rank rank;
    public Card(Color c, Rank r){
        color = c;
        rank = r;
    }
    public Card(Rank r){
        if (r.equals(Rank.WILD) || r.equals(Rank.WILD_4)){
            color = Color.BLACK;
            rank = r;
        }
    }
    public Color getColor(){return this.color;}
    public Rank getRank(){return this.rank;}
    @Override
    public String toString(){
        return color.name() + ' ' + rank.name();
    }
}

// Test deck class by making constructors first