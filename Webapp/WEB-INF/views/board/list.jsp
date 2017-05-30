<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/list" method="post">
					<input type="text" id="keyword" name="keyword" value="${map.keyword }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					
						<c:set var="count" value="${fn:length(map.list) }"/>
						
					
						<c:forEach items="${map.list }" var="vo" varStatus="status">
				
						
					<tr>
						<td>${vo.no}</td>
						<c:choose>
							<c:when test="${vo.dep>=1 }">
							<td class="left" style="padding-left:${vo.dep*10}px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
							</c:when>
							<c:otherwise> 
								<td class="left">
							</c:otherwise>
						
						</c:choose>					
										
						<a href="${pageContext.servletContext.contextPath }/board/view/${vo.no}">${vo.title }</a>
						</td>
						
						<td>${vo.user_name }</td>
						<td>${vo.hit }</td>
						<td>${vo.reg_date }</td>
						<td><a href="${pageContext.servletContext.contextPath }/board/delete/${vo.no}" class="del">삭제</a></td>
					</tr>
					

					
					</c:forEach>	
					

					
				</table>
				
	
				
				<div class="pager">
				
					<ul>
						<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${map.prepage }&keyword=${map.keyword }">◀</a></li>
						
						<c:forEach var='i' begin='1' end='${sum }'>
								<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${i }&keyword=${map.keyword }">${i }</a></li>
						</c:forEach>
					
					<!-- <li class="selected">3</li>  -->	 
					
						<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${map.nextpage }&keyword=${map.keyword }">▶</a></li>
					</ul>
					
					
				</div>		
						
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/include/foot.jsp" />
	</div>
</body>
</html>