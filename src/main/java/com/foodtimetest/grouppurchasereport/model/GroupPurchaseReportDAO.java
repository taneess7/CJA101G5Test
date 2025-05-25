package com.foodtimetest.grouppurchasereport.model;

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


public class GroupPurchaseReportDAO implements GroupPurchaseReportDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GROUP_PURCHASE_REPORT (MEM_ID, GB_ID, REPORT_REASON, REPORT_DETAIL, REPORT_STATUS, CREATE_AT, UPDATE_AT) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE GROUP_PURCHASE_REPORT SET MEM_ID=?, GB_ID=?, REPORT_REASON=?, REPORT_DETAIL=?, REPORT_STATUS=?, CREATE_AT=?, UPDATE_AT=? WHERE REPORT_ID = ?";
	private static final String DELETE = "DELETE FROM GROUP_PURCHASE_REPORT WHERE REPORT_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM GROUP_PURCHASE_REPORT WHERE REPORT_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM GROUP_PURCHASE_REPORT";
	private static final String GET_BY_MEMID = "SELECT * FROM GROUP_PURCHASE_REPORT WHERE MEM_ID = ?";
	private static final String GET_BY_GBID = "SELECT * FROM GROUP_PURCHASE_REPORT WHERE GB_ID = ?";

	@Override
	public void insert(GroupPurchaseReportVO grouppurchasereportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, grouppurchasereportVO.getMemId());
			pstmt.setInt(2, grouppurchasereportVO.getGbId());
			pstmt.setString(3, grouppurchasereportVO.getReportReason());
			pstmt.setString(4, grouppurchasereportVO.getReportDetail());
			pstmt.setInt(5, grouppurchasereportVO.getReportStatus());
			pstmt.setTimestamp(6, new java.sql.Timestamp(grouppurchasereportVO.getCreateAt().getTime()));
			pstmt.setTimestamp(7, new java.sql.Timestamp(grouppurchasereportVO.getUpdateAt().getTime()));

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
	public void update(GroupPurchaseReportVO grouppurchasereportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, grouppurchasereportVO.getMemId());
			pstmt.setInt(2, grouppurchasereportVO.getGbId());
			pstmt.setString(3, grouppurchasereportVO.getReportReason());
			pstmt.setString(4, grouppurchasereportVO.getReportDetail());
			pstmt.setInt(5, grouppurchasereportVO.getReportStatus());
			pstmt.setTimestamp(6, new java.sql.Timestamp(grouppurchasereportVO.getCreateAt().getTime()));
			pstmt.setTimestamp(7, new java.sql.Timestamp(grouppurchasereportVO.getUpdateAt().getTime()));

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
	public void delete(Integer reportId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reportId);

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
	public GroupPurchaseReportVO findByPrimaryKey(Integer reportId) {
		GroupPurchaseReportVO grouppurchasereportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, reportId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
            	grouppurchasereportVO = new GroupPurchaseReportVO();
            	grouppurchasereportVO.setReportId(rs.getInt("REPORT_ID"));
            	grouppurchasereportVO.setMemId(rs.getInt("MEM_ID"));
            	grouppurchasereportVO.setGbId(rs.getInt("GB_ID"));
            	grouppurchasereportVO.setReportReason(rs.getString("REPORT_REASON"));
            	grouppurchasereportVO.setReportDetail(rs.getString("REPORT_DETAIL"));
            	grouppurchasereportVO.setReportStatus(rs.getByte("REPORT_STATUS"));
            	grouppurchasereportVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
            	grouppurchasereportVO.setUpdateAt(rs.getTimestamp("UPDATE_AT"));
            	
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
        return grouppurchasereportVO;
	}

	@Override
	public List<GroupPurchaseReportVO> getAll() {
		 List<GroupPurchaseReportVO> list = new ArrayList<GroupPurchaseReportVO>();
		 GroupPurchaseReportVO grouppurchasereportVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_ALL_STMT);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	grouppurchasereportVO = new GroupPurchaseReportVO();
	            	grouppurchasereportVO.setReportId(rs.getInt("REPORT_ID"));
	            	grouppurchasereportVO.setMemId(rs.getInt("MEM_ID"));
	            	grouppurchasereportVO.setGbId(rs.getInt("GB_ID"));
	            	grouppurchasereportVO.setReportReason(rs.getString("REPORT_REASON"));
	            	grouppurchasereportVO.setReportDetail(rs.getString("REPORT_DETAIL"));
	            	grouppurchasereportVO.setReportStatus(rs.getByte("REPORT_STATUS"));
	            	grouppurchasereportVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
	            	grouppurchasereportVO.setUpdateAt(rs.getTimestamp("UPDATE_AT"));
	            	 
	                list.add(grouppurchasereportVO);
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
	public List<GroupPurchaseReportVO> findByMemId(Integer memId) {
		List<GroupPurchaseReportVO> list = new ArrayList<GroupPurchaseReportVO>();
		GroupPurchaseReportVO grouppurchasereportVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_MEMID);
	            pstmt.setInt(1, memId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	grouppurchasereportVO = new GroupPurchaseReportVO();
	            	grouppurchasereportVO.setReportId(rs.getInt("REPORT_ID"));
	            	grouppurchasereportVO.setMemId(rs.getInt("MEM_ID"));
	            	grouppurchasereportVO.setGbId(rs.getInt("GB_ID"));
	            	grouppurchasereportVO.setReportReason(rs.getString("REPORT_REASON"));
	            	grouppurchasereportVO.setReportDetail(rs.getString("REPORT_DETAIL"));
	            	grouppurchasereportVO.setReportStatus(rs.getByte("REPORT_STATUS"));
	            	grouppurchasereportVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
	            	grouppurchasereportVO.setUpdateAt(rs.getTimestamp("UPDATE_AT"));
	            	 
	                list.add(grouppurchasereportVO);
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
	public List<GroupPurchaseReportVO> findByGBId(Integer gbId) {
		List<GroupPurchaseReportVO> list = new ArrayList<GroupPurchaseReportVO>();
		GroupPurchaseReportVO grouppurchasereportVO = null;

	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = ds.getConnection();

	            pstmt = con.prepareStatement(GET_BY_GBID);
	            pstmt.setInt(1, gbId);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            	grouppurchasereportVO = new GroupPurchaseReportVO();
	            	grouppurchasereportVO.setReportId(rs.getInt("REPORT_ID"));
	            	grouppurchasereportVO.setMemId(rs.getInt("MEM_ID"));
	            	grouppurchasereportVO.setGbId(rs.getInt("GB_ID"));
	            	grouppurchasereportVO.setReportReason(rs.getString("REPORT_REASON"));
	            	grouppurchasereportVO.setReportDetail(rs.getString("REPORT_DETAIL"));
	            	grouppurchasereportVO.setReportStatus(rs.getByte("REPORT_STATUS"));
	            	grouppurchasereportVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
	            	grouppurchasereportVO.setUpdateAt(rs.getTimestamp("UPDATE_AT"));
	            	 
	                list.add(grouppurchasereportVO);
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
