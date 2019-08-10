
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include  file="../allPlugins.jsp" %>
        <s:url var="url_logout" value="/logout"/>
        <s:url var="url_login_form" value="/index"/>
        <s:url var="url_add_contact" value="/add_contact"/>
        <s:url var="url_contact_list" value="/user/contact_list"/>
   
        <div class="menus-div">
            <c:if test="${sessionScope.userId == null}">
                <%-- user is not logged In : Guest user -- Guest Menu --%>
                <a href="${url_login_form}">Home</a> | <a href="#">About Us</a> | <a href="#">Contact Us</a> | <a href="#">Help</a>
            </c:if>
            
              <c:if test="${sessionScope.userId != null && sessionScope.userRole==1}">
               <%-- Admin is logged In : Admin Menu --%>
               <s:url var="url_admin_user_list" value="/admin/user_list" />
                <a href="${url_admin_user_list}">User List</a> | <a href="${url_logout}">Logout</a>
            </c:if>
                
                <c:if test="${sessionScope.userId != null && sessionScope.userRole==2}">
                <%-- General user is logged In : User Menu --%>
                <a href="${url_add_contact}">Add Contact</a> | <a href="${url_contact_list}">Contact List</a> | <a href="${url_logout}">Logout</a>
            </c:if>
        </div>
  
