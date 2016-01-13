<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:html>

    <div id="wrapper" class="page pad_top7" >
        <div class="container">

        <c:url value="/searchUsers" var="href" />
        <ui:navbar active="users" action="${href}" />

        <c:choose>
            <c:when test="${not empty users}">
                <c:url value="/deleteuser.html" var="href" />
                <form action="${href}" method="post">
                    <div class="panel">
                        <div class="panel-heading">
                            <div class="col-sm-3 col-xs-8">
                                <h4><fmt:message key="user.list" /></h4>
                            </div>
                            <div class="align_r">
                                <a id="btndel1" class="btn btn-danger disabled" data-toggle="modal" data-target="#myModal" data-id=""><fmt:message key="delete" /></a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped">
                                <tr>
                                    <td width="1%">#</td>
                                    <td width="*"><fmt:message key="users" />:</td>
                                    <td width="*"><fmt:message key="created.by" /></td>
                                    <td width="*"><fmt:message key="time.adding" /></td>
                                    <td width="*"><fmt:message key="modification.time" /></td>
                                </tr>
                                <tbody>
                                    <c:forEach items="${users}" var="user" varStatus="status">
                                        <tr>
                                            <td width="1%"><span class="badge">${status.count}</span></td>
                                            <sec:authorize ifAnyGranted="admin">
                                                <td>
                                                    <label>
                                                        <input type="checkbox" name="id" value="${user.id}"> <c:url value="/user.html?id=${user.id}" var="href" /> <a href="${href}">${user.name}</a>
                                                    </label>
                                                </td>
                                                <td>${user.userName}</td>
                                            </sec:authorize>
                                            <sec:authorize ifAnyGranted="user" ifNotGranted="admin">
                                                <td>${user.name}</td>
                                                <td>${user.userName}</td>
                                            </sec:authorize>
                                            <td><fmt:formatDate type="both" pattern="yyyy-MM-dd  HH:mm:ss" value="${user.added}" /></td>
                                            <td><fmt:formatDate type="both" pattern="yyyy-MM-dd  HH:mm:ss" value="${user.modified}" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel-footer align_r">
                            <a id="btndel2" class="btn btn-danger disabled"  data-toggle="modal" data-target="#myModal" data-id=""><fmt:message key="delete" /></a>
                        </div>
                    </div>

                    <ui:modal title="Warning!" id="myModal" action="delete">
                        <p><fmt:message key="mess"/></p>
                    </ui:modal>

                </form>
            </c:when>
            <c:otherwise>
                <h2>Not users!</h2>
            </c:otherwise>
        </c:choose>

        <script language="JavaScript">
            var MMNav = {
            init: function() {
                document.onclick = function(e) {
                    var e = e || window.event;
                    var target = e.target || e.srcElement;
                    if (target && target.type == 'checkbox' ) {
                        if(target.checked){
                            document.getElementById('btndel1').setAttribute('class','btn btn-danger');
                            document.getElementById('btndel2').setAttribute('class','btn btn-danger');
                            } else{
                                var s = 0;
                                var check = document.getElementsByName('id');
                                for(var i = 0; i < check.length; i++){
                                    if(check[i].checked) s++
                                    }
                                if(s==0){
                                    document.getElementById('btndel1').setAttribute('class','btn btn-danger disabled');
                                    document.getElementById('btndel2').setAttribute('class','btn btn-danger disabled');
                                    }
                                }
                        }
                    return true;
                    };
                    }
            }
            window.onload=MMNav.init;
        </script>

        </div>
    </div>

    <ui:footer/>

</ui:html>
