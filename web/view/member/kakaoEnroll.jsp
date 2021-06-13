<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<%
	String kakaoUserId = (String)request.getAttribute("kakaoUserId");
	String kakaoUserEmail = (String)request.getAttribute("kakaoUserEmail");
	String kakaoUserName = (String)request.getAttribute("kakaoUserName");
%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/common/css/Enrollstyle.css" />
<div class="bodyall">
        <div class="joinPhrase">
            <h1>회원 추가정보 입력</h1>
            <br><br>
            <p>정보를 정확하게 입력해주세요.</p>
        </div>
        <br>
        <hr style="border: 1px #1F695B solid; margin-top:20px;" >
        <div class="infoinput">
        	<div class="divStyle">
            	<h4>필수정보입력</h4>
        	</div>
            <form name="memberEnrollFrm" action="<%=request.getContextPath()%>/member/memberKakaoEnrollEnd" method="post" id="test">
                <!--Id-->
                    <div>
                        <input type="hidden" id="userId" name="userId" class="int_id" maxlength="20" value="<%=kakaoUserId%>">
                    </div>
                <!--PW-->
                <div>
                	<input type="hidden" id="pswd1" name="password" class="int" maxlength="20" value="<%=kakaoUserId%>">
                </div>
                <!-- NAME -->
                <div class="divStyle">
                     <h3 class="join_title"><label for="name">이름</label></h3>
                    <span class="box int_name">
                        <input type="text" id="name" name="userName" class="int" maxlength="20" value="<%=kakaoUserName%>" readonly>
                    </span>
                    <span class="error_next_box"></span>
                </div>
        
                <!-- NiCKNAME -->
                <div class="divStyle">
                    <h3 class="join_title"><label for="nickname">닉네임</label></h3>
                    <div class="div_input_id">
                        <input type="text" id="nickname" name="userNick" class="nickname" maxlength="20">
                        <button type="button" class="div_input_id_button"  onclick="fn_Nick_duplicate();">중복확인</button>
                    </div>
                    <span class="error_next_box"></span>
                </div>
        
                <!-- EAMIL -->
                <div class="divStyle">
                    <h3 class="join_title"><label for="nickname">이메일</label></h3>
                    <div class="div_input_email">
                        <input type="email" id="email" name="email"  class="email" maxlength="20" value="<%= kakaoUserEmail%>" readonly>
                    </div>
                </div>
        
                <!-- POHNE -->
                <div class="divStyle">
                    <h3 class="join_title"><label for="nickname">휴대전화</label></h3>
                    <span class="box int_name">
                        <input type="text" id="phone" name="phone" class="int" maxlength="20" placeholder="-를 제외해주세요. 예)01012345678">
                    </span>
                    <span class="error_next_box"></span>
                </div>
        
                 <!-- ADDRESS -->
                <div class="divStyle">
                    <h3 class="join_title"><label for="nickname">주소</label></h3>
                     <div class="div_input_address">
                        <input type="text" id="mainaddress" name="address" class="address" maxlength="50" placeholder="우편번호 / 주소 / 상세주소 ">
                        <button type="button" onclick="findAddr();" class="div_input_addr_button">우편번호 찾기</button>
                    </div>
                    <span class="error_next_box"></span>
                </div>
                <br>
	            <hr style="border: 1px #1F695B solid; margin-top:20px;">
	            <br>
	         	<div>
	         		<input type="submit" value="가입하기" style="margin-left:295px; width:100px; height:30px;">
	            </div>
	       </form>
          </div>
		  <form action="" name="checkDuplicateNick">
		  	<input type="hidden" name="nickName">
		  </form>
</div>
<script>
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
		if(pswd1==""){
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
	}
</script>
<%@ include file="/view/common/footer.jsp"%>