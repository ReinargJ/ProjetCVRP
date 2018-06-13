import java.util.LinkedList;

public class Solution {

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

    public Solution generateVoisin(){
        //TODO
    }

    //TODO generer tous les voisins
}
