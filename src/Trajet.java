import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    public Collection<Client> getSegment(int i, int j){
        LinkedList<Client> segment = new LinkedList<>();

        for (int a=i;a < j; a++){
            segment.add(clients.get(a));
        }

        return segment;
    }

    public void deleteSegment(int i, int j){
        for (int a=i;a < j; a++){
           clients.remove(a);
        }
    }

    public void addSegment(int i, Collection<Client> segment){
        clients.addAll(i, segment);
    }
}
