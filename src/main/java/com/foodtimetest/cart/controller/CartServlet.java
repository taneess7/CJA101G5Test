package com.foodtimetest.cart.controller;

import com.foodtimetest.cart.model.CartVO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.foodtimetest.cart.model.CartService;
public class CartServlet extends HttpServlet {

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
            String str = req.getParameter("shopId"); //更改為自己的PK
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入商品編號"); //更改為自己的PK名稱
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
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

            /*************************** 2.開始查詢資料 *****************************************/
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

            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("cartVO", cartVO); //更改為自己的PK                                資料庫取出的empVO物件,存入req
            String url = "/cart/listOneCart.jsp"; //更改為自己的查詢單一資料的頁面
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCart.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer shopId = Integer.valueOf(req.getParameter("shopId"));

            /*************************** 2.開始查詢資料 ****************************************/
            EmpService empSvc = new EmpService();
            EmpVO empVO = empSvc.getOneEmp(empno);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
            String url = "/emp/update_emp_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer empno = Integer.valueOf(req.getParameter("empno").trim());

            String ename = req.getParameter("ename");
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (ename == null || ename.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String job = req.getParameter("job").trim();
            if (job == null || job.trim().length() == 0) {
                errorMsgs.add("職位請勿空白");
            }

            java.sql.Date hiredate = null;
            try {
                hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
            } catch (IllegalArgumentException e) {
                hiredate = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            Double sal = null;
            try {
                sal = Double.valueOf(req.getParameter("sal").trim());
            } catch (NumberFormatException e) {
                sal = 0.0;
                errorMsgs.add("薪水請填數字.");
            }

            Double comm = null;
            try {
                comm = Double.valueOf(req.getParameter("comm").trim());
            } catch (NumberFormatException e) {
                comm = 0.0;
                errorMsgs.add("獎金請填數字.");
            }

            Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

            EmpVO empVO = new EmpVO();
            empVO.setEmpno(empno);
            empVO.setEname(ename);
            empVO.setJob(job);
            empVO.setHiredate(hiredate);
            empVO.setSal(sal);
            empVO.setComm(comm);
            empVO.setDeptno(deptno);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始修改資料 *****************************************/
            EmpService empSvc = new EmpService();
            empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal, comm, deptno);

            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/emp/listOneCart.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String ename = req.getParameter("ename");
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (ename == null || ename.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String job = req.getParameter("job").trim();
            if (job == null || job.trim().length() == 0) {
                errorMsgs.add("職位請勿空白");
            }

            java.sql.Date hiredate = null;
            try {
                hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
            } catch (IllegalArgumentException e) {
                hiredate = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            Double sal = null;
            try {
                sal = Double.valueOf(req.getParameter("sal").trim());
            } catch (NumberFormatException e) {
                sal = 0.0;
                errorMsgs.add("薪水請填數字.");
            }

            Double comm = null;
            try {
                comm = Double.valueOf(req.getParameter("comm").trim());
            } catch (NumberFormatException e) {
                comm = 0.0;
                errorMsgs.add("獎金請填數字.");
            }

            Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

            EmpVO empVO = new EmpVO();
            empVO.setEname(ename);
            empVO.setJob(job);
            empVO.setHiredate(hiredate);
            empVO.setSal(sal);
            empVO.setComm(comm);
            empVO.setDeptno(deptno);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/
            EmpService empSvc = new EmpService();
            empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/emp/listAllEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }

        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer empno = Integer.valueOf(req.getParameter("empno"));

            /*************************** 2.開始刪除資料 ***************************************/
            EmpService empSvc = new EmpService();
            empSvc.deleteEmp(empno);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/emp/listAllEmp.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
    }
}
