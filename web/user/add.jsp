<jsp:include page="/includes/header.jsp" />

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2 class="text-center">Add New User</h2>
            <form action="users" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-3" for="firstName">First Name:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="firstName" name="firstName" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-default" value="add">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/includes/footer.jsp" />
