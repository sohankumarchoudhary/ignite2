package ignite.sampleproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ignitesql {
	Connection con = null;
	public void Getconnection()
	{
		try
		{
			Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
					
				
					
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			
			con = DriverManager.getConnection("jdbc:ignite:thin://192.168.1.176/");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void CreateTable()
	{
		try(Statement stmt = con.createStatement())
		{
			stmt.executeUpdate("create table City(" + 
		"id int PRIMARY KEY , name VARCHAR )" + 
					"WITH \"template=replicated\" ");
		
		
	
		stmt.executeUpdate("create table person(" + 
		"id int  , name VARCHAR ,city_id int , " +

		"PRIMARY KEY ((id , city_id))"+ "WITH \"backups=1 , affinityKey=city_id\""); 
		}
					
		
		catch(SQLException e)
		
		{
			e.printStackTrace();
		}
	}
	
		
	
	public void CreateIndexex()
	{
		try(Statement stmt = con.createStatement())
		{
			stmt.executeUpdate("create INDEX idx_city_name ON City (name)");
			stmt.executeUpdate("create INDEX idx_person_name ON Person(name)");
		}
catch(SQLException e)
		
		{
			e.printStackTrace();
		}
	}
	
	public void InsertData()
	{
		try(PreparedStatement stmt = con.prepareStatement("INSER INTO City(id , name, city_id) values(?,?,?)"))
		{
			stmt.setInt(1, 1);
			stmt.setString(2, "btm");
			stmt.setInt(3, 2);
			stmt.executeUpdate();
		}
catch(SQLException e)
		
		{
			e.printStackTrace();
		}
	
	try(PreparedStatement stmt = con.prepareStatement("INSER INTO Person(id , name , city_id) values(?,?,?)"))
	{
		stmt.setInt(1, 1);
		stmt.setString(2, "ayush");
		stmt.setInt(3, 3);
		stmt.executeUpdate();
	}
catch(SQLException e)
	
	{
		e.printStackTrace();
	}
}
	
public void ShowData()
{
	try(Statement stmt = con.createStatement())
	{
		try(ResultSet rs = stmt.executeQuery("SELECT p.name , c.name " +
	     "FROM Person p , City c" + "Where p.city_id = c.id"))
		{
			System.out.println("query  result");
			while(rs.next())
			{
				System.out.println(rs.getString(1) + rs.getString(2));
			}
		}
	}
		catch(SQLException e)
		
		{
			e.printStackTrace();
		}
	
				
				
	
}

public static void main (String[] args)
{
	ignitesql ob = new ignitesql();
	ob.Getconnection();
	ob.ShowData();
}
		
	}
	
		
	
		

