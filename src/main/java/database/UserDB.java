package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDB {
    private static Connection getConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/event_planning?useSSL=false";
            String userdb = "root";
            String passdb = "password";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userdb, passdb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Map<Integer, User> validate(String username, String pass) {
        Map<Integer, User> userList = new HashMap<>();
        User u = retrieve(username);

        if (u == null) {
            userList.put(-1, null);
        } else if (u.getPassword().equals(pass)) {
            userList.put(1, u);
        } else {
            userList.put(0, null);
        }

        return userList;
    }

    public static int create(String username, String email, String password, String mobile_no) {
        Connection con = getConnection();
        if (con == null) {
            return -2; // Connection failed
        }

        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO users(username, password, email, mobile_no) "
                    + "VALUES('" + username + "', '" + password + "', '" + email + "', '" + mobile_no + "')";
            int rowsAffected = stmt.executeUpdate(sql);

            if (rowsAffected > 0) {
                return 0; // Insert successful
            } else {
                return -1; // Insert failed
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2; // Exception occurred
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static User retrieve(String username) {
        Connection con = getConnection();
        if (con == null) {
            return null;
        }

        User u = null;

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM users WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt(1);
                String usernameT = rs.getString(2);
                String passwordT = rs.getString(3);
                String emailT = rs.getString(4);
                String mobile_noT = rs.getString(5);
                boolean adminT = rs.getBoolean(6);
                boolean event_organizerT = rs.getBoolean(7);
                boolean service_providerT = rs.getBoolean(8);

                u = new User(id, usernameT, passwordT, emailT, mobile_noT,
                        adminT, event_organizerT, service_providerT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return u;
    }

    /**
     * Retrieve a list of users from the database based on a filter.
     *
     * @param filter An integer value that specifies the filter criteria:
     *              - 0: Return all users.
     *              - 1: Return users with adminT set to true.
     *              - 2: Return users with event_organizerT set to true.
     *              - 3: Return users with service_providerT set to true.
     * @return A list of User objects that match the specified filter.
     */
    public static List<User> retrieve(int filter) {
        Connection con = getConnection();
        if (con == null) {
            return new ArrayList<>();
        }

        List<User> users = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM users";

            if (filter == 1) {
                sql += " WHERE admin = 1";
            } else if (filter == 2) {
                sql += " WHERE event_organizer = 1";
            } else if (filter == 3) {
                sql += " WHERE service_provider = 1";
            }

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                String usernameT = rs.getString(2);
                String passwordT = rs.getString(3);
                String emailT = rs.getString(4);
                String mobile_noT = rs.getString(5);
                boolean adminT = rs.getBoolean(6);
                boolean event_organizerT = rs.getBoolean(7);
                boolean service_providerT = rs.getBoolean(8);

                User u = new User(id, usernameT, passwordT, emailT, mobile_noT,
                        adminT, event_organizerT, service_providerT);

                users.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public static void deleteUser(int userId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String deleteQuery = "DELETE FROM users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public static void updateUser(int userId, String userType) {
    	System.out.println("Inside update: " + userId + " " + userType);
        Connection connection = null;
        try {
            connection = getConnection();

            int eventOrganizerValue = 0;
            int adminValue = 0;
            int serviceProviderValue = 0;

            if ("Event Organizer".equals(userType)) {
                eventOrganizerValue = 1;
            } else if ("Service Provider".equals(userType)) {
                serviceProviderValue = 1;
            } else if ("Customer".equals(userType)) {
            	eventOrganizerValue = 0;
            	adminValue = 0;
            	serviceProviderValue = 0;
            } else {
            	eventOrganizerValue = 0;
            	adminValue = 0;
            	serviceProviderValue = 0;
            }

            String updateQuery = "UPDATE users SET event_organizer = ?, admin = ?, service_provider = ? WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, eventOrganizerValue);
            preparedStatement.setInt(2, adminValue);
            preparedStatement.setInt(3, serviceProviderValue);
            preparedStatement.setInt(4, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public static int getCustomerCount() {
        Connection connection = null;
        int customers = 0;

        try {
            connection = getConnection();
            String query = "SELECT COUNT(userid) FROM users WHERE admin = 0 AND event_organizer = 0 AND service_provider = 0";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);

            // Check if the query returned any results
            if (result.next()) {
                customers = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection in a finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return customers;
    }


    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
