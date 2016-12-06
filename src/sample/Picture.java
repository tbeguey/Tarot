package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Picture extends ImageView
{
    private Image image;
    private String path;
    private int numero;
    private TypeCard color;

    public Picture(String path, int n, TypeCard t)
    {
        this.path = path;
        this.image = new Image(this.path);
        this.numero = n;
        this.color = t;
        this.setImage(image);
    }

    public Picture(){
    }

    public int getNumero()
    {
        return this.numero;
    }

    public TypeCard getColor()
    {
        return this.color;
    }
}
