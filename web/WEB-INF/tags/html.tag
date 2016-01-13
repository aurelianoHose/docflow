<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<!DOCTYPE html>
<html>
<head>
    <c:url value="/css/bootstrap.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/css/bootstrap-theme.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/css/bootstrap_state.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/css/netradio.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/fonts/font-awesome-4.1.0/css/font-awesome.min.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/css/jquery.tagit.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/css/tagit.ui-zendesk.css" var="href"/><link rel="stylesheet" href="${href}">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <c:url value="/js/jquery-ui.js" var="href"/><script src="${href}"></script>
    <c:url value="/css/jquery-ui.css" var="href"/><link rel="stylesheet" href="${href}">

    <c:url value="/js/tag-it.min.js" var="href"/><script src="${href}"></script>

    <c:url value="/js/bootstrap.min.js" var="href"/><script src="${href}"></script>
    <c:url value="/js/flow.js" var="href"/><script src="${href}"></script>
    <c:url value="/js/netradio.js" var="href"/><script src="${href}"></script>
    <c:url value="/js/mediaelement-and-player.min.js" var="href"/><script src="${href}"></script>

    <c:url value="/css/pace-theme-flash.min.css" var="href"/><link rel="stylesheet" href="${href}">
    <c:url value="/js/pace.js" var="href"/><script src="${href}"></script>
</head>
<body>
    <jsp:doBody />
</body>
</html>