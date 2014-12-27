package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollPaneDemo extends JFrame implements MouseListener {

        DrawLinePanel jp;
        
        JScrollPane jsp;

        public ScrollPaneDemo(ImageIcon im, Calc_activity CA) {
                super("Cara —°‘Ò«¯”Ú");
                jp = new DrawLinePanel(im.getImage(),CA);
                jp.setPreferredSize(new Dimension(im.getIconWidth(),im.getIconHeight()));
                jsp = new JScrollPane(jp);
                jp.addMouseListener(this);
                getContentPane().add(jsp);
                setSize(1200, 350);
//                setDefaultCloseOperation(XIT_ON_CLOSE);
                setVisible(true);
        }

        public void mouseClicked(MouseEvent e) {
                System.out.println("mouseClicked");
                jp.repaint();
                jsp.validate();
        }
        public JScrollPane comeonbaby(){
        	return jsp;
        }
        public void mousePressed(MouseEvent e) {
                // System.out.println("mousePressed");
        }

        public void mouseReleased(MouseEvent e) {
                // System.out.println("mouseReleased");
        }

        public void mouseEntered(MouseEvent e) {
                // System.out.println("mouseEntered");
        }

        public void mouseExited(MouseEvent e) {
                // System.out.println("mouseExited");
        }

}


