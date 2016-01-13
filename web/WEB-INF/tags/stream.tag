<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="stream" required="true" rtexprvalue="true" type="com.netradio.entity.Stream"%>
<%@ attribute name="count" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="page" required="true" rtexprvalue="true" type="java.lang.Integer" %>

<div class="highlight">
  <div class="row">
    <div class="container-fluid " id="portfolio">
        <div class="col-sm-4  portfolio-item" >
        <c:url value="/image?id=${stream.id}" var="href1" />
            <a href="#" class="portfolio-link" onclick="play('${stream.link}', '${stream.flow}', '${href1}');" data-toggle="modal">
                <div class="caption">
                    <div id="div_title" class="caption-content">
                        <i id="${stream.link}" class="fa fa-play-circle fa-5x"></i>
                    </div>
                </div>
                <c:url value="/image?id=${stream.id}" var="href" />
                <div class="item-image" style="background: url(${href}) no-repeat center center; background-size: auto 100%; background-color: #626B78;">
                </div>
            </a>
        </div>
        <div class="col-sm-8 item_text">

            <sec:authorize ifAnyGranted="admin">
                <button class="btn btn-danger delete" name="id" value="${stream.id}" data-toggle="modal" data-target="#myModal">X</button>
            </sec:authorize>
                <c:set var="f" value="false"/>

                <c:forEach items="${favorites}" var="fav" varStatus="status">
                    <c:if test="${fav eq stream.id}">
                        <c:set var="f" value="true"/>
                    </c:if>
                </c:forEach>

                <c:choose>
                    <c:when test="${f eq true}">
                        <a class="delete2">
                            <i class="fa fa-star fa-2x" onclick="chngFavorite(this,${stream.id}, '${pageContext.request.userPrincipal.name}')"></i>
                        </a>
                    </c:when>

                    <c:otherwise> 
                        <a class="delete2">
                            <i class="fa fa-star-o fa-2x" onclick="chngFavorite(this, ${stream.id}, '${pageContext.request.userPrincipal.name}')"></i>
                        </a>
                    </c:otherwise>
                 </c:choose>

                <sec:authorize ifAnyGranted="admin">
                <div>
                    <c:url value="/stream/stream.html?id=${stream.id}" var="href" />
                    <a href="${href}"><h3><span class="badge font19">${count + countStreams * page}</span>  ${stream.flow}</h3></a>
                </div>
                </sec:authorize>


            <sec:authorize ifAnyGranted="user" ifNotGranted="admin">
                <div><h3><span class="badge font19" >${count + countStreams * page}</span>  ${stream.flow}</h3></div>
            </sec:authorize>
            <div class="row">
                <div class="col-sm-3 align_l bold">
                    <fmt:message key="link"/> 
                </div>
                <div class="col-sm-7">
                    ${stream.link}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3 align_l bold">
                    <fmt:message key="bitrate"/> 
                </div>
                <div class="col-sm-7">
                    ${stream.bitrade}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3 align_l bold">
                    <fmt:message key="codec"/> 
                </div>
                <div class="col-sm-7">
                   ${stream.codecName}
                </div>
            </div>
            <sec:authorize ifAnyGranted="admin">
                <div class="row">
                    <div class="col-sm-3 align_l bold">
                        <fmt:message key="created.by"/> 
                    </div>
                    <div class="col-sm-7">
                        ${stream.userName}
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3 align_l bold">
                        <fmt:message key="time.adding"/> 
                   </div>
                    <div class="col-sm-7">
                        <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${stream.added}"/> 
                    </div>
                </div>
                <c:if test="${not empty stream.modified}">
                    <div class="row">
                        <div class="col-sm-3 align_l bold">
                            <fmt:message key="modification.time"/> 
                        </div>
                        <div class="col-sm-7">
                            <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${stream.modified}"/>
                        </div>
                    </div>
                </c:if>
            </sec:authorize>
            <div class="row">
                <div class="col-sm-3 align_l bold">
                    <fmt:message key="genres"/>
                </div>
                <div class="col-sm-7">
                
                <c:forEach items="${stream.genres}" var="genre">
                <c:url value="/stream/searchbygenre.html?search=${genre.name}" var="href"></c:url>
                    <a href="${href}">#${genre.name}</a>
                </c:forEach>
                </div>
            </div>
        </div>

    </div>
  </div>
</div>