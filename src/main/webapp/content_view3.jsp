<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String mId = (String) session.getAttribute("mId");
String bName = (String) session.getAttribute("bName");
%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.js"></script>
<title>글 내용 보기</title>
</head>
<script>
	function submit_ajax3() {
		var queryString = $("#submitForm2").serialize();
		$.ajax({
			url : 'submitLike2.do',
			type : 'POST',
			data : queryString,
			dataType : 'text',
			success : function(json) {
				//console.log(json);
				var result = JSON.parse(json);
				if (result.code == "success") {
					alert(result.desc)
				} else {
					alert(result.desc);
				}
			}
		});
	}
</script>

<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<nav class="navbar navbar-expand-lg navbar navbar navbar-dark bg-dark">
			<a class="navbar-brand" href="list.do">Cookies</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="announce_list.do">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="free_list.do">자유게시판</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="archive_list.do">자료실</a></li>
				</ul>
			</div>
		</nav>
	</table>
	<table width="500" cellpadding="0" cellspacing="0" border="1">

		<tr>
			<td>번호</td>
			<td>${content_view3.bId}</td>
		</tr>
		<tr>
			<td>조회</td>
			<td>${content_view3.bHit}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${content_view3.bName}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${content_view3.bTitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${content_view3.bContent}</td>
		</tr>
		<tr>
			<td>파일</td>
			<td><a href="download?fname=${content_view3.filename}"
				class="btn btn-primary">📁: <span id="dw">${content_view3.filename}</span></a>
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<form id="submitForm2" name="submitForm2">
					<input type="hidden" id="mId" name="mId"
						value="<%=(String) session.getAttribute("mId")%>"> <input
						type="hidden" id="bId" name="bId" value="${content_view3.bId}">
				</form> 
				<%
				 if (mId.equals(bName)) {
				%>
				 <a href="modify_view3.do?bId=${content_view3.bId}">수정</a>&nbsp;&nbsp;
				<a href="archive_list.do?page=<%=session.getAttribute("cpage")%>">목록보기</a>&nbsp;&nbsp;
				<a href="delete3.do?bId=${content_view3.bId}">삭제</a>&nbsp;&nbsp;
				<%
 					} else {
				 %>
				<a href="archive_list.do?page=<%=session.getAttribute("cpage")%>">목록보기</a>&nbsp;&nbsp;
				<button type="button" onclick="submit_ajax3()">좋아요</button> 
				<%
 					}
 				%>
			</td>
		</tr>
	</table>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>