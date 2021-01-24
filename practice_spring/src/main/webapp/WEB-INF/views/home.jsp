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
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. <br> 안녕</P>
			<c:forEach items="${testList}" var="testVO">
                    ${testVO.test}
            </c:forEach>
            <a href="/test/list">list로 이동</a>
            <a href="/test/write">write로 이동</a>
</body>
</html>
 