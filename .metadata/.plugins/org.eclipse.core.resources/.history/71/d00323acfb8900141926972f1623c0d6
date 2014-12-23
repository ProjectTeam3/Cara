package test1;

import java.awt.*;

import javax.swing.*;

public class TestImage extends JPanel{
	Image im;
	public TestImage(Image im){
		this.im = im;
		
		int width =im.getWidth(this);
		int height = im.getHeight(this);
		setSize(width,height);
		}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.im, 0, 0, getWidth(), getHeight(), this);
	}
	public void setImg(Image im){
	    this.im = im;
	}
}