<jsp:include page="/includes/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h3 class="text-center">Current Users</h3>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th></th>
                            <th>First Name</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="counter" value="${0}" scope="page" />
                        <c:forEach var="user" items="${users}">
                            <c:set var="counter" value="${counter + 1}" scope="page" />
                            <tr>
                                <td>${counter}</td>
                                <td>${user.firstName}</td>
                                <td><button type="button" class="btn btn-danger" onclick='location.href = "users?action=delete&id=${user.id}"'>Delete</button></td>
                                <td><button type="button" class="btn btn-info" onclick='location.href = "users?action=edit&id=${user.id}"'>Edit</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/includes/footer.jsp" />
