<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file = "../_include/inc_header.jsp" %>
<table border="0" align="center">
	<tr>
		<td colspan="30" align="left" style="padding:5px 10px 10px;">
			<c:choose>
			<c:when test="${sessionScope.sessionNo == null || sessionScope.sessionNo == '' || sessionScope.sessionNo == 0}">
				<a href="${path }/member/login">Login</a>
			</c:when>
			<c:otherwise>
				<strong><span style="color:blue;">${sessionScope.sessionName }</span></strong>님 환영합니다!<br>
				<a href="${path }/member/logout">LogOut</a>
			</c:otherwise>
		</c:choose>				
		</td>
	</tr>
	<tr align="center">
		<td style="padding:0px 10px;" id="index">
			<a href="${path }">HOME</a>
		</td>
		<td style="padding:0px 10px;" id="member">
			<a href="${path }/member/list">회원관리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="board">
			<a href="${path }/board/list">게시판</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="gallery">
			<a href="${path }/board/listGallery">갤러리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="boardchk">
			<a href="${path }/boardChk/list">게시판관리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="shopProduct">
			<a href="${path }/shopProduct/list">상품관리</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="shopVendor">
			<a href="${path }/shopVendor/list">제조사관리</a>
		</td>		
		<td>
			<td style="padding:0px 10px;" id="shopMall">
			<a href="${path }/shopMall/list">쇼핑몰</a>
		</td>
		<td>
			<td style="padding:0px 10px;" id="chart">
			<a href="${path }/chart/list">차트</a>
		</td>
	</tr>
</table>