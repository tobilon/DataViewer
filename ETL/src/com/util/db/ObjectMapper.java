package com.util.db;

import java.sql.ResultSet;

public interface ObjectMapper {
	public Object mapping(ResultSet rs);

}
