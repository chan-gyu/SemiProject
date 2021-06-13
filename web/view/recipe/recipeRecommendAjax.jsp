<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.yoriessence.recipe.model.vo.RecipeRecommend" %>
<%
	List<RecipeRecommend> recommend=(List<RecipeRecommend>)request.getAttribute("recommend");
%>
좋아요 <%=recommend.size() %>