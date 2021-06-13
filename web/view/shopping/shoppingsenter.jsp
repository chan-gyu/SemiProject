<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<style>
	.start{
		font-size:38px;
		margin-left:700px;
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
	button{
		
		font-size:38px;
		margin-left:100px;
	}
	
	.explantionset{
		font-size:38px;
	}
	.senter{
		font-size:80px;
		margin-bottom:50px;
		margin-left:60px;
	}
</style>
	<div class="start">
		<form action="<%=request.getContextPath()%>/shoppingsenter" method="post" enctype="multipart/form-data">
			<div>
			<p class="senter">고객센터</p>
				<div>
					<p>회원아이디</p>
					<div>
						<input type="text" name="memberid"  placeholder="회원아이디" required>
					</div>
				</div>
				<div>
					<p>주문번호</p>
					<div><input type="number" name="ordernumber" min="0" placeholder="주문번호" value="0" required></div>
				</div>
				<div>
					<p>상품명</p>
					<div><input type="text" name="productname" min="0" placeholder="상품명" value="0" required></div>
				</div>
				<div>
					<p>문의분류</p>
					<select name="kategorie" style="width:300px;height:50px; font-size:38px;" required>
						<option>문의사항선택</option>
						<option value="반품">반품</option>
						<option value="교환">교환</option>
						<option value="주문취소">주문취소</option>
					</select>
				</div>
				<div>
					<p>상품이미지</p>
					<div>
						<input type="file" name="productimage">
					</div>
				</div>
				<div>
					<p>제목</p>
					<div><input type="text" name="title"></div>
				</div>
				<div>
					<p>내용</p>
					<div><textarea rows="3" cols="30" name="titlesenter" class="explantionset" placeholder="문의내용" style="margin: 0px; height: 262px; width: 468px;"></textarea></div>
				</div>
				
			</div>
			<button onclick="">등록</button>
			<button onclick="back(event)">취소</button>
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
