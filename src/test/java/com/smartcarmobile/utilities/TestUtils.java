package com.smartcarmobile.utilities;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.smartcarmobile.utilities.DriverManager.destroyDatabaseConnection;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creating a shared user account using the API
     */
    protected static String bearerTokenAdmin = PropertyManager.getProperty("bearer.token.admin");
    private static final String baseURI = PropertyManager.getProperty("base.url");

    public void sharedUserAccountCreation(String user_type, int car_id, String access_level, String phone_number, String first_name, String last_name,
                                          String postal_code, String country, String state, String city, String address) throws IOException, InterruptedException {

        switch (new GlobalParams().getPlatformName()) {
            case "android":
                String finalEmailAndroid = PropertyManager.getProperty("shared.user.email.android");
                String passwordAndroid = PropertyManager.getProperty("shared.user.password");
                String confirmPasswordAndroid = PropertyManager.getProperty("shared.user.password");
                createAccount(finalEmailAndroid, user_type, car_id, access_level, phone_number, first_name, last_name, postal_code, country, state, city, address, passwordAndroid, confirmPasswordAndroid);
                System.out.println("Shared User Account For Android Created Successfully: " + finalEmailAndroid + " / " + passwordAndroid);
                break;

            case "ios":
                String finalEmailiOS = PropertyManager.getProperty("shared.user.email.ios");
                String passwordiOS = PropertyManager.getProperty("shared.user.password");
                String confirmPasswordiOS = PropertyManager.getProperty("shared.user.password");
                createAccount(finalEmailiOS, user_type, car_id, access_level, phone_number, first_name, last_name, postal_code, country, state, city, address, passwordiOS, confirmPasswordiOS);
                System.out.println("Shared User Account For iOS Created Successfully: " + finalEmailiOS + " / " + passwordiOS);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
        }
    }

    public void createAccount(String email, String user_type, int car_id, String access_level, String phone_number, String first_name, String last_name,
                              String postal_code, String country, String state, String city, String address, String password, String confirm_password) throws IOException, InterruptedException {

        // Invite User
        String inviteEndpoint = baseURI + "/s/user/invite";

        ObjectNode requestBodyInvite = objectMapper.createObjectNode();
        requestBodyInvite.put("email", email);
        requestBodyInvite.put("user_type", user_type);
        requestBodyInvite.put("car_id", car_id);
        requestBodyInvite.put("access_level", access_level);

        HttpRequest inviteRequest = HttpRequest.newBuilder()
                .uri(URI.create(inviteEndpoint))
                .header("Authorization", bearerTokenAdmin)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(String.valueOf(requestBodyInvite)))
                .build();

        HttpResponse<String> inviteResponse = httpClient.send(inviteRequest, BodyHandlers.ofString());

        if (inviteResponse.statusCode() != 201) {
            throw new RuntimeException("Failed to invite user: " + inviteResponse.body());
        }

        String inviteToken = objectMapper.readTree(inviteResponse.body()).get("token").asText();
        System.out.println("Invitation Created Successfully!");
        waitFor(5000);

        // Accept Invitation
        String acceptInviteEndpoint = baseURI + "/s/user/?token=" + inviteToken;

        ObjectNode requestBodyAccept = objectMapper.createObjectNode();
        requestBodyAccept.put("phone_number", phone_number);
        requestBodyAccept.put("first_name", first_name);
        requestBodyAccept.put("last_name", last_name);
        requestBodyAccept.put("postal_code", postal_code);
        requestBodyAccept.put("country", country);
        requestBodyAccept.put("state", state);
        requestBodyAccept.put("city", city);
        requestBodyAccept.put("address", address);

        HttpRequest acceptRequest = HttpRequest.newBuilder()
                .uri(URI.create(acceptInviteEndpoint))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(String.valueOf(requestBodyAccept)))
                .build();

        HttpResponse<String> acceptResponse = httpClient.send(acceptRequest, BodyHandlers.ofString());

        if (acceptResponse.statusCode() != 201) {
            throw new RuntimeException("Failed to accept invitation: " + acceptResponse.body());
        }
        System.out.println("Invitation Successfully Accepted!");
        waitFor(5000);

        // Set Password
        String setPasswordEndpoint = baseURI + "/s/user/set-password/?token=" + inviteToken;

        ObjectNode setPasswordRequestBody = objectMapper.createObjectNode();
        setPasswordRequestBody.put("password", password);
        setPasswordRequestBody.put("confirm_password", confirm_password);

        HttpRequest setPasswordRequest = HttpRequest.newBuilder()
                .uri(URI.create(setPasswordEndpoint))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(String.valueOf(setPasswordRequestBody)))
                .build();

        HttpResponse<String> setPasswordResponse = httpClient.send(setPasswordRequest, BodyHandlers.ofString());

        if (setPasswordResponse.statusCode() != 200) {
            throw new RuntimeException("Failed to set password: " + setPasswordResponse.body());
        }
        System.out.println("Password Successfully Set!");

        // Close connection
        reset();
    }


    /**
     * Update the column value for device_key using the DB and API
     */
    public void updateDeviceState() throws SQLException, ClassNotFoundException, IOException, InterruptedException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.createDatabaseConnection();
            String device_key;

            switch (new GlobalParams().getPlatformName()) {
                case "android":
                    device_key = PropertyManager.getProperty("register.device.key.android");
                    break;
                case "ios":
                    device_key = PropertyManager.getProperty("register.device.key.ios");
                    break;
                default:
                    throw new IllegalStateException("Unexpected platform: " + new GlobalParams().getPlatformName());
            }


            String selectQuery = "SELECT device_state FROM device WHERE device_key = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, device_key);
            System.out.println("Device key = " + device_key);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String device_state = resultSet.getString("device_state");
                System.out.println("Current device state = " + device_state);

                switch (device_state) {
                    case "installed":
                        deactivateDeviceState(device_key, "deactivated");
                        activateDeviceState(device_key, "activated");
                        break;
                    case "deactivated":
                        activateDeviceState(device_key, "activated");
                        break;
                    case "activated":
                        // Do nothing if already activated
                        break;
                    default:
                        // Handle unexpected device_state
                        System.out.println("Unexpected device_state: " + device_state);
                        break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;  // Rethrow the exception as declared in the method signature
        } finally {
            // Close resources in the reverse order they were opened
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            destroyDatabaseConnection();
        }
    }


    public void deactivateDeviceState(String device_key, String newState) throws
            IOException, InterruptedException {
        String deactivateDeviceEndpoint = baseURI + "/s/device/deactivate/";

        ObjectNode requestBodyDeactivate = objectMapper.createObjectNode();
        requestBodyDeactivate.put("device_key", device_key);
        HttpRequest inviteRequest = HttpRequest.newBuilder()
                .uri(URI.create(deactivateDeviceEndpoint))
                .header("Authorization", bearerTokenAdmin)
                .header("Content-Type", "application/json")
                .method("PATCH", BodyPublishers.ofString(requestBodyDeactivate.toString()))
                .build();

        HttpResponse<String> deactivateResponse = httpClient.send(inviteRequest, BodyHandlers.ofString());

        if (deactivateResponse.statusCode() != 200) {
            throw new RuntimeException("Failed to change state: " + deactivateResponse.body());
        }
        System.out.println("Changed state of device " + device_key + " to " + newState);
    }

    public void activateDeviceState(String device_key, String newState) throws
            IOException, InterruptedException {
        String activateDeviceEndpoint = baseURI + "/s/device/activate/";

        ObjectNode requestBodyActivate = objectMapper.createObjectNode();
        requestBodyActivate.put("device_key", device_key);

        HttpRequest activateRequest = HttpRequest.newBuilder()
                .uri(URI.create(activateDeviceEndpoint))
                .header("Authorization", bearerTokenAdmin)
                .header("Content-Type", "application/json")
                .method("PATCH", BodyPublishers.ofString(requestBodyActivate.toString()))
                .build();

        HttpResponse<String> activateResponse = httpClient.send(activateRequest, BodyHandlers.ofString());

        if (activateResponse.statusCode() != 200) {
            throw new RuntimeException("Failed to change state: " + activateResponse.body());
        }
        System.out.println("Changed state of device " + device_key + " to " + newState);
    }


    /**
     * Update the cellular_used column value  using the DB
     */

    private static long originalCU;
    public static void storeOriginalCellularUsage() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.createDatabaseConnection();

            // Prepare the SELECT query
            String selectQuery = "SELECT cellular_used FROM device WHERE device_key = '123456789'";
            selectStatement = connection.prepareStatement(selectQuery);
            resultSet = selectStatement.executeQuery();

            //int originalCU = 0;
            if (resultSet.next()) {
                originalCU = resultSet.getLong("cellular_used");
                System.out.println("Original Cellular Usage for 123456789 = " + originalCU);
            } else {
                System.out.println("No record found for device_key = 123456789");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (selectStatement != null) {
                selectStatement.close();
            }
            if (connection != null) {
                destroyDatabaseConnection();
            }
        }
    }

    public static void updateDeviceCellularUsage(String desiredCase) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        PreparedStatement updateStatement = null;

        try {
            // Establish database connection
            connection = DriverManager.createDatabaseConnection();

            long newCellularUsage;
            switch (desiredCase) {
                case "50":
                    newCellularUsage = 610231872;
                    break;
                case "75":
                    newCellularUsage = 810231872;
                    break;
                case "90":
                    newCellularUsage = 980231872;
                    break;
                case "100":
                    newCellularUsage = 1080231872;
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + desiredCase);
            }

            // Prepare the UPDATE query
            String updateQuery = "UPDATE device SET cellular_used = ? WHERE device_key = '123456789'";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setLong(1, newCellularUsage);
            updateStatement.executeUpdate();

            // Print the changes
            System.out.println("Updated cellular_used to -> " + newCellularUsage + " - which corresponds to " + desiredCase + "%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (updateStatement != null) {
                updateStatement.close();
            }
            if (connection != null) {
                destroyDatabaseConnection();
            }
        }
    }

    public static void restoreOriginalCellularUsage() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement updateStatement = null;

        try {
            // Establish database connection
            connection = DriverManager.createDatabaseConnection();

            // Prepare the SELECT query
            String updateQuery = "UPDATE device SET cellular_used = ? WHERE device_key = '123456789'";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setLong(1, originalCU);
            updateStatement.executeUpdate();

            System.out.println("Restored cellular_used to original value: " + originalCU);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (updateStatement != null) {
                updateStatement.close();
            }
            if (connection != null) {
                destroyDatabaseConnection();
            }
        }
    }
}