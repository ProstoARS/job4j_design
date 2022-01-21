package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(a -> {
                if (!a.contains(";")) {
                    throw new IllegalArgumentException();
                }
                String[] subStr = a.split(";");
                if (subStr[0].equals(" ")
                        || subStr[0].equals("")
                        || subStr[1].equals("")
                        || subStr[1].equals(" ")
                        || subStr.length != 2) {
                    throw new IllegalArgumentException();
                }
                users.add(new User(subStr[0], subStr[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try (Statement statement = cnt.createStatement()) {
                String sql = String.format(
                        "create table if not exists users(%s, %s, %s);",
                        "id serial primary key",
                        "name text",
                        "email varchar(255)"
                );
                statement.execute(sql);
                for (User user : users) {
                    try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email)"
                            + " values (?,?)")) {
                        ps.setString(1, user.name);
                        ps.setString(2, user.email);
                        ps.execute();
                    }
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "dump.txt");
        db.save(db.load());
    }
}
