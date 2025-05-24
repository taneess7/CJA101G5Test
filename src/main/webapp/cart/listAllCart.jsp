<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodtimetest.cart.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    CartService cartSvc = new CartService();
    List<CartVO> list = cartSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
    <title>所有的購物車資料 - listAllCart.jsp</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }
        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }
        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 800px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
    <tr><td>
        <h3>所有購物車資料 - listAllCart.jsp</h3>
        <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
</table>

<table>
    <tr>
        <th>購物車商品編號</th>
        <th>會員編號</th>
        <th>商品編號</th>
        <th>商品數量</th>
        <th>修改</th>
        <th>刪除</th>
    </tr>
    
    <c:forEach var="cartVO" items="${list}">
        <tr>
            <td>${cartVO.shopId}</td>
            <td>${cartVO.memId}</td>
            <td>${cartVO.prodId}</td>
            <td>${cartVO.prodN}</td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart.do" style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="shopId" value="${cartVO.shopId}">
                    <input type="hidden" name="action" value="getOne_For_Update">
                </FORM>
            </td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart.do" style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="shopId" value="${cartVO.shopId}">
                    <input type="hidden" name="action" value="delete">
                </FORM>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>