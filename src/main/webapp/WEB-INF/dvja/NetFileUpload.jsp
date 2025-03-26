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
                        <s:form action="netUpload" method="post" theme="bootstrap">
                            <s:textfield
                                    label="URL"
                                    name="url"
                                    placeholder="Enter the URL"/>
                            <s:submit cssClass="btn btn-primary"/>
                        </s:form>
                    </div>
                </div>
            </div>


            <div class='row'>
                <div class='col-md-12'>
                    <s:if test="%{textOutput.equals('Not Valid URL')}">
                        <h2>
                            <s:property value="textOutput"></s:property>
                        </h2>
                    </s:if>
                    <s:elseif test="%{textOutput!=null}">
                        <a href="/<s:property value="textOutput"></s:property>"> <s:property
                                value="textOutput"></s:property></a>
                        <h2>Here's how your file looks like</h2>
                        <hr/>

                        <img src='/<s:property value="textOutput"/>'/>
                    </s:elseif>
                    <s:else>
                        <h2>
                            Please upload a file
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
