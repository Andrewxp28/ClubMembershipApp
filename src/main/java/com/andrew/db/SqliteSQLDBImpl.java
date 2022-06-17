package com.andrew.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteSQLDBImpl implements SQLDB {
    private static SqliteSQLDBImpl db;
    private static final String DEFAULT_PATH_STARTER = "jdbc:sqlite:";
    private static Connection conn;
    private static String path;
    public static SqliteSQLDBImpl getInstance() {
        if (db == null) {
            db = new SqliteSQLDBImpl();
        }
        return db;
    }
    @Override
    public Connection getConnection() {
        // connect to the db based on path.
        try {
            // check if there is a connection and it's still valid
            if (conn != null && conn.isValid(1000)) {
                    return conn;
            }
            // if not valid or hasn't been connected, then we go connect to database.
            conn = DriverManager.getConnection(path);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //end of function.
        return conn;
    }

    @Override
    public void closeConnection() {
       try {
           if (conn.isClosed()) {
               System.out.println("Connection to SQLite has already been closed.");
               return;
           }
           conn.close();
           System.out.println("Connection to SQLite has been closed.");
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
    }

    @Override
    public void createTable(String name, String... fields) {
        // do I need to check if field is null? or length == 0?
        // must have one or more fields/columns
        if (fields.length == 0) return;
        // making sql statement to create a table
        StringBuilder statement = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        //statement.append("main.");
        statement.append(name);
        statement.append("(");
        // adding field specifications of table
        for (int i=0; i<fields.length; i++) {
            statement.append(fields[i]);
            if (i < fields.length - 1) {
                // not the last item so we add a comma
                statement.append(", ");
            }

        }
        // close
        statement.append(");");
        // prepping statement and executing statement.
        try (PreparedStatement pstmt = conn.prepareStatement(statement.toString())){
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void setPath(String path) {
        //path = fixFileSeparators(path);
        SqliteSQLDBImpl.path = DEFAULT_PATH_STARTER + path;
    }
    public Connection getConn() {
        return conn;
    }

}
