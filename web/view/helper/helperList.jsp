<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yoriessence.helper.model.vo.Helper" %>
<%@ include file="/view/common/header.jsp"%>
<%
	List<Helper> helper=(List<Helper>)request.getAttribute("helper");
%>
<style>
	div#helper-container{width:900px; margin:0 auto; text-align:center; font-size:20px;}
	table#tbl-notice{width:900px; margin-left:10px; border-collapse:collapse; clear:both; line-height:1.5; font-size:20px;}
	table#tbl-notice thead th {height:50px; padding: 5px 0; text-align:center; border-bottom:3px solid #8CC7BC;color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;}
	table#tbl-notice tbody th {height:100px; padding: 5px 0; text-align:center; border-bottom:1px solid #1F695B; color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;}
	 table#tbl-notice td {height:100px; padding: 5px 0; text-align:center; vertical-align:middle; border-bottom:1px solid #1F695B;}
	input#btn-add{float:right;margin:0 0 15px;}
	.container{
    border: 1px red solid;
    margin-left:100px;
    width:1700px;
    height:1000px;
	}
	#container>h2{
	    text-align: center;
	    font-size: 35px;
	    margin-top:50px;
	}
	.sub_container>div{
	    float: left;
	}
	.sub_container{
	    margin-top: 70px;
	    margin-left: 150px;
	}
	.menu{
		width:100%;
		maring: 0px auto;
	}
	.menu>li{
	    border: 1px black solid;
	    width: 200px;
	    height: 50px;
		text-align:center;
		padding-top:25px;
	}
</style>
<div id="container">
	<h2>도움말</h2>
	<div class="sub_container">
            <div style="width:250px; height:250px;">
                <ul class="menu">
                    <li><a href="<%=request.getContextPath()%>/notice/noticeList">공지사항</a></li>
                    <li style="background-color:#8CC7BC;"><a href="<%=request.getContextPath()%>/helper/helperList">도움말</a></li>
                    <li><a href="<%=request.getContextPath()%>/question/questionList">1:1문의</a></li>
                    <%if(loginMember!=null&&loginMember.getUserId().equals("ADMIN")){%>
                    <li><a href="<%=request.getContextPath()%>/helper/helperForm">글쓰기</a></li>
                    <%} %>
                </ul>
            </div>
            <div id="helper-container">
                <table id="tbl-notice">
	                <thead>
	                    <tr>
	                        <th>번호</th>
	                        <th>제목</th>
	                    </tr>
	                </thead>
                    <tbody>
                    	<%if(helper.isEmpty()) {%>
                            <tr>
                                <td clospan="5">조회된 공지사항이 없습니다.</td>
                            </tr>
                        <%}else{ %>
                        	<% for(Helper h : helper){ %>
                                <tr>
                                    <th><%=h.getNumber() %></th> <!-- 번호 -->
                                    <td><a href="<%=request.getContextPath()%>/helper/helperView?helperNo=<%=h.getNumber()%>"><%=h.getTitle()%></a></td>
                                </tr> 
                           	<%}
                    	}%>
                    </tbody>
                    <!-- 내용출력할것
                    첨부파일 있으면 이미지, 없으면 공란으로 표시
                    이미지파일은 web/images/file.png에 저장 -->
                </table>
				<div id="pageBar">
					<%=request.getAttribute("pageBar") %>
				</div>
            </div>
        </div>
</div>
<%@ include file="/view/common/footer.jsp"%>