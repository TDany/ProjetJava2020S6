/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author danytadrous
 */
public class InterfaceReferent extends JFrame {
    
    private String m_name;
    
    public InterfaceReferent(String name){
        m_name = name;
        
        this.setTitle("INTERFACE PLANNING REFERENT");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(12, 3));
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

        JLabel nameUser = new JLabel();
        nameUser.setText("Hi " + m_name);
        nameUser.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        this.add(nameUser);
        
    }

    
}

