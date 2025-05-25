package com.foodtimetest.groupbuyingcases.model;

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

public class GroupBuyingCasesDAO implements GroupBuyingCasesDAO_interface{
	
    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    private static final String INSERT_STMT = "INSERT INTO GROUPBUYINGCASES (STOR_ID, GB_PROD_ID, MEM_ID, GB_START_TIME, GB_END_TIME, GB_TITLE, GB_DESCRIPTION, GB_STATUS, GB_CREATE_AT, GB_MIN_PRODUCT_QUANTITY, CANCEL_REASON, CUMULATIVE_PURCHASE_QUANTITY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE GROUPBUYINGCASES SET STOR_ID = ?, GB_PROD_ID = ?, MEM_ID = ?, GB_START_TIME = ?, GB_END_TIME = ?, GB_TITLE = ?, GB_DESCRIPTION = ?, GB_STATUS = ?, GB_CREATE_AT = ?, GB_MIN_PRODUCT_QUANTITY = ?, CANCEL_REASON = ?, CUMULATIVE_PURCHASE_QUANTITY = ? WHERE GB_ID = ? ";
    private static final String DELETE = "DELETE FROM GROUPBUYINGCASES WHERE GB_ID = ?";
    private static final String GET_ONE_STMT = "SELECT * FROM GROUPBUYINGCASES WHERE GB_ID = ? ";
    private static final String GET_ALL_STMT = "SELECT * FROM GROUPBUYINGCASES ";
    private static final String GET_BY_MEMID = "SELECT * FROM GROUPBUYINGCASES WHERE MEM_ID = ?";
    private static final String GET_BY_STORID = "SELECT * FROM GROUPBUYINGCASES WHERE STOR_ID = ?";
    private static final String GET_BY_GBPRODID = "SELECT * FROM GROUPBUYINGCASES WHERE GB_PROD_ID = ?";
    
    
    
	@Override
	public void insert(GroupBuyingCasesVO groupbuyingcasesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, groupbuyingcasesVO.getStorId());
	        pstmt.setInt(2, groupbuyingcasesVO.getGbProdId());
	        pstmt.setInt(3, groupbuyingcasesVO.getMemId());
	        pstmt.setTimestamp(4, new java.sql.Timestamp(groupbuyingcasesVO.getGbStartTime().getTime()));
	        pstmt.setTimestamp(5, new java.sql.Timestamp(groupbuyingcasesVO.getGbEndTime().getTime()));
	        pstmt.setString(6, groupbuyingcasesVO.getGbTitle());
	        pstmt.setString(7, groupbuyingcasesVO.getGbDescription());
	        pstmt.setByte(8, groupbuyingcasesVO.getGbStatus());
	        pstmt.setTimestamp(9, new java.sql.Timestamp(groupbuyingcasesVO.getGbCreateAt().getTime()));
	        pstmt.setInt(10, groupbuyingcasesVO.getGbMinProductQuantity());
	        pstmt.setString(11, groupbuyingcasesVO.getCancelReason());
	        pstmt.setInt(12, groupbuyingcasesVO.getCumulativePurchaseQuantity());
	        pstmt.setInt(13, groupbuyingcasesVO.getGbId());
	        
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
	public void update(GroupBuyingCasesVO groupbuyingcasesVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, groupbuyingcasesVO.getStorId());
		        pstmt.setInt(2, groupbuyingcasesVO.getGbProdId());
		        pstmt.setInt(3, groupbuyingcasesVO.getMemId());
		        pstmt.setTimestamp(4, new java.sql.Timestamp(groupbuyingcasesVO.getGbStartTime().getTime()));
		        pstmt.setTimestamp(5, new java.sql.Timestamp(groupbuyingcasesVO.getGbEndTime().getTime()));
		        pstmt.setString(6, groupbuyingcasesVO.getGbTitle());
		        pstmt.setString(7, groupbuyingcasesVO.getGbDescription());
		        pstmt.setByte(8, groupbuyingcasesVO.getGbStatus());
		        pstmt.setTimestamp(9, new java.sql.Timestamp(groupbuyingcasesVO.getGbCreateAt().getTime()));
		        pstmt.setInt(10, groupbuyingcasesVO.getGbMinProductQuantity());
		        pstmt.setString(11, groupbuyingcasesVO.getCancelReason());
		        pstmt.setInt(12, groupbuyingcasesVO.getCumulativePurchaseQuantity());
		        
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
	public void delete(Integer gbId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, gbId);

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
	public GroupBuyingCasesVO findByPrimaryKey(Integer gbId) {
		GroupBuyingCasesVO groupbuyingcasesVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, gbId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
            	groupbuyingcasesVO = new GroupBuyingCasesVO();
            	groupbuyingcasesVO.setGbId(rs.getInt("GB_ID"));
            	groupbuyingcasesVO.setStorId(rs.getInt("STOR_ID"));
            	groupbuyingcasesVO.setGbProdId(rs.getInt("GB_PROD_ID"));
            	groupbuyingcasesVO.setMemId(rs.getInt("MEM_ID"));
            	groupbuyingcasesVO.setGbStartTime(rs.getTimestamp("GB_START_TIME"));
            	groupbuyingcasesVO.setGbEndTime(rs.getTimestamp("GB_END_TIME"));
            	groupbuyingcasesVO.setGbTitle(rs.getString("GB_TITLE"));
            	groupbuyingcasesVO.setGbDescription(rs.getString("GB_DESCRIPTION"));
            	groupbuyingcasesVO.setGbStatus(rs.getByte("GB_STATUS"));
            	groupbuyingcasesVO.setGbCreateAt(rs.getTimestamp("GB_CREATE_AT"));
            	groupbuyingcasesVO.setGbMinProductQuantity(rs.getInt("GB_MIN_PRODUCT_QUANTITY"));
            	groupbuyingcasesVO.setCancelReason(rs.getString("CANCEL_REASON"));
            	groupbuyingcasesVO.setCumulativePurchaseQuantity(rs.getInt("CUMULATIVE_PURCHASE_QUANTITY"));
            	
            	
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
        return groupbuyingcasesVO;
	}
	
