package com.andrew.db;

import org.sqlite.SQLiteConfig;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     *
     *
     */
    public Connection getConnection() {
        // connect to the db based on path.
        try {
            // check if there is a connection and it's still valid
            if (conn != null && conn.isValid(1000)) {
                    return conn;
            }
            // if not valid or hasn't been connected, then we go connect to database.
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(path, config.toProperties());
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

    //@Override
    public void createTable(String name, Map<String, List<String>> foreignKeys, String... fields) {
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
        // add foreign keys if any
        if (foreignKeys != null && !foreignKeys.isEmpty()) {
            statement.append(", ");
            for (Map.Entry<String, List<String>> fKey: foreignKeys.entrySet()) {
                //statement
                statement.append("FOREIGN KEY")
                        .append(" (")
                        .append(fKey.getKey())
                        .append(")")
                        .append(" REFERENCES ")
                        .append(fKey.getValue().get(0))
                        .append(" (")
                        .append(fKey.getValue().get(1))
                        .append(") ")
                        .append("ON UPDATE CASCADE ON DELETE CASCADE")
                        .append(",");
            }
            // removes last comma
            statement.deleteCharAt(statement.length()-1);

        }
        // close
        statement.append(");");
        System.out.println(statement);
        // prepping statement and executing statement.
        try (PreparedStatement pstmt = conn.prepareStatement(statement.toString())){
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createMembersTable() {
        // making sql statement to create a table for members
        String stmt = "CREATE TABLE IF NOT EXISTS members (\n" +
                "    email     TEXT PRIMARY KEY\n" +
                "                   NOT NULL\n" +
                "                   UNIQUE,\n" +
                "    first_name TEXT NOT NULL,\n" +
                "    last_name  TEXT NOT NULL,\n" +
                "    phone     TEXT NOT NULL\n" +
                ");";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
    public void createMembershipTable() {
        // making sql statement to create a table for members
        String stmt = "CREATE TABLE IF NOT EXISTS memberships (\n" +
                "    membership_id INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                          UNIQUE\n" +
                "                          NOT NULL,\n" +
                "    start_date    DATE    NOT NULL,\n" +
                "    end_date      DATE    NOT NULL,\n" +
                "    type          TEXT    NOT NULL,\n" +
                "    member_email  TEXT    NOT NULL\n" +
                "                          REFERENCES members (email) ON DELETE CASCADE\n" +
                "                                                     ON UPDATE CASCADE\n" +
                ");";
        try {
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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

    @Override
    /**  Makes a sqlite Database file if one does already exist. configures the database.
     *
     */
    public void initialiseDb(String path) {
        setPath(path);
        conn = getConnection();
        // creates a table in db for members
        createMembersTable();
        // creates a table in db for memberships
        createMembershipTable();
    }

    @Override
    public void executeCommand(String command) {
        // prepping statement and executing statement.
        try (PreparedStatement pstmt = conn.prepareStatement(command)){
            pstmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
