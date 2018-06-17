import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Solution implements Cloneable{

    private LinkedList<Trajet> trajets;

    private int distance;

    public Solution(LinkedList<Trajet> trajets) {
        this.trajets = trajets;

        this.calculDistance();
    }

    public Solution(){
        this.trajets = new LinkedList<>();
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

    public void calculDistance(){
        this.distance =0;
        trajets.forEach(t -> {
            this.distance+=t.getDistance();
        });
    }

    public ArrayList<Solution> generateVoisins() throws CloneNotSupportedException {
        ArrayList<Solution> voisins = new ArrayList();

        for (int i = 0; i < this.trajets.size(); i++) {
            Trajet trajet = this.trajets.get(i);

            for (int j = 1; j < trajet.getClients().size()-1; j++) {
                Client client = trajet.getClients().get(j);

                for (int k = 0; k < this.trajets.size(); k++) {
                    Trajet trajetInsertion = this.trajets.get(k);

                    if(client.getQuantite() + trajetInsertion.getQuantiteTransport() <= 100){
                        for(int l = 1; l< trajetInsertion.getClients().size()-1; l++){
                            Client clientCourant = trajetInsertion.getClients().get(l);

                            if(client.getNumClient() != clientCourant.getNumClient()){
                                try {
                                    Solution cloneSolution = (Solution)this.clone();

                                    Trajet oldTrajet = cloneSolution.getTrajets().get(i);
                                    Trajet insertTrajet = cloneSolution.getTrajets().get(k);

                                    //System.out.println(insertTrajet.getClients().size());

                                    oldTrajet.removeClient(client);
                                    insertTrajet.addClient(client, l);

                                    //System.out.println(insertTrajet.getClients().size());
                                    oldTrajet.calculDistanceTrajet();
                                    insertTrajet.calculDistanceTrajet();
                                    cloneSolution.calculDistance();
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

        //System.out.println(voisins.size());
        return voisins;

    }

    private List<Trajet> cloneList(List<Trajet> list) {
        try{
            List<Trajet> clone = new LinkedList<Trajet>();
            for (Trajet item : list) clone.add(item.clone());
            return clone;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution s = new Solution((LinkedList)this.cloneList(this.trajets));
        return s;
    }

    public boolean equals(Object o){
        if(!(o instanceof Solution)) return false;

        Solution sol = (Solution)o;

        return this.distance == sol.distance && this.trajets.equals(sol.trajets);
    }

    @Override
    public String toString() {
        String s ="";

        for (Trajet trajet : trajets){
            s+=trajet.toString()+"\n";
        }
        return s;
    }

    //TODO generer tous les voisins


}
