<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yoriessence.helper.model.vo.Helper" %>
<%
	Helper h=(Helper)request.getAttribute("helper");
%>
<%@ include file="/view/common/header.jsp"%>
<div id="helper-container">
	<h2>도움말</h2>
        <table id="tbl-helper">
        <thead>
	        <tr class="tr_title">
	            <th><%=h.getTitle() %></th>
	        </tr>
        </thead>
        <tbody>
        <tr class="tr_content">
            <td><%=h.getContent() %></td>
        </tr>
        </tbody>
        <%if(loginMember!=null&&loginMember.getUserId().equals("ADMIN")){%>
        <tr style="height:100px;">
            <th colspan="2" style="text-align:center;">
                <input type="button" value="수정하기" onclick="location.assign('<%=request.getContextPath()%>/helper/helperUpdate?no=<%=h.getNumber()%>')">
                <input type="button" value="삭제하기" onclick="location.assign('<%=request.getContextPath()%>/helper/helperDelete?no=<%=h.getNumber()%>')">
            </th>
        </tr>
        <%} %>
    </table>
    </div>

<style>
    section#helper-container{width:900px; height:600px; margin:0 auto; text-align:center;}
    section#helper-container h2{margin:10px 0;}
    table#tbl-helper{width:900px; height:600px; margin:0 auto; margin-top:100px; border-collapse:collapse; clear:both; }
    table#tbl-helper thead th {height:50px; padding: 5px 0; text-align:left; border-bottom:3px solid #8CC7BC;color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;} 
    table#tbl-helper tbody td {height:100px; padding: 5px 0; text-align:left; vertical-align:middle; border-bottom:1px solid #1F695B;}
    #helper-container>h2{
	    text-align: center;
	    font-size: 35px;
	    margin-top:50px;
	}

	.tr_file{
		height:50px;
	}
</style>	


<%@ include file="/view/common/footer.jsp"%>