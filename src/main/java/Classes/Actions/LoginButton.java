/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JOptionPane;


/**
 *
 * @author alanvitalp
 */
public class LoginButton implements ActionListener {
   
    private final JTextField userText;

    public LoginButton (JTextField login) {
        this.userText = login;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String user = userText.getText();
        
        JOptionPane.showMessageDialog(null, user);
    } 
}
