package DBase;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

//import javax.swing.text.DateFormatter;

import java.sql.Statement;
@SuppressWarnings("serial")
public class BorrowBook extends JFrame
{		
	static final String DATABASE_URL ="jdbc:mysql://localhost/Library";
	static final String USERNAME ="root";
	static final String PASSWORD ="root";
	String driver = "org.gjt.mm.mysql.Driver";
	private JTextArea queryArea;
	private JTextArea queryArea1;
	private JTextArea resultArea;	
	JComboBox oper,oper1;
	String que,que1,sql,sql0;String c;String is;
	Connection conntion=null;
	Connection conntion_update=null;
	ResultSet rs=null;
	Statement stmt;
	String limits;
	public BorrowBook() 
	{  
		super( "借阅图书" );
		try{
			Class.forName(driver).newInstance();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());

		}
		try {
			conntion=DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
			stmt=conntion.createStatement();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String operater[]={"ReaderID"};
		String operater1[]={"ISBN","Title"};
		oper=new JComboBox(operater);
		oper1=new JComboBox(operater1);
		queryArea = new JTextArea(1,100);
		queryArea.setWrapStyleWord( true );
		queryArea.setLineWrap( true );
		queryArea1 = new JTextArea(1,100);
		queryArea1.setWrapStyleWord( true );
		queryArea1.setLineWrap( true );
		resultArea= new JTextArea(1,100);
		resultArea.setWrapStyleWord( true );
		resultArea.setLineWrap( true );
		Icon bug=new ImageIcon(getClass().getResource("1.jpg"));
		JLabel Label1=new JLabel();
		Label1.setIcon(bug);
		Label1.setText("(∩_∩) (∩_∩) 欢迎借书 (∩_∩) (∩_∩)");
		Label1.setHorizontalTextPosition(NORMAL);
		JScrollPane scrollPane = new JScrollPane( queryArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		JScrollPane scrollPane1 = new JScrollPane( queryArea1,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		JButton submitButton = new JButton("借阅");
		Box boxNorth = Box.createHorizontalBox();
		boxNorth.add(oper);
		boxNorth.add( scrollPane );
		boxNorth.add(oper1);
		boxNorth.add( scrollPane1 );
		boxNorth.add( submitButton );
		add(Label1,BorderLayout.NORTH);
		add( boxNorth, BorderLayout.SOUTH);
		add(resultArea,BorderLayout.CENTER);
		submitButton.addActionListener( 

				new ActionListener() {


					public void actionPerformed( ActionEvent event )
					{
						que=queryArea.getText();
						sql0="SELECT * FROM Reader where ReaderID='"+que+"'";
						try {
							rs=stmt.executeQuery(sql0);
						} catch (SQLException e) {							
							e.printStackTrace();
						}
						try {
							if(rs.next()){
								 limits =rs.getString("Limits");
								 
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (limits=="6"){
							
							String str="您所借的书数量 已经超过 你的借书局限数,请先还书再借书！";
							resultArea.setText( str );
							}
						else{
						c=(String) oper1.getSelectedItem();
						if(c.equals ("ISBN"))
						{
							ResultSet rs1=null;
							que1=queryArea1.getText();

							sql="SELECT * FROM Record where ISBN='"+que1+"'";
							try {
								rs=stmt.executeQuery(sql);
							} catch (SQLException e) {							
								e.printStackTrace();
							}
							try {
								if(rs.next()){

									String ReturnDate=rs.getString("ReturnDatedata");
									System.out.println("该书已被借出，归还时间 "+ReturnDate+";");
									String str="该书已被借出，归还时间 "+ReturnDate+";";
									resultArea.setText( str );
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							try {
								if(!rs.next())
								{									
									String suc=" (∩_∩) 借阅成功 (∩_∩)";
									resultArea.setText( suc );

									sql="select RecordID from Record where RecordID = (select max(RecordID) from Record);";
									rs1=stmt.executeQuery(sql);
									if(rs1.next())
									{
										//Date d=new Date();//获取时间
										//bodate=d.toLocaleString();//借书时间等于系统当前时间 
										
										int RecordID=rs1.getInt("RecordID");
										RecordID++;
										sql="insert into Record(RecordID,ISBN,ReaderID,BorrowingDate,ReturnDatedata) values ('"+RecordID+"','"+que1+"','"+que+"','2011-5-3','2011-6-3');";
										stmt.executeUpdate(sql);
									}
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}


							try {	    
								rs.close();
								rs1.close();
								stmt.close();
								conntion.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

						if(c.equals("Title"))
						{
							que1=queryArea1.getText();
							ResultSet rs1=null;
							String sqlisbn="select * from Books where Tile='"+que1+"'";
							try {
								rs1=stmt.executeQuery(sqlisbn);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							try {
								if(rs1.next()){
									is=rs1.getString("ISBN");
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							sql="SELECT * FROM Record where ISBN='"+is+"'";
							try {
								rs=stmt.executeQuery(sql);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							try {
								if(rs.next()){
									String ReturnDate=rs.getString("ReturnDatedata");
									System.out.println("该书已被借出，归还时间 "+ReturnDate+";");
									String str="该书已被借出，归还时间 "+ReturnDate+";";
									resultArea.setText( str );
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							try {
								if(!rs.next())
								{
									String suc=" (∩_∩) 借阅成功 (∩_∩)";
									resultArea.setText( suc );
									ResultSet rs2=null;
									sql="RecordID from Record where RecordID = (select max(recordid) from record);";
									rs2=stmt.executeQuery(sql);
									if(rs2.next())
									{
										int RecordID=rs2.getInt("RecordID");
										RecordID++;
										sql="insert into Record(RecordID,ISBN,ReaderID,BorrowingDate,ReturnDatedata) values ('"+RecordID+"','"+is+"','"+que+"','2011-5-3','2011-6-3');";
										stmt.executeUpdate(sql);
									}
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
							try {	    
								rs.close();
								rs1.close();
								stmt.close();
								conntion.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

					}
						
					} 
					}
			

		); 

		setSize( 500, 400); 
		setLocation(300,100);
		setVisible( true );  

		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		addWindowListener(
				new WindowAdapter() 
				{

					public void windowClosed( WindowEvent event )
					{
						System.exit( 0 );
					} 
				} 
		);   


	} 

}


