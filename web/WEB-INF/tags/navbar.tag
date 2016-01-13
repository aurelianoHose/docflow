<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="active" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="false" rtexprvalue="true" type="java.lang.String" %>
<script>
var items = [];
var url;
var action = "${action}";
var column;
$( document ).ready(function() {

    if(action == '/netradio/stream/search') {
    url = "http://localhost:8080/netradio/ws/streams";
    column = "flow";
    } else
        if(action == '/netradio/searchGenres') {
            url = "http://localhost:8080/netradio/ws/genres";
            column = "name";
            
        } else
            if(action == '/netradio/searchUsers') {
                url = "http://localhost:8080/netradio/ws/users";
                column = "name";
            }
    if(url != null){
    $.ajax({
        url : url,
        success : function(data) {
            items =  $.map(data, function(el) {
                if (column == "flow") {
                return el.flow;
                } else { 
                    return el.name;
                    }
                })
                $("#qsearch").autocomplete({
                    source: items
                  });
            }
    });
    }
});
</script>
<nav class="navbar navbar-default navbar-fixed-top">
<div class="container">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <c:url value="/stream/listA.html?fl=true" var="href"/><a href="${href}" style="text-decoration:none;">
        <span class="navbar-brand">
          <c:url value="/images/z.png" var="href1"/><img alt="Brand " src="${href1}">
        </span>
        <span class="navbar-brand">NetRadio</span>
      </a>
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <sec:authorize ifAnyGranted="user" ifNotGranted="admin">
        <li class="dropdown${active eq 'flows' or active eq 'myflows' ? ' active' : ''}">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="flows"/> <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="${active eq 'flows' ? 'active' : ''}"><c:url value="/stream/listA.html?fl=true" var="href"/><a href="${href}"><fmt:message key="flows"/> <span class="sr-only">(current)</span></a></li>
                <li class="${active eq 'myflows' ? 'active' : ''}"><c:url value="/stream/listB.html?fl=true" var="href"/><a href="${href}"><fmt:message key="flows.myflows"/></a></li>
            </ul>
          </li>
          <li class="${active eq 'genres' ? 'active' : ''}"><c:url value="/genres.html" var="href"/><a href="${href}"><fmt:message key="genres"/></a></li>
          <%--li class="${active eq 'users' ? 'active' : ''}"><c:url value="/users.html" var="href"/><a href="${href}">Users</a></li> --%>
        </sec:authorize>
        <sec:authorize ifAnyGranted="admin">
        <li class="dropdown${active eq 'flows' or active eq 'newflow' or active eq 'noactiveflows' or active eq 'myflows' ? ' active' : ''}">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="flows"/> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li class="${active eq 'flows' ? 'active' : ''}"><c:url value="/stream/listA.html?fl=true" var="href"/><a href="${href}"><fmt:message key="flows"/></a></li>
            <li class="${active eq 'noactiveflows' ? 'active' : ''}"><c:url value="/stream/listA.html?fl=false" var="href"/><a href="${href}"><fmt:message key="flows.noActual"/></a></li>
            <li class="${active eq 'myflows' ? 'active' : ''}"><c:url value="/stream/listB.html?fl=true" var="href"/><a href="${href}"><fmt:message key="flows.myflows"/></a></li>
            <li class="${active eq 'newflow' ? 'active' : ''}"><c:url value="/stream/stream.html" var="href"/><a href="${href}"><fmt:message key="new.flow"/></a></li>
          </ul>
        </li>
        
        <li class="dropdown${active eq 'genres' or active eq 'newgenre' ? ' active' : ''}">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="genres"/> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li class="${active eq 'genres' ? 'active' : ''}"><c:url value="/genres.html" var="href"/><a href="${href}"><fmt:message key="genres"/></a></li>
            <li class="${active eq 'newgenre' ? 'active' : ''}"><c:url value="/genre.html" var="href"/><a href="${href}"><fmt:message key="new.genre"/></a></li>
          </ul>
        </li>
        
        <li class="dropdown${active eq 'users' or active eq 'newuser' ? ' active' : ''}">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="users"/> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li class="${active eq 'users' ? 'active' : ''}"><c:url value="/users.html" var="href"/><a href="${href}"><fmt:message key="users"/></a></li>
            <li class="${active eq 'newuser' ? 'active' : ''}"><c:url value="/user.html" var="href"/><a href="${href}"><fmt:message key="new.user"/></a></li>
          </ul>
        </li>
        <li class="${active eq 'opt' ? 'active' : ''}"><c:url value="/options.html" var="href"/><a href="${href}"><fmt:message key="options"/></a></li>
        </sec:authorize>
      </ul>
      <c:if test="${not empty action}">
      <form class="navbar-form navbar-left" action="${action}.html" method="POST" role="search">
          <div class="input-group">
          <fmt:message key="quick.search" var="QuickSearch"/>
            <input id="qsearch" type="text" class="form-control" name="search" value="${search}" placeholder="${QuickSearch}">
            <span class="input-group-btn">
                <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
            </span>
         </div>
      </form>
      </c:if>
      <ul class="nav navbar-nav navbar-right">
        <li><c:url value="/info.html" var="href"/><a href="${href}">${pageContext.request.userPrincipal.name} <span class="glyphicon glyphicon-user"></span></a></li>
        <li><c:url value="/logout" var="href"/><a href="${href}"><fmt:message key="exit"/> <span class="glyphicon glyphicon-log-out"></span></a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</div>
</nav>
