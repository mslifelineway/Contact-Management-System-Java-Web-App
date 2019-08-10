

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="../allPlugins.jsp" %>
<s:url var="url_reg_form" value="/reg_form"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
        <%-- CONTENT Area --%>
        <s:url var="url_login" value="/login"/>
        <f:form action="${url_login}" modelAttribute="login_command" class="login-form content-div" >
            <table >
                <tr>
                    <td colspan="2">USER LOGIN FORM</td>
                </tr>
                <tr>
                    <td >username : </td>
                    <td><f:input type="text" path="loginName" placeholder="enter username"/></td>
                </tr>
                <tr>
                    <td>password : </td>
                    <td><f:input type="password" path="loginPassword" placeholder="********" /></td>
                </tr>
                <tr >
                    <td colspan="2" style="text-align: right;">
                        <input type="submit" value="LOGIN" />
                        <br>
                        <c:if test="${err != null}">
                            <p class="error" align="center">${err}</p>
                        </c:if>
                        <c:if test="${param.act eq 'lo' }">
                            <p class="success" align="center">Logout Successfully! Thanks for using contact application.</p>
                        </c:if>
                        <c:if test="${param.act eq 'reg' }">
                            <p class="success" align="center">User Registered successfully! please login...</p>
                        </c:if>
                        <c:if test="${param.act eq 'exists'}">
                            <p class="error" align="center">User Already Exists! Please user another username.</p>
                        </c:if>
                    </td>
                </tr>
                <tr >
                    <td style="text-align: left; font-size: 18px;">
                        <br><br> <a href="${url_reg_form}">Need New Account? Register</a>
                    </td>
                    <td  style="text-align: right; font-size: 18px;">
                        <br><br><a href="#">Forgotten Password?</a>
                    </td>
                </tr>
            </table>
        </f:form> 
    </body>
</html>
