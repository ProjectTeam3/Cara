package ui;

import javax.swing.*;

import java.util.ArrayList;
import java.awt.*;

public class DataLine extends JPanel{
//	ArrayList<JLabel> Data1;
//	ArrayList<JLabel> Data2;
	JLabel l1;
	JLabel l2;
	JLabel Data1;
	JLabel Data2;
	JPanel pnl1;
	JPanel pnl2;
	static DrawLinePanel pnlDraw;
	public DataLine(Image image) {
		setLayout(new BorderLayout(5, 5));	

		Data1 = new JLabel();
		Data2 = new JLabel();

		l1 = new JLabel();
		l2 = new JLabel();
		l1.setText("��ʼ������");
		l2.setText("����������");
		Data1.setText("����1δ��ȡ");	
		Data2.setText("����2δ��ȡ");
		pnl1 = new JPanel();
		pnl2 = new JPanel();
		BoxLayout layout=new BoxLayout(pnl1, BoxLayout.Y_AXIS);
		BoxLayout layout2=new BoxLayout(pnl2, BoxLayout.Y_AXIS);
		pnl1.setLayout(layout);
		pnl2.setLayout(layout2);
		pnl1.add(l1);
		pnl1.add(Data1);
		pnl2.add(l2);
		pnl2.add(Data2);
		pnlDraw = new DrawLinePanel(image);
		pnlDraw.repaint();
		add(l1, BorderLayout.WEST);
		add(l2, BorderLayout.EAST);

		add(Data1, BorderLayout.WEST);
		add(Data2, BorderLayout.EAST);
		add(pnlDraw, BorderLayout.CENTER);
		this.repaint();		
	}
	public void changeData1(String str){
		Data1.setText(str);
	}
	public void changeData2(String str){
		Data2.setText(str);
	}
	
}