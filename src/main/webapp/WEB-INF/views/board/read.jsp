<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript">
$.ajaxSetup({
    error: function(jqXHR, exception) {
        if (jqXHR.status === 0) {
            alert('Not connect.\n Verify Network.');
        }
        else if (jqXHR.status == 400) {
            alert('Server understood the request, but request content was invalid. [400]');
        }
        else if (jqXHR.status == 401) {
            alert('Unauthorized access. [401]');
        }
        else if (jqXHR.status == 403) {
            alert('Forbidden resource can not be accessed. [403]');
        }
        else if (jqXHR.status == 404) {
            alert('Requested page not found. [404]');
        }
        else if (jqXHR.status == 500) {
            alert('Internal server error. [500]');
        }
        else if (jqXHR.status == 503) {
            alert('Service unavailable. [503]');
        }
        else if (exception === 'parsererror') {
            alert('Requested JSON parse failed. [Failed]');
        }
        else if (exception === 'timeout') {
            alert('Time out error. [Timeout]');
        }
        else if (exception === 'abort') {
            alert('Ajax request aborted. [Aborted]');
        }
        else {
            alert('Uncaught Error.n' + jqXHR.responseText);
        }
    }
});

	function blist() {
		var url = "./list"
		url = url + "?col=${param.col}";
		url = url + "&word=${param.word}";
		url = url + "&nowPage=${param.nowPage}";
		url = url + "&loc_id=${param.loc_id}";
		url = url + "&ca_id=${param.ca_id}";
		location.href = url;
	}
	function bupdate() {
		var url = "./update"
		url = url + "?brd_id=${param.brd_id}";
		url = url + "&oldfile=${dto.filename}";
		url = url + "&col=${param.col}";
		url = url + "&word=${param.word}";
		url = url + "&nowPage=${param.nowPage}";
		url = url + "&loc_id=${param.loc_id}";
		url = url + "&ca_id=${param.ca_id}";
		location.href = url;
	}
	function bdel(brd_id) {
		
		if (confirm("정말로 삭제하시겠습니까?")) {
			$.ajax({
				url : "deleteArticle",
				type : "GET",
				dataType : 'text',
				data : {"brd_id": brd_id},
				success : function(data){
					if(data.charAt(0)==='s'){
						alert(data.slice(1));
						var url = "./list";
						url = url + "?col=${param.col}";
						url = url + "&word=${param.word}";
						url = url + "&loc_id=${param.loc_id}";
						url = url + "&ca_id=${param.ca_id}";
						location.href = url;
					} else {
						alert(data);
					}
				},
				error : function(request, status, error){
					alert("request : " + request + " status : " + status + " error : " + error);
				}
			});
		}
	}
	function breply() {
		var url = "./reply";
		url = url + "?brd_id=${param.brd_id}";
		url = url + "&col=${param.col}";
		url = url + "&word=${param.word}";
		url = url + "&nowPage=${param.nowPage}";
		url = url + "&loc_id=${param.loc_id}";
		url = url + "&ca_id=${param.ca_id}";
		location.href = url;
	}
	function fileDown(filename) {
		var url = "${root}/download";
		url += "?filename=filename";
		url += "&dir=/bbs/storage";

		location.href = url;
	}
	function updateLike(brd_id){
		$.ajax({
			url : "./updateLike",
			type : "GET",
			dataType : 'text',
			data : {"brd_id": brd_id},
			success : function(data){
				if(!isNaN(data)){
					$('#like').text(data);
				} else {
					alert(data);
				}
			},
			error : function(request, status, error){
				alert("request : " + request + " status : " + status + " error : " + error);
			}
		});
	}
	function updateDislike(brd_id){
		$.ajax({
			url : "./updateDislike",
			type : "GET",
			dataType : 'text',
			data : {"brd_id": brd_id},
			success : function(data){
				if(!isNaN(data)){
					$('#dislike').text(data);
				} else {
					alert(data);
				}
			},
			error : function(request, status, error){
				alert("request : " + request + " status : " + status + " error : " + error);
			}
		});
	}
</script>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px">


	<div
		style="align: center; margin-top: 50px; margin-bottom: 50px; margin-left: 200px; margin-right: 200px;">
		<TABLE class="w3-table"
			style="width: 840px; background-color: #FFFFFF;">
			<tr>
				<TD colspan="2">
					<h2>
						<b>${dto.title }</b>
					</h2>
				</TD>
			</tr>
			<tr>
				<TD style="text-align: left;"><img class="w3-circle"
					style="width: 20px; height: 20px" src="./storage/default.jpg">
					<span style="font-weight: bold; font-size: 20">${dto.nickname }</span>
					<span style="font-color: #808080; margin-left: 5px">|
						${dto.create_time }</span></td>
				<td style="text-align: right;"><c:choose>
						<c:when test="${dto.filename!=null }">
							<a href="javascript:fileDown('${dto.filename }')"> <img
								style="width: 20px; height: 20px"
								src="./storage/download-icon.jpg"> ${dto.filename }
								(${dto.filesize/1024 }KB)
							</a>
						</c:when>
						<c:otherwise>no file</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>

				<TD colspan="2">
					<div class="w3-border-top" style="padding: 20px; min-height: 600px">
						${dto.content }</div>
				</TD>
			</tr>

			<tr>
				<TD style="width: 200px; height: 50px"><b>view</b>
					${dto.viewcount }</TD>
				<TD style="width: 200px; hegith: 50px; text-alignment: left;">
				<button class="w3-button w3-blue" onclick="updateLike(`${dto.brd_id}`)">like</button>
				&nbsp; <b id="like">${dto.brd_like }</b>
				<button class="w3-button w3-blue" onclick="updateDislike(`${dto.brd_id}`)">dislike</button>
				&nbsp; <b id="dislike">${dto.brd_dislike }</b>
				</TD>
			</tr>
			

		</TABLE>

		<DIV class='bottom'>
			<input class="w3-button w3-purple" type='button' value='목록'
				onclick="blist()"> 
			<button class="w3-button w3-purple" onclick="bupdate()">수정</button>
			<button class="w3-button w3-purple" onclick="bdel(`${dto.brd_id}`)">삭제</button>
			<button class="w3-button w3-purple" onclick="breply()">답변</button>
		</DIV>
	</div>



</body>
</html>
