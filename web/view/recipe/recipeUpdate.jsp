<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp" %>
<%@ page import="com.yoriessence.recipe.model.vo.*, java.util.*" %>

<%
	Recipe recipe=(Recipe)request.getAttribute("recipe");
	/* List<String> ingCategory=(List<String>)request.getAttribute("category"); */
	Map<String, List<RecipeIngredient>> ingredient=(Map<String, List<RecipeIngredient>>)request.getAttribute("ingredient");
	//List<RecipePicture> pictures=(List<RecipePicture>)request.getAttribute("pictures");
	List<RecipeProcedure> procedure=(List<RecipeProcedure>)request.getAttribute("procedure");
%>

<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	/* 전체를 감싸는 div에 margin속성 적용해 페이지의 중앙으로 정렬함 */
	div#recipe_write{
		margin:0 auto;
		width:900px;
		position:relative;
	}
	
	/* 섬네일 미리 보여줄 이미지 태그 설정 */
	img#thumbnail_preview{
		width:200px; height:200px; float:right;
	}
	
	/* p태그(input_title)과 그 외 div를 수평으로 정렬 */
 	.input_title, .input{
		display:inline-block;
	} 
	
	.input_title{
		width:100px;
		margin-right:100px;
		vertical-align:top;
	}
	
	.input_container{
		/* display:inline-block; */
		margin:50px;
	}

	/* 입력창 설정 */
	input, select, textarea{
		/* background-color:rgb(235, 235, 235); */
		border-radius:3px;
		border:rgb(182, 182, 182) 1px solid;
		height:40px;
		padding:5px;
	}
	input[type="text"]{
		width:300px;
	}
	
	textarea{
		width:590px;
		height:100px;
		resize:none;
	}
	textarea[name="recipe_intro"]{
		width:300px;
	}	
	textarea.procedure_content{
		width:450px;
	}
	
	/* 요리정보(인원, 시간, 난이도) */
	#recipe_info_container *{	
		display:inline-block;
	} 
	#recipe_info_container>div{
		margin-right:30px;
	}
	#recipe_info_container select{
		width:100px;
	}
	
	/* 재료 정보 리스트 */
	#ingredient_container{
		display:flex; width:900px; overflow:hidden;
	}
	.ingredient_bundle{
		display:flex; width:500px;
	}
 	#ingredient_info input{
		width:150px;
		margin:5px;
	}
	.ingredient_li{
		width:330px;
	}
	
	.ingredient_li>a{
		display:none;
	}
	
	/* 요리순서 */
	.step_container{
		display:inline-block;
	}
	.step * {
		vertical-align:center;
		
	}

	/* 등록 또는 취소 버튼 */
 	#submit_container{
 		display:flex; justify-content:center;
 	}
 	
 	#submit_container>input{
 		width:100px;
 		margin-left:15px;
 	}
 	
 	.to_center{
 		display:flex;
 		justify-content:center;
 	}
 	
 	.btn_add_ingredient_li{
 		width:80px;
 		height:30px;
 	}
 	#btn_add_ingredient_bundle{
 		width:300px;
 		height:50px;
 	}
 	#btn_add_procedure{
 		width:80px;
 		height:30px;
 	}
 	
 	img{
 		object-fit: cover;
 	}

</style>

