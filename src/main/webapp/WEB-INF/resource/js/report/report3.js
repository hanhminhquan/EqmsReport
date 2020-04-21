var pageIndex = 1;
var grid;
var storeRule;

window.onload = function() {

	refreshList();
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
			url : path + "/getListReport3",
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
	

	var rules = {
		startDate:	startDate,
		endDate:	endDate,
		visitType : dijit.byId("visitType").get('value'),	
		institutionId : dijit.byId("institutionId").get('value'),	
		departmentId : dijit.byId("departmentId").get('value'),	
		workgroupId : dijit.byId("workgroupId").get('value'),	
	};
	return rules;
}

function resetDataForm() {
	dijit.byId('institutionId').set("value", 0);	 
	var dateTemp = dojo.byId("curentDateTemp").value;
	var dateTemp1= dateTemp.split("-"); 
	dojo.byId("startDate").value = dateTemp1[2] + "-" + dateTemp1[1] + "-"+ dateTemp1[0];
	dojo.byId("endDate").value = dateTemp1[2] + "-" + dateTemp1[1] + "-"+ dateTemp1[0];
	dijit.byId('visitType').set("value", 0);	
	dijit.byId('institutionId').set("value", 0);	
	dijit.byId('departmentId').set("value", 0);	
	dijit.byId('workgroupId').set("value", 0);	
	dijit.byId("searchButtom").set('disabled', false);

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
				//col1 : '',
				col2 : dataF[i].queueNo,
				col3 : dataF[i].customerIc,
				col4 : dataF[i].visitType,
			//	col5 : '',
				col6 : dataF[i].institutionName,
				col7 : dataF[i].departmentName,
				col8 : dataF[i].workgroupName,
				col9 : dataF[i].apptTimestamp,
				col10 : dataF[i].registrationTime,
				col11 : dataF[i].waitingTimeStart,
				col12 : dataF[i].waitingTimeEnd,
				col13 : dataF[i].wt,
				col14 : dataF[i].servingTimeStart,
				col15 : dataF[i].servingTimeEnd,
				col16 : dataF[i].st,
				col17 : dataF[i].turnaroundTime,
				col18 : dataF[i].sapBapiActRemark,
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
//					{
//						name : 'Service Date',
//						field : 'col1',
//						editable : false,
//						width : '120px',
//						styles : 'text-align: center; cursor: pointer;'
//					},
					
					{
						name : 'Queue Number',
						field : 'col2',
						width : '100px'
					}, {
						name : 'IC Number',
						field : 'col3',
						width : '150px'
					}, {
						name : 'Visit Type',
						field : 'col4',
						width : '60px'
					},
//					{
//						name : 'Queue Type',
//						field : 'col5',
//						width : '90px'
//					},
					
					{
						name : 'Polyclinic' ,
						field : 'col6',
						width : '150px'
					}, //         
					{
						name : 'Department',
						field : 'col7',
						width : '150px'
					}, {
						name : 'Workgroup',
						field : 'col8',
						width : '150px'
					}, {
						name : 'Appointment Time',
						field : 'col9',
						width : '130px'
					}, {
						name : 'Registration Time',
						field : 'col10',
						width : '130px'
					}, {
						name : 'Wait Start Time',
						field : 'col11',
						width : '130px',						
					}, {
						name : 'Wait End Time',
						field : 'col12',
						width : '130px'
					} , {
						name : 'Wait Time ',
						field : 'col13',
						width : '80px'
					} , {
						name : 'Serving Time Start',
						field : 'col14',
						width : '130px'
					} , {
						name : 'Serving Time End',
						field : 'col15',
						width : '130px'
					} , {
						name : 'Serving Time',
						field : 'col16',
						width : '80px'
					} , {
						name : 'TAT - Turnaround Time',
						field : 'col17',
						width : '150px'
					} , {
						name : 'Remarks ',
						field : 'col18',
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
