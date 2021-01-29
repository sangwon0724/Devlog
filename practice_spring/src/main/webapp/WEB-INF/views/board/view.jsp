<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<title>게시글 보기(categoryNameKor으로 변경)</title>
</head>
<body>
<!-- 네비게이션 시작 -->
		<c:import url="../include/navigation.jsp"></c:import>
<!-- 네비게이션 종료 -->
<section id="content" class="boardContent">
		<aside class="boardList">
			<!-- 카테고리 목록 시작 -->
				<iframe id="category_iframe" width="100%" height="auto" scrolling="yes" src="../include/boardList?category=${boardVO.category}"></iframe>
			<!-- 카테고리 목록 종료 -->
		</aside>
		<section class="boardMain">
			내용 : ${boardVO.text}<br>
			<a href="./write_${boardVO.no}?category=${boardVO.category}">
				<input type="button" value="/boardVO/write로 이동 (update)">
			</a>
			<br>
    		<a href="./delete_${boardVO.no}?category=${boardVO.category}"><input type="button" value="게시글 삭제"></a>
		</section>
	</section>
</body>
</html>