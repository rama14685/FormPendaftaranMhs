/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formpendaftaran;

import java.sql.*;
/**
 *
 * @author HP
 */
public class konekDb {
    String driverdbms = "com.mysql.cj.jdbc.Driver";
    String database   = "jdbc:mysql://localhost/datadb";
    String user       = "root";
    String password   = "";
    
    public konekDb()
    {
    }
    
    public Connection Buka() throws SQLException
    {
        Connection condbms = null;
        try
        {
            Class.forName(driverdbms);
            condbms = DriverManager.getConnection(database, user, password);
            
            return condbms;
        }
        catch (SQLException se)
        {
            System.out.println("Ada Kesalahan Pada SQL !");
            return null;
        }
        catch (Exception ex)
        {
            System.out.println("Koneksi Database Tidak Berhasil !");
            return null;
        }
    }
}
