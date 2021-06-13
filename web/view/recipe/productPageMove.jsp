<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yoriessence.shopping.vo.Product, java.util.List" %>
<%
	List<Product> productList=(List<Product>)request.getAttribute("productList");
%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<style>
	/* 제품 목록 */
	#mealkit_list{
		width:900px;
		display:flex;
		align-items:center;
		margin:30px auto;
		padding-bottom:20px;
		border-bottom:1px solid black;
		justify-content:space-between;
	}
	#mealkit_list button{
		font-size:50px;
		text-align:center;
		background-color:white;
		border:none;
	}
</style>
		<%if(productList.size()!=0){ %>
			<%=request.getAttribute("beforeBtn")%>
			<%for(Product p:productList) {%>
			<div class="mealkit" onclick="location.replace('<%=request.getContextPath()%>/shopping/shopping?productNo=<%=p.getProductNo() %>')">
				<img src="<%=request.getContextPath() %>/image/<%=p.getProductImage()!=null?p.getProductImage():"/img/recipe/no_image.png" %>" height="200px" width="200px">
				<div class="mealkit_info">
					<h4><%=p.getProductName() %></h4>
					<span>가격 <%=p.getPrice() %></span><span>장바구니</span>
				</div>
			</div>
			<%} %>
			<%=request.getAttribute("afterBtn") %>
		<%}else{%>
			<div></div><div class="no_data">관련 상품이 없습니다.</div><div></div>
		<%}%>
		