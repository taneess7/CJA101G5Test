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
        <!-- 頁面標題 - 與首頁完全一致 -->
        <div class="page-header">
            <h1>✏️ 購物車資料修改</h1>
            <h3>編輯商品資訊</h3>
            <p class="subtitle">修改購物車中的商品詳細資訊</p>
        </div>

        <!-- 導航按鈕 - 與首頁風格一致 -->
        <div style="margin-bottom: 25px;">
            <a href="select_page.jsp" class="nav-button">
                🏠 回到購物車首頁
            </a>
        </div>

        <!-- 錯誤訊息 - 與首頁完全一致 -->
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

        <!-- 修改表單 - 使用現代化卡片風格 -->
        <div class="card">
            <h3>🔧 修改商品資訊</h3>
            
            <form method="post" action="<%=request.getContextPath()%>/cart/cart.do" class="modern-form">
                
                <!-- 購物車編號（只讀顯示） -->
                <div class="form-group">
                    <label>🛒 購物車編號</label>
                    <div class="data-highlight" style="display: inline-block; margin-top: 8px;">
                        <%=cartVO.getShopId()%>
                    </div>
                    <div class="info-badge">
                        📋 此編號由系統自動生成，無法修改
                    </div>
                    <input type="hidden" name="shopId" value="<%=cartVO.getShopId()%>">
                </div>

                <!-- 會員編號 -->
                <div class="form-group">
                    <label for="memId">👤 會員編號</label>
                    <input type="number" 
                           id="memId" 
                           name="memId" 
                           class="form-control" 
                           value="<%= (cartVO==null)? "1" : cartVO.getMemId()%>" 
                           placeholder="請輸入會員編號"
                           required>
                </div>

                <!-- 商品編號 -->
                <div class="form-group">
                    <label for="prodId">📦 商品編號</label>
                    <input type="number" 
                           id="prodId" 
                           name="prodId" 
                           class="form-control" 
                           value="<%= (cartVO==null)? "1" : cartVO.getProdId()%>" 
                           placeholder="請輸入商品編號"
                           required>
                </div>

                <!-- 商品數量 -->
                <div class="form-group">
                    <label for="prodN">🔢 商品數量</label>
                    <input type="number" 
                           id="prodN" 
                           name="prodN" 
                           class="form-control" 
                           value="<%= (cartVO==null)? "1" : cartVO.getProdN()%>" 
                           placeholder="請輸入商品數量"
                           min="1"
                           max="999"
                           required>
                </div>

                <!-- 按鈕區域 -->
                <div class="action-section">
                    <input type="hidden" name="action" value="update">
                    <button type="submit" class="btn btn-success">
                        ✅ 確認修改
                    </button>
                    <a href="listAllCart.jsp" class="btn btn-warning" style="margin-left: 15px;">
                        📋 返回列表
                    </a>
                </div>
            </form>
        </div>

        <!-- 操作提示 - 使用統計卡片風格 -->
        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-number">💡</div>
                <div class="stat-label">修改提示</div>
            </div>
        </div>

        <!-- 詳細說明 -->
        <div class="card">
            <h3>📋 修改說明</h3>
            <div style="background: linear-gradient(135deg, #f8f9ff 0%, #fff 100%); 
                        padding: 25px; border-radius: 15px; border-left: 5px solid #667eea;">
                <ul style="margin: 0; padding-left: 20px; line-height: 1.8; color: #2c3e50;">
                    <li>購物車編號由系統自動生成，無法修改</li>
                    <li>會員編號必須為有效的正整數</li>
                    <li>商品編號必須為有效的正整數</li>
                    <li>商品數量限制在 1-999 之間</li>
                    <li>修改後請確認資料的正確性</li>
                </ul>
            </div>
        </div>
    </div>

    <!-- JavaScript 優化 -->
    <script>
        // 表單驗證與用戶體驗優化
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.querySelector('form');
            const inputs = document.querySelectorAll('.form-control');
            
            // 添加輸入時的即時驗證
            inputs.forEach(input => {
                input.addEventListener('input', function() {
                    this.style.borderColor = '#667eea';
                    this.style.boxShadow = '0 0 0 3px rgba(102, 126, 234, 0.1)';
                });
                
                input.addEventListener('blur', function() {
                    if (this.value.trim() === '') {
                        this.style.borderColor = '#dc3545';
                        this.style.boxShadow = '0 0 0 3px rgba(220, 53, 69, 0.1)';
                    } else {
                        this.style.borderColor = '#28a745';
                        this.style.boxShadow = '0 0 0 3px rgba(40, 167, 69, 0.1)';
                    }
                });
            });
            
            // 表單提交驗證
            form.addEventListener('submit', function(e) {
                const memId = document.getElementById('memId').value;
                const prodId = document.getElementById('prodId').value;
                const prodN = document.getElementById('prodN').value;
                
                let hasError = false;
                let errorMessage = '';
                
                if (!memId || parseInt(memId) < 1) {
                    errorMessage += '• 請輸入有效的會員編號\n';
                    hasError = true;
                }
                
                if (!prodId || parseInt(prodId) < 1) {
                    errorMessage += '• 請輸入有效的商品編號\n';
                    hasError = true;
                }
                
                if (!prodN || parseInt(prodN) < 1 || parseInt(prodN) > 999) {
                    errorMessage += '• 請輸入有效的商品數量（1-999）\n';
                    hasError = true;
                }
                
                if (hasError) {
                    alert('❌ 資料驗證失敗：\n\n' + errorMessage);
                    e.preventDefault();
                    return;
                }
                
                if (!confirm('✅ 確定要修改這筆購物車資料嗎？')) {
                    e.preventDefault();
                }
            });
        });
    </script>
</body>
</html>
