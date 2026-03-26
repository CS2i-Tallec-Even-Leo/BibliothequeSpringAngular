package org.leotalleceven.bibliotheque.dao.lecture;

import org.jetbrains.annotations.NotNull;
import org.leotalleceven.bibliotheque.dao.DAO;
import org.leotalleceven.bibliotheque.models.lecture.Stockage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockageDAO extends DAO<Stockage> {

    private static StockageDAO instanceStockageDAO;

    @Override
    public boolean create(@NotNull Stockage aObj) {
        try {
            String sql = "INSERT INTO Stockage VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, aObj.getNombreTotal());
            stmt.setInt(2, aObj.getRestant());
            stmt.setInt(3, aObj.getNumeroTrave());
            stmt.setInt(4, aObj.getNumeroEtagere());
            stmt.setInt(5, aObj.getNiveau());
            stmt.setString(6, aObj.getCategorie());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Stockage aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Stockage aObj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stockage find(int aId) {
        throw new UnsupportedOperationException();
    }

    public static StockageDAO getInstance() {
        if (null == instanceStockageDAO) { // Premier appel
            instanceStockageDAO = new StockageDAO();
        }
        return instanceStockageDAO;

    }

}
