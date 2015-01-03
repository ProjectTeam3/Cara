package ui;
import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DataObType extends JFrame{
	private int Mx = 1200, My = 820;
	private int lx = 150, ly = 0;
	private JPanel conPane;//创建面板
	private JPanel pnlBack;//创建面板

	private JRadioButton jcbE171;
	private JRadioButton jcbE195;
	private JRadioButton jcbE284;
	private JRadioButton jcbE304;
	private JRadioButton jcb0100;
	private JRadioButton jcb1300;

	private JTextField tfData1;
	private JTextField tfData2; 
	
	private JList jlResolution;
	
	private JLabel lbBack;
	private JLabel lbTitle;
	private JLabel lbImageType;
	private JLabel lbResolution;
	private JLabel lbMAIMENG;
	
	private JButton btnBack;
	private JButton btnData;
	private JButton btnlatest;
	
	
	private int resolutionSzie;
	private String datatype;
	private String datatime;
	
	public DataObType(){
		super("Welcome to use Cara3.0");

		DataObType MD = this;
		ImageIcon bg = new ImageIcon("uisource/modeBack.jpg");
	
		this.setResizable(false);
		this.setSize(Mx, My);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lbBack = new JLabel(bg);
		this.lbBack.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(lbBack,new Integer(Integer.MIN_VALUE));
		JPanel jp=(JPanel)this.getContentPane();
		jp.setOpaque(false);
		this.conPane=new JPanel();//初始化面板
		this.conPane.setLayout(null);
		this.conPane.setOpaque(false);
		lbTitle = new JLabel("SOHO实时数据");
		lbImageType = new JLabel("数据类型");
		lbImageType.setBounds(lx+150, 175, 100, 25);
		lbImageType.setOpaque(false); 
		lbImageType.setForeground(Color.WHITE);
		conPane.add(lbImageType);
		
		ButtonGroup ImageTypeGroup = new ButtonGroup();
		
		jcbE171 = new  JRadioButton("E171");
		jcbE171.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datatype =  "eit_171";
				jcb0100.setText("0100");
				jcb1300.setText("1300");
			}
		});
		jcbE171.setBounds(lx+150, 200, 100, 25);
		jcbE171.setOpaque(false); 
