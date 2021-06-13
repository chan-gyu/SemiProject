<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<%@ page import="com.yoriessence.recipe.model.vo.Recipe, com.yoriessence.shopping.vo.Product, java.util.List, java.util.Map" %>
<%
	List<Recipe> recipeList=(List<Recipe>)request.getAttribute("recipeList");
	List<Product> productList=(List<Product>)request.getAttribute("productList");
	Map<String, String> nicknameMap=(Map<String, String>)request.getAttribute("nicknameMap");
%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	section{
		width:1100px;
		margin:20px auto;
 		padding :20px;
		overflow:hidden;	
	}
	
	/* 검색창 */
	.search_row{
		display:flex;
		justify-content:center;
	}
	.search_row>input#btn_search{
		width:70px; height:50px;	
	} 
	
	.search_row>input#keyword{
		display:inline-block;
		width:700px; height:50px;
		margin-right:10px;	
	} 
	
	/* 검색기준 선택 */
/* 	
	.filter_row>*{
		float:left;
	} */
	.filter_row{
		display:flex;
		justify-contents:left;
	}
	.filter_title{
		color: #1F695B;
		font-weight:bold;
		width:70px;
		margin-right:20px;
	}
	.filter_check{
		width:700px;
	}
	.filter_check label{
		margin-right:10px;
	}
	
	#recipe_search{
		width:900px;
		margin:20px auto;
		padding-top:10px;
		border-bottom:1px black solid;
		border-top:1px solid black;
		text-overflow:hidden;
	}
	#recipe_search>*{
		margin-bottom:10px;
	}
	
		
	#select_order{
		width:900px;
		text-align:right;
		margin:0 auto;
	}
	
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
	
	/* 레시피 목록 */
	.grid{
		margin:0 auto;
		padding-top:20px;
		width:900px;
		display:grid;
		grid-template-columns:repeat(3, 1fr);
		gap:50px;
		text-align:center;
	}
	
	 .recipe_thumbnail{
	 	height:200px; width:200px;
	 	background-color:#DCDCDC;
	 	background-size:cover;
	 	
	 }
	
	.checked_order{
		font-weight:bold;
		color:#1F695B;
	}
	#select_order input[type=radio]{
		display:none;
	}
	
	/* 레시피목록 페이지바 */
	#pageBar{
		display:flex;
		justify-content:center;
		margin-top:20px;
	}
	#pageBar *{
		margin-left:15px;
	}
	#pageBar span.cPage{
		color:#1F695B ;
		font-weight:bold;
	}
	
	/* 데이터 없을 때 출력 */
	.no_data{
		display:flex; justify-content:center;
		/* margin:10px; */
	}
	#mealkit_list .no_data{
		margin:30px;
	}
	
	img{
		object-fit: cover;
	}
	
</style>
<section>
<%-- <a href="<%=request.getContextPath()%>/point/pointView?memberId=<%=request.getSession().getAttribute("loginMember")%>">포인트조회</a> --%>
	<form action="<%=request.getContextPath() %>/recipe/recipeSearch" method="post">
		<div class="search_row">
			<!-- <input type="hidden" name="keyword_hidden" id="keyword_hidden" value=""> -->
			<input type="search" name="keyword" id="keyword" value="<%=request.getAttribute("keyword")!=null?request.getAttribute("keyword"):""%>">
			<input type="submit" id="btn_search" id="btn_search" value="검색">
		</div>
		<div id="recipe_search">
			<div class="filter_row">
				<div class="filter_title">종류별</div>
				<div class="filter_check">
					<input type="hidden" value="'한식','중식','일식','양식'" name="category_hidden" id="category_hidden">
					<label><input type="radio" name="category" value="'한식','중식','일식','양식'" checked>전체</label>
					<label><input type="radio" name="category" value="'한식'">한식</label>
					<label><input type="radio" name="category" value="'중식'">중식</label>
					<label><input type="radio" name="category" value="'일식'">일식</label>
					<label><input type="radio" name="category" value="'양식'">양식</label>
				</div>
			</div>
			<div class="filter_row">
				<div class="filter_title">재료별</div>
				<div class="filter_check">
					<input type="hidden" value="'육류','채소류','해물류','과일류','달걀/유제품','가공식품류','쌀','밀가루','곡물','건어물류','버섯류','콩/견과류','기타'" name="ingredient_hidden" id="ingredient_hidden">
					<div>
					<label><input type="radio" name="ingredient" value="'육류','채소류','해물류','과일류','달걀/유제품','가공식품류','쌀','밀가루','곡물','건어물류','버섯류','콩/견과류','기타'" checked>전체</label>
					<label><input type="radio" name="ingredient" value="'육류'">육류</label>
					<label><input type="radio" name="ingredient" value="'채소류'">채소류</label>
					<label><input type="radio" name="ingredient" value="'해물류'">해물류</label>
					<label><input type="radio" name="ingredient" value="'과일류'">과일류</label>
					<label><input type="radio" name="ingredient" value="'달걀/유제품'">달걀/유제품</label>
					<label><input type="radio" name="ingredient" value="'가공식품류'">가공식품류</label>
					<label><input type="radio" name="ingredient" value="'쌀'">쌀</label>
					<label><input type="radio" name="ingredient" value="'밀가루'">밀가루</label>
					</div>
					<div>
					<label><input type="radio" name="ingredient" value="'곡물'">곡물</label>
					<label><input type="radio" name="ingredient" value="'건어물류'">건어물류</label>
					<label><input type="radio" name="ingredient" value="'버섯류'">버섯류</label>
					<label><input type="radio" name="ingredient" value="'콩/견과류'">콩/견과류</label>
					<label><input type="radio" name="ingredient" value="'기타'">기타</label>
					</div>
				</div>
			</div>
		</div>
		<div id="select_order">
			<a id="recipe_enroll_date" class="checked_order">최신순</a>
			<a id="recommend_count">추천순</a>
			<input type="hidden" id="order_by" name="order_by" value="recipe_enroll_date">
		</div>
	</form>
	<div id="mealkit_list">
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
	</div>
	<div id="recipe_list">
		<div class="grid">
			<%if(recipeList!=null&&recipeList.size()!=0){ 
				for(Recipe r:recipeList) {%>
				<div class="recipe">
					<input name="recipeEnrollNo" type="hidden" value="<%=r.getRecipeEnrollNo()%>">
					<img class="recipe_thumbnail" src="<%=request.getContextPath() %>/<%=r.getRepresentPicture()!=null?"upload/recipe/"+r.getRepresentPicture():"/img/recipe/no_image.png" %>">
					<p><%=r.getRecipeTitle() %></p>
					<p><%=nicknameMap.get(r.getMemberId()) %></p>
					<span>좋아요 <%=r.getRecommendCount() %></span>
					<span>댓글 <%=r.getCommentCount() %></span>
					<span>조회수 <%=r.getRecipeViewCount() %></span>
				</div>
			<%}
			}else{ %>
				<div></div><div class="no_data">검색 결과가 없습니다.</div><div></div>
			<%} %>
		</div>
		<div id="pageBar"><%=request.getAttribute("pageBar")!=null?request.getAttribute("pageBar"):"" %></div>
	</div>
