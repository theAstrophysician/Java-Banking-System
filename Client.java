

public class Client{
    private String numeroCompte;
    private String name;
    private String surname;
    private int age;
    private double soldeCompte;
    private Ville ville;

    //contructors
    public Client(){}
    public Client(String numeroTel, String name, String surname, int age, Ville ville){
        this.numeroCompte = numeroTel;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.ville = ville;
        this.soldeCompte = 0;

    }

    //setter and getter for the attribut ville of type Ville

    //to modify the ville(town) of a client, by writting is name in the list of the new ville (town) 
    //and removing it from the list of the previous ville
    public void changerVille(Ville nom_ville){
        if (nom_ville != null) {
            if(nom_ville.getListeClients().contains(this)){
                if(getVille() != null){
                    ville.removeClient(this);
                }
                this.ville = nom_ville;
                ville.getListeClients().add(this);
                System.out.println("Ville remplacé avec succès!");
            }else{
                System.out.println("Attention ce client est non contenu dans la liste de cette ville.");
            }
        }
    }

    public Ville getVille(){
        return this.ville;
    }

    //setter and getter for the attribut nom
    public void setName(String name){
        this.name = name;
        System.out.println("Name replaced successfully!");
    }
    public String getName(){
        return this.name;
    }

    //setter and getter for the attribut prenom
    public void setSurname(String surname){
        this.surname = surname;
        System.out.println("Surname replaced successfully!");
    }
    public String getSurname(){
        return this.surname;
    }

    //setter and getter for the attribut numeroCompte

    //will verify the lenght and the indice (indice can be replaced) of the number before comfirmation
    //otherwise will sent a mesage prompt to signal the problem
    public void setNumeroCompte(String numeroCompte){
        numeroCompte = numeroCompte.replace(" ", "");
        int longueurNumero = numeroCompte.length();
        if(longueurNumero == 12 ){
            if(numeroCompte.startsWith("+223 7") || numeroCompte.startsWith("+223 6") ||
            numeroCompte.startsWith("+223 8") || numeroCompte.startsWith("+223 9"))
            {
                this.numeroCompte = numeroCompte;
                System.out.println("Number registred!!");           
            }
            else if(numeroCompte.startsWith("+2237") || numeroCompte.startsWith("+2236") ||
                    numeroCompte.startsWith("+2238") || numeroCompte.startsWith("+2239"))
                    {
                     this.numeroCompte = numeroCompte;
                     System.out.println("Number registred!!");
                    }
            else
            {
                System.out.println("Invalid number, must begin by the indice +223(needed)");
            }
        }else
        {
            System.out.println("Incorrect number");
        }
    }

    public String getNumeroCompte(){
        return this.numeroCompte;
    }

    //setter and getter for the attribut age
    public void setAge(int age){
        if(age < 18){
            System.out.println("Your are to young to open a compte");
        }else{
            this.age = age;
            System.out.println("Age replaced successfully!");
        }  
    }

    public int getAge(){
        return this.age;
    }

    //setter and getter for the attribut soldeCompte

    /* public void setSoldeCompte(double soldeCompte){
        this.soldeCompte = soldeCompte;
    } */
    public double getSoldeCompte(){
        return this.soldeCompte;
    }

    
    //////////work functions////////////////////
    //remove the compte
    public void removeCompte(){
        if(this.soldeCompte == 0){
            this.getVille().removeClient(this);
            this.destroy();
        }else{
            System.out.println("Compte suppression impossible, because balance not empty");
            System.out.println("Transfer needed or balance must be at 0 before a suppression operation");
        }
        
    }

    //To do a deposit 
    public void deposit(double montant){
        if(montant > 0){
            if(montant >= 500){
                this.soldeCompte += montant;
                System.out.println("Deposit successfull");
                System.out.println("Your actual balance is of : "+ this.soldeCompte+ "fcfa");
            }else{
                System.out.println("Montant too little for a deposit");
            }
        }else{
            System.out.println("Invalid montant,please enter a positive montant.");
        }    
    }

    //To do a withdrawal
    public void retirer(double montant){
        if(montant > 0){
            if(montant <= this.soldeCompte){
                if(montant >= 500){
                    this.soldeCompte -= montant;
                    System.out.println("Withdrawal successfull!");
                    System.out.println("Your actual balance is of : "+ this.soldeCompte+ "fcfa");
                }else{
                    System.out.println("Montant too little for a withdrawal, must be >= 500.");
                }  
            }else{
                System.out.println("Your balance is insufficient for this withdrawal.");
            }
        }else{
            System.out.println("Invalid montant,please enter a correct montant.");
        }
    }

    //To do a transfer, client1 (this) do the transfer and client2 receive it
    public void transferer(Client client2, double montant){
            if(montant > 0){
                if(montant <= this.soldeCompte){
                    if(montant >= 500){
                        this.soldeCompte -= montant;
                        client2.soldeCompte += montant;
                        System.out.println("Transfer successfull!");
                        System.out.println("Your actual balance is of : "+this.soldeCompte+" fcfa");
                    }else{
                        System.out.println("Montant too litle for a transfer, must be >= 500.");
                    }  
                }else{
                    System.out.println("Your balance is insufficient for this withdrawal.");
                }
            }else{
                System.out.println("Invalid montant,please enter a correct montant.");
            }   
    }

    //To find a client by it compte number
    public void searchCompte(String numeroCompte){
            if(this.numeroCompte.equals(numeroCompte.replace(" ",""))){
                System.out.println(this.numeroCompte+" correspond to the compte number of the user "+this+
                                                        " of name : "+this.name+" and first name : "+this.surname);
            }else{
                System.out.println("This number seems to not correspond to any of our client");
            }
    }


    //info on the compte (the current client object in use  'this')
    public void infoCompte(){
            System.out.println("*********************");
            System.out.println("User : "+this);
            System.out.println("Compte number : "+numeroCompte);
            System.out.println("name first name and age : "+name+" "+surname+" "+ age+" ans");
            System.out.println("actual ville : "+ ville.getNomVille());
            System.out.println("actual balance : "+ soldeCompte+" fcfa");
            System.out.println("*********************");
    }
    
    public void destroy(){
        this.name = null;
        this.surname = null;
        this.age = 0;
        this.numeroCompte = null;
        this.ville = null;
    }


}