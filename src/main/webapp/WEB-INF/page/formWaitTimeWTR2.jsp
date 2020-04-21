<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="formContent" >
<fieldset id="fs1" data-dojo-id="fs1" dojoType="dijit.Fieldset" title="Wait Time, Serving Time Percentile Report ">
<c:url value="/waitTimeWTR2" var="url"></c:url>
<input type="hidden" name="pathServer" id="pathServer" value="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  +request.getContextPath() %>"> 
	<form method="post" action="${url}" name="reportSearch" id="reportSearch">
	<input type="hidden" name="membersdepId" id="membersdepId" value=""/>
	<input type="hidden" name="membersWorkgroupId" id="membersWorkgroupId" value=""/>
	<input type="hidden" name="membersVisitTypeId" id="membersVisitTypeId" value=""/>
	<input type="hidden" name="membersServiceType" id="membersServiceType" value=""/>
	<input type="hidden" name="curentDateTemp" id="curentDateTemp" value="${curentDate}"/>
   <table align="center" cellspacing="4" cellpadding="5">
   		<tr> 
         <td><label class="formLabel"> Start Date:</label></td>       
         <td><input type="text" id="startDate" name="startDate" constraints="{datePattern:'dd-MM-yyyy'}" value="${curentDate}" style="width:110px" data-dojo-type="dijit/form/DateTextBox"  /></td>  
         <td><label class="formLabel"> End Date:</label></td>
         <td><input type="text" id="endDate" name="endDate" constraints="{datePattern:'dd-MM-yyyy'}" value="${curentDate}" style="width:110px" data-dojo-type="dijit/form/DateTextBox"  /></td>        
     </tr>
   		<tr>
         <td><label class="formLabel"> Polyclinic Domain:</label></td>
         <td colspan="3">        
            <select name="institutionId" id="institutionId" onchange="changeDataDep()"  data-dojo-type="dijit/form/Select" style="width:200px">                                    
	                 <option value="0" selected="selected">All</option>	
	                 <c:forEach items="${listInstitution}" var="prod">
	                    <option value="${prod.institutionId}" selected="selected">${prod.institutionName}</option>						
					</c:forEach>                                    
            </select>
        </td>
       
     </tr>
     
    <tr>   
        <td><label class="formLabel"> View Selection:</label></td>
         <td colspan="3">
        
            <select name="viewSelection" id="viewSelection"  data-dojo-type="dijit/form/Select" style="width:200px">
               <option value="4" selected="selected"> Wait Time, Serving Time Report  </option>                   
               <option value="5"> Turnaround Time Report  </option>
            </select>
        </td>
     </tr> 
     
     <tr><td align="center" colspan="4">
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