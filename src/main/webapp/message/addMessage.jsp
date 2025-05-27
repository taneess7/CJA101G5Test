<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodtimetest.message.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
MessageVO messageVO = (MessageVO) request.getAttribute("messageVO");
%>
--<%=messageVO == null%>--${messageVO.mesId}--
<!-- for line 100 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>留言資料新增 - addMessage.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>留言資料新增 - addMessage.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="message.do" name="form1">
		<table>




			<tr>
				<td>貼文編號:</td>
				<td><input type="text" name="postId"
					value="<%=(messageVO == null) ? "1" : messageVO.getPostId()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="text" name="memId"
					value="<%=(messageVO == null) ? "1" : messageVO.getMemId()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>留言日期: 系統會幫你抓時間喔</td>
<%--				<td><input name="mesDate" id="mesDate" type="text"></td>--%>
			</tr>
			<tr>
				<td>留言內容:</td>
				<td><input type="text" name="mesContent"
					value="<%=(messageVO == null) ? "12345" : messageVO.getMesContent()%>"
					size="45" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>





	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

	<%
	java.sql.Timestamp mesDate = null;
	try {
		mesDate = messageVO.getMesDate();
	} catch (Exception e) {
		mesDate = new java.sql.Timestamp(System.currentTimeMillis());
	}
	%>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

	<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

	<script>
        $.datetimepicker.setLocale('zh');
        $('#mesDate').datetimepicker({ //日期選擇器ID不匹配
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
	       value:   new Date(),
	       minDate: 0
		});  //你的結尾不見了
</script>
</body>
</html>