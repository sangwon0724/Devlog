<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    	<fmt:parseNumber var="currentPage" value="${currentPage}"/>
    <!-- 한 페이지당 게시글 수 -->
    	<fmt:parseNumber var="postCountOnePage" value="1"/>
    <!-- 한 챕터당 페이지 수 -->
    	<fmt:parseNumber var="pageCountOneChapter" value="10"/>
    <!-- 전체 게시글 수 -->
    	<fmt:parseNumber var="totalPostCount" value="${totalPostCount}"/>
    	
    <!-- 현재 챕터 -->
    	<fmt:parseNumber var="currentChapter" value="${(currentPage-1)/pageCountOneChapter + 1}" type="pattern" pattern="0" integerOnly="true"/>
    <!-- 전체페이지 수 -->
    	<c:if test="${totalPostCount%postCountOnePage==0}">
    		<fmt:parseNumber var="totalPageCount" value="${totalPostCount/postCountOnePage}" type="pattern" pattern="0" integerOnly="true"/>
    	</c:if>
    	<c:if test="${totalPostCount%postCountOnePage!=0}">
    		<fmt:parseNumber var="totalPageCount" value="${(totalPostCount/postCountOnePage)+1}" type="pattern" pattern="0" integerOnly="true"/>
    	</c:if>
    <!-- 전체 챕터 수  -->
    	<c:if test="${totalPageCount%pageCountOneChapter==0}">
    		<fmt:parseNumber var="totalChapter" value="${totalPageCount/pageCountOneChapter}" type="pattern" pattern="0" integerOnly="true"/>
    	</c:if>
    	<c:if test="${totalPageCount%pageCountOneChapter!=0}">
    		<fmt:parseNumber var="totalChapter" value="${(totalPageCount/pageCountOneChapter)+1}" type="pattern" pattern="0" integerOnly="true"/>
    	</c:if>
    <!-- 페이지 생성 시작값과 종료 값-->
    	<c:if test="${currentPage!=totalPageCount}">
    		<fmt:parseNumber var="loopStart" value="${(currentPage-1)*postCountOnePage}"/>
    		<fmt:parseNumber var="loopEnd" value="${currentPage*postCountOnePage-1}"/>
    	</c:if>
    	<c:if test="${currentPage==totalPageCount}">
    		<fmt:parseNumber var="loopStart" value="${(currentPage-1)*postCountOnePage}"/>
    		<fmt:parseNumber var="loopEnd" value="${totalPostCount-1}"/>
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
				<c:forEach items="${boardList}" var="boardList" begin="${loopStart}" end="${loopEnd}">
			    	<a href="/board/view?no=${boardList.no}">제목 : ${boardList.name}</a><br>
			    </c:forEach>
			        현재 페이지 : ${currentPage}<br>
				한 페이지당 게시글 수 : ${postCountOnePage}<br>
				한 챕터당 페이지 수 : ${pageCountOneChapter}<br>
				전체 게시글 수 : ${totalPostCount}<br>
				
				현재 챕터 : ${currentChapter}<br>
				전체페이지 수 : ${totalPageCount}<br>
				전체 챕터 수 : ${totalChapter} = (${totalPageCount}/${pageCountOneChapter})+1<br>
				페이지 생성 시작값 : ${loopStart}<br>
				페이지 생성 종료값 : ${loopEnd}<br>
			</main>
			<footer class="page">
				<form name="page__form" method="get">
					<input name="category" type="hidden" value="${param.category}">
					<input name="page" type="hidden" value="">
					<!-- 총 페이지 수가 한 챕터당 페이지 수보다 작거나 같은 경우 -->
					<c:if test="${totalPageCount<=pageCountOneChapter}">
					      <c:forEach var="i" begin="1" end="${totalPageCount}" step="1">
								<div onclick="setPage(${i})">${i}</div>
					      </c:forEach>
					</c:if>
					<!-- 총 페이지 수가 한 챕터당 페이지 수보다 큰 경우 -->
					<c:if test="${totalPageCount>pageCountOneChapter}">
						<!-- 챕터가 1인 경우 -->
						<c:if test="${currentChapter == 1}">
					      		<c:forEach var="i" begin="1" end="10" step="1">
									<div onclick="setPage(${i})">${i}</div>
					      		</c:forEach>
								<div onclick="setPage(11)">다음</div>
						</c:if>
						<!-- 챕터가 1보다 크고 현재 챕터가 전체 챕터보다 낮은 경우 -->
						<c:if test="${currentChapter>1 && currentChapter<totalChapter}">
								<div onclick="setPage(${pageCountOneChapter*(currentChapter-1)})">이전</div>
					      		<c:forEach var="i" begin="${(currentChapter-1)*pageCountOneChapter+1}" end="${currentChapter*pageCountOneChapter}" step="1">
									<div onclick="setPage(${i})">${i}</div>
					      		</c:forEach>
								<div onclick="setPage(${pageCountOneChapter*currentChapter+1})">다음</div>
						</c:if>
						<!-- 챕터가 1보다 크고 전체 챕터인 경우 -->
						<c:if test="${currentChapter>1 && currentChapter==totalChapter}">
								<div onclick="setPage(${pageCountOneChapter*(currentChapter-1)})">이전</div>
					      		<c:forEach var="i" begin="${(currentChapter-1)*pageCountOneChapter+1}" end="${totalPageCount}" step="1">
									<div onclick="setPage(${i})">${i}</div>
					      		</c:forEach>
						</c:if>
					</c:if>
				</form>
			</footer>
		</section>
	</section>
	<script type="text/javascript">
		function goWrite(category){
			location.href="/board/write?category="+category;
		}
		
		function setPage(pageNumber){
			page__form.page.value=pageNumber;
			page__form.submit();
		}
	</script>
</body>
</html>