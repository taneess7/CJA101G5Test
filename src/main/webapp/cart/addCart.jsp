<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="com.foodtimetest.cart.model.*"%>

<% 
    CartVO cartVO = (CartVO) request.getAttribute("cartVO");
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增購物車項目</title>
    <link rel="stylesheet" href="css/modern-cart.css">
</head>
<body>
    <div class="container">
        <!-- 頁面標題 -->
        <div class="page-header">
            <h1>➕ 新增購物車項目</h1>
            <a href="select_page.jsp" class="nav-button">
                🏠 回到首頁
            </a>
        </div>

        <!-- 錯誤訊息 -->
        <c:if test="${not empty errorMsgs}">
            <div class="error-container">
                <h4>⚠️ 請修正以下錯誤：</h4>
                <ul class="error-list">
                    <c:forEach var="message" items="${errorMsgs}">
                        <li>${message}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <!-- 新增表單 -->
        <div class="modern-form">
            <h3>📝 填寫商品資訊</h3>
            <form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
                <div class="form-group">
                    <label for="memId">👤 會員編號</label>
                    <input type="text" id="memId" name="memId" class="form-control" 
                           value="<%= (cartVO==null)? "" : cartVO.getMemId()%>" 
                           placeholder="請輸入會員編號" required>
                </div>

                <div class="form-group">
                    <label for="prodId">📦 商品編號</label>
                    <input type="text" id="prodId" name="prodId" class="form-control" 
                           value="<%= (cartVO==null)? "" : cartVO.getProdId()%>" 
                           placeholder="請輸入商品編號" required>
                </div>

                <div class="form-group">
                    <label for="prodN">🔢 商品數量</label>
                    <input type="number" id="prodN" name="prodN" class="form-control" 
                           value="<%= (cartVO==null)? "1" : cartVO.getProdN()%>" 
                           min="1" placeholder="請輸入商品數量" required>
                </div>

                <input type="hidden" name="action" value="insert">
                <button type="submit" class="btn btn-success">✅ 送出新增</button>
            </form>
        </div>
    </div>
</body>
</html>
