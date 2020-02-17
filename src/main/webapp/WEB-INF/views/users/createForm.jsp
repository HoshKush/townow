<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
</style>

<script type="text/javascript"	src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script>
    // const test = () => {
    //     $.ajax({
    //         type : 'POST',
    //         url : '/user/test',
    //         data : {
    //             "id" : $('#signUpID').val()
    //         },
    //         success : function(result) {
    //             alert('하이루');
    //         },
    //         error: function(error) {
    //             alert('실패');
    //         }
    //     })	//	End Ajax Request
    // }
    var idFlag = false;
    var pwFlag = false;
    var emFlag = false;
    const idCheck = () => {
        if($('#signUpID').val().length == 0){
            $('#idPannel').attr("style", "color: grey");
            $('#idPannel').text("ID should be between 4-20 characters");
            $('#signUpID').attr("style", "");
        } else if($('#signUpID').val().length < 4 || $('#signUpID').val().length > 20){
            $('#idPannel').attr("style", "color: red");
            $('#idPannel').text("ID should be between 4-20 characters");
            $('#signUpID').attr("style", "border: 2px solid red;");
        } else {
            $.ajax({
                type : 'GET',
                url : '/user/idCheck',
                data: {
                    "id" : $('#signUpID').val(),
                },
                success : function(result) {
                    if(result == 0){
                        idFlag = true;
                        $('#idPannel').attr("style", "color: green");
                        $('#idPannel').text("You can use this ID");
                        $('#signUpID').attr("style", "border: 2px solid green;");
                    } else if(result >= 1){
                        $('#idPannel').attr("style", "color: red");
                        $('#idPannel').text("This ID is already used");
                        $('#signUpID').attr("style", "border: 2px solid red;");
                    }
                },
                error : function(error){
                    $('#idPannel').attr("style", "color: red");
                    $('#idPannel').text("Unknown errors occured. Try again");
                    $('#signUpID').attr("style", "border: 2px solid red;");
                }
            })
        }
        
    }
    const checkEmail = () => {
        if($('#email').val().length == 0){
            $('#emailPannel').attr("style", "color: grey");
            $('#emailPannel').text("로그인을 위해 사용될 이메일을 입력해주세요.");
            $('#email').attr("style", "");
        } else {
            $.ajax({
                type : 'GET',
                url : '/users/emailCheck',
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
                error : function(error){
                    $('#emailPannel').attr("style", "color: red");
                    $('#emailPannel').text("오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    $('#email').attr("style", "border: 2px solid red;");
                }
            })
        }
    }
    const passwordCheck = () => {
        //비밀번호 검사
        let flag = false;
        if($('#signUpPassword2').val().length > 0 && $('#signUpPassword1').val().length > 0){
            if($('#signUpPassword1').val() != $('#signUpPassword2').val()) {
                $('#pw2Pannel').attr("style", "color: red;");
                $('#pw2Pannel').text("Check your password again");
                $('#signUpPassword2').attr("style", "border: 2px solid red;");
                $('#signUpPassword1').attr("style", "border: 2px solid red;");
            } else {
                $('#pw2Pannel').attr("style", "color: green");
                $('#pw2Pannel').text("You can use this password");
                $('#signUpPassword2').attr("style", "border: 2px solid green;");
                $('#signUpPassword1').attr("style", "border: 2px solid green;");
                pwFlag = true;
            }
        } else {
            $('#signUpPassword2').attr("style", "");
            $('#signUpPassword1').attr("style", "");
            $('#pw2Pannel').text("");
        }
    }
    const checkAll = () => {
        //예외처리
        if (!idFlag || !pwFlag || !emFlag) {
            if (!idFlag) {
                if ($('#signUpID').val().length == 0) {
                    $('#signUpID').attr("style", "border: 2px solid red;");
                    $('#idPannel').attr("style", "color: red");
                }
                $('#signUpID').focus();
            }
            if (!pwFlag) {
                if ($('#signUpPassword1').val().length == 0) {
                    $('#signUpPassword1').attr("style", "border: 2px solid red;");
                    $('#signUpPassword2').attr("style", "border: 2px solid red;");
                    $('#pw2Pannel').attr("style", "color: red;");
                    $('#pw2Pannel').text("Check your password again");
                    $('#signUpPassword1').focus();
                } else {
                    $('#pw2Pannel').attr("style", "color: red;");
                    $('#pw2Pannel').text("Check your password again");
                    $('#signUpPassword2').focus();
                }
            }
            if (!emFlag) {
                if ($('#signUpEmail').val().length == 0) {
                    $('#signUpEmail').attr("style", "border: 2px solid red;");
                    $('#emailPannel').attr("style", "color: red");
                    $('#signUpEmail').focus();
                }
            }
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/user/register',
                data: {
                    "id": $('#signUpID').val(),
                    "password": $('#signUpPassword1').val(),
                    "email": $('#signUpEmail').val()
                },
                success: function (result) {
                    alert(`${result}`);
                    location.href = '/';
                },
                error: function (result) {
                    alert('실패');
                }
            })
        }   
    }
    </script>
</head>
<body class="w3-light-grey w3-content">
<div style="align: center">
	<div class="w3-container" style="background-color: white; position: absolute; height: 400px; width: 500px; top: 50%; left: 50%; margin: -100px 0 0 -150px">
	<div class="w3-container w3-purple" style="width: 500px;">
	<h3>회원가입</h3>
	</div>
	<input id="email" type="email" size="45" placeholder="example@example.com" onkeyup="checkEmail()">
	<b id="emailPannel"></b>
	<input id="password" type="password" size="45" placeholder="비밀번호를 입력해주세요" onkeyup="checkPassword()">
	<b id="pwPannel"></b>
	<input id="password2" type="password" size="45" placeholder="비밀번호를 다시 입력해주세요." onkeyup="checkPassword()">
	<b id="pw2Pannel"></b>
	<input id="nickname" type="text" size="45" placeholder="닉네임을 입력해주세요." onkeyup="checkNickname()">
	<b id="nicknamePannel"></b>
	</div>
</div>
</body>
</html>
