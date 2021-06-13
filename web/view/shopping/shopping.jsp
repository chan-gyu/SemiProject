<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.yoriessence.shopping.vo.ShoppingCart, java.util.ArrayList" %>
<%
	int i=0;
	List<ShoppingCart> sc=(List<ShoppingCart>)request.getAttribute("list");
	for(ShoppingCart list : sc) {
		i+=list.getProductprice()*list.getProductnumber()+list.getProductshopify();
	}
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
            margin:180px 100px 20px 0px;
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
        .button{
        	margin-left:1450px;
            font-size:38px;
        }
        .gogo{
        	margin-left:1250px;
        	font-size:38px;
        }
</style> 
	
	<div>
        <h2 id="jan">장바구니</h2>
       	<form id="order" action="<%=request.getContextPath() %>/ShoppingListServlet">
	        <table class="chogo">
	            <tr class="or">
	                <td>상품명</td>
	                <td>가격</td>
	                <td>수량</td>
	                <td>배송비</td>
	                <td>주문하기</td>
	            </tr>
	            <%if(!sc.isEmpty()) {%>
	            <%for(ShoppingCart list : sc) {%>
	            <tr class="or">
	                <td class="fonttext"><%=list.getProductname() %></td>
	                <td class="fonttext"><%=list.getProductprice() %></td>
	                <td class="fonttext">
	                    <p><%=list.getProductnumber() %>EA</p>
	                </td>
	                <td class="fonttext"><%=list.getProductshopify() %>원</td>
	                <td>
	                    <button type="button" onclick="deleteshopping(event);" class="fonttext" value="<%=list.getProductname()%>">삭제하기</button>
	                </td>
	             </tr>
	             	<input type="hidden" name="memberid" value="<%=list.getMemberid() %>">
	             	<%-- <input type="hidden" name="proname" value="<%=list.getProductname() %>">
	             	<input type="hidden" name="pronumber" value="<%=list.getProductnumber() %>">
	             	<input type="hidden" name="proprice" value="<%=list.getProductprice() %>">
	             	<input type="hidden" name="proshopify" value="<%=list.getProductshopify() %>"> --%>
	             <%} %>
	             <%} %>
	        </table>
	        		<%for(int j=0;j<sc.size();j++){%>
	             	<input type="hidden" name="proname<%=j%>" value="<%=sc.get(j).getProductname() %>">
	             	<input type="hidden" name="pronumber<%=j%>" value="<%=sc.get(j).getProductnumber() %>">
	             	<input type="hidden" name="proprice<%=j%>" value="<%=sc.get(j).getProductprice()*sc.get(j).getProductnumber() %>">
	             	<input type="hidden" name="prono<%=j%>" value="<%=sc.get(j).getProductno() %>">
	        		<%} %> 
	        		<input type="hidden" name="sc" value="<%=sc.size() %>">
	        		<div class="gogo">총 주문금액 : <%=i %></div><br>
		        	<button type="button" onclick="test();" class="button">주문하기</button>
		</form>	        	
    </div>
    
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script>
	const deleteshopping=(e)=>{
		if(confirm("삭제하시겠습니까?")){
			location.replace("<%=request.getContextPath()%>/DeleteShopping?productname="+$(e.target).val());
		}
	}
	//결제자 이름, 결제상품 추후 추가
	function test()	{
		var IMP = window.IMP; // 생략가능
		IMP.init('iamport'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
		IMP.request_pay({
		    pg : 'kakao', // version 1.1.0부터 지원.
		    /*
				'kakao':카카오페이,
				html5_inicis':이니시스(웹표준결제)
				'nice':나이스페이
				'jtnet':제이티넷
				'uplus':LG유플러스
				'danal':다날
				'payco':페이코
				'syrup':시럽페이
				'paypal':페이팔
			*/
		    pay_method : 'card',
		    /*
				'samsung':삼성페이,
				'card':신용카드,
				'trans':실시간계좌이체,
				'vbank':가상계좌,
				'phone':휴대폰소액결제
			*/
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '상품',
		    /* 결제창에서 보여질 이름 */
		    amount : <%=i%>,
		    /* 가격 */
		    buyer_email : '<%=loginMember.getEmail()%>',
		    buyer_name : '<%=loginMember.getUserName()%>',
		    buyer_tel : '<%=loginMember.getPhone()%>',
		    buyer_addr : '<%=loginMember.getAddress()%>',
		    buyer_postcode : '123-456',
		    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
		    /*
		    	모바일 결제시,
		    	결제가 끝나고 랜딩되는 URL을 지정
		    	(카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
		    */
		}, function(rsp) {
		    if ( rsp.success ) {
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + <%=loginMember.getUserId()%>;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + <%=i%>;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        $("#order").submit();
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
		});
	}
	
	</script>

<%@ include file="/view/common/footer.jsp"%>