<section>
	<div id="recipe_write">
		<form action="<%=request.getContextPath() %>/recipe/recipeUpdateEnd" method="post" enctype="multipart/form-data" onsubmit="return fn_submit_validate();">
			<div id="basic_info" class="info">
				<input type="file" style="display:none" name="represent_picture" id="represent_picture"/>
				<%if(recipe.getRepresentPicture()!=null){ %>
					<img src="<%=request.getContextPath() %>/upload/recipe/<%=recipe.getRepresentPicture() %>" id="thumbnail_preview"/>
				<%}else{ %>
					<img src="<%=request.getContextPath() %>/img/recipe/no_image.png" style="background-color:#DCDCDC" id="thumbnail_preview"/>
				<%} %>
				<input type="hidden" name="recipe_enroll_no" value="<%=recipe.getRecipeEnrollNo() %>">
				<input type="hidden" name="member_id" id="member_id" value="testId"/>
				<div class="input_container">
					<p class="input_title">레시피 제목</p>
					<div class="input"><input type="text" name="recipe_title" value="<%=recipe.getRecipeTitle() %>" id="recipe_title" placeholder="레시피 제목을 입력하세요." required/></div>
				</div>
				<div class="input_container">
					<p class="input_title">레시피 소개</p>
					<div class="input"><textarea name="recipe_intro" id="recipe_intro"><%=recipe.getRecipeIntro()!=null?recipe.getRecipeIntro():"" %></textarea></div>
				</div>
				<div class="input_container">
					<p class="input_title">동영상</p>
					<div class="input"><input type="text" name="recipe_video_address" value="<%=recipe.getRecipeVideoAddress()==null?"":recipe.getRecipeVideoAddress() %>" id="video_address" placeholder="동영상이 있으면 주소를 입력하세요."/></div>
				</div>
				<div class="input_container">
					<p class="input_title">카테고리</p>
					<div class="input">
						<select name="recipe_category" id="recipe_category" required>
							<option value="">종류별</option>
							<option value="한식">한식</option>
							<option value="중식">중식</option>
							<option value="일식">일식</option>
							<option value="양식">양식</option>
						</select>
						<select name="main_ingredient" id="main_ingredient" required>
							<option value="">재료별</option>
							<option value="육류">육류</option>
							<option value="채소류">채소류</option>
							<option value="해물류">해물류</option>
							<option value="과일류">과일류</option>
							<option value="달걀/유제품">달걀/유제품</option>
							<option value="가공식품류">가공식품류</option>
							<option value="쌀">쌀</option>
							<option value="밀가루">밀가루</option>
							<option value="곡물">곡물</option>
							<option value="건어물류">건어물류</option>
							<option value="버섯류">버섯류</option>
							<option value="콩/견과류">콩/견과류</option>
							<option value="기타">기타</option>
						</select>
					</div>
				</div>
				<div class="input_container">
					<p class="input_title">요리정보</p>
					<div class="input" id="recipe_info_container">
						<div>
							<p>인원</p>
							<select name="recipe_info_howmany" id="recipe_info_howmany">
								<option value="1">1인분</option>
								<option value="2">2인분</option>
								<option value="3">3인분</option>
								<option value="4">4인분</option>
								<option value="5">5인분</option>
								<option value="6">6인분 이상</option>
							</select>
						</div>
						<div>
							<p>시간</p>
							<select name="recipe_info_time" id="recipe_info_time">
								<option value="5">5분 이내</option>
								<option value="10">10분 이내</option>
								<option value="30">30분 이내</option>
								<option value="60">1시간 이내</option>
								<option value="120">2시간 이내</option>
								<option value="121">2시간 이상</option>
							</select>
						</div>
						<div>
							<p>난이도</p>
							<select name="recipe_difficult" id="recipe_difficult">
								<option value="상">상</option>
								<option value="중">중</option>
								<option value="하">하</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div id="ingredient_info">
				<div class="input_container" id="ingredient_container">
                    <p class="input_title">재료</p>
                   	<div id="bundle_container">
                   		<input type="hidden" name="hidden_name" id="hidden_name" value="" class="hidden_name"/>
 	                    <%
	 	                   Set set=ingredient.entrySet();
	 	                   Iterator it=set.iterator();
	 	                   while(it.hasNext()){
		 	                   	Map.Entry e=(Map.Entry)it.next();
		 	                   	String ingCategory=(String)e.getKey();
		 	                   	List<RecipeIngredient> ingList=(List<RecipeIngredient>)e.getValue();%>
								<div class="ingredient_bundle">
			                    	<input type="text" name="ingredient_category" value="<%=ingCategory %>">
			                    	<ul class="ingredient_ul">
			                    	<% for(RecipeIngredient ri:ingList){%>
			                			<li class="ingredient_li">
			                				<input type="text" name="ingredient_name" value="<%=ri.getIngredientName()%>"/><input type="text" name="ingredient_amount" value="<%=ri.getIngredientAmount()%>"/><a>x</a>
		                				</li>
			                  		<%}%>
			                  			<li class="btn_li to_center"><button type="button" class="btn_add_ingredient_li">추가</button></li>
			                  		</ul>
	                				<input type="hidden" name="" value="" class="hidden_ing"/>
                				</div>
 	                   <%}%>
                    </div>
                </div>
			</div>
			<div class="input_container to_center"><button type="button" id="btn_add_ingredient_bundle">재료/양념 묶음 추가</button></div>
			<div id="process">
				<div class="input_container procedure_container">
					<p class="input_title">요리순서</p>
					<div class="step_container">
						<!-- <input type="hidden" id="recipe_procedure" name="recipe_procedure"/> -->
						<input type="hidden" id="procedure_count" name="procedure_count" value="1"/>
                    	<%for(RecipeProcedure rp:procedure) {%>
                        <div class="step">
							<h3>Step 1</h3>
                        	<textarea name="procedure_content1" class="procedure_content"><%=rp.getProcedureContent()%></textarea>
                        	<input type="file" value="" style="display:none" class="procedure_picture" name="procedure_picture1"/>
                        	<%if(rp.getProcedurePicture()!=null) { %>
	                        	<img src="<%=request.getContextPath() %>/upload/recipe/<%=rp.getProcedurePicture()%>" name="procedure_thumbnail" width="100px" height="100px" class="step_img">
	                        <%}else {%>
	                        	<img src="<%=request.getContextPath() %>/img/recipe/no_image.png" name="procedure_thumbnail" width="100px" height="100px" style="background-color:#DCDCDC" class="step_img">
	                        <%} %>
	                        <a style="display:none">x</a>
	                    </div>
	                    <%} %>
					</div>
				</div>
				
				<div class="input_container btn_container to_center"><button type="button" id="btn_add_procedure">추가</button></div>
			</div>
			<div id="tips">
				<div class="input_container">
					<p class="input_title">팁</p>
					<div class="input">
						<textarea name="recipe_tip" id="recipe_tip"><%=recipe.getRecipeTip()!=null?recipe.getRecipeTip():"" %></textarea>
					</div>
				</div>
			</div>
			
			<div id="submit_container" class="input_container">
				<!-- <input type="submit" value="등록" id="btn_submit"> -->
				<input type="submit" value="등록">
				<input type="button" value="취소" id="btn_cancel"/>
			</div>
			
		 </form> 
	</div>
