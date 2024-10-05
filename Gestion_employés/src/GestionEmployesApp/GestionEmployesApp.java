package GestionEmployesApp;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class GestionEmployesApp extends JFrame {

    private DefaultMutableTreeNode root; 
    private JTree employeeTree; 

    public GestionEmployesApp() {
       
        setTitle("Gestion des Employés");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        root = new DefaultMutableTreeNode("Employés");
        initializeEmployeeTree();
        employeeTree = new JTree(root);
        JScrollPane treeScroll = new JScrollPane(employeeTree);
        add(treeScroll, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Ajouter Employé");
        JButton refreshButton = new JButton("Rafraîchir");
        addButton.addActionListener(e -> addEmployee());
        refreshButton.addActionListener(e -> refreshTree());

        panel.add(addButton);
        panel.add(refreshButton);
        add(panel, BorderLayout.SOUTH);
    }

    private void initializeEmployeeTree() {
      
        DefaultMutableTreeNode directeurGeneral = new DefaultMutableTreeNode("Rami (Directeur Général)");
        root.add(directeurGeneral);

   
        DefaultMutableTreeNode developpeur = new DefaultMutableTreeNode("Amali (Développeur)");
        directeurGeneral.add(developpeur);

     
        DefaultMutableTreeNode manager = new DefaultMutableTreeNode("Saddik (Manager)");
        directeurGeneral.add(manager);

      
        DefaultMutableTreeNode dev1 = new DefaultMutableTreeNode("Sebihi (Développeur)");
        DefaultMutableTreeNode dev2 = new DefaultMutableTreeNode("Nouari (Développeur)");
        manager.add(dev1);
        manager.add(dev2);
    }

    private void addEmployee() {
 
        String nom = JOptionPane.showInputDialog(this, "Nom de l'employé:");
        String role = JOptionPane.showInputDialog(this, "Rôle (Directeur Général / Manager / Développeur):");

        if (nom != null && role != null) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nom);
            switch (role.toLowerCase()) {
                case "directeur général":
                    root.add(newNode);
                    break;
                case "manager":
                    DefaultMutableTreeNode directorNode = findNodeByName("Rami (Directeur Général)");
                    if (directorNode != null) {
                        directorNode.add(newNode);
                    }
                    break;
                case "développeur":
                    DefaultMutableTreeNode managerNode = findNodeByName("Saddik (Manager)");
                    if (managerNode != null) {
                        managerNode.add(newNode);
                    } else {
                      
                        DefaultMutableTreeNode directorNodeForDev = findNodeByName("Rami (Directeur Général)");
                        if (directorNodeForDev != null) {
                            directorNodeForDev.add(newNode);
                        }
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Rôle inconnu. Veuillez entrer un rôle valide.");
                    return;
            }
          
            ((DefaultTreeModel) employeeTree.getModel()).reload();
        }
    }

    private DefaultMutableTreeNode findNodeByName(String name) {
    
        return findNodeByName(root, name);
    }

    private DefaultMutableTreeNode findNodeByName(DefaultMutableTreeNode node, String name) {
        if (node.getUserObject().equals(name)) {
            return node;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode foundNode = findNodeByName((DefaultMutableTreeNode) node.getChildAt(i), name);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }

    private void refreshTree() {
      
        JOptionPane.showMessageDialog(this, "Rafraîchir la liste des employés (fonctionnalité à implémenter)");
    }

    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(() -> {
            GestionEmployesApp app = new GestionEmployesApp();
            app.setVisible(true);
        });
    }
}
