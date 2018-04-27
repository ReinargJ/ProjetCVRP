import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Trajet {
    private int distance;
    private int DepotX;
    private int DepotY;
    private int quantiteTransport;

    private LinkedList<Client> clients;

    public Trajet(int DepotX, int DepotY, LinkedList<Client> clients) {
        this.DepotX = DepotX;
        this.DepotY = DepotY;
        this.clients = clients;
        this.quantiteTransport =0;

        Client tmp = null;

        for (Iterator<Client> iter = clients.listIterator(); iter.hasNext();){
            tmp = iter.next();

            this.quantiteTransport += tmp.getQuantite();
        }
    }

    public Trajet(int DepotX, int DepotY) {
        this.DepotX = DepotX;
        this.DepotY = DepotY;
        this.clients = new LinkedList<Client>();
        this.quantiteTransport =0;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance() {

        int totalDist = 0;

        for (int i =0; i<clients.size()-1; i++){
            totalDist += clients.get(i).calculDistance(clients.get(i+1));
        }

        this.distance = totalDist;
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

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getQuantiteTransport() {
        return quantiteTransport;
    }

    public void setQuantiteTransport(int quantiteTransport) {
        this.quantiteTransport = quantiteTransport;
    }

    public Collection<Client> getSegment(int i, int j){
        LinkedList<Client> segment = new LinkedList<>();

        for (int a=i;a < j; a++){
            segment.add(clients.get(a));
        }

        return segment;
}

    public void deleteSegment(int i, int j){
        int curr = i;
        Client client = null;

        for (Iterator<Client> iter = clients.listIterator(); iter.hasNext() && curr<j;){
           client = iter.next();

           quantiteTransport -= client.getQuantite();

           iter.remove();
           curr++;
        }
    }

    public void addClient(Client client){
        clients.add(client);
        quantiteTransport += client.getQuantite();
    }

    public void addSegment(int i, Collection<Client> segment){
        clients.addAll(i, segment);
        for (int j = 0; j < segment.size(); j++) {
            quantiteTransport += clients.get(j).getQuantite();
        }
    }

}
