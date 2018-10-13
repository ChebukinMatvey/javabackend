package services;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class TransactionManager {
	
	@Resource(name = "ds")
	private DataSource ds;
	
	public <T> T doTransaction(Operation<T> op) {
		Connection con=null;
		try {
			con=ds.getConnection();
			con.setAutoCommit(false);
			T res=op.operation(con);
			return res;
		}catch(SQLException ex) {
			if(con!=null)
				try {
					con.rollback();
				} catch (SQLException ex2) {
					ex2.printStackTrace();
				}
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

}
