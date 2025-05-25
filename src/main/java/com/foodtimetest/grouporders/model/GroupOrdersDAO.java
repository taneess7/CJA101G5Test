package com.foodtimetest.grouporders.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class GroupOrdersDAO implements GroupOrdersDAO_interface{
    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    private static final String INSERT_STMT ="INSERT INTO GROUP_ORDERS (GB_OR_ID, GB_ID, STOR_ID, GB_PROD_ID, JOIN_TIME, AMOUNT, QUANTITY, PAY_METHOD, ORDER_STATUS, PAYMENT_STATUS, SHIPPING_STATUS, PAR_NAME, PAR_ADDRESS, PAR_LONGITUDE, PAR_LATITUDE, PAR_PHONE, DELIVERY_METHOD, COMMENT, RATING) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE ="UPDATE GROUP_ORDERS SET GB_OR_ID = ?, GB_ID = ?, STOR_ID = ?, GB_PROD_ID = ?, JOIN_TIME = ?, AMOUNT = ?, QUANTITY = ?, PAY_METHOD = ?, ORDER_STATUS = ?, PAYMENT_STATUS = ?, SHIPPING_STATUS = ?, PAR_NAME = ?, PAR_ADDRESS = ?, PAR_LONGITUDE = ?, PAR_LATITUDE = ?, PAR_PHONE = ?, DELIVERY_METHOD = ?, COMMENT = ?, RATING = ? WHERE GB_OR_ID = ?";
    private static final String DELETE ="DELETE FROM GROUP_ORDERS WHERE GB_OR_ID = ?";
    private static final String GET_ONE_STMT ="SELECT * FROM GROUP_ORDERS WHERE GB_OR_ID = ?";
    private static final String GET_ALL_STMT ="SELECT * FROM GROUP_ORDERS ORDER BY GB_OR_ID";
    private static final String GET_BY_GBID ="SELECT * FROM GROUP_ORDERS WHERE GB_ID = ? ORDER BY GB_OR_ID";
    private static final String GET_BY_PARID ="SELECT * FROM GROUP_ORDERS WHERE PAR_ID = ? ORDER BY GB_OR_ID";
	
    
    @Override
	public void insert(GroupOrdersVO groupOrdersVO) {
    	Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, groupOrdersVO.getGbOrId());
			pstmt.setInt(2, groupOrdersVO.getGbId());
	        pstmt.setInt(3, groupOrdersVO.getStorId());
	        pstmt.setInt(4, groupOrdersVO.getGbProdId());
	        pstmt.setTimestamp(5, new java.sql.Timestamp(groupOrdersVO.getJoinTime().getTime())); 
	        pstmt.setInt(6, groupOrdersVO.getAmount());
	        pstmt.setInt(7, groupOrdersVO.getQuantity());
	        pstmt.setByte(8, groupOrdersVO.getPayMethod());
	        pstmt.setByte(9, groupOrdersVO.getOrderStatus());
	        pstmt.setByte(10, groupOrdersVO.getPaymentStatus());
	        pstmt.setByte(11, groupOrdersVO.getShippingStatus());
	        pstmt.setString(12, groupOrdersVO.getParName());
	        pstmt.setString(13, groupOrdersVO.getParAddress());
	        pstmt.setBigDecimal(14, groupOrdersVO.getParLongitude());
	        pstmt.setBigDecimal(15, groupOrdersVO.getParLatitude());
	        pstmt.setString(16, groupOrdersVO.getParPhone());
	        pstmt.setByte(17, groupOrdersVO.getDeliveryMethod());
	        pstmt.setString(18, groupOrdersVO.getComment()); 
	        pstmt.setInt(19, groupOrdersVO.getRating()); 
		
	        pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
    
	@Override
	public void update(GroupOrdersVO groupOrdersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, groupOrdersVO.getGbOrId());
			pstmt.setInt(2, groupOrdersVO.getGbId());
	        pstmt.setInt(3, groupOrdersVO.getStorId());
	        pstmt.setInt(4, groupOrdersVO.getGbProdId());
	        pstmt.setTimestamp(5, new java.sql.Timestamp(groupOrdersVO.getJoinTime().getTime())); 
	        pstmt.setInt(6, groupOrdersVO.getAmount());
	        pstmt.setInt(7, groupOrdersVO.getQuantity());
	        pstmt.setByte(8, groupOrdersVO.getPayMethod());
	        pstmt.setByte(9, groupOrdersVO.getOrderStatus());
	        pstmt.setByte(10, groupOrdersVO.getPaymentStatus());
	        pstmt.setByte(11, groupOrdersVO.getShippingStatus());
	        pstmt.setString(12, groupOrdersVO.getParName());
	        pstmt.setString(13, groupOrdersVO.getParAddress());
	        pstmt.setBigDecimal(14, groupOrdersVO.getParLongitude());
	        pstmt.setBigDecimal(15, groupOrdersVO.getParLatitude());
	        pstmt.setString(16, groupOrdersVO.getParPhone());
	        pstmt.setByte(17, groupOrdersVO.getDeliveryMethod());
	        pstmt.setString(18, groupOrdersVO.getComment()); 
	        pstmt.setInt(19, groupOrdersVO.getRating()); 
	        
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	
}
		
	
	@Override
	public void delete(Integer gbOrId) {
		Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, gbOrId);

            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
		
	}
	
	@Override
	public GroupOrdersVO findByPrimaryKey(Integer gbOrId) {
		GroupOrdersVO groupOrdersVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, gbOrId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
            	groupOrdersVO = new GroupOrdersVO();
            	groupOrdersVO.setGbOrId(rs.getInt("GB_OR_ID"));
                groupOrdersVO.setGbId(rs.getInt("GB_ID"));
                groupOrdersVO.setStorId(rs.getInt("STOR_ID"));
                groupOrdersVO.setGbProdId(rs.getInt("GB_PROD_ID"));
                groupOrdersVO.setJoinTime(rs.getTimestamp("JOIN_TIME"));
                groupOrdersVO.setAmount(rs.getInt("AMOUNT"));
                groupOrdersVO.setQuantity(rs.getInt("QUANTITY"));
                groupOrdersVO.setPayMethod(rs.getByte("PAY_METHOD"));
                groupOrdersVO.setOrderStatus(rs.getByte("ORDER_STATUS"));
                groupOrdersVO.setPaymentStatus(rs.getByte("PAYMENT_STATUS"));
                groupOrdersVO.setShippingStatus(rs.getByte("SHIPPING_STATUS"));
                groupOrdersVO.setParName(rs.getString("PAR_NAME"));
                groupOrdersVO.setParAddress(rs.getString("PAR_ADDRESS"));
                groupOrdersVO.setParLongitude(rs.getBigDecimal("PAR_LONGITUDE"));
                groupOrdersVO.setParLatitude(rs.getBigDecimal("PAR_LATITUDE"));
                groupOrdersVO.setParPhone(rs.getString("PAR_PHONE"));
                groupOrdersVO.setDeliveryMethod(rs.getByte("DELIVERY_METHOD"));
                groupOrdersVO.setComment(rs.getString("COMMENT"));
                groupOrdersVO.setRating(rs.getInt("RATING"));
            	
            	
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return groupOrdersVO;
	}
	
	@Override
	public List<GroupOrdersVO> getAll() {
		 List<GroupOrdersVO> list = new ArrayList<GroupOrdersVO>();
		 GroupOrdersVO groupOrdersVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_ALL_STMT);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupOrdersVO = new GroupOrdersVO();
	            	groupOrdersVO.setGbOrId(rs.getInt("GB_OR_ID"));
	                groupOrdersVO.setGbId(rs.getInt("GB_ID"));
	                groupOrdersVO.setStorId(rs.getInt("STOR_ID"));
	                groupOrdersVO.setGbProdId(rs.getInt("GB_PROD_ID"));
	                groupOrdersVO.setJoinTime(rs.getTimestamp("JOIN_TIME"));
	                groupOrdersVO.setAmount(rs.getInt("AMOUNT"));
	                groupOrdersVO.setQuantity(rs.getInt("QUANTITY"));
	                groupOrdersVO.setPayMethod(rs.getByte("PAY_METHOD"));
	                groupOrdersVO.setOrderStatus(rs.getByte("ORDER_STATUS"));
	                groupOrdersVO.setPaymentStatus(rs.getByte("PAYMENT_STATUS"));
	                groupOrdersVO.setShippingStatus(rs.getByte("SHIPPING_STATUS"));
	                groupOrdersVO.setParName(rs.getString("PAR_NAME"));
	                groupOrdersVO.setParAddress(rs.getString("PAR_ADDRESS"));
	                groupOrdersVO.setParLongitude(rs.getBigDecimal("PAR_LONGITUDE"));
	                groupOrdersVO.setParLatitude(rs.getBigDecimal("PAR_LATITUDE"));
	                groupOrdersVO.setParPhone(rs.getString("PAR_PHONE"));
	                groupOrdersVO.setDeliveryMethod(rs.getByte("DELIVERY_METHOD"));
	                groupOrdersVO.setComment(rs.getString("COMMENT"));
	                groupOrdersVO.setRating(rs.getInt("RATING"));	
	                
	                list.add(groupOrdersVO);
	            }
	        } catch (SQLException se) {
	            throw new RuntimeException("A database error occured. "
	                    + se.getMessage());
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException se) {
	                    se.printStackTrace(System.err);
	                }
	            }
	            if (pstmt != null) {
	                try {
	                    pstmt.close();
	                } catch (SQLException se) {
	                    se.printStackTrace(System.err);
	                }
	            }
	            if (con != null) {
	                try {
	                    con.close();
	                } catch (Exception e) {
	                    e.printStackTrace(System.err);
	                }
	            }
	        }
	        return list;
	}
	
	@Override
	public List<GroupOrdersVO> findByGBId(Integer gbId) {
		 List<GroupOrdersVO> list = new ArrayList<GroupOrdersVO>();
		 GroupOrdersVO groupOrdersVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_GBID);
	            pstmt.setInt(1, gbId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupOrdersVO = new GroupOrdersVO();
	            	groupOrdersVO.setGbOrId(rs.getInt("GB_OR_ID"));
	                groupOrdersVO.setGbId(rs.getInt("GB_ID"));
	                groupOrdersVO.setStorId(rs.getInt("STOR_ID"));
	                groupOrdersVO.setGbProdId(rs.getInt("GB_PROD_ID"));
	                groupOrdersVO.setJoinTime(rs.getTimestamp("JOIN_TIME"));
	                groupOrdersVO.setAmount(rs.getInt("AMOUNT"));
	                groupOrdersVO.setQuantity(rs.getInt("QUANTITY"));
	                groupOrdersVO.setPayMethod(rs.getByte("PAY_METHOD"));
	                groupOrdersVO.setOrderStatus(rs.getByte("ORDER_STATUS"));
	                groupOrdersVO.setPaymentStatus(rs.getByte("PAYMENT_STATUS"));
	                groupOrdersVO.setShippingStatus(rs.getByte("SHIPPING_STATUS"));
	                groupOrdersVO.setParName(rs.getString("PAR_NAME"));
	                groupOrdersVO.setParAddress(rs.getString("PAR_ADDRESS"));
	                groupOrdersVO.setParLongitude(rs.getBigDecimal("PAR_LONGITUDE"));
	                groupOrdersVO.setParLatitude(rs.getBigDecimal("PAR_LATITUDE"));
	                groupOrdersVO.setParPhone(rs.getString("PAR_PHONE"));
	                groupOrdersVO.setDeliveryMethod(rs.getByte("DELIVERY_METHOD"));
	                groupOrdersVO.setComment(rs.getString("COMMENT"));
	                groupOrdersVO.setRating(rs.getInt("RATING"));	
	                
	                list.add(groupOrdersVO);
	            }
	        } catch (SQLException se) {
	            throw new RuntimeException("A database error occured. "
	                    + se.getMessage());
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException se) {
	                    se.printStackTrace(System.err);
	                }
	            }
	            if (pstmt != null) {
	                try {
	                    pstmt.close();
	                } catch (SQLException se) {
	                    se.printStackTrace(System.err);
	                }
	            }
	            if (con != null) {
	                try {
	                    con.close();
	                } catch (Exception e) {
	                    e.printStackTrace(System.err);
	                }
	            }
	        }
	        return list;
	}
	
	@Override
	public List<GroupOrdersVO> findByParId(Integer parId) {
		 List<GroupOrdersVO> list = new ArrayList<GroupOrdersVO>();
		 GroupOrdersVO groupOrdersVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_PARID);
	            pstmt.setInt(1, parId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupOrdersVO = new GroupOrdersVO();
	            	groupOrdersVO.setGbOrId(rs.getInt("GB_OR_ID"));
	                groupOrdersVO.setGbId(rs.getInt("GB_ID"));
	                groupOrdersVO.setStorId(rs.getInt("STOR_ID"));
	                groupOrdersVO.setGbProdId(rs.getInt("GB_PROD_ID"));
	                groupOrdersVO.setJoinTime(rs.getTimestamp("JOIN_TIME"));
	                groupOrdersVO.setAmount(rs.getInt("AMOUNT"));
	                groupOrdersVO.setQuantity(rs.getInt("QUANTITY"));
	                groupOrdersVO.setPayMethod(rs.getByte("PAY_METHOD"));
	                groupOrdersVO.setOrderStatus(rs.getByte("ORDER_STATUS"));
	                groupOrdersVO.setPaymentStatus(rs.getByte("PAYMENT_STATUS"));
	                groupOrdersVO.setShippingStatus(rs.getByte("SHIPPING_STATUS"));
	                groupOrdersVO.setParName(rs.getString("PAR_NAME"));
	                groupOrdersVO.setParAddress(rs.getString("PAR_ADDRESS"));
	                groupOrdersVO.setParLongitude(rs.getBigDecimal("PAR_LONGITUDE"));
	                groupOrdersVO.setParLatitude(rs.getBigDecimal("PAR_LATITUDE"));
	                groupOrdersVO.setParPhone(rs.getString("PAR_PHONE"));
	                groupOrdersVO.setDeliveryMethod(rs.getByte("DELIVERY_METHOD"));
	                groupOrdersVO.setComment(rs.getString("COMMENT"));
	                groupOrdersVO.setRating(rs.getInt("RATING"));	
	                
	                list.add(groupOrdersVO);
	            }
	        } catch (SQLException se) {
	            throw new RuntimeException("A database error occured. "
	                    + se.getMessage());
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException se) {
	                    se.printStackTrace(System.err);
	                }
	            }
	            if (pstmt != null) {
	                try {
	                    pstmt.close();
	                } catch (SQLException se) {
	                    se.printStackTrace(System.err);
	                }
	            }
	            if (con != null) {
	                try {
	                    con.close();
	                } catch (Exception e) {
	                    e.printStackTrace(System.err);
	                }
	            }
	        }
	        return list;
	}
    
    
    
    
    
    
    
}
