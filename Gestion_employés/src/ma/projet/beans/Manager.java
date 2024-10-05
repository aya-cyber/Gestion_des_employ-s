package ma.projet.beans;

public class Manager extends Personne {

    // Constructeur par défaut
    public Manager() {
    }

    // Constructeur avec paramètres
    public Manager(int id, String nom, double salaire) {
        super(id, nom, salaire);
    }

    public Manager(String nom, double salaire) {
        super(0, nom, salaire);
    }
}
