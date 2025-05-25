package com.foodtimetest.groupbuyingcollectionlist.model;

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


public class GroupBuyingCollectionListDAO implements GroupBuyingCollectionListDAO_interface{

    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    private static final String INSERT_STMT ="INSERT INTO GROUP_BUYING_COLLECTION_LIST (GB_ID, MEM_ID, CREATE_AT) VALUES (?, ?, ?)";
    private static final String DELETE ="DELETE FROM GROUP_BUYING_COLLECTION_LIST WHERE GB_ID = ? AND MEM_ID = ?";
    private static final String GET_ONE_STMT ="SELECT * FROM GROUP_BUYING_COLLECTION_LIST WHERE GB_ID = ? AND MEM_ID = ?";
    private static final String GET_ALL_STMT ="SELECT * FROM GROUP_BUYING_COLLECTION_LIST";
    private static final String GET_BY_MEMID ="SELECT * FROM GROUP_BUYING_COLLECTION_LIST WHERE MEM_ID = ?";
	
    @Override
	public void insert(GroupBuyingCollectionListVO groupbuyingcollectionlistVO) {
    	Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			 	pstmt.setInt(1, groupbuyingcollectionlistVO.getGbId());
	            pstmt.setInt(2, groupbuyingcollectionlistVO.getMemId());
	            pstmt.setTimestamp(3, new java.sql.Timestamp(groupbuyingcollectionlistVO.getCreateAt().getTime()));
	            
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
	public void delete(Integer gbId, Integer memId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, gbId);
            pstmt.setInt(2, memId);

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
	public GroupBuyingCollectionListVO findByPrimaryKey(Integer gbId, Integer memId) {
		GroupBuyingCollectionListVO groupbuyingcollectionlistVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(GET_ONE_STMT);
            
            pstmt.setInt(1, gbId);
            pstmt.setInt(2, memId);            

            rs = pstmt.executeQuery();

            while (rs.next()) {
            	groupbuyingcollectionlistVO = new GroupBuyingCollectionListVO();
            	groupbuyingcollectionlistVO.setGbId(rs.getInt("GB_ID"));
            	groupbuyingcollectionlistVO.setMemId(rs.getInt("MEM_ID"));
            	groupbuyingcollectionlistVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
            	
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
        return groupbuyingcollectionlistVO;
	}
	
	@Override
	public List<GroupBuyingCollectionListVO> getAll() {
		 List<GroupBuyingCollectionListVO> list = new ArrayList<GroupBuyingCollectionListVO>();
		 GroupBuyingCollectionListVO groupbuyingcollectionlistVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_ALL_STMT);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupbuyingcollectionlistVO.setGbId(rs.getInt("GB_ID"));
	            	groupbuyingcollectionlistVO.setMemId(rs.getInt("MEM_ID"));
	            	groupbuyingcollectionlistVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
 
	                list.add(groupbuyingcollectionlistVO);
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
	public List<GroupBuyingCollectionListVO> findByMemId(Integer memId) {
		 List<GroupBuyingCollectionListVO> list = new ArrayList<GroupBuyingCollectionListVO>();
		 GroupBuyingCollectionListVO groupbuyingcollectionlistVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_MEMID);
	            pstmt.setInt(1, memId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	groupbuyingcollectionlistVO.setGbId(rs.getInt("GB_ID"));
	            	groupbuyingcollectionlistVO.setMemId(rs.getInt("MEM_ID"));
	            	groupbuyingcollectionlistVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
           
	                list.add(groupbuyingcollectionlistVO);
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
