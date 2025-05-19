package com.foodtimetest.memfavlist;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FavoriteListJDBCDAO {
	
	private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
	
	private static final String INSERT_STMT =
			"INSERT INTO FAVORITE_LIST (mem_Id,prod_Id) VALUES (?,?)";
	private static final String GET_ALL_STMT =
			"SELECT mem_Id,prod_Id FROM favorite_list";
	private static final String GET_ONE_STMT =
			"SELECT mem_Id,prod_Id FROM favorite_list WHERE mem_Id = ?";
	private static final String DELETE =
			"DELETE FROM favorite_list WHERE mem_Id = ?";
	private static final String UPDATE =
			"UPDATE favorite_list set mem_Id = ?,prod_Id = ? WHERE mem_Id = ?";
	
	public void insert(FavoriteListVO favoriteListVO) {
		
	}

}
