package org.leotalleceven.bibliotheque.dao;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class DAO<T> {

    protected Connection connect = null;

    public DAO() {
        try {
            connect = DBManager.getConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract boolean create(T aObj);

    public abstract boolean delete(T aObj);

    public abstract boolean update(T aObj);

    public abstract T find(int aId);
}
