package com.foodtimetest.memfavlist.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.foodtimetest.memfavlist.FavoriteListJDBCDAO;
import com.foodtimetest.memfavlist.FavoriteListVO;


public class FavoriteListServlet extends HttpServlet{
	 private FavoriteListJDBCDAO dao;

	    @Override
	    public void init() throws ServletException {
	        dao = new FavoriteListJDBCDAO();
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        req.setCharacterEncoding("UTF-8");
	        String action = req.getParameter("action");

	        if (action == null) {
	            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少 action 參數");
	            return;
	        }

	        switch (action) {
	            case "add":
	                addFavorite(req, res);
	                break;
	            case "deleteOne":
	                deleteOneFavorite(req, res);
	                break;
	            case "deleteAll":
	                deleteAllFavorites(req, res);
	                break;
	            default:
	                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "未知的 action: " + action);
	        }
	    }

	    private void addFavorite(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        try {
	            Integer memId = Integer.valueOf(req.getParameter("memId"));
	            Integer prodId = Integer.valueOf(req.getParameter("prodId"));

	            FavoriteListVO vo = new FavoriteListVO();
	            vo.setMemId(memId);
	            vo.setProdId(prodId);

	            dao.insert(vo);
	            res.getWriter().write("新增收藏成功");

	        } catch (Exception e) {
	            e.printStackTrace();
	            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "新增收藏失敗");
	        }
	    }

	    private void deleteOneFavorite(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        try {
	            Integer memId = Integer.valueOf(req.getParameter("memId"));
	            Integer prodId = Integer.valueOf(req.getParameter("prodId"));

	            dao.deleteByMemIdAndProdId(memId, prodId);
	            res.getWriter().write("刪除單筆收藏成功");

	        } catch (Exception e) {
	            e.printStackTrace();
	            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "刪除單筆收藏失敗");
	        }
	    }

	    private void deleteAllFavorites(HttpServletRequest req, HttpServletResponse res) throws IOException {
	        try {
	            Integer memId = Integer.valueOf(req.getParameter("memId"));
	            dao.deleteAllByMemId(memId);
	            res.getWriter().write("刪除會員所有收藏成功");

	        } catch (Exception e) {
	            e.printStackTrace();
	            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "刪除全部收藏失敗");
	        }
	    }
	
}
