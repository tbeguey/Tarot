package sample;

import java.util.ArrayList;

public class Model
{
    private View view;
    //private ArrayList<Card> hand = new ArrayList();
    private ArrayList<Card> cardsPack = new ArrayList<>();
    private ArrayList<Card> dog = new ArrayList();

    private ArrayList<Player> players = new ArrayList<>();

    public Model(View v) {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        this.view = v;
    }

    public void addCardHand(int idPlayer, Card c){
        players.get(idPlayer).addCardsToAPlayer(c);
        cardsPack.remove(c);
        //this.hand.add(c);
    }

    public void addCardDog(Card c) {
        this.dog.add(c);
        cardsPack.remove(c);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getDog() {
        return this.dog;
    }
}
