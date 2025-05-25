<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>購物車管理系統</title>
<link rel="stylesheet" href="css/modern-cart.css">
</head>
<body>
	<div class="container">
		<!-- 頁面標題 -->
		<div class="page-header">
			<h1>🛒 購物車管理系統</h1>
			<p class="subtitle">現代化的購物車管理解決方案</p>
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

		<!-- 快速操作 -->
		<div class="card">
			<h3>📊 快速操作</h3>
			<div style="margin-top: 20px;">
				<a href='listAllCart.jsp' class="btn btn-primary"
					style="margin-right: 15px;"> 📋 查看所有購物車項目 </a> <a
					href='addCart.jsp' class="btn btn-success"> ➕ 新增購物車項目 </a>
			</div>
		</div>

		<!-- 搜尋功能 -->
		<div class="card">
			<h3>🔍 資料查詢</h3>
			<div class="search-section">
				<!-- 查詢方式一 -->
				<div class="search-card">
					<h4>依購物車編號查詢</h4>
					<form method="post"
						action="<%=request.getContextPath()%>/cart/cart.do">
						<div class="form-group">
							<input type="text" name="shopId" class="form-control"
								placeholder="請輸入購物車編號 (例如: 1)">
						</div>
						<input type="hidden" name="action" value="getOne_For_Display">
						<button type="submit" class="btn btn-primary">🔍 查詢</button>
					</form>
				</div>

				<!-- 查詢方式二 -->
				<div class="search-card">
					<h4>依會員編號查詢</h4>
					<form method="post"
						action="<%=request.getContextPath()%>/cart/cart.do">
						<div class="form-group">
							<input type="text" name="memId" class="form-control"
								placeholder="請輸入會員編號">
						</div>
						<input type="hidden" name="action" value="getOne_For_Display">
						<button type="submit" class="btn btn-primary">🔍 查詢</button>
					</form>
				</div>

				<!-- 查詢方式三 -->
				<div class="search-card">
					<h4>組合查詢</h4>
					<form method="post"
						action="<%=request.getContextPath()%>/cart/cart.do">
						<div class="form-group">
							<input type="text" name="memId" class="form-control"
								placeholder="會員編號" style="margin-bottom: 10px;">
						</div>
						<div class="form-group">
							<input type="text" name="prodId" class="form-control"
								placeholder="商品編號">
						</div>
						<input type="hidden" name="action" value="getOne_For_Display">
						<button type="submit" class="btn btn-primary">🔍 查詢</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
