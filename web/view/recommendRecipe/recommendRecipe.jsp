<%@ page import="com.yoriessence.recipe.model.vo.Recipe" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<%
    List<Recipe> todayRecipe = (List<Recipe>)request.getAttribute("todayRecipe");
    List<Recipe> periodRecipe=(List<Recipe>)request.getAttribute("periodRecipe");
    List<Integer> countRecipeLike = (List<Integer>)request.getAttribute("countRecipeLike");
    List<Integer> countRecipeComment = (List<Integer>)request.getAttribute("countRecipeComment");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/recommendRecipe/css/recommend.css">
<script>
let sortday1 = 2;
let sortday2 = 1;

  $(function(){
    $("#yesterday").click((e)=>{

      $.ajax({
        url:"<%=request.getContextPath()%>/sortday.do",
        data:{
          "sortday1":sortday1+1,
          "sortday2":sortday2+1
        },
        dataType:'json',
        success:data=>{
          if(sortday1 === 3 && sortday2 === 2){
            $("#tomorrow").css("display","inline-block");
            $(".recommend_recipe_title span").text("어제의 추천메뉴");
          }else if(sortday1 !== 2 && sortday2 !== 1){
            $("#tomorrow").css("display","inline-block");
            $(".recommend_recipe_title span").text(sortday2-1+"일 전 추천메뉴");
          }

          let val ='';
          $("#today_recommend_recipe_list *").remove();

          if(data[0] === undefined){
            val+='<div class="today_recommend_recipe"><div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div></div>';
            val+='<div class="today_recommend_recipe"><div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div></div>';
            val+='<div class="today_recommend_recipe"><div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div></div>';
            $("#today_recommend_recipe_list").append(val);

          }else {
            $(data).each((i, v) => {

              val = '<div class="today_recommend_recipe">';
              if(v.representPicture === undefined){
                val += '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNo+'"><img id="today_recipe_img" src="<%=request.getContextPath()%>/img/recipe/no_image.png" height="200px" width="200px"><br>';
              }else{
                val += '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNo+'"><img id="today_recipe_img" src="<%=request.getContextPath()%>/upload/recipe/'+v.representPicture+'" height="200px" width="200px"><br>';
              }
              val += '<div class="today_recipe_info">';
              val += '<a id="today_recipe_title" href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNo+'"><h2>' + v.recipeTitle + '</h2></a><br>';
              val += '<span id="today_recipe_memberId">' + v.memberId + '</span><br>';
              val += '<span id="today_recipe_viewCount">좋아요' + v.recommendCount + '</span>';
              val += '<span id="today_recipe_viewCount">조회수' + v.recipeViewCount + '</span>';
              val += '</div>';
              val += '</div>';

              $("#today_recommend_recipe_list").append(val);
            });

          }

        },
        error:(e,s,m)=>{
          console.log(e);
          console.log(s);
        }

      });

      sortday2 = sortday2+1;
      sortday1 = sortday1+1;
    });


    $("#tomorrow").click((e)=>{
      // console.log(sortday1 +': sortday1' , sortday2 +': sortday2' +'내일버튼');

      $.ajax({
        url:"<%=request.getContextPath()%>/sortday.do",
        data:{
          "sortday1":sortday1-1,
          "sortday2":sortday2-1
        },
        dataType:'json',
        success:data=>{
          if(sortday1 === 2 && sortday2 === 1){
            $("#tomorrow").css("display","none");
            $(".recommend_recipe_title span").text("오늘의 추천메뉴");
          }else if(sortday1 === 3 && sortday2 === 2){
            $("#tomorrow").css("display","inline-block");
            $(".recommend_recipe_title span").text("어제의 추천메뉴");
          }else{
            $("#tomorrow").css("display","inline-block");
            $(".recommend_recipe_title span").text(sortday2-1+"일 전 추천메뉴");
          }

            let val ='';
            $("#today_recommend_recipe_list *").remove();
          if(data[0] === undefined){
            val+='<div class="today_recommend_recipe"><div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div></div>';
            val+='<div class="today_recommend_recipe"><div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div></div>';
            val+='<div class="today_recommend_recipe"><div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div></div>';
            $("#today_recommend_recipe_list").append(val);

          }else {
            $(data).each((i, v) => {
              val = '<div class="today_recommend_recipe">';
              if(v.representPicture === undefined){
                val += '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNo+'"><img id="today_recipe_img" src="<%=request.getContextPath()%>/img/recipe/no_image.png" height="200px" width="200px"><br>';

              }else{
                val += '<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNo+'"><img id="today_recipe_img" src="<%=request.getContextPath()%>/upload/recipe/'+v.representPicture+'" height="200px" width="200px"><br>';

              }
              val += '<div class="today_recipe_info">';
              val += '<a id="today_recipe_title" href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.recipeEnrollNo+'"><h2>' + v.recipeTitle + '</h2></a><br>';
              val += '<span id="today_recipe_memberId">' + v.memberId + '</span><br>';
              val += '<span id="today_recipe_viewCount">좋아요' + v.recommendCount + '</span>';
              val += '<span id="today_recipe_viewCount">조회수' + v.recipeViewCount + '</span>';
              val += '</div>';
              val += '</div>';
              $("#today_recommend_recipe_list").append(val);
            });
          }
        },
        error:(e,s,m)=>{
          console.log(e);
          console.log(s);
        }

      });

      sortday2 = sortday2-1;
      sortday1 = sortday1-1;
    });

    sortRecipe();

  });

  function sortRecipe(cPage){
    $("#best_recipe_sort a").click((e)=>{
      const val = $(e.target).text();

      $.ajax({
        url:'<%=request.getContextPath()%>/sortrecommend.do',
        data:{
          "sortVal":val,
          "cPage":cPage,
        },
        dataType:'json',

        success:(data)=>{
          $("#recipe_list *").remove();

          let val ='';
          const pageBarJson = data.pageBarJson;
          $(data).each((i,v)=>{

            console.log(v);

            for(let i =0; i<v.periodRecipeJson.length; i++){
              val += '<div class="recipe">';
              if(v.periodRecipeJson[i].representPicture === undefined){
                val +='<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.periodRecipeJson[i].recipeEnrollNo+'"><img src="<%=request.getContextPath()%>/img/recipe/no_image.png" height="200px" width="200px"></a>';

              }else{
                val +='<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.periodRecipeJson[i].recipeEnrollNo+'"><img src="<%=request.getContextPath()%>/upload/recipe/'+v.periodRecipeJson[i].representPicture+'" height="200px" width="200px"></a>';
              }
              val +='<div class="recipe_info">';
              val +='<a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo='+v.periodRecipeJson[i].recipeEnrollNo+'"><span>'+decodeURI(v.periodRecipeJson[i].recipeTitle)+'</span></a><br>';
              val +='<span>'+decodeURI(v.periodRecipeJson[i].memberId)+'</span><br>';

              if(v.periodRecipeJson[i].recipeRecommend === undefined){
                val +='<span> 좋아요 0 </span>';
              }else{
                val +='<span> 좋아요 '+v.periodRecipeJson[i].recipeRecommend+' </span>';
              }

              if(v.countRecipeCommentJson[i] === undefined){
                val +='<span> 댓글 0 </span>';
              }else{
                val +='<span> 댓글 '+v.countRecipeCommentJson[i]+' </span>';
              }

              val +='<span> 조회수 '+v.periodRecipeJson[i].recipeViewCount+'</span>';
              val +='</div>';
              val +='</div>';
            }
            $("#recipe_list").append(val);
          });
          $("#pageBar *").remove();
          $("#pageBar").append(pageBarJson);
        },
        error:(e,s,m)=>{
          console.log(e);
          console.log(s);
          console.log(m);
        }
      });
    });
  }
