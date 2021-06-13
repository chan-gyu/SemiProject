<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yoriessence.shopping.vo.Product" %>
<%
	Product pro=(Product)request.getAttribute("pds");
%>
<%@ include file="/view/shopping/shoppingall.jsp" %>
	<div class="meaning">
		<div id="allpark">
		<p>검색 상품</p>
			<ul class="test">
				<a href="<%=request.getContextPath()%>/shopping/shopping?productNo=<%=pro.getProductNo()%>">
					<input type="hidden" value="<%=pro.getProductNo() %>">
					<img src="<%=request.getContextPath() %>/image/<%=pro.getProductImage() %>" alt="" class="shop"> 
				</a>
				<li class="jsp">제품명 :<%=pro.getProductName() %></li>
				<li class="jsp">가격 :<%=pro.getPrice() %></li>
			</ul>
		</div>
	</div>




