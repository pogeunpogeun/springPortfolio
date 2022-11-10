<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../_include/inc_header.jsp" %>
<%@ include file = "../_include/_inc_top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap');
	* {font-family: 'IBM Plex Sans KR', sans-serif;}
	table {border-collapse :  collapse;}
	a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: black; text-decoration: underline;}
</style>
<title>Insert title here</title>
</head>
<body>
<h2>회원삭제</h2>
<form name="DirForm">
<input type="hidden" name="no" value="${dto.no }">
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
						<img src="/springPortfolio/attach/springPortfolio/member/${tempArray1[1] }" alt="${tempArray1[0] }" title="" style="width:100p; height:200px;">
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
	<tr>
		<td align="center" style="font-weight:bold;">비밀번호</td>
		<td><input type="text" name="passwd"></td>
	</tr>
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
	<tr>
		<td colspan="3" align="center">
			<button type="button" onClick="save();">확인</button>	
		</td>
	</tr>
</table>
</form>
<div style="border: 0px solid red; width: 438px; margin-top: 10px; margin-bottom:20px;" align="right">
|
<a href="#" onClick="move2('list','');">전체목록</a>
|
<a href="#" onClick="move2('chuga','');">등록</a>
|
</div>
</body>
</html>
<script>
	function save() {
		if(confirm('삭제할까요?')) {
			document.DirForm.action="sakjeProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
	function move(value1, value2) {
		location.href = value1 + "?no=" + value2;
	}
	function move2(value1, value2) {
		location.href = value1 + "?no=" + ${no};
	}
	function downLoad(value1, value2) {
		location.href = "download?no=" + value1 + "&num=" + value2;
	}
</script>