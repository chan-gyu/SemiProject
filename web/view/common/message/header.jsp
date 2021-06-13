<%@ page import="com.yoriessence.member.vo.Member" %><%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 2021/05/25
  Time: 9:15 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Member loginMember=(Member)session.getAttribute("loginMember");
    Cookie[] cookies=request.getCookies();
    String saveId=null;
    if(cookies!=null){
        for(Cookie c : cookies){
            if(c.getName().equals("saveId")){
                saveId=c.getValue();
                break;
            }
        }
    }
%>
<html>
<head>
    <title>요리에센스</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <meta name ="google-signin-client_id" content="393793381722-emq1t5c5as3afleds82jf0u633h15l39.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/common/css/common.css" />
</head>
<script>
  // 가져와야할것.
  // 셰프 좋아요순 5명
  // 레시피 좋아요순 3개
  // 레시피 3개
  function mustStart() {
    $.ajax({
      url: '<%=request.getContextPath()%>/mainpage.do',
      success: data => {
        $("#recommend_recipe *").remove();
        $("#topChef *").remove();
        $("#recipe *").remove();

        const imgtag = ' <div class="recipe_icon"><img src="<%=request.getContextPath()%>/img/mainImg/recipe_icon.png" width="400px"></div>';
        $("#recommend_recipe").append(imgtag);

        $(data.bestThreeRecipe).each((i,v)=>{
          let val='';

          val +='<div class="recipeData">';
          val +='<a href="">';

          if(data.bestThreeRecipe[i].representPicture !== null) {
            val += '<img src="'+data.bestThreeRecipe[i].representPicture+'" width="250px" height="250px">';
          }else{
            val += '<img src="/img/icon/non_profile.png" width="250px" height="250px">';
          }
          val+='<br></a>'

          val+='<div><a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+data.bestThreeRecipe[i].recipeEnrollNum+'"><span class="recipeTitle">'+data.bestThreeRecipe[i].recipeTitle+'</span></a><br>';

          if(data.bestThreeRecipe[i].recipeIntro!== null){
            val +=  '<span class="recipeInfo">'+data.bestThreeRecipe[i].recipeIntro+'</span><br>';
          }else{
            val +=  '<span class="recipeInfo">소개가 없습니다.</span><br>';
          }

          val+='</div>';

          $("#recommend_recipe").append(val);
        });


        const $span = '<span style="font-size: 40px; font-weight: bolder; border-bottom: 1px #1f695b solid">셰프 TOP 5</span>';
        $("#topChef").append($span);

        $(data.bestFiveChef).each((i,v)=>{
            let val2='';

            val2 +='<div class="chefData">';
            val2 +='<a href="<%=request.getContextPath()%>/searchchef.do?chefsearch='+data.bestFiveChef[i].memberNickName+'">';

            if(data.bestFiveChef[i].representPicture !== null) {
              val2 += '<img src="<%=request.getContextPath()%>/upload/profile/'+data.bestFiveChef[i].representPicture+'" width="280px" height="280px">';
            }else{
              val2 += '<img src="<%=request.getContextPath()%>/img/icon/non_profile.png" width="280px" height="280px">';
            };

            val2 += '</a>';
            val2 += '<div>';
            val2 += '<a href="<%=request.getContextPath()%>/searchchef.do?chefsearch='+data.bestFiveChef[i].memberNickName+'"><span class="chefName">'+data.bestFiveChef[i].memberName+'</span></a><br>';
            val2 += '<a href="<%=request.getContextPath()%>/searchchef.do?chefsearch='+data.bestFiveChef[i].memberNickName+'"><span class="chefNick">'+data.bestFiveChef[i].memberNickName+'</span></a><br>';
            val2 += '<span>';
            val2 += '<button class="sendDM">DM 보내기</button><input type="hidden" value="'+data.bestFiveChef[i].memberId+'" class="chefId"></span></div></div>';

           $("#topChef").append(val2);
        });

        const $span2 = '<span style="font-size: 40px; font-weight: bolder; border-bottom: 1px #1f695b solid">최근 레시피</span>';
        $("#recipe").append($span2);
        $(data.threeRecipe).each((i,v)=>{
            let val3=''

            val3 += ' <div class="recipeData">';

            if(data.threeRecipe[i].representPicture !== null) {
              val3 += '<a href=""><img src="'+data.threeRecipe[i].representPicture +'"width="250px" height="250px"></a>';

            }else{
              val3 += '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+data.threeRecipe[i].recipeEnrollNum+'"><img src="<%=request.getContextPath()%>/img/icon/non_profile.png" width="250px" height="250px"></a>';

            }

            val3 +=  '<div>';
            val3 +=  '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+data.threeRecipe[i].recipeEnrollNum+'"><span class="recipeTitle">'+data.threeRecipe[i].recipeTitle+'</span></a><br>';
            if(data.threeRecipe[i].recipeIntro!== null){
                val3 +=  '<span class="recipeInfo">'+data.threeRecipe[i].recipeIntro+'</span><br>';
            }else{
              val3 +=  '<span class="recipeInfo">소개가 없습니다.</span><br>';
            }
            val3 +=  '</div>';
            val3 +=  '</div>';

          $("#recipe").append(val3);
        });

        $(function(){
          $(".sendDM").click((e)=>{

            let id = $(e.target).next().val();
            let option = "width=550,height=650,resizable=no"
            console.log(id);

            <%if(loginMember != null){%>
                let url="<%=request.getContextPath()%>/message?memberId=<%=loginMember.getUserId()%>&&targetId="+id;

                window.open(url,"_blank",option);
            <%}else{%>
                if(confirm('로그인이 필요합니다. 로그인 하시겠습니까?')){
                  trigger.click();
                }else{
                  return;
                }
            <%}%>
          });
        });

      },
      error: (e, m, i) => {
        console.log(e);
        console.log(m);
        console.log(i);
      }
    });
  }
  mustStart();

  <%if(loginMember != null ){%>
      <%if(loginMember.getUserId().equals("admin")){%>
        $("#managerPage").attr("display","inline-block");

        $("#managerPage").click(e=>{
        if(confirm("관리자 페이지로 이동하시겠습니까?")){
          location.assign("<%=request.getContextPath()%>/manager/main.do");
        }
        });
      <%}%>
  <%}%>




