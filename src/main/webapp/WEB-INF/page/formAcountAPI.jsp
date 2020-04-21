<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="formContent" >
<fieldset id="fs1" data-dojo-id="fs1" dojoType="dijit.Fieldset" title="Downtime Queue Info">
<c:url value="/QueueInfo" var="url"></c:url>
<input type="hidden" name="pathServer" id="pathServer" value="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  +request.getContextPath() %>"> 
	<form method="post" action="${url}" name="reportSearch" id="reportSearch">
	<input type="hidden" name="institutionIdTemp" id="institutionIdTemp" value="${institutionIdTemp}"/>
   <table align="center" cellspacing="4" cellpadding="5">
   		<tr>
         <td><label class="formLabel"> URL:</label></td>
         <td>        
           <input type="text" id="URL" name="URL" data-dojo-type="dijit/form/TextBox"  />
        </td>        
     </tr>
     <tr>
         <td><label class="formLabel"> AccNo:</label></td>
         <td>
            <input type="text" id="accNo" name="accNo" data-dojo-type="dijit/form/TextBox"  />
        </td>
     </tr>
      <tr>
         <td><label class="formLabel"> idNo:</label></td>
         <td>
            <input type="text" id="idNo" name="idNo" data-dojo-type="dijit/form/TextBox"  />
        </td>
     </tr>
      <tr>
         <td><label class="formLabel"> phone:</label></td>
         <td>
            <input type="text" id="phone" name="phone" data-dojo-type="dijit/form/TextBox"  />
        </td>
     </tr>
      <tr>
         <td><label class="formLabel"> provider_id:</label></td>
         <td>
            <input type="text" id="provider_id" name="provider_id" data-dojo-type="dijit/form/TextBox"  />
        </td>
     </tr>
     
     <tr>
         <td><label class="formLabel"> action:</label></td>
         <td>
            <input type="text" id="action" name="action" data-dojo-type="dijit/form/TextBox"  />
        </td>
     </tr>
     
     <tr>
         <td><label class="formLabel"> msgID:</label></td>
         <td>
            <input type="text" id="msgID" name="msgID" data-dojo-type="dijit/form/TextBox"  />
        </td>
     </tr>
     
     <tr>
         <td><label class="formLabel"> walletID:</label></td>
         <td>
            <input type="text" id="walletID" name="walletID" data-dojo-type="dijit/form/TextBox"  />
        </td>
     </tr>
   			<tr><td align="center" colspan="4">
		     <div id="bottomForm">
				    <div id="buutomFormContent" >				     	
				        <button data-dojo-type="dijit/form/Button" id="searchButtom" data-dojo-props="showLabel: true" type="button" onclick="getDataView()">Go</button>
				    </div>
		    </div>
     </td>
     
     </tr>		
   </table>
   </form>
</fieldset>
</div>