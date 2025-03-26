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
                <div class='col-md-3'>
                    <jsp:include page="common/Menu.jsp"></jsp:include>
                </div>
                <div class='col-md-9'>
                    <h3><strong>A2: </strong>Broken Authentication and Session Management</h3>

                    <h4>Scenario</h4>
                    <jsp:include page="a2_broken_auth/scenario.jsp"></jsp:include>

                    <h4>Overview</h4>
                    <jsp:include page="a2_broken_auth/description.jsp"></jsp:include>

                    <h4>Reference</h4>
                    <jsp:include page="a2_broken_auth/reference.jsp"></jsp:include>
                </div>
            </div>


        </div>
    </div>
</div>
<jsp:include page="common/Footer.jsp"></jsp:include>
</body>
</html>
