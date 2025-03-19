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
                     <h3><strong>WSTG_BUSL_09: </strong>File Upload Vulnerability</h3>
 
                     <h4>Scenario</h4>
                     <%--                    <jsp:include page="WSTG_ATHZ_01/scenario.jsp"></jsp:include>--%>
                     <div class='markdown'>* [Upload image and view it](<s:url action="bespoke/upload"/>)</div>
                     <h4>Overview</h4>
                                         <div class="markdown">Using path traversal in tandem with file upload to upload malicious jsp file and overwrite existing file.</div>
                     <div>
                         Payload:../../../out/artifacts/dvja_war_exploded/WEB-INF/dvja/a.jsp
                     </div>
 
                     <h4>Reference</h4>
                     <%--                    <div class="markdown">* [https://www.owasp.org/index.php/Top\_10\_2013-A10-Unvalidated\_Redirects\_and\_Forwards](https://www.owasp.org/index.php/Top_10_2013-A10-Unvalidated_Redirects_and_Forwards)--%>
                     <%--                    </div>--%>
                 </div>
             </div>
 
 
         </div>
     </div>
</div>
<jsp:include page="common/Footer.jsp"></jsp:include>
</body>

</html>

