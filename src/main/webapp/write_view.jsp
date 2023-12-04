<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%
	String mId= (String)session.getAttribute("mId");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>

function form_check(){
	
	if($('#bTitle').val().length == 0){
		alert("제목은 필수사항입니다.");
		$('$bTitle').focus();
		return;
	}
	if($('#bContent').val().length == 0){
		alert("내용은 필수사항입니다.");
		$('$bContent').focus();
		return;
	}
	submit_ajax();
}
function submit_ajax(){
	var queryString = $("#write_form").serialize();
	$.ajax({
		url:'write.do',
		type:'POST',
		data:queryString,
		dataType: 'json',
		success:function(json){
			var results=eval(json);
			console.log(results);
			if(results.code=="ok"){
				alert("게시물 작성이 되었습니다.")
				window.location.replace("announce_list.do");
			}else{
				alert(results.desc);
			}
		}
	});
}

</script>
</head>
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

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form id="write_form">
		<input type="hidden" name="bName" id="bName" value="<%=mId %>">
			<tr>
				<td>작성자</td>
				<td> <%=mId %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" id="bTitle" name="bTitle" size="50"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="bContent" name="bContent" rows="10"></textarea> </td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="button" value="입력" onclick="form_check()" />
					&nbsp;&nbsp; <a href="announce_list.do">취소</a>
				</td>
			</tr>
		</form>
	</table>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  
</body>
</html>