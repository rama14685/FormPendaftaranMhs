/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formpendaftaran;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class Fix extends javax.swing.JFrame {
    
    public Fix() {
        initComponents();
        loadData();
    }

    private void loadData() {
        String userId = UserSession.getUserId();
        try {
            konekDb rama = new konekDb();
            Connection con = rama.Buka();

            String query = "SELECT f.nodaftar, f.full_name, f.tanggal, f.alamat, f.email, f.nohp, p.jalur, p.prodi, p.biaya " +
                           "FROM formdaftar f " +
                           "INNER JOIN prodi p ON f.nodaftar = p.nodaftar " +
                           "WHERE f.id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nopendaftaran.setText(rs.getString("nodaftar"));
                nopendaftaran.setEditable(false); 
                nama.setText(rs.getString("full_name"));
                tanggal.setText(rs.getString("tanggal"));
                alamat.setText(rs.getString("alamat"));
                email.setText(rs.getString("email"));
                email.setEditable(false); 
                nohp.setText(rs.getString("nohp"));
                jalurdaftar.setText(rs.getString("jalur"));
                jalurdaftar.setEditable(false); 
                prodi.setText(rs.getString("prodi"));
                prodi.setEditable(false); 
                int biaya = rs.getInt("biaya");
                String formattedBiaya = "Rp " + String.format("%,d", biaya);
                totalbiaya.setText(formattedBiaya);
                totalbiaya.setEditable(false);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void hapusDataProdi() {
        try {
            konekDb rama = new konekDb();
            Connection con = rama.Buka();
            con.setAutoCommit(false); // Mulai transaksi

            String userId = UserSession.getUserId(); // Mendapatkan userId dari UserSession

            // Hapus dari tabel prodi
            String deleteProdiQuery = "DELETE p FROM prodi p\n "
                                        + "INNER JOIN formdaftar f ON p.nodaftar = f.nodaftar\n "
                                        + "WHERE f.id = ?";
            PreparedStatement prodiStmt = con.prepareStatement(deleteProdiQuery);
            prodiStmt.setString(1, userId);
            int prodiRowsDeleted = prodiStmt.executeUpdate();
            prodiStmt.close();

            con.commit(); // Komit transaksi

            JOptionPane.showMessageDialog(null, "Data dari prodi untuk user dengan ID " + userId + " berhasil dihapus.\nJumlah baris yang dihapus: " + prodiRowsDeleted);

            con.close(); // Tutup koneksi setelah selesai

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void hapusDataFormDaftar() {
        try {
            konekDb rama = new konekDb();
            Connection con = rama.Buka();
            con.setAutoCommit(false); // Mulai transaksi

            String userId = UserSession.getUserId(); // Mendapatkan userId dari UserSession

            // Hapus dari tabel formdaftar
            String deleteFormDaftarQuery = "DELETE FROM formdaftar WHERE id = ?";
            PreparedStatement formDaftarStmt = con.prepareStatement(deleteFormDaftarQuery);
            formDaftarStmt.setString(1, userId);
            int formDaftarRowsDeleted = formDaftarStmt.executeUpdate();
            formDaftarStmt.close();

            con.commit(); // Komit transaksi

            JOptionPane.showMessageDialog(null, "Data user berhasil dihapus dari formdaftar.\nRows deleted: " + formDaftarRowsDeleted);

            con.close(); // Tutup koneksi setelah selesai

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        hapus = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        nopendaftaran = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        tanggal = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        nohp = new javax.swing.JTextField();
        jalurdaftar = new javax.swing.JTextField();
        prodi = new javax.swing.JTextField();
        totalbiaya = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("EDIT DATA PENDAFTARAN");

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        nopendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nopendaftaranActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Lengkap");

        jLabel3.setText("Alamat");

        jLabel4.setText("Tanggal Lahir");

        jLabel5.setText("Email");

        jLabel6.setText("No. HP");

        jLabel7.setText("Jalur Pendaftaran");

        jLabel8.setText("Program Studi");

        jLabel9.setText("No. Daftar");

        jLabel10.setText("Biaya");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(28, 28, 28))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(37, 37, 37)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGap(54, 54, 54)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nohp, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nopendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prodi, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jalurdaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalbiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(batal))))
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nopendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jalurdaftar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(prodi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(totalbiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan)
                    .addComponent(hapus)
                    .addComponent(batal))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nopendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nopendaftaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nopendaftaranActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        String userId = UserSession.getUserId();
        try {
            konekDb rama = new konekDb();
            Connection con = rama.Buka();

            String updateFormDaftarQuery = "UPDATE formdaftar SET full_name = ?, tanggal = ?, alamat = ?, email = ?, nohp = ? WHERE id = ?";
            PreparedStatement updateFormDaftarStmt = con.prepareStatement(updateFormDaftarQuery);
            updateFormDaftarStmt.setString(1, nama.getText());
            updateFormDaftarStmt.setString(2, tanggal.getText());
            updateFormDaftarStmt.setString(3, alamat.getText());
            updateFormDaftarStmt.setString(4, email.getText());
            updateFormDaftarStmt.setString(5, nohp.getText());
            updateFormDaftarStmt.setString(6, userId);
            updateFormDaftarStmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");

            updateFormDaftarStmt.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        int hapus = JOptionPane.showOptionDialog(this,
                "Yakin ingin menghapus data?",
                "Data Berhasil Terhapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(hapus==JOptionPane.YES_OPTION){
            hapusDataProdi();
            hapusDataFormDaftar();
            new Login().setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_hapusActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        this.dispose();
        EditCetak editcetak = new EditCetak();
        editcetak.setVisible(true);
    }//GEN-LAST:event_batalActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // Hapus baris ini jika tidak ingin menentukan nodaftar secara manual
//        // new Fix("D001").setVisible(true);
//
//        // Menggunakan nodaftar dari args jika disediakan dari luar (misalnya command line argument)
//        if (args.length > 0) {
//            new Fix(args[0]).setVisible(true);
//        } else {
//            // Jika tidak ada args, Anda bisa menyesuaikan cara mendapatkan nodaftar, misalnya dari FormDaftar atau Prodi sebelumnya
//            // Contoh: new Fix(nodaftarDariFormDaftar).setVisible(true);
//            JOptionPane.showMessageDialog(null, "Tidak ada nodaftar yang diberikan.");
//        }
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private javax.swing.JButton batal;
    private javax.swing.JTextField email;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jalurdaftar;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nohp;
    private javax.swing.JTextField nopendaftaran;
    private javax.swing.JTextField prodi;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField tanggal;
    private javax.swing.JTextField totalbiaya;
    // End of variables declaration//GEN-END:variables
}
