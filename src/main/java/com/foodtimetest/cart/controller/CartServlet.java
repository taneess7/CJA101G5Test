package com.foodtimetest.cart.controller;

import com.foodtimetest.cart.model.CartService;
import com.foodtimetest.cart.model.CartVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CartServlet extends HttpServlet {

    // 統一的參數驗證方法
    private Integer validateIntegerParameter(HttpServletRequest req, String paramName, 
                                           List<String> errorMsgs, String errorMessage) {
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




    public void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        System.out.println("=== Cart Servlet 開始執行 ===");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        System.out.println("接收到的 action: " + action);

        //==================================== 來自select_page.jsp的請求 ====================================================================
        if ("getOne_For_Display".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 - 輸入格式的錯誤處理 ********************* */
            
            // 修正：只驗證有提供的參數，允許其他參數為空
            Integer shopId = null;
            Integer memId = null;
            Integer prodId = null;
            
            String shopIdStr = req.getParameter("shopId");
            String memIdStr = req.getParameter("memId");
            String prodIdStr = req.getParameter("prodId");
            
            // 只驗證有提供且不為空的參數
            if (shopIdStr != null && !shopIdStr.trim().isEmpty()) {
                shopId = validateIntegerParameter(req, "shopId", errorMsgs, "商店編號格式不正確");
            }
            
            if (memIdStr != null && !memIdStr.trim().isEmpty()) {
                memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");
            }
            
            if (prodIdStr != null && !prodIdStr.trim().isEmpty()) {
                prodId = validateIntegerParameter(req, "prodId", errorMsgs, "商品編號格式不正確");
            }
            
            // 確保至少有一個查詢條件
            if (shopId == null && memId == null && prodId == null) {
                errorMsgs.add("請至少提供一個查詢條件");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/select_page.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ************************** 2.開始查詢資料 **************************************** */
            CartService cartSvc = new CartService();
            CartVO cartVO = null;
            
            // 根據提供的參數選擇查詢方法
            if (shopId != null) {
                cartVO = cartSvc.getOneCart(shopId);
            } else if (memId != null && prodId != null) {
                cartVO = cartSvc.getByMemIdAndProdId(memId, prodId);
            } else if (memId != null) {
                // 如果只有會員ID，取該會員的第一筆購物車資料
                List<CartVO> cartList = cartSvc.getByMemId(memId);
                if (!cartList.isEmpty()) {
                    cartVO = cartList.get(0);
                }
            }
            
            if (cartVO == null) {
                errorMsgs.add("查無資料");
            }
            
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/select_page.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ************************** 3.查詢完成,準備轉交(Send the Success view) ************ */
            req.setAttribute("cartVO", cartVO);
            String url = "/cart/listOneCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }


        //==================================== 來自listAllCart.jsp的請求 ====================================================================
        if ("getOne_For_Update".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 ****************************************/
            Integer shopId = validateIntegerParameter(req, "shopId", errorMsgs, "商店編號格式不正確");

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/listAllCart.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ************************** 2.開始查詢資料 ****************************************/
            CartService cartSvc = new CartService();
            CartVO cartVO = cartSvc.getOneCart(shopId);

            /* ************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("cartVO", cartVO);
            String url = "/cart/update_cart_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        //==================================== 來自update_Cart_input.jsp的請求 ====================================================================
        if ("update".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 - 輸入格式的錯誤處理 ********************* */
            Integer shopId = validateIntegerParameter(req, "shopId", errorMsgs, "商店編號格式不正確");
            Integer memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");
            Integer prodId = validateIntegerParameter(req, "prodId", errorMsgs, "商品編號格式不正確");
            Integer prodN = validateIntegerParameter(req, "prodN", errorMsgs, "商品數量格式不正確");

            CartVO cartVO = new CartVO();
            cartVO.setShopId(shopId);
            cartVO.setMemId(memId);
            cartVO.setProdId(prodId);
            cartVO.setProdN(prodN);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("cartVO", cartVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/update_cart_input.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ************************** 2.開始修改資料 **************************************** */
            CartService cartSvc = new CartService();
            cartVO = cartSvc.updateCart(shopId, memId, prodId, prodN);  // 修正：加入 shopId 參數

            /* ************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("cartVO", cartVO);
            String url = "/cart/listOneCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        //==================================== 來自addCart.jsp的請求 ====================================================================
        if ("insert".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /* ********************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************ */
            Integer memId = validateIntegerParameter(req, "memId", errorMsgs, "會員編號格式不正確");
            Integer prodId = validateIntegerParameter(req, "prodId", errorMsgs, "商品編號格式不正確");
            Integer prodN = validateIntegerParameter(req, "prodN", errorMsgs, "商品數量格式不正確");

            CartVO cartVO = new CartVO();
            cartVO.setMemId(memId);
            cartVO.setProdId(prodId);
            cartVO.setProdN(prodN);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("cartVO", cartVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/addCart.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ************************** 2.開始新增資料 ************************************** */
            CartService cartSvc = new CartService();
            cartVO = cartSvc.addCart(memId, prodId, prodN);

            /* ************************** 3.新增完成,準備轉交(Send the Success view) ********** */
            String url = "/cart/listAllCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        //==================================== 來自listAllCart.jsp ====================================================================
        if ("delete".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 ************************************** */
            Integer shopId = validateIntegerParameter(req, "shopId", errorMsgs, "商店編號格式不正確");

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/listAllCart.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ************************** 2.開始刪除資料 ************************************** */
            CartService cartSvc = new CartService();
            cartSvc.deleteCart(shopId);

            /* ************************** 3.刪除完成,準備轉交(Send the Success view) ********** */
            String url = "/cart/listAllCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
    }
}
