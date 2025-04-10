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
                <div class='col-md-6 col-md-offset-3'>
                    <div class='page-header'>
                        <h2>Reset Password</h2>
                    </div>

                    <s:actionerror theme="bootstrap"/>
                    <s:actionmessage theme="bootstrap"/>
                    <s:fielderror theme="bootstrap"/>

                    <div class='page-body'>
                        <s:form action="resetPasswordExecute" method="post" theme="bootstrap">
                            <s:password
                                    label="Password"
                                    name="password"
                                    placeholder="Enter password"/>
                            <s:password
                                    label="Password Confirmation"
                                    name="passwordConfirmation"
                                    placeholder="Enter password confirmation"/>

                            <s:hidden name="login"/>
                            <s:hidden name="key"/>

                            <s:submit cssClass="btn btn-primary"/>
                        </s:form>

                        <br/>
                        <a href='<s:url action="login"/>'>Already registered? Login here</a>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
<jsp:include page="common/Footer.jsp"></jsp:include>
</body>
</html>
