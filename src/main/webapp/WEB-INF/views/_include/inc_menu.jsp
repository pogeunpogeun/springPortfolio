<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Hi</title>
<%@ include file = "../_include/inc_header.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>
<body>
<%-- <table border="0" align="center">
	<tr>
		<td colspan="30" align="left" style="padding:5px 10px 10px;">
			<c:choose>
			<c:when test="${sessionScope.sessionNo == null || sessionScope.sessionNo == '' || sessionScope.sessionNo == 0}">
				<a href="${path }/member/login">Login</a>
			</c:when>
			<c:otherwise>
				<strong><span style="color:blue;">${sessionScope.sessionName }</span></strong>님 환영합니다!<br>
				<a href="${path }/member/logout">LogOut</a>
			</c:otherwise>
		</c:choose>				
		</td>
	</tr>
	<tr align="center">
		<td style="padding:0px 10px;" id="index">
			<a href="${path }">HOME</a>
		</td>
		<td style="padding:0px 10px;" id="member">
			<a href="${path }/member/list">회원관리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="board">
			<a href="${path }/board/list">게시판</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="gallery">
			<a href="${path }/board/listGallery">갤러리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="boardchk">
			<a href="${path }/boardChk/list">게시판관리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="shopProduct">
			<a href="${path }/shopProduct/list">상품관리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="shopVendor">
			<a href="${path }/shopVendor/list">제조사관리</a>
		</td>		
		<td>
			<td style="padding:0px 10px;" id="shopMall">
			<a href="${path }/shopMall/list">쇼핑몰</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="chart">
			<a href="${path }/chart/list">차트</a>
		</td>
	</tr>
</table> --%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-5">
                <a class="navbar-brand" href="#!">Spring Project</a>
                
            <c:choose>
			<c:when test="${sessionScope.sessionNo == null || sessionScope.sessionNo == '' || sessionScope.sessionNo == 0}">
				
			</c:when>
			<c:otherwise>
				<strong><span style="color:white;">${sessionScope.sessionName }님 환영합니다!</span></strong>			
			</c:otherwise>
		</c:choose>			
                   
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="${path }">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="${path }/member/list">Member</a></li>
                        <li class="nav-item"><a class="nav-link" href="${path }/board/list">Board</a></li>
                        <li class="nav-item"><a class="nav-link" href="${path }/board/listGallery">Gallery</a></li>
                        <c:if test="${sessionScope.sessionNo > 0}">
                        <li class="nav-item"><a class="nav-link" href="${path }/member/logout">LogOut</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</body>
</html>