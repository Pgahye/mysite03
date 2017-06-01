<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	
	$("#checkbutton").click(function(){
			var email = $("#email").val();
		
			if(email == ""){
				return;
			}
			//ajax 통신 시작( 객체로 넣음 )
			$.ajax( {
			    url : "/mysite03/user/api/checkemail?email="+email,
			    type: "get",
			    dataType: "json",
			    data: "",
			//  contentType: "application/json",
			    success: function( response ){
			    	
			    	if(response.data== true){
			    		alert("이미 존재하는 이메일입니다. ");
			    	}else{
			    		
			    		alert("사용가능한 이메일입니다. ");
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
	<div id="container">
		
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id="checkbutton" type="button" value="email 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		
		
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>	
		<c:import url="/WEB-INF/views/include/foot.jsp"/>
		
	</div>
</body>
</html>