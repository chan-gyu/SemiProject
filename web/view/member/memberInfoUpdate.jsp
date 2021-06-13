<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%
	Member m = (Member)request.getAttribute("member");
%>
<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/common/css/Enrollstyle.css" />
<section>
 <div class="bodyall">
     <div class="joinPhrase">
         <h1>회원 정보 수정</h1>
         <br><br>
         <p>정보를 정확하게 입력해주세요.</p>
     </div>
     <br>
     <hr style="border: 1px #1F695B solid; margin-top:20px;" >
     <div class="infoinput">
     	<div class="divStyle">
         	<h4>필수정보입력</h4>
     	</div>
         <%if(m.getSnsconn()!=null&&m.getSnsconn().equals("kakao")){ %>
         <form name="memberEnrollFrm" action="<%=request.getContextPath()%>/member/memberUpdate" method="post" onsubmit="return fn_kakaoenroll_validate();">
             <!--Id-->
              <div>
              	<input type="hidden" id="snsconn" name="snsconn" value="kakao">
                <input type="hidden" id="userId" name="userId" class="int_id" maxlength="20" value="<%=m.getUserId()%>" readonly>
              </div>
             <!--PW-->
             <div>
             	<input type="hidden" id="pswd1" name="password" class="int" maxlength="20" value="<%=m.getUserId()%>">
             	<input type="hidden" id="pswd2" name="password2" class="int" maxlength="20" value="<%=m.getUserId()%>">
             </div>
             <!-- <!-- PW2 -->
             <div>
             	<input type="hidden" id="pswd2" name="userPwck" class="int" maxlength="20">
             </div>
             <!-- NAME -->
             <div class="divStyle">
                  <h3 class="join_title"><label for="name">이름</label></h3>
                 <span class="box int_name">
                     <input type="text" id="name" name="userName" class="int" maxlength="20" value="<%=m.getUserName()%>" readonly>
                 </span>
                 <span class="error_next_box"></span>
             </div>
     
             <!-- NiCKNAME -->
             <div class="divStyle">
                 <h3 class="join_title"><label for="nickname">닉네임</label></h3>
                 <div class="div_input_id">
                     <input type="text" id="nickname" name="userNick" class="nickname" maxlength="20" value="<%=m.getNickName()%>">
                     <button type="button" class="div_input_id_button"  onclick="fn_Nick_duplicate();">중복확인</button>
                 </div>
                 <span class="error_next_box"></span>
             </div>
     
             <!-- EAMIL -->
             <div class="divStyle">
                 <h3 class="join_title"><label for="nickname">이메일</label></h3>
                 <div class="div_input_email">
                     <input type="email" id="email" name="email"  class="email" maxlength="20" value="<%=m.getEmail()%>" readonly>
                 </div>
             </div>
     	<%}else{ %>
     	<form name="memberEnrollFrm" action="<%=request.getContextPath()%>/member/memberUpdate" method="post" onsubmit="return fn_enroll_validate();">
     		<!--Id-->
             <div class="divStyle">
                 <div>
                     <h3 class="join_title">
                         <label for="id">아이디</label>
                     </h3>
                  </div>
                 <div class="div_input_id">
                     <input type="text" id="userId" name="userId" class="int_id" maxlength="50" value="<%=m.getUserId()%>" readonly>
                 </div>
                 <span class="error_next_box"></span>
             </div>
     
             <!--PW-->
             <div class="divStyle">
                 <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
                 <span class="box int_pass">
                     <input type="password" id="pswd1" name="password" class="int" maxlength="20">
                     <span id="alertTxt">사용불가</span>
                     <img src="<%=request.getContextPath()%>/img/icon/icon_check_disable.png" id="pswd1_img1" class="pswdImg">
                 </span>
                 <span class="error_next_box"></span>
             </div>
     
             <!-- PW2 -->
             <div class="divStyle">
                  <h3 class="join_title"><label for="pswd2">비밀번호 재확인</label></h3>
                 <span class="box int_pass_check">
                     <input type="password" id="pswd2" name="password2" class="int" maxlength="20">
                     <img src="<%=request.getContextPath()%>/img/icon/icon_pass.png" id="pswd2_img1" class="pswdImg">
                 </span>
                 <span class="error_next_box"></span>
             </div>
     
             <!-- NAME -->
             <div class="divStyle">
                  <h3 class="join_title"><label for="name">이름</label></h3>
                 <span class="box int_name">
                     <input type="text" id="name" name="userName" class="int" maxlength="20" value="<%=m.getUserName()%>">
                 </span>
                 <span class="error_next_box"></span>
             </div>
     
             <!-- NiCKNAME -->
             <div class="divStyle">
                 <h3 class="join_title"><label for="nickname">닉네임</label></h3>
                 <div class="div_input_id">
                     <input type="text" id="nickname" name="userNick" class="nickname" maxlength="20" value="<%=m.getNickName()%>">
                     <button type="button" class="div_input_id_button"  onclick="fn_Nick_duplicate();">중복확인</button>
                 </div>
                 <span class="error_next_box"></span>
             </div>
     
             <!-- EAMIL -->
             <div class="divStyle">
                 <h3 class="join_title"><label for="nickname">이메일</label></h3>
                 <div class="div_input_email">
                     <input type="email" id="email" name="email"  class="email" maxlength="20" value="<%=m.getEmail()%>">
                     <button type="button" class="div_input_email_button" onclick="send_email();">이메일 인증</button>
                 </div>
                 <div class="div_input_email" style="margin-top: 10px;">
                     <input type="text" id="check_email" class="email" placeholder="인증번호" disabled>
                 </div>
                 <span class="error_next_box"></span>
             </div>
     	<%} %>
             <!-- POHNE -->
             <div class="divStyle">
                 <h3 class="join_title"><label for="nickname">휴대전화</label></h3>
                 <span class="box int_name">
                     <input type="text" id="phone" name="phone" class="int" maxlength="20" value="<%=m.getPhone()%>">
                 </span>
                 <span class="error_next_box"></span>
             </div>
     
              <!-- ADDRESS -->
             <div class="divStyle">
                 <h3 class="join_title"><label for="nickname">주소</label></h3>
                  <div class="div_input_address">
                     <input type="text" id="mainaddress" name="address" class="address" maxlength="50" value="<%=m.getAddress()%>">
                     <button type="button" onclick="findAddr();" class="div_input_addr_button">우편번호 찾기</button>
                 </div>
                 <span class="error_next_box"></span>
             </div>
             <br>
          <hr style="border: 1px #1F695B solid; margin-top:20px;">
          <br>
          <div class="joinCheck" >
          	<input type="hidden" name="grade" value="<%=m.getMembergrade()%>">
         	<input type="hidden" name="point" value="<%=m.getPoint()%>">
             <input type="submit" value="정보수정하기" style="margin-left:295px; width:100px; height:30px;">
          </div>
     </form>
       </div>
 </div>
 <form action="" name="checkDuplicateId">
 	<input type="hidden" name="userId">
 </form>
 <form action="" name="checkDuplicateNick">
    	<input type="hidden" name="nickName">
    </form>
 <form action="" name="sendEmailform">
 	<input type="hidden" name="email">
 </form>
