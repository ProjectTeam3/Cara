package test1;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;


class DrawLinePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private int startX = 0;
	private int startY = 0;
	private int endX = 0;
	private int endY = 0;
	Graphics2D g2;
	JPopupMenu p = new JPopupMenu();
	JMenuItem red = new JMenuItem("��ɫ");
	JMenuItem blue = new JMenuItem("��ɫ");
	JMenuItem green = new JMenuItem("��ɫ");
	int flag=0;
	Image im;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.im, 0, 0, getWidth(), getHeight(), this);
	}
 
	public DrawLinePanel(Image img) {
		new ArrayList<Line2D>();
		im = img;
		p.add(red);
		p.add(blue);
		p.add(green);
		red.addActionListener(this);
		blue.addActionListener(this);
		green.addActionListener(this);
		addMouseListener(new MouseHandler());
		
	}

	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {// ��갴��ʱ
			if (event.getButton() == MouseEvent.BUTTON1) {
				startX = event.getX();
				startY = event.getY();
			}
		}

		public void mouseReleased(MouseEvent event) {// ����ͷ�ʱ
			if (event.getButton() == MouseEvent.BUTTON1) {
				endX=event.getX();
				endY=event.getY();
				Container c = (Container) event.getSource();
				Graphics g = c.getGraphics();
				if(flag==1){
					g.setColor(Color.RED);
				}
				else if(flag==2){
					g.setColor(Color.BLUE);
				}
				else if(flag==3){
					g.setColor(Color.GREEN);
				}
				else{
					g.setColor(Color.BLACK);
				}
				g.drawLine(startX, startY, endX, endY);
			} else {
				p.show(getParent(), event.getX(), event.getY());
			}
		}
	}
	
 @Override
 public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
if(e.getSource()==red){
   flag=1;
  }
  if(e.getSource()==blue){
   flag=2;
  }
  if(e.getSource()==green){
   flag=3;
  }
 }
}




//class DrawLineFrame{
// private static final long serialVersionUID = 1L;
// public DrawLineFrame() {
//  JFrame f=new JFrame();
//  f.setTitle("��껭ֱ��(�Ҽ�����ɫ)");
//  f.setSize(400, 400);
//  f.setVisible(true);
//  Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
//  f.setLocation((dim.width-f.getWidth())/2,(dim.height-f.getHeight())/2);
//  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//  
//  
//  
//  DrawLinePanel panel = new DrawLinePanel(im);//////////////////////
//  
//  
//  
//  Container container = f.getContentPane();
//  container.add(panel);
// }
//}