# Software Design & Technical Documentation
## Food Delivery Application (`food_delivery`)

---

### 1. System Overview & Requirement Specifications (SRS)

- **Project Title:** FoodZone Online Food Delivery System
- **Architecture Style:** Model-View-Controller (MVC) Monolith
- **Runtime Environment:** Java Enterprise Edition (Java EE), Apache Tomcat 9.0+, MySQL Server 8.0+

#### Role-Based Access Matrix

| Role (`enum`) | Permissions & System Scope |
| :--- | :--- |
| **`CUSTOMER`** | Register, log in, browse restaurants/menus, add items to cart, place orders, view order confirmations with visual feedback. |
| **`RESTURANT OWNER`** | Access live kitchen dashboard, monitor incoming orders, update live order dispatch statuses, add new menu items. |
| **`ADMIN`** | System-wide oversight, user account management, platform configurations, system diagnostics. |
| **`DELIVERY PARTNER`** | View allocated delivery orders, navigate drop-off locations, update delivery fulfillment statuses. |

---

### 2. Technical Architecture & Component Interaction

```text
       +-------------------------------------------------------------+
       |                        Browser Layer                        |
       |  (HTML5, CSS3, JavaScript, SweetAlert2, Canvas-Confetti)    |
       +------------------------------+------------------------------+
                                      | HTTP POST / GET
                                      v
       +-------------------------------------------------------------+
       |                      Controller Layer                       |
       |     (Servlets: LoginServlet, RegisterServlet, Cart, etc.)   |
       +------------------------------+------------------------------+
                                      | Java Calls
                                      v
       +-------------------------------------------------------------+
       |                   Data Access Layer (DAO)                   |
       |          (UserDAO, MenuDAO, OrderDAO, OrderItemDAO)         |
       +------------------------------+------------------------------+
                                      | JDBC Connection Pool
                                      v
       +-------------------------------------------------------------+
       |                   Relational Database Tier                  |
       |                (MySQL Database: food_delivery)              |
       +-------------------------------------------------------------+
       
   3. Database Schema & Data DictionaryDatabase 
   Name: food_delivery
   A. users TableStores authentication credentials and account roles.Column NameData TypeConstraintsDescriptionidusersINTPrimary Key, Auto IncrementUnique user identifierusernameVARCHAR(100)Unique, Not NullAccount handlepasswordVARCHAR(255)Not NullBCrypt hashed passwordemailVARCHAR(150)Unique, Not NullUser communication emailmobileVARCHAR(15)Not NullContact phone numberaddressTEXTNot NullDefault physical delivery addressroleENUM(...)Not Null'CUSTOMER', 'ADMIN', 'RESTURANT OWNER', 'DELIVERY PARTNER'created_dateDATETIMEDefault Current TimestampAccount creation recordlast_login_dateDATETIMENullableMost recent successful authenticationB. orders TableTracks customer purchase summaries.Column NameData TypeConstraintsDescriptionorderIdINTPrimary Key, Auto IncrementUnique purchase identifieruserIdINTForeign Key (users.idusers)Purchasing customer IDtotalAmountDECIMAL(10,2)Not NullTotal order amountorderStatusVARCHAR(50)Default 'PENDING''PENDING', 'PREPARING', 'OUT_FOR_DELIVERY', 'DELIVERED'orderDateDATETIMEDefault Current TimestampTimestamp when order was placedC. orderitem TableMaintains granular dish records per order.Column NameData TypeConstraintsDescriptionorderItemIdINTPrimary Key, Auto IncrementUnique item entry IDorderIdINTForeign Key (orders.orderId)Reference to parent ordermenuIdINTForeign Key (menu.menuId)Ordered dish IDquantityINTNot NullNumber of units orderedpriceDECIMAL(10,2)Not NullPrice snapshot at time of checkout4. Endpoints & Controller SpecificationsPlaintext+---------------------------------------------------------------------------------------------------------+
   
| URI / Endpoint Mapping      | Method | Trigger Event     | Redirect Parameters / Target Target          |
+-----------------------------+--------+-------------------+----------------------------------------------+
| /callRegisterServlet        | POST   | User Registration | ?status=registered (Success)                 |
|                             |        |                   | ?status=failed (DB Duplicate Error)          |
+-----------------------------+--------+-------------------+----------------------------------------------+
| /callLoginServlet           | POST   | Authentication    | /callResturantServlet?login=success (Cust.)  |
|                             |        |                   | /restaurantDashboard.jsp?login=success (Rest)|
|                             |        |                   | ?error=invalid_password (Password mismatch)  |
|                             |        |                   | ?error=user_not_found (Unknown username)     |
+-----------------------------+--------+-------------------+----------------------------------------------+
| /updateOrderStatusServlet   | POST   | Kitchen Actions   | HTTP 200 (Status: PENDING/PREPARING/DELIVERED|
+-----------------------------+--------+-------------------+----------------------------------------------+
| /logoutServlet              | GET    | Session Destroy   | /login.html?status=logout                    |
+-----------------------------+--------+-------------------+----------------------------------------------+
5. Deployment, Configuration & MaintenanceDatabase Connectivity Setup (DBConnection.java)Javapackage com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "your_mysql_password";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
Database Testing & Reset ProcedureSQLSET FOREIGN_KEY_CHECKS = 0;

DELETE FROM orderitem;
DELETE FROM orders;
DELETE FROM users;

ALTER TABLE orderitem AUTO_INCREMENT = 1;
ALTER TABLE orders AUTO_INCREMENT = 1;
ALTER TABLE users AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 1;
       
       
       
       
       
       