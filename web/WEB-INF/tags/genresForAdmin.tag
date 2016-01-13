<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

    <div class="panel">
      <div class="panel-heading">
        <h3 class="panel-title"><fmt:message key="genre.list" /></h3>
      </div>
      <div class="panel-body">
        <table class="table table-striped" >
          <tr>
            <td width="1%">#</td>
            <td width="*"><fmt:message key="genres" />:</td>
            <td width="*"><fmt:message key="description" />:</td>
            <td width="11%"><fmt:message key="created.by" /></td>
            <td width="9%"><fmt:message key="time.adding" /></td>
            <td width="9%"><fmt:message key="modification.time" /></td>
            <td width="1%">X</td>
          </tr>
          <tbody>
            <c:forEach items="${genres}" var="genre" varStatus="status">
              <tr>
                <td width="*" ><span class="badge">${status.count}</span></td>
                <td width="*" ><label><c:url value="/genre.html?id=${genre.id}" var="href" /><a href="${href}">${genre.name}</a></label></td>
                <td width="*" style="text-align: justify;" >${genre.description}</td>
                <td width="*" >${genre.userName}</td>
                <td width="*" ><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${genre.added}" /></td>
                <td width="*" ><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${genre.modified}" /></td>
                <td width="*" ><button class="btn btn-danger btn-xs delete" name="id" value="${genre.id}" data-toggle="modal" data-target="#myModal">X</button></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>