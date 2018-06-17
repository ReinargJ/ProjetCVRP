import java.util.*;

public class Fourmis {

    private int size;
    private static Double[][] pheromone;
    private List<Client> clients;
    private List<Client> nonDesservi;
    private final int quantiteMaxCamion =  100;
    private final int NB_FOURMI =100;
    private Client depot;

    public Fourmis(int size, List<Client> clients, Client depot) {
        this.size = size;
        this.clients = cloneList(clients);
        this.nonDesservi = cloneList(clients);
        this.depot = depot;
    }

    public void runFourmis(){
        pheromone = new Double[size][size];
        for(Double[] row : pheromone){
            Arrays.fill(row, 1.0);
        }
        int best = 99999999;
        Solution bestFourmi = new Solution();

        for(int iteration = 1; iteration <= 2000; iteration++){

            ArrayList<Solution> fourmis = new ArrayList<>();
            for(int i = 0; i<NB_FOURMI; i++){
                Solution fourmi = genererSolutionAleatoire();
                fourmis.add(fourmi);
            }
            Double moyenne = getMoyenne(fourmis);
            clearPheromone();

            for(Solution fourmi : fourmis){
                fillPheromone(fourmi, moyenne);
            }
            pheromone[0][0] = 1.0;

            int bestLocal = 100000000;
            Solution bestFourmiLocal = new Solution();
            for(Solution fourmi : fourmis){
                if(fourmi.getDistance() < bestLocal){
                    bestFourmiLocal = fourmi;
                    bestLocal = fourmi.getDistance();
                }
            }
            System.out.println("Iteration " + iteration + " : " + bestLocal);
            if(bestLocal < best){
                bestFourmi = bestFourmiLocal;
                best = bestLocal;
            }
        }

        System.out.println("Meilleure fitness : " + best);
        System.out.println(bestFourmi);
    }

    public Solution genererSolutionAleatoire() {
        Solution solution = new Solution();
        LinkedList<Trajet> trajets = solution.getTrajets();

        nonDesservi = cloneList(clients);

        while(nonDesservi.size() > 0){
            int r = new Random().nextInt(nonDesservi.size());
            Client currentClient = nonDesservi.get(r);
            nonDesservi.remove(r);
            Trajet trajet = new Trajet(depot);
            trajets.add(trajet);
            trajet.addClient(currentClient);

            int nbTrajet, quantiteTot=0;

            for(Client client : clients){
                quantiteTot += client.getQuantite();
            }

            nbTrajet = Math.floorDiv(quantiteTot,100);


            for (int i = 0; i < nbTrajet; i++) {
                trajets.add(new Trajet(depot));
            }

            Collections.shuffle(nonDesservi);
            while(nonDesservi.size()>0)  {
                Client next = nonDesservi.get(0);
                HashMap<Double, Integer> choix = new HashMap<Double, Integer>();

                for (int i = 0; i < nbTrajet; i++) {
                    Trajet t = trajets.get(i);

                    Client precedent = t.getClients().getLast();
                    choix.put(Math.random() * pheromone[next.getNumClient()][precedent.getNumClient()], i);
                }

                SortedSet<Double> keys = new TreeSet<Double>(choix.keySet());
                boolean added = false;

                for(Double key : keys){
                    if(!added){
                        int numTrajet = choix.get(key);
                        if (trajets.get(numTrajet).getQuantiteTransport()+next.getQuantite() <= quantiteMaxCamion){
                            trajets.get(numTrajet).addClient(next);
                            added = true;
                        }
                    }
                }
                if(!added){
                    Trajet newTrajet = new Trajet(depot);
                    newTrajet.addClient(next);
                    trajets.add(newTrajet);
                    nbTrajet++;
                }
                nonDesservi.remove(0);
            }

            for(Trajet t : trajets ){
                t.addClient(depot);
                t.calculDistanceTrajet();
            }
        }
        solution.calculDistance();

        return solution;
    }

    private static void fillPheromone(Solution fourmi, Double moyenne) {
        int cout = fourmi.getDistance();
        Double mult = cout / moyenne;


        for(Trajet trajet : fourmi.getTrajets()){
            boolean premier = true;
            Client prec = trajet.getDepot();
            LinkedList<Client> clients = trajet.getClients();

            for (int i = 1; i < clients.size(); i++) {
                Client client = clients.get(i);

            }
            for(Client c : trajet.getClients()){
                pheromone[prec.getNumClient()][c.getNumClient()] *= mult;
                pheromone[c.getNumClient()][prec.getNumClient()] *= mult;
                prec = c;
            }
        }
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

    private static void clearPheromone() {
        for (int i = 0; i< pheromone[0].length; i++){
            for (int j = 0; j< pheromone[0].length; j++){
                if(pheromone[i][j] > 1){
                    pheromone[i][j] = ((pheromone[i][j] - 1) / 9) +1;
                }
                else if (pheromone[i][j] < 1){
                    pheromone[i][j] = 1 - ((1 - pheromone[i][j]) / 9);
                }

            }
        }
    }

    private double getMoyenne(ArrayList<Solution> fourmis){
        Double moy = 0.0;

        for(Solution fourmi: fourmis){
            moy += fourmi.getDistance();
        }

        return moy/fourmis.size();
    }
}
