package logic;

public class Vecteur {
    private int x;
    private int y;

    public Vecteur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int calculerLongueur(Vecteur v){
        return v.getX() -v.getY();
    }
}
