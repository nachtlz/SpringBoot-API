package com.serverintegrador.base.bd;

import java.sql.Connection;
import java.sql.DriverManager;

import com.serverintegrador.base.ArxiuLogs;

public class DBConnection {

    private Connection con;

    public DBConnection() {
        con = null;
    }

    public void open() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"
                    + DBProperties.host + ":" + DBProperties.port
                    + "/" + DBProperties.db, DBProperties.user, DBProperties.pass);
            System.out.print(con);
        } catch (Exception ex) {
            ArxiuLogs.guardarExcepcio(ex);
        }
    }

    public void close() {
        try {
            con.close();
        } catch (Exception ex) {
            ArxiuLogs.guardarExcepcio(ex);
        }
    }

    public Connection getConection() {
        return con;
    }
}
