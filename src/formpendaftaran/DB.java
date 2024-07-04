/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formpendaftaran;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
class DB {
     private static Connection connection;

    static {
        try {
            konekDb db = new konekDb();
            connection = db.Buka();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet read(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }
    
}
