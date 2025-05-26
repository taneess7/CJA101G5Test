package com.foodtimetest.message.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;
import java.util.LinkedList;
import java.util.List;

import com.foodtimetest.message.model.MessageService;
import com.foodtimetest.message.model.MessageVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MessageServlet extends HttpServlet {

	private Integer validateIntegerParameter(HttpServletRequest req, String paramName, List<String> errorMsgs,
			String errorMessage) {
		try {
			String paramValue = req.getParameter(paramName);
			if (paramValue == null || paramValue.trim().isEmpty()) {
				errorMsgs.add(paramName + "不能為空");
				return null;
			}
			return Integer.valueOf(paramValue.trim());
		} catch (NumberFormatException e) {
			errorMsgs.add(errorMessage);
			return null;
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		 //==================================== 來自select_page.jsp的請求 ====================================================================
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/* ************************** 1.接收請求參數 - 輸入格式的錯誤處理 ********************* */
			Integer mesId = null;
			Integer postId = null;
			Integer memId = null;

			String mesIdStr = req.getParameter("mesId");
			String postIdStr = req.getParameter("postId");
			String memIdStr = req.getParameter("memId");
			String mesDateStr = req.getParameter("mesDate");
			String mesContentStr = req.getParameter("mesContent");
			
			if (mesIdStr != null && !mesIdStr.trim().isEmpty()) {
				mesId = validateIntegerParameter(req, "mesId", errorMsgs, "留言編號不正確");
			}
			
			if (postIdStr != null && !postIdStr.trim().isEmpty()) {
				postId = validateIntegerParameter(req, "postId", errorMsgs, "貼文編號不正確");
			}
			
			if (memIdStr != null && !memIdStr.trim().isEmpty()) {
				memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號不正確");
			}
			if(mesId == null && postId == null && memId == null) {
				errorMsgs.add("請至少提供一個條件");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始查詢資料*****************************************/
			MessageService messageSvc = new MessageService();
			MessageVO messageVO = messageSvc.getOneMessage(mesId);
			if(messageVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("messageVO", messageVO);
			String url = "/message/listOneMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		 //==================================== 來自listallmessage.jsp的請求 ====================================================================
		
//		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			Integer mesId = validateIntegerParameter(req, "mesId", errorMsgs, "留言編號不正確");
//		/***************************2.開始查詢資料*****************************************/
//		MessageService mesSvc = new MessageService();
//		MessageVO messageVO = mesSvc.getOneMessage(mesId);
//		
//		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//		req.setAttribute("messageVO", messageVO);
//		String
	}

}
