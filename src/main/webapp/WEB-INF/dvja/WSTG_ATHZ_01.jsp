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
                    <h3><strong>WSTG_ATHZ_01: </strong>Directory Traversal</h3>

                    <h4>Scenario</h4>
                    <%--                    <jsp:include page="WSTG_ATHZ_01/scenario.jsp"></jsp:include>--%>
                    <div class='markdown'>* [View Webserver file](<s:url action="bespoke/traverse"/>)</div>
                    <h4>Overview</h4>
                    <div class="markdown">Applications frequently redirect users to other pages, or use internal
                        forwards in a similar manner. Sometimes the target page is specified in an unvalidated
                        parameter, allowing attackers to choose the destination page.

                        Attacker links to unvalidated redirect and tricks victims into clicking it. Victims are more
                        likely to click on it, since the link is to a valid site. Attacker targets unsafe forward to
                        bypass security checks.
                    </div>

                    <h4>Reference</h4>
                    <div class="markdown">*
                        [https://www.owasp.org/index.php/Top\_10\_2013-A10-Unvalidated\_Redirects\_and\_Forwards](https://www.owasp.org/index.php/Top_10_2013-A10-Unvalidated_Redirects_and_Forwards)
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
<jsp:include page="common/Footer.jsp"></jsp:include>
</body>

</html>

