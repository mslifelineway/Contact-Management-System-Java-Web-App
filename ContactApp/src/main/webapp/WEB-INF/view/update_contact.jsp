
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Registration Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <!--<div class="container">-->
        <%@include  file="includes/header.jsp" %>
        <%@include  file="includes/menus.jsp" %>
        <%-- CONTENT Area --%>
        <s:url var="url_save_update_contact" value="/user/save_updated_contact"/>
        <f:form action="${url_save_update_contact}" modelAttribute="update_contact_command" class="reg-form content-div" >
            <table>
                <tr>
                    <td colspan="2">UPDATE CONTACT FORM</td>
                </tr>
                <tr>
                    <td >Name : </td>
                    <td><f:input type="text" path="name"/></td>
                </tr>
                <tr>
                    <td >Phone : </td>
                    <td><f:input type="number" path="phone"/></td>
                </tr>
                <tr>
                    <td >Email : </td>
                    <td><f:input type="email" path="email"/></td>
                </tr>
                <tr>
                    <td >Address : </td>
                    <td><f:input type="text" path="address"/></td>
                </tr>
                <tr>
                    <td >Remark : </td>
                    <td><f:input type="text" path="remark"/></td>
                </tr>
                <tr >
                    <td colspan="2" style="text-align: right;">
                        <input type="submit" value="Save" />
                        <br>
                        <c:if test="${param.act eq 'failed'}">
                            <p class="error">Failed to save the contact</p>
                        </c:if>
                        <c:if test="${param.act eq 'invalid_contact'}">
                            <p class="error">Invalid contact, contact length is too large!</p>
                        </c:if>
                        <c:if test="${param.act eq 'missing_field'}">
                            <p class="error">Fields are missing!</p>
                        </c:if>
                        <c:if test="${param.act eq 'saved'}">
                            <p class="success">Contact Saved Successfully!</p>
                        </c:if>
                        <c:if test="${param.empty_field != null}">
                            <p class="error">${param.empty_field}</p>
                        </c:if>
                        <c:if test="${err != null}">
                            <p class="error">${err}</p>
                        </c:if>
                    </td>
                </tr> 
            </table>
        </f:form>  
        <%@include  file="includes/footer.jsp" %>   
        <!--</div>-->
    </body>

</html>
