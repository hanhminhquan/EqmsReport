<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!-- 	navigation Bar -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Consultation Wait Time (WTR2)</title>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ include file="/../WEB-INF/resource/commons/setSystemAdmin.jsp"%>   
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/report/commons.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/report/consultWaitingTime.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery.js"></script>
    <style type="text/css">
	  #main { height: 100%; width: 100%; border: 0;}
    </style>
    
    
</head>
<body class="claro">

    <div data-dojo-type="dijit/layout/BorderContainer" id="main">
        <div id="header" data-dojo-type="dijit/MenuBar" data-dojo-props="region:'top'">
              <%@ include file="/../WEB-INF/resource/commons/menu.jsp"%> 
        </div>
         <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top'"><%@ include file="/../WEB-INF/page/formConsultWaitingTime.jsp"%>
         
         
         </div>
        <div data-dojo-type="dijit/layout/ContentPane" style="background-color: #f5fafe;width: 100%; height: 100%;" data-dojo-props="region:'center'">
             <div id=gridReport style="height:99.7%"></div>
        </div>
       
    </div>
     <%@ include file="/../WEB-INF/resource/commons/messagesWaiting.jsp"%> 
</body>
</html>
