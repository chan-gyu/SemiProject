<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.yoriessence.point.model.vo.MemberPoint" %>
<%@ include file="/view/common/header.jsp"%>
<%
	List<MemberPoint> point=(List<MemberPoint>)request.getAttribute("pointList");
%>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	/* section{
		display:flex;
	} */
	#point_container{
		width:1100px;
		margin:0 auto;
		padding:50px;
		/* border:1px solid black; */
		overflow:hidden;
	}
	#point_container a{
		cursor: pointer;
	}
	#point_title{
		font-size:30px;
	}
	#point_info, #point_guide{
		display:inline-block;
	}
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
	
	.filter_choice{
		color:#1F695B;
	}
	
</style>

<script>
	const pageMove=(pageNo)=>{
		$.ajax({
			url:"<%=request.getContextPath()%>/point/pointFilter",
			data:{
				filter:$("#filter_hidden").val(),
				cPage:pageNo
			},
			success:data=>{
				$("#point_body").html(data);
			}
		});
	}
</script>

<section>
	<div id="point_container">
		<h3 id="point_title">포인트 조회</h3>
		<div id="point_info">
			<p>나의 포인트</p>
			<p><%=request.getAttribute("pointSum") %> 점</p>
		</div>
		<ul id="point_guide">
			<li>매주 월요일 추천 10개당 포인트 100점 적립됩니다. (7일 기준 적립)</li>
			<li>포인트는 3000점 이상부터 사용 가능합니다.</li>
		</ul>
		<div id="point_table">
			<div id="point_view_filter">
				<input type="hidden" name="filter_hidden" id="filter_hidden">
				<a id="filter_all" class="filter_choice">전체</a>
				<a id="filter_earned">적립</a>
				<a id="filter_used">사용</a>
			</div>
			<hr>
			<div id="point_header">
				<span>포인트</span>
				<span>적립 구분</span>
				<span>적립 날짜</span>
			</div>
			<hr>
			<div id="point_body">
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
			</div>
		</div>
	</div>
</section>

<script>
	
	
	
	$("#filter_all").click((e)=>{
		$("#filter_hidden").val("");
		$(e.target).addClass("filter_choice");
		$(e.target).siblings().each((i,v)=>{
			$(v).removeClass("filter_choice");
		});
		$.ajax({
			url:"<%=request.getContextPath()%>/point/pointFilter",
			data:{
				cPage:<%=request.getAttribute("cPage")%>
			},
			success:data=>{
				$("#point_body").html(data);
			}
		});
	});
	$("#filter_used").click((e)=>{
		$("#filter_hidden").val("used");
		$(e.target).addClass("filter_choice");
		$(e.target).siblings().each((i,v)=>{
			$(v).removeClass("filter_choice");
		});
		$.ajax({
			url:"<%=request.getContextPath()%>/point/pointFilter",
			data:{
				filter:"used",
				cPage:<%=request.getAttribute("cPage")%>
			},
			success:data=>{
				$("#point_body").html(data);
			}
		});
	});
	$("#filter_earned").click((e)=>{
		$("#filter_hidden").val("earned");
		$(e.target).addClass("filter_choice");
		$(e.target).siblings().each((i,v)=>{
			$(v).removeClass("filter_choice");
		});
		$.ajax({
			url:"<%=request.getContextPath()%>/point/pointFilter",
			data:{
				filter:"earned",
				cPage:<%=request.getAttribute("cPage")%>
			},
			success:data=>{
				$("#point_body").html(data);
			}
		});
	});

	$(".point_row").find($(".point")).each((i,v)=>{
		if($(v).text()<0){
			$(v).css("color","red");
		}else{
			$(v).css("color","blue");
		}
	});
	 
</script>

<%@ include file="/view/common/footer.jsp"%>