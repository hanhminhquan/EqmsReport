var pageIndex = 1;
var grid;
var storeRule;

window.onload = function() {

	refreshList();
}


var c = 0;
var t;
var timer_is_on = 0;
function timedCount() { 
  getDataView("reportSearch",0);
  t = setTimeout(timedCount, 360000);  
}

function setAutoCheck(){
	if (document.getElementById('radioOne').checked) {
		startCount();
	}else {
		stopCount();
	}
}

function startCount() {
  if (!timer_is_on) {
    timer_is_on = 1;
    timedCount();
  }
}

function stopCount() {
  clearTimeout(t);
  timer_is_on = 0;
}

function getDataView(formId, indexView) {
	
	var path = dojo.byId("pathServer").value;
	if (indexView == 1) {
		dijit.byId("searchButtom").set('disabled', true);
		showDialogGeneral('dialogMessageWaiting');
	}
	setTimeout(function() {
		var form_data = getParams();
		$.ajax({
			type : "GET",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			url : path + "/getListQueueOpsDashboard",
			data : form_data, // Note it is important
			success : function(data) {
				relaodData(data);
				if (indexView == 1) hideDialogGeneral('dialogMessageWaiting');
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
		departmentId : dijit.byId("departmentId").get('value'),
		
	};
	return rules;
}

function resetDataForm() {
	dijit.byId('institutionId').set("value", 0);
	dijit.byId('departmentId').set("value", 0);
	
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
	 console.log(JSON.stringify(dataF));
	if (dataF != null && dataF.length > 0) {
		for (var i = 0; i < dataF.length; i++) {

			dataFresh[i] = {
				//id : (i + 1),
				col1 : dataF[i].workgroupName,
				//col2 : '',
				col3 : dataF[i].queueNoNotINyet,
				col4 : dataF[i].totalArrivedSofar,
				col5 : dataF[i].queueNoIn,
				col6 : ' ',
				col8 : ' ',
				col7 : ' ',
				col9 : dataF[i].g5m,
				col10 : dataF[i].g5t30m,
				col11 : dataF[i].g30m,
				col12 : dataF[i].maxWT,
				col13 : dataF[i].stAvg,
				col14 : dataF[i].stMax,
				col15 : dataF[i].ptSeen,
				//col16 : dataF[i].ptSeen,
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
				
				function checkColor(items, rowIndex) {
					var result = "";					
					if (items != null && items.toString() != '0'){
						result = "<span style='color:red;font-weight: bold;'>" + items + "</span>";
					}else {
						result = items;
					}
					return result;
				}
				
				var layout =  [
				               {
				                   noscroll: true,
				                   defaultCell: { width: "84px" },
				                   cells: [
				                       {name : 'Location',field : 'col1',width : '160px'},
			                          // {name : 'Login Username',field : 'col2',	width : '90px'},
			                           {name : 'Queue No. Not IN yet',field : 'col3',width : '120px'},
			                           {name : 'Total arrived so far',field : 'col4',width : '150px'},
			                           {name : 'Queue No. IN',field : 'col5',width : '60px'},   
				                   ]
				               },{
				                   defaultCell: { width: "60px" },
				                   cells: [
				                    [
			                           {name: 'Waiting Time Information at Current Location', field: "col6", styles: 'text-align: center;width:230px', colSpan: 3}, 
			                           { name: 'Pt in Queue' , field: "col7", width: 8, styles: 'text-align: center;'},
			                           {name: 'Pt Seen', field: "col8", styles: 'text-align: center;width:350px', colSpan: 3},
			                         
		                       ],[
				                           
			                           { name: '< 15m' , field: "col9", width: 8, styles: 'text-align: center;'},
			                           { name: '15 - 30 min' , field: "col10", width: 8, styles: 'text-align: center;'},
			                           { name: '> 30 min', field: "col11", width: 5, styles: 'text-align: center;', formatter:checkColor},
			 
			                           
			                           { name: 'Max WT (min)', field: "col12", width: 5, styles: 'text-align: center;'},
			                           { name: 'Average SVCT (min)', field: "col13", width: 5, styles: 'text-align: center;'},				                           
			                           { name: 'Max SVCT(min)', field: "col14", width: 5, styles: 'text-align: center;'},                    
			                           { name: 'Pt Seen', field: "col15", width: 5, styles: 'text-align: center;'},	
			                           //{ name: 'First Pt Call Time', field: "col16", width: 5, styles: 'text-align: center;'},	
				                       ]
				                   ]
				               }
				           ]
				           
				
				


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
