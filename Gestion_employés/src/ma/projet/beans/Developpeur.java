package ma.projet.beans;

public class Developpeur extends Personne {

    // Constructeur par défaut
    public Developpeur() {
    }

    // Constructeur avec paramètres
    public Developpeur(int id, String nom, double salaire) {
        super(id, nom, salaire);
    }

    public Developpeur(String nom, double salaire) {
        super(0, nom, salaire);
    }
}
