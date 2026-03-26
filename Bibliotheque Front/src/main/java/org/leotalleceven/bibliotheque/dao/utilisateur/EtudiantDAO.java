package org.leotalleceven.bibliotheque.dao.utilisateur;

import org.jetbrains.annotations.NotNull;
import org.leotalleceven.bibliotheque.dao.DAO;
import org.leotalleceven.bibliotheque.models.utilisateur.Etudiant;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EtudiantDAO extends DAO<Etudiant> {

    private static EtudiantDAO instanceEtudiantDAO;

    @Override
    public boolean create(@NotNull Etudiant aObj) {
        try {
            String sql = "INSERT INTO Etudiant VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, aObj.getId());
            stmt.setString(2, aObj.getNom());
            stmt.setString(3, aObj.getPrenom());
            stmt.setString(4, aObj.getAdresse());
            stmt.setString(5, aObj.getCodeVille());
            stmt.setInt(6, aObj.getCaution());
            stmt.setInt(6, aObj.getAnneeUniversitaire());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Etudiant aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Etudiant aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Etudiant find(int aId) {
        throw new UnsupportedOperationException();
    }

    public static EtudiantDAO getInstance() {
        if (null == instanceEtudiantDAO) { // Premier appel
            instanceEtudiantDAO = new EtudiantDAO();
        }
        return instanceEtudiantDAO;

    }

}
