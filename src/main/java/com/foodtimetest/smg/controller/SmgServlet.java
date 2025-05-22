package com.foodtimetest.smg.controller;

import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.foodtimetest.smg.model.*;
@WebServlet(name = "smgservlet", urlPatterns = {"/smg/smg.do"})

public class SmgServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("smgId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/smg/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer smgId = null;
				try {
					smgId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/smg/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				SmgService smgSvc = new SmgService();
				SmgVO smgVO = smgSvc.getOneSmg(smgId);
				if (smgVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/smg/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("smgVO", smgVO); 
				String url = "/smg/listOneSmg.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer smgId = Integer.valueOf(req.getParameter("smgId"));
				
				/***************************2.開始查詢資料****************************************/
				SmgService smgSvc = new SmgService();
				SmgVO smgVO = smgSvc.getOneSmg(smgId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("smgVO", smgVO);         // 資料庫取出的empVO物件,存入req
				String url = "/smg/update_smg_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_cart_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer smgId = Integer.valueOf(req.getParameter("smgId").trim());
				
			String smgrName = req.getParameter("smgrName");
			String smgrNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (smgrName == null || smgrName.trim().length() == 0) {
				errorMsgs.add("管理員姓名: 請勿空白");
			} else if(!smgrName.trim().matches(smgrNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
				
			String smgrEmail = req.getParameter("smgrEmail").trim();
			String smgrEmailReg = "^(?!\\.)[\\w!#$%&'*+/=?^`{|}~.-]+(?<!\\.)@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
			if (smgrEmail == null || smgrEmail.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			}else if(!smgrEmail.trim().matches(smgrEmailReg)) {
				errorMsgs.add("信箱格式不符合");
			}
			String smgrAccount = req.getParameter("smgrAccount").trim();

			String smgrPassword = req.getParameter("smgrPassword").trim();
			if(smgrPassword == null || smgrPassword.length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			String smgrPhone =req.getParameter("smgrPhone");
			String smgrPhoneReg = "^(?:\\(?0\\d{1,2}\\)?[-\\s]?)?\\d{6,8}$|^09\\d{2}[-\\s]?\\d{3}[-\\s]?\\d{3}$";
			if(smgrPhone == null || smgrPhone.length() == 0) {
				errorMsgs.add("電話請勿空白");
			}else if(!smgrPhone.trim().matches(smgrPhoneReg)) {
				errorMsgs.add("電話格式錯誤");
			}
			Integer smgrStatus = Integer.valueOf(req.getParameter("smgrStatus"));

				SmgVO smgVO = new SmgVO();
				smgVO.setSmgId(smgId);
				smgVO.setSmgrName(smgrName);
				smgVO.setSmgrEmail(smgrEmail);
				smgVO.setSmgrAccount(smgrAccount);	
				smgVO.setSmgrPassword(smgrPassword);
				smgVO.setSmgrPhone(smgrPhone);
				smgVO.setSmgrStatus(smgrStatus);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("smgVO", smgVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/smg/update_smg_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				SmgService smgSvc = new SmgService();
				smgVO = smgSvc.updateSmg(smgId, smgrEmail, smgrAccount, smgrPassword, smgrName, smgrPhone,smgrStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("smgVO", smgVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/smg/listOneSmg.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addSmg.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String smgrName = req.getParameter("smgrName");
				String smgrNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (smgrName == null || smgrName.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!smgrName.trim().matches(smgrNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String smgrEmail = req.getParameter("smgrEmail").trim();
				String smgrEmailReg = "^(?!\\.)[\\w!#$%&'*+/=?^`{|}~.-]+(?<!\\.)@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
				if (smgrEmail == null || smgrEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}else if(!smgrEmail.trim().matches(smgrEmailReg)) {
					errorMsgs.add("信箱格式不符合");
				}
				String smgrAccount = req.getParameter("smgrAccount").trim();
				if (smgrAccount == null || smgrAccount.length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}else {
					SmgService smgSvc = new SmgService();
				    if (smgSvc.isAccountExists(smgrAccount)) {
				        errorMsgs.add("帳號已存在，請更換其他帳號");
				    }
				}
				String smgrPassword = req.getParameter("smgrPassword").trim();
				if(smgrPassword == null || smgrPassword.length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				String smgrPhone =req.getParameter("smgrPhone");
				String smgrPhoneReg = "^(?:\\(?0\\d{1,2}\\)?[-\\s]?)?\\d{6,8}$|^09\\d{2}[-\\s]?\\d{3}[-\\s]?\\d{3}$";
				if(smgrPhone == null || smgrPhone.length() == 0) {
					errorMsgs.add("手機請勿空白");
				}else if(!smgrPhone.trim().matches(smgrPhoneReg)) {
					errorMsgs.add("手機格式錯誤");
				}
//				Integer SmgId = Integer.valueOf(req.getParameter("SmgId").trim());

				SmgVO smgVO = new SmgVO();
				smgVO.setSmgrName(smgrName);
				smgVO.setSmgrEmail(smgrEmail);
				smgVO.setSmgrAccount(smgrAccount);
				smgVO.setSmgrPassword(smgrPassword);
				smgVO.setSmgrPhone(smgrPhone);
//				SmgVO.setSmgId(SmgId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("smgVO", smgVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/smg/addSmg.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				SmgService smgSvc = new SmgService();
				smgVO = smgSvc.addSmg(smgrEmail, smgrAccount, smgrPassword, smgrName, smgrPhone);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/smg/listAllSmg.jsp";
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
