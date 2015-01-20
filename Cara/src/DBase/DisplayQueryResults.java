package DBase;

//Fig. 20.28: DisplayQueryResults.java
//Display the contents of the Authors table in the books database.
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
//import javax.swing.RowFilter;
//import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;


public class DisplayQueryResults extends JFrame 
{
static final String DATABASE_URL = "jdbc:mysql://localhost/Library";
static final String USERNAME = "root";
static final String PASSWORD = "root";
String DEFAULT_QUERY = "SELECT * FROM Books where 1='1' ";
String temp1=null;
String temp2=null;
String temp3;
String temp4;
String temp5;
String temp6;
String temp7;
private ResultSetTableModel tableModel;
private JTextArea ISBNArea;
private JTextArea TitleArea;
private JTextArea AuthorsArea;
private JTextArea PublisherArea;
private JTextArea PublicationDateArea;
private JTextArea TypeArea;
private JTextArea EditionNumberArea;
public DisplayQueryResults() 
{   
   super( "查询结果" );
   try 
   {
      // create TableModel for results of query SELECT * FROM authors
      tableModel = new ResultSetTableModel( DATABASE_URL,
         USERNAME, PASSWORD, DEFAULT_QUERY );

      // set up JTextArea in which user types queries
      EditionNumberArea = new JTextArea( 1, 9 );
      EditionNumberArea.setWrapStyleWord( true );
      EditionNumberArea.setLineWrap(true );
      ISBNArea = new JTextArea( 1, 20);
      ISBNArea.setWrapStyleWord( true );
      ISBNArea.setLineWrap( true ); 
      TitleArea = new JTextArea( 1, 3);
      TitleArea.setWrapStyleWord( true );
      TitleArea.setLineWrap( true );
      AuthorsArea = new JTextArea(1, 3 );
      AuthorsArea.setWrapStyleWord( true );
      AuthorsArea.setLineWrap( true );
      PublisherArea = new JTextArea(1, 3 );
      PublisherArea.setWrapStyleWord( true );
      PublisherArea.setLineWrap( true );
      PublicationDateArea = new JTextArea( 1, 3 );
      PublicationDateArea.setWrapStyleWord( true );
      PublicationDateArea.setLineWrap( true );
      TypeArea = new JTextArea( 1, 3 );
      TypeArea.setWrapStyleWord( true );
      TypeArea.setLineWrap( true );
      JButton ISBN = new JButton( "ISBN" );
      JButton Title = new JButton( "Title" );
      JButton Authors = new JButton( "Authors" );
      JButton Publisher = new JButton( "Publisher" );
      JButton EditionNumber = new JButton( "EditionNumber" );
      JButton PublicationDate = new JButton( "PublicationDate" );
      JButton Type = new JButton( "Type" );
      JButton submitButton = new JButton( "确定查询" );
      JButton borrowButton = new JButton( "借书" );
      JButton returnButton = new JButton( "还书" );
      JPanel boxNorth = new JPanel();
      JPanel boxNorth1 = new JPanel();
      boxNorth.add( ISBN );
      boxNorth.add( ISBNArea );
      boxNorth.add( Title );
      boxNorth.add( TitleArea );
      boxNorth.add( Authors );
      boxNorth.add( AuthorsArea );
      boxNorth.add( Publisher );
      boxNorth.add( PublisherArea );
      boxNorth1.add( EditionNumber );
      boxNorth1.add( EditionNumberArea );  
      boxNorth1.add( PublicationDate );
      boxNorth1.add( PublicationDateArea );
      boxNorth1.add( Type );
      boxNorth1.add( TypeArea );
      boxNorth1.add( submitButton );
      boxNorth1.add( borrowButton );
      boxNorth1.add( returnButton );
      JTable resultTable = new JTable( tableModel );
      add( boxNorth, BorderLayout.NORTH );
      add( boxNorth1, BorderLayout.CENTER );  
      add( new JScrollPane( resultTable ), BorderLayout.SOUTH );
      borrowButton.addActionListener(   
  	         new ActionListener() 
  	         {
  	            // pass query to table model
  	            public void actionPerformed( ActionEvent event )
  	            {
  	            	new BorrowBook();
  	            }
  	         }// end ActionListener inner class          
  	      );    
      returnButton.addActionListener(   
  	         new ActionListener() 
  	         {
  	            // pass query to table model
  	            public void actionPerformed( ActionEvent event )
  	            {
  	            	
  	            	new Returnbook();
  	            }
  	         }// end ActionListener inner class          
  	      );    
      ISBN.addActionListener( 
    	      
    	         new ActionListener() 
    	         {
    	            // pass query to table model
    	            public void actionPerformed( ActionEvent event )
    	            {
    	            	
    	            	temp1=ISBNArea.getText();
    	            	//System.out.print(temp1);
    	            }
    	         }// end ActionListener inner class          
    	      ); // end call to addActionListener
      Title.addActionListener(     	      
 	         new ActionListener() 
 	         {
 	            // pass query to table model
 	            public void actionPerformed( ActionEvent event )
 	            {
 	            	temp2=TitleArea.getText();
 	            }
 	         }// end ActionListener inner class          
 	      ); // end call to addActionListener   
    Authors.addActionListener(  
 	         new ActionListener() 
 	         {
 	            // pass query to table model
 	            public void actionPerformed( ActionEvent event )
 	            {
 	            	temp3=AuthorsArea.getText();
 	            }
 	         }// end ActionListener inner class          
 	      ); // end call to addActionListener
      Publisher.addActionListener(     	      
 	         new ActionListener() 
 	         {
 	            // pass query to table model
 	            public void actionPerformed( ActionEvent event )
 	            {
 	            	temp4=PublisherArea.getText();
 	            }
 	         }// end ActionListener inner class          
 	      ); // end call to addActionListener 
      
      EditionNumber.addActionListener( 
    	      
  	         new ActionListener() 
  	         {
  	            // pass query to table model
  	            public void actionPerformed( ActionEvent event )
  	            {
  	            	temp7=EditionNumberArea.getText();
  	            }
  	         }// end ActionListener inner class          
  	      ); // end call to addActionListener
      
      PublicationDate.addActionListener(    	      
 	         new ActionListener() 
 	         {
 	            // pass query to table model
 	            public void actionPerformed( ActionEvent event )
 	            {
 	            	temp5=PublicationDateArea.getText();
 	            }
 	         }// end ActionListener inner class          
 	      ); // end call to addActionListener
      
      Type.addActionListener(    	      
  	         new ActionListener() 
  	         {
  	            // pass query to table model
  	            public void actionPerformed( ActionEvent event )
  	            {
  	            	temp6=TypeArea.getText();
  	            }
  	         }// end ActionListener inner class          
  	      ); // end call to addActionListener   
      // create event listener for submitButton
      submitButton.addActionListener(      
         new ActionListener() 
         {
            // pass query to table model
            public void actionPerformed( ActionEvent event )
            {
               // perform a new query
               try 
               {
            	     DEFAULT_QUERY = "SELECT * FROM Books where 1='1' ";
            	   if (temp1!=""&& temp1!=null)
            	      {DEFAULT_QUERY += "and (ISBN='"+temp1+"') "; }
            	      if (temp2!="" && temp2!=null )
            	      {DEFAULT_QUERY+= "and (Title ='"+temp2+"')";}
            	      if (temp3!="" && temp3!=null)
            	      {DEFAULT_QUERY+="and (Authors ='"+temp3+"')";}
            	      if (temp4!=""&& temp4!=null )
            	      {DEFAULT_QUERY+="and (Publisher ='"+temp4+"')";}
            	      if (temp7!=""&& temp7!=null)
            	      {DEFAULT_QUERY+="and (EditionNumber ='"+temp7+"')";}
            	      if (temp5!=""&& temp5!=null)
            	      {DEFAULT_QUERY+="and (PublicationDate ='"+temp5+"')";}
            	      if (temp6!="" && temp6!=null)
            	      {DEFAULT_QUERY+="and (Type ='"+temp6+"')";}
                  tableModel.setQuery( DEFAULT_QUERY );
               } // end try
               catch ( SQLException sqlException ) 
               {
                  JOptionPane.showMessageDialog( null, 
                     sqlException.getMessage(), "Database error", 
                     JOptionPane.ERROR_MESSAGE );
                  try 
                  {
                     tableModel.setQuery( DEFAULT_QUERY );
                     //queryArea.setText( DEFAULT_QUERY );
                  } // end try
                  catch ( SQLException sqlException2 ) 
                  {
                     JOptionPane.showMessageDialog( null, 
                        sqlException2.getMessage(), "Database error", 
                        JOptionPane.ERROR_MESSAGE );
      
                     // ensure database connection is closed
                     tableModel.disconnectFromDatabase();
      
                     System.exit( 1 ); // terminate application
                  } // end inner catch                   
               } // end outer catch
            } // end actionPerformed
         }  // end ActionListener inner class          
      ); // end call to addActionListener
      setSize( 900, 550 ); // set window size
      setLocation(200,90);
      setVisible( true ); // display window  
   } // end try
   catch ( SQLException sqlException ) 
   {
      JOptionPane.showMessageDialog( null, sqlException.getMessage(), 
         "Database error", JOptionPane.ERROR_MESSAGE );
            
      // ensure database connection is closed
      tableModel.disconnectFromDatabase();
      
      System.exit( 1 ); // terminate application
   } // end catch
   setDefaultCloseOperation( DISPOSE_ON_CLOSE );
   addWindowListener(
   
      new WindowAdapter() 
      {
         // disconnect from database and exit when window has closed
         public void windowClosed( WindowEvent event )
         {
            tableModel.disconnectFromDatabase();
            System.exit( 0 );
         } // end method windowClosed
      } // end WindowAdapter inner class
   ); // end call to addWindowListener
} // end DisplayQueryResults constructor

// execute application
public static void main( String args[] ) throws ClassNotFoundException 
{
	   Class.forName("com.mysql.jdbc.Driver");
   new DisplayQueryResults();     
} // end main
} // end class DisplayQueryResults




