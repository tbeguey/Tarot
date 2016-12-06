package sample;

import java.util.ArrayList;
import java.util.Collections;

public class Model extends java.util.Observable {
    private View view;

    public ArrayList<Card> getCardsDeck() {
        return cardsDeck;
    }

    private ArrayList<Card> cardsDeck = new ArrayList<>();
    private ArrayList<Card> dog = new ArrayList();
    private ArrayList<Card> bin = new ArrayList<>(); // servira comme "poubelle" pour les cartes dont le joueur faisant la prise ne veut pas
    private ArrayList<Player> players = new ArrayList<>();
    private boolean littleDry = false;
    private boolean take = false;

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
            Card c = new Card(view.getPictures().get(i), view.getPositionDeckX(),view.getPositionDeckY());
            cardsDeck.add(c);
        }
        Collections.shuffle(cardsDeck); // mélange
    }



    public void addCardHand(int idPlayer){
        if (players.get(idPlayer).getCards().size() != 18 && cardsDeck.size() !=0){
            Card c = cardsDeck.get(cardsDeck.size()-1);
            players.get(idPlayer).addCardsToAPlayer(c);
            if(idPlayer == 0){
                view.updateAddCurrentPlayer(c, true);
            }
            else{
                view.updateAddOtherPlayer(c, idPlayer);
            }
            cardsDeck.remove(c);
        }
    }

    public void removeCardHand(int idPlayer, Card c){ // a tester
        players.get(idPlayer).removeCardsToAPlayer(c);
        view.updateRemove(c);
    }

    public void addCardDog() {
        Card c = cardsDeck.get(cardsDeck.size()-1);
        c.setInDog(true);
        this.dog.add(c);
        view.updateAddCurrentPlayer(c, false);
        cardsDeck.remove(c);
    }

    public void dogToHand(){ // a tester
        for(int i=0;i<dog.size();i++){
            //dog.get(i).flip().play();
            players.get(0).getCards().add(dog.get(i));
        }
        dog.clear();
    }

    public void sortHand(){
        ArrayList<Card> handPique = new ArrayList();
        ArrayList<Card> handCoeur = new ArrayList();
        ArrayList<Card> handCarreau = new ArrayList();
        ArrayList<Card> handTrefle = new ArrayList();
        ArrayList<Card> handAtout = new ArrayList();
        /* Reparti le deck du joueur dans 5 decks différents en fonction de leur couleur */
        for (int i = 0; i < players.get(0).getCards().size(); i++) {
            if (players.get(0).getCards().get(i).getFront().getColor() == TypeCard.Pique) {
                handPique.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getFront().getColor() == TypeCard.Coeur) {
                handCoeur.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getFront().getColor() == TypeCard.Atout) {
                handAtout.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getFront().getColor() == TypeCard.Carreau) {
                handCarreau.add(players.get(0).getCards().get(i));
            } else if (players.get(0).getCards().get(i).getFront().getColor() == TypeCard.Trefle) {
                handTrefle.add(players.get(0).getCards().get(i));
            }
        }
        /* Vide le deck du joueur */
        players.get(0).getCards().clear();
        //players.get(0).getCards().removeAll(players.get(0).getCards()); // utiliser clear?

        /* Trie les cartes selon leur nombre */
        Collections.sort(handPique);
        Collections.sort(handCoeur);
        Collections.sort(handAtout);
        Collections.sort(handCarreau);
        Collections.sort(handTrefle);

        for (int i = 0; i < handPique.size(); i++) {
            players.get(0).getCards().add(handPique.get(i));
            view.updateAddCurrentPlayer(handPique.get(i), true);
        }

        for (int i = 0; i < handCoeur.size(); i++) {
            players.get(0).getCards().add(handCoeur.get(i));
            view.updateAddCurrentPlayer(handCoeur.get(i), true);
        }

        for (int i = 0; i < handAtout.size(); i++) {
            players.get(0).getCards().add(handAtout.get(i));
            view.updateAddCurrentPlayer(handAtout.get(i), true);
        }

        for (int i = 0; i < handCarreau.size(); i++) {
            players.get(0).getCards().add(handCarreau.get(i));
            view.updateAddCurrentPlayer(handCarreau.get(i), true);
        }

        for (int i = 0; i < handTrefle.size(); i++) {
            players.get(0).getCards().add(handTrefle.get(i));
            view.updateAddCurrentPlayer(handTrefle.get(i), true);
        }

        /* Vide les 5 decks de couleurs */
        /*handPique.removeAll(handPique);
        handCoeur.removeAll(handCoeur);
        handAtout.removeAll(handAtout);
        handCarreau.removeAll(handCarreau);
        handTrefle.removeAll(handTrefle);*/
        handPique.clear();
        handCarreau.clear();
        handAtout.clear();
        handTrefle.clear();
        handCoeur.clear();
    }

    public void sortDog(){
        ArrayList<Card> handPique = new ArrayList();
        ArrayList<Card> handCoeur = new ArrayList();
        ArrayList<Card> handCarreau = new ArrayList();
        ArrayList<Card> handTrefle = new ArrayList();
        ArrayList<Card> handAtout = new ArrayList();

        /* On effectue la meme procédure avec le Chien */

        for (int i = 0; i < this.getDog().size(); i++) {
            if (this.getDog().get(i).getFront().getColor() == TypeCard.Pique) {
                handPique.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getFront().getColor() == TypeCard.Coeur) {
                handCoeur.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getFront().getColor() == TypeCard.Atout) {
                handAtout.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getFront().getColor() == TypeCard.Carreau) {
                handCarreau.add(this.getDog().get(i));
            } else if (this.getDog().get(i).getFront().getColor() == TypeCard.Trefle) {
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
            view.updateAddCurrentPlayer(handPique.get(i), false);
        }
        for (int i = 0; i < handCoeur.size(); i++){
            this.getDog().add(handCoeur.get(i));
            view.updateAddCurrentPlayer(handCoeur.get(i), false);
        }
        for (int i = 0; i < handAtout.size(); i++){
            this.getDog().add(handAtout.get(i));
            view.updateAddCurrentPlayer(handAtout.get(i), false);
        }
        for (int i = 0; i < handCarreau.size(); i++){
            this.getDog().add(handCarreau.get(i));
            view.updateAddCurrentPlayer(handCarreau.get(i), false);
        }
        for (int i = 0; i < handTrefle.size(); i++){
            this.getDog().add(handTrefle.get(i));
            view.updateAddCurrentPlayer(handTrefle.get(i), false);
        }

        handPique.removeAll(handPique);
        handCoeur.removeAll(handCoeur);
        handAtout.removeAll(handAtout);
        handCarreau.removeAll(handCarreau);
        handTrefle.removeAll(handTrefle);
    }

    public void testLittleDry(){ // a tester aussi, je pense pas que ca marche
        if(!littleDry){
            for(int i=0;i<players.size();i++){
                int cpt_atout = 0;
                for(int j=0;j<players.get(i).getCards().size();j++){
                    if(players.get(i).getCards().get(j).getFront().getColor() == TypeCard.Atout){
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

    public ArrayList<Card> getBin() {
        return bin;
    }

    public void setView(View v){
        this.view = v;
    }

    public boolean isTake() {
        return take;
    }

    public void setTake(boolean take) {
        this.take = take;
    }
}
