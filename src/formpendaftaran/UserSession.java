/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formpendaftaran;

/**
 *
 * @author HP
 */
public class UserSession {
    private static String userId;

    public static void setUserId(String userId) {
        UserSession.userId = userId;
    }

    public static String getUserId() {
        return userId;
    }
}

