<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<title>sns total</title>
<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="naveridlogin_js_sdk_2.0.2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<script src="https://unpkg.com/jwt-decode/build/jwt-decode.js"></script>

<script>
function recaptchaCallback() {
    document.getElementById('capok').removeAttribute('disabled');
}
 
function pwd_check(){
	if ($('#mPwd3').val() != $('#mPwd2').val()){
		alert("비밀번호가 일치하지 않습니다")
		return;
	}
	if ($('#mId2').val().length == 0){
		alert("아이디는 필수입니다.")
		$('#mId2').focus();
		return;
	}
	if ($('#mEmail').val().length == 0){
		alert("이메일은 필수입니다.")
		$('#mEmail').focus();
		return;
	}
	 	submit_ajax2();
}
function submit_ajax2() {

	var queryString = $("#form2").serialize();
	$.ajax({
		url : 'join.do',
		type : 'POST',
		data : queryString,
		dataType : 'json',
		success : function(json) {
			var results = eval(json);
			console.log(results);
			if (results.code == "ok") {
				alert("회원가입이 완료되었습니다.")
				window.location.replace("memModify_view.do");
			} else {
				alert("회원가입이 실패하였습니다.");
			}
		}

	});
}

	
	function form_check() {
	
		if($('#mId').val().length == 0) {
			alert("아이디를 입력하세요.");
			$('#mId').focus();
			return;
		}
		if($('#mPwd').val().length == 0) {
			alert("비밀번호는 필수사항입니다.");
			$('#mPwd').focus();
			return;
		}
		
		submit_ajax();
	}
	function submit_ajax(){
		
		var queryString = $("#form1").serialize();
		$.ajax({
			url:'loginTotal.do',
			type:'POST',
			data:queryString,
			dataType: 'json',
			success:function(json){
				var results=eval(json);
				console.log(results);
				if(results.code=="ok"){
					alert("로그인되었습니다.")
					window.location.replace("list.do");
				}else{
					alert(results.desc);
				}
			}
			
		});
	}
	</script>
<style>
:root {
	/* COLORS */
	--white: #e9e9e9;
	--gray: #333;
	--blue: #0367a6;
	--lightblue: #008997;
	/* RADII */
	--button-radius: 0.7rem;
	/* SIZES */
	--max-width: 758px;
	--max-height: 420px;
	font-size: 16px;
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}

body {
	align-items: center;
	background-color: var(--white);
	background:
		url("https://img.freepik.com/free-photo/homemade-chocolate-chip-cookies-on-rustic-wooden-table-generative-ai_188544-12710.jpg?w=1380&t=st=1692512730~exp=1692513330~hmac=3c26ee553a4cf45dffea226edadebcc57d7e34d41409327e6820a3e88d7a19f7");
	background-attachment: fixed;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	display: grid;
	height: 100vh;
	place-items: center;
}

.form__title {
	font-weight: 300;
	margin: 0;
	margin-bottom: 1.25rem;
}

.link {
	color: var(--gray);
	font-size: 0.9rem;
	margin: 1.5rem 0;
	text-decoration: none;
}

.container {
	background-color: var(--white);
	border-radius: var(--button-radius);
	box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25), 0 0.7rem 0.7rem
		rgba(0, 0, 0, 0.22);
	height: var(--max-height);
	max-width: var(--max-width);
	overflow: hidden;
	position: relative;
	width: 100%;
}

.container__form {
	height: 100%;
	position: absolute;
	top: 0;
	transition: all 0.6s ease-in-out;
}

.container--signin {
	left: 0;
	width: 50%;
	z-index: 2;
}

.container.right-panel-active .container--signin {
	transform: translateX(100%);
}

.container--signup {
	left: 0;
	opacity: 0;
	width: 50%;
	z-index: 1;
}

.container.right-panel-active .container--signup {
	animation: show 0.6s;
	opacity: 1;
	transform: translateX(100%);
	z-index: 5;
}

.container__overlay {
	height: 100%;
	left: 50%;
	overflow: hidden;
	position: absolute;
	top: 0;
	transition: transform 0.6s ease-in-out;
	width: 50%;
	z-index: 100;
}

.container.right-panel-active .container__overlay {
	transform: translateX(-100%);
}

.overlay {
	background-color: var(--lightblue);
	background:
		url("https://img.freepik.com/free-photo/homemade-chocolate-chip-cookies-on-rustic-wooden-table-generative-ai_188544-12710.jpg?w=1380&t=st=1692512730~exp=1692513330~hmac=3c26ee553a4cf45dffea226edadebcc57d7e34d41409327e6820a3e88d7a19f7");
	background-attachment: fixed;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	height: 100%;
	left: -100%;
	position: relative;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 200%;
}

.container.right-panel-active .overlay {
	transform: translateX(50%);
}

.overlay__panel {
	align-items: center;
	display: flex;
	flex-direction: column;
	height: 100%;
	justify-content: center;
	position: absolute;
	text-align: center;
	top: 0;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
	width: 50%;
}

