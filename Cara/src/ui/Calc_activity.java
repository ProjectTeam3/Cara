package ui;
import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import calc.ImageProcessing;
import calc.Point;
import calc.ResponsivePoint;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Calc_activity extends JFrame{
	private int Mx = 1200, My = 820;

	private int lineX1,lineY1,lineX2,lineY2;
	private JPanel pnlCon;//内容pnl
	public JScrollPane pnlData;//图像的pnl
	private JPanel pnlBack;//背景pnl
	private JButton btnCalc;
	private JButton btnChsSQL;
	private JButton btnChsImg;
	private JButton btnImgPro;
//	private JScrollPane jScrollPane2;
	private ScrollPaneDemo SPD;
	private boolean imgProFlag = false; 
	private ScrollPaneDemo SPD_temp;
	private JButton btnBack;
	private JLabel lbBack;
	private JLabel lbDataImg;
	private JLabel lbready;
	private boolean imgread;
	private String strImg;
	private ImageIcon imgData;
	private ImageIcon imgData_temp;
	private int imgc=0;
//	private DrawLinePanel tempdlp;
	private Point PO;
	private ResponsivePoint RPO;
	private Calc_activity CA;
	public Calc_activity(Mode_decide MD){
		super("射电暴计算 Cara3.0");
		CA = this;
		imgread = false;
		ImageIcon bg = new ImageIcon("uisource/calc/background.png");
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

		this.btnBack=createJButton("uisource/calc/btnback1.png","uisource/calc/btnback2.png","uisource/calc/btnback3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new Mode_decide();  
				dispose();
				
			}
		});
		this.btnBack.setBounds(270-85,50,150,70);
		this.pnlCon.add(btnBack);
		
		this.btnCalc=createJButton("uisource/calc/btncalc1.png","uisource/calc/btncalc2.png","uisource/calc/btncalc3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(imgread){
					if (imgProFlag==false){
						System.out.println("大图");
					double ANS = PO.handler(lineX1, lineX2, lineY1, lineY2);
					System.out.println(lineX1);
					System.out.println(lineX2);
					System.out.println(lineY1);
					System.out.println(lineY2);

					System.out.println(ANS);
					JOptionPane.showMessageDialog(null, " 射电暴速度为： "+ANS+" m/s", " 计算结果 ", JOptionPane.OK_OPTION);
				
					}
					else {
						System.out.println("小图");
						double ANS = RPO.handler(lineX1, lineX2, lineY1, lineY2);
						System.out.println(lineX1);
						System.out.println(lineX2);
						System.out.println(lineY1);
						System.out.println(lineY2);

						System.out.println(ANS);
						JOptionPane.showMessageDialog(null, " 射电暴速度为： "+ANS+" m/s", " 计算结果 ", JOptionPane.OK_OPTION);
					
					
					}
				}
								
			}
		});
		this.btnCalc.setBounds(440-85,50,150,70);
		this.pnlCon.add(btnCalc);
		
		this.btnChsImg = createJButton("uisource/calc/btnimg1.png","uisource/calc/btnimg2.png","uisource/calc/btnimg3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser imgChooser=new JFileChooser();
				imgChooser.setFileFilter(new FileFilter() {
					
					@Override
					
					public String getDescription() {
						return"*.jpg,*.gif";
						}
					@Override
					public boolean accept(File f) {
						// TODO Auto-generated method stub
						if (f.isDirectory()) {
							return true;
							}
						return f.getName().endsWith(".jpg") || f.getName().endsWith(".gif")|| f.getName().endsWith(".bmp")|| f.getName().endsWith(".png");
					}
				});  
				int i=imgChooser.showOpenDialog(Calc_activity.this);
				if(i==imgChooser.APPROVE_OPTION){
					strImg = imgChooser.getSelectedFile().getPath();
					System.out.println(strImg);
		            imgData = new ImageIcon(strImg);
		            try {
						PO = new Point(strImg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
//		            lbDataImg = new JLabel(imgData);
		           
		            if(imgread){
		            	SPD.dispose();
		            }  	
		            else{
		            	imgread = true;
		            }
		        //    tempdlp = new DrawLinePanel(imgData.getImage(),CA);
		            SPD = new ScrollPaneDemo(imgData,CA);   		
		    		redd();
					}
				
			}

			private int min(int a, int b) {
				// TODO Auto-generated method stub
				if (a<b) return a;
				else return b;
			}
		});
		this.btnChsImg.setBounds(610-85,50,150,70);
		this.pnlCon.add(btnChsImg);
		
		this.btnChsSQL = createJButton("uisource/calc/btnsql1.png","uisource/calc/btnsql2.png","uisource/calc/btnsql3.png",new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame dbF  = new DB_activity();
				dispose();
				
			
			}
		});
		this.btnChsSQL.setBounds(780-85,50,150,70);
		this.pnlCon.add(btnChsSQL);	
		
		this.btnImgPro=createJButton("uisource/calc/btnimgpro1.png","uisource/calc/btnimgpro2.png","uisource/calc/btnimgpro3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(imgProFlag==false){
					try {
						
						BufferedImage Original=ImageIO.read(new File(strImg));
						int startpixelx=170;
						int startpixely=50;
						int endpixelx=1730;
						int endpixely=550;
						int width = endpixelx-startpixelx;
						int height = endpixely-startpixely;
						BufferedImage cut=Original.getSubimage(startpixelx, startpixely, width, height);
						ImageProcessing Ip=new ImageProcessing(cut);
						ImageProcessing IMP = new ImageProcessing(cut);
						IMP.process();
						imgProFlag = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					imgData_temp = new ImageIcon("new"+Integer.toString(imgc)+".gif");
					imgc = (imgc+1)%5;
					try {
						RPO = new ResponsivePoint("new"+Integer.toString(imgc)+".gif");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SPD_temp = new ScrollPaneDemo(imgData_temp,CA);  
					System.out.println("IMG Processing");
//					JOptionPane.showMessageDialog(null, " 射电暴速度为： "+ANS+" m/s", " 计算结果 ", JOptionPane.OK_OPTION);
				
								
			}
		});
		this.btnImgPro.setBounds(950-85,50,150,70);
		this.pnlCon.add(btnImgPro);
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
	
}
