<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
	@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap');
	* {font-family: 'IBM Plex Sans KR', sans-serif;}
	table {border-collapse :  collapse;}
	a {text-decoration:none;}
	.flex-container{display:flex;}
	.flex-items{}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${title }</h2>
<c:forEach var="listDto" items="${list }">
<div style="margin-left:20%">
	<div style="border: 1px solid #666666; border-right:none; width:50px; float:left;"></div>
	<div style="border: 1px solid #666666; width:300px; float:left;"></div>
	<div style="border: 1px solid #666666; border-left:none; width:60px; float:left;"></div><br>
	<div style="border: 1px solid #666666; border-top:none; width:412px; height:500px; float:left;"></div>

</div>
</c:forEach>
</body>
</html>