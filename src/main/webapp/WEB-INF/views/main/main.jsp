<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "../_include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

<table border="1" align="center">
   <tr>
      <td align="center" style="pdding:20px 20px;">
         <jsp:include page ="../_include/inc_menu.jsp" />
      </td>
   </tr>
   <tr>
      <td align="center" style="pdding:20px 20px;">
          <jsp:include page="../${folderName }/${fileName }.jsp" />
      </td>
   </tr>
   <tr>
      <td align="center" style="pdding:20px 20px;">
         <jsp:include page ="../_include/inc_bottom.jsp" />
      </td>
   </tr>
</table>

</body>
</html>