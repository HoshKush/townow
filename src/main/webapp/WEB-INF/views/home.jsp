<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! 오호식입니다.
</h1>

<button onclick="location.href='./board/create'">생성</button>
<button onclick="location.href='./users/create'">회원가입</button>

<P>  The time on the server is ${serverTime}. </P>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
				<a class="navbar-brand" href="#">Logo</a>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="${root }/">홈</a></li>

<!-- 					<li class="nav-item dropdown"><a -->
<!-- 							class="nav-link dropdown-toggle" href="#" id="navbardrop" -->
<!-- 							data-toggle="dropdown"> 게시판 </a> -->
<!-- 							<div class="dropdown-menu"> -->
<%-- 								<a class="dropdown-item" href="${root }/bbs/create">게시글 --%>
<%-- 									작성</a> <a class="dropdown-item" href="${root }/bbs/list">게시판 --%>
<!-- 									목록</a> -->
<!-- 							</div></li> -->
<!-- 						<li class="nav-item dropdown"><a -->
<!-- 							class="nav-link dropdown-toggle" href="#" id="navbardrop" -->
<!-- 							data-toggle="dropdown"> 메모 </a> -->
<!-- 							<div class="dropdown-menu"> -->
<%-- 								<a class="dropdown-item" href="${root }/memo/create">메모 --%>
<%-- 									작성</a> <a class="dropdown-item" href="${root }/memo/list">메모 --%>
<!-- 									목록</a> -->
<!-- 							</div></li> -->
<!-- 						<li class="nav-item dropdown"><a -->
<!-- 							class="nav-link dropdown-toggle" href="#" id="navbardrop" -->
<!-- 							data-toggle="dropdown"> 팀 </a> -->
<!-- 							<div class="dropdown-menu"> -->
<%-- 								<a class="dropdown-item" href="${root }/team/create">팀 작성</a> --%>
<%-- 								<a class="dropdown-item" href="${root }/team/list">팀 목록</a> --%>
<!-- 							</div></li> -->
<!-- 						<li class="nav-item dropdown"><a -->
<!-- 							class="nav-link dropdown-toggle" href="#" id="navbardrop" -->
<!-- 							data-toggle="dropdown"> 겔러리 </a> -->
<!-- 							<div class="dropdown-menu"> -->
<%-- 								<a class="dropdown-item" href="${root }/gallery/create">이미지 작성</a>  --%>
<%-- 								<a class="dropdown-item" href="${root }/gallery/list">이미지 목록</a> --%>
<!-- 							</div></li> -->
<!-- 					</ul> -->
					<ul class="nav navbar-nav navbar-right">
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${empty id}"> --%>
								<li class="nav-item"><a class="nav-link" href="${root }/company/create">Sign Up</a></li>
								<li class="nav-item"><a class="nav-link" href="${root }/login">Login</a></li>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<%-- 								<c:if test="${grade != 'A'}"> --%>
<%-- 									<li class="nav-item"><a class="nav-link" href="${root }/member/read">My Info</a></li> --%>
<%-- 									<li class="nav-item"><a class="nav-link" href="${root }/member/delete">계정 삭제</a></li> --%>
<%-- 								</c:if> --%>
<%-- 								<li class="nav-item"><a class="nav-link" href="${root }/member/logout">Logout</a></li> --%>
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 						<c:if test="${not empty id && grade == 'A' }"> --%>
<%-- 							<li id="admin" class="nav-item"><a class="nav-link" href="${root }/admin/list">회원목록</a></li> --%>
<%-- 							<li id="admin" class="nav-item"><a class="nav-link" href="${root }/team/list">직원목록</a></li> --%>
<%-- 							<li id="admin" class="nav-item"><a class="nav-link" href="${root }/team/create">직원등록</a></li> --%>
<%-- 						</c:if> --%>
					</ul>
					</nav>

</body>
</html>
