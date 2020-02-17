<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
</style>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/JavaScript">
	window.onload = function() {
		CKEDITOR.replace('content');
	};
</script>
<script type="text/javascript">
	function incheck(f) {
		if (f.name.value == "") {
			alert("이름을 입력해주세요");
			f.name.focus();
			return false;
		}
		if (f.title.value == "") {
			alert("제목을 입력해주세요")
			f.title.focus();
			return false;
		}
		if (CKEDITOR.instances['content'].getData() == '') {
			window.alert('내용을 입력해 주세요.');
			CKEDITOR.instances['content'].focus();
			return false;
		}
		if (f.passwd.value == "") {
			alert("비밀번호를 입력해주세요")
			f.passwd.focus();
			return false;
		}

	}
</script>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px">
	<div style="align: center">
		<div class="w3-container"
			style="margin-left: 200px; margin-right: 200px; margin-top: 20px; margin-bottom: 20px; width: 800;">
			<div class="w3-container w3-purple" style="width: 800px;">
				<h5>New log</h5>
			</div>

			<FORM name='frm' method='POST' action='./reply'
				enctype="multipart/form-data" onsubmit='return incheck(this)'>
				<!-- 답변있는 부모글의 삭제를 못하게 하기 위하여~ -->
				<input type="hidden" name="brd_id" value="${dto.brd_id }">

				<!-- 답변에 필요한 부모 자료들 -->
				<input type="hidden" name="grp" value="${dto.grp }"> 
				<input type="hidden" name="indent" value="${dto.indent }">
				<input type="hidden" name="ansno" value="${dto.ansno }">

				<!-- 페이징 및 검색 유지를 위한 것 -->
				<input type="hidden" name="col" value="${param.col }"> 
				<input type="hidden" name="word" value="${param.word }"> 
				<input type="hidden" name="nowPage" value="${param.nowPage }"> 
				
				<!-- 카테고리 및 지역 -->
				<input type="hidden" name="loc_id" value="1"> 
				<input type="hidden" name="ca_id" value="1">
				<TABLE style="width: 800px;">
					<TR>
						<TD><input class="w3-input" type="text" name="title"
							placeholder="title" size="100"></TD>
					</TR>

					<TR>
						<TD><textarea class="w3-input" rows="20" cols="45"
								name="content" placeholder="content" style="resize: none;"></textarea></TD>
					</TR>
					<TR>
						<TD>
							<div class="w3-row-padding">
								<div class="w3-third">
									<input class="w3-input" type="text" name="email"
										placeholder="email" size="45">
								</div>
								<div class="w3-third">
									<div class="w3-third"></div>
								</div>
							</div>
						</TD>
					</TR>
					<tr>
						<td><input class="w3-input" type="file" name="filenameMF"></td>
					</tr>
				</TABLE>

				<DIV class='bottom'>
					<button type='submit' class="btn btn-primary">등록</button>
					<input type='button' value='취소' onclick="history.back()">
				</DIV>
			</FORM>
		</div>
	</div>
</body>
</html>
