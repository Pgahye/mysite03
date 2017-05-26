<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
 
	//UserVo authUser = (UserVo)session.getAttribute("authUser");
	
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="modifyForm" method="post" action="${pageContext.servletContext.contextPath }/user/modify">
					<label class="block-label" for="name">이름</label>
					<input type="hidden" name="no" value="${authUser.no }">
					<input id="name" name="name" type="text" value="${authUser.name }">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${authUser.email }">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					
					<fieldset>
						<legend>성별</legend>
						
					<c:choose>
							
					<c:when test="${authUser.gender == 'female'}">
					
						<label>남</label> <input type="radio" name="gender" value="male" >
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						
					</c:when>
					
					<c:when test="${authUser.gender == 'male'}">
					
						<label>남</label> <input type="radio" name="gender" value="male" checked ="checked">
						<label>여</label> <input type="radio" name="gender" value="female">
						
					</c:when>
					
					</c:choose>
						
					</fieldset>
					
				
					
					<input type="submit" value="수정하기">
					
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