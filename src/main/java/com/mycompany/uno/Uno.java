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
        for (int i = 0; i < myDeck.getLength(); i++)
            System.out.println(myDeck.cards[i]);
    }
}
