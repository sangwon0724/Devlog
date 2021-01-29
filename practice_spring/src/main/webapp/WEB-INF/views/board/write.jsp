<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/js/summernote/summernote-lite.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/summernote/summernote-lite.css">
	
	<link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
		function uploadImageFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			
			$.ajax({
				data : data,
				type : "POST",
				url : "./image",	
				dataType:'json',
			    async: true,
				processData: false,		
				contentType: false,				
				success : function(data) {
			    	//항상 업로드된 파일의 url이 있어야 한다.
			    	alert("업로드에 지연시간이 있습니다..");
			    	setTimeout(function(){
			    		   // 1초 후 작동해야할 코드
			    		alert(data.url);
			    		$(editor).summernote('insertImage', data.url);
			    		/*if(document.getElementById('sumnail').value=="null"){
			    			document.getElementById('sumnail').value=data.url;	
			    		}*/
			    		
			    		   }, 1000);  	
			    	
				},
				error:function(request,status,error){			
					alert("code = "+ request.status);
					alert(" message = " + request.responseText);
					alert(" error = " + error);			 	
					$(editor).summernote('insertImage', '');
				}
			
			});
			}
			
		$(document).ready(function() {
			$('#summernote').summernote({
				height: 300,                 // 에디터 높이
				minHeight: null,             // 최소 높이
				maxHeight: null,             // 최대 높이
				focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
				lang: "ko-KR",					// 한글 설정
				placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
				callbacks: {	//여기 부분이 이미지를 첨부하는 부분
					onImageUpload : function(files) {
						uploadImageFile(files[0],this);				
					},
					onPaste: function (e) {
						var clipboardData = e.originalEvent.clipboardData;
						if (clipboardData && clipboardData.items && clipboardData.items.length) {
							var item = clipboardData.items[0];
							if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
								e.preventDefault();
							}
						}
					}
				}
		});
		
		});
	</script>
	<style type="text/css">
		.note-toolbar>div>div>button>i{color: black;}
		.note-toolbar>div>div>button>span{color: black;}
		.note-toolbar>div>button>i{color: black;}
		.temp {width: 200px; height: 100px; border: 2px solid black;}
	</style>
	<title>게시글 작성</title>
</head>
<body>
<!-- 네비게이션 시작 -->
		<c:import url="../include/navigation.jsp"></c:import>
<!-- 네비게이션 종료 -->
<section id="content" class="boardContent">
		<section class="boardWrite">
			<form method="post" name="write__form" enctype="multipart/form-data" accept-charset="utf-8">
				<c:if test="${not empty boardVO.no}">
					<input type="hidden" name="no" value=${boardVO.no}>
				</c:if>
				<c:if test="${empty boardVO.category}">
					<input type="hidden" name="category" value=${param.category}>
				</c:if>
				<c:if test="${not empty boardVO.category}">
					<input type="hidden" name="category" value=${boardVO.category}>
				</c:if>
				
				<input type="text" name="name" value="<c:out value="${boardVO.name}" escapeXml="false"/>" placeholder="제목을 입력해주세요."><br/>
				<textarea id="summernote" name="text" class="editordata">${boardVO.text}</textarea>
				<div class="temp" onclick="write_submit()">등록</div>
	</form>
    <a href="/">home으로 이동</a>
		</section>
	</section>
    
	<script>
		function write_submit(){
			var name = write__form.name.value;
			var text = write__form.text.value;
			if (name=="")
			{
				alert("게시글의 제목을 입력해주세요");
				write__form.name.focus();
				return;
			}
			if (name!="" && text=="")
			{
				alert("게시글의  내용을 입력해주세요");
				write__form.text.focus();
				return;
			}
			if (name!="" && text!="")
			{
				write__form.submit();
			}
		}
	</script>
</body>
</html>