	@Override
	public List<GroupBuyingCasesVO> getAll() {
		 List<GroupBuyingCasesVO> list = new ArrayList<GroupBuyingCasesVO>();
		 GroupBuyingCasesVO groupbuyingcasesVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_ALL_STMT);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupbuyingcasesVO = new GroupBuyingCasesVO();
	            	groupbuyingcasesVO.setGbId(rs.getInt("GB_ID"));
	            	groupbuyingcasesVO.setStorId(rs.getInt("STOR_ID"));
	            	groupbuyingcasesVO.setGbProdId(rs.getInt("GB_PROD_ID"));
	            	groupbuyingcasesVO.setMemId(rs.getInt("MEM_ID"));
	            	groupbuyingcasesVO.setGbStartTime(rs.getTimestamp("GB_START_TIME"));
	            	groupbuyingcasesVO.setGbEndTime(rs.getTimestamp("GB_END_TIME"));
	            	groupbuyingcasesVO.setGbTitle(rs.getString("GB_TITLE"));
	            	groupbuyingcasesVO.setGbDescription(rs.getString("GB_DESCRIPTION"));
	            	groupbuyingcasesVO.setGbStatus(rs.getByte("GB_STATUS"));
	            	groupbuyingcasesVO.setGbCreateAt(rs.getTimestamp("GB_CREATE_AT"));
	            	groupbuyingcasesVO.setGbMinProductQuantity(rs.getInt("GB_MIN_PRODUCT_QUANTITY"));
	            	groupbuyingcasesVO.setCancelReason(rs.getString("CANCEL_REASON"));
	            	groupbuyingcasesVO.setCumulativePurchaseQuantity(rs.getInt("CUMULATIVE_PURCHASE_QUANTITY"));
	                
	                list.add(groupbuyingcasesVO);
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
	
