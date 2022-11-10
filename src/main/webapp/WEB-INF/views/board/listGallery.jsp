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
	a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: black; text-decoration: underline;}
	.wrapper {

    display: grid;

    grid-template-columns: 200px 200px 200px;

    grid-template-rows: 200px 200px 200px;
    
    justify-content: space-evenly;

	}
	.image {
	border: 1px solid black;
	margin-bottom : 30px;
	
	}
	
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Gallery</h2>
<div class="wrapper">

    <div class="image">1</div>

    <div class="image">2</div>

    <div class="image">3</div>

    <div class="image">4</div>

    <div class="image">5</div>

    <div class="image">6</div>
    
    <div class="image">7</div>
    
    <div class="image">8</div>
    
    <div class="image">9</div>

</div>
</body>
</html>
<%-- <div style="border: 0px solid red; width: 50%; margin-top: 10px; margin-bottom:20px;" align="right">
|
<a href="list">전체목록</a>
|
<a href="#" onClick="move('chuga','0');">등록</a>
|
</div>
<div style="border: 0px solid red; width:50% margin-top:10px;" align="center">
<form name="searchForm">
<select name="searchGubun" id="searchGubun">
<c:choose>
	<c:when test="${searchGubun == 'subject' }">
		<option value="">--선택--</option>
		<option value="subject" selected>제목</option>
		<option value="boardWriter">작성자</option>
		<option value="sub_wri">제목/작성자</option>
	</c:when>
	<c:when test="${searchGubun == 'boardWriter' }">
		<option value="">--선택--</option>
		<option value="subject">제목</option>
		<option value="boardWriter" selected>작성자</option>
		<option value="sub_wri">제목/작성자</option>
	</c:when>
	<c:when test="${searchGubun == 'sub_wri' }">
		<option value="">--선택--</option>
		<option value="subject">제목</option>
		<option value="boardWriter">작성자</option>
		<option value="sub_wri" selected>제목/작성자</option>
	</c:when>
	<c:otherwise>
		<option value="" selected>--선택--</option>
		<option value="subject">제목</option>
		<option value="boardWriter">작성자</option>
		<option value="sub_wri">제목/작성자</option>
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
</script> --%>