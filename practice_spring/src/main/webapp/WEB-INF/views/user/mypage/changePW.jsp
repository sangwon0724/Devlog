<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<title>비밀번호 변경</title>
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
			<form name="changePW__form" method="post">
			<input type="password" name="pw">
			<input type="password" name="pwcheck">
			<div class="temp" onclick="changePW_submit()">비밀번호 변경</div>
		</form>
		</section>
    
	</section>
	<script>
		function changePW_submit(){
			var pw = changePW__form.pw.value;
			var pwcheck = changePW__form.pwcheck.value;
			if (pw=="")
			{
				alert("변경할 비밀번호를 입력해주세여");
				changePW__form.pw.focus();
				return;
			}
			if(pw!="" && pwcheck==""){
				alert("재확인 비밀번호를 입력해주세여");
				changePW__form.pwcheck.focus();
				return;
			}
			if(pw!="" && pwcheck!="" && pw!=pwcheck){
				alert("입력하신 두 비밀번호의 값이 다릅니다. 다시 입력해주세요.");
				changePW__form.pwcheck.value="";
				return;
			}
			if(pw!="" && pwcheck!="" && pw==pwcheck){
				changePW__form.submit();
			}
		}
	</script>
    
		<c:if test="${result eq 'success'}">
			<script>
				setTimeout("timer_alert()", 500);
				function timer_alert(){
					alert("비밀번호 변경에 성공했습니다.");
				}
			</script>
		</c:if>
		<c:if test="${result eq 'fail'}">
			<script>
				setTimeout("timer_alert()", 500);
				function timer_alert(){
					alert("비밀번호 변경에 실패했습니다.");
				}
			</script>
		</c:if>
</body>
</html>