<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
[
<c:forEach items="${user_list}" var="user">
    {
    id: '${user.id}',
    name: '${user.name}',
    surname: '${user.surname}',
    email: '${user.email}',
    login: '${user.login}'
    }
    ,
</c:forEach>
]