package Prepared_Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JDBC_Util_Package.JDBCUtil;

public class ReadAll_using_Statement {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public void getReadAll() throws SQLException {
		
		String sqlSelectQuery = "select sid,sname,sage,saddress from student";
		
		try
		{
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				statement = connection.createStatement();
			
			if(statement != null)
			{
				resultSet = statement.executeQuery(sqlSelectQuery);
				if(resultSet != null)
				{
					System.out.println("ID\tNAME\tAGE\tADDRESS");
					while (resultSet.next())
					{
						Integer id = resultSet.getInt("sid");
						String name = resultSet.getString("sname");
						Integer age = resultSet.getInt("sage");
						String address = resultSet.getString("saddress");
						System.out.println(id + "\t" + name + "\t" + age + "\t" + address);
					}
				}
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.closeConnection(resultSet, statement, connection);
			System.out.println("Prepared Statement");
		}
	}
}