.overlay--left {
	transform: translateX(-20%);
}

.container.right-panel-active .overlay--left {
	transform: translateX(0);
}

.overlay--right {
	right: 0;
	transform: translateX(0);
}

.container.right-panel-active .overlay--right {
	transform: translateX(20%);
}

.btn {
	background-color: var(--blue);
	background-image: linear-gradient(90deg, var(--blue) 0%,
		var(--lightblue) 74%);
	border-radius: 20px;
	border: 1px solid var(--blue);
	color: var(--white);
	cursor: pointer;
	font-size: 0.8rem;
	font-weight: bold;
	letter-spacing: 0.1rem;
	padding: 0.9rem 4rem;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
}

.form>.btn {
	margin-top: 1.5rem;
}

.btn:active {
	transform: scale(0.95);
}

.btn:focus {
	outline: none;
}

.form {
	background-color: var(--white);
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 3rem;
	height: 100%;
	text-align: center;
}

.input {
	background-color: #fff;
	border: none;
	padding: 0.9rem 0.9rem;
	margin: 0.5rem 0;
}

@
keyframes show { 0%, 49.99% {
	opacity: 0;
	z-index: 1;}
50%,100%
{
opacity:1;z-index:5;}
}
</style>
</head>

<body>


	<div class="container right-panel-active">
		<!-- Sign Up -->
		<div class="container__form container--signup">
			<form class="form" id="form1">
				<h2 class="form__title">로그인</h2> 
				<input type="text" name="mId" id="mId" placeholder="User ID" class="input" /> 
				<input type="password" name="mPwd" id="mPwd"placeholder="Password" class="input" />
				<div class="form-group" id="google_recaptha">
<script src='https://www.google.com/recaptcha/api.js'></script>
<div class="g-recaptcha" id="gcap" data-sitekey="6LeXk5gnAAAAAJeHIAAbgifA4BtinsAOpitvUKra" data-callback="recaptchaCallback"></div>
</div>
				<button class="btn" onclick="form_check()">로그인</button>
				<br />
				
				<div id="login3">
					<div id="buttonDiv3"></div>
				</div>
			
				<div id="logout3" style="display: none;">
					<input type="button" onclick="signOut3();" value="구글 로그아웃" /><br>

					<img id="upic3" src=""><br> <span id="uname3"></span>
				</div>
			</form>
		</div>

		
		

		<div class="container__form container--signin">
			<form class="form" id="form2">
				<h3 class="form__title">회원가입</h3>
				<input type="text" name="mId"  id ="mId2" placeholder="id" class="input" /> 
				<input type="password" name="mPwd" id ="mPwd2" placeholder="Password" class="input" /> 
				<input type="password" name="mPwd2" id ="mPwd3" placeholder="Password check" class="input" /> 
				<input type="text" name="mEmail" id ="mEmail" placeholder="suyeon@gmail.com"class="input" />
				<button class="btn" onclick="pwd_check()">회원가입</button>
			</form>
		</div>
		  <!-- Overlay -->
		<div class="container__overlay">
			<div class="overlay">
				<div class="overlay__panel overlay--left">
					<button class="btn" id="signIn">회원가입</button>
				</div>
				<div class="overlay__panel overlay--right">
					<button class="btn" id="signUp">로그인</button>
				</div>
			</div>
		</div>
	</div>
		
<script>
const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
const fistForm = document.getElementById("form1");
const secondForm = document.getElementById("form2");
const container = document.querySelector(".container");

signInBtn.addEventListener("click", () => {
  container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
  container.classList.add("right-panel-active");
});

fistForm.addEventListener("submit", (e) => e.preventDefault());
secondForm.addEventListener("submit", (e) => e.preventDefault());


</script>	
			<!-- 구글 -->
			<script>
	window.onload = function() {
	    google.accounts.id.initialize({
	        client_id: "463865349996-u7vrvqpj7epaacn5831bnsnc994e0tev.apps.googleusercontent.com",
	        callback: handleCredentialResponse3
	    });
	    google.accounts.id.renderButton(
	            document.getElementById("buttonDiv3"),
	            { theme: "outline", text: "signin", width: 250 },
	        );
		    //google.accounts.id.prompt();
	}

	function handleCredentialResponse3(response) {
	    var profile = jwt_decode(response.credential);
		console.log("ID: " + profile.sub);
		console.log('Name: ' + profile.name);
	    console.log("Image URL: " + profile.picture);
	    console.log("Email: " + profile.email);    
		
		$('#login3').css('display', 'none');
    	$('#logout3').css('display', 'block');
    	$('#upic3').attr('src', profile.picture);
    	$('#uname3').html('[ ' +profile.name + ' ]');
	}
	function signOut3() {
	    google.accounts.id.disableAutoSelect();
	    
        $('#login3').css('display', 'block');
        $('#logout3').css('display', 'none');
        $('#upic3').attr('src', '');
        $('#uname3').html('');
	}
	
	</script>
	</body>
	
	</html>



