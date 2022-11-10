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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h2>회원가입</h2>
<form name="DirForm">
	<table border="1" width="60%" style="margin-bottom:30px;">
		<tr>
			<td align="center" style="font-weight:bold;" rowspan="2">아이디</td>
			<td>
				<input type="text" name="id" id="id">
				<button type="button" id="idChk">중복체크</button>
			</td>
		<tr>
			<td colspan="2">
			<div id="result">&nbsp;</div>
			</td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">비밀번호확인</td>
			<td><input type="password" name="passwdChk"></td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">닉네임</td>
			<td><input type="text" name="nickname"></td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">연락처</td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">이메일</td>
			<td>
				<input type="text" name="email1">
				@
				<input type="text" name="input_email" id="input_email" disabled value="선택하세요">
				<select name="email2" id="email2">
				<option value="gmail.com">gmail.com</option>
				<option value="naver.com">naver.com</option>
				<option value="daum.net">daum.net</option>
				<option value="nate.com">nate.com</option>
				<option value="outlook.com">outlook.com</option>
				<option value="1">직접입력</option>			
				</select>
			</td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">주민번호</td>
			<td><input type="text" name="jumin1" style="width:100px;"> - <input type="text" maxlength="1" name="jumin2" style="width:8px;">******
			</td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">주소</td>
			<td>
				<input type="text" id="sample6_postcode" placeholder="우편번호" name="juso1">
				<input type="button" onClick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>		
				<input type="text" id="sample6_address" placeholder="주소" name="juso2"><br>
				<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="juso3">
				<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="juso4">
			</td>
		</tr>	
		<tr>
			<td align="center" style="font-weight:bold;">회원등급</td>
			<td>
				<input type="radio" name="grade" value="최우수회원">최우수회원&nbsp;
				<input type="radio" name="grade" value="우수회원">우수회원&nbsp;
				<input type="radio" name="grade" value="일반회원" checked>일반회원&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center" style="font-weight:bold;">첨부파일</td>
			<td>
				<input type="file" name="file"><br>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onClick="save();">확인</button>	
			</td>
		</tr>
	</table>
</form>
</body>
</html>
<script>
	function save() {
		if(confirm('저장할까요?')) {
			document.DirForm.enctype = "multipart/form-data";
			document.DirForm.action="chugaAttachProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
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
  	$("#idChk").click(function(){
  		let id = $("#id").val();
  		
  		$.ajax({
  			url : "idChk",
  			type : "post",
  			data : {id: id},
  			dataType : 'json',
  			success : function(result) {
  				if(result == 1) {
  					$("#result").html("이미 사용중인 아이디입니다.");
  					$("#result").css('color','red');
  				} else {
  					$("#result").html("사용할 수 있는 아이디입니다.");
  					$("#result").css('color','blue');
  				}
  			},
  			error : function() {
  				alert("서버요청실패");
  			}
  		});
  	});
  	$("#email2").change(function(){
  		$("#email2 option:selected").each(function () {
	  		if($(this).val()== '1') {
	  			$("#input_email").val('');
	  			$("#input_email").attr("disabled",false);
	  		} else {
	  			$("#input_email").val($(this).text());
	  			$("#input_email").attr("disabled",true);
	  		}			
  		});
  	});
</script>