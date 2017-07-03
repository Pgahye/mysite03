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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">

.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset {
 
 	float : none;
 	text-align : center;
}

.ui-dialog .ui-dialog-buttonpane button {

	margin-left:10px;
	margin-right:auto;
}	

#dialog-message p {

	padding:20px 0;
	font-weight:bold;
	font-size:1.0em;

}


 #dialog-delete-form p {
 
 	padding:10px;
	font-weight:bold;
	font-size:1.0em; 
 
 }
 
#dialog-delete-from input[type="password"] {
 
 	padding : 50px;
 	border : 1px solid #333;
 	outline : none;
 	width: 180px;
 
 }

#dialog-delete-from p.error {
	color: #f00;
}
 
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
// jquery plugin

(function($){
	
	$.fn.hello = function(){
		
		console.log($(this).attr("id")+"-----> hello");	
		
	}
	
	
})(jQuery);


var isEnd = false;

var listItemTemplate = new EJS({url:"${pageContext.request.contextPath }/assets/js/ejs-template/guestbook-list-item.ejs"}); //템플릿을  바인딩 하는 방식 
var listTemplate = new EJS({url:"${pageContext.request.contextPath }/assets/js/ejs-template/guestbook-list.ejs"}); //템플릿을  바인딩 하는 방식 


var messageBox = function(title, message, callback){
	
	$( "#dialog" ).attr("title", title);
	$( "#dialog p" ).text(message);
	
	
	 $( "#dialog" ).dialog({
	      modal: true,
	  
	      
	      buttons: {
	        Ok: function() {
	          $( this ).dialog( "close" );
	        }
	 		
	 		//	,
	       // Cancel : function(){ 버튼  추가할수도 있음 
	       // 	console.log();
	       // }
	      },
	      close: callback || function(){ // undefine일 경우 에러발생할수 있으므로 빈 function을 넣어준다. 
	    	  
	    	  
	      }
	    });
	
	
}
var render = function(vo, mode){
	// 상용 app에서는 template 라이브러리를 사용해서 함  ex) ejs, , underscore, mustache 
	var html = listItemTemplate.render(vo); //템플릿 가지고옴 
		//"<li data-no='"+vo.no+"'>" +
		//"<strong>"+ vo.name + "</strong>" +
		//"<p>"+ vo.message.replace( /\n/gi, "<br>")+"</p>" + 
			
		//"   <a href='' data-no='" + vo.no + "'>삭제</a>" + 
		
		//"</li>";
		
		
		
	if(mode === true){
		
		$("#list-guestbook").prepend(html);
		
	}else{
		
		$("#list-guestbook").append(html);		
		
	}	
		
	
		
		
		
		
		
};

