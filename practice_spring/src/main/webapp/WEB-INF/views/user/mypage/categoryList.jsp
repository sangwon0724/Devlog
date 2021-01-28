<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		#category_iframe {border : none;}
		/* 공간 짤림 방지용 */
		.blank {width: 100%; height: 50px;}
	</style>
	<title>카테고리 관리</title>
</head>
<body>
<!-- 네비게이션 시작 -->
		<c:import url="../../include/navigation.jsp"></c:import>
<!-- 네비게이션 종료 -->
	<section id="content" class="mypageContent">
		<aside class="mypageTask">
			<!-- 작업목록 시작 -->
					<c:import url="../../include/mypageTask.jsp"></c:import>
			<!-- 작업목록 종료 -->
		</aside>
		<section class="mypageMain">
			<iframe id="category_iframe" width="100%" height="auto" scrolling="no" src="/user/mypage/categoryView"></iframe>
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
</body>
</html>