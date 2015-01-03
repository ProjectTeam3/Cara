package ui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SunData extends JFrame{
	private int Mx = 1600, My = 1100;
	private JPanel conPane;//创建面板
	private JPanel pnlBack;//创建面板
	private JLabel lbBack;
	private JLabel lbImg;
	private JButton btnBack;
	public SunData(String imgaddress1,String imgaddress2,String imgaddress3){
		super("Welcome to use Cara3.0");
		SunData MD = this;
		ImageIcon bg = new ImageIcon("uisource/imgback.png");
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
		lbImg=new JLabel();
		lbImg.setText("<html><img src='"+imgaddress1+imgaddress2+imgaddress3+"' /></html>");
		lbImg.setBounds((int)(800-Integer.valueOf(imgaddress2)*0.5), (int)(500-Integer.valueOf(imgaddress2)*0.5), Integer.valueOf(imgaddress2), Integer.valueOf(imgaddress2));
		conPane.add(lbImg);
		btnBack=createJButton("uisource/btnback0.png","uisource/btnback1.png","uisource/btnback2.png",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame mainF = new DataObType();  
				dispose();
				
			}
		});
		this.btnBack.setBounds(100,400,75,225);
		this.conPane.add(btnBack);
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

}
