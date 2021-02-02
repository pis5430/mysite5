<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="">일반게시판</a></li>
				<li><a href="">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="read">
					<form action="${pageContext.request.contextPath}/comment/modify" method="get">
						<!-- 작성자 -->
						<div class="form-group">
							<span class="form-text">작성자</span> <span class="form-value">${commentOne.name}</span>
						</div>

						<!-- 조회수 -->
						<div class="form-group">
							<span class="form-text">조회수</span> <span class="form-value">${commentOne.hit}</span>
						</div>

						<!-- 작성일 -->
						<div class="form-group">
							<span class="form-text">작성일</span> <span class="form-value">${commentOne.reg_date}</span>
						</div>

						<!-- 제목 -->
						<div class="form-group">
							<span class="form-text">제 목</span> <span class="form-value">${commentOne.title}</span>
						</div>

						<!-- 내용 -->
						<div id="txt-content">
							<span class="form-value">${commentOne.content}</span>
						</div>
						<!-- 수정 버튼이 작성한 사람에게만 보이도록 -->
						<c:if test="${authUser.no == commentOne.user_no}">
							<a id="btn_modify" href="${pageContext.request.contextPath}/comment/modifyForm?no=${commentOne.no}">수정</a>
						</c:if>
						
						<!-- 목록버튼 -->
						<a id="btn_modify" href="${pageContext.request.contextPath}/comment/list">목록</a>
						
						<!-- 로그인 시에만 보이도록 댓글달기 버튼(첫번째 댓글이 아닌 두번째부터의 댓글) -->
						<c:if test="${!empty sessionScope.authUser}">
							<a id="btn_modify" href="${pageContext.request.contextPath}/comment/commentForm?group_no=${commentOne.group_no}&order_no=${commentOne.order_no}&depth=${commentOne.depth + 1}">댓글달기</a>
						</c:if>
					</form>
					<!-- //form -->
				</div>
				<!-- //read -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
