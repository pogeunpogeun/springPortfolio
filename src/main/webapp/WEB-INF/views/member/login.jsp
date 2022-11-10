<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../_include/inc_header.jsp" %>
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
<h2>LogIn </h2>
<form name="DirForm">
<table border="1" style="margin-bottom:50px;">
	<tr>
		<td style="font-weight:bold;" align="center">ID</td>
		<td><input type="text" name="id"></td>
	</tr>
	<tr>
		<td style="font-weight:bold;" align="center">PASSWD</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="button" onClick="login();">로그인</button>
		</td>
	</tr>
</table>
</form>
<script>
	function login() {
		if(confirm('로그인할까요?')) {
			document.DirForm.action="loginProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>
</body>
</html>