package org.leotalleceven.bibliotheque.dao.utilisateur;

import org.jetbrains.annotations.NotNull;
import org.leotalleceven.bibliotheque.dao.DAO;
import org.leotalleceven.bibliotheque.models.utilisateur.Enseignant;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnseignantDAO extends DAO<Enseignant> {

    private static EnseignantDAO instanceEnseignantDAO;

    @Override
    public boolean create(@NotNull Enseignant aObj) {
        try {
            String sql = "INSERT INTO Enseignant VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, aObj.getId());
            stmt.setString(2, aObj.getNom());
            stmt.setString(3, aObj.getPrenom());
            stmt.setString(4, aObj.getAdresse());
            stmt.setString(5, aObj.getCodeVille());
            stmt.setInt(6, aObj.getCaution());
            stmt.setInt(6, aObj.getCodeDepartement());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Enseignant aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Enseignant aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Enseignant find(int aId) {
        throw new UnsupportedOperationException();
    }

    public static EnseignantDAO getInstance() {
        if (null == instanceEnseignantDAO) { // Premier appel
            instanceEnseignantDAO = new EnseignantDAO();
        }
        return instanceEnseignantDAO;

    }

}
