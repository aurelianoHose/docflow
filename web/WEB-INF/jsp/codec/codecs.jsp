<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<body>
<h1><fmt:message key="codecs"/></h1>
  <c:url value="/codec/codec.html" var="href"/><a href="${href}"><fmt:message key="codec.new"/></a>
  <c:choose>
  <c:when test="${not empty codecs}">
  <table>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>&nbsp;</td>
    </tr>
    <c:forEach items="${codecs}" var="codec" varStatus="status">
    <tr>
        <td>${status.count}</td>
        <td><c:url value="/codec/codec.html?id=${codec.id}" var="href" /><a href="${href}">${codec.name}</a></td>
        <td><c:url value="/codec/delete.html?id=${codec.id}" var="href" /><a href="${href}">x</a></td>
    </tr>
    </c:forEach>
  </table>
  </c:when>
  <c:otherwise>
  <h2><fmt:message key="codecs.notFound"/></h2>
  </c:otherwise>
  </c:choose>
</body>
</html>
