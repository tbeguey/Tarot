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
    private int positionCardX = 150;
    private int positionCardY = 50;
    private int positionDogX = 350;
    private int positionDogY = 700;
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
        this.window.setTitle("Tarot - S3B - Beguey");
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

        this.root.getChildren().add(this.distribution);
        this.root.getChildren().add(this.returnedAll);
        this.root.getChildren().add(this.sort);
        this.root.getChildren().add(this.take);

        this.window.setScene(this.scene);
        this.window.show();
    }

    public void update(Card c, boolean Hand){
        if(Hand){
            c.setX(positionCardX);
            c.setY(positionCardY);
            this.positionCardX += 150;
            if (this.model.getPlayers().get(0).getCards().size() == 9) {
                this.positionCardY = 250;
                this.positionCardX = 150;
            }
        }
        else{
            c.setX(positionDogX);
            c.setY(positionDogY);
            this.positionDogX +=150;
        }
        this.root.getChildren().add(c);
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
}
