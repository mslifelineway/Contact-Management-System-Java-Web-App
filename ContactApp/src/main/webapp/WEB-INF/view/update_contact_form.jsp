
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
        <s:url var="url_save_contact" value="/save_contact"/>
        <f:form action="${url_save_contact}" modelAttribute="add_contact_command" class="reg-form content-div" >
            <table>
                <tr>
                    <td colspan="2">ADD CONTACT FORM</td>
                </tr>
                <tr>
                    <td >Name : </td>
                    <td><f:input type="text" path="contact.name" placeholder=""/></td>
                </tr>
                <tr>
                    <td >Phone : </td>
                    <td><f:input type="number" path="contact.phone" placeholder=""/></td>
                </tr>
                <tr>
                    <td >Email : </td>
                    <td><f:input type="email" path="contact.email" placeholder=""/></td>
                </tr>
                <tr>
                    <td >Address : </td>
                    <td><f:input type="text" path="contact.address" placeholder=""/></td>
                </tr>
                <tr>
                    <td >Remark : </td>
                    <td><f:input type="text" path="contact.remark" placeholder=""/></td>
                </tr>
                <tr >
                    <td colspan="2" style="text-align: right;">
                        <input type="submit" value="Save" />
                        <br>
                        <c:if test="${param.act eq 'already_saved'}">
                            <p class="error">This contact is already saved by this Name!<br> 
                                (hint : use another contact or name.)</p>
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
