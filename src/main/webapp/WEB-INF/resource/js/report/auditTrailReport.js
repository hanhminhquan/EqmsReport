var pageIndex = 1;
var grid;
var storeRule;

window.onload = function() {

	refreshList();
	loadFilteringDepartment();
	loadFilteringWorkgroup();
	changeDataDep(dijit.byId('institutionId'));
	changeWorrdGroup();
}

function getDataView(formId) {
	dijit.byId("searchButtom").set('disabled', true);
	var path = dojo.byId("pathServer").value;
	showDialogGeneral('dialogMessageWaiting');
	setTimeout(function() {
		var form_data = getParams();
		$.ajax({
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			url : path + "/getListauditTrailReport",
			data : form_data, // Note it is important
			success : function(data) {
				relaodData(data);
				hideDialogGeneral('dialogMessageWaiting');
				dijit.byId("searchButtom").set('disabled', false);
			},
			error : function() {

			}
		});
	}, 120)

}

function submitFrom(formId) {
	dojo.byId(formId).submit();
}

function getParams() {
	
	startDate = "";
	var startDateTemp = dojo.byId("startDate").value;
	if (startDateTemp != ''){
		var startDateTemp1= startDateTemp.split("-"); 
		startDate = startDateTemp1[2] + "-" + startDateTemp1[1] + "-"+ startDateTemp1[0];
	}
	
	endDate = "";
	var endDateTemp = dojo.byId("endDate").value;
	if (endDateTemp != ''){
		var endDateTemp1= endDateTemp.split("-"); 
		endDate = endDateTemp1[2] + "-" + endDateTemp1[1] + "-"+ endDateTemp1[0];
	}
	
//	dojo.byId("membersdepId").value = getMembersData(dojo.byId('departmentId'),0);
//	dojo.byId("membersWorkgroupId").value = getMembersData(dojo.byId('workgroupId'),0);

	var rules = {
		startDate:	startDate,
		endDate:	endDate,		
		institutionId : dijit.byId("institutionId").get('value'),
		departmentId : getValueDepartmentSelectedIndex(dijit.byId("departmentId").get('value')),
		workgroupId : getValueWorkgroupSelectedIndex(dijit.byId("workgroupId").get('value')),
		userId : dijit.byId("userId").get('value'),
		queueNo:dojo.byId("QueueNo").value, 
		customerIc:dojo.byId("CustomerIc").value, 
	};
	return rules;
}

function getMembersData(obj, typeData){     
    var member ="";
    if(typeof obj !="undefined"){
        if(obj!=null){        
           for(i=0;i<obj.length;i++){
              if (obj[i].selected == true){
                  if (member != '')  member += ',';
                  if (typeData == 1) member += "'" + obj[i].value + "'";
                  else member += obj[i].value;
              }
           }
       }
   }
   return member;
}


function resetDataForm() {		 
	var dateTemp = dojo.byId("curentDateTemp").value;
	var dateTemp1= dateTemp.split("-"); 
	dojo.byId("startDate").value = dateTemp1[2] + "-" + dateTemp1[1] + "-"+ dateTemp1[0];
	dojo.byId("endDate").value = dateTemp1[2] + "-" + dateTemp1[1] + "-"+ dateTemp1[0];		
	dojo.byId("membersdepId").value = "";
	dojo.byId("membersWorkgroupId").value = "";
	dijit.byId("institutionId").set('value', 0);
	dijit.byId("userId").set('value', 0);
	dojo.byId("QueueNo").value = "";
	dojo.byId("CustomerIc").value = "";
}

function getUnDataMultiSelect(objData)
{      
    var a=dojo.byId(objData).options;
    var b=a.length;       
    for(var i=0; i < b; i++) a[i].selected = false;	    
      
}


