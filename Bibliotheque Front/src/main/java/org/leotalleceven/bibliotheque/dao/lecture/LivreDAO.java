package org.leotalleceven.bibliotheque.dao.lecture;

import org.jetbrains.annotations.NotNull;
import org.leotalleceven.bibliotheque.dao.DAO;
import org.leotalleceven.bibliotheque.models.lecture.Livre;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivreDAO extends DAO<Livre> {

    private static LivreDAO instanceLivreDAO;

    @Override
    public boolean create(@NotNull Livre aObj) {
        try {
            String sql = "INSERT INTO Livre VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, aObj.getTitre());
            stmt.setInt(2, aObj.getCaution());
            stmt.setString(3, aObj.getCodeBarre());
            stmt.setString(4, aObj.getiSBN());
            stmt.setString(5, aObj.getAuteur());
            stmt.setString(6, aObj.getGenre());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Livre aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Livre aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Livre find(int aId) {
        throw new UnsupportedOperationException();
    }

    public static LivreDAO getInstance() {
        if (null == instanceLivreDAO) { // Premier appel
            instanceLivreDAO = new LivreDAO();
        }
        return instanceLivreDAO;

    }

}
