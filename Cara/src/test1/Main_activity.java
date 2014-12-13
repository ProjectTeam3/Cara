package test1;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main_activity extends JFrame{
	JButton btnOpenImg;
	JButton btnLeft;
	JButton btnGo;
	JButton btnRight;
	JButton btnQuit;
	JPanel pnlMain;
	JPanel pnlImgLine;
	JPanel pnlTitle;
	JPanel pnlButtons;
	public static void main(String[] args){
		new Main_activity("Test UI of Cara");		
	}
	public Main_activity(String title){
		super(title);
		this.setSize(500, 500);
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
		JLabel lbTitle = new JLabel("Cara测试界面0.03",JLabel.CENTER);
		lbTitle.setFont(new Font("黑体", Font.PLAIN, 32));
		pnl.add(lbTitle);
		return pnl;
	}
	private JPanel getButtonsPanel(){
		
		btnOpenImg = new JButton("打开");
		btnGo = new JButton("生成");
		btnLeft = new JButton("←");
		btnRight = new JButton("→");
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
		gbc.gridwidth=0;
		pnl.add(btnQuit, gbc);
		return pnl;
	}
	private JPanel getImgLinePanel(){
		JPanel pnl = new JPanel();
		JPanel pnlImg = getImgPanel();
		JPanel pnlData1 = getData1Panel();
		JPanel pnlData2 = getData2Panel();

		pnl.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;	
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx = 0;
		pnl.add(pnlData1, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		pnl.add(pnlImg, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.gridwidth=0;
		pnl.add(pnlData2, gbc);
		return pnl;
	}
	private JPanel getImgPanel(){
		JPanel pnl = new JPanel();
		JLabel lbTitle = new JLabel("Data1",JLabel.CENTER);
		pnl.add(lbTitle);
		return pnl;
	}
	private JPanel getData1Panel(){
		JPanel pnl = new JPanel();
		JLabel lbTitle = new JLabel("IMG",JLabel.CENTER);
		pnl.add(lbTitle);
		return pnl;
	}
	private JPanel getData2Panel(){
		JPanel pnl = new JPanel();
		JLabel lbTitle = new JLabel("Data2",JLabel.CENTER);
		pnl.add(lbTitle);
		return pnl;
	}
}
