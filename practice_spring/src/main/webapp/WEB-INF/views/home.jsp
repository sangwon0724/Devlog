<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>스프링 연습</title>
</head>
<body>
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
 