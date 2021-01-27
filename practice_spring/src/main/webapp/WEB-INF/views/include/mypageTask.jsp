<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>마이페이지 작업 목록</title>
</head>
<body>
	<div class="task changePW<c:if test="${myTask eq 'changePW'}"> selectTask</c:if>" onclick="goChangePW()">
		<span>비밀번호 변경</span>
	</div>
	<div class="task categoryList<c:if test="${myTask eq 'categoryList'}"> selectTask</c:if>" onclick="goCategoryList()">
		<span>카테고리 관리</span>
	</div>
	<div class="task categoryAdd<c:if test="${myTask eq 'categoryAdd'}"> selectTask</c:if>" onclick="goCategoryAdd()">
		<span>카테고리 추가</span>
	</div>
	<script type="text/javascript">
		function goChangePW(){
			location.href="/user/mypage/changePW";
		}
		function goCategoryList(){
			location.href="/user/mypage/categoryList";
		}
		function goCategoryAdd(){
			location.href="/user/mypage/categoryAdd";
		}
	</script>
</body>
</html>