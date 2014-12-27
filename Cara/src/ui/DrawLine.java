package ui;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

class DrawLinePanel extends JPanel implements ActionListener {
	static int startX = 0;
	static int startY = 0;
	static int endX = 0;
	static int endY = 0;
	private Calc_activity CA; 
	static boolean fla = false;
	Graphics2D g2;
	JPopupMenu p = new JPopupMenu();
	JMenuItem red = new JMenuItem("红色");
	static Image im;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.im, 0, 0, getWidth(), getHeight(), this);
	}
 
	public DrawLinePanel(Image img,Calc_activity xx) {
		new ArrayList<Line2D>();
		im = img;
//		p.add(red);
		red.addActionListener(this);
		addMouseListener(new MouseHandler());
		CA = xx;
	}
	public void redd(){
		this.repaint();
	}
	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {// 鼠标按下时
			if (event.getButton() == MouseEvent.BUTTON1) {
				redd();
				startX = event.getX();
				startY = event.getY();
			}
		}

		public void mouseReleased(MouseEvent event) {// 鼠标释放时
			if (event.getButton() == MouseEvent.BUTTON1) {
				endX=event.getX();
				endY=event.getY();
				Container c = (Container) event.getSource();
				Graphics g = c.getGraphics();
				g.setColor(Color.RED);
				g.drawLine(startX, startY, endX, endY);
				CA.setData(startX, startY, endX, endY);
				
			} else {
				p.show(getParent(), event.getX(), event.getY());
				
			}
		}
	}
	public int getsx(){
		return startX;
	}
	public int getex(){
		return endX;
	}
	public int getsy(){
		return startY;
	}
	public int getey(){
		return endY;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==red){	
		}
	}
}
