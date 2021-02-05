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
			<div class="boardView_content">
				<header><span>${boardVO.name}</span></header>
				<main>${boardVO.text}</main>
			</div>
			<c:if test="${empty sessionScope.user.id}">
				<div class="boardView_buttonList">
					<button onclick="goBack()">목록 보기</button>
				</div>
			</c:if>
			<c:if test="${not empty sessionScope.user.id}">
				<div class="boardView_buttonList">
					<button onclick="goBack()">목록 보기</button>
					<button onclick="goUpdate(${boardVO.no},'${boardVO.category}')">게시글 수정</button>
					<button onclick="goDelete(${boardVO.no},'${boardVO.category}')">게시글 삭제</button>
				</div>
			</c:if>
		</section>
	</section>
	
	<script type="text/javascript">
		function goUpdate(no,category){
			location.href="./write_"+no+"?category="+category;
		}
		function goDelete(no,category){
			location.href="./delete_"+no+"?category="+category;
		}
		function goBack(){
			window.history.back();
		}
	</script>
</body>
</html>