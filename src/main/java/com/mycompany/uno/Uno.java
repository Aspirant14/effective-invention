/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.uno;
/**
 *
 * @author torre
 */
// Figure out how to run main in a test package instead.
// Latest success: printed out deck of cards.
public class Uno {
    
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        int size = myDeck.size();
        myDeck.shuffle();
        System.out.println(myDeck);
        System.out.println("\n");
        myDeck.shuffle();
        System.out.println(myDeck);
    }
}
