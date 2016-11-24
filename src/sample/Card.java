package sample;

public class Card implements Comparable<Card>
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

    @Override
    public int compareTo(Card c){
        return this.getP().getNumero()-c.getP().getNumero();
    }
    public Picture getP()
    {
        return this.p;
    }

    public void setP(Picture p)
    {
        this.p = p;
    }
    public int getX() {
        return x;
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
}
