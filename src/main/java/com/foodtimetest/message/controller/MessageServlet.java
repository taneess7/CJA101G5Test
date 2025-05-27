package com.foodtimetest.message.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;
import java.sql.Timestamp;
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

		// ==================================== 來自select_page.jsp的請求
		// ====================================================================
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
//			if (mesId == null && postId == null && memId == null) {
//				errorMsgs.add("請至少提供一個條件");
//			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MessageService messageSvc = new MessageService();
//			System.out.println("mesId: " + mesId); // debug
			MessageVO messageVO = messageSvc.getOneMessage(mesId);
			if (messageVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("messageVO", messageVO);
			String url = "/message/listOneMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		// ====================================
		// 來自listallMessage.jsp的請求====================================================================
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer mesId = validateIntegerParameter(req, "mesId", errorMsgs, "留言編號不正確");

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/listAllMessage.jsp");
				failureView.forward(req, res);
				return;
			}
			
//		/***************************2.開始查詢資料*****************************************/
			MessageService mesSvc = new MessageService();
			MessageVO messageVO = mesSvc.getOneMessage(mesId);
			System.out.println("mesId = " + mesId);
			System.out.println("DEBUG: messageVO=" + messageVO);
			if (messageVO == null) {
			    errorMsgs.add("查無留言資料，請確認留言編號是否正確");
			    RequestDispatcher failureView = req.getRequestDispatcher("/message/listAllMessage.jsp");
			    failureView.forward(req, res);
			    return;
			}
//		
//		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("messageVO", messageVO);
			String url = "/message/update_message_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// ====================================
		// 來自update_message_input.jsp的請求====================================================================
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer mesId = validateIntegerParameter(req, "mesId", errorMsgs, "留言編號格式不正確");
			Integer postId = validateIntegerParameter(req, "postId", errorMsgs, "貼文編號格式不正確");
			Integer memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");
			java.sql.Timestamp mesDate = new java.sql.Timestamp(System.currentTimeMillis());
			//java.sql.Timestamp mesDate = null;
			String mesContent = req.getParameter("mesContent");

			MessageVO messageVO = new MessageVO();
			messageVO.setMemId(memId);
			messageVO.setPostId(postId);
//			messageVO.setMesDate(mesDate);
			messageVO.setMesContent(mesContent);

			if (mesContent == null || mesContent.trim().length() == 0) {
				errorMsgs.add("留言內容格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/update_message_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MessageService messageSvc = new MessageService();
			messageVO = messageSvc.updateMessage(mesId, postId, memId, mesDate, mesContent);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("messageVO", messageVO);
			String url = "/message/listOneMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// ====================================
		// 來自addMessage.jsp的請求====================================================================
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer mesId = validateIntegerParameter(req, "mesId", errorMsgs, "留言編號格式不正確");
			Integer postId = validateIntegerParameter(req, "postId", errorMsgs, "貼文編號格式不正確");
			Integer memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");
//			java.sql.Timestamp mesDate = null;
			java.sql.Timestamp mesDate = new java.sql.Timestamp(System.currentTimeMillis());
			String mesContent = req.getParameter("mesContent");

			MessageVO messageVO = new MessageVO();
			messageVO.setMemId(memId);
			messageVO.setPostId(postId);
		messageVO.setMesDate(mesDate);
			messageVO.setMesContent(mesContent);

			if (mesContent == null || mesContent.trim().length() == 0) {
				errorMsgs.add("留言內容格式不正確");
			}			
			

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/listAllMessage.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 *****************************************/
			MessageService messageSvc = new MessageService();
			messageVO = messageSvc.addMessage( postId, memId, mesDate, mesContent);
			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
			String url = "/message/listAllMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// ====================================
		// 來自listAllMessage.jsp的請求====================================================================
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer mesId = validateIntegerParameter(req, "mesId", errorMsgs, "留言編號不正確");
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/message/listAllMessage.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始查詢資料 *****************************************/
			MessageService messageSvc = new MessageService();
			messageSvc.deleteMessage(mesId);
			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
			String url = "/message/listAllMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
