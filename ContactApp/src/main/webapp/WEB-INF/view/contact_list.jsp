
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>User Registration Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include  file="allPlugins.jsp" %>

    <body>
        <%
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            if (session.getAttribute("userId") == null) {
                System.out.println("------------ user id is null ------------");
                System.out.println(session.getAttribute("userId"));
                response.sendRedirect("../index");
            }else{
                System.out.println("------------ user id is not null ------------");
                System.out.println(session.getAttribute("userId"));
            }

        %>
        <!--<div class="container">-->
        <%@include  file="includes/header.jsp" %>
        <%@include  file="includes/menus.jsp" %>
        <%-- CONTENT Area --%>
       
        <table class="content-div" style="width:100%; background: lightblue;">
            <tr>
                <td >
                    <table style="width:100%; background: lightblue;">
                        <tr>
                            <td style="text-align: right;padding: 10px;">
                                <s:url var="url_search_contact" value="/user/search_contact" />
                                <form action="${url_search_contact}" class="form-inline">
                                    <b class="form-control" style="float: left">YOUR CONTACT LIST!</b>
                                    <input class="form-control" type="text" value="${param.searchText}" name="searchText" placeholder="type to search contact..."/>
                                    <button class="form-control">Find</button>
                                </form>
                            </td>
                        </tr>
                    </table>

                </td>
            </tr>
            <tr>
                <td >
                    <s:url var="url_delete_bulk_contact" value="/user/delete_bulk_contact"/>
                    <form action="${url_delete_bulk_contact}"> 
                        <table class="table table-bordered content-div" style="border: 1px solid black; width:100%; background: lightblue;padding:20px;">
                            <tr>
                                <th style="text-align: center">SELECT</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Remark</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>

                            <c:if test="${empty contactLists}">
                                <tr>
                                    <td colspan="8" class="error" align="center">No Record Found.</td>
                                </tr>
                            </c:if>

                            <c:forEach var="c" items="${contactLists}" varStatus="st">
                                <tr>
                                    <td style="text-align: center;" >
                                        <input type="checkbox" name="cids" value="${c.contactId}" />
                                    </td>
                                    <td>${c.name}</td>
                                    <td>${c.phone}</td>
                                    <td>${c.email}</td>
                                    <td>${c.address}</td>
                                    <td>${c.remark}</td>

                                    <s:url var="url_update_contact" value="/user/update_contact">
                                        <s:param name="cid" value="${c.contactId}"/>
                                    </s:url>
                                    <td ><a href="${url_update_contact}" style="color:#c15436; font-weight: bold">Update</a></td>

                                    <s:url var="url_del_contact" value="/user/del_contact">
                                        <s:param name="cid" value="${c.contactId}"/>
                                    </s:url>
                                    <td><a href="${url_del_contact}" style="color:#c15436; font-weight: bold">Delete</a></td>
                                </tr> 

                            </c:forEach> 
                            <tr>
                                <td colspan="8">
                                    <button type="submit" >Delete Selected Record</button><br>
                                </td>
                            </tr>

                            <c:if test="${param.act eq 'all_deleted'}">
                                <tr>
                                    <td colspan="8" style="text-align: center">
                                        <p class="success">Selected Record deleted successfully!</p>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${param.del eq 'ok'}">
                                <tr>
                                    <td colspan="8" style="text-align: center">
                                        <p class="success">Record deleted successfully!</p>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${param.act eq 'updated'}">
                                <tr>
                                    <td colspan="8" style="text-align: center">
                                        <p class="success">Record Updated successfully!</p>
                                    </td>
                                </tr> 
                            </c:if>

                            <c:if test="${param.act eq 'saved'}">
                                <tr>
                                    <td colspan="8" style="text-align: center">
                                        <p class="success">Record Saved successfully!</p>
                                    </td>
                                </tr> 
                            </c:if>


                        </table>
                    </form>
                </td>
            </tr>  
        </table> <br><br><br>
        <%@include  file="includes/footer.jsp" %> 
    </body>

</html>