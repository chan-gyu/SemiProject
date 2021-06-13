<%@ page import="java.util.Map" %>
<%@ page import="com.yoriessence.chef.model.vo.Profile" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String memberId = (String)request.getAttribute("memberId");
    List<Profile> profile = (List<Profile>)request.getAttribute("profile");
    Map<String,String> mesList = (Map<String,String>)request.getAttribute("mesList");
%>
<head>
    <title>대화목록</title>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/common/css/messageList.css">
</head>
<body>
<div id="titleCon">
    <div>
        <span></span>
        <span>대화 목록</span>
        <span></span>
    </div>
</div>
<div id="container">
        <%if(mesList!= null){%>
            <%for(Map.Entry<String,String> e :mesList.entrySet()){%>
                <div class="userInfo">
                    <div>
                        <a href="<%=request.getContextPath()%>/getMessage.do?targetId=<%=e.getKey()%>&memberId=<%=memberId%>">
                            <%if(profile.get(0).getProfilePic()!=null){%>
                                <img src="<%=request.getContextPath()%>/upload/profile/<%=profile.get(0).getProfilePic()%>">
                            <%}else{%>
                                 <img src="<%=request.getContextPath()%>/img/icon/non_profile.png">
                            <%}%>
                        </a>
                    </div>
                    <div class="targetInfo">
                        <div>
                            <a href="<%=request.getContextPath()%>/getMessage.do?targetId=<%=e.getKey()%>&memberId=<%=memberId%>">
                                <%=e.getKey()%>
                            </a>
                        </div>
                        <div class="targetIntro">
                            <%=e.getValue()%>
                        </div>
                    </div>
                </div>
            <%}%>
        <%}else{%>
            <div id="nonContainer">
                <div class="nonMessage">
                    <div>아직 아무에게도 메세지를 보내지 않았습니다.</div>
                </div>
            </div>
        <%}%>
</div>
</body>
</html>
