<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 2021/05/30
  Time: 10:54 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String waybill = (String) request.getAttribute("waybill");
    int orderNum = (int)request.getAttribute("orderNum");
%>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/common/css/waybill.css" />
    <title>운송장 입력</title>
</head>
<body>
<div id="wayBillContainer">
    <div class="title">배송정보 관리</div>
    <div class="recentBill">
        <div>현재 등록된 운송장 번호</div><br><br>
        <input type="text" class="waybill" value="<%=waybill%>" readonly>
    </div>
    <div class="recentBill">
        <div class="reviseWaybill">
            수정할 운송장 번호<br><br>
            <div class="select">
                <select>
                    <option>대한통운</option>
                    <option>우체국</option>
                    <option>한진택배</option>
                    <option>롯데택배</option>
                </select>
            </div>
            <br><br>
            <input type="text" class="inputWaybill" placeholder="송장번호 입력">
        </div>
        <div class="enrollWaybill">
            등록할 운송장 번호<br><br>
            <div class="select">
                <select>
                    <option>대한통운</option>
                    <option>우체국</option>
                    <option>한진택배</option>
                    <option>롯데택배</option>
                </select>
            </div>
            <br><br>
            <input type="text" class="inputWaybill" placeholder="송장번호 입력">
        </div>
    </div>
    <div class="btn">
        <button class="enroll">등록</button>
        <button class="revise">수정</button>
        <button class="cancel">취소</button>
    </div>
</div>
<script>
    if($('.recentBill>input').val()==='null'||$('.recentBill>input').val()===''){
      $('.reviseWaybill').css('display','none');
      $('.revise').css('display','none');

    }else{
      $('.enrollWaybill').css('display','none');
      $('.enroll').css('display','none');

    }

    $(".enroll").click((e)=>{
      let waybill = $('.inputWaybill').val();
      location.replace('<%=request.getContextPath()%>/manager/updateWaybill?waybill='+waybill+'&orderNum=<%=orderNum%>');
      // window.close();
    });

    $(".revise").click((e)=>{
      let waybill = $('.inputWaybill').val();
      location.replace('<%=request.getContextPath()%>/manager/updateWaybill?waybill='+waybill+'&orderNum=<%=orderNum%>');
      // window.close();
    });

    $(".cancel").click((e)=> {
        window.close();
    });
</script>
</body>
</html>
