<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>foodtime GroupBuyingCollectionList: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>foodtime GroupBuyingCollectionList: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for foodtime
		GroupBuyingCollectionList:Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllGroupBuyingCollectionList.jsp'>List</a> all
			GroupBuyingCollectionLists. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="groupbuyingcollectionlist.do">
				<b>輸入會員編號 (如1):</b> <input type="text" name="memId"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="collectionSvc" scope="page"
			class="com.foodtimetest.groupbuyingcollectionlist.model.GroupBuyingCollectionListService" />

		<li>
			<FORM METHOD="post" ACTION="groupbuyingcollectionlist.do">
				<label for="memId">會員編號：</label> <select name="memId" id="memId">
					<c:forEach var="collectionVO" items="${allCollections}">
						<option value="${collectionVO.memId}">
							${collectionVO.memId}</option>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display" />
				<button type="submit">送出</button>
			</form>
			
		</li>
				
		<li>
			<FORM METHOD="post" ACTION="groupbuyingcollectionlist.do">
				<!-- 下拉選單：選團購編號 -->
				<label for="gbId">團購編號：</label> <select name="gbId" id="gbId">
					<c:forEach var="collectionVO" items="${allCollections}">
						<option value="${collectionVO.gbId}">
							${collectionVO.gbId}</option>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display" />
				<button type="submit">送出</button>
			</form>
		</li>
		
			
<!-- 
			<h4>查詢是否已收藏</h4>
			<form method="post" action="GroupBuyingCollectionListServlet">
				<label for="gbId">團購編號 (gbId)：</label> <input type="text" id="gbId"
					name="gbId" /><br /> <br /> <label for="memId">會員編號
					(memId)：</label> <input type="text" id="memId" name="memId" /><br /> <br />
				<input type="hidden" name="action" value="getOne_For_Display" />
				<button type="submit">查詢</button>
			</form>
 -->	
 </ul>	
<h3>收藏清單管理</h3>

		<ul>
		  <li><a href='addGroupBuyingCollectionList.jsp'>Add</a> a new GroupBuyingCollectionList.</li>
		</ul>
 	
</body>
</html>
