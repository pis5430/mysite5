<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
					<c:if test="${authUser != null }">
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>
				
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						<c:forEach items="${gList}" var="vo">
							<li>
								<div class="view" id="viewDiv" data-no="${vo.no}" data-userno="${vo.user_no}" >
									<img name="imgView" class="imgItem" src="${pageContext.request.contextPath}/upload/${vo.saveName}">
									<div class="imgWriter">작성자: <strong>${vo.name}</strong></div>
								</div>
							</li>
						</c:forEach>	
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src ="">
						 <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
					<input id="modalNo" type="hidden" name="gallery_no" value="">
					
				</div>
				<form method="" action="">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
				
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">

	//이미지 올리기를 클릭햇을때
	$("#btnImgUpload").on("click" , function(){
		console.log("이미지 올리기 모달 창 호출");
		
		//글작성 필드 초기화
		$("#addModalContent").val("");
		
		//파일업로드 초기화
		$("#file").val("");
		
		//모달창 호출
		$("#addModal").modal();		
		
	});
	
	//이미지 박스를 클릭햇을때 
	$("#viewDiv").on("click" , function(){
		console.log("이미지 보기 모달 창 호출");
		
		//파일 데이터 수집
		var no = $(this).data("no")
		
		//ajax방식으로 요청 
		$.ajax({
			//보낼때
			url : "${pageContext.request.contextPath}/gallery/view", 	 
			//url : "${pageContext.request.contextPath}/api/guest/write",
			type : "post",
			//contentType : "application/json", //json으로 보낼때 사용
			data : {no : no}, //링크 뒤에 붙는 정보를 date에 넣어줌 , JSON.stringify() json으로 변환,

			//받을때
			dataType : "json",
			success : function(galleryVo){
				/*성공시 처리해야될 코드 작성*/
				
				//확인
				console.log(galleryVo);
				console.log(galleryVo.no);
				console.log(galleryVo.name);
				console.log(galleryVo.saveName); //
				
				// src에 가져온 데이터 넣어주기
				$("#viewModelImg").attr("src", "${pageContext.request.contextPath}/upload/" + galleryVo.saveName);
				
				//content 넣어주기
				$("#viewModelContent").html(galleryVo.content);
				
				//no값 넣어주기
				$("#modalNo").attr("value", galleryVo.no);		
				
			},
			
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			
		});
		
		//모달창 호출
		$("#viewModal").modal();
			
		
		
	});
	
	
	//갤러리 클릭 후 모달창으로 보기  + html조합하여 화면에 출력
	function render(fileName){
		
		//문자열로 만듬 , 소문자만 인식가능 ""안에
		var str = "";
		str += "<img class='imgItem' src='${pageContext.request.contextPath}/upload/"+fileName+">";
		
		
	}



</script>




</html>

