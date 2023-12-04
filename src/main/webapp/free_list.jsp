<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String mId = (String) session.getAttribute("mId");
String searchWord = (String) session.getAttribute("searchWord");
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

<title>자유게시판</title>
</head>
<script>
	function searchBbs() {

		if ($('#searchword').val().length == 0) {
			alert("검색어를 입력하세요");
			$('#searchword').focus()

			return;
		}

		if ($('#SearchChoice').val() == "bName") {

			document.searchForm.action = "free_list.do";
		}
		if ($('#SearchChoice').val() == "bTitle") {

			document.searchForm.action = "free_list.do";
		}

		document.searchForm.submit();
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

	<div class="container">
    <div class="row justify-content-end">
        <div class="col-4">
          <div class="text-right">
 	 <%=mId%>님 안녕하세요
        
        </div>
        <div class="col-4 text-right">
            <div class="btn-group" role="group">
                <a class="btn btn-dark text-white" href="mContent_view.do">회원 정보 수정</a>&nbsp;&nbsp;
                <a class="btn btn-dark text-white" href="withdraw_view.do">회원 탈퇴</a>&nbsp;&nbsp;
                <a class="btn btn-dark text-white" href="logout.do">로그아웃</a>&nbsp;&nbsp;
            </div>
        </div>
    </div>
</div>
		<h1 class="text-center">자유게시판</h1>
		
		<table class="table table-hover table-sm" width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
		<td scope="col">번호</td>
				<td scope="col">작성자</td>
				<td scope="col">제목</td>
				<td scope="col">날짜</td>
				<td scope="col">조회</td>
			<td scope="col">좋아요</td>
		</tr>
		<c:forEach items="${list2}" var="dto">
			<tr>
				<td>${dto.bId}</td>
				<td>${dto.bName}</td>
				<td><c:forEach begin="1" end="${dto.bIndent}">-</c:forEach> <a
					href="content_view2.do?bId=${dto.bId}">${dto.bTitle}</a></td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td>
				<td>${dto.bLike}</td>
			</tr>
		</c:forEach>

		<div class="text-right">
				<form id="searchForm" name="searchForm">
					<select id="SearchChoice" name="SearchChoice">
						<option value="bTitle">제목</option>
						<option value="bName">작성자</option>
					</select> <input type="text" id="searchword" name="searchword"
						placeholder="검색어를 입력하세요">
					<button type="button" onclick="searchBbs()">검색</button>

				</form>
			</div>
		</div>
		<tr>
			<td colspan="6" text-right><a href="write_view2.do">글쓰기</a></td>
		</tr>
		<tr>
			<td colspan="6">
				<!-- 처음 --> <c:choose>
					<c:when test="${page.curPage -1 < 1}">
					[&lt;&lt;]
				</c:when>
					<c:otherwise>
						<a href="free_list.do?page=1">[&lt;&lt;]</a>
					</c:otherwise>
				</c:choose> <!-- 이전 --> <c:choose>
					<c:when test="${page.curPage -1 < 1}">
					[&lt;]
				</c:when>
					<c:otherwise>
						<a href="free_list.do?page=${page.curPage -1 }">[&lt;]</a>
					</c:otherwise>
				</c:choose> <!-- 개별페이지 --> <c:forEach var="fEach" begin="${page.startPage}"
					end="${page.endPage}" step="1">
					<c:choose>
						<c:when test="${page.curPage == fEach}">
						[${fEach}] &nbsp;
					</c:when>

						<c:otherwise>
							<a href="free_list.do?page=${fEach}">[${fEach}]</a> &nbsp;
					</c:otherwise>
					</c:choose>
				</c:forEach> <!-- 다음 --> <c:choose>
					<c:when test="${(page.curPage +1) >page.totalPage}">
					[&gt;]
				</c:when>
					<c:otherwise>
						<a href="free_list.do?page=${page.curPage +1 }">[&gt;]</a>
					</c:otherwise>
				</c:choose> <!-- 끝 --> <c:choose>
					<c:when test="${page.curPage  == page.totalPage}">
						[&gt;&gt;]
					</c:when>
					<c:otherwise>
						<a href="free_list.do?page=${page.totalPage}">[&gt;&gt;]</a>
					</c:otherwise>
				</c:choose>
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