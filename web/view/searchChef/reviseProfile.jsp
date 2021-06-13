<%@ page import="com.yoriessence.chef.model.vo.Profile" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 2021/05/20
  Time: 10:07 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/searchChef/css/reviseProfile.css" />
<%
    List<Profile> chefProfile = (List<Profile>)request.getAttribute("chefProfile");
%>
<section>
    <form id="form" method="post" action="<%=request.getContextPath()%>/reviseProfile">
        <div id="reviseContainer">
            <div>제목</div>
            <div>
                <input type="hidden" name="memberId" value="<%=chefProfile.get(0).getMemberId()%>">
                <input type="text" name="title" value="<%=chefProfile.get(0).getProfileName()%>" placeholder="프로필 제목을 입력해주세요">
            </div>
            <div class="picContainer">
                <%if(chefProfile.get(0).getProfilePic() != null){%>
                            <%--            프로필 사진이 있다면 사진 출력, 없으면 기본사진--%>
                    <img src="<%=request.getContextPath()%>/upload/profile/<%=chefProfile.get(0).getProfilePic()%>"
                         width="300px" height="300px" style="border-radius: 200px; border: #0A1329 1px solid">

                <%}else{%>
                    <img src="<%=request.getContextPath()%>/img/icon/non_profile.png" width="250px" height="250px">
                <%}%>
                <br>
                <p id="picRevise">
                    <span style="font-size: 17px">프로필 수정하기</span>
                    <br><input type="file" id="upfile" name="pic">
                    <br>
                    <span style="font-size:12px">3MB 이하의 파일만 가능합니다.</span>
                </p>
            </div>
            <div>자기소개</div>
            <div>
                <textarea name="intro" cols="50" rows="10"><%=chefProfile.get(0).getSelfIntro()%></textarea>
            </div>
            <div>SNS 주소 1</div>
            <div>
                <input type="text" name="sns1" value="<%=chefProfile.get(0).getProfileSnsUrl1()%>" placeholder="sns 주소를 입력해주세요">
            </div>
            <div>SNS 주소 2</div>
            <div>
                <input type="text" name="sns2" value="<%=chefProfile.get(0).getProfileSnsUrl2()%>" placeholder="sns 주소를 입력해주세요">
            </div>
            <div>
                <input type="button" id="enroll" value="수정">
                <input type="button" id="cancel" value="취소">
            </div>
        </div>
    </form>
</section>
<script>

    $("#enroll").click(e=>{
      if(confirm("프로필을 수정하시겠습니까?")){
        const formData = new FormData();
        formData.append("memberId",$("[name=memberId]").val());
        formData.append("title",$("[name=title]").val());
        formData.append("intro",$("[name=intro]").val());
        formData.append("sns1",$("[name=sns1]").val());
        formData.append("sns2",$("[name=sns2]").val());
        formData.append("pic",$("#upfile")[0].files[0]);
        // 파일은 배열 형식으로 가져와짐

        $.ajax({
          url:'<%=request.getContextPath()%>/reviseProfile',
          data:formData,
          type:"post",
          processData:false,
          contentType:false,

          success:data=>{
            alert("수정되었습니다.");
            location.replace("<%=request.getContextPath()%>/searchchef.do?chefsearch=<%=chefProfile.get(0).getMemberNickName()%>")
          }

        });

      }else{
        return;
      }
    });

    $("#cancel").click(e=>{
      if(confirm("프로필 수정을 취소하시겠습니까?")){
        location.replace("<%=request.getContextPath()%>/searchchef.do?chefsearch=<%=chefProfile.get(0).getMemberNickName()%>");
      }else{
        return false;
      }
    });

    function checkSNSURL(){
      if($("[name=sns1]").val() === "null"){
        $("[name=sns1]").val("");
      }

      if($("[name=sns2]").val() ==="null"){
        $("[name=sns2]").val("");
      }
    }

    checkSNSURL();
</script>
<%@ include file="/view/common/footer.jsp"%>
