package sample;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by theo on 24/11/16.
 */
public class Player {
    private ArrayList<Card> cards = new ArrayList<>();

    public Player(){

    }

    public void addCardsToAPlayer(Card c){
        cards.add(c);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
