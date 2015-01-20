package com.alex.MineSweeper;

import javax.swing.Icon;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MineIcon {
	
	private static Icon mineIcon = new ImageIcon("material\\mine.png");
	private static Icon suspectIcon= new ImageIcon("material\\question.png");
	private static Icon oneIcon = new ImageIcon("material\\one.png");
	private static Icon twoIcon = new ImageIcon("material\\two.png");
	private static Icon threeIcon = new ImageIcon("material\\three.png");
	private static Icon fourIcon = new ImageIcon("material\\four.png");
	private static Icon fiveIcon = new ImageIcon("material\\five.png");
	private static Icon sixIcon = new ImageIcon("material\\six.png");
	private static Icon sevenIcon = new ImageIcon("material\\seven.png");
	private static Icon eightIcon = new ImageIcon("material\\eight.png");
	private static Image image=Toolkit.getDefaultToolkit().getImage("material\\nine.png");
	
	static Icon getSuspectIcon(){
		return suspectIcon;
	}
	
	static Icon getMineIcon(){
		return mineIcon;
	}
	
	static Icon getNumberIcon(int mineCount){
		switch(mineCount){
		case(1):return oneIcon;
		case(2):return twoIcon;
		case(3):return threeIcon;
		case(4):return fourIcon;
		case(5):return fiveIcon;
		case(6):return sixIcon;
		case(7):return sevenIcon;
		case(8):return eightIcon;
		}
		return null;
	}
	static Image getIcon(){
		return image;
	}
}
