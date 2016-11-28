package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.Collections;

public class Model extends java.util.Observable {
    private View view;
    private ArrayList<Card> cardsDeck = new ArrayList<>();
    private ArrayList<Card> dog = new ArrayList();
    private ArrayList<Player> players = new ArrayList<>();
    private boolean littleDry = false;

    public Model() {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
    }

    public void initialiseCardsDeck(){
        for (int i = 0; i < view.getPictures().size(); i++) {
            Card c = new Card(view.getPictures().get(i), view.getPositionCardX(),view.getPositionCardY());
            cardsDeck.add(c);
        }
        Collections.shuffle(cardsDeck);
    }



    public void addCardHand(int idPlayer){
        if (players.get(idPlayer).getCards().size() != 18){
            Card c = cardsDeck.get(cardsDeck.size()-1);
            players.get(idPlayer).addCardsToAPlayer(c);
            if(idPlayer == 0){
                view.update(c, true);
            }
            cardsDeck.remove(c);
        }
    }

    public void addCardDog() {
        Card c = cardsDeck.get(cardsDeck.size()-1);
        c.setInDog(true);
        this.dog.add(c);
        view.update(c, false);
        cardsDeck.remove(c);
    }

    public void sortDeck(){
        ArrayList<Card> handPique = new ArrayList();
        ArrayList<Card> handCoeur = new ArrayList();
        ArrayList<Card> handCarreau = new ArrayList();
        ArrayList<Card> handTrefle = new ArrayList();
        ArrayList<Card> handAtout = new ArrayList();
        /* Reparti le deck du joueur dans 5 decks différents par rapport à leur couleur */
        for (int i = 0; i < players.get(0).getCards().size(); i++) {
            if (players.get(0).getCards().get(i).getP().getColor() == TypeCard.Pique) {
                handPique.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getP().getColor() == TypeCard.Coeur) {
                handCoeur.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getP().getColor() == TypeCard.Atout) {
                handAtout.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getP().getColor() == TypeCard.Carreau) {
                handCarreau.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getP().getColor() == TypeCard.Trefle) {
                handTrefle.add(players.get(0).getCards().get(i));
            }
        }
        /* Vide le deck du joueur */
        players.get(0).getCards().removeAll(players.get(0).getCards());

        /* Trie les cartes selon leur nombre */
        Collections.sort(handPique);
        Collections.sort(handCoeur);
        Collections.sort(handAtout);
        Collections.sort(handCarreau);
        Collections.sort(handTrefle);

        for (int i = 0; i < handPique.size(); i++) {
            players.get(0).getCards().add(handPique.get(i));
            handPique.get(i).getP().setX(view.getPositionCardX());
            handPique.get(i).getP().setY(view.getPositionCardY());
            view.setPositionCardX(view.getPositionCardX()+150);
            if (players.get(0).getCards().size() == 9) {
                view.setPositionCardX(150);
                view.setPositionCardY(250);
            }
        }

        for (int i = 0; i < handCoeur.size(); i++) {
            players.get(0).getCards().add(handCoeur.get(i));
            handCoeur.get(i).getP().setX(view.getPositionCardX());
            handCoeur.get(i).getP().setY(view.getPositionCardY());
            view.setPositionCardX(view.getPositionCardX()+150);
            if (players.get(0).getCards().size() == 9) {
                view.setPositionCardX(150);
                view.setPositionCardY(250);
            }
        }

        for (int i = 0; i < handAtout.size(); i++) {
            players.get(0).getCards().add(handAtout.get(i));
            handAtout.get(i).getP().setX(view.getPositionCardX());
            handAtout.get(i).getP().setY(view.getPositionCardY());
            view.setPositionCardX(view.getPositionCardX()+150);
            if (players.get(0).getCards().size() == 9) {
                view.setPositionCardX(150);
                view.setPositionCardY(250);
            }
        }

        for (int i = 0; i < handCarreau.size(); i++) {
            players.get(0).getCards().add(handCarreau.get(i));
            handCarreau.get(i).getP().setX(view.getPositionCardX());
            handCarreau.get(i).getP().setY(view.getPositionCardY());
            view.setPositionCardX(view.getPositionCardX()+150);
            if (players.get(0).getCards().size() == 9) {
                view.setPositionCardX(150);
                view.setPositionCardY(250);
            }
        }

        for (int i = 0; i < handTrefle.size(); i++) {
            players.get(0).getCards().add(handTrefle.get(i));
            handTrefle.get(i).getP().setX(view.getPositionCardX());
            handTrefle.get(i).getP().setY(view.getPositionCardY());
            view.setPositionCardX(view.getPositionCardX()+150);
            if (players.get(0).getCards().size() == 9) {
                view.setPositionCardX(150);
                view.setPositionCardY(250);
            }
        }

        /* Vide les 5 decks de couleurs */
        handPique.removeAll(handPique);
        handCoeur.removeAll(handCoeur);
        handAtout.removeAll(handAtout);
        handCarreau.removeAll(handCarreau);
        handTrefle.removeAll(handTrefle);

        /* On effectue la meme procédure avec le Chien */

        for (int i = 0; i < this.getDog().size(); i++) {
            if (this.getDog().get(i).getP().getColor() == TypeCard.Pique) {
                handPique.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getP().getColor() == TypeCard.Coeur) {
                handCoeur.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getP().getColor() == TypeCard.Atout) {
                handAtout.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getP().getColor() == TypeCard.Carreau) {
                handCarreau.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getP().getColor() == TypeCard.Trefle) {
                handTrefle.add(this.getDog().get(i));
            }
        }

        this.getDog().removeAll(this.getDog());

        Collections.sort(handPique);
        Collections.sort(handCoeur);
        Collections.sort(handAtout);
        Collections.sort(handCarreau);
        Collections.sort(handTrefle);

        for (int i = 0; i < handPique.size(); i++){
            this.getDog().add(handPique.get(i));
            handPique.get(i).getP().setX(view.getPositionDogX());
            handPique.get(i).getP().setY(view.getPositionDogY());
            view.setPositionDogX(view.getPositionDogX()+150);
        }
        for (int i = 0; i < handCoeur.size(); i++){
            this.getDog().add(handCoeur.get(i));
            handCoeur.get(i).getP().setX(view.getPositionDogX());
            handCoeur.get(i).getP().setY(view.getPositionDogY());
            view.setPositionDogX(view.getPositionDogX()+150);
        }
        for (int i = 0; i < handAtout.size(); i++){
            this.getDog().add(handAtout.get(i));
            handAtout.get(i).getP().setX(view.getPositionDogX());
            handAtout.get(i).getP().setY(view.getPositionDogY());
            view.setPositionDogX(view.getPositionDogX()+150);
        }
        for (int i = 0; i < handCarreau.size(); i++){
            this.getDog().add(handCarreau.get(i));
            handCarreau.get(i).getP().setX(view.getPositionDogX());
            handCarreau.get(i).getP().setY(view.getPositionDogY());
            view.setPositionDogX(view.getPositionDogX()+150);
        }
        for (int i = 0; i < handTrefle.size(); i++){
            this.getDog().add(handTrefle.get(i));
            handTrefle.get(i).getP().setX(view.getPositionDogX());
            handTrefle.get(i).getP().setY(view.getPositionDogY());
            view.setPositionDogX(view.getPositionDogX()+150);
        }

        handPique.removeAll(handPique);
        handCoeur.removeAll(handCoeur);
        handAtout.removeAll(handAtout);
        handCarreau.removeAll(handCarreau);
        handTrefle.removeAll(handTrefle);
    }

    public void testLittleDry(){
        if(!littleDry){
            for(int i=0;i<players.size();i++){
                int cpt_atout = 0;
                for(int j=0;j<players.get(i).getCards().size();j++){
                    if(players.get(i).getCards().get(j).getP().getColor() == TypeCard.Atout){
                        cpt_atout++;
                    }
                }
                if(cpt_atout < 1)
                    littleDry = true;
            }
        }
        if(littleDry){
            System.out.println("PETIT SEC"); // a modifier
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getDog() {
        return this.dog;
    }

    public void setView(View v){
        this.view = v;
    }
}
