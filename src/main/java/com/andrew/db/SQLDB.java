package com.andrew.db;

import java.sql.Connection;

public interface SQLDB {
    public Connection getConnection();
    public void closeConnection();
    public void createTable(String name, String... fields);
    public void setPath(String path);
}
