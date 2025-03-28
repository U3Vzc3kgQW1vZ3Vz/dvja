<%@ taglib prefix="s" uri="/struts-tags" %>
<div class='list-group' style="overflow: scroll;height: 90%;max-height: 500px">
    <a href='<s:url action="a1_injection"/>' class='list-group-item'>
        <i class='fa fa-angle-double-right'></i> A1: Injection
    </a>
    <a href='<s:url action="a2_broken_auth"/>' class='list-group-item'>
        <i class='fa fa-angle-double-right'></i> A2: Broken Authentication and Session Management
    </a>
    <a href='<s:url action="a3_xss"/>' class='list-group-item'>
        <i class='fa fa-angle-double-right'></i> A3: Cross-site Scripting (XSS)
    </a>
    <a href="<s:url action="a4_idor"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> A4: Insecure Direct Object Reference (IDOR)
    </a>
    <a href="<s:url action="a5_security_misconfiguration"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> A5: Security Misconfiguration
    </a>
    <a href="<s:url action="a6_sensitive_data"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> A6: Sensitive Data Exposure
    </a>
    <a href="<s:url action="a7_missing_access_control"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> A7: Missing Function Level Access Control
    </a>
    <a href="<s:url action="a8_csrf"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> A8: Cross-site Request Forgery (CSRF)
    </a>
    <a href="<s:url action="a9_vuln_component"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> A9: Using Components with Known Vulnerability
    </a>
    <a href="<s:url action="a10_redirect"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> A10: Unvalidated Redirects and Forwards
    </a>
    <a href="<s:url action="WSTG_ATHZ_01"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> WSTG_ATHZ_01: Directory Traversal
    </a>
    <a href="<s:url action="WSTG_BUSL_09"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> WSTG_BUSL_09: File Upload Vulnerability
    </a>
    <a href="<s:url action="WSTG_INPV_04"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> WSTG_INPV_04: HTTP Parameter Pollution
    </a>
    <a href="<s:url action="WSTG_INPV_11"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> WSTG_INPV_11: Code Injection
    </a>
    <a href="<s:url action="WSTG_INPV_18"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> WSTG_INPV_18: Server Side Template injection
    </a>
    <a href="<s:url action="WSTG_INPV_19"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> WSTG_INPV_19: Server Side Request Forgery
    </a>
    <a href="<s:url action="WSTG_INPV_20"/>" class="list-group-item">
        <i class="fa fa-angle-double-right"></i> WSTG_INPV_20: Deserialization Vulnerability
    </a>

</div>