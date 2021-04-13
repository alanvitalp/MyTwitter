/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Components;

import Classes.Actions.LoginButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login {

    public Login () {
        
        JFrame login = new JFrame();
        login.setTitle("Janela de Login");
        login.setSize(600, 400);
        login.setLocation(500, 300);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        JLabel loginLabel = new JLabel("Faça seu login:"); 
        JTextField loginText = new JTextField(10);
        JButton loginButton = new JButton("Entrar");
        
        JPanel loginPanel = new JPanel();
        
        loginPanel.add(loginLabel);
        loginPanel.add(loginText);  
        loginPanel.add(loginButton);
        
        login.add(loginPanel);
        login.setVisible(true);
        
        
        LoginButton loginAction = new LoginButton(loginText);
        loginButton.addActionListener(loginAction);
    }
}


