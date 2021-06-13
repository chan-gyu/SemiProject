<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yoriessence.member.vo.Member" %>
<% 
	String AuthenticationKey = (String)request.getAttribute("AuthenticationKey");
	String userName = (String)request.getAttribute("userName");
	String userId = (String)request.getAttribute("userId");
	Member mName = (Member)request.getAttribute("mName");
	Member mId = (Member)request.getAttribute("mId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%if(userName!=null) {%>
	<div id="Container">
		<h2>이메일 전송이 완료되었습니다.</h2>
		<input type="text" id="check_key" placeholder="인증번호 입력">
		<button type="button" onclick="fn_check_Name();">인증</button>
	</div>
	<% }else if(userId!=null) {%>
	<div id="Container2">
		<h2>이메일 전송이 완료되었습니다.</h2>
		<input type="text" id="check_key" placeholder="인증번호 입력">
		<button type="button" onclick="fn_check_id();">인증</button>
		<form action="<%=request.getContextPath() %>/member/memberChangPw" id="ChangPwform" method="post" onsubmit="return fn_check_validate();">
			<input type="hidden" name="userId" value="<%=userId%>">
		</form>
	</div>
	<% }else if(userName==null&&userId==null){%>
	<div>
		<h2>이메일 전송이 완료되었습니다.</h2>
		<input type="text" id="check_key" placeholder="인증번호 입력">
		<button type="button" onclick="fn_check();">인증</button>
	</div>
	<% } %>
	
	<script>
		<%if(userName!=null) {%>
		const fn_check_Name=()=>{
			const AuthenticationKey='<%=AuthenticationKey%>';
			const check_key=$("#check_key").val();
			var kakao='kakao';
			if(AuthenticationKey!=check_key){
				alert("인증번호가 일치하지 않습니다.");
			}else{
				if(<%=mName.getSnsconn()%>!=null&&<%=mName.getSnsconn()%>==kakao){
					var p = document.createElement("p");
					p.style.color="red";
					p.innerHTML="회원님의 아이디는 카카오계정 입니다.";
					var div= document.getElementById("Container");
					div.append(p);
				}else{
					alert("인증되었습니다.");
					var p = document.createElement("p");
					p.style.color="red";
					p.innerHTML="회원님의 아이디는 "+'<%=mName.getUserId()%>'+" 입니다.";
					var div= document.getElementById("Container");
					div.append(p);
				}
			}
		}
		<% }else if(userId!=null) {%>
		const fn_check_id=()=>{
			const AuthenticationKey='<%=AuthenticationKey%>';
			const check_key=$("#check_key").val();
			var kakao='kakao';
			if(AuthenticationKey!=check_key){
				alert("인증번호가 일치하지 않습니다.");
			}else{
				if(<%=mId.getSnsconn()%>!=null&&<%=mId.getSnsconn()%>==kakao){
					var p = document.createElement("p");
					p.style.color="red";
					p.innerHTML="회원님은 카카오계정 입니다.";
					var div= document.getElementById("Container2");
					div.append(p);
				}else{
					var form=document.getElementById("ChangPwform");
					var h1 = document.createElement("h1");
					h1.innerHTML="비밀번호 변경";
					var input = document.createElement("input");
					input.setAttribute("type","password");
					input.setAttribute("name","password");
					input.setAttribute("placeholder","새 비밀번호");
					input.setAttribute("id","pw");
					var input2 = document.createElement("input");
					input2.setAttribute("type","password");
					input2.setAttribute("name","password2");
					input2.setAttribute("placeholder","새 비밀번호 확인");
					input2.setAttribute("id","pwck");
					input2.style.marginLeft="20px";
					var button = document.createElement("input");
					button.setAttribute("type","submit");
					button.innerHTML="변경하기";
					button.style.marginLeft="15px";
					form.append(h1);
					form.append(input);
					form.append(input2);
					form.append(button);
					alert("인증되었습니다.");
				}
				
				$(function(){
					$("#pwck").blur((e)=>{
						const pwck=$(e.target).val();
						const pw=$("#pw").val();
						if(pwck!=pw){
							alert("패스워드가 일치하지 않습니다.");
							$("#pw").focus();
						}
					});
				});
			}
		}
		const fn_check_validate=()=>{
			const pswd1=$("#pw").val();
			console.log(pswd1);
			const num = pswd1.search(/[0-9]/g);
			const eng = pswd1.search(/[a-z]/ig);
			if(pswd1.length < 8 || pswd1.length > 15){
				console.log("1");
				alert("비밀번호는 8자리 ~ 15자리 이내로 입력해주세요.");
				return false;
			}else if(pswd1.search(/\s/) != -1){
				console.log("2");
			 	alert("비밀번호는 공백 없이 입력해주세요.");
			  	return false;
			}else if(num < 0 || eng < 0){
				 console.log("3");
			  	alert("비밀번호는 영문,숫자를 혼합하여 입력해주세요.");
			  	return false;
			}
		}
		<% }else if(userName==null&&userId==null){%>
		const fn_check=()=>{
			const AuthenticationKey='<%=AuthenticationKey%>';
			const check_key=$("#check_key").val();
			if(AuthenticationKey!=check_key){
				alert("인증번호가 일치하지 않습니다.");
			}else{
				alert("인증되었습니다.");
				opener.memberEnrollFrm.check_email.value=AuthenticationKey;
				close();
			}
		}
		<% } %>
	</script>
</body>
</html>