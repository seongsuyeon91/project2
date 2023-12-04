<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>


<title>정보 수정</title>
</head>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function form_check() {
		if ($('#mPwd').val().length == 0) {
			alert("비밀번호는 필수사항입니다.");
			$('#mPwd').focus();
			return;
		}
		submit_ajax();
	}

	function submit_ajax() {
		var queryString = $("#modify_form").serialize();
		$.ajax({
			url : 'memModify.do',
			type : 'POST',
			data : queryString,
			dataType : 'json',
			success : function(json) {
				var results = eval(json);
				console.log(results);
				if (results.code == "ok") {
					alert("정보 수정이 완료되었습니다.")
					window.location.replace("list.jsp");
				} else {
					alert(results.desc);
				}
			}
		});
	}
</script>

<body>
	  <nav class="navbar navbar-expand-lg navbar navbar navbar-dark bg-dark">
  <a class="navbar-brand" href="list.do">Cookies</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
 	 <li class="nav-item">
        <a class="nav-link" href="announce_list.do">공지사항</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="free_list.do">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="archive_list.do">자료실</a>
      </li>
    </ul>
  </div>
</nav>
	<form id="modify_form">
		<input type="hidden" name="mId" id="mId" value="${mContent_view.mId}">
		<table width="500" cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td>아이디</td>
				<td>${mContent_view.mId}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="mName" id="mName"
					value="${mContent_view.mName}"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="mPwd" id="mPwd"
					value="${mContent_view.mPwd}"></td>
			</tr>
			<tr>
				<td>전화번호
				<td><select name="phone1"
					value="${fn:substring(mContent_view.mPhone,0,3)}">
						<option value="010">010</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
						<option value="011">011</option>
				</select>- 
				<input type="text" id="phone2" name="phone2" size="5" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1'); value="${fn:substring(mContent_view.mPhone,4,8)}"> - 
				<input type="text" id="phone3" name="phone3" size="5" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1'); value="${fn:substring(mContent_view.mPhone,9,13)}"><br /></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="mEmail" id="mEmail"
					value="${mContent_view.mEmail}"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="수정"
					onclick="form_check()" /> <a href="list.do">취소</a>&nbsp;&nbsp;</td>
			</tr>
		</table>
	</form>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  
</body>
</html>