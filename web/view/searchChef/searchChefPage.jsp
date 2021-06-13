<%@ page import="java.util.List" %>
<%@ page import="com.yoriessence.chef.model.vo.Profile" %>
<%@ page import="com.yoriessence.recipe.model.vo.Recipe" %>
<%@ page import="com.yoriessence.chef.model.vo.User" %>
<%@ page import="com.yoriessence.chef.model.vo.RecipeComment" %>
<%@ page import="com.yoriessence.chef.model.vo.RecipeRecommend" %><%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 2021/05/08
  Time: 12:17 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/searchChef/css/searchChefPage.css">
<%
    List<Profile> chefProfile = (List<Profile>)request.getAttribute("chefProfile");
    List<Recipe> chefRecipe = (List<Recipe>)request.getAttribute("recipe");
    List<RecipeComment> countComment = (List<RecipeComment>)request.getAttribute("countComment");
    List<RecipeRecommend> recipeRecommend = (List<RecipeRecommend>)request.getAttribute("recipeRecommends");

    User userInfo = (User)request.getAttribute("userInfo");
%>
<section>
    <div id="chef_Profile">
        <div class="chefContainer">
            <%if(chefProfile != null){%>
                <span id="picContainer">

                    <%if(chefProfile.get(0).getProfilePic() != null){%>
                        <img src="<%=request.getContextPath()%>/upload/profile/<%=chefProfile.get(0).getProfilePic()%>" height="200px" width="200px" style="border-radius: 200px">
                    <%}else{%>
                        <img src="<%=request.getContextPath()%>/img/icon/non_profile.png" height="200px" width="200px" style="border-radius: 200px">
                    <%}%>

                    <br><input type="button" id="sendDM" value="DM 보내기">
                </span>
                <img class="recommendStar" src="" width="35px" height="35px">
                <span id="chef_content">
                    <img src="<%=request.getContextPath()%>/img/icon/chef_cefti.png" width="50px" height="50px">
                    <span id="chefTitle"><%=chefProfile.get(0).getProfileName()%></span>
                    <span><img src="<%=request.getContextPath()%>/img/icon/icon_setting.png" width="40px" height="40px"></span>
                    <br><br><br>
                    <span class="text">
                        <p><a><%=chefProfile.get(0).getSelfIntro()%></a></p>
                        <br>

                        <%if(chefProfile.get(0).getProfileSnsUrl1() == null){%>
                            <span><a href=""><img class="snsIcon1" src=""></a></span>
                        <%}else{%>
                            <span><a href="https://<%=chefProfile.get(0).getProfileSnsUrl1()%>"><img class="snsIcon1" src=""></a></span>
                        <%}%>

                        <%if(chefProfile.get(0).getProfileSnsUrl2() == null){%>
                            <span><a href=""><img class="snsIcon2" src=""></a></span>
                        <%}else{%>
                            <span><a href="https://<%=chefProfile.get(0).getProfileSnsUrl2()%>"><img class="snsIcon2" src=""></a></span>
                        <%}%>
                    </span>
                </span>
                <script>
                    $("#sendDM").click(e=>{
                      <%if(loginMember !=null){%>
                          let url='<%=request.getContextPath()%>/messagelist?memberId=<%=loginMember.getUserId()%>&targetId=<%=chefProfile.get(0).getMemberId()%>';
                          let option ="width=520,height=770"
                          window.open(url,'_self',option);

                      <%}else{%>
                            if(confirm("로그인이 필요합니다. 로그인하시겠습니까?")){
                            trigger.click();
                            }
                      <%}%>
                    });

                </script>
            <%}%>
        </div>
    </div>
    <nav id="sort">
        <a>최신순</a>
        |
        <a>조회순</a>
    </nav>
    <div id="recipe_list" class="grid">

    <% if(chefRecipe != null){%>
        <% for(int i=0; i<chefRecipe.size(); i++){%>
            <div class="recipe">
                <%if(chefRecipe.get(i).getRepresentPicture() != null){%>
                    <a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=chefRecipe.get(i).getRecipeEnrollNo()%>"><img src="<%=request.getContextPath()%>/upload/recipe/<%=chefRecipe.get(i).getRepresentPicture()%>" height="200px" width="200px"></a>
<%--                글 내용으로--%>
                <%}else{%>
                    <a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=chefRecipe.get(i).getRecipeEnrollNo()%>"><img src="<%=request.getContextPath()%>/img/recipe/no_image.png" height="200px" width="200px"></a>
<%--                글 내용으로--%>
                <%}%>
                <div class="recipe_info">
                    <p>
                        <a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=chefRecipe.get(i).getRecipeEnrollNo()%>">
                            <%=chefRecipe.get(i).getRecipeTitle()%>
                        </a>
                    </p>
<%--                    글 내용으로--%>
                    <p><a href=""><%=chefRecipe.get(i).getMemberId()%></a></p>
<%--                    프로필로--%>
                    <span>좋아요

<%--                      좋아요 로직   수정해야함--%>

                    <%try{%>
                        <%=recipeRecommend.get(i).getRecipeRecommendNum()%>
                    <%}catch (IndexOutOfBoundsException e){%>
                        0
                    <%}%>


                    </span>

                    <span> 댓글
                    <%try{%>
                        <%=countComment.get(i).getCountRecipeComment()%>
                    <%}catch (IndexOutOfBoundsException e){%>
                        0
                    <%}%>
                    </span>

                    <span>조회수 <%=chefRecipe.get(i).getRecipeViewCount()%></span>
                </div>
            </div>
        <%}%>
    <%}%>
    </div>
    <div id="pageBar">
        <%=request.getAttribute("pageBar")%>
    </div>
