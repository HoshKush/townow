<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="../ssi/ssi.jsp"%>

<!DOCTYPE html>
<html> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 

.search{
	width:80%;
	text-align:left;
	margin:5px auto;
} 
</style>
<script type="text/javascript">
function read(brd_id){
	var url = "read";
	url = url + "?brd_id=" + brd_id;
	url = url + "&col=${param.col}"; 
	url = url + "&word=${param.word}"; 
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&ca_id=${param.ca_id}";
	url = url + "&loc_id=${param.loc_id}";
	location.href=url;
	
}
function fileDown(filename){
	var url = "${root}/download";
	url = url + "?filename="+filename;
	url = url + "&dir=/bbs/storage";
	
	location.href=url;
}
function createContent(){
	let url = "./create";
	url = url + "?ca_id=${param.ca_id}";
	url = url + "&loc_id=${param.loc_id}";
	
	location.href=url;
}
</script> 
</head>
 
<body class="w3-light-grey w3-content" style="max-width:1600px">
<header id="portfolio">
<div style="margin-left:200px; margin-right:200px">
<div class="w3-container">
    <h1><b>지역, 카테고리 이름</b></h1>
  	
   <div class="w3-section w3-bottombar w3-padding-16">
      <div style="margin-bottom:10px">
<form method="post" action="./list">
<select name="col">
<option value="nickname" <c:if test="${col=='nickname' }">selected</c:if>>작성자</option>
<option value="title" <c:if test="${col=='title'}">selected</c:if>>제목</option>
<option value="content" <c:if test="${col=='content'}">selected</c:if>>내용</option>
<option value="total">전체출력</option>
</select>
<input type="text" name="word" value="${word }">
<button class="w3-button w3-purple">검색</button>
			<button type="button" class="w3-button w3-purple" onclick="createContent()">등록</button>

</form>
</div>
</div>
</div>
</div>
</header>
<div style="align:center">
<div class="w3-container" style="margin-left:200px; margin-right:200px;
 margin-top:20px; margin-bottom:20px; width:800;">

  <TABLE class="w3-table w3-hoverable" style="background-color:#FFFFFF;">
  <thead>
    <TR class="w3-hover-purple">
      <TH>No</TH>
      <TH>Title</TH>
      <TH>Writer</TH>
      <TH>Views</TH>
      <TH>Date</TH>
      <TH>Likes</TH>
   </TR>
   </thead>
 	<c:choose>
 		<c:when test="${empty list }">
 	<tbody>
	 <tr class="w3-hover-purple">
	 	<td colspan='6'>
	 	등록된 글이 없습니다.</td>
	 </tr>
	 </tbody>
	 </c:when>
	 <c:otherwise>
	 <c:forEach var="dto" items="${list }">
 
<tbody>
   <tr class="w3-hover-purple">
   	  <td>${dto.brd_id }</td>
   	  <td>
   	  <c:if test="${dto.indent>0 }">
   		  &nbsp;&nbsp;<img src='../menu/images/re.jpg'>
   	  </c:if>
   	  
   	  <a href="javascript:read('${dto.brd_id }')">${dto.title }</a>
   	  </td>
   	  <td>${dto.nickname }</td>
   	  <td>${dto.viewcount }</td>
   	  <td>${dto.create_time }</td>
	  <td>${dto.brd_like - dto.brd_dislike }</td>  	  

   </tr>
   </tbody>
	</c:forEach>
	</c:otherwise>
	</c:choose>
  </TABLE>
  
  <DIV class='bottom'>
	${paging }
  </DIV>
</div>
 
 </div>
</body>
</html> 