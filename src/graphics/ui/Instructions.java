/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class Instructions extends  JPanel {


    public Instructions(){
                setLayout(new BorderLayout());
		JPanel northPanel = new NorthPane();
		JPanel centerPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();        
               c.insets = new Insets(4,4,4,4);
                c.fill = GridBagConstraints.HORIZONTAL; 
        
               String[][] values = new String[][]{
            {"Topic 02","Game RPG"},
            {"Author","Group 5"},
            {"Date created","5/12/2016"},
            {"Move"," key A : Left , key D : Right , key W : Up , key S : Down."},
            {"Attack monster","key Space"},
            {"Warehouse ","key E"},
            {"Rule of game","Players control characters pass the maps before emptying blood monsters."},
            {"Note","Can pick up objects and gold to increase blood flow."},
            {"Game over","Health bar is empty and you have to overcome all the maps"}
        };
        
        for(int i = 0; i<values.length; i++){
        	JLabel header = new JLabel(values[i][0]+": ");
        	header.setFont(new Font(header.getFont().getName(),Font.BOLD,13));
        	JLabel data = new JLabel(values[i][1]);
        	c.gridx = 0;
            c.gridy = i;
            centerPanel.add(header,c);
            c.gridx = 1;
            centerPanel.add(data,c);
        }
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));   
	}
	
	public static void createAboutPanel(){                   
        JFrame f = new JFrame("Instruction");
        Instructions ap = new Instructions();
        f.getContentPane().add(ap);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
	
	class NorthPane extends JPanel{
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		NorthPane(){          
            JLabel label = new JLabel("How to play the game\n OOP 20161",JLabel.LEFT);
            label.setFont(new Font(label.getFont().getName(),Font.BOLD,15));
            label.setForeground(Color.decode("#9900AF"));
            add(label);
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = this.getWidth()-5;
            int height = this.getHeight() - 1;
            g.setColor(Color.decode("#9900FF"));
            g.drawLine(0, height, width, height);
        }
    }
}
