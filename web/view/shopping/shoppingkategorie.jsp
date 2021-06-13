<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.yoriessence.shopping.vo.Product, java.util.Random" %>
<%
	List<Product> pd=(List<Product>)request.getAttribute("kategori");
%>
<%@ include file="/view/shopping/shoppingall.jsp" %>
<!-- 
	추천상품의 경우는 랜덤요소로 넣으면 편리할거같음 랜덤요소 추가햇지만 쿠키나 세션에 저장을해서 보내야할거같음
	음식 태그 선택시 해당태그의 음식을 전체상품으로 출력해야함
	상품 이미지 클릭시 해당상품 구매페이지로 이동
-->
	<div class="meaning">
		<div class="title2">전체상품</div>
		<div class="allpark">
			<%for(Product pdt : pd) {%>
				<div class="top2">
					<div>
						<a href="<%=request.getContextPath()%>/shopping/shopping?productNo=<%=pdt.getProductNo()%>">
							<input type="hidden" value="<%=pdt.getProductNo() %>">
							<img src="<%=request.getContextPath() %>/image/<%=pdt.getProductImage() %>" alt="" class="shop">
						</a>
					</div>
					<div class="mallContentInfo">
						<span class="jsp">제품명 :<%=pdt.getProductName() %></span><br>
						<span class="jsp">가격 :<%=pdt.getPrice() %></span>
					</div>
				</div>
			<%}%>
		</div>
		<div id="pageBar">
			<div><%=request.getAttribute("pageBar") %></div>
		</div>
	</div>

<%@ include file="/view/common/footer.jsp"%>
