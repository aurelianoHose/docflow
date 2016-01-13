<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<div class="panel">
    <div class="panel-heading">
        <h3 class="panel-title"><fmt:message key="genre.list" /></h3>
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <tr>
                <td width="1%">#</td>
                <td width="*"><fmt:message key="genres" />:</td>
                <td width="*"><fmt:message key="description" />:</td>
            </tr>
            <tbody>
                <c:forEach items="${genres}" var="genre" varStatus="status">
                    <tr>
                        <td width="1%"><span class="badge">${status.count}</span></td>
                        <td width="*">${genre.name}</td>
                        <td width="*" style=" text-align: justify;">${genre.description}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>