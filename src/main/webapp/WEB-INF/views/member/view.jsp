<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../_include/inc_header.jsp" %>
<%@ include file = "../_include/_inc_top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
	@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap');
	* {font-family: 'IBM Plex Sans KR', sans-serif;}
	table {border-collapse :  collapse; table-layout:fixed;}
	a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: black; text-decoration: underline;} 
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>내정보보기</h2>
<table border="1">
	<tr>
		<td align="center" style="font-weight:bold;">순번</td>
		<td>${dto.no }</td>
		<td rowspan="7" style="width:200px; height: 250px;">
			<c:choose>
					<c:when test="${dto.attachInfo == '-' }">
						&nbsp;
					</c:when>
					<c:otherwise>
						<c:set var="tempArray1" value="${fn:split(dto.attachInfo, ',') }" />
						<c:set var="temp1" value="${tempArray1[0] }" />
						<c:set var="temp2" value="${tempArray1[1] }" />
						<img src="/springPortfolio/attach/springPortfolio/member/${tempArray1[1] }" alt="${tempArray1[0] }" title="" style="width:100%; height:80%; padding:0px; margin:0px;">
					</c:otherwise>
			</c:choose>	
		</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">아이디</td>
		<td>${dto.id }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">닉네임</td>
		<td>${dto.nickname }</td>
	</tr>
	<%-- <tr>
		<td align="center" style="font-weight:bold;">비밀번호</td>
		<td>${dto.passwd }</td>
	</tr> --%>
	<tr>
		<td align="center" style="font-weight:bold;">연락처</td>
		<td>${dto.phone }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">이메일</td>
		<td>${dto.email }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">주민번호</td>
		<td>${fn:substring(dto.jumin, 0, 6)} - ${fn:substring(dto.jumin, 6, 7)}******</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">주소</td>
		<td colspan="2">${dto.juso1 } ${dto.juso2 } ${dto.juso3 } ${dto.juso4 }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">회원등급</td>
		<td colspan="2">${dto.grade }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">등록일</td>
		<td colspan="2">${dto.regiDate }</td>
	</tr>
</table>
<div style="border: 0px solid red; width: 438px; margin-top: 10px; margin-bottom:20px;" align="right">
|
<a href="list">전체목록</a>
|
<a href="#" onClick="move('list','0');">목록</a>
|
<a href="#" onClick="move('chuga','');">등록</a>
|
</div>
</body>
</html>
<script>
	function move(value1, value2) {
		location.href = value1 + "?no=" + value2 + "&pageNumber=" + ${pageNumber} + "&${searchQueryString }";
	}
</script>
</body>
</html>