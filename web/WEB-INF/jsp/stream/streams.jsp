<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:html>

    <c:url value="/js/favotirestreams.js" var="href"/><script src="${href}"></script>

    <div id="wrapper" class="pad_top7 page">
        <div class="container">
            <c:url value="/stream/search" var="action"/>
            <ui:navbar active="flows" action="${action}"/>
            <h1><fmt:message key="flows.list"/></h1>
            <c:choose>
                <c:when test="${not empty streams}">
                    <c:forEach items="${streams}" var="stream" varStatus="status">
                        <ui:stream stream="${stream}" count="${status.count}" page="${_page_}"/>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2><fmt:message key="flows.notFound"/></h2>
                </c:otherwise>
            </c:choose>

            <nav>
                <ul class="pager">
                    <li>
                        <c:if test="${_page_ != 0}">
                            <c:url value="/stream/list.html?page=${_page_ - 1}" var="href"/>
                            <a href="${href}"><fmt:message key="previous"/> ${_page_}</a>
                        </c:if>
                    </li>
                     <li>
                        <c:if test="${_page_ != (_count_ - 1) && _count_ != 0}">
                            <c:url value="/stream/list.html?page=${_page_ + 1}" var="href"/>
                            <a href="${href}"><fmt:message key="next"/> ${_page_ + 2}</a>
                        </c:if>
                    </li>
                </ul>
            </nav>

            <ui:modal title="Warning!" id="myModal" action="delete">
                <p><fmt:message key="mess"/></p>
            </ui:modal>

            <ui:navbar2/>
        </div>
    </div>

    <div style="margin-bottom: 61px;">
        <ui:footer/>
    </div>

</ui:html>
