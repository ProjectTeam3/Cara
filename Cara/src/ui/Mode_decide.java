package ui;
import javax.rmi.CORBA.Util;
import javax.swing.*;

import Database.FileDownload;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Mode_decide extends JFrame{
	private int Mx = 1200, My = 820;
	private JPanel btnPane;//创建面板
	private JPanel pnlBack;//创建面板
	private JButton btnToCountFrm;//创建按钮1
	private JButton btnToSQL;//创建按钮2
	private JButton btnToOb;//创建按钮3
	private JLabel lbBack;
	private JLabel imgtest;
	
	public Mode_decide(){
		super("Welcome to use Cara3.0");

		Mode_decide MD = this;
		ImageIcon bg = new ImageIcon("uisource/modeBack.jpg");
	
		this.setResizable(false);
		this.setSize(Mx, My);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lbBack = new JLabel(bg);
		this.lbBack.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(lbBack,new Integer(Integer.MIN_VALUE));
		JPanel jp=(JPanel)this.getContentPane();
		jp.setOpaque(false);
		this.btnPane=new JPanel();//初始化面板
		this.btnPane.setLayout(null);
		this.btnPane.setOpaque(false);
		this.btnToCountFrm=createJButton("uisource/btn10.png","uisource/btn11.png","uisource/btn12.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new Calc_activity(MD); 
				dispose();
				
			}
		});
		this.btnToCountFrm.setBounds(140,200,240,300);
		this.btnPane.add(btnToCountFrm);
		this.btnToSQL=createJButton("uisource/btn20.png","uisource/btn21.png","uisource/btn22.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF  = new DB_activity();
				dispose();
				
			}
		});
		this.btnPane.add(btnToSQL);
		this.btnToSQL.setBounds(600-120, 200, 240, 300);
		this.btnToOb=createJButton("uisource/btn30.png","uisource/btn31.png","uisource/btn32.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new DataObType(); 
				dispose();
			}
		});
		this.btnPane.add(btnToOb);
		this.btnToOb.setBounds(1200-240-140, 200, 240, 300);
		this.add(this.btnPane);
		this.setVisible(true);
	}
	

	public static JButton createJButton(String imgIcon, String imgRO, String imgPre,ActionListener listener) {  
        JButton jb = new JButton();  
        jb.setBorderPainted(false);  
        jb.setFocusPainted(false);  
        jb.setContentAreaFilled(false);  
        jb.setDoubleBuffered(true);  
        jb.setIcon(new ImageIcon(imgIcon));  
        jb.setRolloverIcon(new ImageIcon(imgRO));  
        jb.setPressedIcon(new ImageIcon(imgPre));  
        jb.setOpaque(false);  
        jb.setFocusable(false);  
        jb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
        jb.addActionListener(listener);  
        return jb;  
    }  
	public static void main(String[] args){
		Mode_decide example=new Mode_decide();

		System.out.println("Mode decide Frame");
	}
}
//gittest