<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="/mysite5/assets/js/jquery/jquery-1.12.4.js" ></script>
<script type="text/javascript" src="/mysite5/assets/bootstrap/js/bootstrap.js" ></script>


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
	
	<!-- 모달창 영역 -->
	<div class="modal fade" id="delModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">방명록 삭제</h4>
	      </div>
	      <div class="modal-body">
	       <label>비밀번호</label>
	       <input type="text" id="modalPassword" name="password" value="" ><br>
	       <!-- no히든처리 -->
	       <input type="hidden" id="modalNo" name="no" value="" >
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	        <button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

</body>

<script type="text/javascript">

	//DOM이 생성되면 ,(시작됫을때) 
	$("document").ready( function(){
		//테스트
		console.log("ready")
		
		//리스트 출력
		fetchList();	
		
		
	});
	
	
	
	//삭제버튼 클릭할때 --> 비밀번호 입력창 호출
	$("#guestbookListArea").on("click" , "a", function(){
		event.preventDefault(); //기본 기능을 구동 못하게 막아줌 , a링크가 연결되지 못하게 막음 
		console.log("모달 창 호출");
		
		//비밀번호 필드 초기화
		$("#modalPassword").val("");
			
		//번호 읽어오기 (no)
		var no = $(this).data("no");
		//console.log(no);
		$("#modalNo").val(no);
		
		//모달창 호출
		$("#delModal").modal();
			  
			
	});
	
	
	//모달창 삭제버튼 클릭
	$("#modalBtnDel").on("click" , function(){
		console.log("모달창 삭제버튼 클릭")		
		
		
		//모달창 비밀번호, no수집
		var gueatBookVo = {
			password : $("#modalPassword").val(),
			no : $("#modalNo").val()
		}
		
		
		//ajax 삭제오쳥
		$.ajax({
		//보낼때
			url : "${pageContext.request.contextPath }/api/guest/delete",		
			type : "post",
			//contentType : "application/json",
			data : gueatBookVo,
	
			//받을때
			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				console.log(count);
				
				if(count == 1){
					//count == 1 --> 삭제작업				
					$("#delModal").modal("hide"); //모달창 닫기				
					//no 테이블(글) 안보이도록 처리
					$("#t-"+no).remove();
					
				}else{
					alert("비밀번호가 틀렸습니다.");
					$("#modalPassword").val("");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	
		
	});




	//방명록 등록버튼 클릭할때 
	$("#btnSubmit").on("click" , function(){
		console.log("방명록 등록버튼 클릭");
		
		//방명록 데이터 수집
		var guestBookVo = {
			name : $("[name='name']").val(),
			password : $("[name='pass']").val(),
			content : $("[name='content']").val()
		};
		
		//console.log(guestBookVo);
		
		//ajax방식으로 요청 (저장) , json으로 요청하는 법 (contentType씀)	
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath}/api/guest/write2", //json방식	 
			//url : "${pageContext.request.contextPath}/api/guest/write",
			type : "post",
			contentType : "application/json", //json으로 보낼때 사용
			data : JSON.stringify(guestBookVo), //링크 뒤에 붙는 정보를 date에 넣어줌 , JSON.stringify() json으로 변환,

			//받을때
			dataType : "json",
			success : function(guestBookVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestBookVo);
				//console.log(guestBookVo.no);
				//console.log(guestBookVo.name);
				
				//참고 --> {no: 49, name: "김김이", password: null, content: "김김이", date: "2021-02-09 04:06:55"}
				render(guestBookVo, "up"); //게스트북 정보 출력
				
				//입력폼 비우기
				$("[name='name']").val("");
				$("[name='pass']").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
	
	
	
	//방명록 글 정도  + html조합하여 화면에 출력
	function render(guestBookVo , updown){
		
		//문자열로 만듬 , 소문자만 인식가능 ""안에
		var str = "";
		str += "<table id='t-"+guestBookVo.no+"' class='guestRead'>";
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
		str += "		<td><a href='' data-no=" + guestBookVo.no + ">[삭제]</a></td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<td colspan=4 class='text-left'>"+ guestBookVo.content +"</td>";
		str += "	</tr>";
		str += "</table>";
		
		//$("#guestbookListArea").html(str);
		//$("#guestbookListArea").append(str);
		
		
		if(updown == 'down'){
			$("#guestbookListArea").append(str);
		}else if(updown == 'up'){
			$("#guestbookListArea").prepend(str);
		}else{
			console.log("방향 미지정");
		}
		
		
		
	}
	
	//전체 리스트 출력
	function fetchList(){
		
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
					render(guestBookList[i] , "down");
				}
			
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
	}

</script>



</html>