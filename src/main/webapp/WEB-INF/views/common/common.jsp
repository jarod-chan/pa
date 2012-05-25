<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="ctx" value="pa" scope="request"/>
<c:set var="pagewidth" value="1010" scope="request"/>
<jsp:useBean id="pagefunc" class="java.util.HashMap" scope="request"></jsp:useBean>
<jsp:useBean id="pagetitle" class="java.util.HashMap" scope="request"></jsp:useBean>
<!doctype html>
