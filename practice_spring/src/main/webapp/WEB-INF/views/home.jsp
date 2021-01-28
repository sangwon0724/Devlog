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
	<section id="content" class="boardContent">
		<aside class="boardList">
			<!-- 카테고리 목록 시작 -->
				<iframe id="category_iframe" width="100%" height="auto" scrolling="yes" src="/include/boardList"></iframe>
			<!-- 카테고리 목록 종료 -->
		</aside>
		<section class="homeMain">
			메인 홈페이지 내용
		</section>
	</section>
	
	<script type="text/javascript">
		var iframe = document.getElementById('category_iframe');
	
		window.addEventListener('DOMContentLoaded', function () {
			iframe.addEventListener('load', autoHeight);
		});
	
		function autoHeight() {
			var frame = iframe;
		    var sub = frame.contentDocument ? frame.contentDocument : frame.contentWindow.document;
		    iframe.height = sub.body.scrollHeight;
		}
	</script>
	<c:if test="${result eq 'onlyManager'}">
		<script>
			setTimeout("timer_alert()", 500);
			function timer_alert(){
				alert("관리자용 페이지입니다.");
			}
		</script>
	</c:if>
</body>
</html>
 