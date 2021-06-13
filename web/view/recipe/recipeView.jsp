<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<%@ page import="java.util.*, com.yoriessence.recipe.model.vo.*, com.yoriessence.chef.model.vo.Profile" %>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<%
	Recipe recipe=(Recipe)request.getAttribute("recipeView");
	/* List<String> ingCategory=(List<String>)request.getAttribute("category"); */
	Map<String, List<RecipeIngredient>> ingredient=(Map<String, List<RecipeIngredient>>)request.getAttribute("ingredient");
	List<RecipeComment> comments=(List<RecipeComment>)request.getAttribute("comments");
	/* List<RecipePicture> pictures=(List<RecipePicture>)request.getAttribute("pictures"); */
	List<RecipeProcedure> procedure=(List<RecipeProcedure>)request.getAttribute("procedure");
	List<RecipeRecommend> recommend=(List<RecipeRecommend>)request.getAttribute("recommend");
	List<Profile> chefProfile=(List<Profile>)request.getAttribute("profile");
%>
<style>
                
    section{
        /* width:1100px; */
        margin:20px auto;
        padding :20px;
    }
    
    #chef_Profile{
        width:900px;
        display:flex;
        align-items:center;
        margin:0 auto;
        margin-top:30px;
        padding-bottom:20px;
        border-bottom:1px solid black;
        padding-top: 20px;
        border-top:1px solid black;
    
        justify-content:space-between;
    }
    .chefContainer{
        display:inline-flex;
    }
    .chefContainer .like{
        width: 30px;
        height: 30px;
    }
    #picContainer{
        margin-left: 30px;
    }
    #picContainer>img{
        border-radius: 200px;
    }
    
    #chefTitle{
        font-weight: bold;
        font-size:28px;
    }
    #chef_content .text{
        font-size: 20px;
    }
    #sendDM{
        margin-left: 65px;
        margin-top: 5px;
    }
    #chef_content{
        margin-left: 50px;
    }
    


	/* 레시피 출력부분 */
	
    #chef_recipe{
        width:900px;
        margin:0 auto;
        position:relative;
    }
    
    .recipe_container{
        margin:0 auto;
        width:900px;
    }
    
    .recipe_container>div{
        margin:0 auto;
        padding:50px 10px;
     	border-bottom:1px solid black;
	}
	
	/* 로그인 유저가 좋아요 누른 레시피일때 적용할 클래스 */
	.recommended{
		color:#1F695B ;
	}
	
	/* 레시피 정보 출력 */
    #recipe_info{
        text-align:center;
    }
    #recipe_info>*{
    	margin-bottom:20px;
    }
    
    /* 요리정보 출력 */
	#cooking_info>div{
		display:flex;
	}
	#cooking_info>div>p{
		margin-right:30px;
		margin-bottom:10px;
	}
    
    /* 요리 섬네일 */
    .recipe_thumbnail{
    	margin-bottom:20px;
    }
    
    #recipe_title{
    	display:block;
    	font-size:50px;
    	font-weight:bold;
    	margin-bottom:40px;
    	width:700px;
    	margin:0 auto;
    }
    
    #recipe_intro{
    	color:grey;
    	font-weight:bold;
    	font-size:30px;
    }
    
    .info_align{
    	display:flex; justify-content:space-between; padding:0 30px;
    }
                
	/* 레시피 메뉴 */
	.recipe_menu{
		position:absolute;
		top:50px;
		right:50px;
	}
	.recipe_menubar{
		display:none;
		border:#DCDCDC solid 1px;
		padding:10px;
		width:60px;
		position:absolute;
		top:75px;
		right:0px;
	}
	.recipe_menubar>*{
		display:block;
	}
	.recipe_menubar button{
		border:none;
		background-color:white;
	}
	
                
	/* 레시피 재료 */
	.info_title{
		font-size:20px;
		font-weight:bold;
		color:#1F695B;
		margin-bottom:20px;
	}
	
	.ingredient_container>*{
		display:inline-block;
		vertical-align:top;
	}
	.ingredient_category{
		width:200px;
	}
	
	.ingredient_li{
		display:flex; margin-bottom:10px;
	}
	
	.ingredient_name{
		width:200px;
	}
	.ingredient_amount{
		color:grey;
	}
	
	/* 레시피 과정 */
	.step{
		display:flex;
		margin:20px 0;
	}
	.step:last-child{
		margin-bottom:0;
	}
	.step_title{
		width:100px;
	}
	.step_content{
		width:680px;
	}
	.step_img{
		width:100px; height:100px;
		float:right;
	}
	

	/* 레시피 댓글 */
    .comment_container{
        margin:0 auto;
        padding:50px 10px;
     	border-bottom:1px solid black;
     	overflow:hidden;
	}
	
    #comment_title{
		margin-bottom:20px;
    }
    
    .profile_img{
    	width:50px; height:50px; float:left;
    	margin-right:10px;
    }
    
	/* 댓글 작성창 */
    #write_comment{
    	margin-bottom:20px;
    }
	
    #recipe_comment{
    	width:750px; height:50px;
    	resize:none;
    }
	
	#comment_submit{
		width:60px; height:50px; vertical-align:top;
	}
	
	/* 댓글창 */
	.comment_row{
		margin-bottom:20px;
     	overflow:hidden;
	}
	
    .comment_info{
    	display:flex;
    }
    
    img{
    	object-fit: cover;
    }

    
