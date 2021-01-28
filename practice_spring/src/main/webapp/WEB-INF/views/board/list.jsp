<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<title>게시글 목록</title>
</head>
<body>
<!-- 네비게이션 시작 -->
		<c:import url="../include/navigation.jsp"></c:import>
<!-- 네비게이션 종료 -->
<section id="content" class="boardContent">
		<aside class="boardList">
			<!-- 카테고리 목록 시작 -->
				<iframe id="category_iframe" width="100%" height="auto" scrolling="yes" src="../include/boardList"></iframe>
			<!-- 카테고리 목록 종료 -->
		</aside>
		<section class="boardMain">
			<header class="write">
				<c:if test="${not empty sessionScope.user.id}">
					<span onclick="goWrite('${category}')">글쓰기 ▶</span>
				</c:if>
			</header>
			<main class="postList">
				게시판 : ${category}
			</main>
		</section>
	</section>
	<script type="text/javascript">
		function goWrite(category){
			location.href="/board/write?category="+category;
		}
	</script>
</body>
</html>