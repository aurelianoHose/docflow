<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html>
    <script>
        var mass;
        $(function() {
            $(document).ready(function() {
                $.ajax({
                    url : "http://localhost:8080/netradio/ws/genres",
                    success : function(data) {
                        mass =  $.map(data, function(el) {
                            return el.name;
                            })
                        $('#tags').tagit({
                            availableTags: mass,
                            fieldName: "skills"
                            });
                        }
                });
                });
            });
    </script>
    <div id="wrapper" class="page pad_top11" >
        <div class="container">

            <ui:navbar active="newflow" />

            <c:url value="/stream/save.html" var="href" />
            <div class="row">
                <form:form cssClass="form-horizontal" action="${href}" method="POST" modelAttribute="streamModel" enctype="multipart/form-data">
                <form:hidden path="id" />
                    <div class="panel">

                        <div class="panel-heading">
                            <h4><fmt:message key="flow"></fmt:message></h4>
                        </div>

                        <div class="panel-body">
                            <div class="col-sm-6">
                                <ui:input2 path="flow" label="fl" labeWidth="4" place="flow.enter"/>
                                <ui:input2 path="link" label="link" labeWidth="4" place="link.enter"/>
                                <ui:input2 path="bitrade" pat="^[ 0-9]+$"  label="bitrate" labeWidth="4" place="bitrade.enter"/>
                                <ui:select path="codecId" label="codec" labeWidth="4"/>
                                <ui:file path="icon" label="image" labeWidth="4" />
                                <ui:check path="actual" labeWidth="4" label="flow.actual"/>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><fmt:message key="genres" /></label>
                                    <div class="col-sm-8">
                                        <input id="tags" name="genres" value="${genres}"/>
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="col-sm-4 col-sm-offset-1">
                                <c:if test="${not empty streamModel.id}">
                                    <c:url value="/image?id=${streamModel.id}" var="href" />
                                    <img class="img-thumbnail" src="${href}">
                                 </c:if>
                                <c:if test="${empty streamModel.id}">
                                    <c:url value="/images/zzz.jpg" var="href" />
                                    <img class="img-thumbnail" src="${href}">
                                </c:if>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button class="btn btn-primary pull-right" type="submit"><fmt:message key="save" /></button>
                                </div>
                            </div>
                        </div>

                    </div>
                </form:form>
            </div>

        </div>
    </div>

    <ui:footer/>

</ui:html>
