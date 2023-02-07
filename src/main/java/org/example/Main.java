package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String SELECT_QUERY = "SELECT email FROM public.\"user\" WHERE email LIKE 'p%'";
    private static final String INSERT_QUERY = "INSERT INTO public.\"user\" (first_name, last_name) VALUES (?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM public.\"user\" WHERE id = ?";

    private static final String UPDATE_QUERY = "UPDATE public.\"user\" SET balance = %d WHERE \"first_name\" = '%s'";


    public static void main(String[] args) {
        // QueryExecutor.executeSelect(SELECT_QUERY);
        // QueryExecutor.executeInsert(INSERT_QUERY);
        // QueryExecutor.executeDelete(DELETE_QUERY);

        String ktosUpdate = String.format(UPDATE_QUERY, 2000, "ktos");
        String ktos1Update = String.format(UPDATE_QUERY, 1000, "ktos1");

//        QueryExecutor.executeUpdate(ktos1Update);
//        QueryExecutor.executeUpdate(ktosUpdate);

        List <String> queries = Arrays.asList(ktosUpdate, ktos1Update);

        try(Connection connection = DbConnector.connect();
            ){
            QueryExecutor.executeUpdateTransaction(queries, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}