<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodtimetest.cart.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CartVO cartVO = (CartVO) request.getAttribute("cartVO");
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>購物車商品詳細資料</title>
<link rel="stylesheet" href="css/modern-cart.css">

</head>
<body>
	<div class="container">
		<!-- 頁面標題 -->
		<div class="page-header">
			<h1>🛒 購物車商品詳細資料</h1>
			<p class="subtitle">查看購物車項目的完整資訊</p>
		</div>

		<!-- 功能說明 -->
		<div class="info-badge">📝 此頁面採用 JSP Script 的寫法取值</div>

		<!-- 商品詳細資料卡片 -->
		<div class="detail-card">
			<h3 style="color: #2c3e50; margin-bottom: 25px; text-align: center;">
				📦 商品資訊詳情</h3>

			<table class="detail-table">
				<thead>
					<tr>
						<th>🛒 購物車商品編號</th>
						<th>👤 會員編號</th>
						<th>📦 商品編號</th>
						<th>🔢 商品數量</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><span class="data-highlight"> <%=cartVO.getShopId()%>
						</span></td>
						<td><span style="color: #667eea; font-weight: 600;"> <%=cartVO.getMemId()%>
						</span></td>
						<td><span style="color: #764ba2; font-weight: 600;"> <%=cartVO.getProdId()%>
						</span></td>
						<td><span class="data-highlight"
							style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);">
								<%=cartVO.getProdN()%> 件
						</span></td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 詳細資訊卡片 -->
		<div class="card">
			<h3>📊 項目統計資訊</h3>
			<div class="stats-grid" style="margin-top: 20px;">
				<div class="stat-card"
					style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
					<div class="stat-number"><%=cartVO.getShopId()%></div>
					<div class="stat-label">購物車編號</div>
				</div>
				<div class="stat-card"
					style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);">
					<div class="stat-number"><%=cartVO.getMemId()%></div>
					<div class="stat-label">會員編號</div>
				</div>
				<div class="stat-card"
					style="background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);">
					<div class="stat-number"><%=cartVO.getProdId()%></div>
					<div class="stat-label">商品編號</div>
				</div>
				<div class="stat-card"
					style="background: linear-gradient(135deg, #a8edea 0%, #fed6e3 100%); color: #2c3e50;">
					<div class="stat-number"><%=cartVO.getProdN()%></div>
					<div class="stat-label">購買數量</div>
				</div>
			</div>
		</div>

		<!-- 操作按鈕區域 -->
		<div class="action-section">
			<a href="select_page.jsp" class="nav-button"
				style="margin-right: 15px;"> 🏠 回到首頁 </a> <a href="listAllCart.jsp"
				class="btn btn-primary" style="margin-right: 15px;"> 📋 查看所有購物車
			</a>

			<form method="post"
				action="<%=request.getContextPath()%>/cart/cart.do"
				style="display: inline-block; margin-right: 15px;">
				<input type="hidden" name="shopId" value="<%=cartVO.getShopId()%>">
				<input type="hidden" name="action" value="getOne_For_Update">
				<button type="submit" class="btn btn-warning">✏️ 修改此項目</button>
			</form>

			<form method="post"
				action="<%=request.getContextPath()%>/cart/cart.do"
				style="display: inline-block;">
				<input type="hidden" name="shopId" value="<%=cartVO.getShopId()%>">
				<input type="hidden" name="action" value="delete">
				<button type="submit" class="btn btn-danger"
					onclick="return confirm('確定要刪除這個購物車項目嗎？')">🗑️ 刪除項目</button>
			</form>
		</div>

		<!-- 購物車資訊摘要 -->
		<div class="card" style="margin-top: 30px;">
			<h3>📝 購物車項目摘要</h3>
			<div
				style="background: #f8f9ff; padding: 20px; border-radius: 10px; margin-top: 15px;">
				<p
					style="margin: 0; color: #2c3e50; font-size: 1.1rem; line-height: 1.6;">
					<strong>會員 <%=cartVO.getMemId()%></strong> 在購物車 <strong
						style="color: #667eea;"><%=cartVO.getShopId()%></strong> 中，
					選購了商品編號 <strong style="color: #764ba2;"><%=cartVO.getProdId()%></strong>，
					數量為 <strong style="color: #fa709a;"><%=cartVO.getProdN()%>
						件</strong>。
				</p>
			</div>
		</div>
	</div>

	<!-- 浮動操作按鈕（手機版友善） -->
	<div style="position: fixed; bottom: 20px; right: 20px; z-index: 1000;">
		<a href="select_page.jsp" class="btn btn-primary"
			style="border-radius: 50%; width: 60px; height: 60px; display: flex; align-items: center; justify-content: center; font-size: 1.5rem; box-shadow: 0 8px 25px rgba(102, 126, 234, 0.4);">
			🏠 </a>
	</div>
</body>
</html>
