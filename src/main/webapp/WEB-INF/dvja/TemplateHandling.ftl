
<html>
<head>
    <title>Hello</title>
</head>
<body>
<@s.form method="post" name="inputform" action="welcome">
    <@s.textarea label="Details" name="name" rows=5 cols=40 />
    <@s.submit value="Save" align="center"/>
</@s.form>
<@s.if test="%{name==null}">
    <h2>
        Enter Name
    </h2>
</@s.if>
<@s.else>
    Welcome ${name}
</@s.else>
</body>
</html>

