package org.leotalleceven.bibliotheque.dao;

import org.jetbrains.annotations.NotNull;
import org.leotalleceven.bibliotheque.models.Auteur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuteurDAO extends DAO<Auteur> {


    @Override
    public boolean create(@NotNull Auteur aObj) {
        try {
            // String titre, int caution, String codeBarre, String iSBN, String auteur, String genre
            String sql = "INSERT INTO Auteur VALUES (?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, aObj.getId());
            stmt.setString(2, aObj.getNom());
            stmt.setString(3, aObj.getPrenom());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Auteur aObj) {
        return false;
    }

    @Override
    public boolean update(Auteur aObj) {
        return false;
    }

    @Override
    public Auteur find(int aId) {
        return null;
    }
}
