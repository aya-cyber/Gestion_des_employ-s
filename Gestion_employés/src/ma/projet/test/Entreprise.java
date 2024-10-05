package ma.projet.test;

import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.service.DeveloppeurService;
import ma.projet.service.ManagerService;

import java.util.List;

public class Entreprise {

    public static void main(String[] args) {
        DeveloppeurService developpeurService = new DeveloppeurService();
        ManagerService managerService = new ManagerService();
        Developpeur dev1 = new Developpeur("Dev 1", 3000);
        Developpeur dev2 = new Developpeur("Dev 2", 3200);
        developpeurService.create(dev1);
        developpeurService.create(dev2);
        Manager manager = new Manager("Manager 1", 5000);
        managerService.create(manager);
        Developpeur dev3 = new Developpeur("Dev 3", 3500);
        developpeurService.create(dev3);
        Manager directeurGeneral = new Manager("Directeur Général", 8000);
        managerService.create(directeurGeneral);
        System.out.println("---- Hiérarchie des employés ----");
        System.out.println("Directeur Général: " + directeurGeneral.getNom() + " - Salaire: " + directeurGeneral.getSalaire());
        System.out.println("Manager: " + manager.getNom() + " - Salaire: " + manager.getSalaire());
        System.out.println("Développeurs gérés par le manager:");
        List<Developpeur> developpeurs = developpeurService.getAll();
        for (Developpeur dev : developpeurs) {
            System.out.println("Développeur: " + dev.getNom() + " - Salaire: " + dev.getSalaire());
        }
        System.out.println("Développeur direct sous la responsabilité du Directeur Général: " + dev3.getNom() + " - Salaire: " + dev3.getSalaire());

    }
}
