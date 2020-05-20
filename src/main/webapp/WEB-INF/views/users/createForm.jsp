<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
</style>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script>
    var niFlag = false;
    var pwFlag = false;
    var emFlag = false;
    const checkNickname = () => {
        if($('#nickname').val().length == 0){
            $('#nicknamePannel').attr("style", "color: grey");
            $('#nicknamePannel').text("다른사용자에게 표시될 아이디를 입력해주세요(한/영/숫자조합 최대 20자)");
            $('#nickname').attr("style", "");
        } else if($('#nickname').val().length > 20){
            $('#nicknamePannel').attr("style", "color: red");
            $('#nicknamePannel').text("닉네임이 너무 깁니다.");
            $('#nickname').attr("style", "border: 2px solid red;");
        } else {
            $.ajax({
                type : 'GET',
                url : './checkNickname',
                data: {
                    "nickname" : $('#nickname').val(),
                },
                success : function(result) {
                    if(result == 0){
                        niFlag = true;
                        $('#nicknamePannel').attr("style", "color: green");
                        $('#nicknamePannel').text("사용가능한 닉네임입니다.");
                        $('#nickname').attr("style", "border: 2px solid green;");
                    } else if(result >= 1){
                        $('#nicknamePannel').attr("style", "color: red");
                        $('#nicknamePannel').text("이미 사용 중인 닉네임입니다.");
                        $('#nickname').attr("style", "border: 2px solid red;");
                    }
                },
                error : function(error){
                    $('#nicknamePannel').attr("style", "color: red");
                    $('#nicknamePannel').text("오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    $('#nickname').attr("style", "border: 2px solid red;");
                }
            })
       
    }
    }
    
    const checkEmail = () => {
        if($('#email').val().length == 0){
            $('#emailPannel').attr("style", "color: grey");
            $('#emailPannel').text("로그인을 위해 사용할 이메일을 입력해주세요.");
            $('#email').attr("style", "");
        } else {
            $.ajax({
                type : 'GET',
                url : './checkEmail',
                dataType: "text",
                data: {
                    "email" : $('#email').val(),
                },
                success : function(result) {
                    if(result == 0){
                        $('#emailPannel').attr("style", "color: green");
                        $('#emailPannel').text("사용 가능한 이메일 입니다.");
                        $('#email').attr("style", "border: 2px solid green;");
                        emFlag = true;                        
                    } else if(result >= 1){
                        $('#emailPannel').attr("style", "color: red");
                        $('#emailPannel').text("이미 사용중인 이메일 입니다.");
                        $('#email').attr("style", "border: 2px solid red;");
                    }
                },
                error : function(request, status, error){
                	alert("request : " + request + " status : " + status + " error : " + error);
                    $('#emailPannel').attr("style", "color: red");
                    $('#emailPannel').text("오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    $('#email').attr("style", "border: 2px solid red;");
                }
            })
        }
    }
    const checkPassword = () => {
        //비밀번호 검사
        if($('#password2').val().length > 0 && $('#password1').val().length > 0){
            if($('#password1').val() != $('#password2').val()) {
                $('#pw2Pannel').attr("style", "color: red;");
                $('#pw2Pannel').text("Check your password again");
                $('#password2').attr("style", "border: 2px solid red;");
                $('#password1').attr("style", "border: 2px solid red;");
            } else {
                $('#pw2Pannel').attr("style", "color: green");
                $('#pw2Pannel').text("You can use this password");
                $('#password2').attr("style", "border: 2px solid green;");
                $('#password1').attr("style", "border: 2px solid green;");
                pwFlag = true;
            }
        } else {
            $('#password2').attr("style", "");
            $('#password1').attr("style", "");
            $('#pw2Pannel').text("");
        }
    }
    const checkAll = () => {
        //예외처리
        if (!niFlag || !pwFlag || !emFlag) {
            if (!niFlag) {
                if ($('#nickname').val().length == 0) {
                    $('#nickname').attr("style", "border: 2px solid red;");
                    $('#nicknamePannel').attr("style", "color: red");
                }
                $('#nickname').focus();
            }
            if (!pwFlag) {
                if ($('#password1').val().length == 0) {
                    $('#password1').attr("style", "border: 2px solid red;");
                    $('#password2').attr("style", "border: 2px solid red;");
                    $('#pw2Pannel').attr("style", "color: red;");
                    $('#pw2Pannel').text("Check your password again");
                    $('#password1').focus();
                } else {
                    $('#pw2Pannel').attr("style", "color: red;");
                    $('#pw2Pannel').text("Check your password again");
                    $('#password2').focus();
                }
            }
            if (!emFlag) {
                if ($('#email').val().length == 0) {
                    $('#email').attr("style", "border: 2px solid red;");
                    $('#emailPannel').attr("style", "color: red");
                    $('#email').focus();
                }
            }
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: './register',
                data: {
                    "nickname": $('#nickname').val(),
                    "password": $('#password1').val(),
                    "email": $('#email').val()
                },
                success: function (result) {                	
                    if(result === "success"){
                    	alert("환영합니다! 이메일을 인증받으시면 로그인 및 글쓰기가 가능합니다.");
                    	location.href="../"
					}
                },
                error: function (request, status, error) {
                	alert("request : " + request + " status : " + status + " error : " + error);
                }
            })
        }   
    }
    
</script>
</head>
<body class="w3-light-grey w3-content">
		<div id="pannel" style="align: center">
			<div class="w3-container" style="background-color: white; position: absolute; height: 400px; width: 500px; top: 50%; left: 50%; margin: -100px 0 0 -150px">
				<div class="w3-container w3-purple" style="width: 500px;">
					<h3 id="titlePannel">회원가입</h3>
				</div>
				<input id="email" type="email" size="45" placeholder="example@example.com" onkeyup="checkEmail()">
				<p id="emailPannel">로그인을 위해 사용할 이메일을 입력해주세요.</p>
				<input id="password1" type="password" size="45" placeholder="비밀번호를 입력해주세요" onkeyup="checkPassword()">
				<p id="pwPannel"></p>
				<input id="password2" type="password" size="45" placeholder="비밀번호를 다시 입력해주세요." onkeyup="checkPassword()">
				<p id="pw2Pannel"></p>
				<input id="nickname" type="text" size="45" placeholder="닉네임을 입력해주세요." onkeyup="checkNickname()">
				<p id="nicknamePannel"></p>
				<button class="w3-button w3-blue" onclick="checkAll()">가입</button>
	</div>
</div>
</body>
</html>
