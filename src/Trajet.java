import java.util.*;

public class Trajet implements Cloneable{
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

    public Trajet(Client depot, int distance, int quantiteTransport) {
        this.depot = depot;
        this.distance = distance;
        this.clients = new LinkedList<Client>();
        this.clients.add(depot);
        this.quantiteTransport =quantiteTransport;
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

    public void addClient(Client client, int position ){
        clients.add(position, client);
        quantiteTransport += client.getQuantite();
    }

    public void addClient(Client client){
        if(clients.size()> 0){
            calculDistanceTrajet();
        }

        clients.add(client);
        quantiteTransport += client.getQuantite();
    }



    public void addSegment(int i, Collection<Client> segment){
        clients.addAll(i, segment);
        for (int j = i; j < i+segment.size(); j++) {
            quantiteTransport += clients.get(j).getQuantite();
            calculDistanceTrajet();
        }
    }

    public void removeClient(Client client){
        this.clients.remove(client);
        this.quantiteTransport -= client.getQuantite();
    }

    public void calculDistanceTrajet(){
        this.distance = 0;

        for (int i = 1; i < clients.size(); i++) {
            this.distance+=this.clients.get(i).calculDistance(this.clients.get(i-1));
        }
    }
    public void calculDistanceTrajet(Client nouveauClient, int position){
        this.distance += nouveauClient.calculDistance(clients.get(position));
    }


    public Client getDepot() {
        return depot;
    }

    private List<Client> cloneList(List<Client> list) {
        try{
            List<Client> clone = new LinkedList<Client>();
            for (Client item : list) clone.add(item.clone());
            return clone;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    @Override
    protected Trajet clone() throws CloneNotSupportedException {
        Trajet t = new Trajet(this.depot.clone(), this.distance, this.quantiteTransport);
        t.setClients((LinkedList)this.cloneList(this.clients));

        return t;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Trajet)) return false;

        Trajet t = (Trajet)o;

        return t.distance == this.distance && t.quantiteTransport == this.quantiteTransport && this.clients.equals(t.clients);
    }

    @Override
    public String toString() {
        String s = " ";

        for(Client client : clients){
            s+= client.getNumClient() + " -> ";
        }

        return s;
    }
}
