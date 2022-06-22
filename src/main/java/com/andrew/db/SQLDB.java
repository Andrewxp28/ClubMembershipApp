package com.andrew.db;

import java.sql.Connection;
import java.util.Map;

public interface SQLDB {
    public Connection getConnection();
    public void closeConnection();
    //public void createTable(String name, Map<String, Map.Entry<String, String>> foreignKeys, String... fields);
    public void setPath(String path);
    public void initialiseDb(String path);
    public void executeCommand(String command);
}
