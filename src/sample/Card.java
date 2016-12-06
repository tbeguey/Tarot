package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Card extends Group implements Comparable<Card>
{
    private Picture front;
    private ImageView back = new ImageView();
    private int x;
    private int y;
    private boolean inDog;
    private static Image image_cached = new Image("file:./ressources-100/cache.jpg");
    private static long halfFlipDuration = 1000;


    public Card(Picture p, int x, int y)
    {
        inDog = false;
        this.front = p;
        this.x = x;
        this.y = y;
        this.front.setX(x);
        this.front.setY(y);
        this.back.setX(x);
        this.back.setY(y);
        this.back.setImage(image_cached);
        this.back.setFitWidth(80);
        this.back.setFitHeight(170);
        this.front.setFitWidth(80);
        this.front.setFitHeight(170);
        this.getChildren().add(this.front);
        //this.getChildren().add(this.back);

        this.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(!inDog)
                    flip().play();
            }
        });
    }

    Transition flip() {
        final RotateTransition rotateOutFront = new RotateTransition(Duration.millis(halfFlipDuration), front);
        rotateOutFront.setInterpolator(Interpolator.LINEAR);
        rotateOutFront.setAxis(Rotate.Y_AXIS);
        rotateOutFront.setFromAngle(0);
        rotateOutFront.setToAngle(90);
        //
        final RotateTransition rotateInBack = new RotateTransition(Duration.millis(halfFlipDuration), back);
        rotateInBack.setInterpolator(Interpolator.LINEAR);
        rotateInBack.setAxis(Rotate.Y_AXIS);
        rotateInBack.setFromAngle(-90);
        rotateInBack.setToAngle(0);
        //
        return new SequentialTransition(rotateOutFront, rotateInBack);
    }

    public void move(int posX, int posY){
        TranslateTransition translateTransitionFront = new TranslateTransition(Duration.millis(3000), front);
        translateTransitionFront.setToX(posX); // à 350
        translateTransitionFront.setToY(posY);
        TranslateTransition translateTransitionBack = new TranslateTransition(Duration.millis(3000), back);
        translateTransitionBack.setToX(posX); // à 350
        translateTransitionBack.setToY(posY);

        new ParallelTransition(translateTransitionFront, translateTransitionBack).play();
    }

    public void moveGard(){
        TranslateTransition translateTransitionFrontX = new TranslateTransition(Duration.millis(1000), front);
        translateTransitionFrontX.setToX(500);
        TranslateTransition translateTransitionBackX = new TranslateTransition(Duration.millis(1000), back);
        translateTransitionBackX.setToX(500);

        ParallelTransition parallelTransitionX = new ParallelTransition(translateTransitionFrontX, translateTransitionBackX);

        TranslateTransition translateTransitionFrontY = new TranslateTransition(Duration.millis(500), front);
        translateTransitionFrontY.setToY(500);
        TranslateTransition translateTransitionBackY = new TranslateTransition(Duration.millis(500), back);
        translateTransitionBackY.setToY(500);

        ParallelTransition parallelTransitionY = new ParallelTransition(translateTransitionFrontY, translateTransitionBackY);

        new SequentialTransition(parallelTransitionX, parallelTransitionY).play();
    }

    @Override
    public int compareTo(Card c){
        return this.getFront().getNumero()-c.getFront().getNumero();
    }

    public Picture getFront()
    {
        return this.front;
    }

    public void setX(int x) {
        this.x = x;
        front.setX(x);
        back.setX(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        front.setY(y);
        back.setY(y);
    }

    public void setInDog(boolean inDog) {
        this.inDog = inDog;
    }

}
