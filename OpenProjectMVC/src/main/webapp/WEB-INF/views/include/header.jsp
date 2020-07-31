<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%
	//로그인 여부 체크! ( session에 memberInfo 객체 )
	boolean loginStatus = session.getAttribute("memberInfo")==null?false:true;
	/* boolean loginStatus = ${sessionScope.memberInfo}==null?false:true; */
	
	//String checkId=(String)session.getAttribute("id");
	
%>
	<%-- <c:forEach items="${sessionScope.memberInfo}" var="memberInfo" varStatus="status">
	</c:forEach> --%>

	<h1 id="header">OpenProject</h1>
		<ul id="nav">
			<li><a href="<c:url value='/member/memberRegForm'/>">회원가입</a></li>
			<%-- <li><a href="${pageContext.request.contextPath}/member/memberRegForm.do">회원가입</a></li> --%>
			<%-- <li><a href="<c:url value="/member/memberRegForm.do"/>">회원가입</a></li> --%>
			
			<li><a href="${pageContext.request.contextPath}/member/memberList.do">회원리스트</a></li>
			
	<% 
	if(loginStatus){
	%>
			<li><a href="<c:url value="/member/logout.jsp"/>">로그아웃</a></li>
	<% }else { %>
			<li><a href="<c:url value="/member/memberLoginForm.jsp"/>">로그인</a></li>
			
	<% } %>
			<li><a href="<c:url value="/member/myPage/myPage.jsp"/>">마이페이지</a></li>
			<li><a href="<c:url value="/guestBook/list.jsp"/>">방명록(비회원)</a></li>
			<li><a href="<c:url value="/board/list.jsp"/>">게시판(회원제)</a></li>
	</ul>

    