package org.leotalleceven.bibliotheque.dao.lecture;

import org.jetbrains.annotations.NotNull;
import org.leotalleceven.bibliotheque.dao.DAO;
import org.leotalleceven.bibliotheque.models.lecture.Revue;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RevueDAO extends DAO<Revue> {

    private static RevueDAO instanceRevueDAO;

    @Override
    public boolean create(@NotNull Revue aObj) {
        try {
            String sql = "INSERT INTO Revue VALUES (?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, aObj.getTitre());
            stmt.setInt(2, aObj.getCaution());
            stmt.setString(3, aObj.getCodeBarre());
            stmt.setInt(4, aObj.getNumeroVolume());
            stmt.setDate(5, aObj.getDateParution());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Revue aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Revue aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Revue find(int aId) {
        throw new UnsupportedOperationException();
    }

    public static RevueDAO getInstance() {
        if (null == instanceRevueDAO) { // Premier appel
            instanceRevueDAO = new RevueDAO();
        }
        return instanceRevueDAO;

    }

}
