<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../_include/inc_header.jsp" %>
<%-- <%@ include file = "../_include/_inc_top.jsp" %> --%>
<!DOCTYPE html>
<html>
<head>
<style>
	@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap');
	* {font-family: 'IBM Plex Sans KR', sans-serif;}
	table {border-collapse :  collapse;}
	a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: black; text-decoration: underline;}
	input {border:none;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판</h2>
<form name="DirForm">
<table border="1" width="80%" style="margin-bottom:10px;">
	<tr>
		<td style="text-align:center; font-weight:bold;">작성자</td>
		<td>${sessionName }</td>
		<td style="text-align:center; font-weight:bold;" colspan="3">비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">제목</td>
		<td colspan="3"><input type="text" name="subject"></td>	
		<td style="text-align:center; font-weight:bold;">이메일</td>
		<td>${email }</td>	
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">내용</td>
		<td colspan="5">
		<textarea name="content" style="width:99.5%; height:300px; border:none;"></textarea>
		</td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">카테고리</td>
		<td colspan="5">
			<select name="tbl">
			<option value="">--선택--</option>
		<c:forEach var = "dto" items = "${list }">
				<option value="${dto.tbl }">${dto.tblName }</option>
		</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">Private</td>
		<td colspan="5">
			<input type="checkbox" name="secretGubun" value="T">Private
		</td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">Notice</td>
		<td colspan="5">
			<input type="checkbox" name="noticeNo" value="1">Notice
		</td>
	</tr>
</table>
</form>
<div style="width:80%; text-align:right; margin-bottom:50px;">
<button type="button" onClick="save();">등록하기</button>
</div>
<script>
	function save() {
		if(confirm('등록하시겠습니까?')) {
			document.DirForm.enctype = "multipart/form-data";
			document.DirForm.action="chugaProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>
