<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ taglib uri="http://java.netradio.com/jsp/ui/functions" prefix="uf"%>

<fmt:bundle basename="messages">
    <ui:html>

    <div id="wrapper" class="page pad_top11">
        <div class="container">

            <ui:navbar/>

                <div class="row">
                    <div class="col-sm-offset-3 col-sm-6">
                        <c:if test="${not empty messageSuccess && empty errorPasswd && empty errorConfirmed}">
                            <ui:alert color="#3E7048" key="${messageSuccess}" clazz="success" />
                        </c:if>
                        <c:if test="${not empty errorPasswd && empty messageSuccess && empty errorConfirmed}">
                            <ui:alert color="#EE5F5B" key="${errorPasswd}" clazz="danger" />
                        </c:if>
                        <c:if test="${not empty errorConfirmed && empty messageSuccess && empty errorPasswd}">
                            <ui:alert color="#E08217" key="${errorConfirmed}" clazz="warning" />
                        </c:if>
                        <form:form cssClass="form-horizontal" action="savepasswd.html" metod="POST" modelAttribute="user">
                        <div class="panel">
                            <div class="panel-heading">
                                <h4>${user.name}</h4>
                            </div>
                            <div class="panel-body">
                                <h4><fmt:message key="psswd.edit" /></h4>
                                <form:hidden path="id" />
                                <fmt:message key="psswd.new" var="labelNew" />
                                <ui:passwd2 name="passwdOld" label="psswd.old" labeWidth="4" glyph="lock" />
                                <ui:passwd path="passwd" label="${labelNew}" labeWidth="4" glyph="lock" />
                                <ui:passwd2 name="passwdConfirm" label="psswd.confirm" labeWidth="4" glyph="lock" />
                            </div>
                            <div class="panel-footer align_r">
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button class="btn btn-primary" type="submit">
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
