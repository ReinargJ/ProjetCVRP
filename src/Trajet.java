import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Trajet {
    private int distance;
    private Client depot;
    private int quantiteTransport;

    private LinkedList<Client> clients;

    public Trajet(Client depot, LinkedList<Client> clients) {
        this.depot = depot;
        this.clients = clients;
        this.quantiteTransport =0;

        Client tmp = null;

        for (Iterator<Client> iter = clients.listIterator(); iter.hasNext();){
            tmp = iter.next();

            this.quantiteTransport += tmp.getQuantite();
        }
    }

    public Trajet(Client depot) {
        this.depot = depot;
        this.clients = new LinkedList<Client>();
        this.clients.add(depot);
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
        if(clients.size()> 0){
            calculAjoutDistance(client);
        }

        clients.add(client);
        quantiteTransport += client.getQuantite();
    }

    public void addSegment(int i, Collection<Client> segment){
        clients.addAll(i, segment);
        for (int j = i; j < i+segment.size(); j++) {
            quantiteTransport += clients.get(j).getQuantite();
            calculAjoutDistance(clients.get(j));
        }
    }

    public void calculAjoutDistance(Client nouveauClient){
        this.distance += nouveauClient.calculDistance(clients.getLast());
    }

}
