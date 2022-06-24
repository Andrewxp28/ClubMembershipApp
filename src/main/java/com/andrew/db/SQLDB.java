package com.andrew.db;

import java.sql.Connection;

/**
 * An interface to interact with an SQL database.
 */
public interface SQLDB {
    /**
     * Establishes or returns a Connection instance to a database.
     * @return Connection instance connected to database.
     */
    Connection getConnection();

    /**
     * Closes connection to database.
     */
    void closeConnection();

    /**
     * sets the relative path to the database file.
     * @param path relative path to database file.
     */
    void setPath(String path);

    /**
     * initialises the database with a connection, and creates the required tables for the database.
     * @param path path to database file.
     */
    void initialiseDb(String path);

    /**
     * executes a command that is formatted to SQL to the database.
     * @param command
     */
    void executeCommand(String command);
}
