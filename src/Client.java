public class Client implements Cloneable{
    private int numClient;
    private int x;
    private int y;
    private int quantite;

    public Client(int numClient, int x, int y, int quantite) {
        this.numClient = numClient;
        this.x = x;
        this.y = y;
        this.quantite = quantite;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
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
        return (int)Math.round(Math.hypot(a.getX() - this.getX(),a.getY() - this.getY()));
    }

    @Override
    protected Client clone() throws CloneNotSupportedException {
        Client c = new Client(this.numClient, this.x, this.y, this.quantite);
        return c;
    }
}
