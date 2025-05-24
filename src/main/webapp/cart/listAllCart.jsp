<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodtimetest.cart.model.*"%>

<%
    CartService cartSvc = new CartService();
    List<CartVO> list = cartSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>所有購物車資料</title>
    <link rel="stylesheet" href="css/modern-cart.css">
</head>
<body>
    <div class="container">
        <!-- 頁面標題 -->
        <div class="page-header">
            <h1>📋 所有購物車資料</h1>
            <a href="select_page.jsp" class="nav-button">
                🏠 回到首頁
            </a>
        </div>

        <!-- 統計資訊 -->
        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-number">${list.size()}</div>
                <div class="stat-label">總購物車項目</div>
            </div>
        </div>

        <!-- 資料表格 -->
        <div class="card">
            <table class="modern-table">
                <thead>
                    <tr>
                        <th>🛒 購物車編號</th>
                        <th>👤 會員編號</th>
                        <th>📦 商品編號</th>
                        <th>🔢 商品數量</th>
                        <th>⚙️ 操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cartVO" items="${list}">
                        <tr>
                            <td><strong>${cartVO.shopId}</strong></td>
                            <td>${cartVO.memId}</td>
                            <td>${cartVO.prodId}</td>
                            <td>
                                <span style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); 
                                           color: white; padding: 5px 10px; border-radius: 15px; font-weight: 600;">
                                    ${cartVO.prodN}
                                </span>
                            </td>
                            <td>
                                <div class="action-buttons">
                                    <form method="post" action="<%=request.getContextPath()%>/cart/cart.do" class="action-form">
                                        <input type="hidden" name="shopId" value="${cartVO.shopId}">
                                        <input type="hidden" name="action" value="getOne_For_Update">
                                        <button type="submit" class="btn btn-warning btn-sm">✏️ 修改</button>
                                    </form>
                                    
                                    <form method="post" action="<%=request.getContextPath()%>/cart/cart.do" class="action-form">
                                        <input type="hidden" name="shopId" value="${cartVO.shopId}">
                                        <input type="hidden" name="action" value="delete">
                                        <button type="submit" class="btn btn-danger btn-sm" 
                                                onclick="return confirm('確定要刪除這個項目嗎？')">🗑️ 刪除</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
