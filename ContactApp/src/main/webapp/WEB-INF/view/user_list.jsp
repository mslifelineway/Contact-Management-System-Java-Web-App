
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
                response.sendRedirect("../index.jsp");
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
                                    <b class="form-control" style="float: left">USER LIST!</b>
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
                                <th>Sl.No.</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Login Name</th>
                                <th>User Role</th>
                                <th>Login Status</th>
                            </tr>

                            <c:if test="${empty userLists}">
                                <tr>
                                    <td colspan="8" class="error" align="center">No Record Found.</td>
                                </tr>
                            </c:if>

                            <c:forEach var="u" items="${userLists}" varStatus="st">
                                <tr>
                                    <td>${st.count}</td>
                                    <td>${u.userName}</td>
                                    <td>${u.userPhone}</td>
                                    <td>${u.userEmail}</td>
                                    <td>${u.userAddress}</td>
                                    <td>${u.userLoginName}</td>
                                    <td>
                                        <c:if test="${u.userRole ==1}">
                                            <p> Admin </p>
                                        </c:if>
                                        <c:if test="${u.userRole ==2}">
                                            <p>  Normal User</p>
                                        </c:if>
                                    </td>
                                    <td>
                                        <select class="id_${u.userId}" onchange="changeLoginStatus(this.value,${u.userId})">
                                            <option value="1">Active</option>
                                            <option value="2">Block</option>
                                        </select>
                                        <script type="text/javascript">
                                                $('.id_${u.userId}').val(${u.userLoginStatus});
                                        </script>
                                    </td>
                                    <%--<td>${u.userLoginStatus}</td> --%>
                                </tr>
                            </c:forEach> 
                        </table>
                    </form>
                </td>
            </tr>  
        </table> <br><br><br>  
        <%@include  file="includes/footer.jsp" %> 
        <script type="text/javascript">
            function changeLoginStatus(lstatus, uid) {
                //--- changing login status of a user. this is done only by Admin
                $.ajax({
                    type: "POST",
                    url: "change_login_status",
                    data: {userId: uid, loginStatus: lstatus},
                    success: function (data) {
                        alert(data);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                        console.log("ERROR: ", jqXHR);
                        display(e);

                    }
                });
            }


        </script>
    </body>

</html>