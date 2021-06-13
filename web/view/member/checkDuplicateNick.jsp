<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.yoriessence.member.vo.Member" %>
<%
	Member result = (Member)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 중복 확인</title>
<style>
	div#checkId-container{text-aligh:center;padding-top:50px}
	span#duplicated{color:red;font-weight:bolder}
</style>
</head>
<body>
	<div id="checkId-container">
		<%if(result==null){ %>
			[<span><%=request.getParameter("nickName") %></span>]는 사용가능합니다.
			<br><br>
			<button type="button" onclick="fn_close();">닫기</button>
		<%}else{ %>
		[<span id="duplicated"><%=request.getParameter("nickName") %></span>]는 사용중입니다.
			<br><br>
			<form action="<%=request.getContextPath()%>/checkDuplicateNick" method="post">
				<input type="text" name="nickName" id="nickName">
				<input type="submit" value="중복검사">
			</form>
		<%} %>
	</div>
	<script>
		const fn_close=()=>{
			const nickname='<%=request.getParameter("nickName")%>';
			opener.memberEnrollFrm.nickname.value=nickname;
			//윈도우창 닫기
			close();
		}
	</script>

</body>
</html>