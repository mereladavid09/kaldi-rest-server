# kaldi-rest-server

This project uses Quarkus, the Supersonic Subatomic Java Framework and Mysql Database.

## Installing Quarkus
   
### Mac OS:
1. **Install Homebrew** (if not already installed):
   
   ```sh
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   
3. **Install Quarkus CLI using Homebrew** (if not already installed):
   
   ```sh
   brew install quarkusio/tap/quarkus

### Windows:
1. **Install Quarkus using PowerShell. Open PowerShell as Administrator and run** (if not already installed):
   
   ```sh
   iex "& { $(iwr https://ps.jbang.dev) } trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/" && iex "& { $(iwr https://ps.jbang.dev) } app install --fresh --force quarkus@quarkusio"

## Installing Mysql

### Mac OS:

1. **Install MySQL 8.* using Homebrew**:
   
   ```sh
     brew install mysql@8
   
3. **Start MySQL**:
   
   ```sh
     brew services start mysql@8
   
4. **Secure the MySQL installation (optional but recommended)**:
 
     ```sh
      mysql_secure_installation
     
### Windows:

1. **Download MySQL Installer from MySQL website : https://www.mysql.com/**
2. **Run the Installer and follow the prompts to install MySQL Server version 8.*.**
3. **Start MySQL Server from the MySQL Workbench or command line.**

### Additional Notes:

- **Mac OS:** By specifying `mysql@8`, Homebrew will install the latest version of MySQL 8.
- **Windows:** Ensure that during installation, you select the MySQL 8.* version.

## Running kaldi_customer_support_setup_db.sql :

### Mac OS:

1. **Open Terminal**
   
2. **Log in to MySQL**:
   **Run the following command to log in as the root user**
   ```sh
     mysql -u root -p
   
3. **Run the SQL script to create the database:**

    1. **Navigate to the directory where the `kaldi_customer_support_setup_db.sql` file is located**:
       
       ```sh
       cd /path/to/your/sql/file
       

    2. **Run the SQL script**:
       
       ```sh
       source kaldi_customer_support_setup_db.sql;
       
    **Replace /path/to/your/sql/file with the actual path to the SQL file.**
### Windows:

1. **Open Command Prompt:**
    **Press Win + R, type cmd, and press Enter.**
2. **Log in to MySQL:**
     **Run the following command to log in as the root user**:
     ```sh
     mysql -u root -p

  **Enter your MySQL root password when prompted.**
     
3. **Run the SQL script to create the database**:
   
   1. **Navigate to the directory where the `kaldi_customer_support_setup_db.sql` file is located**:
       
       ```sh
       cd C:\path\to\your\sql\file
       

  2. **Run the SQL script**:
       
       ```sh
       source kaldi_customer_support_setup_db.sql;
       
  **Replace C:\path\to\your\sql\file with the actual path to the SQL file.**

## Configuring MySQL in the Application:

**You can configure MySQL either in the application.properties file or by setting environmental variables in your IDE.**

### Option 1: Configuring application.properties:

  1. **Open the src/main/resources/application.properties file.**
  2. **Update the following properties with the user created in the       kaldi_customer_support_setup_db.sql:**
  3. **Replace {DB_USERNAME} with db user's username**
  4. **Replace {DB_PASSWORD} with db user's password**
     
     ```properties
       quarkus.datasource.db-kind=mysql
       quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/kaldi_customer_support
       quarkus.datasource.jdbc.driver=com.mysql.cj.jdbc.Driver
       quarkus.datasource.username=${DB_USERNAME}
       quarkus.datasource.password=${DB_PASSWORD}

### Option 2: Setting Environment Variables in IntelliJ IDEA:
  1. **Navigate TO RUN->EDIT CONFIGURATIONS**
  2. **In the left menu select configuration**
  3. **Under Java Options click Modify.**
  4. **Select Environment Variables**
  5. **Add DB_USERNAME and DB_PASSWORD environment variables with the database user's authentication data.**

## Running Quarkus Application in DEV MODE:

### Option 1: Run in Console/Terminal:
  1. **Open the console.**
  2. **Navigate to the project directory**
  3. **Run the following command**:
     
      ```sh
        quarkus:dev
      
### Option 2: Setting Up IntelliJ IDEA to Run the Application:
  1. **Navigate to RUN->EDIT CONFIGURATIONS**
  2. **In the top left corner click +(Add New Configuration)**
  3. **Select maven**
  4. **Under Run in the command line use the following command**
     
     ```sh
        quarkus:dev
     

## Troubleshooting
### If you encounter any issues, please check the following:
  1.**Error Messages: Review any error messages for guidance.**
