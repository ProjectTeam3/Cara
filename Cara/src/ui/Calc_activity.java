package ui;
import javax.rmi.CORBA.Util;
import javax.swing.*;

import calc.Point;

import java.awt.*;
import java.awt.event.*;
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
//	private JScrollPane jScrollPane2;
	private ScrollPaneDemo SPD;
	private JButton btnBack;
	private JLabel lbBack;
	private JLabel lbDataImg;
	private JLabel lbready;
	private boolean imgread;
	private String strImg;
	private ImageIcon imgData;
	private DrawLinePanel tempdlp;
	private Point PO;
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
		this.btnBack.setBounds(270,50,150,70);
		this.pnlCon.add(btnBack);
		
		this.btnCalc=createJButton("uisource/calc/btncalc1.png","uisource/calc/btncalc2.png","uisource/calc/btncalc3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(imgread){
					double ANS = PO.handler(lineX1, lineX2, lineY1, lineY2);
					System.out.println(lineX1);
					System.out.println(lineX2);
					System.out.println(lineY1);
					System.out.println(lineY2);

					System.out.println(ANS);
					JOptionPane.showMessageDialog(null, " 射电暴速度为： "+ANS+"km/s", " 计算结果 ", JOptionPane.OK_OPTION);
				}
								
			}
		});
		this.btnCalc.setBounds(440,50,150,70);
		this.pnlCon.add(btnCalc);
		
		this.btnChsImg = createJButton("uisource/calc/btnimg1.png","uisource/calc/btnimg2.png","uisource/calc/btnimg3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser imgChooser=new JFileChooser();
				int i=imgChooser.showOpenDialog(Calc_activity.this);
				if(i==imgChooser.APPROVE_OPTION){
					strImg = imgChooser.getSelectedFile().getPath();
					System.out.println(strImg);
//		            imgData=new ImageIcon(strImg).getImage();
		            imgData = new ImageIcon(strImg);
		            try {
						PO = new Point(strImg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		            lbDataImg = new JLabel(imgData);
		           
		            if(imgread){
//		            	pnlData.setVisible(false);//隐藏掉
//		            	pnlCon.remove(SPD);//删除panel中的控件
		            	SPD.dispose();
		            }  	
		            else{
		            	imgread = true;
		            }
		            tempdlp = new DrawLinePanel(imgData.getImage(),CA);
//		            pnlData = tempdlp;
		            SPD = new ScrollPaneDemo(imgData,CA);
//		            pnlData = SPD.comeonbaby();
//		            pnlData.setBounds(0,210,1200,350);
//		            jScrollPane2 = new JScrollPane(lbDataImg);  
//		   
//		            jScrollPane2.setPreferredSize(new Dimension(1200, 350));
//		            jScrollPane2.setBounds(600-(min(9999999,1200)/2), (210+175-(min(9999999,350)/2)), 1200,350); 
//		            jScrollPane2.setBounds(0,210,1200,350);
//		    		pnlCon.add(pnlData);
		    		
		    		redd();
				}	
			}

			private int min(int a, int b) {
				// TODO Auto-generated method stub
				if (a<b) return a;
				else return b;
			}
		});
		this.btnChsImg.setBounds(610,50,150,70);
		this.pnlCon.add(btnChsImg);
		
		this.btnChsSQL = createJButton("uisource/calc/btnsql1.png","uisource/calc/btnsql2.png","uisource/calc/btnsql3.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new Mode_decide();  
				dispose();
				
			}
		});
		this.btnChsSQL.setBounds(780,50,150,70);
		this.pnlCon.add(btnChsSQL);	
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
