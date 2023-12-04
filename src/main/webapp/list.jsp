<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String mId = (String) session.getAttribute("mId");
session.removeAttribute("SearchChoice");
session.removeAttribute("searchword");
%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<%
session.removeAttribute("searchword");
session.removeAttribute("Search");
%>

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
</table>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<%=mId%>
			님 안녕하세요
			<br>
			<p>
		</tr>
		<a href="mContent_view.do">회원 정보 수정</a>&nbsp;&nbsp;
		<a href="withdraw_view.do">회원 탈퇴</a>&nbsp;&nbsp;
		<a href="logout.do">로그아웃</a>&nbsp;&nbsp;

	</table>

	<%-- <div class="mover">
		사용자 아이디 : <%= mId %>
		<input type="text" id="messageinput" />
		<button type="button" onclick="openSocket();">Open</button>
		<button type="button" onclick="send();">Send</button>
		<button type="button" onclick="closeSocket();">Close</button>
	</div> --%>
<!-- <script type="text/javascript">
    var webSocket;
    var messages = document.getElementById("messages");

    function openSocket() {
      if (webSocket != undefined && webSocket.readyState != WebSocket.CLOSED) {
        writeResponse("WebSocket is already opened.");
        return;
      }

      webSocket = new WebSocket("ws://" + window.location.host + "/Notice_board/websocketendpoint");

      webSocket.onopen = function (event) {
        if (event.data == undefined)
          return;

        writeResponse(event.data);
      };

      webSocket.onmessage = function (event) {
        writeResponse(event.data);
      };

      webSocket.onclose = function (event) {
        writeResponse("Connection closed");
      };
    }

    function send() {
      var id = out.print(mId);
      var text = document.getElementById("messageinput").value;
      webSocket.send(id + "|" + text);
    }

    function closeSocket() {
      if (webSocket !== null) {
        webSocket.close();
        webSocket = null;
        console.log("WebSocket connection closed.");
      }
    }

    function writeResponse(text) {
      messages.innerHTML += "<br />" + text;
    }

    // 페이지 닫을 때 WebSocket 연결 종료
    window.onbeforeunload = function () {
      closeSocket();
    }; -->
  </script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
 
</body>
</html>