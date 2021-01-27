<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<html>
<head>
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<title>스프링 연습</title>
</head>
<body>
<!-- 네비게이션 시작 -->
		<c:import url="./include/navigation.jsp"></c:import>
<!-- 네비게이션 종료 -->
	<section id="content">
		<c:if test="${result eq 'onlyManager'}">
			<script>
				setTimeout("timer_alert()", 500);
				function timer_alert(){
					alert("관리자용 페이지입니다.");
				}
			</script>
		</c:if>
			<h1>
				Hello world!  
			</h1>
			
			<P>  The time on the server is ${serverTime}. <br> 안녕</P>
			<c:forEach items="${testList}" var="testVO">
                    ${testVO.test}
            </c:forEach><br>
            <a href="/test/list">/test/list로 이동</a><br>
            <a href="/test/write">/test/write로 이동</a>
	</section>
</body>
</html>
 