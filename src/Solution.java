import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Solution implements Cloneable{

    private LinkedList<Trajet> trajets;

    private int distance;

    public Solution(LinkedList<Trajet> trajets) {
        this.trajets = trajets;

        trajets.forEach(t -> {
            this.distance+=t.getDistance();
        });
    }

    public LinkedList<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(LinkedList<Trajet> trajets) {
        this.trajets = trajets;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }



    public ArrayList<Solution> generateVoisins() throws CloneNotSupportedException {
        ArrayList<Solution> voisins = new ArrayList();

        for (int i = 0; i < this.trajets.size(); i++) {
            Trajet trajet = this.trajets.get(i);

            for (int j = 0; j < trajet.getClients().size(); j++) {
                Client client = trajet.getClients().get(j);

                for (int k = 0; k < this.trajets.size(); k++) {
                    Trajet trajetInsertion = this.trajets.get(k);

                    if(client.getQuantite() + trajetInsertion.getQuantiteTransport() <= 100){
                        for(int l = 0; l< trajet.getClients().size(); l++){
                            Client clientCourant = trajet.getClients().get(l);

                            if(!client.equals(clientCourant)){
                                try {
                                    Solution cloneSolution = (Solution)this.clone();

                                    Trajet oldTrajet = cloneSolution.getTrajets().get(i);
                                    Trajet insertTrajet = cloneSolution.getTrajets().get(k);

                                    insertTrajet.addClient(client);
                                    oldTrajet.getClients().remove(clientCourant);

                                    voisins.add(cloneSolution);
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                }
            }
        }

        return voisins;

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        this.trajets = (LinkedList)this.trajets.clone();
        return super.clone();
    }

    //TODO generer tous les voisins


}
