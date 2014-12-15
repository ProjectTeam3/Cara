package test1;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main_activity extends JFrame{
	ImageProcessing IP;
	String strInfo;
	JButton btnOpenImg;
	JButton btnGo;
	JButton btnFresh;
	int Mx = 500;
	int My = 500;
	JButton btnQuit;
	JPanel pnlMain;
	static DataLine DL;
	JPanel pnlImgLine;
	JPanel pnlTitle;
	JPanel pnlButtons;
	String strImg;
	JPanel pnlImg; 
	JScrollPane scrollImg;
	static Image image;
	static Main_activity MA;
	TestImage tImg;
	public static void main(String[] args){
		image = new ImageIcon("sample.gif").getImage();
		System.out.println("FIrst!");
		MA = new Main_activity("Test UI of Cara");
//		MA.dispose();
//		MA.setVisible(true);
	}
	public Main_activity(String title){
		super(title);
		this.setSize(Mx, My);
		init();
		this.getContentPane().add(pnlMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void init(){

		pnlMain = getMainPanel();
		this.getContentPane().add(pnlMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private JPanel getMainPanel(){
		JPanel pnl = new JPanel();
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		pnlTitle = getTitlePanel();
		DL = new DataLine(image);
		pnlImgLine = DL;
		pnlButtons = getButtonsPanel();
		pnl.add(pnlTitle);
		pnl.add(pnlImgLine);
		pnl.add(pnlButtons);
		return pnl;
	}
	private JPanel getTitlePanel(){
		JPanel pnl = new JPanel();
		JLabel lbTitle = new JLabel("Cara测试界面0.1",JLabel.CENTER);
		lbTitle.setFont(new Font("黑体", Font.PLAIN, 32));
		pnl.add(lbTitle);
		return pnl;
	}
	private JPanel getButtonsPanel(){
		JFileChooser imgChooser=new JFileChooser();
		JFileChooser infoChooser=new JFileChooser();
		JButton btnDraw = new JButton("直线数据");
		btnDraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DL.changeData1("StartX = "+DL.pnlDraw.startX+" StartY = "+DL.pnlDraw.startY);
				DL.changeData2("EndX = "+DL.pnlDraw.endX+" EndY = "+DL.pnlDraw.endY);
			}
		});
		btnOpenImg = new JButton("打开");
		btnOpenImg.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i=imgChooser.showOpenDialog(Main_activity.this);
				if(i==imgChooser.APPROVE_OPTION){
					strImg = imgChooser.getSelectedFile().getPath();
					System.out.println(strImg);
		            image=new ImageIcon(strImg).getImage();
		            DL.pnlDraw.im = image;
		            DL.pnlDraw.repaint();
//		            MA.dispose();
//		            MA.setVisible(true);
				}
				
//			E	Main_activity.this.dispose();
				Main_activity.this.setVisible(true);
				
		        if(i==imgChooser.CANCEL_OPTION)return;
				}
		});
		btnFresh = new JButton("信息文件");
		btnFresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				pnlImgLine.repaint();
				int i=infoChooser.showOpenDialog(Main_activity.this);
				if(i==infoChooser.APPROVE_OPTION){
					strInfo = infoChooser.getSelectedFile().getPath();
					System.out.println(strInfo);
				}
			}
		});
		btnGo = new JButton("生成");
		btnGo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					IP =  new ImageProcessing(strImg,strInfo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {			
					double v = IP.process();
					System.out.println("v = "+v);
					JOptionPane.showMessageDialog(null,"Speed is "+v);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnQuit = new JButton("退出");
		btnQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MA.dispose();
			}
		});
		JPanel pnl = new JPanel();
		GridBagLayout gbl_pnl = new GridBagLayout();
		pnl.setLayout(gbl_pnl);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=0;
		pnl.add(btnOpenImg, gbc);
		GridBagConstraints gbc1=new GridBagConstraints();
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		pnl.add(btnGo, gbc1);
		GridBagConstraints gbc2=new GridBagConstraints();
		gbc2.gridx = 2;
		gbc2.gridy = 0;
		pnl.add(btnQuit, gbc2);
		GridBagConstraints gbc3=new GridBagConstraints();		
		gbc3.gridx = 3;
		gbc3.gridy = 0;
		pnl.add(btnFresh, gbc3);
		GridBagConstraints gbc4=new GridBagConstraints();	
		gbc4.gridx = 4;
		gbc4.gridy = 0;
		pnl.add(btnDraw, gbc4);
		return pnl;
	}

	private void showDataFromFile(String address2) throws NumberFormatException, IOException{
		FileInputStream fis = new FileInputStream(address2);
		InputStreamReader isr = new InputStreamReader(fis);
		LineNumberReader lnr = new LineNumberReader(isr);
		int timestart;
		int timeend;
		
		String s =null;
		int i,t=1;
		while ((s=lnr.readLine())!=null && t<=2){
			i=Integer.parseInt(s);
			if (t==1){
				timestart=i;
			}
			else{
				timeend=i;
			}
			t++;
		}
		if (t<=2){
			System.out.println("error: not enough information!");
		}
	}
}
