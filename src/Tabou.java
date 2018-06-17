import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tabou {

    private int tailleInterdits;

    private ArrayList interdits;

    private Solution solutionOpti;

    private int distanceOpti;

    private ArrayList<Solution> listeTabou;

    public Tabou(int tailleInterdits, Solution solutionInitiale) {
        this.tailleInterdits = tailleInterdits;
        this.solutionOpti = solutionInitiale;
        this.distanceOpti = this.solutionOpti.getDistance();
        this.listeTabou = new ArrayList(this.tailleInterdits);
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

    public Solution exectuerTabou(){

        Solution localBestSolution = this.solutionOpti;

        int i =0;
        do {
            try {
                Boolean addToList = true;
                ArrayList<Solution> voisins = localBestSolution.generateVoisins();
                Solution currentBest = voisins.get(0);

                for (int j = 0; j < voisins.size(); j++) {
                    Solution voisin = voisins.get(j);

                    if(voisin.getDistance() < this.solutionOpti.getDistance()){
                        this.solutionOpti = voisin;
                        currentBest = voisin;
                        localBestSolution = voisin;
                        addToList = false;
                    }
//                    else if(voisin.getDistance() < localBestSolution.getDistance()){
//                        if(!(this.listeTabou.contains(voisin))) {
//                            currentBest = voisin;
//                            addToList = true;
//                        }
//                    }
                }
                if(addToList){


                    Solution min=null;
                    int a = 0;

                    while(min == null){
                        if(!this.listeTabou.contains(voisins.get(a))){
                            min = voisins.get(a);
                        }
                    }


                    for(Solution v: voisins){
                        if(min.getDistance()>= v.getDistance()){
                            if(!this.listeTabou.contains(v)){
                                min = v;
                            }
                        }
                    }

                    if(this.listeTabou.size() > this.tailleInterdits){
                        this.listeTabou.remove(0);
                    }

                    this.listeTabou.add(min);
                    currentBest = min;
                }
                localBestSolution = currentBest;

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            i++;
        } while(i<2000);

        return this.solutionOpti;

    }


}