</section>
<script>
  'use strict;'
  function inputSNSIcon() {
    const path = "<%=request.getContextPath()%>/img/icon/icon_";
    const snsIcon1 = $(".snsIcon1");
    const snsIcon2 = $(".snsIcon2");


    if(snsIcon1.parent().attr("href").includes("instagram")){
      snsIcon1.attr("src",path+"insta.png");
    }else if(snsIcon1.parent().attr("href").includes("youtube")){
      snsIcon1.attr("src",path+"youtube.png");
    }else if(snsIcon1.parent().attr("href").includes("facebook")){
      snsIcon1.attr("src",path+"facebook.png");
    }else{
      snsIcon1.css("display","none");
    }

    if(snsIcon2.parent().attr("href").includes("instagram")){
      snsIcon2.attr("src",path+"insta.png");
    }else if(snsIcon2.parent().attr("href").includes("youtube")){
      snsIcon2.attr("src",path+"youtube.png");
    }else if(snsIcon2.parent().attr("href").includes("facebook")){
      snsIcon2.attr("src",path+"facebook.png");
    }else{
      snsIcon2.css("display","none");
    }
  };

  <%if(cookies!=null){%>
    $(".recommendStar").click((e)=>{
        <%if(loginMember == null){%>
            if(confirm("로그인이 필요합니다.로그인 하시겠습니까?")) {
              // 로그인 되어있는지 확인
              // 로그인 화면 실행
              trigger.click();
            }
        <%}else{%>
            <%for(Cookie c : cookies){%>
                  // 로그인을 했다면 쿠키확인

                 <%if(!c.getName().equals("checkRecommend"+chefProfile.get(0).getMemberId() )){%>
                    // 쿠키값이 없다면
                    $.ajax({
                      url:"<%=request.getContextPath()%>/recommendChef.do",
                      data:{
                        "chefId":"<%=chefProfile.get(0).getMemberId()%>",
                        "recommendYN":recommendChef()
                      },
                      success:data=>{
                        alert("추천 되었습니다.");
                      }
                    });
                <%}else{%>
                    // 쿠키값이 있다면
                    $.ajax({
                      url:"<%=request.getContextPath()%>/recommendChef.do",
                      data:{
                        "chefId":"<%=chefProfile.get(0).getMemberId()%>",
                        "recommendYN":cancelRecommend()
                      },
                      success:data=>{
                        alert("추천이 취소되었습니다.");
                      }
                    });
                <%}%>
            <%}%>
      <%}%>
    });
  <%}%>

  // 쿠키확인해서 추천이미지 변경
  function checkCookie(){
    <%if(cookies!=null){%>
        <%for(Cookie c : cookies){%>
            <%if(!c.getName().equals("checkRecommend"+chefProfile.get(0).getMemberId())){%>
                $(".recommendStar").attr("src","<%=request.getContextPath()%>/img/icon/recommend_unclick.png");
            <%}else{%>
                $(".recommendStar").attr("src","<%=request.getContextPath()%>/img/icon/recommend_click.png");
            <%}%>
        <%}%>
    <%}%>
  };
  checkCookie();

  function recommendChef(){
    if($(".recommendStar").attr("src").includes("un")){
      if(confirm("추천하시겠습니까?")){
        // 회원만 추천가능하도록 하자
        // 로그인 했는지 확인하는거 추가해야함
        $(".recommendStar").attr({
          src:"<%=request.getContextPath()%>/img/icon/recommend_click.png",
        });
        return 1;
        // 추천함
      }
    }
  };

  function cancelRecommend(){
    // 추천 안함
    if(confirm("추천을 취소하시겠습니까?")){
      $(".recommendStar").attr({
          src:"<%=request.getContextPath()%>/img/icon/recommend_unclick.png",
        });
      return 0;
    }
  };

  let sortVal = '';

  $("#sort>a").click((e)=>{

    sortVal=$(e.target).text();

    sortAjax(<%=request.getParameter("cPage")%>,"<%=userInfo.getMemberNickName()%>",sortVal);
  });

  function sortAjax(cPage,chefNick,sortVal){

    $.ajax({
      url: "<%=request.getContextPath()%>/searchchefajax.do",
      data:{
        "sortVal":sortVal,
        "cPage":cPage,
        "chefsearch":chefNick
      },
      success:data=>{
        $("#recipe_list *").remove();

        let val='';

        $(data.recipeInfo).each((i,v)=>{
          // console.log(v);
          if(v != null) {
            val = '<div class="recipe">';

            if (v.representPicture !== undefined) {
              val += '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNum+'"><img src="<%=request.getContextPath()%>/upload/recipe/'+v.representPicture+'" height="200px" width="200px"></a>';
            } else {
              val += '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNum+'"><img src="<%=request.getContextPath()%>/img/recipe/non_recipe.png" height="200px" width="200px"></a>';
            }

            val += '<div class="recipe_info">';
            val += '<p><a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNum+'">' + decodeURI(v.recipeTitle) + '</a></p>';
            val += '<p><a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNum+'">' + v.memberId + '</a></p>';

            for (let j in data.countRecommend) {
              if (v.recipeEnrollNum === j) {
                val += '<span>좋아요 ' + data.countRecommend[j] + ' </span>';
              }
            }
            val += '<span>좋아요 0 </span>';

            for(let j in data.countComment){
              if (v.recipeEnrollNum === j) {
                val += '<span>댓글 '+data.countComment[j]+' </span>';
              }
            }
            val += '<span>댓글 0 </span>';


            val+='<span>조회수 '+v.recipeViewCount+' </span>';
            val+='</div>';
            val+='</div>';

            $("#recipe_list").append(val);
          }
        });

        $("#pageBar *").remove();
        $("#pageBar").html(data.pageBar);
      },

      error:(e,m,i)=>{
        console.log(e);
        console.log(m);
        console.log(i);
      }
    });
  }

  function chefCertifiedImg(){
    if(<%= !(userInfo.getMemberGrade().equals("cf")) %>){
      $("#chef_content>img").css("display","none");
    }
  }

  $("#chefTitle + span>img").click((e)=>{
    <%if(loginMember != null
           && (loginMember.getUserId().equals(chefProfile.get(0).getMemberId())
                                 || loginMember.getUserId().equals("admin"))){%>
      // 로그인했고 작성자이거나 관리자이면
        location.replace('<%=request.getContextPath()%>/movereviseprofile?chefId=<%=chefProfile.get(0).getMemberNickName()%>');
    <%}else{%>
        alert("본인만 수정가능합니다");
    <%}%>
  });


  chefCertifiedImg();
  inputSNSIcon();
</script>
<%@ include file="/view/common/footer.jsp"%>
