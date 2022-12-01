<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../_include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Anton&display=swap');
	@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@300&display=swap');
	* {font-family: 'IBM Plex Sans KR', sans-serif;}
/* 	table {border-collapse :  collapse;}	
	a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: black; text-decoration: underline;} */
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="container px-4 px-lg-5">
            <!-- Heading Row-->
            <div class="row gx-4 gx-lg-5 align-items-center my-5">
                <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="https://i.ibb.co/TWTHQ4J/main-Image.jpg" alt="..." /></div>
                <div class="col-lg-5">
                    <h1 class="font-weight-light">Spring Project</h1>
                    <p>Spring을 통해 구현한 회원관리, 게시판 프로젝트입니다. 단위 프로그램마다 공통기능 · 추가기능을 구현하여 쓰임에 불편함이 없도록 구현했습니다. 
                    추가적으로 학습 후, 지속적으로 업데이트 할 예정입니다. </p>
                    <a class="btn btn-primary" href="https://github.com/pogeunpogeun">Visit GitHub</a>
                </div>
            </div>
			<form name="DirForm">
            <!-- Call to Action-->
            <div class="card text-white bg-secondary my-5 py-4 text-center">
                <div class="card-body"> 
                <c:choose>
			<c:when test="${sessionScope.sessionNo == null || sessionScope.sessionNo == '' || sessionScope.sessionNo == 0}">
				서비스 이용하기&nbsp;&nbsp;(Log-In)${sessionNo }
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" name="id" placeholder="id">             
                <input type="password" name="passwd" placeholder="password">
                <button type="button" class="btn btn-primary btn-sm"  onClick="login();">Log-In</button>
			</c:when>
			<c:otherwise>
				이제 모든 서비스를 이용하실 수 있습니다.<br>
				Spring Framework Project 
			</c:otherwise>
		</c:choose>	
		</form>		             
                </div>
            </div>
            <!-- Content Row-->
            <div class="row gx-4 gx-lg-5">
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Member</h2>
                            <p class="card-text">
                           		DB에 등록된 정보를 이용한 로그인을 통해 가져오는 세션정보를 활용,
                           		회원정보 등록,수정,삭제, 검색, 페이징 기능을 구현했습니다. 또한 DB와 연동시
                           		보안을 위한 암호화, 중복방지 기능을 구현하였습니다.
                            </p>
                        </div>
                        
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Board</h2>
                            <p class="card-text">
                            	기본적인 게시글 등록, 수정, 삭제, 검색, 페이징 기능과 선택에 따른 공지글, 비밀글 등록
                            	기능을 구현했습니다. 그리고 기존 등록 된 글에 대한 답변글과 게시글마다 개별적으로 등록 가능한
                            	덧글, 해당 게시글의 조회수 중복방지 기능을 구현했습니다. 
                          
                            </p>
                        </div>
                        
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Gallery</h2>
                            <p class="card-text">
                            	기존의 게시판에서 첨부파일에 조금 더 포커스가 맞춰진 게시판입니다. 
                            	 목록에서 이미지를 노출시키고,
                            	상세보기를 통해 첨부 된 텍스트를 읽을 수 있게 구현했습니다. 
                            </p>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
</body>
</html>

<script>
	function login() {
		if(confirm('로그인할까요?')) {
			document.DirForm.action="${path}/member/loginProc";
			document.DirForm.method="post";
			document.DirForm.submit();
		}
	}
</script>