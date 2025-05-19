<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodtimetest.cart.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  CartVO cartVO = (CartVO) request.getAttribute("cartVO"); //更改為自己的VO //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneCart.jsp</title>
<%--	//更改為自己的--%>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>購物車資料 - listOneCart.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%--//此處需要更改為跟自己表格格式一樣的架構--%>
<table>
	<tr>
		<th>購物車商品編號</th>
		<th>會員編號</th>
		<th>商品編號</th>
	</tr>
	<tr>
<%--		//更改為自己的VO來取值--%>
		<td><%=cartVO.getShopId()%></td>
		<td><%=cartVO.getMemId()%></td>
		<td><%=cartVO.getProdId()%></td>
	</tr>
</table>

</body>
</html>