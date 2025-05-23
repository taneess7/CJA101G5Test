<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodtimetest.member.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemberVO memVO = (MemberVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - listOneMem.jsp</title>

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
		 <h3>會員資料 - listOneMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員郵箱</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員暱稱</th>
		<th>會員姓名</th>
		<th>會員電話</th>
		<th>會員性別</th>
		<th>會員縣市</th>
		<th>會員鄉鎮市區</th>
		<th>會員居住街道</th>
		<th>會員驗證碼</th>
		<th>會員頭像</th>
		<th>會員註冊時間</th>
		<th>會員狀態</th>
		<th>會員是否禁止發言</th>
		<th>會員是否禁止發文</th>
		<th>會員是否禁止成團</th>
		<th>會員是否加入團購</th>
	</tr>
	<tr>
		<td><%=memVO.getMemId()%></td>
		<td><%=memVO.getMemEmail()%></td>
		<td><%=memVO.getMemAccount()%></td>
		<td><%=memVO.getMemPassword()%></td>
		<td><%=memVO.getMemNickname()%></td>
		<td><%=memVO.getMemName()%></td>
		<td><%=memVO.getMemPhone()%></td>
		<td><%=memVO.getMemGender()%></td>
		<td><%=memVO.getMemCity()%></td>
		<td><%=memVO.getMemCityarea()%></td>
		<td><%=memVO.getMemAddress()%></td>
		<td><%=memVO.getMemCode()%></td>
		<td><%=memVO.getMemAvatar()%></td>
		<td><%=memVO.getMemTime()%></td>
		<td><%=memVO.getMemStatus()%></td>
		<td><%=memVO.getMemNoSpeak()%></td>
		<td><%=memVO.getMemNoPost()%></td>
		<td><%=memVO.getMemNoGroup()%></td>
		<td><%=memVO.getMemNoJoingroup()%></td>
	</tr>
</table>

</body>
</html>