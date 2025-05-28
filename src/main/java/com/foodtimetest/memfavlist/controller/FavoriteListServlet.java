package com.foodtimetest.memfavlist.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.foodtimetest.memfavlist.FavoriteListJDBCDAO;
import com.foodtimetest.memfavlist.FavoriteListService;
import com.foodtimetest.memfavlist.FavoriteListVO;
import com.foodtimetest.smg.model.SmgService;
import com.foodtimetest.smg.model.SmgVO;

public class FavoriteListServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str1 = req.getParameter("memId");
			String str2 = req.getParameter("prodId");
			if (str1 == null || (str1.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			if (str2 == null || (str2.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/favoritelist/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer memId = null;
			Integer prodId = null;
			try {
				memId = Integer.valueOf(str1);
				prodId = Integer.valueOf(str2);
			} catch (Exception e) {
				errorMsgs.add("會員編號或商品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/favoritelist/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			FavoriteListService favoriteListSvc = new FavoriteListService();
			FavoriteListVO favoriteListVO = favoriteListSvc.getOneFavorite(memId, prodId);
			if (favoriteListVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/favoritelist/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("favoriteListVO", favoriteListVO);
			String url = "/favoritelist/listOneFavoriteList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.接收請求參數****************************************/
//				Integer prodId = Integer.valueOf(req.getParameter("prodId"));
//				
//				/***************************2.開始查詢資料****************************************/
//				FavoriteListService favorieListSvc = new FavoriteListService();
//				FavoriteListVO favorieListVO = favorieListSvc.getOneFavorite(memId, prodId);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("favorieListVO", favorieListVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/favoritelist/update_favorieList_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_cart_input.jsp
//				successView.forward(req, res);
//		}
//		

//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());
//				
////			String prodId = req.getParameter("prodId");
////			String prodIdReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
////			if (prodId == null || prodId.trim().length() == 0) {
////				errorMsgs.add("商品編號: 請勿空白");
////			} else if(!prodId.trim().matches(prodIdReg)) { //以下練習正則(規)表示式(regular-expression)
////				errorMsgs.add("商品編號: 只能是數字");
////			}
////				
//			
//			
//			
////			Integer smgrStatus = Integer.valueOf(req.getParameter("smgrStatus"));
//
//			FavoriteListVO favoriteListVO = new FavoriteListVO();
//			favoriteListVO.setMemId(memId);
//			favoriteListVO.setProdId(prodId);
//	
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("favoriteListVO", favoriteListVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/favoritelist/update_favoriteList_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				FavoriteListService favoriteListSvc = new FavoriteListService();
//				favoriteListVO = favoriteListSvc.updateFavoriteList(memId, prodId);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("favoriteListVO", favoriteListVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/favoritelist/listOneFavoritelist.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//		}

		if ("insert".equals(action)) { // 來自addSmg.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer memId = null;
			Integer prodId = null;
			try {
				memId = Integer.valueOf(req.getParameter("memId"));
			} catch (Exception e) {
				errorMsgs.add("會員編號請填數字");
			}

			try {
				prodId = Integer.valueOf(req.getParameter("prodId"));
			} catch (Exception e) {
				errorMsgs.add("商品編號請填數字");
			}

//				Integer SmgId = Integer.valueOf(req.getParameter("SmgId").trim());

			FavoriteListVO favoriteListVO = new FavoriteListVO();
			favoriteListVO.setMemId(memId);
			favoriteListVO.setProdId(prodId);

//				SmgVO.setSmgId(SmgId);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("favoriteListVO", favoriteListVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/favoritelist/addFavoriteList.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			FavoriteListService favoriteListSvc = new FavoriteListService();
			favoriteListVO = favoriteListSvc.addFavoriteList(memId, prodId);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/favoritelist/listAllFavoriteList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//		}
	}
}
