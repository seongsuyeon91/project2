<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<%
	String mId= (String)session.getAttribute("mId");
	%>
<html>
<head>
<meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<title>회원수정 화면</title>
</head>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function form_check() {
		if ($('#mId').val().length == 0) {
			alert("아이디는 필수사항입니다.");
			$('#mId').focus();
			return;
		}
		if ($('#mPwd').val().length == 0) {
			alert("비밀번호는 필수사항입니다.");
			$('#mPwd').focus();
			return;
		}
		if ($('#mName').val().length == 0) {
			alert("이름은 필수사항입니다.");
			$('#mName').focus();
			return;
		}
		if ($('#phone2').val().length == 0) {
			alert("전화번호는 필수사항입니다.");
			$('#phone2').focus();
			return;
		}
		
		if ($('#phone3').val().length == 0) {
			alert("전화번호는 필수사항입니다.");
			$('#phone3').focus();
			return;
		}
		if ($('#mEmail').val().length == 0) {
			alert("이메일은 필수사항입니다.");
			$('#mEmail').focus();
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
<table width="500" cellpadding="0" cellspacing="0" border="1">
  <nav class="navbar navbar-expand-lg navbar navbar navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Cookies</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
    
    </ul>
  </div>
</nav>
개인정보를 수정해주세요
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form id="modify_form" action="modify_form">
			<tr>
				<td>아이디</td>
				<td><input type="text" id="mId" name="mId" value="${mContent_view.mId}" readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="mPwd" name="mPwd" value="${mContent_view.mPwd}" size="50"></td>
			</tr>

			<tr>
				<td>이름</td>
				<td><input type="text" id="mName" name="mName" size="50"></td>
			</tr>
			<tr>
				<td>전화번호
				<td><select name="phone1">
						<option value="010">010</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
						<option value="011">011</option>
				</select>- <input type="text" id="phone2" name="phone2" size="5" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">- 
				<input type="text" id="phone3" name="phone3" size="5" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"><br /></td>
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" id="mEmail" name="mEmail" value="${mContent_view.mEmail}" size="50"></td>
			</tr>

			<tr>
				<td colspan="2">
				<input type="button" value="정보수정" onclick="form_check()"> &nbsp;&nbsp;
			</tr>
		</form>
	</table>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  
</body>
</html>
