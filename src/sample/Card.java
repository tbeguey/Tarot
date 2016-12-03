package sample;

import javafx.scene.Parent;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;

public class Card extends Parent implements Comparable<Card>
{
    private Picture p;
    private int x;
    private int y;
    private boolean inDog;
    private static int nbRemove = 0;

    public Card(Picture p, int x, int y)
    {
        inDog = false;
        this.p = p;
        this.x = x;
        this.y = y;
        p.setX(x);
        p.setY(y);
        this.getChildren().add(p);
        this.changeActionToChangeImage();
    }

    public void changeActionToChangeImage(){
        this.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(!inDog)
                    p.changeImage();
            }
        });
    }

    public void changeActionToRemove(){
        this.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(nbRemove < 6) {
                    getChildren().remove(p); // le mieux ca serait que j'enleve pas juste l'image mais toute la carte et en la retirant de la main du joueur
                    nbRemove++;
                }
                // arriver à 6 faut ranger les cartes mais je sais pas comment savoir qu'on est arrivé à 6
            }
        });
    }

    @Override
    public int compareTo(Card c){
        return this.getP().getNumero()-c.getP().getNumero();
    }

    public Picture getP()
    {
        return this.p;
    }

    public void setX(int x) {
        this.x = x;
        p.setX(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        p.setY(y);
    }

    public void setInDog(boolean inDog) {
        this.inDog = inDog;
    }

    public static int getNbRemove() {
        return nbRemove;
    }

}
