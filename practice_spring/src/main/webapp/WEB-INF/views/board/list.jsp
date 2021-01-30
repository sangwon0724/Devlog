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

	<!-- 현제페이지 -->page
    	<c:set var="currentPage" value="${currentPage}"/> <!-- 폼을 통해 넘겨받은 현재페이지 값을 불러온다. -->
    <!-- 한 페이지당 게시글 수 -->listCount
    	<c:set var="postCountOnePage" value="5"/>
    <!-- 한 챕터당 페이지 수 -->listPage
    	<c:set var="pageCountOneChapter" value="10"/>
    <!-- 전체 게시글 수 -->totalCount
    	<c:set var="totalPostCount" value="${totalPostCount}"/>
    <!-- 전체페이지 수 -->totalPage
    	<c:set var="totalPageCount" value="${totalPostCount / postCountOnePage + (1 - ((totalPostCount / postCountOnePage) % 1)) % 1}"/>
    <!-- 페이지 생성 시작값 -->
    	<c:set var="startPage" value="1"/>
    <!-- 페이지 생성 종료값 -->
    	<c:set var="endPage" value="${totalPage}"/>
    
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
			    전체 개시글 개수 : ${totalPostCount}<br>
			    현재 페이지 : ${currentPage}
			    <!-- var 뒤에 begin="0" end="10" 추가하기, 당연히 시작은 0부터-->
			</main>
			<footer class="page">
				<!-- 전체 페이지 수가 페이지 수보다 클 때 -->
			    <c:if test="${totalPage gt listPage}">
			        <c:set var="endPage" value="${listPage}"/>
			    </c:if>
			    
			    <!-- 현재페이지가 페이지 수보다 크거나 같을 때 -->
			    <c:if test="${currentPage ge listPage}">
			        <c:set var="startPage" value="${(currentPage / listPage - (currentPage / listPage) % 1) * listPage}"/>
			        <c:set var="endPage" value="${startPage + listPage}"/>
			        
			        <!-- 현재페이지가 끝 페이지일 때 -->
			        <c:if test="${currentPage ge ((totalPage / listPage - (totalPage / listPage) % 1) * listPage)}">
			            <c:set var="endPage" value="${totalPage}"/>
			        </c:if>
			    </c:if>
			    
			    <!-- 현재페이지가 마지막 페이지일 때 -->
			    <c:if test="${currentPage mod listPage eq 0}">
			        <c:set var="startPage" value="${currentPage}"/>
			        <c:set var="endPage" value="${currentPage + listPage}"/>
			        
			        <c:if test="${(currentPage + listPage) ge totalPage}">
			            <c:set var="endPage" value="${totalPage}"/>
			        </c:if>
			    </c:if>
			    
			    <a href="#" class="p_skip first" onclick="fnPaging(1);">제일처음으로</a>
			    <a href="#" class="p_skip prev" <c:if test="${currentPage gt '1'}">onclick="fnPaging(${currentPage - 1});"</c:if>>이전으로</a>
			    
			    <c:forEach begin="${startPage}" end="${endPage}" step="1" var="nowPage">
			        <c:choose>
			            <c:when test="${nowPage eq currentPage}">
			                <strong onclick="fnPaging(${nowPage});">${nowPage}</strong>
			            </c:when>
			            <c:otherwise>
			                <a href="#" onclick="fnPaging(${nowPage});">${nowPage}</a>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			    
			    <a href="#" class="p_skip next" <c:if test="${currentPage lt totalPage}">onclick="fnPaging(${currentPage + 1});"</c:if>>다음으로</a>
			    <a href="#" class="p_skip end" onclick="fnPaging(${totalPage});">제일마지막으로</a>
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