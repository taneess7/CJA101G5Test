package com.foodtimetest.groupbuyingcollectionlist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.foodtimetest.groupbuyingcollectionlist.model.GroupBuyingCollectionListService;
import com.foodtimetest.groupbuyingcollectionlist.model.GroupBuyingCollectionListVO;

public class GroupBuyingCollectionListServlet extends HttpServlet {

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

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("=== GroupBuyingCollectionList Servlet 開始執行 ===");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("接收到的 action: " + action);
		
		
		
		
		//-----------查全部資料------------
		
		if ("listAll".equals(action)) {
		    // 1. 撈出所有收藏清單
		    GroupBuyingCollectionListService collectionSvc = new GroupBuyingCollectionListService();
		    List<GroupBuyingCollectionListVO> allCollections = collectionSvc.getAll();  

		    // 2. 放入 request
		    req.setAttribute("allCollections", allCollections);

		    // 3. 轉交到 JSP
		    RequestDispatcher rd = req.getRequestDispatcher(
		        "/groupbuyingcollectionlist/listAllGroupBuyingCollectionList.jsp"
		    );
		    rd.forward(req, res);
		    return;
		}


		
		
		
		
		
		
		
		
		
		
		
		
		
		

		// ================================來自select_page.jsp的請求==========================================

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* ************************** 1.接收請求參數 - 輸入格式的錯誤處理 ********************* */

			// 修正：只驗證有提供的參數，允許其他參數為空
			Integer gbId = null;
			Integer memId = null;

			String gbIdStr = req.getParameter("gbId");
			String memIdStr = req.getParameter("memId");

			// 只驗證有提供且不為空的參數
			if (gbIdStr != null && !gbIdStr.trim().isEmpty()) {
				gbId = validateIntegerParameter(req, "gbId", errorMsgs, "團購編號格式不正確");
			}

			if (memIdStr != null && !memIdStr.trim().isEmpty()) {
				memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");
			}

			// 至少要有一個條件
			if (gbId == null && memId == null) {
				errorMsgs.add("請至少提供一個查詢條件");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/* ************************* 2.開始查詢資料 ************************************ */
			GroupBuyingCollectionListService collectionSvc  = new GroupBuyingCollectionListService();
			GroupBuyingCollectionListVO collectionVO  = null;

			// 根據提供的參數選擇查詢方法
			if (gbId != null && memId != null) {
			    // 用雙主鍵查詢
				collectionVO  = collectionSvc.getOneCollection(gbId, memId);
			} else if (memId != null) {
			    // 依會員查詢多筆
			    List<GroupBuyingCollectionListVO> list = collectionSvc.getByMemId(memId);
			    if (!list.isEmpty()) {
			    	collectionVO = list.get(0); // 取第一筆示範
			    }
			} 
			if (collectionVO == null) {
                errorMsgs.add("查無資料");
            }
            
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/select_page.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ********************** 3.查詢完成,準備轉交(Send the Success view) ************ */
            
            req.setAttribute("collectionVO", collectionVO);
            String url = "/groupbuyingcollectionlist/listOneGroupBuyingCollectionList.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
			
		}
				  
		//==================================== 來自 listAllGroupBuyingCollectionList.jsp 的請求 ===========================================
		if ("getOne_For_Update".equals(action)) {
		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    /* ************************** 1. 接收請求參數 ****************************************/
		    Integer gbId = validateIntegerParameter(req, "gbId", errorMsgs, "團購編號格式不正確");
		    Integer memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/listAllGroupBuyingCollectionList.jsp");
		        failureView.forward(req, res);
		        return;
		    }

		    /* ************************** 2. 開始查詢資料 ****************************************/
		    GroupBuyingCollectionListService collectionSvc  = new GroupBuyingCollectionListService();
		    GroupBuyingCollectionListVO  collectionVO  = collectionSvc.getOneCollection(gbId, memId);

		    /* ************************** 3. 查詢完成, 準備轉交(Send the Success view) ************/
		    req.setAttribute("collectionVO", collectionVO);
		    String url = "/groupbuyingcollectioniist/listAllGroupBuyingCollectionList.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}

		//沒有修改功能
		
		
		//==================================== 來自 addGroupBuyingCollectionList.jsp 的請求 ===========================================
		if ("insert".equals(action)) {
		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    /* ********************** 1. 接收請求參數 - 輸入格式的錯誤處理 ************************ */
		    Integer gbId = validateIntegerParameter(req, "gbId", errorMsgs, "團購編號格式不正確");
		    Integer memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");

		    GroupBuyingCollectionListVO  collectionVO  = new GroupBuyingCollectionListVO ();
		    collectionVO .setGbId(gbId);
		    collectionVO .setMemId(memId);

		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("collectionVO", collectionVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/addGroupBuyingCollectionList.jsp");
		        failureView.forward(req, res);
		        return;
		    }

		    /* ************************** 2. 開始新增資料 ************************************** */
		    GroupBuyingCollectionListService  collectionSvc  = new GroupBuyingCollectionListService();
		    collectionVO  = collectionSvc.addCollection(gbId, memId);

		    /* ************************** 3. 新增完成, 準備轉交(Send the Success view) ********** */
		    String url = "/groupbuyingcollectionlist/listAllGroupBuyingCollectionList.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}

		
		 //==================================== 來自listAllGroupBuyingCollectionList.jsp ====================================================================
        if ("delete".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            
            /* ************************** 1. 接收請求參數 ************************************** */
            Integer gbId = validateIntegerParameter(req, "gbId", errorMsgs, "團購編號格式不正確");
            Integer memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/groupbuyingcollectionlist/listAllGroupBuyingCollectionList.jsp");
                failureView.forward(req, res);
                return;
            }
            
            /* ************************** 2. 開始刪除資料 ************************************** */
            GroupBuyingCollectionListService collectionSvc = new GroupBuyingCollectionListService();
            collectionSvc.deleteCollection(gbId, memId);

            /* ************************** 3. 刪除完成，準備轉交(Send the Success view) ********** */
            String url = "/groupbuyingcollectionlist/listAllGroupBuyingCollectionList.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
		
        
        
        
        
        
        
        
        
        
	}
}













