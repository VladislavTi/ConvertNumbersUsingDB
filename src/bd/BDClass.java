package bd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BDClass {

	
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	
	public static void create() throws ClassNotFoundException, SQLException {
		BDClass.Connect();
		BDClass.CreateDB();
	}
	
	
	// Connection to DB
	public static void Connect() throws ClassNotFoundException, SQLException 
	   {
		   conn = null;
		   Class.forName("org.sqlite.JDBC");
		   conn = DriverManager.getConnection("jdbc:sqlite:TEST.s3db");
		   
		   System.out.println("BD connected");
	   }
	
	// Table creating
	public static void CreateDB() throws ClassNotFoundException, SQLException
	   {
		statmt = conn.createStatement();
		statmt.execute("CREATE TABLE if not exists 'tableNumber' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'words' text, 'numbers' INT);");
		
		System.out.println("Table already exist");
	   }
	
	// Filling table
	public static void WriteDB(String inputString, int number) throws SQLException
	{
		statmt.execute(String.format("INSERT INTO 'tableNumber' ('words', 'numbers') VALUES ('%s', %s); ", inputString, number));
	}
	
	// Read DB, search and delete min value
	public static void ReadDB() throws ClassNotFoundException
	   {
		
		//search id with min value
		try {
			resSet = statmt.executeQuery("SELECT * FROM tableNumber WHERE numbers=(SELECT MIN(numbers) FROM tableNumber) LIMIT 1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
		//getting value of id
		String handwriting = new String();
		try {
			handwriting = resSet.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
		//delete record (find by id) from table. 
		try {
			resSet = statmt.executeQuery(String.format("DELETE FROM 'tableNumber' WHERE id = '%s'", handwriting));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
	    }
	
		// Close connection to DB
		public static void CloseDB() throws ClassNotFoundException, SQLException
		   {
			conn.close();
			statmt.close();
			resSet.close();
			
			System.out.println("DB connect closed");
		   }

}