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
			url : path + "/getListConsultWaitingTime",
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
	serviceDate = "";
	var startDateTemp = dojo.byId("serviceDate").value;
	if (startDateTemp != ''){
		var startDateTemp1= startDateTemp.split("-"); 
		serviceDate = startDateTemp1[2] + "-" + startDateTemp1[1] + "-"+ startDateTemp1[0];
	}		
	dojo.byId("membersVisitTypeId").value = getMembersData(dojo.byId('visitTypeId'), 1);
	var rules = {
		serviceDate:serviceDate,
		startDate:	dojo.byId("startDate").value,
		endDate:	dojo.byId("endDate").value,
		membersVisitTypeId:dojo.byId("membersVisitTypeId").value,
		institutionId : dijit.byId("institutionId").get('value'),
		departmentId : getValueDepartmentSelectedIndex(dijit.byId("departmentId").get('value')),
		workgroupId : getValueWorkgroupSelectedIndex(dijit.byId("workgroupId").get('value')),	
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
	dojo.byId("serviceDate").value = dateTemp1[2] + "-" + dateTemp1[1] + "-"+ dateTemp1[0];
//	dojo.byId("endDate").value = dateTemp1[2] + "-" + dateTemp1[1] + "-"+ dateTemp1[0];
	
	dojo.byId("startDate").value = "";
	dojo.byId("endDate").value = "";
	
	dojo.byId("membersVisitTypeId").value = "";
	dojo.byId("membersServiceType").value = "";
	dojo.byId("membersdepId").value = "";
	dojo.byId("membersWorkgroupId").value = "";
	
	getUnDataMultiSelect('visitTypeId');
	dijit.byId("institutionId").set('value', 0);
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
				col1 : dataF[i].apptTimestamp,
				col2 : dataF[i].registrationTime,
				col3 : dataF[i].waitingTimeStart,
				col4 : dataF[i].waitingTimeEnd,
				col5 : dataF[i].wt,
				col6 : dataF[i].visitType,
				col7 : dataF[i].customerIc,
				col8 : dataF[i].customerName,
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
						name : 'Appointment	Time',
						field : 'col1',
						width : '135px'
					}, 
					
					{
						name : 'Registration Time',
						field : 'col2',
						width : '135px'
					}, {
						name : 'Starting Time to Wait for Consultation',
						field : 'col3',
						width : '135px'
					}, {
						name : 'Starting Time for Consultation',
						field : 'col4',
						width : '135px'
					},
					{
						name : 'Waiting Time',
						field : 'col5',
						width : '90px'
					}, //         
					{
						name : 'Visit Type',
						field : 'col6',
						width : '90px'
					}, {
						name : 'Patient IC',
						field : 'col7',
						width : '100px'
					}, {
						name : 'Patient Name',
						field : 'col8',
						width : '150px'
					}]
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
