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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원목록</h2>
<div style="width:50%; border:0px solid red; text-align:left;">
* 총 회원 수 [${totalRecord }] 
</div>
<table border="1" width="50%">
	<tr>
		<th>순번</th>
		<th>이름</th>
		<th>등록일</th>
		<th>비고</th>
	</tr>
		<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="4">
			등록된 회원이 없습니다.
			</td>
		</tr>
		</c:if>
		<c:forEach var="dto" items="${list }">
		<tr>
			<td align="center">${dto.no }</td>
			<td align="center"><a href="#" onClick="move('view','${dto.no}');">${dto.name }</a></td>
			<td align="center">${dto.regiDate }</td>
			<td align="center">
				<a href="#" onClick="move('sujung','${dto.no}');">수정 
				/
				<a href="#" onClick="move('sakje','${dto.no}');">삭제
		</tr>
	</c:forEach>
</table>
</body>
</html>
<div style="border: 0px solid red; width: 50%; margin-top: 10px; margin-bottom:20px;" align="right">
|
<a href="list">전체목록</a>
|
<a href="#" onClick="move('chuga','');">등록</a>
|
</div>
<div style="border: 0px solid red; width:50% margin-top:10px;" align="center">
<form name="searchForm">
<select name="searchGubun" id="searchGubun">
<c:choose>
	<c:when test="${searchGubun == 'id' }">
		<option value="">--선택--</option>
		<option value="id" selected>아이디</option>
		<option value="name">이름</option>
		<option value="id_name">아이디/이름</option>
	</c:when>
	<c:when test="${searchGubun == 'name' }">
		<option value="">--선택--</option>
		<option value="id">아이디</option>
		<option value="name" selected>이름</option>
		<option value="id_name">아이디/이름</option>
	</c:when>
	<c:when test="${searchGubun == 'id_name' }">
		<option value="">--선택--</option>
		<option value="id">아이디</option>
		<option value="name">이름</option>
		<option value="id_name" selected>아이디/이름</option>
	</c:when>
	<c:otherwise>
		<option value="" selected>--선택--</option>
		<option value="id">아이디</option>
		<option value="name">이름</option>
		<option value="id_name">아이디/이름</option>
	</c:otherwise>
</c:choose>
</select>
&nbsp;
<input type="text" name="searchData" id="searchData" value="${searchData }" style="width:150px">
&nbsp;
<button type="button" onClick="search();">검색</button>
</form>
</div>
<c:if test="${totalRecord > 0 }">
<div style="boder:0px solid red; width: 80%; margin-top: 10px; align="center">
	 <!-- ------------------------------------------------------------------------------ -->
<c:if test="${totalRecord > 0 }">
<c:set var="value1" value="list"></c:set>
		<a href="#" onclick="goPage('${value1 }', '1');">[첫페이지]</a>
		&nbsp;&nbsp;
		<c:if test="${startPage > blockSize}">
			<a href="#" onclick="goPage('${value1 }', '${startPage - blockSize }');">[이전10개]</a>
		</c:if>
		<c:if test="${startPage <= blockSize}">
			[이전10개]
		</c:if>
		&nbsp;	
		<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
			<c:if test="${i == pageNumber}">
				[${i }]
			</c:if>
			<c:if test="${i != pageNumber}">
				<a href="#" onclick="goPage('${value1 }', '${i }');">${i }</a>
			</c:if>			    
		</c:forEach>	
		&nbsp;
		<c:if test="${lastPage < totalPage}">
			<a href="#" onclick="goPage('${value1 }', '${startPage + blockSize }');">[다음10개]</a>
		</c:if>
		<c:if test="${lastPage >= totalPage}">
			[다음10개]
		</c:if>	
		&nbsp;&nbsp;
		<a href="#" onclick="goPage('${value1 }', '${totalPage }');">[끝페이지]</a>
</c:if>
	<!-- ------------------------------------------------------------------------------ --> 
</div>
</c:if>
<script>
	function move(value1, value2) {
		location.href = value1 + "?no=" + value2 + "&pageNumber=" + ${pageNumber } + "&${searchQueryString }";
	}
	function search() {
		if(confirm('검색하시겠습니까?')) {
			document.searchForm.action="listSearch";
			document.searchForm.method="post";
			document.searchForm.submit();
		}
	}
	function goPage(value1,value2) {		
		location.href = "list?pageNumber=" + value2 + "&${searchQueryString }";
	}
</script>
