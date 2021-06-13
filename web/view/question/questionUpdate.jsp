<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
<%@ page import="com.yoriessence.question.model.vo.Question" %>
<%
	Question q=(Question)request.getAttribute("question");

%>

	 <div id="question-container">
    <form action="<%=request.getContextPath()%>/question/questionUpdateEnd" method="post" enctype="multipart/form-data">
    	<input type="hidden" name="questionNo" value="<%=q.getQuestionNumber()%>">
        <table id="tbl-question">
        <tr Style="height:50px;">
            <th>제 목</th>
            <td><input type="text" name="questionTitle" id="questionTitle" value="<%=q.getQuestionTitle()%>" required></td>
        </tr>
        <tr Style="height:50px;">
            <th>첨부파일</th>
            <td style="position:relative;">
            <%if(q.getQuestionPic()!=null) { %>
            <input type="file" name="up_file">
            <input type="hidden" name="oriFile" value="<%=q.getQuestionPic()%>">
            <span id="fname"><%=q.getQuestionPic() %></span>
            <%} else{ %>
            <input type="file" name="up_file">
            <%} %>
           </td>
        </tr>
        <tr>
        	<th>작성자</th>
        	<td><input type="text" id="MemberId" name="MemberId" value="<%=q.getMemberId()%>" style="border:none;" readonly></td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea rows="5" cols="50" name="content" style="border:none; margin:0 auto;width:100%; height:100%;"><%=q.getQuestionContent() %></textarea></td>
        </tr>
        <tr Style="height:50px;">
            <th colspan="2">
                <input type="submit" value="등록하기" onclick="">
            </th>
        </tr>
    </table>
    </form>
    </div>

    <style>
    div#question-container{width:1000px; margin:0 auto; text-align:center;}
    div#question-container h1{margin:10px 0; }
    table#tbl-question{width:1000px; height:600px; margin:0 auto; margin-top:100px;border:1px solid #1F695B; border-collapse:collapse; clear:both; }
    table#tbl-question th {width: 125px; padding: 5px 0; text-align:center; border-bottom:1px solid #1F695B; color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;} 
    table#tbl-question td {border:1px #1F695B solid; padding: 5px 0 5px 10px; text-align:left;}
    #questionTitle{
    	width:830px;
    	border: none;
    	height: 40px;
    }
    </style>



<%@ include file="/view/common/footer.jsp"%>