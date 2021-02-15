<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	<meta charset="utf-8">
<title>로그인</title>
</head>
<body>
<!-- 네비게이션 시작 -->
		<c:import url="../include/navigation.jsp"></c:import>
<!-- 네비게이션 종료 -->
	<section id="content">
		<c:if test="${result eq 'fail'}">
			<script>
				setTimeout("timer_alert()", 500);
				function timer_alert(){
					alert("로그인에 실패하였습니다. 잘못 입력한 값이 존재합니다.");
				}
			</script>
		</c:if>
		<c:if test="${result eq 'need'}">
			<script>
				setTimeout("timer_alert()", 500);
				function timer_alert(){
					alert("로그인이 필요한 서비스 입니다.");
				}
			</script>
		</c:if>
		<c:remove var="result" scope="session" />
		<form name="login__form" method="post" class="login__form">
			<div class="loginLine">
				<header><span>아이디</span></header>
				<main><input type="text" name="id"></<main>
			</div>
			<div class="loginLine">
				<header><span>비밀번호</span></header>
				<main><input type="password" name="pw" onkeyup="enterkey()"></<main>
			</div>
			<div class="submitButton" onclick="login_submit()">로그인</div>
		</form>
	</section>
    
	<script>
		function login_submit(){
			var id = login__form.id.value;
			var pw = login__form.pw.value;
			if (!id)
			{
				alert("아이디를 입력해주세요.");
				login__form.id.focus();
				return;
			}
			else
			{
				if (!pw)
				{
					alert("비밀번호를 입력해주세여.");
					login__form.pw.focus();
					return;
				}
				else{
					login__form.submit();
				}
			}
		}
		
		//엔터키가 눌렸을 때  실행
		function enterkey() {
	        if (window.event.keyCode == 13) {
	        	login_submit();
	        }
		}
	</script>
</body>
</html>