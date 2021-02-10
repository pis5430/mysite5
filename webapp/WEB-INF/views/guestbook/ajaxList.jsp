<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/mysite5/assets/js/jquery/jquery-1.12.4.js" ></script>


<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/aside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>일반방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<!-- <form action="${pageContext.request.contextPath}/api/guest/write" method="post"> -->
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></td>
								<td><input id="input-pass"type="password" name="pass"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
					<input type="hidden" name="action" value="add">
					
				<!--</form> -->
				
				
				<div id="guestbookListArea">
					<!-- 방명록 리스트 출력 -->
				</div>
				
				<!-- //guestRead -->
				
				
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">

	//DOM이 생성되면 ,(브라우저 준비가 끝날을때?) 
	$("document").ready( function(){
		//테스트
		console.log("ready")
		
		
		
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath}/api/guest/list",		
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			//받을때
			dataType : "json",
			success : function(guestBookList){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestBookList); //잘 들어갔는지 확인 

				//게스트 정보 출력
				for(var i=0; i<guestBookList.length; i++){
					render(guestBookList[i]);
				}
			
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
		
	});
	






	$("#btnSubmit").on("click" , function(){
		console.log("방명록 등록버튼 클릭");
		
		//방명록 데이터 수집
		var name = $("[name='name']").val();
		var password = $("[name='pass']").val();
		var content = $("[name='content']").val();
		
		console.log(name);
		console.log(password);
		console.log(content);
		
		/*
		vat guestbookVo ={
				name: name , 
				password: password , 
				content : content
		};
		*/
		
		//ajax방식으로 요청 (저장)
		
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath}/api/guest/write",		
			type : "post",
			//contentType : "application/json",
			data : {name: name , password: password , content : content}, //링크 뒤에 붙는 정보를 date에 넣어줌

			//받을때
			dataType : "json",
			success : function(guestBookVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestBookVo);
				//console.log(guestBookVo.no);
				//console.log(guestBookVo.name);
				
				//참고 --> {no: 49, name: "김김이", password: null, content: "김김이", date: "2021-02-09 04:06:55"}
				render(guestBookVo); //게스트북 정보 출력
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
		
	});
	
	
	
	//방명록 글 정도  + html조합하여 화면에 출력
	function render(guestBookVo){
		
		//문자열로 만듬
		var str = "";
		str += "<table class='guestRead'>";
		str += "	<colgroup>";
		str += "		<col style='width: 10%'>";
		str += "		<col style='width: 40%'>";
		str += "		<col style='width: 40%'>";
		str += "		<col style='width: 10%'>";		
		str += "	</colgroup>";
		str += "	<tr>";
		str += "		<td>"+ guestBookVo.no +"</td>";
		str += "		<td>"+ guestBookVo.name +"</td>";
		str += "		<td>"+ guestBookVo.date +"</td>";
		str += "		<td><a href=''>[삭제]</a></td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<td colspan=4 class='text-left'>"+ guestBookVo.content +"</td>";
		str += "	</tr>";
		str += "</table>";
		
		//$("#guestbookListArea").html(str);
		$("#guestbookListArea").prepend(str);
		
		
	}
	
	

</script>



</html>