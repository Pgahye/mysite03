<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>



<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>

var View = {
		
		focus : function(){
			
			$("#email").focus(function(){
				
				$(this).css({
					
					"border" : "3px solid #044419",
					"background-Color" : "#90C028"
					
				});
			});
			
				$("#email").blur(function(){
					
					$(this).css({
						
						"border" : "",
						"background-Color" : "#fff"
						
					});
					
				});
				
			
			
		},
		
		

		
		init : function(){
			

			$("#checkimage").hide();
			
			View.focus();
					
		},
		
		
		submit : function(){
			
			$("#join-form").submit(function(){
				
				
				console.log($("#checkimage").is("visible"));
				
				if($("#name").val() == ""){
					alert("이름은 필수 항목 입니다. ");
					return false; //true일때 이동함 
				}
				
				if($("#email").val() == ""){
					alert("이메일은 필수 항목 입니다. ");
					return false; //true일때 이동함 
				}
				
				
				if($("#checkimage").is(':visible')==false){
					
					alert("email 중복확인을 하셔야 합니다. ");
					return false; //true일때 이동함 
					
				}
				
				if($("#password").val() == ""){
					alert("비밀먼호는 필수 항목 입니다. ");
					return false; //true일때 이동함 
				}

				
				if ($("#agree-prov").is(':checked') ==false){
					
					alert("약관에 동의 하셔야 합니다  ");
					return false;
				}
				
			
					
					return true; //true일때 이동함 
				
				
				
			});
			
			
			
		}
		
		
		
}

$(function(){

	View.init();
	

	
	View.submit();
	
	
	$("#checkbutton").click(function(){
	
		//ajax 통신 시작( 객체로 넣음 )
		$.ajax( {
		    url : "/mysite03/user/api/checkemail?email="+$("#email").val(),
		    type: "get",
		    dataType: "json",
		    data: "",
		//  contentType: "application/json",
		    success: function( response ){
		    	
		    	if(response.data== true){
		    		alert("이미 존재하는 이메일입니다. ");
		    		
		    		View.focus();
		    		
		    		
		    	}else{
		    		
		    		alert("사용가능한 이메일입니다. ");
		    		$("#checkimage").show();
		    		$("#checkbutton").hide();
	
		    		
		    	}
		       console.log( response.data );
		    },
		    error: function( jqXHR, status, error ){
		       console.error( status + " : " + error );
		    }

		   });
	 
		
		
		
	
	});
	
	
});

	
			


</script>
</head>
<body>
	<!-- <spring:message code="hello"></spring:message>
		<spring:message code="name" text="이름"></spring:message>	
	 -->
	<div id="container">
		
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		
		<div id="content">
			<div id="user">

				<form:form 
				modelAttribute="userVo"
				id="join-form" 
				name="joinForm" 
				method="post" 
				action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name"><spring:message code="name" text="이름"/></label>
					<input id="name" name="name" type="text" value="${userVo.name }">
					
					
					<spring:hasBindErrors name="userVo">
					   <c:if test="${errors.hasFieldErrors('name') }">
					   		<P style="text-align:left; color:red;">
					        <strong>
					        <spring:message 
	   						  code="${errors.getFieldError( 'name' ).codes[0] }" 				     
	   						  text="${errors.getFieldError( 'name' ).defaultMessage }" />			        
					        </strong>
					   		</P>
					   </c:if>
					</spring:hasBindErrors>
					
					

					<label class="block-label" for="email">이메일</label>
					
					<form:input path="email" id="email" name="email"/>
					
					<img id="checkimage" src="${pageContext.servletContext.contextPath }/assets/images/check.png" style="width: 30px; height : 30px;">
					<input id="checkbutton" type="button" value="email 중복체크" style="display:;">
					
					
					 <p style="margin:0; padding:0; color:red; text-align:left">
					 <form:errors path="email" />
					 </p>
					 

					
					<label class="block-label">패스워드</label>
					<input id="password" name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" id="gender" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" id="gender" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		
		
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>	
		<c:import url="/WEB-INF/views/include/foot.jsp"/>
		
	</div>
</body>
</html>