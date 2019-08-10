
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Dashboard</title>
        <%@include  file="allPlugins.jsp" %>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            if (session.getAttribute("userId") == null) {
                response.sendRedirect("../index");
            }

        %>
        <div class="container">
            <%@include  file="includes/header.jsp" %>
            <%@include  file="includes/menus.jsp" %>
            
            <div class="content-div">
                <h2>Welcome : 
                    <c:if test="${sessionScope.userId != null}">
                        ${userName}
                    </c:if>
                  </h2>
                user dashboard
                content area 
            </div>
            
            <%@include  file="includes/footer.jsp" %>   
        </div>
    </body>
</html>
