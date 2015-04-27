package ui;


import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Database.FileDownload;
import calc.Point;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DB_activity extends JFrame {

	private int Mx = 1200, My = 820;

	private int lineX1,lineY1,lineX2,lineY2;
	private JPanel pnlCon;//内容pnl
	public JScrollPane pnlData;//图像的pnl
	private JPanel pnlBack;//背景pnl

//	private JScrollPane jScrollPane2;
	private ScrollPaneDemo SPD;
	private JButton btnBack;
	private JButton btnFtp;
	private JButton btnSql;
	private JComboBox jcbYr;
	private JComboBox jcbMt;
	private JComboBox jcbDy;
	private int intYr;
	private int intMt;
	private int intDy;
	
	private JLabel lbBack;
	private JLabel lbDataImg;
	private JLabel lbready;
	private boolean imgread;
	private String strImg;
	private ImageIcon imgData;
	private DrawLinePanel tempdlp;
	private Point PO;
	private DB_activity DA;
	public DB_activity(){
		super("Cara3.0数据管理界面");
		DA = this;
		imgread = false;
		ImageIcon bg = new ImageIcon("uisource/modeBack.jpg");
		this.setResizable(false);
		this.setSize(Mx, My);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lbBack = new JLabel(bg);
		this.lbBack.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(lbBack,new Integer(Integer.MIN_VALUE));
		JPanel jp=(JPanel)this.getContentPane();
		jp.setOpaque(false);
		
		
		this.pnlCon=new JPanel();
		this.pnlCon.setLayout(null);
		this.pnlCon.setOpaque(false);

		JPanel pnlYear = year();
		JPanel pnlMonth = month();
		JPanel pnlDay = day();

		pnlCon.add(pnlYear);
		pnlCon.add(pnlMonth);
		pnlCon.add(pnlDay);
		intYr = jcbYr.getSelectedIndex();
		intMt = jcbMt.getSelectedIndex();
		intDy = jcbDy.getSelectedIndex();
		
	    this.setVisible(true);
		
		this.btnBack=createJButton("uisource/calc/btnback1.png","uisource/calc/btnback2.png","uisource/calc/btnback3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new Mode_decide();  
				dispose();
				
			}
		});
		this.btnBack.setBounds(600-75,550,150,70);
		this.pnlCon.add(btnBack);
		this.btnSql=createJButton("uisource/btnsql1.png","uisource/btnsql2.png","uisource/btnsql3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new Mode_decide();  
				dispose();
				
			}
		});
		this.btnSql.setBounds(600-225,400,450,70);
//		this.pnlCon.add(btnSql);
		this.btnFtp=createJButton("uisource/btnftp1.png","uisource/btnftp2.png","uisource/btnftp3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				intYr = jcbYr.getSelectedIndex();
				intMt = jcbMt.getSelectedIndex();
				intDy = jcbDy.getSelectedIndex();
				
				System.out.println("我是卖报的小行家");
				String tempA ;
				String tempB ;
				if(intMt+1<10) tempA= "0";
				else tempA = "";
				if(intDy+1<10) tempB= "0";
				else tempB = "";
				FileDownload FD = new FileDownload();
				boolean temp = FD.handler(""+(intYr+2000),tempA+(intMt+1),tempB+(intDy+1));
				if(temp)JOptionPane.showMessageDialog(null, " FTP下载成功 ", "FTP下载结果 ", JOptionPane.OK_OPTION);
				else JOptionPane.showMessageDialog(null, " FTP下载失败 ", " FTP下载结果 ", JOptionPane.OK_OPTION);
				FD = null;
				System.gc();
				JFrame mainF = new DB_activity();  
				dispose();
			}
		});
		this.btnFtp.setBounds(600-225,300,450,70);
		this.pnlCon.add(btnFtp);
		this.add(this.pnlCon);

		this.setVisible(true);
	}
	public void setData(int x1,int y1,int x2,int y2){
		lineX1 = x1;
		lineY1 = y1;
		lineX2 = x2;
		lineY2 = y2;
	
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
//	public static void main(String[] args){
//
//		Calc_activity example=new Calc_activity();
//
//		System.out.println("Mode decide Frame");
//	}
	public void redd(){
		this.repaint();
	}
	public JFrame rthis()
	{
		return this;
	}
	public JPanel year(){
		JPanel contentPane=new JPanel();
	    contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBounds(600-75-200,150,150,48);
//	    this.setContentPane(contentPane);
		contentPane.setBackground(Color.black);  
//		contentPane.setFocusPainted(false);  
	    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
	    JLabel label=new JLabel("年份:");
	    label.setForeground(Color.WHITE);
	    contentPane.add(label);
	    jcbYr=new JComboBox();
//	    comboBox.setBackground(Color.black);  
	    for(int i = 2000;i<2015;i++){
	    	jcbYr.addItem(i+"年");
	    }
	    contentPane.add(jcbYr);
	    return contentPane;
	}
	public JPanel month(){
		JPanel contentPane=new JPanel();
	    contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBounds(600-75,150,150,48);
//	    this.setContentPane(contentPane);
		contentPane.setBackground(Color.black);  
//		contentPane.setFocusPainted(false);  
	    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
	    JLabel label=new JLabel("月份:");
	    label.setForeground(Color.WHITE);
	    contentPane.add(label);
	    jcbMt=new JComboBox();
//	    comboBox.setBackground(Color.black);  
	    for(int i = 1;i<13;i++){
	    	jcbMt.addItem(i+"月");
	    }
	    contentPane.add(jcbMt);
	    return contentPane;
	}
	public JPanel day(){
		JPanel contentPane=new JPanel();
	    contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBounds(600-75+200,150,150,48);
//	    this.setContentPane(contentPane);
		contentPane.setBackground(Color.black);  
//		contentPane.setFocusPainted(false);  
	    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
	    JLabel label=new JLabel("日期:");
	    label.setForeground(Color.WHITE);
	    contentPane.add(label);
	    jcbDy=new JComboBox();  
//	    comboBox.setBackground(Color.black);  
	    for(int i = 1;i<32;i++){
	    	jcbDy.addItem(i+"日");
	    }
	    contentPane.add(jcbDy);
	    return contentPane;
	}
}
