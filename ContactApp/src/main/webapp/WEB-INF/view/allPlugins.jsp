
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
    <s:url var="bootstrap_min_css" value="/resources/css/bootstrap.min.css"/>
    <link href="${bootstrap_min_css}" rel="stylesheet" type="text/css" />
    
    <s:url var="jquery_min_js" value="/resources/js/jquery-3.3.1.min.js"/>
    <script src="${jquery_min_js}" type="text/javascript" ></script>
    
    <s:url var="bootstrap_min_js" value="/resources/js/bootstrap.min.js"/>
    <script src="${bootstrap_min_js}" type="text/javascript"></script>
    
    <s:url var="style_css" value="/resources/css/style.css"/>
    <link href="${style_css}" rel="stylesheet" type="text/css" />
    
    <s:url var="responsive_css" value="/resources/css/responsive.css"/>
    <link href="${responsive_css}" rel="stylesheet" type="text/css" />
  