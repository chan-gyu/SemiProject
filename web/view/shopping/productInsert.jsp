<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/view/common/header.jsp"%>

<style>
	.start{
		font-size:38px;
		margin-left:500px;
	}
	div{
		margin-bottom:50px;
	}
	input{
		font-size:38px;
	}
	option{
		font-size:38px;
	}
	p{
		margin-bottom:10px;
	}
	#button{
		
		font-size:38px;
		margin-left:100px;
	}
</style>
	<div class="start">
		<form action="<%=request.getContextPath()%>/ProductInsert" method="post" enctype="multipart/form-data">
			<div>
				<div>
					<p>상품명</p>
					<div>
						<input type="text" name="productname"  placeholder="등록할 상품명" required>
					</div>
				</div>
				<div>
					<p>재고수량</p>
					<div><input type="number" name="stock" min="0" placeholder="상품재고수량" value="0" required></div>
				</div>
				<div>
					<p>가격</p>
					<div><input type="number" name="price" min="0" placeholder="가격" value="0" required></div>
				</div>
				<div>
					<p>상품설명</p>
					<div><input type="text" name="explantion"  placeholder="상품기본설명"></div>
				</div>
				<div>
					<p>상품이미지</p>
					<div>
						<input type="file" name="productimage">
					</div>
				</div>
				<div>
					<p>제품 카테고리</p>
					<select name="kategorie" style="width:300px;height:50px; font-size:38px;" required>
						<option>카테고리선택</option>
						<option value="한식">한식</option>
						<option value="중식">중식</option>
						<option value="일식">일식</option>
						<option value="양식">양식</option>
						<option value="과자">과자</option>
					</select>
				</div>
				<div>
					<p>배송비 설정</p>
					<div><input type="number" min="0" name="productshopify" placeholder="배송비" value="0" required></div>
				</div>
			</div>
			<button id="button" onclick="">등록</button>
			<button id="button" onclick="back(event)">취소</button>
		</form>
	</div>
	
	
	<script>
		const back=(e)=>{
			if(confirm("등록 취소하시겠습니까?")){
				location.replace("<%=request.getContextPath()%>/index.jsp");
			}
		}
	</script>
<%@ include file="/view/common/footer.jsp"%>






















