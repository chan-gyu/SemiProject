<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
  <div id="notice-container">
  <h1>공지사항 등록</h1>
    <form action="<%=request.getContextPath()%>/notice/noticeWriteEnd" method="post" enctype="multipart/form-data">
        <table id="tbl-notice">
        <tr Style="height:50px;">
            <th>제 목</th>
            <td><input type="text" name="noticeTitle" id="noticeTitle" required></td>
        </tr>
        <tr Style="height:50px;">
            <th>첨부파일</th>
            <td><input type="file" name="up_file"></td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea rows="5" cols="50" id="content" name="content" style="border:none; margin:0 auto;width:100%; height:100%;"></textarea></td>
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
    div#notice-container{width:1000px; margin:0 auto; text-align:center;}
    div#notice-container h1{margin:10px 0; }
    table#tbl-notice{width:1000px; height:600px; margin:0 auto; margin-top:100px; border:1px solid #8CC7BC; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; padding: 5px 0; text-align:center; border-bottom:1px solid #8CC7BC; color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;} 
    table#tbl-notice td {border:1px #8CC7BC solid; padding: 5px 0 5px 10px; text-align:left;}
    #noticeTitle{
    	width:830px;
    	border: none;
    	height: 40px;
    }
    </style>

<%@ include file="/view/common/footer.jsp"%>