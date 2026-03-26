package org.leotalleceven.bibliotheque.dao.utilisateur;

import org.jetbrains.annotations.NotNull;
import org.leotalleceven.bibliotheque.dao.DAO;
import org.leotalleceven.bibliotheque.models.utilisateur.Particulier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParticulierDAO extends DAO<Particulier> {

    private static ParticulierDAO instanceParticulierDAO;

    @Override
    public boolean create(@NotNull Particulier aObj) {
        try {
            String sql = "INSERT INTO Utilisateur VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, aObj.getId());
            stmt.setString(2, aObj.getNom());
            stmt.setString(3, aObj.getPrenom());
            stmt.setString(4, aObj.getAdresse());
            stmt.setString(5, aObj.getCodeVille());
            stmt.setInt(6, aObj.getCaution());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Particulier aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Particulier aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Particulier find(int aId) {
        throw new UnsupportedOperationException();
    }

    public static ParticulierDAO getInstance() {
        if (null == instanceParticulierDAO) { // Premier appel
            instanceParticulierDAO = new ParticulierDAO();
        }
        return instanceParticulierDAO;

    }

}
