package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller
{
    private View view;
    private Model model;

    public Controller(Model m, View v)
    {
        this.view = v;
        this.model = m;

        view.getDistribution().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
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

        view.getReturnedAll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (model.getPlayers().get(0).getCards().size() + model.getDog().size() == 24){
                    for (int i = 0; i < model.getPlayers().get(0).getCards().size(); i++) {
                        model.getPlayers().get(0).getCards().get(i).flip().play();
                    }
                }
            }
        });

        view.getSort().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setCardPlaced(0);
                if (model.getPlayers().get(0).getCards().size() + model.getDog().size() == 24) {
                    view.setPositionCardX(150);
                    view.setPositionCardY(50);
                    view.setPositionDogX(350);
                    view.setPositionDogY(700);
                    model.sortHand();
                    model.testLittleDry(); // on test le petit sec une fois que toutes les cartes sont distribuées et triées
                }
            }
        });

        view.getTake().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setCardPlaced(0);
                model.dogToHand();
                view.setPositionCardX(150);
                view.setPositionCardY(50);
                view.setPositionDogX(350);
                view.setPositionDogY(700);
                model.sortHand();
                view.a();
            }
        });

        view.getNext().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setCardPlaced(0);
                view.setPositionCardX(150);
                view.setPositionCardY(50);
                view.setPositionDogX(350);
                view.setPositionDogY(700);
                model.sortHand();
            }
        });
    }
}
