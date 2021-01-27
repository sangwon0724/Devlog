<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<title>마이페이지</title>
</head>
<body>
<!-- 네비게이션 시작 -->
		<c:import url="../../include/navigation.jsp"></c:import>
<!-- 네비게이션 종료 -->
	<section id="content" class="mypageContent">
		<aside class="mypageTask">
			<!-- 작업목록 시작 -->
					<c:import url="../../include/mypageTask.jsp"></c:import>
			<!-- 작업목록 종료 -->
		</aside>
		<section class="mypageMain">
		</section>
	</section>
</body>
</html>