</script>
<body>
<div id="totalContainer">
    <div class="header">
        <div class="logo">
            <a href="<%=request.getContextPath()%>/index.jsp">
                <img src="<%=request.getContextPath()%>/img/icon/mainlogo.png"
                     height="150px">
            </a>
        </div>

        <div class="menu">
            <span>
                <a href="<%=request.getContextPath()%>/rankchef.do">셰프 랭킹</a>
            </span>
            <span>
                <a href="<%=request.getContextPath()%>/bestrecipe.do">추천 레시피</a>
            </span>
            <span>
                <a href="<%=request.getContextPath()%>/recipe/recipeList">레시피</a>
            </span>
        </div>

        <div class="controlIcon">
            <span id="managerPage">
                <button>관리자페이지</button>
            </span>
            <span>
                    <a href="">
                        <img src="<%=request.getContextPath()%>/img/icon/icon_search.png">
                    </a>
            </span>
            <span>
                <a href="">
                    <img src="<%=request.getContextPath()%>/img/icon/icon_cart.png">
                </a>
            </span>

            <%if(loginMember==null){ %>
                <span>
                    <a href="#none" class="LoginTriger">
                        <img src="<%=request.getContextPath()%>/img/icon/icon_login.png" alt="">
                    </a>
                </span>
            <%}else{ %>
                <a><img src="<%=request.getContextPath()%>/img/icon/icon_login.png" width="55px" height="55px"></a>
                <ul id="dropdown_ul2">
                    <li><a href="<%=request.getContextPath()%>/searchchef.do?chefsearch=<%=loginMember.getUserId()%>">프로필</a></li>
                    <li><a href="<%=request.getContextPath()%>/member/memberPwCheck?userId=<%=loginMember.getUserId()%>">회원정보수정</a></li>
                    <li><a href="#">나의레시피</a></li>
                    <li><a href="#">주문정보</a></li>
                    <li><a href="#">나의포인트</a></li>
                    <li><a href="<%=request.getContextPath()%>/notice/noticeList">고객센터</a></li>
                    <li><a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a></li>
                </ul>
            <%} %>
            <div class="popup">
                <div class="popup-content-container">
                    <span class="close-button">x</span>
                    <h1 class="loginfont">L O G I N</h1>
                    <form name="formLogin" action="<%=request.getContextPath()%>/member/login.do" method="POST" class="loginform">
                        <div class="input-container">
                            <input type="text" name="userId" id="login_id" class="input-default" placeholder="아이디" title="아이디 입력">
                        </div>
                        <div class="alert-container">
                    <span id="loginAlertId" class="alert-text hidden">
                        <span class="icon-close">
                            ::before
                            ::after
                        </span>
                        "아이디 또는 이메일 주소를 입력해주세요."
                    </span>
                        </div>
                        <div class="input-container">
                            <input type="password" name="password" id="login_password" class="input-default" placeholder="비밀번호" title="비밀번호 입력">
                        </div>
                        <div class="alert-container inline-block">
                            <span id="loginAlertPassword" class="alert-text hidden">...</span>
                        </div>
                        <div class="SearchIdPwSpan">
                    <span>
                        <a href="">아이디 찾기</a>
                        <span>/</span>
                        <a href="">비밀번호 찾기</a>
                    </span>
                        </div>
                        <div class="IdCheck">
	                <span>
	                    <input type="checkbox">
	                    <label for="">아이디 저장</label>
	                </span>
                        </div>
                        <div class="loginbutton">
                            <button>L O G I N</button>
                        </div>
                    </form>
                    <div>
                        <div style="margin-top: 40px;">
                            <hr>
                        </div>
                        <div class="kakaoface">
                            <div class="kakaologinbtn">
                                <a id="kakao-login-btn"></a>
                                <a href="http://developers.kakao.com/logout"></a>
                            </div>
                            <div class="googlelogin">
                                <div class="g-signin2" data-onsuccess="onSignIn" style="width:225px; height:50px;" ></div>
                            </div>
                        </div>
                        <div class="join">
                            <a href="<%=request.getContextPath()%>/member/memberEnroll">회원가입</a>
                        </div>
                        <form action="<%=request.getContextPath() %>/member/memberKakaoLogin" method="post" id="kakaologinform" name="kakaologinform">
		             		<input type="hidden"  id="kakao_id" name="kakao_id">
		             		<input type="hidden"  id="kakao_email" name="kakao_email">
		             		<input type="hidden"  id="kakao_name" name="kakao_name">
		             	</form>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script>
      // 로그인 로직 구현

      const Login_Popup=()=>{
        const url ="<%=request.getContextPath()%>/loginpopup";
        const status= "left=1000px , top= 500px, width=800px, height=500px"

        open(url,"_blank",status);
      }

      var model = document.querySelector(".popup");
      var trigger = document.querySelector(".LoginTriger");
      var closeButton = document.querySelector(".close-button");
      // console.log(model);
      function toggleModel(){
        model.classList.toggle("show-model");
      }
      function windowOnClick(event){
        if(event.target === model) {
          toggleModel();
        }
      }

      trigger.addEventListener("click",toggleModel);
      closeButton.addEventListener("click", toggleModel);
      window.addEventListener("click", windowOnClick);



      // 카카오 로그인
     	Kakao.init('9821b1adf6591b5708f7ee0615e8458b');
      Kakao.Auth.createLoginButton({
          container: '#kakao-login-btn',
          success: function (authObj) {
          	Kakao.API.request({
              	url: '/v2/user/me',
              	success: function(res) {
                  	var userId = res.id;
                  	var userEmail = res.kakao_account.email;
                  	var userName = res.properties.nickname;
                  	
                  	console.log(userId);
                  	console.log(userEmail);
                  	console.log(userName);
                  	console.log(res.kakao_account);
                  	
			          	kakaologinform.kakao_id.value=userId;
			           	kakaologinform.kakao_email.value=userEmail;
			           	kakaologinform.kakao_name.value=userName
			           	kakaologinform.submit();
                   },
                   fail: function(error) {
                     alert(JSON.stringify(error));
                   }
                 });
	           	
          	 if(!Kakao.Auth.getAccessToken()){
           		console.log("토큰없음");
           		return;
           	}else{
           		console.log("토큰있음");
           		console.log(Kakao.Auth.getAccessToken())
           		return;
           	}
          },
          fail: function (err) {
              alert(JSON.stringify(err));
          }
      });
      //카카오 로그아웃
      logoutWithKakao=()=>{
      	if(Kakao.Auth.getAccessToken()){
      		console.log('카카오 인증 액세스 토큰이 존재합니다',Kakao.Auth.getAccessToken())
      		Kakao.Auth.logout(()=>{
      			consol.log('로그아웃 되었습니다',Kakao.Auth.getAccessToken());
      			this.setState({
      				isLogin:false
      			})
      		});
      	}
      }
      //구글 테스트
      function init() {
        gapi.load('auth2', function() {
          gapi.auth2.init();
          options = new gapi.auth2.SigninOptionsBuilder();
          options.setPrompt('select_account');
          // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
          options.setScope('email profile openid https://www.googleapis.com/auth/user.birthday.read');
          // 인스턴스의 함수 호출 - element에 로그인 기능 추가
          // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
          gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
        })
      }

      function onSignIn(googleUser) {
        var access_token = googleUser.getAuthResponse().access_token
        $.ajax({
          // people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
          url: 'https://people.googleapis.com/v1/people/me'
          // key에 자신의 API 키를 넣습니다.
          , data: {personFields:'birthdays', key:'AIzaSyByMI_fY8EQyTZkJdCdRp8QlT6NDWXFr6g', 'access_token': access_token}
          , method:'GET'
        })
          .done(function(e){
            //프로필을 가져온다.
            var profile = googleUser.getBasicProfile();
            console.log(profile)
          })
          .fail(function(e){
            console.log(e);
          })
      }
      function onSignInFailure(t){
        console.log(t);
      }


      //웹소켓서버에 연결하기
      //WebSocket객체를 생성함
      const socket=new WebSocket("ws://localhost:9090/<%=request.getContextPath()%>/chatting");
      //http프로토콜로 통신하는 주소 ws:주소
      //https프로토콜이용 : wss:주소

      //socket설정
      //접속후 실행되는 이벤트 핸들러 등록
      socket.onopen=e=>{
        console.log("웹소켓서버에 접속 성공!");
        console.log(e);
      }
      //웹소켓서버에서 sendText, sendObject매소드를 실행하면 실행되는 함수
      socket.onmessage=e=>{
        console.log("메세지수신");
        //수신된데이터를 받으려면 이벤트객체의 data속성을 이용함
        console.log(e);
        console.log(e.data);
        //Object형태의 String 데이터를 객체로 변환해주기

        console.log(JSON.parse(e.data));
        const msg=JSON.parse(e.data);
        //let msg=e.data.split(",");
        //0 : 보낸사람
        //1 : 메세지
        if(msg["sender"]==$("#sender").val()){
          //$("#msgContainer").append($("<p>").text("<"+msg[0]+"> "+msg[2]).css("text-align","left"));
          $("#msgContainer").append($("<p>").text("<"+msg["sender"]+"> "+msg["msg"]).css("text-align","left"));
        }else{
          //$("#msgContainer").append($("<p>").text("<"+msg[0]+"> "+msg[2]).css("text-align","right"));
          $("#msgContainer").append($("<p>").text("<"+msg["sender"]+"> "+msg["msg"]).css("text-align","right"));
        }
        //메세지처리에 대한 로직을 여기에 구현을 함.

      }

      const sendMsg=()=>{
        //웹소켓서버에 메세지를 전송하는 함수
        //전송할 메세지 전처리
        //전처리한 메세지를 전송하는방법 : socket.send("데이터전송");
        //socket.send($("#sender").val()+","+$("#receiver").val()+","+$("#msg").val());
        msg=new Message($("#sender").val(),$("#receiver").val(),$("#msg").val());
        //javascript 객체를 스트링으로 변환해서 전송
        //JSON.stringify(object)함수를 이용
        socket.send(JSON.stringify(msg));
      }

      function Message(sender,reciver,msg){
        this.sender=sender;
        this.reciver=reciver;
        this.msg=msg;
      }


    </script>
    <%--        header 끝 section 시작--%>
