<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
	<div class="Container">
		<div>
			<h1>아이디 찾기</h1>
			<div  class="sub_Container">
				<label for="userName" style="margin-right: 10px"> 회원 이름</label><input type="text" id="userName" name="userName" class="div_input"><br>
				<label for="email" style="margin-right: 26px">이메일</label> <input type="text" id="email" name="email" class="div_input">
				<button type="button" class="div_btn" onclick="id_send_email();">인증</button>
			</div>
		</div>
		<hr style="margin-top:50px; border: 1.5px #1F695B solid;">
		<div style="margin-top:50px;">
			<h1>비밀번호 찾기</h1>
			<div class="sub_Container">
				<label for="userId" style="margin-right: 10px">회원 아이디</label><input type="text" id="userId" name="userId" class="div_input"><br>
				<label for="email2" style="margin-right: 42.5px">이메일</label> <input type="text" id="email2" name="email2" class="div_input">
				<button type="button" class="div_btn" onclick="pw_send_email();">인증</button>
			</div>
		</div>
		<form action="" name="sendEmailform">
    	<input type="hidden" name="email">
    	<input type="hidden" name="name">
    	</form>
    	<form action="" name="sendEmailform2">
    	<input type="hidden" name="email">
    	<input type="hidden" name="id">
    	</form>
	</div>
	
	<style>
	.Container{
		width:650px;
		height:500px;
		margin-top:100px;
		margin-left:500px;
	}
	.sub_Container{
		margin-top:20px;
		display:space-around;
	}
	.div_input{
		width: 300px;
		height: 50px;
		border: solid 1px #dadada;
	}
	.div_btn{
		width:50px;
		height:50px
	}
	</style>
	<script>
		//아이디 찾기 이메일보내기
		const id_send_email=()=>{
			const url = "<%=request.getContextPath()%>/member/email";
			const title = "EnrollEmail";
			const status = "left=500px, top=100px, width=600px, height=300px";
			open("",title,status);
			sendEmailform.email.value=$("#email").val();
			sendEmailform.name.value=$("#userName").val();
			sendEmailform.method="post";
			sendEmailform.action=url;
			sendEmailform.target=title;
			sendEmailform.submit();
		}
		//비밀번호 찾기 이메일보내기
		const pw_send_email=()=>{
			const url = "<%=request.getContextPath()%>/member/email";
			const title = "EnrollEmail";
			const status = "left=500px, top=100px, width=600px, height=300px";
			open("",title,status);
			sendEmailform2.email.value=$("#email2").val();
			sendEmailform2.id.value=$("#userId").val();
			sendEmailform2.method="post";
			sendEmailform2.action=url;
			sendEmailform2.target=title;
			sendEmailform2.submit();
		}
	</script>
<%@ include file="/view/common/footer.jsp"%>