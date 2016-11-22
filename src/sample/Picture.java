package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Picture
        extends ImageView
{
    private Image image;
    private Image image_cached;
    private String path;
    private String path_cachedCard = "file:./ressources-100/cache.jpg";
    private int numero;
    private TypeCard color;

    public Picture(String path, int n, TypeCard t)
    {
        this.path = path;
        this.image = new Image(this.path);
        this.image_cached = new Image(this.path_cachedCard);
        setImage(this.image_cached);
        this.numero = n;
        this.color = t;
    }

    public void changeImage()
    {
        setImage(this.image);
    }

    public String getPath()
    {
        return this.path;
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
