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
<div id="comment" style="border: 0px solid red; width:80%;" align="left">
<h2>Reply</h2>
<table border="1" align="center" width="100%">
	<tr>
		<td style="text-align:center; font-weight:bold;">이름</td>
		<td>${nickname}</td>
		<td style="text-align:center; font-weight:bold;">비밀번호</td>
		<td colspan="2"><input type="password" name="passwd" id="passwd" size="10" value=""></td>
		<td style="display:none;"><input type="text" name="no" id="no" value="${boardNo }"></td>
		<td style="display:none;"><input type="hidden" name="writer" id="writer" value="${nickname }"></td>
	</tr>
	<tr>
		<td colspan="6">
			<textarea name="content" id="content" style="width:99%; border:none;"></textarea>
		</td>
	</tr>
</table>
<div style="border: 0px solid red; width:100%; margin-top:10px;" align="right">
	<button type="button" id="btnCommentSave">등록하기</button>
	<button type="button" id="btnCommentReset">초기화</button>
</div>
</div>

<div style="height: 20px;"></div>

<table border="0" align="center" width="100%">
<c:forEach var="dto" items="${commentList }">
			<tr>
				<td style="padding: 0 0 10 0;">
					<table border="0" align="center" width="100%">
						<tr>
							<td>${dto.writer } &nbsp; ${dto.regiDate }</td>
						</tr>
						<tr>
							<td colspan="2">
								${dto.content }
							</td>
						</tr>				
					</table>
					<hr>
				</td>
			</tr>
</c:forEach>
</table>
<input type="hidden" id="result">

<script>
$(document).ready(function(){
	$("#btnCommentSave").click(function(){
		commentSave();
	});
	$("#btnCommentReset").click(function(){
		commentReset();
	});
});

function commentSave() {
  	var param = {
		"no" : ${boardNo },
		"writer" : $("#writer").val(),
		"passwd" : $("#passwd").val(),
		"content" : $("#content").val()
	}
	var url = "${path}/board/commentInsertProc";
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			$("#result").val(data);
			if ($("#result").val() == 1) {

			} else {

			}
			commentList(); 
		}
	});
}

function suntaek(value1, value2, value3, value4) {
	$("#procGubun").text(value1);
	$("#commentWriter").val(value2);
	$("#commentContent").val(value3);
	$("#btnCommentSave").text(value4);
}

function commentReset() {
	$("#procGubun").text('chuga');
	$("#commentWriter").val('');
	$("#commentContent").val('');
	$("#btnCommentSave").text('등록하기');
}
</script>