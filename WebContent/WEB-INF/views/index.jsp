<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="java.util.List"%>
<%@ page import="com.estsoft.DB.MySQLWebDBConnection"%>
<%@ page import="com.estsoft.emailList.dao.EmailListDao"%>
<%@ page import="com.estsoft.emailList.vo.EmailListVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<EmailListVo> list = (List<EmailListVo>) request.getAttribute("list");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트에 가입되었습니다.</h1>
	<p>입력한 정보 내역입니다.</p>


	<!-- 메일정보 리스트 -->
	<%-- list에서 하나씩 빼서 테이블를 채운다--%>

	<!--  c:foreach는 itesms라는 것을 가지고 있고, 그게 list라는 객체로 찾는걸 알 수 있다. -->
	<!-- var가 알아서 vo가 UserVo type인걸 알 수 있다.-->
	<c:forEach items="${list}" var="vo">

		<table border="1" cellpadding="5" cellspacing="2">
			<tr>
				<td align=right>First name:</td>
				<td>${status.count}${vo.firstName}</td>
			</tr>
			<tr>
				<td align=right width="110">Last name:</td>
				<td width="110">${vo.lastName}</td>
			</tr>
			<tr>
				<td align=right>Email address:</td>

				<td>${vo.email}</td>

			</tr>
		</table>
		<br>
	</c:forEach>
	<p>
		<a href="/emailList3/form">추가메일 등록</a>
	</p>
	<br>

</body>
</html>
