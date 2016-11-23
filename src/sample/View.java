package sample;

import java.util.ArrayList;

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
    private int pos_x = 150;
    private int pos_y = 50;
    private int pos_x_dog = 350;
    private int pos_y_dog = 700;
    private ArrayList<Picture> pictures = new ArrayList();
    private int iterator_returned = 0;
    private int aleaCard = 0;

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
                if (model.getHand().size() == 15 && model.getDog().size() != 6){
                    aleaCard = (int)(1 + Math.random() * pictures.size());
                    newCardOnDog(aleaCard);
                    pictures.remove(pictures.get(aleaCard-1));
                }
                for (int i = 1; i <= 3; i++){
                        if (model.getHand().size() != 18){
                            aleaCard = (int)(1 + Math.random() * pictures.size());
                            newCardOnHand(aleaCard);
                        }
                        pictures.remove(pictures.get(aleaCard-1));
                }
                if (model.getHand().size() != 18 && model.getDog().size() != 6) {
                aleaCard = (int)(1 + Math.random() * pictures.size());
                newCardOnDog(aleaCard);
                pictures.remove(pictures.get(aleaCard-1));
                }
            }
        });

        this.returned.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (model.getHand().size() > iterator_returned){
                    model.getHand().get(iterator_returned).getP().changeImage();
                    iterator_returned++;
                }
                else {
                    model.getDog().get(iterator_returned-model.getHand().size()).getP().changeImage();
                    iterator_returned++;
                }
            }
        });

        this.returnedAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (model.getHand().size() + model.getDog().size() == 24){
                    for (int i = 0; i < model.getHand().size(); i++) {
                        model.getHand().get(i).getP().changeImage();
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
                ArrayList<Card> handPique = new ArrayList();
                ArrayList<Card> handCoeur = new ArrayList();
                ArrayList<Card> handCarreau = new ArrayList();
                ArrayList<Card> handTrefle = new ArrayList();
                ArrayList<Card> handAtout = new ArrayList();
                pos_x = 150;
                pos_y = 50;
                pos_x_dog = 350;
                pos_y_dog = 700;

                for (int i = 0; i < model.getHand().size(); i++) {
                    if (model.getHand().get(i).getP().getColor() == TypeCard.Pique) {
                        handPique.add(model.getHand().get(i));
                    } else if (model.getHand().get(i).getP().getColor() == TypeCard.Coeur) {
                        handCoeur.add(model.getHand().get(i));
                    } else if (model.getHand().get(i).getP().getColor() == TypeCard.Atout) {
                        handAtout.add(model.getHand().get(i));
                    } else if (model.getHand().get(i).getP().getColor() == TypeCard.Carreau) {
                        handCarreau.add(model.getHand().get(i));
                    } else if (model.getHand().get(i).getP().getColor() == TypeCard.Trefle) {
                        handTrefle.add(model.getHand().get(i));
                    }
                }
                model.getHand().removeAll(model.getHand());
                /*
                model.quickSort(handPique);
                model.quickSort(handCoeur);
                model.quickSort(handAtout);
                model.quickSort(handCarreau);
                model.quickSort(handTrefle);
                */

                for (int i = 0; i < handPique.size(); i++) {
                    model.getHand().add(handPique.get(i));
                    handPique.get(i).getP().setX(pos_x);
                    handPique.get(i).getP().setY(pos_y);
                    pos_x +=150;
                    if (model.getHand().size() == 9) {
                        pos_x = 150;
                        pos_y = 250;
                    }
                }

                for (int i = 0; i < handCoeur.size(); i++) {
                    model.getHand().add(handCoeur.get(i));
                    handCoeur.get(i).getP().setX(pos_x);
                    handCoeur.get(i).getP().setY(pos_y);
                    pos_x +=150;
                    if (model.getHand().size() == 9) {
                        pos_x = 150;
                        pos_y = 250;
                    }
                }

                for (int i = 0; i < handAtout.size(); i++) {
                    model.getHand().add(handAtout.get(i));
                    handAtout.get(i).getP().setX(pos_x);
                    handAtout.get(i).getP().setY(pos_y);
                    pos_x +=150;
                    if (model.getHand().size() == 9) {
                        pos_x = 150;
                        pos_y = 250;
                    }
                }

                for (int i = 0; i < handCarreau.size(); i++) {
                    model.getHand().add(handCarreau.get(i));
                    handCarreau.get(i).getP().setX(pos_x);
                    handCarreau.get(i).getP().setY(pos_y);
                    pos_x +=150;
                    if (model.getHand().size() == 9) {
                        pos_x = 150;
                        pos_y = 250;
                    }
                }

                for (int i = 0; i < handTrefle.size(); i++) {
                    model.getHand().add(handTrefle.get(i));
                    handTrefle.get(i).getP().setX(pos_x);
                    handTrefle.get(i).getP().setY(pos_y);
                    pos_x +=150;
                    if (model.getHand().size() == 9) {
                        pos_x = 150;
                        pos_y = 250;
                    }
                }
                handPique.removeAll(handPique);
                handCoeur.removeAll(handCoeur);
                handAtout.removeAll(handAtout);
                handCarreau.removeAll(handCarreau);
                handTrefle.removeAll(handTrefle);

                for (int i = 0; i < model.getDog().size(); i++) {
                    if (model.getDog().get(i).getP().getColor() == TypeCard.Pique) {
                        handPique.add(model.getDog().get(i));
                    } else if (model.getDog().get(i).getP().getColor() == TypeCard.Coeur) {
                        handCoeur.add(model.getDog().get(i));
                    } else if (model.getDog().get(i).getP().getColor() == TypeCard.Atout) {
                        handAtout.add(model.getDog().get(i));
                    } else if (model.getDog().get(i).getP().getColor() == TypeCard.Carreau) {
                        handCarreau.add(model.getDog().get(i));
                    } else if (model.getDog().get(i).getP().getColor() == TypeCard.Trefle) {
                        handTrefle.add(model.getDog().get(i));
                    }
                }

                model.getDog().removeAll(model.getDog());

                for (int i = 0; i < handPique.size(); i++){
                    model.getDog().add(handPique.get(i));
                    handPique.get(i).getP().setX(pos_x_dog);
                    handPique.get(i).getP().setY(pos_y_dog);
                    pos_x_dog += 150;
                }
                for (int i = 0; i < handCoeur.size(); i++){
                    model.getDog().add(handCoeur.get(i));
                    handCoeur.get(i).getP().setX(pos_x_dog);
                    handCoeur.get(i).getP().setY(pos_y_dog);
                    pos_x_dog += 150;
                }
                for (int i = 0; i < handAtout.size(); i++){
                    model.getDog().add(handAtout.get(i));
                    handAtout.get(i).getP().setX(pos_x_dog);
                    handAtout.get(i).getP().setY(pos_y_dog);
                    pos_x_dog += 150;
                }
                for (int i = 0; i < handCarreau.size(); i++){
                    model.getDog().add(handCarreau.get(i));
                    handCarreau.get(i).getP().setX(pos_x_dog);
                    handCarreau.get(i).getP().setY(pos_y_dog);
                    pos_x_dog += 150;
                }
                for (int i = 0; i < handTrefle.size(); i++){
                    model.getDog().add(handTrefle.get(i));
                    handTrefle.get(i).getP().setX(pos_x_dog);
                    handTrefle.get(i).getP().setY(pos_y_dog);
                    pos_x_dog += 150;
                }
            }
        });

        this.root.getChildren().add(this.distribution);
        this.root.getChildren().add(this.returned);
        this.root.getChildren().add(this.returnedAll);
        this.root.getChildren().add(this.sort);

        this.window.setScene(this.scene);
        this.window.show();

        initialisePicture();
    }

    public void update(Card c){
        this.root.getChildren().add(c.getP());
    }

    public void newCardOnHand(int alea){
        Card c = new Card(this.pictures.get(alea - 1), this.pos_x, this.pos_y);
        this.model.addCardHand(c);
        update(c);
        this.pos_x += 150;
        if (this.model.getHand().size() == 9) {
            this.pos_y = 250;
            this.pos_x = 150;
        }
    }

    public void newCardOnDog(int alea)
    {
        Card c = new Card((Picture)this.pictures.get(alea - 1), this.pos_x_dog, this.pos_y_dog);
        this.model.addCardDog(c);
        update(c);
        this.pos_x_dog += 150;
    }
}
