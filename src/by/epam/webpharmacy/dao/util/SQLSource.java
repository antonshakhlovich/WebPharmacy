package by.epam.webpharmacy.dao.util;

/**
 * Contains names of all possible sql databases
 */
public enum SQLSource {
    MYSQL ("jdbc:mysql://31.130.206.159/webpharmacy","root","LENoep78529", "com.mysql.jdbc.Driver"),
    MYSQL_LOCAL ("jdbc:mysql://localhost:3306/webpharmacy","root","123456", "com.mysql.jdbc.Driver"),
    MYSQL_MYCLOUD ("jdbc:mysql://node33623-library.mycloud.by/webpharmacy","root","LENoep78529", "com.mysql.jdbc.Driver");

    private String connectionURI;
    private String username;
    private String password;
    private String classDriver;

    SQLSource(String connectionURI, String username, String password, String classDriver) {
        this.connectionURI = connectionURI;
        this.username = username;
        this.password = password;
        this.classDriver = classDriver;
    }

    public String getConnectionURI() {
        return connectionURI;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getClassDriver() {
        return classDriver;
    }
}
