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
<h2>회원수정</h2>
<form name="DirForm">
<input type="hidden" name="no" value="${dto.no }">
<table border="1">
<tr>
		<td align="center" style="font-weight:bold;">순번</td>
		<td>${dto.no }</td>
		<td rowspan="7" style="width:200px; height: 250px;">
			<c:choose>
					<c:when test="${dto.attachInfo == '-' }">
						&nbsp;
					</c:when>
					<c:otherwise>
						<c:set var="tempArray1" value="${fn:split(dto.attachInfo, ',') }" />
						<c:set var="temp1" value="${tempArray1[0] }" />
						<c:set var="temp2" value="${tempArray1[1] }" />
						<img src="/springPortfolio/attach/springPortfolio/member/${tempArray1[1] }" alt="${tempArray1[0] }" title="" style="width:100p; height:200px;">
					</c:otherwise>
			</c:choose>	
		</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">아이디</td>
		<td>${dto.id }</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">닉네임</td>
		<td><input type="text" name="nickname" value="${dto.nickname }"></td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">비밀번호</td>
		<td><input type="text" name="passwd"></td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">연락처</td>
		<td><input type="text" name="phone" value="${dto.phone }"></td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">이메일</td>
		<td><input type="text" name="email" value="${dto.email }"></td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">주민번호</td>
		<td>${fn:substring(dto.jumin, 0, 6)} - ${fn:substring(dto.jumin, 6, 7)}******</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">주소</td>
		<td colspan="2">
			<input type="text" id="sample6_postcode" placeholder="우편번호" name="juso1" value="${dto.juso1 }">
			<input type="button" onClick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>		
			<input type="text" id="sample6_address" placeholder="주소" name="juso2" value="${dto.juso2 }"><br>
			<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="juso3" value="${dto.juso3 }">
			<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="juso4" value="${dto.juso4 }">
		</td>
	</tr>	
	<tr>
	<tr>
		<td align="center" style="font-weight:bold;">회원등급</td>
		<td colspan="2">
			<c:if test="${dto.grade== '최우수회원'}">
			<input type="radio" name="grade" value="최우수회원" checked>최우수회원&nbsp;
			<input type="radio" name="grade" value="우수회원">우수회원&nbsp;
			<input type="radio" name="grade" value="일반회원">일반회원&nbsp;
			</c:if>
			<c:if test="${dto.grade== '우수회원'}">
			<input type="radio" name="grade" value="최우수회원">최우수회원&nbsp;
			<input type="radio" name="grade" value="우수회원" checked>우수회원&nbsp;
			<input type="radio" name="grade" value="일반회원">일반회원&nbsp;
			</c:if>
			<c:if test="${dto.grade== '일반회원'}">
			<input type="radio" name="grade" value="최우수회원">최우수회원&nbsp;
			<input type="radio" name="grade" value="우수회원">우수회원&nbsp;
			<input type="radio" name="grade" value="일반회원" checked>일반회원&nbsp;
			</c:if>
		</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold;">등록일</td>
		<td colspan="2">${dto.regiDate }</td>
	</tr>
	<tr>
		<td colspan="3" align="center">
			<button type="button" onClick="save();">확인</button>	
		</td>
	</tr>
</table>
</form>
<div style="border: 0px solid red; width: 438px; margin-top: 10px; margin-bottom:20px;" align="right">
|
<a href="#" onClick="move2('list','');">전체목록</a>
|
<a href="#" onClick="move2('chuga','');">등록</a>
|
</div>
</body>
</html>
<script>
	function save() {
		if(confirm('수정할까요?')) {
			document.DirForm.enctype = "multipart/form-data";
			document.DirForm.action="sujungProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
	function move(value1, value2) {
		location.href = value1 + "?no=" + value2;
	}
	function move2(value1, value2) {
		location.href = value1 + "?no=" + ${no};
	}
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>