</script>
<section>
    <div id="prev_recipe_button">
        <div class="prev_button">
            <button id="yesterday" style="border-radius: 10px; width: 50px; height: 35px; cursor: pointer">
                <
            </button>
            <span class="tooltip">
                    <p>어제 추천 메뉴를 보려면 클릭!</p>
            </span>
        </div>
        <div class="recommend_recipe_title">
            <span>오늘의 추천 메뉴</span>
        </div>
        <div class="next_button">
            <button id="tomorrow" style="border-radius: 10px; width: 50px; height: 35px; cursor: pointer">
                >
            </button>
            <span class="tooltip">
                    <p>내일 추천 메뉴를 보려면 클릭!</p>
            </span>
        </div>

    </div>
    <div id="today_recommend_recipe_list">
    <%if(todayRecipe.size()>0){%>
        <%for(int i = 0; i<todayRecipe.size(); i++){%>
            <div class="today_recommend_recipe">
                <a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=todayRecipe.get(i).getRecipeEnrollNo()%>"><img id="today_recipe_img" src="<%=request.getContextPath()%>/upload/recipe/<%=todayRecipe.get(i).getRepresentPicture()%>" height="200px" width="200px"></a><br>
                <div class="today_recipe_info">
                    <a id="today_recipe_title" href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=todayRecipe.get(i).getRecipeEnrollNo()%>"><h2><%=todayRecipe.get(i).getRecipeTitle()%></h2></a>
                    <span id="today_recipe_memberId"><%=todayRecipe.get(i).getMemberId()%></span><br>
                    <span id="today_recipe_viewCount">조회수 <%=todayRecipe.get(i).getRecipeViewCount()%></span>
                </div>
            </div>
        <%}%>
    <%}else{%>
        <div class="today_recommend_recipe">
            <div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div>
        </div>
        <div class="today_recommend_recipe">
            <div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div>
        </div>
        <div class="today_recommend_recipe">
            <div><img src="<%=request.getContextPath()%>/img/icon/prepared_recipe.png"></div>
        </div>
    <%}%>
    </div>
    <div id="best_recipe_title_container" class="title_container_grid">
        <div id="best_recipe_title">
            <span> 베스트 레시피 </span>
        </div>
        <div id="best_recipe_sort">
            <a>주간</a>
            |
            <a>월간</a>
            |
            <a>전체</a>
        </div>
    </div>
    <div id="recipe_list" class="grid">
        <%if(periodRecipe.size() > 0){%>
            <%for(int i=0; i<periodRecipe.size(); i++){%>
                <div class="recipe">
                    <%if(periodRecipe.get(i).getRepresentPicture() != null){%>
                        <a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=periodRecipe.get(i).getRecipeEnrollNo()%>"><img src="<%=request.getContextPath()%>/upload/recipe/<%=periodRecipe.get(i).getRepresentPicture()%>" height="200px" width="200px"></a>
                    <%}else{%>
                        <a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=periodRecipe.get(i).getRecipeEnrollNo()%>"><img src="<%=request.getContextPath()%>/img/recipe/no_image.png" height="200px" width="200px"></a>
                    <%}%>
                    <div class="recipe_info">
                        <a href="<%=request.getContextPath()%>/recipe/recipeView?recipeEnrollNo=<%=periodRecipe.get(i).getRecipeEnrollNo()%>"><span><%=periodRecipe.get(i).getRecipeTitle()%></span></a><br>
                        <span><%=periodRecipe.get(i).getMemberId()%></span><br>

                        <%try{%>
                            <span>좋아요 <%=countRecipeLike.get(i)%> </span>
                        <%}catch (IndexOutOfBoundsException e){%>
                            <span> 0</span>
                        <%}%>

                        <%try{%>
                            <span> 댓글 <%=countRecipeComment.get(i)%> </span>
                        <%}catch (IndexOutOfBoundsException e){%>
                            <span> 0</span>
                        <%}%>
                        <span> 조회수 <%=periodRecipe.get(i).getRecipeViewCount()%></span>
                    </div>
                </div>
            <%}%>
        <%}%>
    </div>
    <div id="pageBar">
        <%=request.getAttribute("pageBar")%>
    </div>
</section>
<%@ include file="/view/common/footer.jsp"%>
