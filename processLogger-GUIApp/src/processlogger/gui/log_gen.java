package processlogger.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class log_gen extends javax.swing.JFrame {

    public log_gen() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pid12 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jLabel6.setText("© CSPIT-IT");

        jLabel7.setText("© CSPIT-IT");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Process Logger");
        setBounds(new java.awt.Rectangle(400, 70, 5, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel1.setText("Process Id:");

        pid12.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pid12ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel4.setText("Set Delay in ms (milli seconds):");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel5.setText("Maximum File Size in MB:");

        jButton1.setText("Start Logging");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop Logging");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("© CSPIT-IT");

        jButton4.setText("Graph");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Script MT Bold", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("PROCESS LOGGER");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Enter Server Name:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
                .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addComponent(pid12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addComponent(jTextField1))
                .addGap(0, 0, Short.MAX_VALUE))))
                .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pid12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }

    private void pid12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pid12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pid12ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String hostname = jTextField1.getText();
        if (flag != 0) {
            JOptionPane.showMessageDialog(null, "Please stop logging");
        } else {
            try {

                clientsocket = new DatagramSocket();
            } catch (SocketException ex) {
                Logger.getLogger(log_gen.class.getName()).log(Level.SEVERE, null, ex);
            }

            dis = new BufferedReader(new InputStreamReader(System.in));
            try {
                ia = InetAddress.getByName(hostname);
            } catch (UnknownHostException ex) {
                System.out.println("in exit " + ex);
            }
            System.out.println("Client is Running... Type 'STOP' to Quit");

            String str;
            str = "STOP";

            buf3 = str.getBytes();
            try {
                clientsocket.send(new DatagramPacket(buf3, str.length(), ia, sport));

            } catch (IOException ex) {
                Logger.getLogger(log_gen.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                clientsocket.close();
            }
            System.out.println(str);
            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (flag != 1) {
            JOptionPane.showMessageDialog(null, "Please start logging");
        } else {
            Chooser c = new Chooser();
            c.setVisible(true);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (flag == 0) {
            flag = 1;
            boolean exflag;
            String pid1 = pid12.getText();
            String interval = jTextField4.getText();
            String fsize = jTextField5.getText();
            String hostname = jTextField1.getText();
            System.out.println("hostname" + hostname);

            if (pid1.contentEquals("") || interval.contentEquals("") || fsize.contentEquals("") || hostname.contentEquals("")) {
                JOptionPane.showMessageDialog(null, "No Fields Should be Empty");
                flag = 0;
                return;
            }

            try {
                clientsocket = new DatagramSocket();
            } catch (SocketException ex) {
                System.out.println(ex);
            }

            try {
                ia = InetAddress.getByName(hostname);
                System.out.println(hostname);
                exflag = true;
            } catch (UnknownHostException ex) {
                System.out.println(ex);
                flag = 0;
                clientsocket.close();
                JOptionPane.showMessageDialog(null, "Check PC Name or Network Connection");
                exflag = false;
            }

            if (exflag) {
                System.out.println("Client is Running... Type 'STOP' to Quit");
                String str;

                System.out.println("uf declaration");
                str = pid1 + "=;" + interval + "=;" + fsize;
                System.out.println(str);
                try {
                    buf = str.getBytes();
                    clientsocket.send(new DatagramPacket(buf, str.length(), ia, sport));
                    clientsocket.disconnect();
                    clientsocket.close();

                    System.out.println("buf");

                    try {
                        clientsocket = new DatagramSocket(cport);
                    } catch (SocketException ex) {
                        System.out.println(ex);
                        ex.printStackTrace();
                    }
                    try {

                        clientsocket.setSoTimeout(3000);
                        clientsocket.receive(dp);

                        String incorrectpid = new String(dp.getData(), 0, dp.getLength());

                        clientsocket.close();

                        if ("invalid".equals(incorrectpid)) {
                            JOptionPane.showMessageDialog(null, "Invalid process id");
                            flag = 0;
                        }
                    } catch (SocketTimeoutException ste) {
                        System.out.println(ste);
                        flag = 0;
                        clientsocket.close();
                        JOptionPane.showMessageDialog(null, "Server Not Started");
                    } catch (IOException ioe) {
                        System.out.println(ioe);
                        clientsocket.close();
                    }
                } catch (IOException ioe) {
                    System.out.println("in ioe " + ioe);
                    clientsocket.close();
                }
            }
            System.out.println("after if");
        }else{
            JOptionPane.showMessageDialog(null, "start already pressed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String hostname = jTextField1.getText();
        if (flag != 1) {
            JOptionPane.showMessageDialog(null, "Please start logging");
        } else {
            System.out.print("h");
            try {
                clientsocket = new DatagramSocket();
            } catch (SocketException ex) {
                System.out.println(ex);
            }

            dis = new BufferedReader(new InputStreamReader(System.in));
            try {
                ia = InetAddress.getByName(hostname);
            } catch (UnknownHostException ex) {
                System.out.println(ex);
            }
            System.out.println("Client is Running... Type 'STOP' to Quit");

            String str;
            str = "stop";

            flag = 0;
            try {
                buf4 = str.getBytes();
                clientsocket.send(new DatagramPacket(buf4, str.length(), ia, sport));
            } catch (IOException ex) {
                System.out.println(ex);
            } finally {
                clientsocket.close();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(log_gen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(log_gen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(log_gen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(log_gen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new log_gen().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField pid12;
    // End of variables declaration//GEN-END:variables
    private static DatagramSocket clientsocket;
    static DatagramSocket clientsocket2;
    private static BufferedReader dis;
    private static InetAddress ia;
    private static byte buf[] = new byte[1024];
    private static byte buf1[] = new byte[1024];
    private static byte buf3[] = new byte[1024];
    private static byte buf4[] = new byte[1024];
    private static DatagramPacket dp = new DatagramPacket(buf1, buf1.length);
    private static int cport = 6002, sport = 6003;
    private int flag = 0;
}
