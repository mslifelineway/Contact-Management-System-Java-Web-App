
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Registration Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include  file="allPlugins.jsp" %>
    </head>
    <body>
        <!--<div class="container">-->
        <%@include  file="includes/header.jsp" %>
        <%@include  file="includes/menus.jsp" %>
        <%-- CONTENT Area --%>
        <s:url var="url_reg" value="/register"/>
        <f:form action="${url_reg}" modelAttribute="reg_command" class="reg-form content-div" >
            <table>
                <tr>
                    <td colspan="2">USER REGISTRATION FORM</td>
                </tr>
                <tr>
                    <td >Name : </td>
                    <td><f:input type="text" path="user.userName" placeholder="enter your name"/></td>
                </tr>
                <tr>
                    <td >Phone : </td>
                    <td><f:input type="number" path="user.userPhone" placeholder=""/></td>
                </tr>
                <tr>
                    <td >Email : </td>
                    <td><f:input type="email" path="user.userEmail" placeholder="enter email address"/></td>
                </tr>
                <tr>
                    <td >Address : </td>
                    <td><f:input type="text" path="user.userAddress" placeholder="enter your address"/></td>
                </tr>
                <tr>
                    <td >username : </td>
                    <td><f:input type="text" path="user.userLoginName" placeholder="enter username"/></td>
                </tr>
                <tr>
                    <td>password : </td>
                    <td><f:input type="password" path="user.userPassword" placeholder="********" /></td>
                </tr>
                <tr >
                    <td colspan="2" style="text-align: right;">
                        <input type="submit" value="Register" />
                        <br>
                        <c:if test="${param.empty_field != null}">
                            <p class="error" align="center">${param.empty_field}</p>
                        </c:if>
                        <c:if test="${err != null}">
                            <p class="error" align="center">${err}</p>
                        </c:if>
                    </td>
                </tr>
                <tr >
                    <td style="text-align: left; font-size: 16px;">
                        <s:url var="url_login_form" value="/index"/>
                        <br><br> <a href="${url_login_form}">Back to Sign In? Login</a>
                    </td>
                </tr>

            </table>
        </f:form>  
        <%@include  file="includes/footer.jsp" %>   
        <!--</div>-->
    </body>

</html>
