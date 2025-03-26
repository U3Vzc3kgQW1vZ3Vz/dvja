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
                        <h2>File Display Page</h2>
                    </div>

                    <div class='page-body'>
                        <s:form action="traverse" method="post" theme="bootstrap">
                            <s:textfield
                                    label="Path"
                                    name="path"
                                    placeholder="Enter the file's name"/>

                            <s:submit cssClass="btn btn-primary"/>
                        </s:form>
                    </div>
                </div>
            </div>


            <div class='row'>
                <div class='col-md-12'>
                    <h2>Here's how your file looks like</h2>
                    <hr/>

                    <img src='data:image/jpeg;base64,<s:property value="fileOutput"/>'/>

                </div>
            </div>


        </div>
    </div>
</div>
<jsp:include page="common/Footer.jsp"></jsp:include>
</body>
</html>
