<%@page import="com.itwillbs.domain.BoardDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp4/content.jsp</title>
</head>
<body>
<%
//세션에서 로그인정보 가져오기
//String id=(String)session.getAttribute("id");
%>
<h1>글내용보기(로그인 : ${sessionScope.id})</h1>
<%
//BoardDTO boardDTO=(BoardDTO)request.getAttribute("boardDTO");
	%>
<table border="1">
<tr><td>글번호</td><td>${boardDTO.num }</td></tr>
<tr><td>글쓴이</td><td>${boardDTO.name }</td></tr>
<tr><td>글쓴날짜</td><td>${boardDTO.date }</td></tr>
<tr><td>조회수</td><td>${boardDTO.readcount }</td></tr>
<tr><td>글제목</td><td>${boardDTO.subject }</td></tr>
<tr><td>글내용</td><td>${boardDTO.content }</td></tr>
<tr><td colspan="2">
<%
// 로그인 필수 
// 로그인, 글쓴이 일치하면 => 글수정 , 글삭제 버튼 보이기
//if(id != null){
//	if(id.equals(boardDTO.getName())){
		%>
<%-- <input type="button" value="글수정" onclick="location.href='update.bo?num=${boardDTO.num }'"> --%>
<%-- <input type="button" value="글삭제" onclick="location.href='delete.bo?num=${boardDTO.num }'">		 --%>
		<%
//	}
//}
%>

<c:if test="${!(empty sessionScope.id) }">
	<c:if test="${sessionScope.id eq boardDTO.name}">
		<input type="button" value="글수정" onclick="location.href='${pageContext.request.contextPath}/board/update?num=${boardDTO.num }'">
		<input type="button" value="글삭제" onclick="location.href='${pageContext.request.contextPath}/board/delete?num=${boardDTO.num }'">			
	</c:if>
</c:if>

<input type="button" value="글목록" onclick="location.href='${pageContext.request.contextPath}/board/list'">
</td></tr>
</table>	



</body>
</html>



