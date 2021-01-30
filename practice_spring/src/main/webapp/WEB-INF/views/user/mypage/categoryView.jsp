<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>카테고리 리스트 뷰</title>
<style type="text/css">
	/* 화면 잘리는 거 방지용 */
	.blank {width: 100%; height: 50px;}
	.category_view_line {width: 100%; height: 50px; display: flex; justify-content: space-between; align-items: center; background-color: red;}
</style>
</head>
<body>
	<c:forEach items="${categoryList}" var="categoryVO">
		<div class="category_view_line">
			${categoryVO.categoryNameKor}
			<form method="post" action="./categoryView">
				<input type="hidden" name="category_function" value="moveUp">
				<input type="hidden" name="orderNo" value=${categoryVO.orderNo}>
				<input type="submit" value="🔼">
			</form>
			<form method="post" action="./categoryView">
				<input type="hidden" name="category_function" value="moveDown">
				<input type="hidden" name="orderNo" value=${categoryVO.orderNo}>
				<input type="submit" value="🔽">
			</form>
			<form method="post" action="./categoryView">
				<input type="hidden" name="category_function" value="deleteList">
				<input type="hidden" name="orderNo" value=${categoryVO.orderNo}>
				<input type="hidden" name="categoryName" value="${categoryVO.categoryName}">
				<input type="submit" value="❌">
			</form>
		</div>
		<br>
	</c:forEach>
	<div class="blank"></div>  <!-- 공간 짤림 방지용 -->
</body>
</html>