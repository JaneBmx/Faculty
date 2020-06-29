<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="main"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="main_block">
    <h2><fmt:message bundle="${simple}" key="index.welcome"/></h2>
    <p><fmt:message bundle="${simple}" key="index.describe"/></p>
    <hr>
    <table>
        <tr>
            <td colspan="2"><h3><fmt:message bundle="${simple}" key="index.common"/>:</h3></td>
        </tr>
        <tr>
            <td><fmt:message bundle="${simple}" key="index.count.faculties"/>:</td>
            <td>77</td>
        </tr>

        <tr>
            <td><fmt:message bundle="${simple}" key="index.count.places"/>:</td>
            <td>55</td>
        </tr>

        <tr>
            <td><fmt:message bundle="${simple}" key="index.count.free"/>:</td>
            <td>777</td>
        </tr>

        <tr>
            <td><fmt:message bundle="${simple}" key="index.count.enrolls"/>:</td>
            <td>77</td>
        </tr>
    </table>
    <hr>

    <div class="news">
        <h3><fmt:message bundle="${simple}" key="index.news"/></h3>
        <fmt:message bundle="${simple}" key="index.new1"/>.<br>
        <fmt:message bundle="${simple}" key="index.blabla"/>
        <fmt:message bundle="${simple}" key="index.blabla"/>
        <fmt:message bundle="${simple}" key="index.blabla"/></div>
    <br>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>