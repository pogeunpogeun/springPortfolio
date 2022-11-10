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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
 <c:choose>
	<c:when test = "${secretView == 'viewPasswdPage' }">
	<h2>Private</h2>
	<form name="DirForm">
		<input type="hidden" name="no" value="${dto.no }">
		<table border="1" width="30%" style="margin-bottom:20px;">
	<tr>
		<td style="font-weight:bold;" align="center">비밀번호</td>
		<td><input type="password" name="passwdChk"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="button" onClick="passwdSubmit();">확인</button>
		</td>
	</tr>
</table>
<script>
	function passwdSubmit() {
		document.DirForm.action="viewPasswdProc"
		document.DirForm.method="post";
		document.DirForm.submit();
	}
</script>
	</c:when>
<c:otherwise>
	<h2>게시글 상세보기</h2>
<table border="1" width="80%" style="margin-bottom:20px;">
	<tr>
		<td style="text-align:center; font-weight:bold;">작성자</td>
		<td colspan="5">${dto.boardWriter }</td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">제목</td>
		<td colspan="3">${dto.subject }</td>	
		<td style="text-align:center; font-weight:bold;">이메일</td>
		<td>${dto.email }</td>	
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">내용</td>
		<td colspan="5" style="width:99.5%; height:300px; vertical-align:top;">
		${dto.content }
		</td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">카테고리</td>
		<td colspan="5">
			<c:choose>
				<c:when test = "${dto.tbl == 1 }">
					자유게시판
				</c:when>
				<c:otherwise>
					갤러리
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">Private</td>
		<td colspan="5">
			<c:choose>
				<c:when test = "${dto.secretGubun == 'T' }">
					Private
				</c:when>
				<c:otherwise>
					Public
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td style="text-align:center; font-weight:bold;">Notice</td>
		<td colspan="5">
			<c:choose>
				<c:when test = "${dto.noticeNo == 1 }">
					Notice
				</c:when>
				<c:otherwise>
					General
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<div style="border: 0px solid red; padding-top:20px; width:80%;" align="right">
|
<a href="#" onClick="move('chuga','${pageNumber }','${dto.no}');">답글등록</a>
|
<a href="#" onClick="move('chuga','${pageNumber }','${dto.no}');">전체목록</a>
|
<a href="#" onClick="move('chuga','${pageNumber }','${dto.no}');">수정</a>
|
<a href="#" onClick="move('chuga','${pageNumber }','${dto.no}');">삭제</a>
|
</div>	
 		<div style="border: 0px solid red; padding-top: 10px; width: 80%; margin-bottom:10px; " align="center">
			<table border="0" width="100%">
				<tr>
					<td width="150">이전글</td>
					<td>
						<c:choose>
						<c:when test = "${dto.preNo > 0 }">
							<a href="#" onClick="move('view','${pageNumber }', '${dto.preNo }');">${dto.preSubject }</a>
						</c:when>
						<c:otherwise>
							이전글이 없습니다.
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>다음글</td>
					<td>
						<c:choose>
						<c:when test = "${dto.nxtNo > 0 }">
							<a href="#" onClick="move('view', '${pageNumber }','${dto.nxtNo }');">${dto.nxtSubject }</a>
						</c:when>
						<c:otherwise>
							다음글이 없습니다.
						</c:otherwise>
						</c:choose>				
					</td>
				</tr>		
			</table>
		</div>
	<script>
		function move(value1, value2, value3) {
			location.href = value1 + "?no=" + value3 + "&pageNumber=" + value2 + "&${searchQueryString}";
		}
	</script>
<div id="commentList" style="margin-bottom:50px;">
<c:forEach var="commentDto" items="${commentList }">
<%--
<table border="0" align="center" width="80%" style="margin-left:80px;">
			<tr>
				<td style="padding: 0 0 10 0;">
					<table border="0" align="center" width="100%">
						<tr>
							<td>${commentDto.writer }(${commentDto.boardNo }) &nbsp; ${commentDto.regiDate }</td>
						</tr>
						<tr>
							<td colspan="2">
								${commentDto.content}
							</td>
						</tr>				
					</table>
					<hr>
				</td>
			</tr>
</table>
 --%>
</c:forEach>
</div>
<div id="commentResult" style="width:80%; margin-bottom:20px;"></div>
</c:otherwise>
</c:choose>
</body>
</html>
<script>
 $(document).ready(function(){
	commentList();
});
function commentList() {
	let param = { 
			"no" :${dto.no}
	}
	
	$.ajax({
		url: "${path}/board/commentList",
		type: "post",
		data: param,
		success: function(result) {
			$("#commentResult").html(result);
		}
	});
} 
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
		"no" : $("#no").val(),
		"writer" : $("#writer").val(),
		"passwd" : $("#passwd").val(),
		"content" : $("#content").val()
	}
	var url = "commentInsertProc";
	$.ajax({
		type: "post",
		data: param,
		datatype: "JSON",
		url: "commentInsertProc",
		success: function(result) {
			if (result == 1) {
				console.log("result: " + result);
			} else {
				console.log("result: " + result);
			}
		}
	});
}

function suntaek(value1, value2, value3, value4) {
	$("#commentNo").text(value1);
	$("#writer").text(value2);
	$("#content").val(value3);
	$("#btnCommentSave").text(value4);
}

function commentReset() {
	$("#content").val('');
	$("#passwd").val('');
	$("#btnCommentSave").text('등록하기');
}
</script>
