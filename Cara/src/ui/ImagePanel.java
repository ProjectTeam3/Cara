package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ImagePanel extends JPanel {
    private Image image;

    private Dimension theSize = new Dimension(600, 400);

    public void setImage(Image image) {
            this.image = image;
            this.repaint();
    }

    public Image getImage() {
            return this.image;
    }

    public void paintComponent(Graphics g) {
            ImageIcon img = new ImageIcon("sample.gif");
            g.drawImage(img.getImage(), 0, 0, theSize.width, theSize.height, null);
    }

    public void enlarge() {
            theSize.width = (theSize.width * 101) / 100;
            theSize.height = (theSize.height * 101) / 100;
            setSize(theSize);
    }
    
    public Dimension getPreferredSize() {
            return this.theSize;
    }
}