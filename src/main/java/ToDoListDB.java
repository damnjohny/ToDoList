import java.sql.*;

public class ToDoListDB {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mydbtest?autoReconnect=true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Alexnik_11";

    public static Connection connection;
    public static Driver driver;
    public static Statement statement;

    public static void connectToDB() {
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.out.println("Can't register DB driver!");
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            if (!statement.isClosed()) System.out.println("Successful connection to Data Base!");

        } catch (SQLException throwables) {
            System.out.println("Can't connect to DataBase!");
        }
    }

    public static void addTask(String task) {
        String sql = "insert into tasks (task) value ('" + task + "')";
        try {
            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println("Adding task error!");
            e.printStackTrace();
        }
    }

    public static void deleteTask(String task) {
        String sql = "delete from tasks where task = " + "'" + task + "'";
        try {
            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println("Error when deleting task");
        }
    }

    public static void deleteAllTasks() {
        try {
            statement.execute("delete from tasks");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editTask(String oldTask, String newTask) {
        String sql = "update tasks set task = " + "'" + oldTask + "'" + " where task = " + "'" + newTask + "'";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        connectToDB();
//        addTask("test");
//        deleteTask("wash the car");
        editTask("java", "kotlin");

    }

}
