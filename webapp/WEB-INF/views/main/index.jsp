<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>${sitevo.title }</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	
	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
	
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="${pageContext.request.contextPath }${sitevo.file }" width="500">
					<h2>${sitevo.welcomeMessage }</h2>
					<p>
						${sitevo.description}.<br><br>
						<a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>	
		<c:import url="/WEB-INF/views/include/foot.jsp"/>
		
	
	</div>
</body>
</html>