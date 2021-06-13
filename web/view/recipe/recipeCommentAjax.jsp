<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.yoriessence.recipe.model.vo.RecipeComment" %>
<%
	List<RecipeComment> comments=(List<RecipeComment>)request.getAttribute("comments");
%>

<style>

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
    

</style>

    <%if(comments.size()!=0){ 
    	for(RecipeComment c:comments){%>
     <div class="comment_row">
         <img class="profile_img" src="">
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

<script>
	$(function(){

		$("#comment_title").html("댓글 <%=comments.size()%> 개");
	})
</script>

