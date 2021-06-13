<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp"%>
  <div id="helper-container">
  <h1>도움말 등록</h1>
    <form action="<%=request.getContextPath()%>/helper/helperWriteEnd" method="post">
        <table id="tbl-helper">
        <tr Style="height:50px;">
            <th>제 목</th>
            <td><input type="text" name="helperTitle" id="helperTitle" required></td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea rows="5" cols="50" name="content" style="border:none; margin:0 auto;width:100%; height:100%;"></textarea></td>
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
    div#helper-container{width:1000px; margin:0 auto; text-align:center;}
    div#helper-container h1{margin:10px 0; }
    table#tbl-helper{width:1000px; height:600px; margin:0 auto; margin-top:100px; border:1px solid #8CC7BC; border-collapse:collapse; clear:both; }
    table#tbl-helper th {width: 125px; padding: 5px 0; text-align:center; border-bottom:1px solid #8CC7BC; color:#1F695B; font-weight:bold; background:#f3f6f7; vertical-align:middle;} 
    table#tbl-helper td {border:1px #8CC7BC solid; padding: 5px 0 5px 10px; text-align:left;}
    #helperTitle{
    	width:830px;
    	border: none;
    	height: 40px;
    }
    </style>	


<%@ include file="/view/common/footer.jsp"%>