</style>

    <section>
        <div id="chef_Profile">
           <div class="chefContainer">
            <%if(chefProfile != null){%>
                <span id="picContainer">

                    <%if(chefProfile.get(0).getProfilePic() != null){%>
                        <img src="<%=request.getContextPath()%>/upload/profile/<%=chefProfile.get(0).getProfilePic()%>" height="200px" width="200px" style="border-radius: 200px">
                    <%}else{%>
                        <img src="<%=request.getContextPath()%>/img/icon/non_profile.png" height="200px" width="200px" style="border-radius: 200px">
                    <%}%>

                    <br><input type="button" id="sendDM" value="DM 보내기">
                </span>
                <img class="recommendStar" src="" width="35px" height="35px">
                <span id="chef_content">
                    <img src="<%=request.getContextPath()%>/img/icon/chef_cefti.png" width="50px" height="50px">
                    <span id="chefTitle"><%=chefProfile.get(0).getProfileName()%></span>
                    <span><img src="<%=request.getContextPath()%>/img/icon/icon_setting.png" width="40px" height="40px"></span>
                    <br><br><br>
                    <span class="text">
                        <p><a><%=chefProfile.get(0).getSelfIntro()%></a></p>
                        <br>

                        <%if(chefProfile.get(0).getProfileSnsUrl1() == null){%>
                            <span><a href=""><img class="snsIcon1" src=""></a></span>
                        <%}else{%>
                            <span><a href="https://<%=chefProfile.get(0).getProfileSnsUrl1()%>"><img class="snsIcon1" src=""></a></span>
                        <%}%>

                        <%if(chefProfile.get(0).getProfileSnsUrl2() == null){%>
                            <span><a href=""><img class="snsIcon2" src=""></a></span>
                        <%}else{%>
                            <span><a href="https://<%=chefProfile.get(0).getProfileSnsUrl2()%>"><img class="snsIcon2" src=""></a></span>
                        <%}%>
                    </span>
                </span>
                <script>
                    $("#sendDM").click(e=>{
                      <%if(loginMember !=null){%>
                          let url='<%=request.getContextPath()%>/messagelist?memberId=<%=loginMember.getUserId()%>&targetId=<%=chefProfile.get(0).getMemberId()%>';
                          let option ="width=520,height=770"
                          window.open(url,'_self',option);

                      <%}else{%>
                            if(confirm("로그인이 필요합니다. 로그인하시겠습니까?")){
                            trigger.click();
                            }
                      <%}%>
                    });

					$("#chefTitle + span>img").click((e)=>{
						<%if(loginMember != null
                               && (loginMember.getUserId().equals(chefProfile.get(0).getMemberId())
                                                     || loginMember.getUserId().equals("admin"))){%>
						// 로그인했고 작성자이거나 관리자이면
						location.replace('<%=request.getContextPath()%>/movereviseprofile?chefId=<%=chefProfile.get(0).getMemberId()%>');
						<%}else{%>
						alert("본인만 수정가능합니다");
						<%}%>
					});
                </script>
            <%}%>
        </div>
        </div>
        <div id="chef_recipe">
        	 <% if(loginMember!=null&&loginMember.getUserId().equals(recipe.getMemberId())) {%>
	        	<a class="recipe_menu">메뉴</a>
	      		<div class="recipe_menubar">
       				<form action="<%=request.getContextPath()%>/recipe/recipeUpdate?recipeEnrollNo=<%=recipe.getRecipeEnrollNo() %>" method="post">
		      			<button type="submit" id="recipeUpdate">수정</button>
		      		</form>
       				<form action="<%=request.getContextPath()%>/recipe/recipeDelete?recipeEnrollNo=<%=recipe.getRecipeEnrollNo() %>" method="post">
	      				<button type="submit" id="recipeDelete" onsubmit="fn_delete_validate()">삭제</button>
	      			</form>
	      		</div>
	      	<%} %>
            <div class="recipe_container">
                <div id="recipe_info">
                	<%if(recipe.getRepresentPicture()!=null){ %>
                    	<img class="recipe_thumbnail" src="<%=request.getContextPath() %>/upload/recipe/<%=recipe.getRepresentPicture() %>" width="500px" height="281px">
                   	<%} %>
                    <p id="recipe_title"><%=recipe.getRecipeTitle() %></p>
                    <%if(recipe.getRecipeIntro()!=null){%>
                    	<p id="recipe_intro"><%=recipe.getRecipeIntro() %></p>
                    <%} %>
                    <%if(recipe.getRecipeVideoAddress()!=null) {%>
                    	<p><%=recipe.getRecipeVideoAddress() %></p>
                    <%} %>
                    <div class="info_align">
                    	<%
                    	boolean flag=false;
                    	for(RecipeRecommend rr:recommend) { 
                    		/* if(rr.getMemberId().equals(loginMember.getUserId())) { */
                    		if(loginMember!=null&&rr.getMemberId().equals(loginMember.getUserId())) {
                    			flag=true;
                    			break;
                    		}%>
                    	<% } %>
                    	<span id="recommend_info" class=<%=flag?"recommended":""%>>좋아요 <%=recipe.getRecommendCount() %></span>
                    	<input type="checkbox" id="recommend_check" style="display:none" <%=flag?"checked":"" %>>
                    	<span id="comment_info">댓글 <%=comments.size() %></span>
                    	<span id="view_count_info">조회수 <%=recipe.getRecipeViewCount() %></span></div>
                </div>
                <div id="cooking_info">
                    <p class="info_title">요리 정보</p>
                    <div>
	                	<p><b>인원</b> <%=recipe.getRecipeInfoHowmany() %> 인분</p>
	                	<%
	                		String time="";
	                		if(recipe.getRecipeInfoTime()<=30) {
	                			time=recipe.getRecipeInfoTime()+"분 이내";
	                		}else if(recipe.getRecipeInfoTime()==121){
	                			time="2시간 이상";
	                		}else{
	                			time=recipe.getRecipeInfoTime()/60+"시간 이내";
	                		}
	                	%> 
	                	<p><b>시간</b><%=time %></p>
	                	<p><b>난이도</b> <%=recipe.getRecipeDifficult() %></p>
                	</div>
                	<div>
                		<p><b>주재료</b> <%=recipe.getMainIngredient() %></p>
                		<p><b>카테고리</b> <%=recipe.getRecipeCategory() %></p>
                	</div>
                </div>
                <div id="recipe_ingredient">
                    <p class="info_title">재료</p>
                   	<div>
	                    <%
	                    Set set=ingredient.entrySet();
	 	                   Iterator it=set.iterator();
	 	                   while(it.hasNext()){
		 	                   	Map.Entry e=(Map.Entry)it.next();
		 	                   	String ingCategory=(String)e.getKey();
		 	                   	List<RecipeIngredient> ingList=(List<RecipeIngredient>)e.getValue();%>
	                    	<div class="ingredient_container">
		                    	<p class="ingredient_category"><%=ingCategory%></p>
		                    	<ul class="ingredient_ul">
			                    	<%for(RecipeIngredient ri:ingList){%>
			                			<li class="ingredient_li">
			                				<p class="ingredient_name"><%=ri.getIngredientName() %></p>
			                				<p class="ingredient_amount"><%=ri.getIngredientAmount() %></p>
		                				</li>
			                  		<%}%>
		                  		</ul>
	                    	</div>
	                    <%} %>
                    </div>
                </div>
                <div id="recipe_procedure">
                    <p class="info_title">만드는 방법</p>
                    <div>
	                    <ul>
	                    	<%for(RecipeProcedure rp:procedure) {%>
	                        <li class="step">
	                        	<p class="step_title">Step <%=rp.getProcedureNo() %></p>
	                        	<p class="step_content"><%=rp.getProcedureContent()%></p>
 	                        	<%if(rp.getProcedurePicture()!=null){%>
	                        		<img src="<%=request.getContextPath() %>/upload/recipe/<%=rp.getProcedurePicture()%>" class="step_img"></li>
	                        	<%}%>
	                       	<% } %> 
	                    </ul>
                    </div>
                </div>
                <%if(recipe.getRecipeTip()!=null){ %>
                <div id="recipe_tip">
                    <p class="info_title">팁</p>
                    <div>
	                    <%=recipe.getRecipeTip()%>
                    </div>
                </div>
                <%} %>
            </div>
            <div class="comment_container">
            	<p class="info_title" id="comment_title">댓글 </p>
                <div id="write_comment">
                	<%if(loginMember!=null) {%>
	                    <img class="profile_img" src="">
                    <%}else{ %>
                    	<img class="profile_img" src="<%=request.getContextPath()%>/img/icon/non_profile.png">
                    <%} %>
                    <input type="hidden" name="recipe_enroll_no" id="recipe_enroll_no" value="<%=recipe.getRecipeEnrollNo()%>">
                    <input type="hidden" name="recipe_comment_writer" id="recipe_comment_writer" value="<%=loginMember!=null?loginMember.getUserId():null%>"> <%-- <%=loginMember.getUserId()%> --%>
                    <textarea name="recipe_comment" id="recipe_comment" rows="2" cols="150"></textarea>
                    <input type="submit" name="comment_submit" id="comment_submit" value="등록">
                </div>
                <div id="view_comment">
	                <%if(comments.size()!=0){ 
	                	for(RecipeComment c:comments){%>
		                <div class="comment_row">
		                    <img class="profile_img" src="<%=c.getWriterProfile()!=null?c.getWriterProfile():request.getContextPath()+"/img/icon/non_profile.png" %>">
		                    <div class="comment_info">
		                    	<a class="comment_writer"><%=c.getRecipeCommentWriter() %></a>
		                    	<p class="comment_date"><%=c.getCommentEnrollDate() %></p>
	                    	</div>
	                    	<p class="comment_content"><%=c.getRecipeComment() %></p>
		                </div>
	                	<%}
	                }else{%>
	                	<div class="no_comment">작성된 댓글이 없습니다.</div>
	                <%} %>
                </div>
            </div>
        </div>
    </section>
    
    <script>
    	$(function(){
    		
    		  'use strict;'
    		  function inputSNSIcon() {
    		    const path = "<%=request.getContextPath()%>/img/icon/icon_";
    		    const snsIcon1 = $(".snsIcon1");
    		    const snsIcon2 = $(".snsIcon2");


    		    if(snsIcon1.parent().attr("href").includes("instagram")){
    		      snsIcon1.attr("src",path+"insta.png");
    		    }else if(snsIcon1.parent().attr("href").includes("youtube")){
    		      snsIcon1.attr("src",path+"youtube.png");
    		    }else if(snsIcon1.parent().attr("href").includes("facebook")){
    		      snsIcon1.attr("src",path+"facebook.png");
    		    }else{
    		      snsIcon1.css("display","none");
    		    }

    		    if(snsIcon2.parent().attr("href").includes("instagram")){
    		      snsIcon2.attr("src",path+"insta.png");
    		    }else if(snsIcon2.parent().attr("href").includes("youtube")){
    		      snsIcon2.attr("src",path+"youtube.png");
    		    }else if(snsIcon2.parent().attr("href").includes("facebook")){
    		      snsIcon2.attr("src",path+"facebook.png");
    		    }else{
    		      snsIcon2.css("display","none");
    		    }
    		  };

    		  <%if(cookies!=null){%>
    		    $(".recommendStar").click((e)=>{
    		        <%if(loginMember == null){%>
    		            if(confirm("로그인이 필요합니다.로그인 하시겠습니까?")) {
    		              // 로그인 되어있는지 확인
    		              // 로그인 화면 실행
    		              trigger.click();
    		            }
    		        <%}else{%>
    		            <%for(Cookie c : cookies){%>
    		                  // 로그인을 했다면 쿠키확인

    		                 <%if(c!=null&&c.getName()!=null&&!c.getName().equals("checkRecommend"+chefProfile.get(0).getMemberId() )){%>
    		                    // 쿠키값이 없다면
    		                    $.ajax({
    		                      url:"<%=request.getContextPath()%>/recommendChef.do",
    		                      data:{
    		                        "chefId":"<%=chefProfile.get(0).getMemberId()%>",
    		                        "recommendYN":recommendChef()
    		                      },
    		                      success:data=>{
    		                        alert("추천 되었습니다.");
    		                      }
    		                    });
    		                <%}else{%>
    		                    // 쿠키값이 있다면
    		                    $.ajax({
    		                      url:"<%=request.getContextPath()%>/recommendChef.do",
    		                      data:{
    		                        "chefId":"<%=chefProfile.get(0).getMemberId()%>",
    		                        "recommendYN":cancelRecommend()
    		                      },
    		                      success:data=>{
    		                        alert("추천이 취소되었습니다.");
    		                      }
    		                    });
    		                <%}%>
    		            <%}%>
    		      <%}%>
    		    });
    		  <%}%>

    		  // 쿠키확인해서 추천이미지 변경
    		  function checkCookie(){
    		    <%if(cookies!=null){%>
    		        <%for(Cookie c : cookies){%>
    		            <%if(!c.getName().equals("checkRecommend"+chefProfile.get(0).getMemberId())){%>
    		                $(".recommendStar").attr("src","<%=request.getContextPath()%>/img/icon/recommend_unclick.png");
    		            <%}else{%>
    		                $(".recommendStar").attr("src","<%=request.getContextPath()%>/img/icon/recommend_click.png");
    		            <%}%>
    		        <%}%>
    		    <%}%>
    		  };
    		  checkCookie();

    		  function recommendChef(){
    		    if($(".recommendStar").attr("src").includes("un")){
    		      if(confirm("추천하시겠습니까?")){
    		        // 회원만 추천가능하도록 하자
    		        // 로그인 했는지 확인하는거 추가해야함
    		        $(".recommendStar").attr({
    		          src:"<%=request.getContextPath()%>/img/icon/recommend_click.png",
    		        });
    		        return 1;
    		        // 추천함
    		      }
    		    }
    		  };

    		  function cancelRecommend(){
    		    // 추천 안함
    		    if(confirm("추천을 취소하시겠습니까?")){
    		      $(".recommendStar").attr({
    		          src:"<%=request.getContextPath()%>/img/icon/recommend_unclick.png",
    		        });
    		      return 0;
    		    }
    		  };

    		
    	
    		//댓글 등록하는 기능
     		$("#comment_submit").click(e=>{
     			//아무것도 입력하지 않으면 등록 안되도록 처리
     			if(<%=loginMember==null%>){
		            if(confirm("로그인이 필요합니다.로그인 하시겠습니까?")) {
  		              // 로그인 되어있는지 확인
  		              // 로그인 화면 실행
  		              trigger.click();
  		            }
    			}else if($("#recipe_comment").val()==""){
     				alert("내용을 입력하세요.");
     			}else{
					$.ajax({
						url:"<%=request.getContextPath()%>/recipe/insertComment",
						data:{
							recipeEnrollNo:<%=recipe.getRecipeEnrollNo()%>,
							recipeComment:$("#recipe_comment").val(),
							recipeCommentWriter:$("#recipe_comment_writer").val()
						},
						success:data=>{
							$("#view_comment").html(data);
							$("#comment_info").text("댓글 "+$(".comment_row").length);
						}
					});
     			}
    		});
    		
    		const fn_delete_validate=()=>{
    			if(confirm("삭제 후엔 복구가 불가능합니다. 정말로 삭제하시겠습니까?")){
    				return true;
    			}
   				return false; 
    		}
    		
    		
    		$("a.recipe_menu").click(e=>{
    			const menu=$("div.recipe_menubar");
    			if(menu.css("display")=="block"){
    				menu.css("display","none");
    			}else{
    				menu.css("display","block");
    			}
    		});
    		
    		
    		$(".step_title").each((i,v)=>{
    			$(v).html("Step "+(i+1));
    		});
    		

    		$("#comment_title").html("댓글 <%=comments.size()%> 개");
    		
    		$("#recommend_info").click(e=>{
			    $("#recommend_check").click();
			    let checked=$("#recommend_check").prop("checked");
			    if(<%=loginMember==null%>){
		            if(confirm("로그인이 필요합니다.로그인 하시겠습니까?")) {
  		              // 로그인 되어있는지 확인
  		              // 로그인 화면 실행
  		              trigger.click();
  		            }
			    }else
			    if(<%=loginMember!=null&&!(loginMember.getUserId().equals(recipe.getMemberId()))%>){
		    			$.ajax({
		    				url:"<%=request.getContextPath()%>/recipe/recommend",
		    				data:{
		    					<%-- loginId:<%=loginMember.getUserId()%> --%>
		    					loginId:"testId2",
		    					recipeEnrollNo:<%=recipe.getRecipeEnrollNo()%>,
		    					check:checked
		    				},
		    				success:data=>{
	    						$("#recommend_info").html(data).toggleClass("recommended");
		    				}
		    			});
     			}else{
    				alert("자신의 레시피에는 추천할 수 없습니다.");
    			}
    		});
    		
    		
    		
    		
    	});
    </script>
    
<%@ include file="/view/common/footer.jsp"%>