package Prepared_Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import JDBC_Util_Package.JDBCUtil;

public class ReadOne {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet resultSet = null;
	
	public void getReadOne() throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the sid :");
		int id = scanner.nextInt();

		String sqlSelectQuery = "select sid,sname,sage,saddress from student where sid=?";

		try
		{
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);
			if (pstmt != null)
			{
				pstmt.setInt(1, id);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null)
			{
				if (resultSet.next())
				{
					System.out.println("ID\tNAME\tAGE\tADDRESS");
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)+ "\t" + resultSet.getString(4));
				}
				else
				{
					System.out.println("Record not available for the given id:: " + id);
				}
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.closeConnection(resultSet, pstmt, connection);
			System.out.println("Prepared Statement");
		}
	}
}
