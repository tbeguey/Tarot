package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main
        extends Application
{
    private Model model;
    private View view;
    private Controller controller;

    public void start(Stage primaryStage)
            throws Exception
    {
        this.model = new Model();
        this.view = new View(this.model);
        this.controller = new Controller(this.model, this.view);
        model.setView(this.view);
        model.initialiseCardsDeck();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
