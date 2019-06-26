package ru.rit.personsearch.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlTransaction<T> {
    T execute(Connection connection) throws SQLException;
}
