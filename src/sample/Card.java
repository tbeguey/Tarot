package sample;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;

public class Card extends Group
{
    private CardModel cardModel;
    private ImageView front;
    private ImageView back = new ImageView();
    private int x;
    private int y;
    private static Image image_cached = new Image("file:./ressources-100/cache.jpg");
    private static long halfFlipDuration = 1000;
    private static long MoveDuration = 3000;
    private static long GardMoveDuration = 6000;

    public Card(ImageView front, CardModel cardModel, int x, int y)
    {
        this.cardModel = cardModel;
        this.front = front;
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

    }
    Collection<Node> getNodes(){
        ArrayList<Node> al = new ArrayList<>();
        al.add(front);
        al.add(back);
        return al;
    }

    public void flip() {
        System.out.print("Whaou tu m'as fait fliper !");
        final RotateTransition rotateOutFront = new RotateTransition(Duration.millis(halfFlipDuration), front);
        rotateOutFront.setInterpolator(Interpolator.LINEAR);
        rotateOutFront.setAxis(Rotate.Y_AXIS);
        rotateOutFront.setFromAngle(90);
        rotateOutFront.setToAngle(0);
        //
        final RotateTransition rotateInBack = new RotateTransition(Duration.millis(halfFlipDuration), back);
        rotateInBack.setInterpolator(Interpolator.LINEAR);
        rotateInBack.setAxis(Rotate.Y_AXIS);
        rotateInBack.setFromAngle(0);
        rotateInBack.setToAngle(90);

        rotateInBack.setOnFinished(event -> {front.setOpacity(1); back.setOpacity(0);});
        //

        new SequentialTransition(rotateOutFront, rotateInBack).play();
    }

    public void move(int posX, int posY){
        TranslateTransition translateTransitionFront = new TranslateTransition(Duration.millis(MoveDuration), front);
        translateTransitionFront.setToX(posX); // à 350
        translateTransitionFront.setToY(posY);

        TranslateTransition translateTransitionBack = new TranslateTransition(Duration.millis(MoveDuration), back);
        translateTransitionBack.setToX(posX); // à 350
        translateTransitionBack.setToY(posY);

        new ParallelTransition(translateTransitionFront, translateTransitionBack).play();
    }

    public void moveGard(){
        TranslateTransition translateTransitionFrontX = new TranslateTransition(Duration.millis(GardMoveDuration), front);
        translateTransitionFrontX.setToX(500);
        TranslateTransition translateTransitionBackX = new TranslateTransition(Duration.millis(GardMoveDuration), back);
        translateTransitionBackX.setToX(500);

        ParallelTransition parallelTransitionX = new ParallelTransition(translateTransitionFrontX, translateTransitionBackX);

        TranslateTransition translateTransitionFrontY = new TranslateTransition(Duration.millis(500), front);
        translateTransitionFrontY.setToY(500);
        TranslateTransition translateTransitionBackY = new TranslateTransition(Duration.millis(500), back);
        translateTransitionBackY.setToY(500);

        ParallelTransition parallelTransitionY = new ParallelTransition(translateTransitionFrontY, translateTransitionBackY);

        new SequentialTransition(parallelTransitionX, parallelTransitionY).play();
    }


    public CardModel getCardModel() {
        return cardModel;
    }
}
