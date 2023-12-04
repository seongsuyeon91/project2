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

<title>탈퇴화면</title>
<script src="http://code.jquery.com/jquery.js"></script>
</head>
<script>
	function pwd_check(){
		if ($('#mPwd').val() != $('#mPwd2').val()){
			alert("비밀번호가 일치하지 않습니다")
			return;
		}else
			alert("비밀번호가 확인되었습니다.")
	}
	
	function form_check() {
		

		if ($('#mPwd').val().length == 0) {
			alert("비밀번호는 필수사항입니다.");
			$('#mPwd').focus();
			return;
		}

		submit_ajax();
	}

	function submit_ajax() {

		var queryString = $("#withdraw_form").serialize();
		$.ajax({
			url : 'withdraw.do',
			type : 'POST',
			data : queryString,
			dataType : 'json',
			success : function(json) {
				var results = eval(json);
				console.log(results);
				if (results.code == "ok") {
					alert("탈퇴가 되었습니다.")
					window.location.replace("loginTotal_view.do");
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
</table>

	<table width="700" cellpadding="0" cellspacing="0" border="1">
		<form id="withdraw_form">
		<tr>
				<td>아이디</td>
				<td><input type="text" value="${mContent_view.mId}" readonly></td>
			</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="mPwd" name="mPwd" value="${mContent_view.mPwd}" readonly></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" id="mPwd2" name="mPwd2" size="50">
			<input type="button" value="확인"onclick="pwd_check()" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="탈퇴"
				onclick="form_check()" /> &nbsp;&nbsp; <a href="list.do">돌아가기</a>&nbsp;&nbsp;
			</td>
		</tr>
		</table>
	</table>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  
</body>
</html>