<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="height:20px;">
<c:if test="${message!=null}">
	<font id="msg" style="color:red;" >${message}</font>
</c:if>
</div>