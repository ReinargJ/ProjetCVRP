import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graphe {
    private List<Client> clients;
    private List<Client> nonDesservi;
    private final int quantiteMaxCamion =  100;
    private Trajet trajetOpti;
    //private Trajet trajetCourant;

    private Client depot = new Client(0,0,0);

    public void genererSolutionAleatoire() {
        while(nonDesservi.size() > 0){
            int r = (int)Math.random()*((nonDesservi.size() - 0)+1);
            Client currentClient = nonDesservi.get(r);
            nonDesservi.remove(r);
            Trajet trajet = new Trajet(0, 0);
            trajet.addClient(currentClient);
            Client closest;

            do {
                closest = getClosest(nonDesservi,currentClient);
                trajet.addClient(closest);
                currentClient = closest;
            }while(trajet.getQuantiteTransport() + closest.getQuantite() <=quantiteMaxCamion);
        }
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
