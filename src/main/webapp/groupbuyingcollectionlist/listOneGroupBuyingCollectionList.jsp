<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.foodtimetest.groupbuyingcollectionlist.model.*" %>
<%-- 此頁暫練習採用 Scriptlet 的寫法取值 --%>

<%
  GroupBuyingCollectionListVO collectionVO =
      (GroupBuyingCollectionListVO) request.getAttribute("collectionVO");
  if (collectionVO == null) {
      out.println("<p style='color:red;'>找不到該筆收藏資料，請先執行查詢。</p>");
      return;
  }
%>

<html>
<head>
    <title>收藏清單資料 - listOneCollection.jsp</title>

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
        width: 600px;
        background-color: white;
        margin-top: 5px;
        margin-bottom: 5px;
        border-collapse: collapse;
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
<body bgcolor="white">

<h4>此頁暫練習採用 Scriptlet 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>收藏清單資料 - listOneCollection.jsp</h3>
            <h4>
                <a href="select_page.jsp">
                    <img src="images/back1.gif" width="100" height="32" border="0" alt="回首頁"/>
                    回首頁
                </a>
            </h4>
        </td>
    </tr>
</table>

<table>
  <tr><th>團購編號</th><td><%= collectionVO.getGbId() %></td></tr>
  <tr><th>會員編號</th><td><%= collectionVO.getMemId() %></td></tr>
  <tr><th>收藏時間</th><td><%= collectionVO.getCreateAt() %></td></tr>
</table>


</body>
</html>
