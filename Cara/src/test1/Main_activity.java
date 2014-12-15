package test1;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main_activity extends JFrame{
	ImageProcessing IP;
	String strInfo;
	JButton btnOpenImg;
	JButton btnLeft;
	JButton btnGo;
	JButton btnFresh;
	int Mx = 500;
	int My = 500;
	JButton btnRight;
	JButton btnQuit;
	JPanel pnlMain;
	JPanel pnlImgLine;
	JPanel pnlTitle;
	JPanel pnlButtons;
	String strImg;
	JPanel pnlImg; 
	static Image image;
	TestImage tImg;
	public static void main(String[] args){
		image = new ImageIcon("sample.gif").getImage();
		Main_activity MA;
		MA = new Main_activity("Test UI of Cara");
		MA.dispose();
		MA.setVisible(true);
	}
	public Main_activity(String title){
		super(title);
		this.setSize(Mx, My);
		this.setVisible(true);
		init();
		this.getContentPane().add(pnlMain);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		pnlImgLine = getImgLinePanel();
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
//		            image=image.getScaledInstance(400, 400, Image.SCALE_DEFAULT );//调整图像大小400,400
		            tImg.setImg(image);
				}
				tImg.repaint();
				Main_activity.this.repaint();
				GridBagConstraints gbc=new GridBagConstraints();
				gbc.fill=GridBagConstraints.BOTH;	
				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.ipadx = image.getWidth(pnlImgLine);
				if (gbc.ipadx>500) gbc.ipadx = gbc.ipadx/(gbc.ipadx/500);
				gbc.ipady = image.getHeight(pnlImgLine);
				if (gbc.ipady>500) gbc.ipady = gbc.ipadx/(gbc.ipadx/500);;
				gbc.weightx = 0;
				Mx = gbc.ipadx+ 150;
				if (Mx<500) Mx = 500;
				My = gbc.ipady+ 150;
				if (My<500) My = 500;
						
				pnlImgLine.add(pnlImg, gbc);
				pnlImgLine.repaint();
				Main_activity.this.setSize(Mx,My);
				Main_activity.this.dispose();
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
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new Insets(15,15,15,15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=0;
		pnl.add(btnOpenImg, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		pnl.add(btnGo, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		pnl.add(btnQuit, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth=0;
		pnl.add(btnFresh, gbc);
		return pnl;
	}
	private JPanel getImgLinePanel(){
		JPanel pnl = new JPanel();
//		pnl.setBackground(Color.CYAN);
		tImg = new TestImage(image);
		pnlImg = tImg;
		pnlImg.setBackground(Color.DARK_GRAY);
		JPanel pnlData1 = getData1Panel();
		JPanel pnlData2 = getData2Panel();

		pnl.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;	
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=0;
		gbc.weightx = 1;
		pnl.add(pnlData1, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = image.getWidth(this);
		gbc.ipady = image.getHeight(this);
		gbc.weightx = 0;
		pnl.add(pnlImg, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth=0;
		gbc.ipady = 0;
		gbc.ipadx = 0;
		pnl.add(pnlData2, gbc);
		return pnl;
	}
	private JPanel getImgPanel(Image image){
		JPanel pnl = new JPanel(){
			public void paint(Graphics g){
				super.paint(g);
		        
		        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
		        this.repaint();
			}
		};	
		return pnl;
	}
	private JPanel getData1Panel(){
		JPanel pnl = new JPanel();
		JLabel lbTitle = new JLabel("Data1",JLabel.CENTER);
		pnl.add(lbTitle);
		return pnl;
	}
	private JPanel getData2Panel(){
		JPanel pnl = new JPanel();
		JLabel lbTitle = new JLabel("Data2",JLabel.CENTER);
		pnl.add(lbTitle);
		return pnl;
	}
	private void frsImgLinePanel(JPanel pnl){
		pnl = new JPanel();
//		pnl.setBackground(Color.CYAN);
		tImg = new TestImage(image);
		pnlImg = tImg;
		pnlImg.setBackground(Color.DARK_GRAY);
		JPanel pnlData1 = getData1Panel();
		JPanel pnlData2 = getData2Panel();

		pnl.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;	
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=0;
		gbc.weightx = 1;
		pnl.add(pnlData1, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = image.getWidth(this);
		gbc.ipady = image.getHeight(this);
		gbc.weightx = 0;
		pnl.add(pnlImg, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth=0;
		gbc.ipady = 0;
		gbc.ipadx = 0;
		pnl.add(pnlData2, gbc);
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
