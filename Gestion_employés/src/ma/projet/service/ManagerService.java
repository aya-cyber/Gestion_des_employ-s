/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

/**
 *
 * @author Lenovo
 */

import ma.projet.dao.IDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;

public class ManagerService implements IDao<Manager> {

    @Override
    public boolean create(Manager manager) {
        try {
            String sql = "INSERT INTO manager(nom, salaire) VALUES(?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la création: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Manager manager) {
        try {
            String sql = "UPDATE manager SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, manager.getNom());
            ps.setDouble(2, manager.getSalaire());
            ps.setInt(3, manager.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Manager manager) {
        try {
            String sql = "DELETE FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, manager.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Manager getById(int id) {
        try {
            String sql = "SELECT * FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Manager> getAll() {
        List<Manager> managers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM manager";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                managers.add(new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de la liste: " + ex.getMessage());
        }
        return managers;
    }
}

