import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Graphe {
    private List<Client> clients;
    private List<Client> nonDesservi;
    private final int quantiteMaxCamion =  100;
    private Trajet trajetOpti;
    //private Trajet trajetCourant;
    private Client depot;

    public Graphe(List<Client> clients, Client depot) {
        this.clients = clients;
        this.nonDesservi = clients;
        this.depot = depot;
    }

    public Solution genererSolutionAleatoire() {
        LinkedList<Trajet> trajets = new LinkedList<>();

        while(nonDesservi.size() > 0){
            int r = new Random().nextInt(nonDesservi.size());
            Client currentClient = nonDesservi.get(r);
            nonDesservi.remove(r);
            Trajet trajet = new Trajet(depot);
            trajets.add(trajet);
            trajet.addClient(currentClient);
            Client closest = getClosest(nonDesservi,currentClient);

            while(closest!=null && nonDesservi.size()>0 && trajet.getQuantiteTransport() + closest.getQuantite() <=quantiteMaxCamion) {
                trajet.addClient(closest);
                currentClient = closest;
                nonDesservi.remove(closest);

                closest = getClosest(nonDesservi,currentClient);
            }

            trajet.addClient(depot);
            trajet.calculDistanceTrajet();
        }

        return new Solution(trajets);
    }

    public Client getClosest(List<Client> clients, Client client){
        Client closest = null;
        Client tmp;
        int distMin=-1;

        for (Iterator<Client> iter = clients.listIterator(); iter.hasNext();){
            tmp = iter.next();

            int dist = client.calculDistance(tmp);
            if(distMin == -1 || distMin > dist){
                distMin = dist;
                closest = tmp;
            }
        }
        return closest;
    }

}
