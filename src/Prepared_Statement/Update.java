package Prepared_Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import JDBC_Util_Package.JDBCUtil;

public class Update {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	public void doUpdate() throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the ID of the column, whose values you want to UPDATE :");
		int id = scanner.nextInt();
		
		System.out.println("Enter the AGE :");
		int age = scanner.nextInt();

		System.out.println("Enter the NAME :");
		String name = scanner.next();

		System.out.println("Enter the ADDRESS :");
		String address = scanner.next();
		
		String sqlUpdateQuery = "update student set sname=?,sage=?,saddress=? where sid=?";

		try
		{
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
			{
				pstmt = connection.prepareStatement(sqlUpdateQuery);
				if (pstmt != null)
				{
					pstmt.setString(1, name);
					pstmt.setInt(2, age);
					pstmt.setString(3, address);
					pstmt.setInt(4, id);
					int noOfRows = pstmt.executeUpdate();
					System.out.println("No of rows updated is :: " + noOfRows);
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
			JDBCUtil.closeConnection(null, pstmt, connection);
			System.out.println("Prepared Statement");
		}
	}
}

