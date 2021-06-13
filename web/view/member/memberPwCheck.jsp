<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<%
	Member m = (Member)request.getAttribute("member");
%>
<section>
	<div id="update_div">
		<h1>회원정보수정</h1><br>
		<p>개인정보 보호를 위해 비밀번호를 확인 합니다.</p><br>
		<form action="<%=request.getContextPath() %>/member/memberupdateConn" id="checkform" name="checkform" method="post" onsubmit="fn_pwck_validate();"> <!-- 암호화오류해결 return추가 -->
			비밀번호 <input type="password" id="up_pw" class="up_pw">
			<input type="hidden" name="userId" value="<%=loginMember.getUserId()%>">
			<button type="submit">확인</button>
		</form>
	</div>
</section>

<style>
#update_div{
	margin-top: 100px;
	margin-left: 500px;
	width:500px;
	height:300px;
	border:1px #1F695B solid;
	text-align:center;
}
p{
	margin-top:10px;
}
div>h1{
	margin-top:40px;
	font-size:50px;
}
#up_pw{
	width: 200px;
	height: 30px;
	border: solid 1px #dadada;
}
form{
	margin-top:50px;
	display:inline-block;
}
button{
	width: 50px;
	height: 30px;
}
</style>

<script>
	$(function(){
		var kakao='kakao';
		if(<%=m.getSnsconn()%>==kakao){
			console.log(<%=m.getSnsconn()%>);
			checkform.submit();
		}
	});
	const fn_pwck_validate=()=>{
		const uppw=$("#up_pw");
		const pw = '<%=m.getPassword()%>';
		if(uppw.val()==""||uppw.val()!=pw){
			alert("비밀번호가 일치하지 않습니다.");
			uppw.focus();
			return false;
		}
	}
</script>
<%@ include file="/view/common/footer.jsp"%>