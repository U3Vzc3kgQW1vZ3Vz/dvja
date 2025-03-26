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
                <div class='col-md-12'>
                    <div class='page-header'>
                        <h2>Welcome Page</h2>
                    </div>

                    <div class='page-body'>
                        <s:form action="welcome" method="post" theme="bootstrap">
                            <s:textfield
                                    label="Name"
                                    name="name"
                                    placeholder="Enter your name"/>

                            <s:submit cssClass="btn btn-primary"/>
                        </s:form>
                    </div>
                </div>
            </div>


            <div class='row'>
                <div class='col-md-12'>
                    <hr/>
                    <s:if test="%{name!=null}">
                        <pre><s:property value="name"/></pre>
                    </s:if>
                    <s:else>
                        <h2>
                            Please enter your name
                        </h2>
                    </s:else>

                </div>
            </div>


        </div>
    </div>
</div>
<jsp:include page="common/Footer.jsp"></jsp:include>
</body>
</html>
