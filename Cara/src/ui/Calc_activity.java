package ui;
import javax.rmi.CORBA.Util;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Calc_activity extends JFrame{
	private int Mx = 1200, My = 820;
	private JPanel pnlCon;//����pnl
	public JPanel pnlData;//ͼ���pnl
	private JPanel pnlBack;//����pnl
	private JButton btnCalc;
	private JButton btnChsSQL;
	private JButton btnChsImg;
	private JButton btnBack;
	private JLabel lbBack;
	private JLabel lbDataImg;
	private JLabel lbready;
	private boolean imgread;
	private String strImg;
	private ImageIcon imgData;
	public Calc_activity(){
		super("��籩���� Cara3.0");
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
				JFrame mainF = new Mode_decide();  
				dispose();
				
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
		            lbDataImg = new JLabel(imgData);
		            if(imgread){
		            	pnlData.setVisible(false);//���ص�
		            	pnlCon.remove(pnlData);//ɾ��panel�еĿؼ�
		            }
		            else{
		            	imgread = true;
		            }
		            pnlData=new DrawLinePanel(imgData.getImage());
		            pnlData.setBounds(600-(imgData.getIconWidth()/2), (210+175-(imgData.getIconHeight()/2)), imgData.getIconWidth(),imgData.getIconHeight()); 
		            pnlData.setLayout(null);
		            pnlData.setOpaque(false);
		    		pnlCon.add(pnlData);
		    		redd();
				}	
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

		Calc_activity example=new Calc_activity();

		System.out.println("Mode decide Frame");
	}
	public void redd(){
		this.repaint();
	}
	public JFrame rthis()
	{
		return this;
	}
	
}
