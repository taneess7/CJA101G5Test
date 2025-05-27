<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodtimetest.groupbuyingcollectionlist.model.GroupBuyingCollectionListVO" %>

<%
  // 取得錯誤輸入後存入 request 的 VO
  GroupBuyingCollectionListVO collectionVO = (GroupBuyingCollectionListVO) request.getAttribute("collectionVO");
%>

<html>
<head>
<title>新增收藏清單 - addGroupBuyingCollectionList.jsp</title>

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
    padding: 5px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr>
	  <td>
	    <h3>新增收藏清單</h3>
	  </td>
	  <td>
	    <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	  </td>
	</tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤訊息顯示區 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="groupbuyingcollectionlist.do" name="form1">
  <table>
	<tr>
		<td>團購編號 (gbId):</td>
		<td><input type="text" name="gbId"
			value="<%= (collectionVO == null) ? "" : collectionVO.getGbId() %>" size="40" /></td>
	</tr>
	<tr>
		<td>會員編號 (memId):</td>
		<td><input type="text" name="memId"
			value="<%= (collectionVO == null) ? "" : collectionVO.getMemId() %>" size="40" /></td>
	</tr>
  </table>

  <br>
  <input type="hidden" name="action" value="insert">
  <input type="submit" value="送出新增">
</FORM>

</body>
</html>
