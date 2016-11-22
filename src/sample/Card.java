package sample;

public class Card
{
    private Picture p;
    private int x;
    private int y;

    public Card(Picture p, int x, int y)
    {
        this.p = p;
        this.x = x;
        this.y = y;
        p.setX(x);
        p.setY(y);
    }

    public Picture getP()
    {
        return this.p;
    }

    public void setP(Picture p)
    {
        this.p = p;
    }
}
