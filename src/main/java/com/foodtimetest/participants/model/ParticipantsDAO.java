package com.foodtimetest.participants.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantsDAO implements ParticipantsDAO_interface{
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
    		"INSERT INTO PARTICIPANTS (PAR_ID, MEM_ID, GB_ID, PAR_PHONE, PAR_NAME, PAR_ADDRESS, PAR_LONGITUDE, PAR_LATITUDE, IS_LEADER, PAR_PURCHASE_QUANTITY, PAYMENT_STATUS) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_STMT =
    		"UPDATE PARTICIPANTS SET PAR_ID = ?, MEM_ID = ?, GB_ID = ?, PAR_PHONE = ?, PAR_NAME = ?, PAR_ADDRESS = ?, PAR_LONGITUDE = ?, PAR_LATITUDE = ?, IS_LEADER = ?, PAR_PURCHASE_QUANTITY = ?, PAYMENT_STATUS = ? WHERE = PAR_ID";
    private static final String GET_ONE_STMT =
    		"SELECT * FROM PARTICIPANTS WHERE = PAR_ID";
    private static final String GET_ALL_STMT =
    		"SELECT * FROM PARTICIPANTS";
    
    
    
    
    
    
    
    
    
    
    
    
	@Override
	public void insert(ParticipantsVO participantsVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(ParticipantsVO participantsVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ParticipantsVO findByPrimaryKey(Integer parId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ParticipantsVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ParticipantsVO> findByGBId(Integer gbId) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
    
    
}
