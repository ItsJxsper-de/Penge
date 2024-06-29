package dev.itsjxsper.pengeapi.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;

public class MariadbTabel {

    private final MariadbConnction connection;
    private final String name;
    private final HashMap<String, MariadbDataType> columns;


    public MariadbTabel(MariadbConnction connection, String name, HashMap<String, MariadbDataType> columns) {
        this.connection = connection;
        this.name = name;
        this.columns = columns;
        createTable();
    }

    public void createTable() {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + name + "(");
        for (String colum : columns.keySet()) {
            sql.append(colum).append(" ").append(columns.get(colum).toDB()).append(",");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(");");
        try {
            Statement statement = this.connection.getConnection().createStatement();
            statement.executeUpdate(sql.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getColumNames() {
        return columns.keySet();
    }

    public MariadbDataType getType(String columName) {
        return columns.get(columName);
    }

    public static class Condition {
        String value;
        String columName;

        public Condition(String columName, String value) {
            this.value = value;
            this.columName = columName;
        }
    }

    public void set(String columName, Object objects, Condition condition) {
        if(objects == null) {
            remove(condition);
            return;
        }
        if(exits(condition)) {
            try {
                String sql = "update " + this.name + " set " + columName + "=? where " + condition.columName + "=?";
                PreparedStatement ps = connection.getConnection().prepareStatement(sql);
                ps.setObject(1, objects);
                ps.setString(2, condition.value);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                String sql = "insert into " + this.name + " ("  + columName + ") values (?)";
                PreparedStatement ps = connection.getConnection().prepareStatement(sql);
                ps.setObject(1, objects);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void remove(Condition condition) {
        try {
            String sql = "delete from " + this.name + " where " + condition.columName + "=?";
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, condition.value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement select(String columName, Condition condition) {
        try {
            String sql = "select " + columName +" from " + this.name +" where " + condition.columName + "=?";
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, condition.value);
            return ps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String columName, Condition condition) {
        try {
            ResultSet rs = select(columName, condition).executeQuery();
            if (rs.next()) {
                return rs.getString(columName);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getInt(String columName, Condition condition) {
        try {
            ResultSet rs = select(columName, condition).executeQuery();
            if (rs.next()) {
                return rs.getInt(columName);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getBoolean(String columName, Condition condition) {
        try {
            ResultSet rs = select(columName, condition).executeQuery();
            if (rs.next()) {
                return rs.getBoolean(columName);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean exits(Condition condition) {
        try {
            String sql = "select " + condition.columName +" from " + this.name +" where " + condition.columName + "=?";
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, condition.value);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