var fetchList = function(){
	
	if(isEnd === true){
		
		return ; 
	}
	
	var startNo = 0; 
	
	startNo = $("#list-guestbook li").last().data("no") || 0; // 앞이 null일경우 false , 0으로 
	
	
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
				
				//$.each(response.data, function(index, vo){
				//	render(vo, false);
				//});
				
				var htmls = listTemplate.render(response); // response 객체를 넘김  listTemplate에서 data로 읽음 
				
				$("#list-guestbook").append(htmls);
				$("#list-guestbook").hello();
			
		},
		error: function( jqXHR, status, e ){
			console.error( status + " : " + e );
		}
		} );

	
	
}
$(function(){
	
	
	var dialogDelete = $( "#dialog-delete-form" ).dialog({
	      autoOpen: false,
	      height: 200,
	      width: 350,
	      modal: true,
	      buttons: {
	        "삭제": function(){
	        	
	        	var no = $("#delete-no").val(); 
	        	var password = $("#delete-password").val();
	        	
	        	
	      		//ajax 통신 
	        	$.ajax( {
	    			url : "${pageContext.request.contextPath }/guestbook/api/delete",
	    			type: "post",
	    			dataType: "json", // 받아야되는 데이터 타입 
	    			data: "no= "+no + "&" + 
	    			"pwd=" + password,
	    			//contentType: 'application/json', //json 타입으로 데이터를 보낼때 사용함 
	    			
	    			success: function(response){

	    				
	    					if(response.result === "fail"){
	    						
	    						console.error(response.message);
	    						return;
	    					}
	    					
	    					//삭제 실패 
	    					if(response.data === -1){
	    						
	    						$("#dialog-delete-form .validateTips.normal").hide();
	    						$("#dialog-delete-form .validateTips.error").show();
	    						 $("#delete-password").val("");
	    						return; 
	    						
	    						
	    					}
	    					// 삭제 성공
	    					$("#list-guestbook li[data-no='"+ response.data +"']").remove();
	    					dialogDelete.dialog("close");
	    		
	    				
	    			},
	    			error: function( jqXHR, status, e ){
	    				console.error( status + " : " + e );
	    			}
	    			} );
	      		
	        },
	        "취소": function() {
	          
	        	dialogDelete.dialog("close");
	        }
	      },
	      close: function() {
	    	 $("#dialog-delete-form .validateTips.normal").show();
			$("#dialog-delete-form .validateTips.error").hide();
	    	  $("#delete-password").val("");
	      }
	    });
	 
	
	
	//$("#list-guestbook li a").click(function( event ){
		
		//event.preventDefault();
		//console.log("삭제");
		
	//});
	
	// live event 
	$(document).on("click", "#list-guestbook li a", function(){
		
		event.preventDefault();
		
	 	window.no = $(this).data("no");
		
		$("#delete-no").val(no);
		
		dialogDelete.dialog("open");
		
	});
	
	$("#add-form").submit(function(event){
		
		// submit 이벤트 기본 동작을 막음 (posting) ajax로 통신하기 위해 
		event.preventDefault();
		
		var vo = {};
		
		// validate form data
		
		vo.name = $("#input-name").val();
		
		if(vo.name === ""){
			
			messageBox("방명록에 글남기기", "이름은 필수 입력 항목 입니다.  ", function(){$("#input-name").focus();});
			//$("#dialog").dialog();
			//alert("이름은 필수 입력 항목 입니다. ");
			return ; 
			
		}
		
		vo.pwd = $("#input-password").val();
		
		if(vo.pwd === ""){
			
			messageBox("방명록에 글남기기", "비밀번호는 필수 입력 항목 입니다. ", function(){$("#input-password").focus();});
			
			return ; 
			
		}
		
		vo.message = $("#ta-message").val();
		
		if(vo.message === ""){
			
			
			messageBox("방명록에 글남기기", "내용은 필수 입력 항목 입니다. ", function(){$("#ta-message").focus();});
			
			return ; 
			
			
		}
		
		// ajax 통신 
		console.log($.param(vo));
		console.log(JSON.stringify(vo));
		
		$.ajax( {
			url : "${pageContext.request.contextPath }/guestbook/api/ajinsert",
			type: "post",
			dataType: "json", // 받아야되는 데이터 타입 
			//data: $.param(vo), 
			data: JSON.stringify(vo), // 자바 객체를 json string로 변경하는 부분 	(반대로도 가능함 )
			//"name= "+name + "&" + 
			//"pwd=" + password + "&"+ "message=" + message,
			//contentType: 'application/json; charset=utf-8', //json 타입으로 데이터를 보낼때 사용함 !!! 그냥 자바 객체로 주고 받을경우는 필요가 없음 (controller. api 수정필요)
			
			success: function(response){

				
					if(response.result === "fail"){
						
						console.error(response.message);
						return;
					}
					
					// rendering (html 만들기)
					
					//console.log(response.data.name);
					render(response.data, true);
					
					// reset form 
					
					$("#add-form").get(0).reset(); //html 엘리먼트 reset을 사용함 
				
			},
			error: function( jqXHR, status, e ){
				console.error( status + " : " + e );
			}
			} );

		
		
		
	});
	
	
	$(window).scroll(function(){
		
		var $window = $(this);
		
		var scrollTop = $window.scrollTop();
		
		var windowHeight = $window.height();
		
		var documentHeight = $(document).height();
		
		//console.log(documentHeight+ ">=" +scrollTop + "+ "+windowHeight );
		
		// 스크롤바 thumb가 바닥전 10px 까지 왔을때 ...
		if(scrollTop + windowHeight +10 > documentHeight ){ // 거의 맨밑일때 
			
			fetchList();
		} 
		
		
	});
	
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
					<textarea id="ta-message" placeholder="내용을 입력해 주세요." name="message"></textarea>
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
			
			<div id="dialog" title="" style="display:none">
  			<p> </p>
			</div>	
			
			
			
			
			
			</div>
			
						
		</div>
		
		
		<div id="dialog-delete-form" title="메세지 삭제 " style="display:none">
		  <p class="validateTips normal">작성시 입력했던 비밀번호를 입력하시오 </p>
		  <p class="validateTips error" style="display:none">비밀번호가 틀립니다.  </p>
		  <form>
		    
		      
		      <input type="hidden" name="no" id ="delete-no" value="" />
		      <input type="password" name="password" id="delete-password" value="" class="text ui-widget-content ui-corner-all">
		 
		      <!-- Allow form submission with keyboard without duplicating the dialog button -->
		      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
		   
		  </form>
		</div>
		
		
		
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/include/foot.jsp" />
	</div>
</body>
</html>