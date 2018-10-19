package com.nokinobi.services;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface Operation<E> {
	E operation(DataSource ds) throws SQLException;
}
