package test1;

import javax.swing.*;

import java.util.ArrayList;
import java.awt.*;

public class DataLine extends JPanel{
	ArrayList<JLabel> Data1;
	ArrayList<JLabel> Data2;
	
	public DataLine(Image image) {
		setLayout(new BorderLayout(5, 5));	
		JScrollPane scrollPane1 = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		DrawLinePanel pnlDraw = new DrawLinePanel(image);
		pnlDraw.repaint();
		
		add(scrollPane1, BorderLayout.WEST);
		add(scrollPane2, BorderLayout.EAST);
		add(pnlDraw, BorderLayout.CENTER);
		this.repaint();
		
		
	}
//	public JPanel Imgchange(Image img){		
//		DataLine pnl = new DataLine(img);
//		setLayout(new BorderLayout(5, 5));	
//		JScrollPane scrollPane1 = new JScrollPane();
//		JScrollPane scrollPane2 = new JScrollPane();
//		DrawLinePanel pnlDraw = new DrawLinePanel(img);
//		add(scrollPane1, BorderLayout.WEST);
//		add(scrollPane2, BorderLayout.EAST);
//		add(pnlDraw, BorderLayout.CENTER);
//	}
	
	
}
