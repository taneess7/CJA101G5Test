<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodtimetest.groupbuyingcollectionlist.model.*"%>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>所有團購收藏清單</title>
  <style>
    table { width: 80%; margin:20px auto; border-collapse: collapse; }
    th, td { border:1px solid #ccc; padding:8px; text-align:center; }
    th { background:#f4f4f4; }
  </style>
</head>
<body>
  <h2 style="text-align:center;">所有團購收藏清單</h2>

  <c:if test="${empty allCollections}">
    <p style="text-align:center;">目前沒有任何收藏！</p>
  </c:if>

  <c:if test="${not empty allCollections}">
    <table>
      <thead>
        <tr>
          <th>團購編號 (gbId)</th>
          <th>會員編號 (memId)</th>
          <th>收藏時間 (createAt)</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="vo" items="${allCollections}">
          <tr>
            <td>${vo.gbId}</td>
            <td>${vo.memId}</td>
            <td><fmt:formatDate value="${vo.createAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
              <form method="post" action="groupbuyingcollectionlist.do">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="gbId"   value="${vo.gbId}"/>
                <input type="hidden" name="memId"  value="${vo.memId}"/>
                <button type="submit" onclick="return confirm('確定刪除此收藏？');">
                  刪除
                </button>
              </form>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:if>
</body>
</html>
