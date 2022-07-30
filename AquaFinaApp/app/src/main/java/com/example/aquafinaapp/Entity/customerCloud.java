package com.example.aquafinaapp.Entity;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class customerCloud {
    Connection conn;
    String uname, pass, ip, port, database;


    public static final String databaseName = "CSIT321";
    public static final String url = "jdbc:mysql://aquafina-db.ceol90zmycsj.us-west-1.rds.amazonaws.com:3306/" + databaseName;
    public static final String uName = "admin";
    public static final String uPass = "password";


//    @SuppressLint("NewApi")
//    public static Connection connectionClass() {


        //================================================================================================================================================================================================
//
//        if (System.getenv("aquafina-db.ceol90zmycsj.us-west-1.rds.amazonaws.com") != null) {
//            try {
//                Class.forName("org.mysql.Driver");
//                String database = "CSIT321";
//                String uname = "admin";
//                String pass = "password";
//                String hostname = "aquafina-db.ceol90zmycsj.us-west-1.rds.amazonaws.com";
//                String port = "3306";
//                String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?user=" + uname + "&password=" + pass;
//                System.out.println("Getting remote connection with connection string from environment variables.");
////                Log.e("Getting remote connection with connection string from environment variables.");
//                Connection con = DriverManager.getConnection(jdbcUrl);
//                System.out.println("Remote connection successful.");
////                Log.e("Remote connection successful.");
//                return con;
//            } catch (ClassNotFoundException e) {
//                Log.e("db connection failed!!!", e.getMessage());
//            }
//            catch (SQLException e) {
//                Log.e("sql not found!!!!!", e.getMessage());
//            }
//        }
//        return null;


        //================================================================================================================================================================================================
//
//        ip = "192.168.56.1/32";
//        database = "CSIT321";
//        uname = "admin";
//        pass = "password";
//        port = "3306";
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        Connection connection = null;
//
//        String ConnectionURL1 = null;
//        String ConnectionURL = null;
//
//        try{
//            Class.forName("org.mysql.Driver");
//            ConnectionURL = "jdbc:mysql://" + ip + ":" + port + "/" + "databaseName=" + database + "?user=" + uname + "&password=" + pass;
////            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
//            ConnectionURL1 = ip + ":" + port + ";" + "databaseName=" + database + ";user=" + uname + ":password=" + pass + ";";
//            connection = DriverManager.getConnection(ConnectionURL);
//            System.out.println("database connect successfully!!!!!!!!!!!!!");
//        }catch(Exception ex) {
//            Log.e("Error", ex.getMessage());
//            System.out.println("database connect failllllllll!!!!!!!!!!!!!");
//        }
//            return connection;
//    }



    public static boolean getCustomerLogin(String userName, String password) {
        new Thread(() -> {

            StringBuilder cusInfo = new StringBuilder();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, uName, uPass);
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("SELECT cUsername, cPassword FROM customer WHERE cUsername= '" + userName + "' AND cPassword='" + password + "';");

//                statement.execute("SELECT cUsername, cPassword FROM customer WHERE cUsername= '" + userName + "' AND cPassword='" + password + "';";

                while (rs.next()) {
                    cusInfo.append(rs.getString(0)).append("").append(rs.getString(1));
                }


                connection.close();

            } catch (Exception e) {
                Log.e("Error getting info", e.getMessage());
            }
        }).start();

        return true;

    }



//
//    public boolean getCustomerLogin(String userName, String password) {
//
////        String rsUserName = "";
////        String rsPassword = "";
//
//        Connection con;
//
//        StringBuilder resultText = new StringBuilder();
//
//        try {
//            con = connectionClass();
//
//            if (con != null) {
//                String query = "SELECT cUsername, cPassword FROM customer WHERE cUsername= '" + userName + "' AND cPassword='" + password + "';";
//                Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery(query);
//
//                while (rs.next()) {
//                    resultText.append(rs.getString(0)).append(rs.getString(1));
////                    rsUserName = rs.getString(0);
////                    rsPassword = rs.getString(1);
//                    System.out.println("tttttttttttttttttteeeeeeeeeeeeeeesssssssssssssstttttttttttttt:" + resultText);
//                }
////                totalCredentials = rsUserName + rsPassword;
//            }
//
//        }catch (Exception ex) {
//            Log.e("Error", ex.getMessage());
//        }
//
//        return false;
//    }




}
