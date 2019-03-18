package com.gupao.templet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplemt{

    private DataSource dataSource;

    public JdbcTemplemt(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List query(String sql, RowMapper rowMapper) throws Exception {
        Connection connection = getConnection(dataSource);
        PreparedStatement ps = getPreparementStatement(connection, sql);
        ResultSet resultSet = getResultSet(ps);
        List list = execute(resultSet,rowMapper);

        close(resultSet,ps,connection);
        return list;
    }

    private void close(ResultSet resultSet, PreparedStatement ps, Connection connection) {

        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List execute(ResultSet ResultSet, RowMapper rowMapper) throws SQLException {
        List list = new ArrayList();
        int index = 1;
        while (ResultSet.next()){
            Object convert = rowMapper.convert(ResultSet, index);
            index++;
            list.add(convert);
        }
        return list;
    }


    private ResultSet getResultSet(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }


    private PreparedStatement getPreparementStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    private Connection getConnection(DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }
}
