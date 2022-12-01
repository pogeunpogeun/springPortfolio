<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "../_include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>
<body>
<!-- 
<table border="1" align="center">
   <tr>
      <td align="center" style="pdding:20px 20px;"> -->
         <jsp:include page ="../_include/inc_menu.jsp" />
<!--       </td>
   </tr>
   <tr> -->
<!--       <td align="center" style="pdding:20px 20px;"> -->

          <jsp:include page="../${folderName }/${fileName }.jsp" />

<!--       </td>
   </tr>
   <tr>
      <td align="center" style="pdding:20px 20px;"> -->
         <jsp:include page ="../_include/inc_bottom.jsp" />
<!--       </td>
   </tr>
</table> -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</body>
</html>