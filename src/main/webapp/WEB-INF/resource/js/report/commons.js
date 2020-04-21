
function AddNewData(obj,dataList) {  
     var check = false ;
     var id= idParam;
     var valueText = text;
     for (i = 0; i < destination.length; i++) {                              
          if (destination.options[i].value == id){
               check=true;
               break;
          }                              
      }  
      if (!check){ 
          destination.options[destination.length] = new Option(valueText, id);
      }
}


function getListDataSelect(paramsFunction, obj) {
	dijit.byId("searchButtom").set('disabled', true);
	var path = dojo.byId("pathServer").value;
		var form_data = getParams();
		$.ajax({
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			url : path + "/" + paramsFunction,
			data : form_data, // Note it is important
			success : function(data) {
				AddAllData(obj, data);
			},
			error : function() {

			}
		});
}

function AddAllData(destination,dataList) {
    if (destination!=null){    
        for (k=0;k<dataList.length;k++){   
          destination.options[destination.length] = new Option(dataList[k].name, dataList[k].id);
          
       }
     }
}


function removeAllItem(obj) {    
    for (i=obj.length-1;i>=0;i--){                               
         obj.remove(i);
    }
} 


function changeDataDep(objId){	
	getListDataDep(objId.get('value'));	
}

function changeWorrdGroup(){	
	
	var departmentId = dijit.byId("departmentId").get('value') != null && dijit.byId("departmentId").get('value') != ''?dijit.byId("departmentId").get('value'):0	
	getListDataWork(departmentId);
}

function getListDataDep(institutionId) {
	var path = dojo.byId("pathServer").value;
	//showDialogGeneral('dialogMessageWaiting');
	setTimeout(function() {		
		var form_data = {};
		$.ajax({
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			url : path + "/getDepartmentByInstitutionId/" + institutionId,
			data : form_data, // Note it is important
			success : function(data) {	
			//	console.log(JSON.stringify(data))
				reLoadFilteringDepartment(data);
				//hideDialogGeneral('dialogMessageWaiting');
			},
			error : function() {

			}
		});
	}, 120)

}


function getListDataWork(id) {
		var urlParams = "getWorkgroupByDepartmentId/" + id;		
		var path = dojo.byId("pathServer").value;
		
		var form_data = {};
		$.ajax({
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			url : path + "/" + urlParams,
			data : form_data, // Note it is important
			success : function(data) {	
				//console.log(JSON.stringify(data))
				reLoadFilteringWorkgroup(data);
			},
			error : function() {

			}
		});
	

}


var storeDepartmentId;
var selectDepartmentId;
function loadFilteringDepartment() {
    require(["dojo/store/Memory", "dijit/form/FilteringSelect", "dojo/domReady!"], function (Memory, FilteringSelect) {
        dataExt = new Array();
        var dataItemdataExt = {items: [dataExt]};
        storeDepartmentId = new dojo.data.ItemFileReadStore({data: dataItemdataExt});
        selectDepartmentId = new FilteringSelect({
            id: "departmentId",
            name: "departmentId",
            class: "cusInput",
            store: storeDepartmentId,
            style: {width: "200px"},
            queryExpr: "*\$\{0\}*",
            autoComplete: false,
            required: false,
            searchAttr: "name",
            onChange: function () {  
               if (this.item != null && this.item.id != null){
            	   getListDataWork(this.item.id.toString());
               }else {
            	   getListDataWork(0);
               }   
            	
            },
        }, "departmentId");
        selectDepartmentId.startup();
    });
}

function reLoadFilteringDepartment(dataDepartment) {
    dataExt = new Array();
    dataDep = new Array();
    dataExt[0] = {name: "All", id: 0};
    var index = 1;
    for (var i = 0; i < dataDepartment.length; i++) {    	
        dataExt[index] = {name: dataDepartment[i].departmentName, id: dataDepartment[i].departmentId};
        dataDep[i] = {name: dataDepartment[i].departmentName, id: dataDepartment[i].departmentId};
        index++;
    }
    
    
    storeDepartmentId.close();
    var dataItem = {items: [dataExt]};
    storeDepartmentId = new dojo.data.ItemFileReadStore({ data: dataItem });
    storeDepartmentId.fetch();
    selectDepartmentId.set("store", storeDepartmentId); 
    dijit.byId('departmentId').set("value", "");

}

function getValueDepartmentSelectedIndex(index)
{
   var result = 0;  
   if (dataDep != null && dataDep != '' && index  != '' &&  index > 1 && dataDep.length >0){
          result = dataDep[index-2].id;
   }
   return result;
}


var storeWorkgroupId;
var selectWorkgroupId;
function loadFilteringWorkgroup() {
    require(["dojo/store/Memory", "dijit/form/FilteringSelect", "dojo/domReady!"], function (Memory, FilteringSelect) {
        dataExt = new Array();
        var dataItemdataExt = {items: [dataExt]};
        storeWorkgroupId = new dojo.data.ItemFileReadStore({data: dataItemdataExt});
        selectWorkgroupId = new FilteringSelect({
            id: "workgroupId",
            name: "workgroupId",
            class: "cusInput",
            store: selectWorkgroupId,
            style: {width: "200px"},
            queryExpr: "*\$\{0\}*",
            autoComplete: false,
            required: false,
            searchAttr: "name",
            onChange: function () {
            },
        }, "workgroupId");
        selectWorkgroupId.startup();
    });
}


function reLoadFilteringWorkgroup(dataWorkgroup) {
    dataExt = new Array();
    dataWorkGroup = new Array();
    dataExt[0] = {name: "All", id: 0};
    var index = 1;
    for (var i = 0; i < dataWorkgroup.length; i++) {    	
        dataExt[index] = {name: dataWorkgroup[i].workgroupName, id: dataWorkgroup[i].workgroupId};
    	dataWorkGroup[i] = {name: dataWorkgroup[i].workgroupName, id: dataWorkgroup[i].workgroupId};
        index++;
    }
    var dataItem = {items: [dataExt]};
    storeWorkgroupId.close();
    storeWorkgroupId = new dojo.data.ItemFileReadStore({data: dataItem});
    storeWorkgroupId.fetch();
    selectWorkgroupId.set("store", storeWorkgroupId);
   // dijit.byId('workgroupId').set("value", "");
}


function getValueWorkgroupSelectedIndex(index)
{
   var result = 0;
 //  console.log("dataDep:" + JSON.stringify(dataDep))
   if (dataWorkGroup != null && dataWorkGroup != '' && index  != '' &&  index > 1 && dataWorkGroup.length >0){
          result = dataWorkGroup[index-2].id;
   }
   return result;
}

function getSelectWorkgroupFormIndex(workgroupId)
{
   var result = 1;
   for(var i=0;i<dataWorkGroup.length;i++){
       if (workgroupId == dataWorkGroup[i].workgroupId){
            result = i+1;
            break;
        }
   }
   return result;
}
