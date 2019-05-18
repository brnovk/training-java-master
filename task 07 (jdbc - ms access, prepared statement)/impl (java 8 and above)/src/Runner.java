import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import by.training.beans.Segment;

public class Runner {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		final int INDEX_LENGTH = 1;
		final int INDEX_COUNT = 2;

		final String DB_CLASS = "net.ucanaccess.jdbc.UcanaccessDriver";
		final String DB_URL = "jdbc:ucanaccess://db/segments.mdb";

		final String QUERY_FETCH_COORDINATES = 
				  "SELECT abs(int((x1-x2)+0.5)) AS len, COUNT(*) AS num "
				+ "FROM Coordinates "
				+ "GROUP BY abs(int((x1-x2)+0.5)) "
				+ "ORDER BY abs(int((x1-x2)+0.5)) DESC";
		final String QUERY_FETCH_FREQUENCIES = "SELECT * "
											 + "FROM Frequencies " 
											 + "WHERE len > num;";
		final String QUERY_FILLING_FREQUENCIES = "INSERT INTO Frequencies "
											   + "VALUES (?,?)";
		final String QUERY_CLEAR_FREQUENCIES = "DELETE * "
											 + "FROM Frequencies";

		List<Segment> segments = new ArrayList<Segment>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DB_CLASS);
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
			statement.execute(QUERY_CLEAR_FREQUENCIES);
			resultSet = statement.executeQuery(QUERY_FETCH_COORDINATES);
			preparedStatement = 
					connection.prepareStatement(QUERY_FILLING_FREQUENCIES);

			while (resultSet.next()) {
				segments.add(new Segment(resultSet.getInt(INDEX_LENGTH),
						resultSet.getInt(INDEX_COUNT)));
			}

			for (Segment segment : segments) {
				preparedStatement.setInt(INDEX_LENGTH, segment.getLength());
				preparedStatement.setInt(INDEX_COUNT, segment.getCount());
				preparedStatement.executeUpdate();
			}
			resultSet = statement.executeQuery(QUERY_FETCH_FREQUENCIES);

			System.out.println("len;num");
			while (resultSet.next()) {
				System.out.format("%d;%d\n", resultSet.getInt(INDEX_LENGTH),
											 resultSet.getInt(INDEX_COUNT));
			}

		} catch (ClassNotFoundException | SQLException ex) {
			System.err.println(ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
	}
}
