<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="messages">
<!DOCTYPE html>
<html>
<body>
<h1><fmt:message key="codec"/></h1>
    <c:url value="/codec/save.html" var="href" />
    <form action="${href}" method="POST">
        <input type="hidden" name="id" value="${codec.id}">
        <input type="text" name="name" value="${codec.name}">
        <button type="submit"><fmt:message key="save" /></button>
    </form>
</body>
</html>
</fmt:bundle>
