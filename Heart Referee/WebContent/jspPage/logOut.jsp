<%@page import="common.CommonParameters"%>
<% 
	session.removeAttribute(CommonParameters.SESSION_USER);
	session.removeAttribute(CommonParameters.SESSION_NAME);
	request.setAttribute("partition",(String) session.getAttribute(CommonParameters.SESSION_PARTITION));
	request.getRequestDispatcher("index.jsp").forward(request,response);
	/* response.sendRedirect("index.jsp"); */
%>