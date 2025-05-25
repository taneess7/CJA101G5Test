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
<title>購物車資料修改</title>
<link rel="stylesheet" href="css/modern-cart.css">
</head>
<body>
	<div class="container">
		<!-- 頁面標題 - 與其他頁面相同風格 -->
		<div class="page-header">
			<h1>✏️ 購物車資料修改</h1>
			<p class="subtitle">編輯購物車商品的詳細資訊</p>
		</div>

		<!-- 導航按鈕 - 與其他頁面相同 -->
		<div style="margin-bottom: 25px;">
			<a href="select_page.jsp" class="nav-button"> 🏠 回到首頁 </a>
		</div>

		<!-- 錯誤訊息 - 與其他頁面相同風格 -->
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

		<!-- 商品資訊概覽 - 與其他頁面相同的統計卡片風格 -->
		<div class="card">
			<h3>📋 目前商品資訊</h3>
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
					<div class="stat-label">商品數量</div>
				</div>
			</div>
		</div>

		<!-- 修改表單 - 使用與 addCart.jsp 相同的風格 -->
		<div class="card">
			<h3>🔧 修改商品資訊</h3>

			<form method="post"
				action="<%=request.getContextPath()%>/cart/cart.do"
				onsubmit="return validateForm()">

				<!-- 購物車編號（只讀顯示） -->
				<div class="form-group">
					<label>🛒 購物車編號</label>
					<div
						style="background: linear-gradient(135deg, #e9ecef 0%, #f8f9fa 100%); color: #2c3e50; font-weight: 600; padding: 15px 20px; border-radius: 10px; border: 2px solid #dee2e6; font-size: 1.1rem; text-align: center; margin-bottom: 10px;">
						<%=cartVO.getShopId()%>
						<span style="font-size: 0.9rem; opacity: 0.7; margin-left: 10px;">(無法修改)</span>
					</div>
					<input type="hidden" name="shopId" value="<%=cartVO.getShopId()%>">
				</div>

				<!-- 會員編號 -->
				<div class="form-group">
					<label for="memId">👤 會員編號</label> <input type="number" id="memId"
						name="memId" class="form-control"
						value="<%=(cartVO == null) ? "1" : cartVO.getMemId()%>"
						placeholder="請輸入會員編號" min="1" required>
				</div>

				<!-- 商品編號 -->
				<div class="form-group">
					<label for="prodId">📦 商品編號</label> <input type="number"
						id="prodId" name="prodId" class="form-control"
						value="<%=(cartVO == null) ? "1" : cartVO.getProdId()%>"
						placeholder="請輸入商品編號" min="1" required>
				</div>

				<!-- 商品數量 -->
				<div class="form-group">
					<label for="prodN">🔢 商品數量</label> <input type="number" id="prodN"
						name="prodN" class="form-control"
						value="<%=(cartVO == null) ? "1" : cartVO.getProdN()%>"
						placeholder="請輸入商品數量" min="1" max="999" required>
				</div>

				<!-- 按鈕區域 - 與其他頁面相同風格 -->
				<div style="text-align: center; margin-top: 30px;">
					<input type="hidden" name="action" value="update">

					<button type="submit" class="btn btn-success"
						style="margin-right: 15px;">✅ 確認修改</button>

					<button type="reset" class="btn btn-warning"
						style="margin-right: 15px;" onclick="resetForm()">🔄 重置表單
					</button>

					<a href="listAllCart.jsp" class="btn btn-primary"
						style="margin-right: 15px;"> 📋 查看所有購物車 </a> <a
						href="select_page.jsp" class="btn"
						style="background: linear-gradient(135deg, #6c757d 0%, #495057 100%); color: white;">
						❌ 取消修改 </a>
				</div>
			</form>
		</div>

		<!-- 操作說明 - 與其他頁面相同的卡片風格 -->
		<div class="card">
			<h3>📖 操作說明</h3>
			<div style="background: #f8f9ff; padding: 20px; border-radius: 10px;">
				<ul
					style="margin: 0; padding-left: 20px; color: #2c3e50; line-height: 1.8;">
					<li><strong>購物車編號：</strong>系統自動生成，無法修改</li>
					<li><strong>會員編號：</strong>請輸入有效的會員編號</li>
					<li><strong>商品編號：</strong>請輸入要購買的商品編號</li>
					<li><strong>商品數量：</strong>請輸入 1-999 之間的數量</li>
					<li><strong>確認修改：</strong>點擊後將保存所有變更</li>
					<li><strong>取消修改：</strong>將返回首頁，不保存任何變更</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- JavaScript 表單驗證 -->
	<script>
        function validateForm() {
            const memId = document.getElementById('memId').value;
            const prodId = document.getElementById('prodId').value;
            const prodN = document.getElementById('prodN').value;
            
            // 驗證會員編號
            if (!memId || parseInt(memId) < 1) {
                alert('❌ 請輸入有效的會員編號（必須大於0）');
                document.getElementById('memId').focus();
                return false;
            }
            
            // 驗證商品編號
            if (!prodId || parseInt(prodId) < 1) {
                alert('❌ 請輸入有效的商品編號（必須大於0）');
                document.getElementById('prodId').focus();
                return false;
            }
            
            // 驗證商品數量
            if (!prodN || parseInt(prodN) < 1 || parseInt(prodN) > 999) {
                alert('❌ 請輸入有效的商品數量（1-999之間）');
                document.getElementById('prodN').focus();
                return false;
            }
            
            // 確認修改
            const confirmMsg = `確定要修改購物車資料嗎？\n\n` +
                              `會員編號：${memId}\n` +
                              `商品編號：${prodId}\n` +
                              `商品數量：${prodN}`;
            
            return confirm(confirmMsg);
        }
        
        function resetForm() {
            if (confirm('🔄 確定要重置表單嗎？所有修改將被清除。')) {
                // 重新設定原始值
                document.getElementById('memId').value = '<%=cartVO.getMemId()%>';
                document.getElementById('prodId').value = '<%=cartVO.getProdId()%>';
                document.getElementById('prodN').value = '<%=cartVO.getProdN()%>';
            }
        }
        
        // 頁面載入完成後的處理
        document.addEventListener('DOMContentLoaded', function() {
            // 為輸入框添加即時驗證
            const inputs = document.querySelectorAll('input[type="number"]');
            inputs.forEach(input => {
                input.addEventListener('input', function() {
                    if (this.value && parseInt(this.value) < 1) {
                        this.style.borderColor = '#ff6b6b';
                    } else {
                        this.style.borderColor = '#667eea';
                    }
                });
            });
            
            // 商品數量特殊處理
            const prodNInput = document.getElementById('prodN');
            prodNInput.addEventListener('input', function() {
                const value = parseInt(this.value);
                if (value && (value < 1 || value > 999)) {
                    this.style.borderColor = '#ff6b6b';
                } else {
                    this.style.borderColor = '#667eea';
                }
            });
        });
    </script>
</body>
</html>
