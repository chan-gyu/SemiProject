<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yoriessence.notice.model.vo.Notice" %>
<%
	Notice n=(Notice)request.getAttribute("notice");
%>
<%@ include file="/view/common/header.jsp"%>
<div id="notice-container">
	<h2>공지사항</h2>
        <table id="tbl-notice">
        <thead>
	        <tr class="tr_title">
	            <th><%=n.getTitle() %></th>
	        </tr>
        </thead>
   		<%if(n.getFilePath()!=null) { %>
        <tr class="tr_file">
            <td><a href="<%=request.getContextPath()%>/notice/fileDownload?fname=<%=n.getFilePath()%>">첨부파일 : <img src="<%=request.getContextPath()%>/img/icon/icon_file.png" width="30" height="30"><%=n.getFilePath()%></a></td>
        </tr>
   		<%} %>
   		<tbody>
	        <tr class="tr_content">
	            <td><%=n.getContent()%></td>
	        </tr>
   		</tbody>
        <%if(loginMember!=null&&loginMember.getUserId().equals("ADMIN")){%>
        <tr style="height:100px;">
            <td colspan="2" style="text-align:center;">
                <input type="button" value="수정하기" onclick="location.assign('<%=request.getContextPath()%>/notice/noticeUpdate?no=<%=n.getNumber()%>')">
                <input type="button" value="삭제하기" onclick="location.assign('<%=request.getContextPath()%>/notice/noticeDelete?no=<%=n.getNumber()%>')">
            </td>
        </tr>
        <%} %>
    </table>
    </div>

<style>
    section#notice-container{width:900px; height:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:900px; height:600px; margin:0 auto; margin-top:100px; border-collapse:collapse; clear:both; }
   	table#tbl-notice thead th{height:50px; padding: 5px 0; text-align:left; border-bottom:3px solid #8CC7BC;color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;}
    table#tbl-notice tbody td {height:100px; padding: 5px 0; text-align:left; vertical-align:middle; border-bottom:1px solid #1F695B;}
    #notice-container>h2{
	    text-align: center;
	    font-size: 35px;
	    margin-top:50px;
	}
	.tr_content{
		height: 400px;
	}
	.tr_file{
		height:50px;
	}
</style>	


<%@ include file="/view/common/footer.jsp"%>