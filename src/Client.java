public class Client {
    private int x;
    private int y;
    private int quantite;

    public Client(int x, int y, int quantite) {
        this.x = x;
        this.y = y;
        this.quantite = quantite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int calculDistance(Client a){
        return Math.abs(a.getX() - this.getX()) + Math.abs(a.getY() - this.getY());
    }
}
