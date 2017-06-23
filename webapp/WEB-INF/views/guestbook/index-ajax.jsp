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

					<li data-no='${count - status.index }'>
						<strong>${vo.name }</strong>
						<p>
							${fn:replace(vo.message, newLine, "<br>") }
						</p>
		
						<a href='${pageContext.servletContext.contextPath }/guestbook/delete/${vo.no }' data-no=''>삭제</a> 
					</li>

									
				</ul>
				
			</c:forEach>	
			</div>
						
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/include/foot.jsp" />
	</div>
</body>
</html>