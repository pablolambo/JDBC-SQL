package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.List;

public class QueryExecutor {
    private static final Logger logger = LogManager.getLogger(QueryExecutor.class);
    static long startTime;
    static long endTime;

    // SELECT
    public static void executeSelect(String selectQuery) {
        System.out.println("Executing query: " + selectQuery);
        startTime = System.currentTimeMillis();
        try(Connection connection = DbConnector.connect();
            PreparedStatement preStatement = connection.prepareStatement(selectQuery))
        {
            ResultSet resultSet = preStatement.executeQuery();
            while(resultSet.next()){
                logger.info(resultSet.getString("email"));
            }
            System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // INSERT
    public static void executeInsert(String insertQuery) {
        System.out.println("Executing query: " + insertQuery);
        String firstName = Input.getFirstName();
        String lastName = Input.getLastName();

        try(Connection connection = DbConnector.connect();
            PreparedStatement preStatement = connection.prepareStatement(insertQuery))
        {
            preStatement.setString(1, firstName);
            preStatement.setString(2, lastName);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE
    public static void executeDelete(String deleteQuery) {
        System.out.println("Executing query: " + deleteQuery);
        int id = Input.getId();

        try(Connection connection = DbConnector.connect();
            PreparedStatement preStatement = connection.prepareStatement(deleteQuery))
        {
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE in one transaction
    public static void executeUpdateTransaction(List<String> queries, Connection connection){
        try {
            connection.setAutoCommit(false);
            queries.forEach(query -> executeUpdate(query));
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error: ",e);
        }
    }

    public static void executeUpdate(String updateQuery){
        System.out.println("Executing query: " + updateQuery);
        try(Connection connection = DbConnector.connect();
            PreparedStatement preStatement = connection.prepareStatement(updateQuery))
        {
            preStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
