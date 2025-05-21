package com.foodtimetest.Coupon.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

import com.foodtimetest.Coupon.model.CouponService;
import com.foodtimetest.Coupon.model.CouponVO;




/**
 * Servlet implementation class CouponServlet
 */
@WebServlet(name = "CouponServlet", urlPatterns = {"/coupon/coupon.do"})
public class CouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CouponServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/*****************單一查詢****************/

		if ("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("couId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 有errorMsgs就跳頁，不處理後續流程，程式中斷

			}

			Integer couId = null;
			try {
				couId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 有errorMsgs，程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CouponService couponSvc = new CouponService();
			CouponVO couponVO = couponSvc.getOneCoupon(couId);
			if (couponVO == null) {
				errorMsgs.add("查無資料");
			}
			// 如果有錯誤，將使用者導回原本的輸入表單頁面
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couponVO", couponVO);
			String url = "/coupon/listOneCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCoupon.jsp
			successView.forward(req, res);
		}
	}
}
