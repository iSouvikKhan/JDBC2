package Prepared_Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import JDBC_Util_Package.JDBCUtil;

public class Delete {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	
	public void doDelete() throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the ID of the column, which you want to DELETE :");
		int id = scanner.nextInt();
		
		String SqlDeleteQuery = "delete from student where sid = ?";
		
		try
		{
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
			{
				pstmt = connection.prepareStatement(SqlDeleteQuery);
				if (pstmt != null)
				{
					pstmt.setInt(1, id);
					int noOfRows = pstmt.executeUpdate();
					System.out.println("No of rows deleted is :: " + noOfRows);
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

