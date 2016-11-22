package sample;

public class Controller
{
    private View view;
    private Model model;

    public Controller(Model m, View v)
    {
        this.view = v;
        this.model = m;
    }
}
