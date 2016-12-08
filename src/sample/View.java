package sample;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View implements Observer{
    private Stage window = new Stage();
    private Group root = new Group();
    private Scene scene = new Scene(this.root, 300.0D, 250.0D, Color.LIGHTGREY);
    private Model model;
    private Button distribution = new Button();
    private Button returnedAll = new Button();
    private Button sort = new Button();
    private Button take = new Button();
    private Button gard = new Button();
    private int positionDeckX = 750;
    private int positionDeckY = 400;
    private int positionCardX = 150;
    private int positionCardY = 50;
    private int positionDogX = 350;
    private int positionDogY = 700;
    private int cardPlaced = 0;
    private ArrayList<Card> cardsView = new ArrayList();

    public void initialisePicture(){
        for (int i = 0; i < model.getCardsDeck().size(); i++) {
            if(model.getCardsDeck().get(i).getColor() == TypeCard.Trump){
                this.cardsView.add(new Card(new ImageView("file:./ressources-100/" + model.getCardsDeck().get(i).getNumero()
                        + ".jpg"), model.getCardsDeck().get(i), positionDeckX, positionDeckY));
            }
            else if(model.getCardsDeck().get(i).getColor() == TypeCard.Excuse){
                this.cardsView.add(new Card(new ImageView("file:./ressources-100/excuse.jpg"),model.getCardsDeck().get(i),positionDeckX,positionDeckY));
            }
            else{
                this.cardsView.add(new Card(new ImageView("file:./ressources-100/" + model.getCardsDeck().get(i).getNumero()
                        + model.getCardsDeck().get(i).getColor() + ".jpg"),model.getCardsDeck().get(i),positionDeckX,positionDeckY));
            }
        }
    }

    public View(Model m)
    {
        this.model = m;
        this.window.setTitle("Tarot - S3B - Tricha Yamin - Beguey Théo");
        this.window.setFullScreen(true);
        this.distribution.setLayoutX(200.0D);
        this.distribution.setLayoutY(10.0D);
        this.distribution.setText("Distribution");
        this.returnedAll.setLayoutX(400.0D);
        this.returnedAll.setLayoutY(10.0D);
        this.returnedAll.setText("Retourner toutes les cartes");
        this.sort.setLayoutX(600.0D);
        this.sort.setLayoutY(10.0D);
        this.sort.setText("Trier");
        this.take.setLayoutX(800.0D);
        this.take.setLayoutY(10.0D);
        this.take.setText("Prise");
        this.gard.setText("Garde sans le Chien");
        this.gard.setLayoutX(1200.0D);
        this.gard.setLayoutY(10.0D);

        this.root.getChildren().add(this.distribution);
        this.root.getChildren().add(this.returnedAll);
        this.root.getChildren().add(this.sort);
        this.root.getChildren().add(this.take);
        this.root.getChildren().add(this.gard);

        this.window.setScene(this.scene);
        this.window.show();
        initialisePicture();
    }

    public Card whichCardView(CardModel cardModel){
        for (int i = 0; i < cardsView.size(); i++) {
            if(cardsView.get(i).getCardModel().getNumero() == cardModel.getNumero() &&
                    cardsView.get(i).getCardModel().getColor() == cardModel.getColor()){
                return cardsView.get(i);
            }
        }
        return null;
    }

    public void updateAddCurrentPlayer(Card c){
        System.out.print(" 4 :" + c.getCardModel().getNumero());
        System.out.println(c.getCardModel().getColor());
            c.move(positionCardX-positionDeckX,positionCardY-positionDeckY);
            cardPlaced++;
            this.positionCardX += 150;
            if (cardPlaced == 9) {
                this.positionCardY = 250;
                this.positionCardX = 150;
            }
            else if (cardPlaced == 18){
                this.positionCardY = 450;
                this.positionCardX = 150;
            }
    }

    public void updateAddDog(Card c){
        System.out.print(" 4 :" + c.getCardModel().getNumero());
        System.out.println(c.getCardModel().getColor());
        c.move(positionDogX-positionDeckX,positionDogY-positionDeckY);
        this.positionDogX +=150;
    }

    public void updateAddOtherPlayer(Card c){
        System.out.print(" 4 :" + c.getCardModel().getNumero());
        System.out.println(c.getCardModel().getColor());
        if(model.getIdPlayerDistrib() == 1){
            c.move(-900,0);
        }
        else if(model.getIdPlayerDistrib() == 2){
            c.move(0,500);
        }
        else if(model.getIdPlayerDistrib() == 3){
            c.move(900,0);
        }
    }

    public void removeCard(){
        for(int i=0;i<model.getPlayers().get(0).getCards().size();i++) {
            int finalI = i;
            /*cardsView.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("LA JE CLIQUE CA REMOVE");*/
                    if(model.getGap().size() !=6){
                        if(model.getPlayers().get(0).getCards().get(finalI).getNumero() !=14 ||
                                (model.getPlayers().get(0).getCards().get(finalI).getNumero() != 1
                                        && model.getPlayers().get(0).getCards().get(finalI).getColor() == TypeCard.Trump) ||
                                model.getPlayers().get(0).getCards().get(finalI).getNumero() != 21 ||
                                model.getPlayers().get(0).getCards().get(finalI).getNumero() != 0){
                            model.getGap().add(model.getPlayers().get(0).getCards().get(finalI));
                            model.getPlayers().get(0).removeCardsToAPlayer(model.getPlayers().get(0).getCards().get(finalI));
                            //root.getChildren().remove(model.getPlayers().get(0).getCards().get(finalI));
                        }
                    }
                    else{
                        for (int j = 0; j < model.getGap().size(); j++) {
                            Card c = whichCardView(model.getGap().get(j));
                            root.getChildren().removeAll(c.getNodes());
                        }
                        cardPlaced = 0;
                        positionCardX = 150;
                        positionCardY = 50;
                        positionDogX = 350;
                        positionDogY = 700;
                        model.sortHand();
                        break;
                    }
                }
            //});
        }
    //}

    public void updateDeck(){
            System.out.print("cardViews size : " + cardsView.size());
        for (int i = 0; i < cardsView.size(); i++) {
            //this.root.getChildren().add(cardsView.get(i));
            this.root.getChildren().addAll(cardsView.get(i).getNodes());
        }
    }

    public void returnedAllCard(){
        for (int i = 0; i < cardsView.size(); i++) {
            if(!cardsView.get(i).getCardModel().isInDog())
                cardsView.get(i).flip();
        }
    }

    public void updateRemove(Card c){
        this.root.getChildren().remove(c);
    }


    public void setPositionCardX(int positionCardX) {
        this.positionCardX = positionCardX;
    }

    public void setPositionCardY(int positionCardY) {
        this.positionCardY = positionCardY;
    }

    public void setPositionDogX(int positionDogX) {
        this.positionDogX = positionDogX;
    }

    public void setPositionDogY(int positionDogY) {
        this.positionDogY = positionDogY;
    }

    public void setCardPlaced(int cardPlaced) { this.cardPlaced = cardPlaced; }
    public int getPositionDeckX() {
        return positionDeckX;
    }

    public int getPositionDeckY() {
        return positionDeckY;
    }


    @Override
    public void update(Observable o, Object arg) {
        CardModel cardModel = (CardModel) arg;
        System.out.print(" 2 :" + cardModel.getNumero());
        System.out.println(cardModel.getColor());
        Card c = whichCardView(cardModel);
        System.out.print(" 3 :" + c.getCardModel().getNumero());
        System.out.println(c.getCardModel().getColor());
        if(model.getNotif() == Notification.AddCardCurrentPlayer){
            updateAddCurrentPlayer(c);
        }
        else if(model.getNotif() == Notification.AddOtherPlayer){
            updateAddOtherPlayer(c);
        }
        else if(model.getNotif() == Notification.AddDog){
            updateAddDog(c);
        }
    }

    public Button getDistribution() {
        return distribution;
    }

    public Button getReturnedAll() {
        return returnedAll;
    }

    public Button getSort() {
        return sort;
    }

    public Button getTake() {
        return take;
    }

    public Button getGard() { return gard; }
}
