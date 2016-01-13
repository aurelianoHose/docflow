<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html>

    <div id="wrapper" class="page pad_top7">
        <div class="container">

            <c:url value="/searchGenres" var="action" />
            <ui:navbar active="genres" action="${action}"/>
            <sec:authorize ifAnyGranted="user" ifNotGranted="admin"><ui:title title="genre.list"></ui:title></sec:authorize>

            <c:choose>
                <c:when test="${not empty genres}">
                    <sec:authorize ifAnyGranted="admin">
                        <ui:genresForAdmin />
                    </sec:authorize>
                    <sec:authorize ifAnyGranted="user" ifNotGranted="admin">
                        <ui:genresForUsers />
                    </sec:authorize>

                </c:when>
                <c:otherwise>
                    <h2><fmt:message key="genres.notFound" /></h2>
                </c:otherwise>
            </c:choose>

            <ui:modal title="Warning!" id="myModal" action="/deletegenre">
                <p><fmt:message key="mess"/></p>
            </ui:modal>

        </div>
    </div>

    <ui:footer/>

</ui:html>

