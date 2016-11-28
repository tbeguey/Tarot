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

    public Card(Picture p, int x, int y)
    {
        inDog = false;
        this.p = p;
        this.x = x;
        this.y = y;
        p.setX(x);
        p.setY(y);
        this.getChildren().add(p);

        this.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(!inDog)
                    p.changeImage();
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
}
