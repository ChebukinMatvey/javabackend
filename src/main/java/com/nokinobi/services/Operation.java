package com.nokinobi.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface Operation<E> {
	E operation(Connection con) throws SQLException; 
}
