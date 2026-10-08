# JDBC2 - Console CRUD with PreparedStatement

A small Java console application that performs CRUD operations on a MySQL `student` table using JDBC. Insert, update, delete and single-record lookup are done with parameterized `PreparedStatement` queries; listing the whole table uses a plain `Statement`.

## Features

The menu-driven `Driver` class offers five operations:

| Option | Operation | JDBC API |
| --- | --- | --- |
| 1 | Read the entire table | `Statement` |
| 2 | Read one record by ID (`sid`) | `PreparedStatement` |
| 3 | Insert a record (age, name, address) | `PreparedStatement` |
| 4 | Update a record by ID | `PreparedStatement` |
| 5 | Delete a record by ID | `PreparedStatement` |

- Before reading one record, and before and after insert, update and delete, the full table is printed so you can see the change.
- After each operation, enter `7` to run another operation or `9` to exit. Other input is rejected and you are asked again.
- `JDBCUtil` keeps the connection settings in one place and closes the `ResultSet`, statement and `Connection` after each operation.

## Tech Stack

- Java (the Eclipse project targets JavaSE-18)
- JDBC with MySQL Connector/J
- Eclipse IDE project files (`.project`, `.classpath`)

## Project Structure

```
src/
  JDBC_Util_Package/
    JDBCUtil.java                 # opens and closes the database connection
  Prepared_Statement/
    Driver.java                   # main class, console menu
    ReadAll_using_Statement.java  # list all students (Statement)
    ReadOne.java                  # find a student by sid
    Insert.java                   # add a student
    Update.java                   # update a student by sid
    Delete.java                   # delete a student by sid
bin/                              # compiled classes produced by Eclipse
```

The `bin/` folder also contains compiled classes from older `CRUD` and `CRUD_Through_Console` packages that have no source in this repository.

## Prerequisites

- JDK 18 or later
- A running MySQL server
- The MySQL Connector/J JAR (the Eclipse project expects it as a user library named `MySQLJAR`)

## Database Setup

The code connects to `jdbc:mysql://localhost:3306/javaconnectiondb` and uses a `student` table with the columns `sid`, `sname`, `sage` and `saddress`. A matching table can be created like this:

```sql
CREATE DATABASE javaconnectiondb;
USE javaconnectiondb;

CREATE TABLE student (
  sid INT AUTO_INCREMENT PRIMARY KEY,
  sname VARCHAR(100),
  sage INT,
  saddress VARCHAR(255)
);
```

`sid` must be generated automatically, because inserts only provide name, age and address.

The database URL, username and password are hard-coded in `src/JDBC_Util_Package/JDBCUtil.java`. Change them there to match your MySQL setup.

## Running

### Eclipse

1. Import the folder with **File > Import > Existing Projects into Workspace**.
2. Create a user library named `MySQLJAR` that contains the MySQL Connector/J JAR, or add the JAR to the build path.
3. Run `Prepared_Statement.Driver` as a Java application.

### Command line

Replace `mysql-connector-j.jar` with the path to your connector JAR.

Linux/macOS:

```bash
javac -d out src/JDBC_Util_Package/*.java src/Prepared_Statement/*.java
java -cp "out:mysql-connector-j.jar" Prepared_Statement.Driver
```

Windows:

```cmd
javac -d out src\JDBC_Util_Package\*.java src\Prepared_Statement\*.java
java -cp "out;mysql-connector-j.jar" Prepared_Statement.Driver
```

## Notes

- Name and address are read with `Scanner.next()`, so each must be a single word with no spaces.
