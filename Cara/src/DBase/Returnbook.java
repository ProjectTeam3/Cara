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
import java.sql.Statement;
@SuppressWarnings("serial")
public class Returnbook extends JFrame
{		
	static final String DATABASE_URL = "jdbc:mysql://localhost/library";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	String driver = "org.gjt.mm.mysql.Driver";
	private JTextArea queryArea;
	private JTextArea resultArea;	
	JComboBox box;
	String Itemchoise,sql;String choise;String isbn;
	Connection conntion=null;
	Connection conntion_update=null;
	ResultSet rs=null;
	Statement stmt;

	public Returnbook() 
	{  
		super( "还书" );
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

		String operater[]={"ISBN","Title"};
		box=new JComboBox(operater);
		queryArea = new JTextArea(1,100);
		queryArea.setWrapStyleWord( true );
		queryArea.setLineWrap( true );
		resultArea= new JTextArea(1,100);
		resultArea.setWrapStyleWord( true );
		resultArea.setLineWrap( true );
		JScrollPane scrollPane = new JScrollPane( queryArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		JButton submitButton = new JButton("归还");
		Icon bug=new ImageIcon(getClass().getResource("2.jpg"));
		JLabel Label1=new JLabel();
		Label1.setIcon(bug);
		Label1.setText("(∩_∩) (∩_∩) 欢迎还书 (∩_∩) (∩_∩)");
		Label1.setHorizontalTextPosition(NORMAL);
		Box boxNorth = Box.createHorizontalBox();
		boxNorth.add(box);
		boxNorth.add( scrollPane );
		boxNorth.add( submitButton );
		add(Label1,BorderLayout.NORTH);
		add( boxNorth, BorderLayout.SOUTH );
		add(resultArea,BorderLayout.CENTER);
		submitButton.addActionListener( 

				new ActionListener() {

					public void actionPerformed( ActionEvent event )
					{
						Itemchoise=queryArea.getText();
						choise=(String) box.getSelectedItem();
						if(choise.equals ("ISBN"))
						{
							sql="delete FROM record where ISBN='"+Itemchoise+"'";
							try {
								stmt.executeUpdate(sql);
								String str="(∩_∩) 归还成功 (∩_∩)";
								resultArea.setText( str );
							} catch (SQLException e) {							
								e.printStackTrace();
							}
							try {	    
								stmt.close();
								conntion.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if(choise.equals("Title"))
						{
							String sqlisbn="select * from books where Tile='"+Itemchoise+"'";
							try {
								rs=stmt.executeQuery(sqlisbn);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							try {
								if(rs.next()){
									isbn=rs.getString("ISBN");
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							sql="delete FROM Record where ISBN='"+isbn+"'";
							try {
								stmt.executeUpdate(sql);
								String str="(∩_∩) 归还成功 (∩_∩)";
								resultArea.setText( str );
							} catch (SQLException e) {							
								e.printStackTrace();
							}
							
							try {	    
								rs.close();
								stmt.close();
								conntion.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

					}
				}        
		); 

		setSize( 500, 400 ); 
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