</section>

<script>
	const fn_submit_validate=()=>{
 		if($("#hidden_name").val().length==0){
			alert("재료를 하나 이상 입력하세요.");
			$("input[name=ingredient_category]").first().focus();
			return false;
		}else if($("#hidden_ing").val().length==0){
			alert("재료를 하나 이상 입력하세요.");
			$("input[name=ingredient_name]").first().focus();
		}else if($("#recipe_procedure").val()!==undefined||$("#recipe_procedure").val()==""){
			alert("요리 과정을 입력하세요.");
			$("textarea[name=step]").first().focus();
			return false;
		}else{  
			//과정별 사진 ajax로 처리하던 코드  
 			return true;
		}
	} 
	
	const fn_update_ingredient=()=>{
		let names="";
		$("div#bundle_container").find($("input[name=ingredient_category]")).each((i,v)=>{
			if($(v).val()!=""){
				if(i!=0) names+=",";
				names+=$(v).val();
			}
		});
		$("div#bundle_container").find($("input[name=hidden_name]")).val(names);
		 
		$(".ingredient_bundle").each((i,v)=>{
			const ingredient_li=$(v).find($("li.ingredient_li"));
			let value="";
			ingredient_li.each((ii,vv)=>{
				if($(vv).find($("input[name=ingredient_name]")).val().length>0
						&&$(vv).find($("input[name=ingredient_amount]")).val().length>0){
					if(ii!=0) value+=",";
					value+=$(vv).find($("input[name=ingredient_name]")).val()
						+":"+$(vv).find($("input[name=ingredient_amount]")).val();
				}
			});
			$(v).find($("input.hidden_ing")).val(value).attr("name", $(v).find($("input[name=ingredient_category]")).val());
		});
	}

	const fn_procedure_update=()=>{
		//div위에 몇 단계인지 표기하고 file 태그의 이름 변경
		$("div.step").each((i,v)=>{
			$(v).find("input.procedure_picture").attr("name", "procedure_picture"+(i+1));
			$(v).find("textarea").attr("name", "procedure_content"+(i+1));
			$(v).find("h3").text("Step"+(i+1));
		});
/* 		//과정을 parsing처리할 수 있도록 하나의 string으로 만들기
		let value="";
		$("textarea[name=step]").each((i,v)=>{
			if(i!=0) value+="Step.";
			value+=$(v).val();
		});
		$("input#recipe_procedure").val(value); */
		//단계 수 세기
		$("#procedure_count").val($("div.step").length);
	}
	
	$(function(){
		
		
		//요리 카테고리 자동선택
		$("#recipe_category").children().each((i,v)=>{
			if($(v).val()=="<%=recipe.getRecipeCategory()%>") {
				$(v).prop("selected", "true");
			}
		});
		//요리 메인재료 자동선택
		$("#main_ingredient").children().each((i,v)=>{
			if($(v).val()=="<%=recipe.getMainIngredient()%>") {
				$(v).prop("selected", "true");
			}
		});
		
		//요리 인원 자동선택
		$("#recipe_info_howmany").children().each((i,v)=>{
			if($(v).val()=="<%=recipe.getRecipeInfoHowmany()%>") {
				$(v).prop("selected", "true");
			}
		});
		
		//요리 시간 자동선택
		$("#recipe_info_time").children().each((i,v)=>{
			if($(v).val()=="<%=recipe.getRecipeInfoTime()%>") {
				$(v).prop("selected", "true");
			}
		});
		
		//요리 난이도 자동선택
		$("#recipe_difficult").children().each((i,v)=>{
			if($(v).val()=="<%=recipe.getRecipeDifficult()%>") {
				$(v).prop("selected", "true");
			}
		});
		
		//과정별 사진첨부 이벤트
		$("img[name=procedure_thumbnail]").click(e=>{
			$(e.target).prev().click();
		});
		$("input.procedure_picture").change(e=>{
			if(e.target.files[0].type.includes("image")){
				let reader=new FileReader();
				let result="";
				const img=$(e.target).next();
				reader.onload=function(e){
					img.attr("src", e.target.result);
				}
				reader.readAsDataURL(e.target.files[0]);
			}
		});
		
		//재료 li태그 삭제버튼 보이도록 하는 이벤트 추가
		$(".ingredient_li").hover(
			function(e){
				$(e.target).find("a").css("display","inline-block");
			},
			function(e){
				$(e.target).find("a").css("display","none");
			}
		);
		//재료묶음 추가 이벤트
		$("#btn_add_ingredient_bundle").click(e=>{
			const ul=$("<ul>").addClass("ingredient_ul");
			const li=$("li.ingredient_li").first().clone(true);
			//li의 모든 input태그의 값 비우기
			li.find("input").each((i,v)=>{
				$(v).val("");
			});
			const btn_li=$("li.btn_li").first().clone(true);
			for(let i=0;i<3;i++){
				ul.append(li.clone(true));
			}
			ul.append(btn_li);
			const div=$("<div>").addClass("ingredient_bundle")
				.append($("input[name=ingredient_category]").first().clone(true).val(""))
				.append(ul)
				.append($("<input>").attr("type","hidden").addClass("hidden_ing"));
			$("div#bundle_container").append(div);
		});
		
		//재료 li태그 삭제하기
		$(".ingredient_li").find("a").click(e=>{
			if($(e.target).parent().parent().find("li.ingredient_li").length>1){
				$(e.target).parent().remove();
				$("#bundle_container").find("li.ingredient_li").find("input").blur();
			}else if($("#bundle_container").find("div.ingredient_bundle").length<2){
				alert("재료는 하나 이상 입력하세요.");
			}else{
				$(e.target).parent().parent().parent().remove();
				$("#bundle_container").find("li.ingredient_li").find("input").blur();
			}
			fn_update_ingredient();
		});
		
		//재료 li태그 하나 추가하기
		$(".btn_add_ingredient_li").click(e=>{
			const li=$("li.ingredient_li").first().clone(true);
			li.find("input").each((i,v)=>{
				$(v).val("");
			});
			$(e.target).parent().parent().find("li.ingredient_li").last().after(li);
		});
		
		$("#btn_cancel").click(e=>{
			if(confirm("변경내용은 저장되지 않습니다. 작성을 취소하시겠습니까?")){
				location.replace("<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=recipe.getRecipeEnrollNo()%>");
			}
			
		});
		
		//hidden_ing에 묶음의 재료명:재료양 기입
		$("#bundle_container").find("li.ingredient_li").find("input").blur(e=>{
			fn_update_ingredient();
		});
		
		//hidden_names에 묶음명 기입
		$("#bundle_container").find("input[name=ingredient_category]").blur(e=>{
			fn_update_ingredient();
		});
		
		//단계 수정됐을때 값 반영하기
		$("textarea[name=step]").blur(e=>{
			fn_procedure_update();
		});
		
		//요리과정 삭제버튼 보이도록 하는 이벤트 추가
		$("div.step>*").hover(
			function(e){
				$(e.target).parent().find("a").css("display","inline-block");
			},
			function(e){
				$(e.target).find("a").css("display","none");
			}
		);
		
		//요리과정 추가하기
		$("#btn_add_procedure").click(e=>{
			const div=$("div.step").first().clone(true);
			//복사된 태그들의 값 비워주기
			div.find("img").attr("src", "<%=request.getContextPath() %>/img/recipe/no_image.png");
			div.find("textarea").val("");
			div.find("input[type=file]").val("");
			//첨부파일 태그 추가하면서 이름을 1씩 증가시키고, hidden태그로 숫자 셈
			$("div.step_container").append(div);
			fn_procedure_update();
		});

		//요리과정 삭제하기
		$(".step").find("a").click(e=>{
			if($(".step").length>1){
				$(e.target).parent().remove();
			}else{
				alert("요리 과정을 하나 이상 입력하세요.");
			}
			fn_procedure_update();
		});
		
 		$("#thumbnail_preview").click(e=>{
			$(e.target).prev().click();
		});
		
		$("#represent_picture").change(e=>{
			if(e.target.files[0].type.includes("image")){
				let reader=new FileReader();
				reader.onload=function(e){
					$("#thumbnail_preview").attr("src", e.target.result);
				}
				reader.readAsDataURL(e.target.files[0]);
			}
		}); 

		fn_procedure_update();
		fn_update_ingredient();
		
	});
</script>

<%@ include file="/view/common/footer.jsp" %>