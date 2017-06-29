<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/index-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
var isEnd = false;
var render = function(vo){
	// 상용 app에서는 template 라이브러리를 사용해서 함  ex) ejs, leaf
	var html = 
		"<li data-no='"+vo.no+"'>" +
		"<strong>"+ vo.name + "</strong>" +
		"<p>"+ vo.message.replace( /\n/gi, "<br>")+"</p>" + 
		

		"<a href='${pageContext.servletContext.contextPath }/guestbook/delete/"+vo.no +"' data-no=''>삭제</a>"+
		
		"</li>";
		
	$("#list-guestbook").append(html);	
		
		
		
		
		
};

var fetchList = function(){
	
	if(isEnd === true){
		
		return ; 
	}
	
	var startNo = 0; 
	
	startNo = $("#list-guestbook li").last().data("no") || 0; // 앞이 null일경우 false , 0으로 
	console.log(startNo);
	
	$.ajax( {
		url : "${pageContext.request.contextPath }/guestbook/api/list?sno="+startNo,
		type: "get",
		dataType: "json", // 받아야되는 데이터 타입 
		data: "",
		//contentType: 'application/json', //json 타입으로 데이터를 보낼때 사용함 
		success: function(response){

				if(response.result ==="fail"){
					
					console.error(response.message);
					return;
				}
				
				// detect end 
				
				if(response.data.length < 5){
					
					isEnd=true;
					
					$("#btn").prop("disabled", true);
				}
				
				// rendering (html 만들기)
				
				$.each(response.data, function(index, vo){
					render(vo);
				});
			
		},
		error: function( jqXHR, status, e ){
			console.error( status + " : " + e );
		}
		} );

	
	
}
$(function(){
	
	$("#btn").click(function(){
		
		
		fetchList();
		
		
		
	});
	//최초리스트 가지고 오기 
	fetchList();
	
});

</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="${pageContext.servletContext.contextPath }/guestbook/ajinsert" method="post">
					<input type="text" id="input-name" placeholder="이름" name="name">
					<input type="password" id="input-password" placeholder="비밀번호 " name="pwd">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요." name="message"></textarea>
					<input type="submit" value="등록하기" />
				</form>
				
				<hr>
				
				
				<c:set var="count" value="${fn:length(list) }"/>
				<c:forEach items="${list }" var="vo" varStatus="status">
				
				<ul id="list-guestbook">
					<!-- 
					<li data-no='${count - status.index }'>
						<strong>${vo.name }</strong>
						<p>
							${fn:replace(vo.message, newLine, "<br>") }
						</p>
		
						<a href='${pageContext.servletContext.contextPath }/guestbook/delete/${vo.no }' data-no=''>삭제</a> 
					</li>
					 -->
									
				</ul>
				
			</c:forEach>
			<div style="margin:10px 0; text-align: center">
			<button id ="btn">다음</button>
				
			</div>
				
			</div>
						
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/include/foot.jsp" />
	</div>
</body>
</html>