/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formpendaftaran;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HP
 */
public class Prodi extends javax.swing.JFrame {

    /**
     * Creates new form Prodi
     */
    public Prodi() {
        initComponents();
        hitungBiaya();
        
        jalur.addActionListener(e -> hitungBiaya());
    }

    private void input_data() {
        try {
            konekDb rama = new konekDb();
            Connection con = rama.Buka();    

            // Ambil id user yang sedang login dari UserSession
            String idUser = UserSession.getUserId();

            // Ambil nodaftar dari formdaftar berdasarkan id user
            String nodaftarUser = getNodaftarUser(idUser);

            // Pastikan nodaftarUser tidak kosong atau null
            if (nodaftarUser == null || nodaftarUser.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID User tidak ditemukan. Pendaftaran tidak dapat dilanjutkan.");
                this.dispose();
                return;
            }

            // Hitung biaya berdasarkan jalur pendaftaran yang dipilih
            hitungBiaya();

            // Pastikan totalBiaya sudah terisi
            if (totalBiaya.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Biaya belum dihitung. Pastikan jalur pendaftaran dipilih.");
                return;
            }

            // Menghilangkan simbol "Rp" dan koma pada biaya dan mengubahnya menjadi int
            String biayaStr = totalBiaya.getText().replace("Rp ", "").replace(",", "").trim();
            int biaya = Integer.parseInt(biayaStr);

            // Query untuk memasukkan data ke tabel prodi
            String strsql = "INSERT INTO prodi (nodaftar, jalur, prodi, biaya) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(strsql);
            pst.setString(1, nodaftarUser); // Gunakan nodaftar dari formdaftar
            pst.setString(2, (String) jalur.getSelectedItem());
            pst.setString(3, (String) prodi.getSelectedItem()); 
            pst.setInt(4, biaya);

            // Eksekusi perintah INSERT
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Data Diri Berhasil Disimpan");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data");
            }

            // Tutup PreparedStatement
            pst.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format biaya tidak valid. Pastikan biaya sudah dihitung dengan benar.");
        }
    }

    private String getNodaftarUser(String idUser) throws SQLException {
        String nodaftarUser = "";

        konekDb rama = new konekDb();
        Connection con = rama.Buka();            

        String strsql = "SELECT nodaftar FROM formdaftar WHERE id = ?";
        PreparedStatement pst = con.prepareStatement(strsql);
        pst.setString(1, idUser);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            nodaftarUser = rs.getString("nodaftar");
        }

        rs.close();
        pst.close();
        con.close(); // Tambahkan penutupan koneksi di sini

        return nodaftarUser;
    }

    private void hitungBiaya() {
        String selectedJalur = (String) jalur.getSelectedItem();

        // Variabel untuk menyimpan biaya
        int biaya = 0;

        // Tentukan biaya berdasarkan jalur pendaftaran
        if (selectedJalur != null) {
            switch (selectedJalur.trim()) { // Menghapus spasi kosong yang mungkin ada
                case "PMDK":
                    biaya = 18000000; // Biaya untuk jalur PMDK
                    break;
                case "Reguler":
                    biaya = 26000000; // Biaya untuk jalur Reguler
                    break;
                case "KIPK":
                    biaya = 0; // Biaya untuk jalur KIP
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Jalur pendaftaran tidak valid. Silakan pilih jalur pendaftaran yang sesuai.");
                    return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silakan pilih jalur pendaftaran terlebih dahulu.");
            return;
        }

        // Set nilai biaya ke text field totalBiaya dengan format Rp
        totalBiaya.setText(String.format("Rp %,d", biaya));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jalur = new javax.swing.JComboBox<>();
        prodi = new javax.swing.JComboBox<>();
        totalBiaya = new javax.swing.JTextField();
        daftar = new javax.swing.JButton();
        batal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("FORM PENDAFTARAN");

        jLabel2.setText("Jalur Pendaftaran");

        jLabel3.setText("Program Studi");

        jLabel4.setText("Biaya Pendaftaran");

        jalur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PMDK", "Reguler", "KIPK" }));
        jalur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jalurMouseClicked(evt);
            }
        });

        prodi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teknik Informatika - S1", "Sistem Informasi - S1", "Desain Komputer Visual - S1", "Ilmu Komunikasi - S1" }));

        daftar.setBackground(new java.awt.Color(51, 0, 255));
        daftar.setForeground(new java.awt.Color(255, 255, 255));
        daftar.setText("Daftar");
        daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarActionPerformed(evt);
            }
        });

        batal.setBackground(new java.awt.Color(255, 51, 0));
        batal.setForeground(new java.awt.Color(255, 255, 255));
        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(99, 99, 99)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(prodi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalBiaya)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jalur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(batal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(daftar)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jalur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(prodi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daftar)
                    .addComponent(batal))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_batalActionPerformed

    private void daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarActionPerformed
        // TODO add your handling code here:
        int simpan = JOptionPane.showOptionDialog(this,
                "Apakah Data Sudah Sesuai? Simpan?",
                "Simpan Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(simpan==JOptionPane.YES_OPTION){
            input_data();
            new EditCetak().setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_daftarActionPerformed

    private void jalurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jalurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jalurMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Prodi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prodi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prodi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prodi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prodi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JButton daftar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> jalur;
    private javax.swing.JComboBox<String> prodi;
    private javax.swing.JTextField totalBiaya;
    // End of variables declaration//GEN-END:variables
}
