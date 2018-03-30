import java.util.LinkedList;

public class Trajet {
    private int distance;
    private int DepotX;
    private int DepotY;

    private LinkedList<Client> clients;

    public Trajet(int DepotX, int DepotY, LinkedList<Client> clients) {
        this.DepotX = DepotX;
        this.DepotY = DepotY;
        this.clients = clients;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDepotX() {
        return DepotX;
    }

    public void setDepotX(int depotX) {
        DepotX = depotX;
    }

    public int getDepotY() {
        return DepotY;
    }

    public void setDepotY(int depotY) {
        DepotY = depotY;
    }

    public LinkedList<Client> getClients() {
        return clients;
    }

    public void setClients(LinkedList<Client> clients) {
        this.clients = clients;
    }


}
