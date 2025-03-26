<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/Head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="common/Navigation.jsp"></jsp:include>
<div class='container' style='min-height: 450px'>
    <div class='row'>
        <div class='col-md-12'>

            <div class='row'>
                <div class="col-md-3">
                    <div class='list-group'>
                        <a href="userSearch" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Find Other Users
                        </a>
                        <a href="ping" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Network Connectivity Check
                        </a>
                        <a href="editUser" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Edit User Details
                        </a>
                        <a href="<s:url action="listProduct"/>" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Product Management
                        </a>
                        <a href="bespoke/upload" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Upload Local File
                        </a>
                        <a href="bespoke/netUpload" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Upload Online File
                        </a>
                        <a href="bespoke/welcome" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Welcome Page
                        </a>
                        <a href="bespoke/traverse" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> View Images
                        </a>
                        <a href="bespoke/serialize" class="list-group-item">
                            <i class="fa fa-angle-double-right"></i> Serializing User Object
                        </a>
                    </div>
                </div>

                <div class="col-md-9">

                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="common/Footer.jsp"></jsp:include>
</body>
</html>
