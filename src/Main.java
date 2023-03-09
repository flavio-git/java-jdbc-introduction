import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/alura";
        Properties props = new Properties();
        props.setProperty("user", "flavio");
        props.setProperty("password", "flaviodb");
        String query = "SELECT * FROM cars WHERE make = 'Audi';";

        Connection conn = DriverManager.getConnection(url, props);
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        // getting metadata
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        System.out.println("Cars Tables\n");

        // show columns name
        for (int i = 1; i <= numberOfColumns; i++) {
            System.out.printf("%-20s\t", metaData.getColumnName(i).toUpperCase());
        }
        System.out.println();

        // getting registers
        while (result.next()){
            int id = result.getInt("id");
            System.out.printf("%-20d\t", id);
            String make = result.getString("make");
            System.out.printf("%-20s\t", make);
            String model = result.getString("model");
            System.out.printf("%-20s\t", model);
            String year = result.getString("year");
            System.out.printf("%-20s\t", year);
            double price = result.getDouble("price");
            System.out.printf("%-20.2f\t", price);
            System.out.println();
        }
        conn.close();
    }
}