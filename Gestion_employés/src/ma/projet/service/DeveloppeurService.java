package ma.projet.service;

import ma.projet.dao.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Developpeur;
import ma.projet.connexion.Connexion;

public class DeveloppeurService implements IDao<Developpeur> {

    @Override
    public boolean create(Developpeur dev) {
        try {
            String sql = "INSERT INTO developpeur(nom, salaire) VALUES(?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, dev.getNom());
            ps.setDouble(2, dev.getSalaire());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la création: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Developpeur dev) {
        try {
            String sql = "UPDATE developpeur SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, dev.getNom());
            ps.setDouble(2, dev.getSalaire());
            ps.setInt(3, dev.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Developpeur dev) {
        try {
            String sql = "DELETE FROM developpeur WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, dev.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Developpeur getById(int id) {
        try {
            String sql = "SELECT * FROM developpeur WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Developpeur> getAll() {
        List<Developpeur> developpeurs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM developpeur";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                developpeurs.add(new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de la liste: " + ex.getMessage());
        }
        return developpeurs;
    }
}
