<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/shopping/css/essenceMall.css" />
<div id="mallContainer">
	<div class="searchProduct">
		<div>
			<form action="<%=request.getContextPath() %>/shopping/search">
				<input type="text" name="search" class="search" size="38" placeholder="검색할 상품을 입력하세요">
				<button type="submit" class="search2">검색</button>
			</form>
		</div>
	</div>
	<div class="mallCategory">
		<div>
			<form action="<%=request.getContextPath() %>/ShoppingKategorie">
				<input type="hidden" name="kategori" value="일식">
				<input type="submit" class="underlines" value="일식">
			</form>
		</div>
		<div>
			<form action="<%=request.getContextPath() %>/ShoppingKategorie">

				<input type="hidden" name="kategori" value="중식">
				<input type="submit" class="underlines" value="중식">
			</form>
		</div>
		<div>
			<form action="<%=request.getContextPath() %>/ShoppingKategorie">
				<input type="hidden" name="kategori" value="양식">
				<input type="submit" class="underlines" value="양식">
			</form>
		</div>
		<div>
			<form action="<%=request.getContextPath() %>/ShoppingKategorie">
				<input type="hidden" name="kategori" value="한식">
				<input type="submit" class="underlines" value="한식">
			</form>
		</div>
		<div>
			<form action="<%=request.getContextPath() %>/ShoppingKategorie">
				<input type="hidden" name="kategori" value="과자">
				<input type="submit" class="underlines" value="과자">
			</form>
		</div>
	</div>
</div>
















































