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
			url : path + "/getListReport2",
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
	var rules = {
		institutionId : dijit.byId("institutionId").get('value'),		
	};
	return rules;
}

function resetDataForm() {
	dijit.byId('institutionId').set("value", 0);	
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
				col1 : dataF[i].institutionCode,
				col2 : dataF[i].workgroupName,
				col3 : dataF[i].departmentName,				
				col4 : dataF[i].queueNo,
				col5 : dataF[i].customerIc,
				col6 : dataF[i].customerName,
				col7 : dataF[i].visitType,
				col8 : dataF[i].apptTimestamp,
				col9 : dataF[i].registrationTime,
				col10 : dataF[i].statusDescription,
				col11 : dataF[i].vipNormal
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
					cells : [ {
						name : 'Polyclinic Code',
						field : 'col1',
						editable : false,
						width : '120px',					
					}, {
						name : 'Workgroup',
						field : 'col2',
						width : '150px'
					}, {
						name : 'DepartmentName',
						field : 'col3',
						width : '150px'
					}, {
						name : 'Queue No',
						field : 'col4',
						width : '90px'
					}, {
						name : 'NRIC',
						field : 'col5',
						width : '90px'
					}, {
						name : 'Patient Name',
						field : 'col6',
						width : '200px'
					}, //         
					{
						name : 'Visit Type',
						field : 'col7',
						width : '60px'
					}, {
						name : 'Appointment Date/Time',
						field : 'col8',
						width : '150px'
					}, {
						name : 'Registration Date/ Time',
						field : 'col9',
						width : '150px'
					}, {
						name : 'Queue Status',
						field : 'col10',
						width : '150px'
					}, {
						name : 'Priority Indicator',
						field : 'col11',
						width : '100px',
						formatter : priorityIndicator
					}, ]
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
