package sample;

import java.util.ArrayList;

public class Model
{
    private View view;
    private ArrayList<Card> hand = new ArrayList();
    private ArrayList<Card> dog = new ArrayList();

    public Model(View v)
    {
        this.view = v;
    }

    public void addCardHand(Card c)
    {
        this.hand.add(c);
    }

    public void addCardDog(Card c)
    {
        this.dog.add(c);
    }

    public ArrayList<Card> getHand()
    {
        return this.hand;
    }

    public ArrayList<Card> getDog()
    {
        return this.dog;
    }

}
