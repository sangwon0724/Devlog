<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>test 테이블 - view</title>
</head>
<body>
    <c:out value="${testVO.test}" escapeXml="false"/>
    <a href="./write_${testVO.no}"><input type="button" value="write로 이동 (update)"></a>
    <a href="./delete_${testVO.no}"><input type="button" value="게시글 삭제"></a>
</body>
</html>