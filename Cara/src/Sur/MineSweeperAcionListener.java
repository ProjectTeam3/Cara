package com.alex.MineSweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperActionListener  implements ActionListener{
	private MineSweeper mineSweeper;
	private MineSweeperHelper mineSweeperHelper;
	
	public MineSweeperActionListener(MineSweeper mineSweeper, MineSweeperHelper mineSweeperHelper){
		this.mineSweeper=mineSweeper;
		this.mineSweeperHelper=mineSweeperHelper;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("退出(x)")){
			System.exit(0);
		}
		if(e.getActionCommand().equals("新游戏(n)")){
			mineSweeperHelper.newGame(mineSweeper.rows, mineSweeper.columns);
			return;
		}
		if(e.getActionCommand().equals("小型(s) (8x8,10 mines)")){
			int previousRows = mineSweeper.rows;
			int previousColumns = mineSweeper.columns;
			mineSweeper.rows=8;
			mineSweeper.columns=8;
			mineSweeper.numberOfMines=10;
			mineSweeperHelper.newGame(previousRows, previousColumns);
			return;
		}
		if(e.getActionCommand().equals("中型(m) (16x16, 40 mines)")){
			int previousRows = mineSweeper.rows;
			int previousColumns = mineSweeper.columns;
			mineSweeper.rows=16;
			mineSweeper.columns=16;
			mineSweeper.numberOfMines=40;
			mineSweeperHelper.newGame(previousRows, previousColumns);
			return;
		}
		if(e.getActionCommand().equals("大型(l) (16x32, 60 mines)")){
			int previousRows = mineSweeper.rows;
			int previousColumns = mineSweeper.columns;
			mineSweeper.rows=16;
			mineSweeper.columns=32;
			mineSweeper.numberOfMines=60;
			mineSweeperHelper.newGame(previousRows, previousColumns);
			return;
		}
	}
}
