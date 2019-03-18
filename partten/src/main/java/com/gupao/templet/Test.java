package com.gupao.templet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        JdbcTemplemt jdbcTemplemt = new JdbcTemplemt(null);
        String sql = "select * from database";
        List query = jdbcTemplemt.query(sql, new RowMapper() {
            @Override
            public Object convert(ResultSet rs, int index) throws SQLException {
                User user = new User();
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                return user;
            }
        });
        System.out.println(query);
    }
}
