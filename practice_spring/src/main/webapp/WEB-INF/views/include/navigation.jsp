<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>네비게이션</title>
</head>
<body>
	<c:choose>
	 	<c:when test="${empty sessionScope.user.id}">
            <div class="navigation noneSession">
            	<div class="nav_logo"><span onclick="goHome()">로고</span></div>
            	<div class="nav_blank"></div>
            	<div class="nav_btnSpace_noneSession"><button onclick="goLogin()">로그인</button></div>
			</div>
        </c:when>
	 	<c:when test="${not empty sessionScope.user.id}">
            <div class="navigation existSession">
            	<div class="nav_logo"><span onclick="goHome()">로고</span></div>
            	<div class="nav_blank"><span>${sessionScope.user.userName} 님 환영합니다.</span></div>
            	<div class="nav_btnSpace_existSession"><button onclick="goMyPage()">마이페이지</button><button onclick="goLogout()">로그아웃</button></div>
			</div>
        </c:when>
	</c:choose>
	<!-- Scripts -->
	<script src="<c:url value="/resources/js/navigation.js" />"></script>
</body>
</html>