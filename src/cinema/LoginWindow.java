/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Kuljeet
 */
public class LoginWindow extends javax.swing.JFrame 
{
    /**
     * Creates new form LoginWindow
     */
    public LoginWindow() 
    {
        initComponents();
        javaVersionLabel.setText(System.getProperty("java.version"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        loginButton = new javax.swing.JButton();
        cancelButtoon = new javax.swing.JButton();
        userNameTextBox = new javax.swing.JTextField();
        passwordTextBox = new javax.swing.JPasswordField();
        welcomeLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        javaVersionLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(400, 300));
        setMinimumSize(new java.awt.Dimension(450, 300));
        setPreferredSize(new java.awt.Dimension(430, 300));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginButton.setText("Login");
        loginButton.setMaximumSize(new java.awt.Dimension(100, 24));
        loginButton.setMinimumSize(new java.awt.Dimension(100, 24));
        loginButton.setPreferredSize(new java.awt.Dimension(100, 24));
        loginButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                loginButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 200, 100, 24));

        cancelButtoon.setText("Cancel");
        cancelButtoon.setMaximumSize(new java.awt.Dimension(100, 24));
        cancelButtoon.setMinimumSize(new java.awt.Dimension(200, 24));
        cancelButtoon.setPreferredSize(new java.awt.Dimension(100, 24));
        cancelButtoon.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtoonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButtoon, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 200, 100, 24));

        userNameTextBox.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        userNameTextBox.setText("Kuljeet");
        userNameTextBox.setToolTipText("Please enter a username");
        userNameTextBox.setMaximumSize(new java.awt.Dimension(200, 24));
        userNameTextBox.setMinimumSize(new java.awt.Dimension(200, 24));
        userNameTextBox.setPreferredSize(new java.awt.Dimension(200, 24));
        userNameTextBox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                userNameTextBoxActionPerformed(evt);
            }
        });
        getContentPane().add(userNameTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 100, 200, 24));

        passwordTextBox.setText("Chhabra");
        passwordTextBox.setToolTipText("Please enter a password.");
        passwordTextBox.setMaximumSize(new java.awt.Dimension(200, 24));
        passwordTextBox.setMinimumSize(new java.awt.Dimension(200, 24));
        passwordTextBox.setPreferredSize(new java.awt.Dimension(200, 24));
        getContentPane().add(passwordTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 140, 200, 24));

        welcomeLabel.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Welcome to Love Movies Ticket Booking System");
        welcomeLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        welcomeLabel.setMaximumSize(new java.awt.Dimension(300, 24));
        welcomeLabel.setMinimumSize(new java.awt.Dimension(300, 24));
        welcomeLabel.setPreferredSize(new java.awt.Dimension(300, 24));
        getContentPane().add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 40, 300, 24));

        userNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userNameLabel.setText("Username");
        userNameLabel.setToolTipText("");
        userNameLabel.setMaximumSize(new java.awt.Dimension(90, 24));
        userNameLabel.setMinimumSize(new java.awt.Dimension(90, 24));
        userNameLabel.setPreferredSize(new java.awt.Dimension(90, 20));
        getContentPane().add(userNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 100, 90, 24));

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordLabel.setText("Password");
        passwordLabel.setMaximumSize(new java.awt.Dimension(90, 24));
        passwordLabel.setMinimumSize(new java.awt.Dimension(90, 24));
        passwordLabel.setName(""); // NOI18N
        passwordLabel.setPreferredSize(new java.awt.Dimension(90, 24));
        getContentPane().add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 140, -1, -1));
        passwordLabel.getAccessibleContext().setAccessibleName("Pasword");

        javaVersionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        javaVersionLabel.setMaximumSize(new java.awt.Dimension(40, 16));
        javaVersionLabel.setMinimumSize(new java.awt.Dimension(40, 16));
        javaVersionLabel.setPreferredSize(new java.awt.Dimension(40, 16));
        getContentPane().add(javaVersionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtoonActionPerformed
        frameCloseEvent();
    }//GEN-LAST:event_cancelButtoonActionPerformed

    private void userNameTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameTextBoxActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        try
        {
            String username = userNameTextBox.getText();
            char[] password = passwordTextBox.getPassword();
            if( CinemaProperties.GetCinemaProperties().VerifyUsername(username) &&
                    CinemaProperties.GetCinemaProperties().VerifyPassword(new String(password)))
            {
                this.setVisible(false);
                MainWindow mw = new MainWindow();
                mw.pack();
                mw.setLocationRelativeTo(null);
                mw.setVisible(true);
            }
            else
            {
                //usernmae of password wrong dialog.
                JOptionPane.showMessageDialog(this, "Username or Password Incorrect" , "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        frameCloseEvent(); // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }
    
    private void frameCloseEvent()
    {
        int result = JOptionPane.showConfirmDialog(this, 
                                                   "Are you sure to close the application", 
                                                   "Close Application", 
                                                   JOptionPane.YES_NO_OPTION, 
                                                   JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButtoon;
    private javax.swing.JLabel javaVersionLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextBox;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTextBox;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}