/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * 
 * @author danytadrous
 */
public class InterfaceEtudiant extends JFrame {
    
    private String m_name;
    JComboBox choixSemaine = new JComboBox();
    String[] semaine = {"1", "2", "3", "4", "5"};
    
    public InterfaceEtudiant(String name){
        m_name = name;
        
        this.setTitle("INTERFACE PLANNING ETUDIANT");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(16,8));
        
        JLabel nameUser = new JLabel();
        nameUser.setText("Hi " + m_name);
        nameUser.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        this.add(nameUser);
        
        JLabel monEDT = new JLabel();
        monEDT.setText("Mon emploi du temps");
        this.add(monEDT);
        
        for (int i = 0; i < 6; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel PhrasePourChoixSemaine = new JLabel();
        PhrasePourChoixSemaine.setText("Quelle semaine ?");
        this.add(PhrasePourChoixSemaine);
        
        
        JComboBox choixSemaine = new JComboBox(semaine);
        this.add(choixSemaine);
        
        for (int i = 0; i < 7; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonEDT = new JButton("Afficher mon EDT");
        this.add(ButtonEDT);
        ButtonEDT.addActionListener(new ButtonEDTListener());
        
        for (int i = 0; i < 8; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel leLundi = new JLabel();
        leLundi.setText("Lundi");
        leLundi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leLundi.setOpaque(true);
        leLundi.setBackground(Color.white);
        this.add(leLundi);
        
        JLabel leMardi = new JLabel();
        leMardi.setText("Mardi");
        leMardi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leMardi.setOpaque(true);
        leMardi.setBackground(Color.white);
        this.add(leMardi);
        
        JLabel leMercredi = new JLabel();
        leMercredi.setText("Mercredi");
        leMercredi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leMercredi.setOpaque(true);
        leMercredi.setBackground(Color.white);
        this.add(leMercredi);
        
        JLabel leJeudi = new JLabel();
        leJeudi.setText("Jeudi");
        leJeudi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leJeudi.setOpaque(true);
        leJeudi.setBackground(Color.white);
        this.add(leJeudi);
        
        JLabel leVendredi = new JLabel();
        leVendredi.setText("Vendredi");
        leVendredi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leVendredi.setOpaque(true);
        leVendredi.setBackground(Color.white);
        this.add(leVendredi);
        
        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure89 = new JLabel();
        heure89.setText("8H - 9H");
        heure89.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure89.setOpaque(true);
        heure89.setBackground(Color.white);
        this.add(heure89);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours89 = new JPanel();
            lesCours89.setBackground(Color.white);
            this.add(lesCours89);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure910 = new JLabel();
        heure910.setText("9H - 10H");
        heure910.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure910.setOpaque(true);
        heure910.setBackground(Color.white);
        this.add(heure910);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours910 = new JPanel();
            lesCours910.setBackground(Color.white);
            this.add(lesCours910);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1011 = new JLabel();
        heure1011.setText("10H - 11H");
        heure1011.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1011.setOpaque(true);
        heure1011.setBackground(Color.white);
        this.add(heure1011);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1011 = new JPanel();
            lesCours1011.setBackground(Color.white);
            this.add(lesCours1011);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1112 = new JLabel();
        heure1112.setText("11H - 12H");
        heure1112.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1112.setOpaque(true);
        heure1112.setBackground(Color.white);
        this.add(heure1112);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1112 = new JPanel();
            lesCours1112.setBackground(Color.white);
            this.add(lesCours1112);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure1213 = new JLabel();
        heure1213.setText("12H - 13H");
        heure1213.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1213.setOpaque(true);
        heure1213.setBackground(Color.white);
        this.add(heure1213);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1213 = new JPanel();
            lesCours1213.setBackground(Color.white);
            this.add(lesCours1213);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure1314 = new JLabel();
        heure1314.setText("13H - 14H");
        heure1314.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1314.setOpaque(true);
        heure1314.setBackground(Color.white);
        this.add(heure1314);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1314 = new JPanel();
            lesCours1314.setBackground(Color.white);
            this.add(lesCours1314);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1415 = new JLabel();
        heure1415.setText("14H - 15H");
        heure1415.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1415.setOpaque(true);
        heure1415.setBackground(Color.white);
        this.add(heure1415);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1415 = new JPanel();
            lesCours1415.setBackground(Color.white);
            this.add(lesCours1415);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1516 = new JLabel();
        heure1516.setText("15H - 16H");
        heure1516.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1516.setOpaque(true);
        heure1516.setBackground(Color.white);
        this.add(heure1516);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1516 = new JPanel();
            lesCours1516.setBackground(Color.white);
            this.add(lesCours1516);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure1617 = new JLabel();
        heure1617.setText("16H - 17H");
        heure1617.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1617.setOpaque(true);
        heure1617.setBackground(Color.white);
        this.add(heure1617);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1617 = new JPanel();
            lesCours1617.setBackground(Color.white);
            this.add(lesCours1617);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1718 = new JLabel();
        heure1718.setText("17H - 18H");
        heure1718.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1718.setOpaque(true);
        heure1718.setBackground(Color.white);
        this.add(heure1718);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1718 = new JPanel();
            lesCours1718.setBackground(Color.white);
            this.add(lesCours1718);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1819 = new JLabel();
        heure1819.setText("18H - 19H");
        heure1819.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1819.setOpaque(true);
        heure1819.setBackground(Color.white);
        this.add(heure1819);
        
        for (int i = 0; i < 5; i++) {
            JPanel lesCours1819 = new JPanel();
            lesCours1819.setBackground(Color.white);
            this.add(lesCours1819);
        }
        
         for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
       
         
    }
    
    
    private class ButtonEDTListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

           
            

    }
    }
}