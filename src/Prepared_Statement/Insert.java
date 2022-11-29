package Prepared_Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import JDBC_Util_Package.JDBCUtil;

public class Insert {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	public void doInsert() throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the AGE :");
		int age = scanner.nextInt();

		System.out.println("Enter the NAME :");
		String name = scanner.next();

		System.out.println("Enter the ADDRESS :");
		String address = scanner.next();

		String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`) values(?,?,?)";
		
		try
		{
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);
			if (pstmt != null)
			{
				pstmt.setString(1, name);
				pstmt.setInt(2, age);
				pstmt.setString(3, address);
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows Affected is :: " + rowAffected);
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
			JDBCUtil.closeConnection(null, pstmt, connection); // ResultSet , Statement , Connection , Scanner
			System.out.println("Prepared Statement");
		}
	}
}
