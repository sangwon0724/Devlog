<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
<style type="text/css">
	a {margin: 10px auto 0 auto;}
</style>
<title>게시판 목록</title>
</head>
<body>
	<br>
	<c:forEach items="${categoryList}" var="categoryVO">
    	<a href="javascript:void(0)" onclick="goBoard('${categoryVO.categoryName}')"
    	<c:if test="${param.category ne categoryVO.categoryName}"> class="notThisBoard"</c:if>
    	>${categoryVO.categoryNameKor} (${categoryVO.postCount})</a><br><br>
    </c:forEach>
	<div class="blank"></div>  <!-- 공간 짤림 방지용 -->
    <script>
    	function goBoard(boardName){
    		window.parent.location.href="/board/list?category="+boardName
    	}
    </script>
</body>
</html>