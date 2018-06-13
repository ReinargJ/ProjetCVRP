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
        Graphe graphe = new Graphe(clients, depot);

       Solution solutionInitiale =  graphe.genererSolutionAleatoire();
        ArrayList voisins = new ArrayList();
        try {
            voisins = solutionInitiale.generateVoisins();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(solutionInitiale);

        //Tabou tabou = new Tabou(10,voisins);
        

    }
}