function relaodData(dataF) {
	var dataFresh = new Array();
	var data = {
		identifier : 'id',
		items : []
	};
	var emptyCells = {
		items : []
	};
	var emptyStore = new dojo.data.ItemFileWriteStore({
		data : emptyCells
	});
	grid.setStore(emptyStore);
	// console.log(JSON.stringify(dataF));
	if (dataF != null && dataF.length > 0) {
		for (var i = 0; i < dataF.length; i++) {

			dataFresh[i] = {				
				col1 : dataF[i].registrationTime,
				col2 : dataF[i].userName,				
				col3 : dataF[i].customerName,
				col4 : dataF[i].queueNo,
				col5 : dataF[i].departmentName,
				col6 : dataF[i].terminalName,
				col7 : dataF[i].institutionName,
				col8 : '',
				col9 : '',
				col10 : '',
					
					
			};
		}

		for (var i = 0; i < dataFresh.length; i++) {
			data.items.push(dojo.mixin({
				id : i + 1
			}, dataFresh[i]));
		}

		var storedataFresh = new dojo.data.ItemFileWriteStore({
			data : data
		});
		grid.setStore(storedataFresh);
	}
}

function refreshList() {
	require(
			[ "dojox/grid/DataGrid", "dojox/grid/cells",
					"dojox/grid/cells/dijit", "dojo/store/Memory",
					"dojo/data/ObjectStore", "dojo/date/locale",
					"dojo/currency", "dijit/form/DateTextBox",
					"dijit/form/CurrencyTextBox",
					"dijit/form/HorizontalSlider", "dijit/form/DropDownButton",
					"dojo/dom", "dijit/DropDownMenu", "dijit/MenuItem",
					'dijit/form/Button', "dijit/form/ComboBox",
					"dojo/domReady!" ],
			function(DataGrid, cells, cellsDijit, Memory, ObjectStore, locale,
					currency, DateTextBox, CurrencyTextBox, HorizontalSlider,
					Button, DropDownButton, DropDownMenu, MenuItem, dom) {

				function formatCurrency(inDatum) {
					return isNaN(inDatum) ? '...' : currency.format(inDatum,
							this.constraint);
				}

				function formatDate(inDatum) {
					return locale.format(new Date(inDatum), this.constraint);
				}

				function formatter(item, rowIndex) {
					return '<button class="btn btn-primary" onClick="getRuleById('
							+ item
							+ ','
							+ rowIndex
							+ ')" data-dojo-type="dijit/form/Button" type="button">'
							+ dojo.byId("global_edit").value
							+ '</button> <button class="btn btn-danger" onClick="deleteReule('
							+ item
							+ ','
							+ rowIndex
							+ ')" data-dojo-type="dijit/form/Button" type="button">'
							+ dojo.byId("global_delete").value + '</button>';
				}

				function priorityIndicator(items, rowIndex) {
					var result = "";
					if (items == 'N')
						var result = "No";
					else if (items == 'Y')
						var result = "Yes";
					return result;
				}

				layout = [ {
					cells : [
					         
					{
						name : 'Service Date',
						field : 'col1',
						width : '140px'
					}, 
					
					{
						name : 'ADID',
						field : 'col2',
						width : '120px'
					},
					
					{
						name : 'Patient IC',
						field : 'col3',
						width : '150px'
					},
					
					{
						name : 'Queue Number',
						field : 'col4',
						width : '100px'
					}, {
						name : 'Service Area',
						field : 'col5',
						width : '130px'
					}, {
						name : 'Service Terminal and Location',
						field : 'col6',
						width : '140px'
					},
					{
						name : 'Service Type',
						field : 'col7',
						width : '120px'
					},
					{
						name : 'Action Type',
						field : 'col8',
						width : '150px'
					},
					{
						name : 'Action',
						field : 'col9',
						width : '150px'
					},
					{
						name : 'Action Time Stamp',
						field : 'col9',
						width : '150px'
					}
					
					]
				} ]

				var data = {
					identifier : 'id',
					items : []
				};
				var data_list = new Array();
				for (var i = 0; i < data_list.length; i++) {
					data.items.push(dojo.mixin({
						id : i + 1
					}, data_list[i]));
				}
				storeRule = new dojo.data.ItemFileWriteStore({
					data : data
				});

				// create the grid.
				var rows_view = 20;
				grid = new dojox.grid.EnhancedGrid({
					id : 'grid',
					store : storeRule,
					structure : layout,
					plugins : {
						pagination : {

							description : true,
							sizeSwitch : false,
							pageStepper : false,
							gotoButton : false,
							maxPageStep : 1,
							position : "bottom"
						}
					}
				}, 'gridReport');
				grid.startup();

			});
}
