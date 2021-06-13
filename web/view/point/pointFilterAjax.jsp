<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.yoriessence.point.model.vo.MemberPoint" %>
<%
	List<MemberPoint> point=(List<MemberPoint>)request.getAttribute("pointList");
%>
<style>
 	#point_table{
		width:1100px;
		border:black 1px solid;
		margin-top:20px;		
	}
	#point_header{
		display:flex;
		justify-content:space-between;
	}
	#point_table>div{
		margin:30px;
	} 
	.point_row{
		display:grid;
		grid-template-columns:repeat(3, 1fr);
		gap:50px;
	}
	.point_row>span:last-child{
		text-align:right;
	}
	#pageBar{
		display:flex;
		justify-content:center;
	}
	#pageBar *{
		margin-left:15px;
	}
	#pageBar span.cPage{
		color:#1F695B ;
		font-weight:bold;
	}
</style>

				<div id="points">
					<%if(point.size()>0){ %>
						<%for(MemberPoint mp:point) { %>
							<div class="point_row">
								<span class="point"><%=mp.getPoint() %></span>
								<span class="point_info"><%=mp.getPointInfo() %></span>
								<span class="point_date"><%=mp.getPointDate()%></span>
							</div>
						<%}%>
					<%}else{ %>
						<span class="no_point">적립 내용이 없습니다.</span>
					<%} %>
				</div>
				<div id="pageBar"><%=request.getAttribute("pageBar") %></div>

<script>
	 $(".point_row").find($(".point")).each((i,v)=>{
		if($(v).text()<0){
			$(v).css("color","red");
		}else{
			$(v).css("color","blue");
		}
	});
	  
</script>


