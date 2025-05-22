package com.foodtimetest.cart.controller;

import com.foodtimetest.cart.model.CartService;
import com.foodtimetest.cart.model.CartVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class CartServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Cart Servlet");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 - 輸入格式的錯誤處理 ********************* */
            String str = req.getParameter("shopId"); //更改為自己的PK
            if (str == null || (str.trim()).isEmpty()) {
                errorMsgs.add("請輸入商品編號"); //更改為自己的PK名稱
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer shopId = null; //更改為自己的PK
            try {
                shopId = Integer.valueOf(str); //更改為自己的PK
            } catch (Exception e) {
                errorMsgs.add("商品編號格式不正確"); //更改為自己的PK錯誤訊息
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/select_page.jsp"); //更改為自己的專案目錄路徑
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /* ************************** 2.開始查詢資料 **************************************** */
            CartService CartSvc = new CartService(); //更改為自己的Service
            CartVO cartVO = CartSvc.getOneCart(shopId); //更改為自己的Service的方法
            if (cartVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/select_page.jsp"); //更改為自己的選擇路徑
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /* ************************** 3.查詢完成,準備轉交(Send the Success view) ************ */
            req.setAttribute("cartVO", cartVO); //更改為自己的PK                                資料庫取出的cartVO物件,存入req
            String url = "/cart/listOneCart.jsp"; //更改為自己的查詢單一資料的頁面
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCart.jsp
            successView.forward(req, res);
        }
//==================================== 來自listAllCart.jsp的請求 ====================================================================
        if ("getOne_For_Update".equals(action)) { // 來自listAllCart.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 ****************************************/
            Integer shopId = Integer.valueOf(req.getParameter("shopId"));

            /* ************************** 2.開始查詢資料 ****************************************/
            CartService cartSvc = new CartService();
            CartVO cartVO = cartSvc.getOneCart(shopId);

            /* ************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("cartVO", cartVO); // 資料庫取出的cartVO物件,存入req
            String url = "/cart/update_cart_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_cart_input.jsp
            successView.forward(req, res);
        }
//==================================== 來自update_Cart_input.jsp的請求 ====================================================================
        if ("update".equals(action)) { // 來自update_Cart_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 - 輸入格式的錯誤處理 ********************* */
            Integer shopId = Integer.valueOf(req.getParameter("shopId").trim());
//==================================== 處理會員編號 ====================================================================
            Integer memId = Integer.valueOf(req.getParameter("memId").trim());
//==================================== 處理商品編號 ====================================================================
            Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());
//==================================== 處理商品價格 ====================================================================
            Integer prodN = Integer.valueOf(req.getParameter("prodN").trim());

            CartVO cartVO = new CartVO();
            cartVO.setShopId(shopId);
            cartVO.setMemId(memId);
            cartVO.setProdId(prodId);
            cartVO.setProdN(prodN);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("cartVO", cartVO); // 含有輸入格式錯誤的cartVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/update_cart_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /* ************************** 2.開始修改資料 **************************************** */
            CartService cartSvc = new CartService();
            cartVO = cartSvc.updateCart(shopId, memId, prodId);

            /* ************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("cartVO", cartVO); // 資料庫update成功後,正確的的cartVO物件,存入req
            String url = "/cart/listOneCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCart.jsp
            successView.forward(req, res);
        }
//==================================== 來自addCart.jsp的請求 ====================================================================
        if ("insert".equals(action)) { // 來自addCart.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /* ********************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************ */
            Integer shopId = Integer.valueOf(req.getParameter("shopId").trim());
//==================================== 處理會員編號 ====================================================================
            Integer memId = Integer.valueOf(req.getParameter("memId").trim());
//==================================== 處理商品編號 ====================================================================
            Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());
//==================================== 處理商品價格 ====================================================================
            Integer prodN = Integer.valueOf(req.getParameter("prodN").trim());

            CartVO cartVO = new CartVO();
            cartVO.setShopId(shopId);
            cartVO.setMemId(memId);
            cartVO.setProdId(prodId);
            cartVO.setProdN(prodN);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("cartVO", cartVO); // 含有輸入格式錯誤的cartVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/cart/addCart.jsp");
                failureView.forward(req, res);
                return;
            }

            /* ************************** 2.開始新增資料 ************************************** */
            CartService cartSvc = new CartService();
            cartVO = cartSvc.addCart( memId, prodId , prodN);

            /* ************************** 3.新增完成,準備轉交(Send the Success view) ********** */
            String url = "/cart/listAllCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCart.jsp
            successView.forward(req, res);
        }
//==================================== 來自listAllCart.jsp ====================================================================
        if ("delete".equals(action)) { // 來自listAllCart.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /* ************************** 1.接收請求參數 ************************************** */
            Integer shopId = Integer.valueOf(req.getParameter("shopID").trim());

            /* ************************** 2.開始刪除資料 ************************************** */
            CartService cartSvc = new CartService();
            cartSvc.deleteCart(shopId);

            /* ************************** 3.刪除完成,準備轉交(Send the Success view) ********** */
            String url = "/cart/listAllCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
    }
}
