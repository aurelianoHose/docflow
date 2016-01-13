<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<fmt:bundle basename="messages">
    <ui:html>
        <div id="wrapper" class="page pad_top11">
            <div class="container">
                <ui:navbar active="newgenre" />
                <c:url value="/savegenre.html" var="href" />

                <div class="row">
                    <div class="col-sm-offset-3 col-sm-6">
                        <form:form cssClass="form-horizontal" action="${href}" method="POST" modelAttribute="genre">
                            <div class="panel">
                                <div class="panel-heading">
                                    <h4><fmt:message key="genre" /></h4>
                                </div>
                                <div class="panel-body">
                                    <form:hidden path="id" />
                                    <ui:input2 path="name" label="genre" labeWidth="3" glyph="music" place="genre.enter"/>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"><fmt:message key="description"/></label>
                                        <div class="col-sm-9">
                                            <fmt:message key="description.enter" var="descr"/>
                                            <form:textarea id="textarea" path="description" rows="6" cols="60" placeholder="${descr}"></form:textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <button class="btn btn-primary pull-right" type="submit">
                                                <fmt:message key="save" />
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>

            </div>
        </div>

        <ui:footer/>

    </ui:html>
</fmt:bundle>
