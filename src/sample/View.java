package sample;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View
{
    private Stage window = new Stage();
    private Group root = new Group();
    private Scene scene = new Scene(this.root, 300.0D, 250.0D, Color.LIGHTGREY);
    private Model model;
    private Button distribution = new Button();
    private Button returned = new Button();
    private Button returnedAll = new Button();
    private Button sort = new Button();
    private int positionCardX = 150;
    private int positionCardY = 50;
    private int positionDogX = 350;
    private int positionDogY = 700;
    private ArrayList<Picture> pictures = new ArrayList();
    private int iterator_returned = 0;

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
        this.window.setTitle("Tarot");
        this.window.setFullScreen(true);
        this.distribution.setLayoutX(200.0D);
        this.distribution.setLayoutY(10.0D);
        this.distribution.setText("Distribution");
        this.returned.setLayoutX(400.0D);
        this.returned.setLayoutY(10.0D);
        this.returned.setText("Retourner");
        this.returnedAll.setLayoutX(600.0D);
        this.returnedAll.setLayoutY(10.0D);
        this.returnedAll.setText("Retourner toutes les cartes");
        this.sort.setLayoutX(800.0D);
        this.sort.setLayoutY(10.0D);
        this.sort.setText("Trier");

        this.distribution.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if (model.getPlayers().get(0).getCards().size() == 15 && model.getDog().size() != 6){
                    model.addCardDog();
                }
                for (int i = 1; i <= 4; i++){
                    for (int j = 1; j <= 3; j++) {
                        model.addCardHand(i-1);
                    }

                }
                if (model.getPlayers().get(0).getCards().size() != 18 && model.getDog().size() != 6) {
                    model.addCardDog();
                }
            }
        });

        this.returned.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (model.getPlayers().get(0).getCards().size() > iterator_returned){
                    model.getPlayers().get(0).getCards().get(iterator_returned).getP().changeImage();
                    iterator_returned++;
                }
                else {
                    model.getDog().get(iterator_returned-model.getPlayers().get(0).getCards().size()).getP().changeImage();
                    iterator_returned++;
                }
            }
        });

        this.returnedAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (model.getPlayers().get(0).getCards().size() + model.getDog().size() == 24){
                    for (int i = 0; i < model.getPlayers().get(0).getCards().size(); i++) {
                        model.getPlayers().get(0).getCards().get(i).getP().changeImage();
                    }
                    for (int i = 0; i < model.getDog().size(); i++) {
                        model.getDog().get(i).getP().changeImage();
                    }
                }
            }
        });

        this.sort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                positionCardX = 150;
                positionCardY = 50;
                positionDogX = 350;
                positionDogY = 700;
                model.sortDeck();
            }
        });

        this.root.getChildren().add(this.distribution);
        this.root.getChildren().add(this.returned);
        this.root.getChildren().add(this.returnedAll);
        this.root.getChildren().add(this.sort);

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
        this.root.getChildren().add(c.getP());
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
}
