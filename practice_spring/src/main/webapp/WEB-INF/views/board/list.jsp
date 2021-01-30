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

	<!-- 현재 페이지 -->
    	<c:set var="currentPage" value="${currentPage}"/>
    <!-- 한 페이지당 게시글 수 -->
    	<c:set var="postCountOnePage" value="10"/>
    <!-- 한 챕터당 페이지 수 -->
    	<c:set var="pageCountOneChapter" value="10"/>
    <!-- 전체 게시글 수 -->
    	<c:set var="totalPostCount" value="${totalPostCount}"/>
    	
    <!-- 현재 챕터 -->
    	<c:set var="currentChapter" value="${(currentPage-1) div pageCountOneChapter + 1}"/>
    <!-- 전체페이지 수 -->
    	<c:if test="${totalPostCount%postCountOnePage==0}">
    		<c:set var="totalPageCount" value="${totalPostCount div postCountOnePage}"/>
    	</c:if>
    	<c:if test="${totalPostCount%postCountOnePage!=0}">
    		<c:set var="totalPageCount" value="${totalPostCount div postCountOnePage+1}"/>
    	</c:if>
    <!-- 전체 챕터 수  -->
    	<c:if test="${totalPageCount%pageCountOneChapter==0}">
    		<c:set var="totalPageCount" value="${totalPageCount div pageCountOneChapter}"/>
    	</c:if>
    	<c:if test="${totalPageCount%pageCountOneChapter!=0}">
    		<c:set var="totalPageCount" value="${totalPageCount div pageCountOneChapter+1}"/>
    	</c:if>
    <!-- 페이지 생성 시작값과 종료 값-->
    	<c:if test="${currentPage!=totalPageCount}">
    		<c:set var="loopStart" value="${(currentPage-1)*postCountOnePage}"/>
    		<c:set var="loopEnd" value="${currentPage*postCountOnePage-1}"/>
    	</c:if>
    	<c:if test="${currentPage==totalPageCount}">
    		<c:set var="loopStart" value="${(currentPage-1)*postCountOnePage}"/>
    		<c:set var="loopEnd" value="${totalPostCount-1}"/>
    	</c:if>
    	
    
<section id="content" class="boardContent">
		<aside class="boardList">
			<!-- 카테고리 목록 시작 -->
				<iframe id="category_iframe" width="100%" height="auto" scrolling="yes" src="../include/boardList?category=${category}"></iframe>
			<!-- 카테고리 목록 종료 -->
		</aside>
		<section class="boardMain">
			<header class="write">
				<c:if test="${not empty sessionScope.user.id}">
					<span onclick="goWrite('${category}')" class="cursorPointer">글쓰기 ▶</span>
				</c:if>
			</header>
			<main class="postList">
				게시판 : ${category}<br>
				<c:forEach items="${boardList}" var="boardList" begin="0" end="${totalPostCount}">
			    	<a href="/board/view?no=${boardList.no}">제목 : ${boardList.name}</a><br>
			    </c:forEach>
			        현재 페이지 : ${currentPage}<br>
				한 페이지당 게시글 수 : ${postCountOnePage}<br>
				한 챕터당 페이지 수 : ${pageCountOneChapter}<br>
				전체 게시글 수 : ${totalPostCount}<br>
				
				현재 챕터 : ${currentChapter}<br>
				전체페이지 수 : ${totalPageCount}<br>
				전체 챕터 수 : ${totalPageCount}<br>
				페이지 생성 시작값 : ${loopStart}<br>
				페이지 생성 종료값 : ${loopEnd}<br>
			    <!-- var 뒤에 begin="0" end="10" 추가하기, 당연히 시작은 0부터-->
			</main>
			<footer class="page">
			</footer>
		</section>
	</section>
	<script type="text/javascript">
		function goWrite(category){
			location.href="/board/write?category="+category;
		}
	</script>
</body>
</html>