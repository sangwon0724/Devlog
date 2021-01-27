<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<meta charset="utf-8">
<title>카테고리 추가</title>
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
			<form method="post" name="categoryAdd_form" >
				<input type="text" name="categoryName" placeholder="영어로만 작성해주세요."><Br>
				<input type="text" name="categoryNameKor"><Br>
				<div class="temp" onclick="categoryAdd_submit()">등록</div>
			</form>
		</section>
	</section>
	<script>
		function categoryAdd_submit(){
			var categoryName = categoryAdd_form.categoryName.value;
			var categoryNameKor = categoryAdd_form.categoryNameKor.value;
			if (categoryName=="")
			{
				alert("분류코드를 작성해주시길 바랍니다.");
				categoryAdd_form.categoryName.focus();
				return;
			}
			if(categoryName!="" && categoryNameKor==""){
				alert("게시판의 제목을 작성해주시길 바랍니다.");
				categoryAdd_form.categoryNameKor.focus();
				return;
			}
			if(categoryName!="" && categoryNameKor!=""){
				categoryAdd_form.submit();
			}
		}
	</script>
	
	<c:if test="${result eq 'overlap'}">
			<script>
				setTimeout("timer_alert()", 500);
				function timer_alert(){
					alert("이미 존재하는 분류명입니다.");
				}
			</script>
		</c:if>
</body>
</html>