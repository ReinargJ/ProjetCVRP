import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String [ ] args) {
        LinkedList<Client> clients = new LinkedList<>();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader("resources\\data01.txt"));
            String read = null;
            while ((read = in.readLine()) != null) {
                String[] splited = read.split("\\s+");
                for (String part : splited) {
                    String [] clientSplit = part.split(";");
                    if(!clientSplit[0].equals("i")){
                        clients.add(new Client(Integer.parseInt(clientSplit[0]), Integer.parseInt(clientSplit[1]), Integer.parseInt(clientSplit[2]), Integer.parseInt(clientSplit[3])));
                    }
                }
            }
            System.out.println("loaded");
        } catch (IOException e) {
            System.out.println("There was a problem: " + e);
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }

        Client depot = clients.poll();

        Fourmis fourmis = new Fourmis(clients.size()+1, clients, depot);

        Graphe graphe = new Graphe(clients, depot);

       Solution solutionInitiale =  graphe.genererSolutionAleatoireClosest();

        System.out.println("Solution initiale: "+solutionInitiale.getDistance());
        System.out.println("En cours...");
        Tabou tabou = new Tabou(20,solutionInitiale);

        Solution best = tabou.exectuerTabou();
        System.out.println("done: "+best.getDistance());

        fourmis.runFourmis();
    }
}
