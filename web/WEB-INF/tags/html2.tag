<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<!DOCTYPE html>
<html>
<head>
    <c:url value="/css/bootstrap.min.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/css/slider.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/fonts/font-awesome-4.1.0/css/font-awesome.min.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/css/netradio.css" var="href"/><link rel="stylesheet" href="${href}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <c:url value="/js/wow.min.js" var="href"/><script src="${href}"></script>
    <c:url value="/css/animate.css" var="href"/><link rel="stylesheet" href="${href}">
</head>

<body>
    <jsp:doBody />
    <c:url value="/js/jquery-1.10.2.min.js" var="href"/><script src="${href}"></script>
    <c:url value="/js/jquery.easing.min.js" var="href"/><script src="${href}"></script>
    <c:url value="/js/bootstrap.js" var="href"/><script src="${href}"></script>
    <c:url value="/js/bootstrap.min.js" var="href"/><script src="${href}"></script>
</body>

</html>