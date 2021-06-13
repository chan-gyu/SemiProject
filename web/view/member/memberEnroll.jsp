<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/view/common/header.jsp"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/common/css/Enrollstyle.css" />
    <div class="bodyall">
        <div class="joinPhrase">
            <h1>회원가입</h1>
            <br><br>
            <p>정보를 정확하게 입력해주세요.</p>
        </div>
        <br>
        <hr style="border: 1px #1F695B solid; margin-top:20px;" >
        <div class="infoinput">
        	<div class="divStyle">
            	<h4>필수정보입력</h4>
        	</div>
            <form name="memberEnrollFrm" action="<%=request.getContextPath()%>/member/memberEnrollEnd" method="post" onsubmit="return fn_enroll_validate();" id="test">
                <!--Id-->
                <div class="divStyle">
                    <div>
                        <h3 class="join_title">
                            <label for="id">아이디</label>
                        </h3>
                     </div>
                    <div class="div_input_id">
                        <input type="text" id="userId" name="userId" class="int_id" maxlength="20" placeholder="5글자이상">
                        <button type="button" class="div_input_id_button"  onclick="fn_id_duplicate();">중복확인</button>
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
                        <input type="text" id="name" name="userName" class="int" maxlength="20">
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
                        <input type="email" id="email" name="email"  class="email" maxlength="20" placeholder="example@naver.com">
                        <button type="button" class="div_input_email_button"onclick="send_email();">이메일 인증</button>
                    </div>
                    <div class="div_input_email" style="margin-top: 10px;">
                        <input type="text" id="check_email" class="email" placeholder="인증번호" disabled>
                    </div>
                    <span class="error_next_box"></span>
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
                        <input type="text" id="mainaddress" name="address" class="address" maxlength="50" placeholder="우편번호 / 주소 / 상세주소">
                        <button type="button" onclick="findAddr();" class="div_input_addr_button">우편번호 찾기</button>
                    </div>
                    <span class="error_next_box"></span>
                </div>
                <br>
	            <hr style="border: 1px #1F695B solid; margin-top:20px;">
	            <br>
	            <div class="joinCheck" >
	                 <input type="checkbox" id="checkbox1"> 동의(필수)
	                 <br>
	                 <br>
	                 <textarea name="areacontent" cols="95" rows="10">
	                 	개인정보 수집 및 이용약관

						서브타이틀아이콘 제1조(개인정보의 처리목적)
						
						① 통계청은 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며,
						    이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.
						
						  1. 홈페이지 회원 가입 및 관리 회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증, 회원자격 유지·관리,
						  제한적 본인확인제 시행에 따른 본인확인, 서비스 부정이용 방지, 만 14세 미만 아동의 개인정보 처리시
						  법정대리인의 동의여부 확인등을 목적으로 개인정보를 처리합니다.
						  ※ 좀더 상세한 통계청의 개인정보파일 등록사항 공개는 행정자치부 개인정보보호 종합지원 포털(www.privacy.go.kr)
						  → 개인정보민원 → 개인정보열람등 요구 → 개인정보파일 목록검색 메뉴를 활용해주시기 바랍니다.
						
						서브타이틀아이콘 제2조(개인정보의 처리 및 보유기간)
						
						① 통계청은 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집시에 동의받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.
						② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.
						
						  1. 보유근거 : 이용약관
						  2. 개인정보 보유 목적: 홈페이지 회원 가입 및 관리
						  3. 보유기간 : 2년
						
						서브타이틀아이콘 제3조(개인정보의 제3자 제공)
						
						① 통계청은 정보주체의 동의, 법률의 특별한 규정 등 개인정보 보호법 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.
						
						서브타이틀아이콘 제4조(개인정보처리의 위탁)
						
						① 통계청은 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.
						
						  1. <통계분류포털 홈페이지 운영 및 유지관리>
						  - 위탁받는 자 (수탁자) : (주)마리너스시스템
						  - 위탁하는 업무의 내용 : 회원정보 수집 프로그램 및 데이터베이스 유지보수
						  2. 통계청은 위탁계약 체결시 개인정보 보호법 제25조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적관리적 보호조치,
						  재위탁 제한, 수탁자에 대한 관리감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게
						  처리하는지를 감독하고 있습니다.
						  3. 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.
						
						서브타이틀아이콘 제5조(정보주체의 권리·의무 및 행사방법)
						
						① 정보주체는 통계청에 대해 언제든지 다음 각 호의 개인정보 보호 관련 권리를 행사할 수 있습니다.
						
						  1. 개인정보 열람요구
						  2. 오류 등이 있을 경우 정정 요구
						  3. 삭제요구
						  4. 처리정지 요구
						
						② 제1항에 따른 권리 행사는 통계청에 대해 개인정보 보호법 시행규칙 별지 제8호 서식에 따라 서면, 전자우편, 모사전송(FAX) 등을
						    통하여 할 수 있으며 통계청은 이에 대해 지체 없이 조치하겠습니다.
						③ 정보주체가 개인정보의 오류 등에 대한 정정 또는 삭제를 요구한 경우에는 통계청은 정정 또는 삭제를 완료할 때까지 당해
						    개인정보를 이용하거나 제공하지 않습니다.
						④ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 할 수 있습니다. 이 경우 개인정보 보호법
						시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.
						
						서브타이틀아이콘 제6조(처리하는 개인정보 항목) 통계청은 다음의 개인정보 항목을 처리하고 있습니다.
						
						① 홈페이지 회원 가입 및 관리
						
						  1. 필수항목 : 성명, 아이디, 비밀번호, 전화번호, 이메일, 직업
						  2. 선택항목 : 생년월일, 보유 면허증
						② 인터넷 서비스 이용과정에서 아래 개인정보 항목이 자동으로 생성되어 수집될 수 있습니다.
						
						  1. IP주소, 쿠키, MAC주소, 서비스 이용기록, 방문기록, 불량 이용기록 등
						
						서브타이틀아이콘 제7조(개인정보의 파기)
						
						① 통계청은 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를
						     파기합니다.
						② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속
						보존하여야 하는 경우에는, 해당 개인정보(또는 개인정보파일)를 별도의 데이터베이스(DB)로 옮기거나 보관 장소를 달리하여
						    보존합니다.
						③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.
						
						  1. 파기절차 통계청은 파기하여야 하는 개인정보(또는 개인정보파일)에 대해 개인정보 파기계획을 수립하여 파기합니다.
						     통계청은 파기 사유가 발생한 개인정보(또는 개인정보파일)를 선정하고, 통계청의 개인정보 보호책임자의 승인을 받아
						    개인정보(또는 개인정보파일)를 파기합니다.
						
						  2. 파기방법 통계청은 전자적 파일 형태로 기록·저장된 개인정보는 기록을 재생할 수 없도록 파기하며,
						     종이 문서에 기록·저장된 개인정보는 분쇄기로 분쇄하거나 소각하여 파기합니다.
						
						서브타이틀아이콘 제8조(개인정보의 안전성 확보조치)
						
						① 통계청은 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.
						
						  1. 관리적 조치 : 내부관리계획 수립·시행, 정기적 직원 교육 등
						  2. 기술적 조치 : 개인정보처리시스템 등의 접근권한 관리, 접근통제시스템 설치, 고유식별 정보 등의 암호화, 보안프로그램 설치
						  3. 물리적 조치 : 전산실, 자료보관실 등의 접근통제
						
						서브타이틀아이콘 제9조(개인정보 보호책임자)
						
						① 통계청은 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여
						아래와 같이 개인정보 보호책임자를 지정하고 있습니다.
						
						  1.개인정보 보호책임자 성명 : 최연옥, 직책 : 통계정보국장, 직급 : 일반직 고위공무원, 연락처 : ☎ 042-481-2390,
						    FAX: 042-481-2474※ 개인정보 보호 담당부서로 연결됩니다.
						    정보주체께서는 통계청 서비스를 이용하면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보
						    보호책임자 및 담당부서로 문의할 수 있습니다. 통계청은 정보주체의 문의에 대해 지체없이 답변 및 처리해드릴 것입니다.
						
						서브타이틀아이콘 제10조(개인정보 열람청구)
						
						① 정보주체는 개인정보 보호법 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다. 통계청은 정보주체의 개인정보 열람청구가
						신속하게 처리되도록 노력하겠습니다.
						
						1. 개인정보 열람청구 접수 : 통계기준과 담당자 : 정언진 연락처 : ☎ 042-481-2529 FAX: 042-481-2180
						2. 정보주체께서는 제1항의 열람청구 접수·처리부서 이외에, 행정자치부의 '개인정보보호 종합지원 포털' 웹사이트를 통하여서도
						    개인정보 열람청구를 하실 수 있습니다.
						    행정자치부 개인정보보호 종합지원 포털 → 개인정보 민원 → 개인정보 열람등 요구(본인확인을 위하여 아이핀(I-PIN)이 있어야 함)
						
						서브타이틀아이콘 제11조(권익침해 구제방법)
						
						① 정보주체는 아래의 기관에 대해 개인정보 침해에 대한 피해구제, 상담 등을 문의하실 수 있습니다.아래의 기관은 통계청과는
						    별개의 기관으로서, 통계청의 자체적인 개인정보 불만처리, 피해구제 결과에 만족하지 못하시거나 보다 자세한 도움이
						    필요하시면 문의하여 주시기 바랍니다.
						
						  1. 개인정보보호 종합지원 포털 (행정자치부 운영)
						  - 소관업무 : 개인정보 침해사실 신고, 상담 신청, 자료제공
						  - 홈페이지 : www.privacy.go.kr
						  - 전화 : 02-2100-3394
						  2. 개인정보 침해신고센터 (한국인터넷진흥원 운영)
						  - 소관업무 : 개인정보 침해사실 신고, 상담 신청
						  - 홈페이지 : privacy.kisa.or.kr
						  - 전화 : (국번없이) 118
						  - 주소 : (138-950) 서울시 송파구 중대로 135 한국인터넷진흥원 개인정보침해신고센터
						  3. 개인정보 분쟁조정위원회 (한국인터넷진흥원 운영)
						  - 소관업무 : 개인정보 분쟁조정신청, 집단분쟁조정 (민사적 해결)
						  - 홈페이지 : privacy.kisa.or.kr
						  - 전화 : (국번없이) 118
						  주소 : (138-950) 서울시 송파구 중대로 135 한국인터넷진흥원 개인정보침해신고센터
						  4.경찰청 사이버테러대응센터
						  - 소관업무 : 개인정보 침해 관련 형사사건 문의 및 신고
						  - 홈페이지 : www.netan.go.kr
						  - 전화 : (사이버범죄) 02-393-9112, (경찰청 대표) 1566-0112
						
						서브타이틀아이콘 제12조(개인정보 처리방침 변경)
						
						① 이 개인정보 처리방침은 2014. 9. 1.부터 적용됩니다.
						  1. 2014. 9. 1 적용
	                 </textarea>
	                <br>
	                <br>
	                <input type="submit" value="가입하기" style="margin-left:295px; width:100px; height:30px;">
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
	
</script>
<%@ include file="/view/common/footer.jsp"%>