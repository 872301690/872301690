package com.gupao.templet;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    public T convert(ResultSet rs, int index) throws SQLException;
}
