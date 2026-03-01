import java.util.ArrayList;

public class Ville {
    private String nomVille;
    private ArrayList<Client> listeClients;

    public Ville() {
    }

    public Ville(String ville) {
        this.listeClients = new ArrayList<>();
        this.nomVille = ville;
    }

    // To remove a client from the list
    public void removeClient(Client client) {
        this.listeClients.remove(client);
    }

    // to add a client to the list
    public void addClient(Client client) {
        this.listeClients.add(client);
    }

    public String getNomVille() {
        return this.nomVille;
    }

    public void setNomVille(String nom_ville) {
        this.nomVille = nom_ville;
    }

    // the list of all clients for the the ville(town)
    public ArrayList<Client> getListeClients() {
        return this.listeClients;
    }

    // to verify if a client already exist in the client list by is compte number
    public boolean clientExiste(String numeroCompte) {
        for (Client client : listeClients) {
            if (client.getNumeroCompte().equals(numeroCompte.replace(" ", ""))) {
                return true;
            }
        }
        return false;
    }
}
