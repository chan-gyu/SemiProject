<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<%@ page import="com.yoriessence.notice.model.vo.Notice" %>
<%
	Notice n=(Notice)request.getAttribute("notice");

%>

	 <div id="notice-container">
    <form action="<%=request.getContextPath()%>/notice/noticeUpdateEnd" method="post" enctype="multipart/form-data">
    	<input type="hidden" name="noticeNo" value="<%=n.getNumber()%>">
        <table id="tbl-notice">
        <tr Style="height:50px;">
            <th>제 목</th>
            <td><input type="text" name="noticeTitle" id="noticeTitle" value="<%=n.getTitle()%>" required></td>
        </tr>
        <tr Style="height:50px;">
            <th>첨부파일</th>
            <td style="position:relative;">
            <%if(n.getFilePath()!=null) { %>
            <input type="file" name="up_file">
            <input type="hidden" name="oriFile" value="<%=n.getFilePath()%>">
            <span id="fname"><%=n.getFilePath() %></span>
            <%} else{ %>
            <input type="file" name="up_file">
            <%} %>
           </td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea rows="5" cols="50" name="content" style="border:none; margin:0 auto;width:100%; height:100%;"><%=n.getContent() %></textarea></td>
        </tr>
        <tr Style="height:50px;">
            <th colspan="2">
                <input type="submit" value="등록하기" onclick="">
            </th>
        </tr>
    </table>
    </form>
    </div>
    <script>
    	$(function(){
    		$("input[name=up_file]").change(e =>{
    			if($(e.target).val()==""){
    				$("#fname").show();
    			}else{
    				$("#fname").hide();
    			}
    		});
    	});
    </script>

    <style>
    div#notice-container{width:1000px; margin:0 auto; text-align:center;}
    div#notice-container h1{margin:10px 0; }
    table#tbl-notice{width:1000px; height:600px; margin:0 auto; margin-top:100px; border:1px solid #1F695B; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; padding: 5px 0; text-align:center; border-bottom:1px solid #1F695B; color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;} 
    table#tbl-notice td {border:1px #1F695B solid; padding: 5px 0 5px 10px; text-align:left;}
    #noticeTitle{
    	width:830px;
    	border: none;
    	height: 40px;
    }
    </style>



<%@ include file="/view/common/footer.jsp"%>