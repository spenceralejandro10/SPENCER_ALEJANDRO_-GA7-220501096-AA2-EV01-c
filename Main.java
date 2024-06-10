package tareaSena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/floristeria";
    private static final String USER = "root"; // Tu nombre de usuario de MySQL
    private static final String PASSWORD = ""; // Tu contraseña de MySQL

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");

            // Consulta SQL para obtener información de la tabla de productos
            String consulta = "SELECT * FROM productos";
            statement = connection.prepareStatement(consulta);
            resultSet = statement.executeQuery();

            // Mostrar resultados en la consola
            System.out.println("Datos de la tabla 'productos':");
            System.out.println("ID\tNombre\t\tDescripción\tPrecio\tStock");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double precio = resultSet.getDouble("precio");
                int stock = resultSet.getInt("stock");

                // Mostrar los datos en la consola
                System.out.println(id + "\t" + nombre + "\t\t" + descripcion + "\t" + precio + "\t" + stock);
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } finally {
            try {
                // Cerrar recursos
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
