<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/header.jsp" %>

<style>
	#recipe_write{
		margin:0 auto;
		padding:20px;
		width:880px;
		position:relative;
	}
	form>div>div{
		margin:30px;
		position:relative;
	}
	#recipe_write p{
		display:inline-block;
	}
	#recipe_write input, select, textarea{
		background-color:rgb(235, 235, 235);
		border-radius:3px;
		border:rgb(182, 182, 182) 1px solid;
		height:30px;
		padding-left:5px;
	}
	.input{
		position:absolute;
		top:0;
		left:200;
	}
	.horizontal{
		display:flex;
	}
	.horizontal:first-child{
		background-color:red;
	}
	.input>div{
		margin-right:10px;
	}
	.input input{
		margin-right:10px; margin-bottom:10px;
	}

</style>

<section>
	<div id="recipe_write">
		<form>
			<div id="basic_info" class="info">
				<div>
					<p>레시피 제목</p>
					<div class="input"><input type="text" name="recipe_title" placeholder="레시피 제목을 입력하세요."></div>
				</div>
				<div>
					<p>레시피 소개</p>
					<div class="input"><input type="text" name="recipe_intro" placeholder=""></div>
				</div>
				<div>
					<p>동영상</p>
					<div class="input"><input type="text" name="recipe_intro" placeholder="동영상이 있으면 주소를 입력하세요."></div>
				</div>
				<div>
					<p>카테고리</p>
					<div class="input">
						<select name="recipe_cat">
							<option>종류별</option>
							<option value="한식">한식</option>
							<option value="중식">중식</option>
							<option value="일식">일식</option>
							<option value="양식">양식</option>
						</select>
						<select name="main_ingredient">
							<option>재료별</option>
							<option value="육류">육류</option>
							<option value="채소류">채소류</option>
							<option value="과일류">과일류</option>
							<option value="해산물">해산물</option>
						</select>
					</div>
				</div>
				<div>
					<p>요리정보</p>
					<div class="input horizontal">
						<div>
							<p>인원</p>
							<select name="servings">
								<option value="1">1인분</option>
								<option value="2">2인분</option>
								<option value="3">3인분</option>
								<option value="4">4인분</option>
								<option value="5">5인분</option>
								<option value="6">6인분 이상</option>
							</select>
						</div>
						<div>
							<p>시간</p>
							<select name="duration">
								<option value="5">5분 이내</option>
								<option value="10">15분 이내</option>
								<option value="30">30분 이내</option>
								<option value="60">1시간 이내</option>
								<option value="120">2시간 이내</option>
								<option value="121">2시간 이상</option>
							</select>
						</div>
						<div>
							<p>난이도</p>
							<label><input type="radio" name="difficulty" value="상">상</label>
							<label><input type="radio" name="difficulty" value="중">중</label>
							<label><input type="radio" name="difficulty" value="하">하</label>
						</div>
					</div>
				</div>
			</div>
			<div id="ingredient_info">
				<div>
					<p>재료</p>
					<div class="input horizontal">
						<input type="text" name="ing_cat">
						<ul>
							<li><input type="text"></li>
							<li><input type="text"></li>
							<li><input type="text"></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="process">
				<p>요리순서</p>
				<div>
					<div class="step"><textarea></textarea></div>
				</div>
			</div>
			<div id="tips">

			</div>
			<div id="tags">

			</div>
		</form>
	</div>
</section>
<%@ include file="/view/common/footer.jsp" %>