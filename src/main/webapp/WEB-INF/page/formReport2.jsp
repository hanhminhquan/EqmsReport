<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="formContent" >
<fieldset id="fs1" data-dojo-id="fs1" dojoType="dijit.Fieldset" title="Downtime Queue Script">
<c:url value="/QueueScript" var="url"></c:url>
<input type="hidden" name="pathServer" id="pathServer" value="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  +request.getContextPath() %>"> 
	<form method="post" action="${url}" name="reportSearch" id="reportSearch">
   <table align="center" cellspacing="4" cellpadding="5">
   		<tr>
         <td><label class="formLabel"> Polyclinic Domain:</label></td>
         <td>
        
            <select name="institutionId" id="institutionId"  data-dojo-type="dijit/form/Select" style="width:200px">
                   <option value="0" selected="selected"> All </option>                   
	                 <c:forEach items="${listInstitution}" var="prod">
	                    <option value="${prod.institutionId}" selected="selected">${prod.institutionName}</option>						
					</c:forEach>
                                    
            </select>
        </td>        
     </tr>
     
     <tr><td align="center" colspan="2">
		     <div id="bottomForm">
				    <div id="buutomFormContent" >
				     	<button data-dojo-type="dijit/form/Button" id="clearButtom" data-dojo-props="showLabel: true" type="button" onclick="resetDataForm()">Clear All</button>
				     	<button data-dojo-type="dijit/form/Button" id="exportExcelButtom" data-dojo-props="showLabel: true" type="button" onclick="submitFrom('reportSearch')">Export to Excel</button>
				        <button data-dojo-type="dijit/form/Button" id="searchButtom" data-dojo-props="showLabel: true" type="button" onclick="getDataView()">Go</button>
				    </div>
		    </div>
     </td>
     
     </tr>					
   </table>
   </form>
</fieldset>
</div>