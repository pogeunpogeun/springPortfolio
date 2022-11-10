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
	table {border-collapse :  collapse;}
	a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: black; text-decoration: underline;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판관리</h2>
<table border="1" width="50%">
	<tr>
		<th>boardChkNo</th>
		<th>tbl</th>
		<th>tblName</th>
		<th>regiDate</th>
	</tr>
	<c:forEach var = "dto" items = "${list }">
		<tr>
			<td>${dto.boardChkNo }</td>
			<td>${dto.tbl }</td>
			<td>${dto.tblName }</td>
			<td>${dto.regiDate }</td>
		</tr>
	</c:forEach>
</table>
