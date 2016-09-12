<jsp:include page="/includes/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h3 class="text-center">Edit a User</h3>
            <form action="users" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-3" for="firstName">First Name:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="firstName" 
                               name="firstName" value='<c:out value="${user.firstName}" />' required>
                        <input type="hidden" name="id" value='<c:out value="${user.id}" />'>
                        <input type="hidden" name="action" value="edit">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="button" class="btn btn-default" onclick='location.href = "users?action=list"'>Cancel</button>
                        <button type="submit" class="btn btn-info" value="edit">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/includes/footer.jsp" />
