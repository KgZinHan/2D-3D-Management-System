<%@page import="common.CommonParameters"%>
<% 
	session.removeAttribute(CommonParameters.SESSION_USER);
	session.removeAttribute(CommonParameters.SESSION_NAME);
	session.invalidate();
	response.sendRedirect("index.jsp");
%>