</section>
<script>
/* 	let pPage=1;
	$("#product_before").click(e=>{
		if(pPage!=1){
			pPage--;
		}else{
			pPage=3;
		}
		productPageMove();
	})
	$("#product_after").click(e=>{
		if(pPage!=3){
			pPage++;
		}else{
			pPage=1;
		}
		productPageMove();
	}) */
	
	const productPageMove=(page)=>{
		$.ajax({
			url:"<%=request.getContextPath()%>/recipe/productPageMove",
			data:{
				cPage:page,
				keyword:$("#keyword").val()
			},
			success:data=>{
				$("#mealkit_list").html(data);
			}
		});
	}


 	const pageMove=(pageNo)=>{
		$.ajax({
			url:"<%=request.getContextPath()%>/recipe/recipePageMove",
			data:{
				keyword:$("#keyword").val(),
				category:$("#category_hidden").val(),
				ingredient:$("#ingredient_hidden").val(),
				order:$("#order_by").val(),
				cPage:pageNo
			},
			success:data=>{
				$("#recipe_list").html(data);
			}
		});
	}

	$("div.recipe").click(e=>{
		const recipeEnrollNo=$(e.target).parent().find($("input[name=recipeEnrollNo]")).val();
		console.log(recipeEnrollNo);
		location.assign("<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo="+recipeEnrollNo);
	});
	
	$("#select_order a").click(e=>{
		$("#order_by").val($(e.target).attr("id"));
		$(e.target).addClass("checked_order");
		$(e.target).siblings("a").removeClass("checked_order");
		fn_search_ajax();
	});
	
	$("input[name=category]").change(e=>{
 		let value="";
 		let flag=true;
 		$("input[name=category]").each((i,v)=>{
 			if(v.checked==true){
 				if(value!="") value+=","
 				value+=$(v).val();
 				flag=false;
 			}
 		});
		$("#category_hidden").val(value);
		fn_search_ajax();
	});
	
	$("input[name=ingredient]").change(e=>{
 		let value="";
 		let flag=true;
 		$("input[name=ingredient]").each((i,v)=>{
 			if(v.checked==true){
 				if(value!="") value+=","
 				value+=$(v).val();
 				flag=false;
 			}
 		});
		$("#ingredient_hidden").val(value);
		fn_search_ajax();
	});
	
	$("#btn_search").click(e=>{
	/* 	$("#keyword_hidden").val($(e.target).prev().val()); */
		fn_search_ajax();
		
	}); 
	
	const fn_search_ajax=()=>{
		$.ajax({
			url:"<%=request.getContextPath()%>/recipe/recipeSearchAjax",
			dataType:"html",
			data:{
				keyword:$("#keyword").val(),
				category:$("#category_hidden").val(),
				ingredient:$("#ingredient_hidden").val(),
				order:$("#order_by").val()
			},
			success:data=>{
				$("#recipe_list").html(data);
			}
		});
	}
	

	
	
	
</script>
<%@ include file="/view/common/footer.jsp"%>