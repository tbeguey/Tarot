package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
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
    private Button next = new Button();
    private int positionDeckX = 750;
    private int positionDeckY = 400;
    private int positionCardX = 150;
    private int positionCardY = 50;
    private int positionDogX = 350;
    private int positionDogY = 700;
    private int cardPlaced = 0;
    private ArrayList<Picture> pictures = new ArrayList();

    public void initialisePicture(){
        for (int i = 1; i <= 21; i++){
            this.pictures.add(new Picture("file:./ressources-100/" + i + ".jpg", i, TypeCard.Atout));
        }
        for (int i = 1; i <= 14; i++){
            this.pictures.add(new Picture("file:./ressources-100/" + i + "Carreau.jpg", i, TypeCard.Carreau));
            this.pictures.add(new Picture("file:./ressources-100/" + i + "Pique.jpg", i, TypeCard.Pique));
            this.pictures.add(new Picture("file:./ressources-100/" + i + "Trefle.jpg", i, TypeCard.Trefle));
            this.pictures.add(new Picture("file:./ressources-100/" + i + "Coeur.jpg", i, TypeCard.Coeur));
        }
        this.pictures.add(new Picture("file:./ressources-100/excuse.jpg", 0, TypeCard.Atout));
    }

    public View(Model m)
    {
        initialisePicture();
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
        this.next.setText("Test");
        this.next.setLayoutX(1000.0D);
        this.next.setLayoutY(10.0D);
        this.gard.setText("Garde");
        this.gard.setLayoutX(1200.0D);
        this.gard.setLayoutY(10.0D);

        this.root.getChildren().add(this.distribution);
        this.root.getChildren().add(this.returnedAll);
        this.root.getChildren().add(this.sort);
        this.root.getChildren().add(this.take);
        this.root.getChildren().add(this.next);
        this.root.getChildren().add(this.gard);

        this.window.setScene(this.scene);
        this.window.show();
    }

    public void updateAddCurrentPlayer(Card c, boolean Hand){
        if(Hand){
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
        else{
            c.move(positionDogX-positionDeckX,positionDogY-positionDeckY);
            this.positionDogX +=150;
        }
    }

    public void updateAddOtherPlayer(Card c, int idPlayer){
        if(idPlayer == 1){
            c.move(-900,0);
        }
        else if(idPlayer == 2){
            c.move(0,500);
        }
        else if(idPlayer == 3){
            c.move(900,0);
        }
        //c.setX(-50);
        //c.setY(-50);
    }

    public void removeCard(){
        for(int i=0;i<model.getPlayers().get(0).getCards().size();i++) {
            int finalI = i;
            model.getPlayers().get(0).getCards().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(model.getGap().size() !=6){
                        model.getPlayers().get(0).removeCardsToAPlayer(model.getPlayers().get(0).getCards().get(finalI));
                        model.getGap().add(model.getPlayers().get(0).getCards().get(finalI));
                        model.getPlayers().get(0).getCards().remove(model.getPlayers().get(0).getCards().get(finalI));
                        root.getChildren().remove(model.getPlayers().get(0).getCards().get(finalI));
                    }
                    else{
                        positionCardX = 150;
                        positionCardY = 50;
                        positionDogX = 350;
                        positionDogY = 700;
                        model.sortHand();
                    }
                }
            });
        }
    }

    public void updateDeck(){
        for (int i = 0; i < model.getCardsDeck().size(); i++) {
            this.root.getChildren().add(model.getCardsDeck().get(i));
        }
    }

    public void updateRemove(Card c){
        this.root.getChildren().remove(c);
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }
    public int getPositionCardX() {
        return positionCardX;
    }
    public int getPositionCardY() {
        return positionCardY;
    }
    public int getPositionDogX() {
        return positionDogX;
    }
    public int getPositionDogY() {
        return positionDogY;
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

    public Button getNext() { return next; }
}
