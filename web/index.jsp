<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 2021/04/29
  Time: 11:31 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="view/common/header.jsp"%>
<%--        header 끝 section 시작--%>
<div class="main-content">
    <div id ="banner">
        <img src="<%=request.getContextPath()%>/img/mainImg/bannerpng.png"
             width="1500px" height="300px">
    </div>
    <div id="recommend_recipe">
    </div>  
    <div id="topChef">
    </div>

    <div id="recipe">
    </div>

<%--        section 끝 footer 시작--%>
<%@ include file="view/common/footer.jsp"%>
