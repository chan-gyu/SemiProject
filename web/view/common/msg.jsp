<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	 String msg=(String)request.getAttribute("msg");
	String script=(String)request.getAttribute("script");
	String loc = (String)request.getAttribute("loc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템메세지</title>
</head>
<body>
	<script>
		alert("<%=msg%>");
		//페이지를 닫아주기
		<%=script!=null?script:""%>

		<%if(loc.equals("/")){%>
			window.close();
		<%}%>

		//페이지전환하는 로직구성
		location.replace("<%=request.getContextPath()%><%=request.getAttribute("loc")%>");
	</script>

</body>
</html>