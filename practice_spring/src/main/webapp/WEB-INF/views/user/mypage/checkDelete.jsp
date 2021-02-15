<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>카테고리 삭제 확인</title>
<style type="text/css">
	body {display: flex; flex-direction : column; justify-content: space-around; align-items: center; padding-top: 20vh;}
	.flexbox{display: flex; flex-direction : column; justify-content: center; align-items: center; margin-top: 10vh; color: red;}
</style>
</head>
<body>
	<div>
		${param.categoryNameKor}을 삭제하시겠습니까?&emsp;
		<input type="button" value="삭제하기" onclick="goDelete()">&emsp;
		<input type="button" value="취소하기" onclick="windowclose()">
	</div>
	<div class="flexbox">
		<span>해당 팝업창을 통해서 삭제하거나 종료하지 않고</span>
		<span>카테고리 리스트창에서 리스트의 순서를 변경 후</span>
		<span>삭제시 삭제 문제가 발생하게 되니 조심하기</span>
		<span>(ex : 19를 삭제하는데 18이랑 19를</span>
		<span>순서 변경시 18이 19되서 기존 18을 삭제)</span>
	</div>
	
	<script type="text/javascript">
		function goDelete(){
			opener.deleteForm${param.orderNo}.submit();
			window.close();
		}
		function windowclose(){
			window.close();
		}
	</script>
</body>
</html>