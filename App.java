import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean isrunning = true;

        //Creation of the global lists client and ville
        ArrayList<Client> tableauClients = new ArrayList<>();
        ArrayList<Ville> tableauVilles = new ArrayList<>();

        while(isrunning){
            int choix = 0;
            //Choice verification
            while(choix < 1 || choix > 7){
                //Selection menu
                System.out.println("1.) Add a compte");  
                System.out.println("2.) Search compte"); 
                System.out.println("3.) Modify compte"); 
                System.out.println("4.) Remove  compte");
                System.out.println("5.) A transaction (deposit , withdrawal or transfer)");
                System.out.println("6.) User infos");
                System.out.println("7.) Quit ");

                System.out.println("");
                System.out.println("Please choose a option");
                choix = controlSaisisInt();
                input.nextLine();
                System.out.println("");
                
            }

            switch (choix) {
                case 1 -> {
                            addCompte(tableauVilles, tableauClients); 
                            System.out.println("**************************");
                        }
                case 2 -> { 
                            System.out.println("A compte number is needed for this operation");
                            String numeroCompte = controlSaisisString();
                            Client client = identifyClient(numeroCompte, tableauClients);
                            if(client != null){
                                client.searchCompte(numeroCompte);
                            }else{
                                System.out.println("Sorry but this compte number doesn't correspond to any of our clients");
                            }
                            System.out.println("**************************");
                        }
                case 3 -> { 
                            System.out.println("A compte number is needed for this operation");
                            String numeroCompte = controlSaisisString();
                            Client client = identifyClient(numeroCompte, tableauClients);
                            if(client != null){
                                modifyCompte(client, tableauVilles);
                            }else{
                                System.out.println("Sorry but this compte number doesn't correspond to any of our clients");
                                }

                            System.out.println("**************************");
                        }
                case 4 -> { 
                            System.out.println("A compte number is needed for this operation");
                            String numeroCompte = controlSaisisString();
                            Client client = identifyClient(numeroCompte, tableauClients);
                            if(client != null){client.removeCompte();
                                                tableauClients.remove(client);
                            }else{
                                System.out.println("Sorry but this compte number doesn't correspond to any of our clients");
                                 }
                            System.out.println("**************************");
                        }
                case 5 -> {
                           transaction(tableauClients);
                           System.out.println("**************************");
                        }
                            
                case 6 -> { 
                            System.out.println("Your compte number");
                            String numeroCompte = controlSaisisString();
                            Client client = identifyClient(numeroCompte, tableauClients);
                            if(client != null){
                                client.infoCompte();
                             }else{
                                System.out.println("Sorry but this compte number doesn't correspond to any of our clients");
                                }
                           System.out.println("**************************");
                        }
                case 7 -> {
                            isrunning = false; 
                            System.out.println("Thanks, and see you!");  }      
                default -> {
                            System.out.println("Please enter a valid choice");
                            System.out.println("**************************");
                        }
            }
        
        }
        input.close();
    }







    ////////////////////functios zones//////////////////////////////////

        public static void addCompte(ArrayList<Ville> tableauVilles, ArrayList<Client> tableauClients){
    

                    System.out.println("-- Phone number for registration with it indice(+223).");
                    String numeroCompte = controlSaisisString();

                    System.out.println("-- name");
                    String nom = controlSaisisString();

                    System.out.println("-- first name");
                    String prenom = controlSaisisString();

                    System.out.println("-- age");
                    int age = controlSaisisInt();
                    input.nextLine();

                    System.out.println("-- actual Ville");
                    String ville_nom = controlSaisisString();

                //To see if the ville name enter by the client exits if yes we recuperate it with the function 
                //ifVilleExiste, otherwise we create it the help of the if statement and then add it to the list of villes
                    Ville ville = ifVilleExiste(ville_nom, tableauVilles);
                    if(ville == null){
                        ville = new Ville(ville_nom);
                        tableauVilles.add(ville);
                    }

                //To see if the compte number already exist or not if no we create a new one then add it to the
                //list of the corresponding ville and then in the globale clients list
                    if(ifClientExist(numeroCompte, tableauVilles)){
                        System.out.println("Compte number "+numeroCompte+" already exist in the database");
                    }else{
                        Client client = new Client();
                        client.setNumeroCompte(numeroCompte);
                        if(client.getNumeroCompte() != null){
                           numeroCompte = numeroCompte.replace(" ", "");
                            client = new Client(numeroCompte, nom,  prenom, age, ville);
                            ville.addClient(client);
                            tableauClients.add(client);
                            System.out.println("Compte created successfully");
                            System.out.println("Your actual compte infos :");
                            client.infoCompte();
                        }else{
                            System.out.println("Compte client not created, please try enter again the tel number.");
                        }
                    }
            } 

        //To verify if a certain client exist in the global client list
        public static boolean ifClientExist(String numeroCompte, ArrayList<Ville> tableauVilles){
            for(Ville ville : tableauVilles){
                if(ville.clientExiste(numeroCompte.replace(" ",""))){
                    return true;
                }
            }
            return false;
        }

        //to verify if the ville entered exist or not
        public static Ville ifVilleExiste(String nomVille, ArrayList<Ville> tableauVilles){
            for(Ville ville : tableauVilles){
                if(ville.getNomVille().equals(nomVille.replace(" ",""))){
                    return ville;
                }
            }
            return null;
        }   

        //to identify a client by it compte number and then recuperate is data
        public static Client identifyClient(String numeroCompte, ArrayList<Client> tableauClients){
            for(Client client : tableauClients){
                if(client.getNumeroCompte().equals(numeroCompte.replace(" ",""))){
                    return client;
                }
            }
            return null;
        }

        //To modify the informations of a compte with the getters, setters et other functions
        public static void modifyCompte(Client client, ArrayList<Ville> tableauVilles){
            
            System.out.println("1.) change name");
            System.out.println("2.) change surname (first name)");
            System.out.println("3.) change age");
            System.out.println("4.) change ville");
            System.out.println("");
            System.out.println("Please enter a choice");
            int choix = controlSaisisInt();
            input.nextLine();
            System.out.println("");

            switch(choix){
                case 1 -> {
                            System.out.println("New name");
                            String name = controlSaisisString();
                            client.setName(name);
                          }
                case 2 -> { 
                            System.out.println("New surname");
                            String surname = controlSaisisString();
                            client.setSurname(surname);
                          }  
                case 3 ->  { 
                            System.out.println("New age");
                            int age = controlSaisisInt();
                            client.setAge(age);
                           }   
                case 4 ->  {    
                                System.out.println("New ville");
                                String nomVille = controlSaisisString();
                                Ville ville = ifVilleExiste(nomVille, tableauVilles);
                                if(ville == null){
                                    ville = new Ville(nomVille);
                                    ville.addClient(client);
                                    tableauVilles.add(ville);
                                    }
                                client.changerVille(ville);
                           }
                default -> System.out.println("Please enter a valid choice");                                         
            }
        }
        
        //the differents banking operations possible for the client
        public static void transaction(ArrayList<Client> tableauClients){

            System.out.println("1.) a deposit ");
            System.out.println("2.) a withdrwal ");
            System.out.println("3.) a transefer ");
            System.out.println("");
            System.out.println("Please choice a  transaction option");
            int choix = controlSaisisInt();
            input.nextLine();
            System.out.println("");

            switch(choix){
                case 1 -> { 
                            System.out.println("A compte number is needed for this operation");
                            String numeroCompte = controlSaisisString();
                            Client client = identifyClient(numeroCompte, tableauClients);
                            if(client != null){
                                 System.out.println("Your montant ");
                                 double montant = controlSaisisDouble();
                                 client.deposit(montant);
                            }else{
                                System.out.println("Sorry but this compte number doesn't correspond to any of our clients");
                            }
                           
                        }
                case 2 -> {
                            System.out.println("A compte number is needed for this operation");
                            String numeroCompte = controlSaisisString();
                            Client client = identifyClient(numeroCompte, tableauClients);
                            if(client != null){
                                System.out.println("Your montant ");
                                double montant = controlSaisisDouble();
                                client.retirer(montant);
                            }else{
                                System.out.println("Sorry but this compte number doesn't correspond to any of our clients");
                            }
                            
                        }
                case 3 -> { 
                            System.out.println("Two compte number are needed for this operation");
                            System.out.println("The sender's compte number(Yours)");
                            String numeroCompte1 = controlSaisisString();
                            Client client1 = identifyClient(numeroCompte1, tableauClients);
                            System.out.println("The receier's compte number");
                            String numeroCompte2 = controlSaisisString();
                            Client client2 = identifyClient(numeroCompte2, tableauClients);
                            if(client1 != null && client2 != null){
                                System.out.println("Your montant ");
                                double montant = controlSaisisDouble();
                                client1.transferer(client2, montant);
                            }else{
                                System.out.println("Transfer impossible , [client1 or client2  indisponible]");
                            }
                            
                        }
                default -> System.out.println("Please enter a valid choice");
            }
        }
        
        public static String controlSaisisString(){
            String nom;
            while(true){
                try{
                System.out.print("Enter: ");
                nom = input.nextLine();
                return nom;
                }catch(Exception e){
                    System.out.println("Please enter a String.");
                    input.nextLine();
                }
            }     
        }

        //Saisi controls for int
        public static int controlSaisisInt(){
            int age;
            while(true){
                try{
                System.out.print("Enter: ");
                age = input.nextInt();
                return age;
                }catch(Exception e){
                    System.out.println("Please enter a integer.");
                    input.nextLine();
                }
            }
        
        }

        //Saisi controls for double
        public static double controlSaisisDouble(){
            double montant;
            while(true){
                try{
                System.out.print("Enter: ");
                montant = input.nextDouble();
                return montant;
                }catch(Exception e){
                    System.out.println("Please enter a double.");
                    input.nextLine();
                }
            }
        }
        
}
    

