import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tabou {

    private int tailleInterdits;

    private ArrayList interdits;

    private Solution solutionOpti;

    private int distanceOpti;

    public Tabou(int tailleInterdits, LinkedList<Trajet> trajetsInitial) {
        this.tailleInterdits = tailleInterdits;
        this.solutionOpti = new Solution(trajetsInitial);
        this.distanceOpti = this.solutionOpti.getDistance();
    }

    public int getTailleInterdits() {
        return tailleInterdits;
    }

    public void setTailleInterdits(int tailleInterdits) {
        this.tailleInterdits = tailleInterdits;
    }

    public ArrayList getInterdits() {
        return interdits;
    }

    public void setInterdits(ArrayList interdits) {
        this.interdits = interdits;
    }

    public Solution getSolutionOpti() {
        return solutionOpti;
    }

    public void setSolutionOpti(Solution solutionOpti) {
        this.solutionOpti = solutionOpti;
    }

    public List<Trajet> exectuerTabou(){


        List<Trajet> solution = null;
        //int tailleSegment = 2;

        Trajet trajet1;
        Trajet trajet2;
        for (Iterator<Trajet> iter = solutionOpti.getTrajets().listIterator(); iter.hasNext();) {
            trajet1 = iter.next();
            int debut = (int)Math.random()*(trajet1.getClients().size() - 1);
            int fin = (int)Math.random()*(trajet1.getClients().size() - debut);
            trajet1.getSegment(debut, fin);

        }
            return solution;

    }


}

