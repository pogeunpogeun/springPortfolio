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
<div class="container" style="margin-top:50px;">
  <h2 style="font-weight:bold;">Members</h2>
  <p>* 총 회원 수 [${totalRecord }] </p>            
  <table class="table">
    <thead>
      <tr>
        <th>No</th>
        <th>Name</th>
        <th>Date</th>
        <th>Option</th>
      </tr>
    </thead>
    <tbody>
     <c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="4">
			등록된 회원이 없습니다.
			</td>
		</tr>
		</c:if>
		<c:forEach var="dto" items="${list }">
		<tr>
			<td>${dto.no }</td>
			<td><a href="#" onClick="move('view','${dto.no}');">${dto.name }</a></td>
			<td>${dto.regiDate }</td>
			<td>
				<a href="#" onClick="move('sujung','${dto.no}');">수정 
				/
				<a href="#" onClick="move('sakje','${dto.no}');">삭제
		</tr>
	</c:forEach>
    </tbody>
  </table>
<ul class="nav justify-content-end">
  <li class="nav-item">
    <a class="nav-link" href="${path }/member/list">[전체목록]</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#" onClick="move('chuga','');">[등록하기]</a>
  </li>
</ul>
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
<button type="button" class="btn btn-dark btn-sm" onClick="search();">Search</button>
</form>
<nav aria-label="Page navigation example">
<ul class="pagination">
<c:if test="${totalRecord > 0 }">
	 <!-- ------------------------------------------------------------------------------ -->
<c:if test="${totalRecord > 0 }">
<c:set var="value1" value="list"></c:set>
  <c:if test="${startPage > blockSize}">
		 <li class="page-item"><a class="page-link" href="#" onclick="goPage('${value1 }', '${startPage - blockSize }');">Previous</a></li>
		</c:if>
		<c:if test="${startPage <= blockSize}">
		<!-- <li class="page-item"><a class="page-link" href="#">Previous</a></li> -->
	</c:if>
    <li class="page-item"><a class="page-link" href="#" onclick="goPage('${value1 }', '1');">Previous</a></li>
		<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
			<c:if test="${i == pageNumber}">
				<li class="page-item"><a class="page-link" href="#">${i }</a></li>
			</c:if>
			<c:if test="${i != pageNumber}">
				<li class="page-item"><a class="page-link" href="#" onclick="goPage('${value1 }', '${i }');">${i }</a></li>
			</c:if>			    
		</c:forEach>	
		<c:if test="${lastPage < totalPage}">
			<li class="page-item"><a class="page-link" href="#" onclick="goPage('${value1 }', '${startPage + blockSize }');">Next</a></li>
		</c:if>
		<c:if test="${lastPage >= totalPage}">
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</c:if>	
		 <%-- <a href="#" onclick="goPage('${value1 }', '${totalPage }');">[끝페이지]</a> --%>
</c:if>
	<!-- ------------------------------------------------------------------------------ --> 
</c:if>
 </ul>
</nav>
</div>
</body>
</html>
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
		location.href = "${path}/member/list?pageNumber=" + value2 + "&${searchQueryString }";
	}
</script>
