<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yoriessence.recipe.model.vo.Recipe, com.yoriessence.shopping.vo.Product, java.util.List, java.util.Map" %>
<%
	List<Recipe> recipeList=(List<Recipe>)request.getAttribute("recipeList");
	Map<String, String> nicknameMap=(Map<String, String>)request.getAttribute("nicknameMap");
%>
			<%if(recipeList.size()!=0){ 
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
				<div></div><p>검색 결과가 없습니다.</p><div></div>
			<%} %>
		</div>
		<div id="pageBar"><%=request.getAttribute("pageBar")!=null?request.getAttribute("pageBar"):"" %></div>