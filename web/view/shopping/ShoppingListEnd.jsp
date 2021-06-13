<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List,com.yoriessence.shopping.vo.OrderDetails" %>
<%
	List<OrderDetails> ods=(List<OrderDetails>)request.getAttribute("od");
%>
<%@ include file="/view/common/header.jsp"%>
   <style>
 		 .or>td{
			border: 1px gray solid;
        }
        
        .fonttext{
            font-size:38px;
        }
        .pod{
            /* border: 1px red solid; */
            height: 300px;
            width:300px;
        }
        #check{
            /* border: 1px red solid; */
            width:50px;
            height:50px;
        }
        .chogo{
            /* border: 1px red solid; */
            height:130px;
            width:1600px;
            margin:180px 100px 100px 100px;
            font-size:30px;
            text-align:center;
        }
        #jan{
            /* border: 1px red solid; */
            position: absolute;
            top:30%;
            left: 45%;
            font-size:50px;
            width:200px;
        }
       
        .gogo{
        	margin-left:1350px;
        	font-size:38px;
        }
        button{
        	margin-left:1450px;
        }
</style> 
<div>
     <h2 id="jan">주문내역</h2>
	        <table class="chogo">
	            <tr class="or">
	                <td>주문번호</td>
	                <td>상품명</td>
	                <td>총주문금액</td>
	                <td>주문일자</td>
	                <td>결제일자</td>
	            </tr>
	            <%if(!ods.isEmpty()) {%>
	            <%for(OrderDetails odl : ods) {%>
	            <tr class="or">
	                <td class="fonttext"><%=odl.getOrdernumber() %></td>
	                <td class="fonttext"><%=odl.getProductname() %></td>
	                <td class="fonttext">
	                    <p><%=odl.getOrderamount() %>원</p>
	                </td>
	                <td class="fonttext"><%=odl.getOrderdate()%></td>
	                <td class="fonttext"><%=odl.getPaymentdate()%></td>
	             </tr>
	             <%} %>
	             <%} %>
	        </table>
	               <button type="button" onclick="senter(event);" class="fonttext">반품/교환문의</button>
    </div>
    
<script>
	const senter=(e)=>{
			location.replace("<%=request.getContextPath()%>/view/shopping/shoppingsenter.jsp");
		}
	
</script>





























<%@ include file="/view/common/footer.jsp"%>