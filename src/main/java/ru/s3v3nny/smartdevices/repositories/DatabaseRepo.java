package ru.s3v3nny.smartdevices.repositories;

import ru.s3v3nny.smartdevices.models.RelevantIndicator;
import ru.s3v3nny.smartdevices.models.Indicator;
import ru.s3v3nny.smartdevices.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepo {

    private static DatabaseRepo instance;

    public static DatabaseRepo getInstance() {
        if (instance == null) {
            instance = new DatabaseRepo();
        }

        return instance;
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://sloth-1.suslovd.ru:9132/smart_devices";
        String user = "user";
        String pass = "pass";
        return DriverManager.getConnection(url, user, pass);
    }

    public void addUser(User user) throws SQLException {
        Connection connection = getNewConnection();

        String update = "INSERT INTO users VALUES (?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, user.getUser_id());
        statement.setString(2, user.getFirst_name());
        statement.setString(3, user.getSecond_name());

        statement.executeUpdate();

        connection.close();
    }

    public void updateIndicators(Indicator indicator) throws SQLException {
        Connection connection = getNewConnection();

        String update = "INSERT INTO indicators (user_id, steps, pulse, sleep, created_at) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, indicator.getUser_id());
        statement.setInt(2, indicator.getSteps());
        statement.setInt(3, indicator.getPulse());
        statement.setString(4, indicator.getSleep());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        statement.setTimestamp(5, timestamp);

        statement.executeUpdate();

        connection.close();
    }

    public List<RelevantIndicator> getRelevantIndicators() throws SQLException {
        Connection connection = getNewConnection();

        String query = "select users.first_name, users.second_name, indicators.pulse, indicators.sleep, indicators.steps " +
                "from users " +
                "join indicators on users.user_id=indicators.user_id " +
                "where indicators.created_at = (select max(created_at) " +
                "from indicators " +
                "where user_id = users.user_id" +
                ");";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<RelevantIndicator> relevantIndicatorsList = new ArrayList<>();
        do {
            resultSet.next();
            RelevantIndicator relevantIndicator = new RelevantIndicator();
            relevantIndicator.setFirst_name(resultSet.getString("first_name"));
            relevantIndicator.setSecond_name(resultSet.getString("second_name"));
            relevantIndicator.setSteps(resultSet.getInt("steps"));
            relevantIndicator.setPulse(resultSet.getInt("pulse"));
            relevantIndicator.setSleep(resultSet.getString("sleep"));
            relevantIndicatorsList.add(relevantIndicator);
        } while(!(resultSet.isLast()));

        connection.close();

        return relevantIndicatorsList;
    }

    public boolean checkUuid(String uuid) throws SQLException {
        Connection connection = getNewConnection();

        String query = "SELECT * FROM users WHERE user_id=?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, uuid);

        ResultSet resultSet  = statement.executeQuery();

        connection.close();

        return resultSet.next();
    }
}
