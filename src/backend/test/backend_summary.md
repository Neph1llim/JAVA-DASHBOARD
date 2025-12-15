# SUMMARY OF THE TESTING PROCESS AND DEVELOPMENT PROCESS OF BACKEND

## TABLE OF CONTENTS
All info here are from test directory of the backend.
-test.java
-DatabaseChecker.java
-TestDatabaseTables.java
-ExceptionTest.java
-ModelTest.java
-ServiceTest.java
-UtilsTest.java
-CompleteDaoTest.java

## OVERALL SUMMARY
Plinanout ko muna ang development structure. (Check na lang sa backend folder, ayan ang structure)
-utils layer
-services layer
-model layer
-exceptions layer
-db layer
-dao layer
    -impl (implementation layer)
    -interfaces

- **Para san ba yan?** Para madali basahin ang kada layer ng backend and maayos ang flow.
- **Pano sya nagana?** Check ang baba may nilagay ako na parang flowchart

```graphql

#BASIC DIAGRAM EXPLANATION
┌─────────────────┐
│ Java Application│
│   (Your GUI)    │
└────────┬────────┘
         │
┌────────▼────────┐
│  JDBC API       │ ← Java's database interface
│  (java.sql.*)   │
└────────┬────────┘
         │
┌────────▼────────┐
│  JDBC Driver    │ ← MySQL Connector
│  Manager        │
└────────┬────────┘
         │
┌────────▼────────┐
│    Database     │
│     (MySQL)     │
└─────────────────┘

```

```graphql

Data Flow Graph Example

1. USER ACTION
   ┌─────────────────┐
   │ Click "Login"   │
   │ Button in GUI   │
   └────────┬────────┘
            │
            ▼
2. GUI LAYER
   ┌─────────────────────────────────────┐
   │ LoginPanel.java                     │
   │ - Get email & password from fields  │
   │ - Disable button, show loading...   │
   └────────┬────────────────────────────┘
            │ authService.login(email, password)
            ▼
3. SERVICE LAYER
   ┌──────────────────────────────────────────────────────┐
   │ AuthService.login()                                  │
   │ ✓ Validate email format (ValidationUtil)            │
   │ ✓ Check if password not empty                       │
   │ ✓ Call userDao.findByEmail(email)                   │
   │ ✓ Check if user exists                              │
   │ ✓ Verify password (PasswordUtil.verify())           │
   │ ✓ Set session (SessionManager.setCurrentUser())     │
   │ ✓ Update last login (userDao.updateLastLogin())     │
   └────────┬─────────────────────────────────────────────┘
            │ userDao.findByEmail(email)
            ▼
4. DAO LAYER
   ┌──────────────────────────────────────────────────┐
   │ UserDaoImpl.findByEmail()                        │
   │ - Get database connection                        │
   │ - Prepare SQL: SELECT * FROM users WHERE email=? │
   │ - Execute query                                  │
   │ - Extract User from ResultSet                    │
   └────────┬─────────────────────────────────────────┘
            │ SQL Query
            ▼
5. DATABASE CONNECTION
   ┌────────────────────────────────────┐
   │ DatabaseConnection.getConnection() │
   │ - Return active connection         │
   └────────┬───────────────────────────┘
            │ JDBC
            ▼
6. JDBC API
   ┌────────────────────────────────────┐
   │ PreparedStatement                  │
   │ - Execute SQL query                │
   │ - Return ResultSet                 │
   └────────┬───────────────────────────┘
            │ MySQL Protocol
            ▼
7. DATABASE
   ┌────────────────────────────────────┐
   │ MySQL (note_app database)          │
   │ users table:                       │
   │ ┌────────────────────────────────┐ │
   │ │ user_id: 1                     │ │
   │ │ email: test@example.com        │ │
   │ │ username: testuser             │ │
   │ │ password_hash: $2a$12$...      │ │
   │ └────────────────────────────────┘ │
   └────────┬───────────────────────────┘
            │ Return data
            ▼
   ┌─────────────────┐
   │ User object     │
   │ populated with  │
   │ database data   │
   └────────┬────────┘
            │ Back through layers
            ▼
8. BACK TO GUI
   ┌─────────────────────────────────────┐
   │ LoginPanel.java                     │
   │ ✓ Login successful!                 │
   │ - Hide login panel                  │
   │ - Show main dashboard               │
   │ - Display welcome message           │
   └─────────────────────────────────────┘

```
## test.java
-test file lang wherein tinest and basic database connectvity. Unang test na ginawa right after ma connect ang mysql_connector (check libraries folder)
-Nag test connection, chineck and database schema na pinag coconectan, required na tables, tsaka verification ng database setup kung tama.

## DatabaseChecker.java
-check ulti ng database if nag istore ba ang mga ininput while testing (turns out yes, run mo na lang ung file para makita mo output. If ayaw, install ka ng XAMPP and lagay ka ng mysql-connector. Sa xampp paste mo ung schema.sql sa loob ng db.

## TestDatabasetables.java
-test show ng mga tables kung tama ung mga attributes para ma ensure na tama rin ang pupuntahan kapag tuampak na sa mga layers.

## ExceptionTest.java
-tinetest ung mga files sa exception folder
-from authentication to validation sa inputs ng user

## ModelTest.java
-lahat ng galign sa GUI ay dine muna pupunta.
-getter and setter nandito rin
-after nan punta ng service layer to catch and verify bago dalhin sa service layer then sa DAO

## ServiceTest.java
-test lang na nagana lahat ng nasa service folder
-ensuring that model can transfer data to the service ng maayos pa punta sa DAO then eventually lalagay sa database

## UtilsTest.java
-Password, validation, session, security
-logic ng validation andito, logic gn security andito rin na eventually binabasa naman ng exception
-password hashing (salt algorithm, search na lang if d alam nasa internet naman yan. Basta nag gegenerate yan ng unicode)

## CompleteDaoTest.java
-testing lahat ng nasa DAO or Data Access Object layer.
-dito muna dadalhin then tsaka pupunta ng database ang mga data to distribute sa kanya-kanyang tables.




