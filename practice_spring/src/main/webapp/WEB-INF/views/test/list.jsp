<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
	.line{width:600px; height: 150px; border: 5px solid black;}
</style>
<title>test 테이블의 리스트</title>
</head>
<body>
	<c:forEach items="${testList}" var="testVO">
         <div class="line">
             <a href="./view?no=${testVO.no}">${testVO.test}</a>
         </div>
         <br/>
    </c:forEach>
</body>
</html>