//		jcbE171=createJCheckCox("uisource/btn30.png","uisource/btn31.png","uisource/btn32.png");
		jcbE171.setForeground(Color.WHITE);
		conPane.add(jcbE171);
		jcbE195 = new  JRadioButton("E195");
		jcbE195.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datatype =  "eit_195";
				jcb0100.setText("0113");
				jcb1300.setText("1313");
			}
		});
		jcbE195.setForeground(Color.WHITE);
		jcbE195.setBounds(lx+150, 225, 100, 25);
		jcbE195.setOpaque(false); 
		conPane.add(jcbE195);
		jcbE284 = new  JRadioButton("E284");
		jcbE284.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datatype =  "eit_284";
				jcb0100.setText("0106");
				jcb1300.setText("1306");
			}
		});
		
		jcbE284.setForeground(Color.WHITE);
		jcbE284.setBounds(lx+150, 250, 100, 25);
		jcbE284.setOpaque(false);
		conPane.add(jcbE284);
		jcbE304 = new  JRadioButton("E304");
		jcbE304.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datatype =  "eit_304";
				jcb0100.setText("0119");
				jcb1300.setText("1319");
			}
		});
		
		jcbE304.setForeground(Color.WHITE);
		jcbE304.setBounds(lx+150, 275, 100, 25);
		jcbE304.setOpaque(false);
		conPane.add(jcbE304);
		ImageTypeGroup.add(jcbE171);
		ImageTypeGroup.add(jcbE195);
		ImageTypeGroup.add(jcbE284);
		ImageTypeGroup.add(jcbE304);
		
		ButtonGroup timeGroup = new ButtonGroup();
		jcb0100 = new JRadioButton("0100");
		jcb0100.setBounds(lx+525, 250, 80, 25);
		jcb0100.setForeground(Color.white);
		jcb0100.setOpaque(false);
		jcb0100.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datatime  = jcb0100.getText();;
			}
		});
		conPane.add(jcb0100);
		timeGroup.add(jcb0100);
		jcb1300 = new JRadioButton("1300");
		jcb1300.setBounds(lx+670, 250, 100, 25);
		jcb1300.setForeground(Color.white);
		jcb1300.setOpaque(false);
		jcb1300.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datatime = jcb1300.getText();
			}
		});
		conPane.add(jcb1300);
		timeGroup.add(jcb1300);
		
		lbResolution = new JLabel("Resolution");
		lbResolution.setBounds(lx+300, 175, 100, 25);
		lbResolution.setOpaque(false); 
		lbResolution.setForeground(Color.WHITE);
		conPane.add(lbResolution);
		String[] jg = {"512","1024"};  
		jlResolution = new JList(jg);
		jlResolution.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if(jlResolution.getSelectedIndex()!=-1)
				{
					if(jlResolution.getSelectedIndex()==0){
						resolutionSzie = 512;
					}
					else resolutionSzie = 1024;
					
				}
				
			}
		});
		jlResolution.setVisibleRowCount(2);  
		JScrollPane jsp = new JScrollPane(jlResolution); 
		jsp.setBounds(lx+300, 200, 100, 44);
		conPane.add(jsp);
		tfData1 = new JTextField();
		tfData1.setBounds(lx+500, 200, 100, 25);
		conPane.add(tfData1);
		lbMAIMENG = new JLabel("~");
		lbMAIMENG.setBounds(lx+621, 200, 10, 25);
		lbMAIMENG.setForeground(Color.WHITE);
//		conPane.add(lbMAIMENG);
		tfData2 = new JTextField();
		tfData2.setBounds(lx+650, 200, 100, 25);
//		conPane.add(tfData2);
		
		btnBack=createJButton("uisource/calc/btnback1.png","uisource/calc/btnback2.png","uisource/calc/btnback3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new Mode_decide();  
				dispose();
				
			}
		});
		this.btnBack.setBounds(lx+200,400,150,70);
		this.conPane.add(btnBack);
		
		btnData=createJButton("uisource/calc/btnsql1.png","uisource/calc/btnsql2.png","uisource/calc/btnsql3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String temp ="http://sohowww.nascom.nasa.gov//data/REPROCESSING/Completed/"+tfData1.getText().substring(0,3)+"/eit195/"+tfData1.getText()+"/"+tfData1.getText()+"_"+datatime+"_"+datatype.substring(0,2)+datatype.substring(4,6)+"_"+""+resolutionSzie+".jpg";
				JFrame mainF = new SunData("http://sohowww.nascom.nasa.gov//data/REPROCESSING/Completed/"+tfData1.getText().substring(0,4)+"/"+datatype.substring(0,3)+datatype.substring(4,7)+"/"+tfData1.getText()+"/"+tfData1.getText()+"_"+datatime+"_"+datatype.substring(0,3)+datatype.substring(4,7)+"_",""+resolutionSzie,".jpg");  
				System.out.println(temp);
				dispose();
				
			}
		});
		this.btnData.setBounds(700,400,150,70);
		this.conPane.add(btnData);
		
		btnlatest=createJButton("uisource/btn40.png","uisource/btn41.png","uisource/calc/btn42.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new SunData("http://sohowww.nascom.nasa.gov/data/realtime/"+datatype+"/",""+resolutionSzie,"/latest.jpg");  
				dispose();
				
			}
		});
		this.btnlatest.setBounds(525,400,150,70);
		this.conPane.add(btnlatest);
		this.add(this.conPane);
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
		DataObType example=new DataObType();

		System.out.println("Mode decide Frame");
	}
}
