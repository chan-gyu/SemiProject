<%@ page import="java.util.List" %>
<%@ page import="com.yoriessence.chef.model.vo.Profile" %><%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 2021/05/26
  Time: 12:32 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>메세지보내기</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/common/css/message.css">
</head>
<%
    String memberId = (String)request.getAttribute("memberId");
    String targetId = (String)request.getAttribute("targetId");
    List<Profile> profile = (List<Profile>)request.getAttribute("profile");
%>

<script>
  const memberId = '<%=memberId%>';
  const targetId = '<%=targetId%>';

    $.ajax({
      url: '<%=request.getContextPath()%>/getMessage.do',
      data: {
        "memberId": memberId,
        "targetId": targetId,
        //  테스트용 아이디, 타겟아이디
      },
      success: data => {
        $(data.message).each((i, v) => {
          let val = '';

          if (v.memberId === memberId) {
            val += '<div class="me"><span>' + v.sendMessage + '</span></div>';
          } else if (v.memberId === targetId) {
            val += '<div class="you"><span>' + v.sendMessage + '</span></div>';
          }

          $(".content").append(val);
        });

      },

      error: (e, m, i) => {
        console.log(e);
        console.log(m);
        console.log(i);
      }
    });


</script>
<body>
    <div id="messageContainer">
        <div id="messageTop">
            <div class="messageProfile">
                <a href="<%=request.getContextPath()%>/searchchef.do?chefsearch=<%=profile.get(0).getMemberNickName()%>">
                    <%if(profile.get(0).getProfilePic() != null ){%>
                        <img src="<%=request.getContextPath()%>/upload/profile/<%=profile.get(0).getProfilePic()%>>">
                    <%}else{%>
                        <img src="<%=request.getContextPath()%>/img/icon/non_profile.png">
                    <%}%>
                </a>
            </div>
            <div class="messageBtn">
                <button>목록</button>
            </div>
            <div class="messageId">
                <span><%=targetId%></span>
            </div>
        </div>
        <div id="messageContent">
            <div class="content">
            </div>
        </div>
        <div id="messageInput">
            <div>
                <input type="text" name="messageVal" placeholder="보낼 메세지를 입력해주세요">
                <button id="sendMessage">보내기</button>
            </div>
        </div>
    </div>
</body>
<script>
  $("#sendMessage").click(e=>{

    if($("[name=messageVal]").val().trim() ===''){
      alert("메세지를 입력해주세요");
      $(e.target).preventDefault();
      return;

    }else{
      $.ajax({
        url:'<%=request.getContextPath()%>/getMessage.do',
        data:{
          "messageVal":$("[name=messageVal]").val(),
          "memberId":memberId,
          "targetId":targetId,
          //  테스트용 아이디, 타겟아이디
        },
        success:data=>{
          $(".content *").remove();

          $(data.message).each((i,v)=>{
            let val='';

            console.log(v);

            if(v.memberId === memberId ){
              val+='<div class="me"><span>'+v.sendMessage+'</span></div>';
            }else if(v.memberId === targetId){
              val+='<div class="you"><span>'+v.sendMessage+'</span></div>';
            }

            $(".content").append(val);
          });

          $("[name=messageVal]").val('').focus();

        },

        error:(e,m,i)=>{
          console.log(e);
          console.log(m);
          console.log(i);
        }
      });
    }
  });

  $(".messageBtn button").click(e=>{
    let url='<%=request.getContextPath()%>/messagelist?memberId='+memberId+'&targetId='+targetId;
    let option ="width=520,height=770"
    window.open(url,'_self',option);
  });
</script>
</html>
