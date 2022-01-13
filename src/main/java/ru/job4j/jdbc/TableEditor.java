package ru.job4j.jdbc;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
        System.out.println("Соединение установлено");
    }

    public void createTable(String tableName) {
                String sql = String.format(
                        "create table if not exists %s(%s);",
                        tableName, "id serial primary key");
                executeStatement(sql);
        System.out.println("таблица создана");
    }

    public void dropTable(String tableName) {
        String sql = String.format(
        "drop table %s",
                tableName
        );
        executeStatement(sql);
        System.out.println("таблица удалена");
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add %s %s",
                tableName, columnName, type);
        executeStatement(sql);
        System.out.println("столбец добавлен");
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table %s drop %s",
                tableName, columnName);
        executeStatement(sql);
        System.out.println("столбец удален");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table %s rename %s to %s",
                tableName, columnName, newColumnName);
        executeStatement(sql);
        System.out.println("столбец переименован");
    }

    private void executeStatement(String sqlCommand) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCommand);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            System.out.println("соединение успешно закрыто");
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("app.properties");
        Properties properties = new Properties();
        properties.load(in);
        TableEditor te = new TableEditor(properties);
        te.createTable("bkz");
        System.out.println(getTableScheme(te.connection, "bkz"));
        te.addColumn("bkz", "employee", "varchar (255)");
        System.out.println(getTableScheme(te.connection, "bkz"));
        te.renameColumn("bkz", "employee", "new_employee");
        System.out.println(getTableScheme(te.connection, "bkz"));
        te.dropColumn("bkz", "new_employee");
        System.out.println(getTableScheme(te.connection, "bkz"));
        te.dropTable("bkz");
        te.close();
    }
}