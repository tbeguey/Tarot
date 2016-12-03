package sample;


import org.junit.*;
import java.util.ArrayList;

import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;


/**
 * Created by oka on 25/11/16.
 */
public class UnitTest {

    @Test
    public void addCardPlayer()
    {
        Player p1=new Player();
        Player p2 = new Player();
        Picture img = new Picture();

        Card c = new Card(img,0,0);
        ArrayList<Card> cards1 = p1.getCards();
        ArrayList<Card> cards2 = p2.getCards();

        cards1.clear();
        cards2.clear();
        boolean equal = (cards1.size()==cards2.size());
        p1.addCardsToAPlayer(c);

        assert (cards1.size()!=cards2.size() && equal == true);

    }

    @Test
    public void testAddCardPlayer2()
    {
        Player p1=new Player();
        Player p2 = new Player();
        Picture img = new Picture();
        Card c = new Card(img,0,0);
        p1.addCardsToAPlayer(c);
        assertFalse(p1.getCards() == p2.getCards());
    }
    @Test
    public void testAddCardDog()
    {
        Picture img = new Picture();
        Card c = new Card(img,0,0);

        ArrayList<Card> dog = new ArrayList<>();
        dog.clear();
        int tg = dog.size();
        dog.add(c);

        assertFalse(tg ==dog.size());




    }


    @Test
    public void testSortDeck()
    {
        TypeCard t = TypeCard.Pique;



        //Card c = new Card(img,0,0);
        Player p = new Player();
        //p.addCardsToAPlayer(c);


    }
    @Test
    public void testDrawPlayerException()
    {

    }

}
