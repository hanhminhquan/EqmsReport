<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="formContent" >
<fieldset id="fs1" data-dojo-id="fs1" dojoType="dijit.Fieldset" title="Ops Dashboard">
<c:url value="/QueueOpsDashboard" var="url"></c:url>
<input type="hidden" name="pathServer" id="pathServer" value="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  +request.getContextPath() %>"> 
	<form method="post" action="${url}" name="reportSearch" id="reportSearch">
   <table align="center" cellspacing="4" cellpadding="5">
   		<tr>
         <td><label class="formLabel"> Polyclinic:</label></td>
         <td>
        
            <select name="institutionId" id="institutionId"  data-dojo-type="dijit/form/Select" style="width:200px">
                   <option value="0" selected="selected"> All </option>                   
	                 <c:forEach items="${listInstitution}" var="prod">
	                    <option value="${prod.institutionId}" selected="selected">${prod.institutionName}</option>						
					</c:forEach>
                                    
            </select>
        </td>
        <td><label class="formLabel"> Visit Type:</label></td>         
        <td><label class="formLabel"> Polyclinic:</label></td>
         <td>
        
            <select name="departmentId" id="departmentId"  data-dojo-type="dijit/form/Select" style="width:200px">
                   <option value="0" selected="selected"> All </option>                   
	                 <c:forEach items="${listDepartment}" var="dep">
	                    <option value="${dep.departmentId}" selected="selected">${dep.departmentName}</option>						
					</c:forEach>
                                    
            </select>
            <label class="formLabel"> Auto:</label>
           <input type="radio" name="drink" id="radioOne" value="1" onclick="setAutoCheck()"/>
           <label class="formLabel"> Manual:</label>
           <input type="radio" name="drink" id="radioTwo" value="0" checked="checked" onclick="setAutoCheck()"/>
        </td>
     </tr>
     
     
     <tr><td align="center" colspan="4">
		     <div id="bottomForm">
				    <div id="buutomFormContent" >
				     	<button data-dojo-type="dijit/form/Button" id="clearButtom" data-dojo-props="showLabel: true" type="button" onclick="resetDataForm()">Clear All</button>
				     	<button data-dojo-type="dijit/form/Button" id="exportExcelButtom" data-dojo-props="showLabel: true" type="button" onclick="submitFrom('reportSearch')">Export to Excel</button>
				        <button data-dojo-type="dijit/form/Button" id="searchButtom" data-dojo-props="showLabel: true" type="button" onclick="getDataView('reportSearch', 1)">Go</button>
				    </div>
		    </div>
     </td>
     
     </tr>					
   </table>
   </form>
</fieldset>
</div>