<script>
	//비밀번호 값 일치 check
	$(function(){
		$("#pswd2").blur((e)=>{
			const pwck=$(e.target).val();
			const pw=$("#pswd1").val();
			if(pwck!=pw){
				alert("패스워드가 일치하지 않습니다.");
				$("#pswd1").focus();
			}
		});
	});
	//아이디 중복검사
	const fn_id_duplicate=()=>{
		const url = "<%=request.getContextPath()%>/checkDuplicateId";
		const title = "checkDuplicateId";
		const status = "left=500px, top=100px, width=600px, height=300px";
		open("",title,status);
		checkDuplicateId.userId.value=$("#userId").val();
		checkDuplicateId.method="post";
		checkDuplicateId.action=url;
		checkDuplicateId.target=title;
		checkDuplicateId.submit();
	}
	//닉네임 중복검사
	const fn_Nick_duplicate=()=>{
		const url = "<%=request.getContextPath()%>/checkDuplicateNick";
		const title = "checkDuplicateNick";
		const status = "left=500px, top=100px, width=600px, height=300px";
		open("",title,status);
		checkDuplicateNick.nickName.value=$("#nickname").val();
		checkDuplicateNick.method="post";
		checkDuplicateNick.action=url;
		checkDuplicateNick.target=title;
		checkDuplicateNick.submit();
	}
	//이메일보내기
	const send_email=()=>{
		const url = "<%=request.getContextPath()%>/member/email";
		const title = "EnrollEmail";
		const status = "left=500px, top=100px, width=600px, height=300px";
		open("",title,status);
		sendEmailform.email.value=$("#email").val();
		sendEmailform.method="post";
		sendEmailform.action=url;
		sendEmailform.target=title;
		sendEmailform.submit();
	}
	//우편주소
    function findAddr(){
        new daum.Postcode({
            oncomplete: function(data) {
            	var addr="";
            	//지번인지 도로명인지 분기처리
            	if(data.userSelectedType =='R'){ //도로명 선택
            		addr=data.roadAddress;
            	}else{ //지번선택
            		addr=data.jibunAddress;
            	}
           		document.getElementById('mainaddress').value = addr;
           		document.getElementById('subaddress').focus();
            }
        }).open();
    }
  //입력부분 비어있으면 전송X
	const fn_enroll_validate=()=>{
		const userId=$("#userId");
		if(userId.val().length<4){
			alert("아이디는 최소 5자리 이상이여야 합니다.");
			userId.focus();				
			return false;
		}
		const pswd1=$("#pswd1").val();
		const pswd2=$("#pswd2").val();
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
		if(pswd1==""||pswd2==""){
			alert("비밀번호를 입력해야합니다.")
			return false;
		}
		const name=$("#name").val();
		if(name==""){
			alert("이름을 입력해야 합니다.")
			return false;
		}
		const Nick=$("#nickname").val();
		if(Nick==""){
			alert("닉네임을 입력해야 합니다.")
			return false;
		}
		const check_email=$("#check_email").val();
		 if(check_email==""){
			alert("이메일 인증이 필요합니다.");
			return false;
		}
		const phone=$("#phone").val();
		if(phone==""){
			alert("전화번호를 입력해야 합니다.")
			return false;
		}
		const address=$("#mainaddress").val();
		if(address==""){
			alert("주소를 입력해야 합니다.")
			return false;
		}
		if(document.getElementById("checkbox1").checked==false){
			alert("개인정보 수집 및 이용양관에 동의하셔야 합니다.")
			return false;
		}
		
	}
	const fn_kakaoenroll_validate=()=>{
		const userId=$("#userId");
		if(userId.val().length<4){
			alert("아이디는 최소 5자리 이상이여야 합니다.");
			userId.focus();				
			return false;
		}
		const pswd1=$("#pswd1").val();
		const pswd2=$("#pswd2").val();
		if(pswd1==""||pswd2==""){
			alert("비밀번호를 입력해야합니다.")
			return false;
		}
		const name=$("#name").val();
		if(name==""){
			alert("이름을 입력해야 합니다.")
			return false;
		}
		const Nick=$("#nickname").val();
		if(Nick==""){
			alert("닉네임을 입력해야 합니다.")
			return false;
		}
		const check_email=$("#check_email").val();
		 if(check_email==""){
			alert("이메일 인증이 필요합니다.");
			return false;
		}
		const phone=$("#phone").val();
		if(phone==""){
			alert("전화번호를 입력해야 합니다.")
			return false;
		}
		const address=$("#mainaddress").val();
		if(address==""){
			alert("주소를 입력해야 합니다.")
			return false;
		}
		if(document.getElementById("checkbox1").checked==false){
			alert("개인정보 수집 및 이용양관에 동의하셔야 합니다.")
			return false;
		}
	}
</script>
</section>
<%@ include file="/view/common/footer.jsp"%>