	public List<GroupBuyingCasesVO> findByMemId(Integer memId) {
		 List<GroupBuyingCasesVO> list = new ArrayList<GroupBuyingCasesVO>();
		 GroupBuyingCasesVO groupbuyingcasesVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_MEMID);
	            pstmt.setInt(1, memId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupbuyingcasesVO = new GroupBuyingCasesVO();
	            	groupbuyingcasesVO.setGbId(rs.getInt("GB_ID"));
	            	groupbuyingcasesVO.setStorId(rs.getInt("STOR_ID"));
	            	groupbuyingcasesVO.setGbProdId(rs.getInt("GB_PROD_ID"));
	            	groupbuyingcasesVO.setMemId(rs.getInt("MEM_ID"));
	            	groupbuyingcasesVO.setGbStartTime(rs.getTimestamp("GB_START_TIME"));
	            	groupbuyingcasesVO.setGbEndTime(rs.getTimestamp("GB_END_TIME"));
	            	groupbuyingcasesVO.setGbTitle(rs.getString("GB_TITLE"));
	            	groupbuyingcasesVO.setGbDescription(rs.getString("GB_DESCRIPTION"));
	            	groupbuyingcasesVO.setGbStatus(rs.getByte("GB_STATUS"));
	            	groupbuyingcasesVO.setGbCreateAt(rs.getTimestamp("GB_CREATE_AT"));
	            	groupbuyingcasesVO.setGbMinProductQuantity(rs.getInt("GB_MIN_PRODUCT_QUANTITY"));
	            	groupbuyingcasesVO.setCancelReason(rs.getString("CANCEL_REASON"));
	            	groupbuyingcasesVO.setCumulativePurchaseQuantity(rs.getInt("CUMULATIVE_PURCHASE_QUANTITY"));
	                
	                list.add(groupbuyingcasesVO);
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
	public List<GroupBuyingCasesVO> findByStorId(Integer storId) {
		 List<GroupBuyingCasesVO> list = new ArrayList<GroupBuyingCasesVO>();
		 GroupBuyingCasesVO groupbuyingcasesVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_STORID);
	            pstmt.setInt(1, storId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupbuyingcasesVO = new GroupBuyingCasesVO();
	            	groupbuyingcasesVO.setGbId(rs.getInt("GB_ID"));
	            	groupbuyingcasesVO.setStorId(rs.getInt("STOR_ID"));
	            	groupbuyingcasesVO.setGbProdId(rs.getInt("GB_PROD_ID"));
	            	groupbuyingcasesVO.setMemId(rs.getInt("MEM_ID"));
	            	groupbuyingcasesVO.setGbStartTime(rs.getTimestamp("GB_START_TIME"));
	            	groupbuyingcasesVO.setGbEndTime(rs.getTimestamp("GB_END_TIME"));
	            	groupbuyingcasesVO.setGbTitle(rs.getString("GB_TITLE"));
	            	groupbuyingcasesVO.setGbDescription(rs.getString("GB_DESCRIPTION"));
	            	groupbuyingcasesVO.setGbStatus(rs.getByte("GB_STATUS"));
	            	groupbuyingcasesVO.setGbCreateAt(rs.getTimestamp("GB_CREATE_AT"));
	            	groupbuyingcasesVO.setGbMinProductQuantity(rs.getInt("GB_MIN_PRODUCT_QUANTITY"));
	            	groupbuyingcasesVO.setCancelReason(rs.getString("CANCEL_REASON"));
	            	groupbuyingcasesVO.setCumulativePurchaseQuantity(rs.getInt("CUMULATIVE_PURCHASE_QUANTITY"));
	                
	                list.add(groupbuyingcasesVO);
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
	public List<GroupBuyingCasesVO> findByGBProdId(Integer gbProdId) {
		 List<GroupBuyingCasesVO> list = new ArrayList<GroupBuyingCasesVO>();
		 GroupBuyingCasesVO groupbuyingcasesVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_GBPRODID);
	            pstmt.setInt(1, gbProdId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupbuyingcasesVO = new GroupBuyingCasesVO();
	            	groupbuyingcasesVO.setGbId(rs.getInt("GB_ID"));
	            	groupbuyingcasesVO.setStorId(rs.getInt("STOR_ID"));
	            	groupbuyingcasesVO.setGbProdId(rs.getInt("GB_PROD_ID"));
	            	groupbuyingcasesVO.setMemId(rs.getInt("MEM_ID"));
	            	groupbuyingcasesVO.setGbStartTime(rs.getTimestamp("GB_START_TIME"));
	            	groupbuyingcasesVO.setGbEndTime(rs.getTimestamp("GB_END_TIME"));
	            	groupbuyingcasesVO.setGbTitle(rs.getString("GB_TITLE"));
	            	groupbuyingcasesVO.setGbDescription(rs.getString("GB_DESCRIPTION"));
	            	groupbuyingcasesVO.setGbStatus(rs.getByte("GB_STATUS"));
	            	groupbuyingcasesVO.setGbCreateAt(rs.getTimestamp("GB_CREATE_AT"));
	            	groupbuyingcasesVO.setGbMinProductQuantity(rs.getInt("GB_MIN_PRODUCT_QUANTITY"));
	            	groupbuyingcasesVO.setCancelReason(rs.getString("CANCEL_REASON"));
	            	groupbuyingcasesVO.setCumulativePurchaseQuantity(rs.getInt("CUMULATIVE_PURCHASE_QUANTITY"));
	                
	                list.add(groupbuyingcasesVO);
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
