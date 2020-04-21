dojo.require("dijit.Menu");
dojo.require("dijit.MenuItem");
dojo.require("dijit.PopupMenuItem");

dojo.require("dijit.Calendar");
dojo.require("dijit.ColorPalette");
dojo.require("dijit.ProgressBar");
dojo.require("dijit.TitlePane");
dojo.require("dijit.Tooltip");
dojo.require("dijit.Tree");

dojo.require("dijit.MenuBar");
dojo.require("dijit.MenuBarItem");
dojo.require("dijit.PopupMenuBarItem");

// editor:
dojo.require("dijit.Editor");
dojo.require("dijit._editor.plugins.FontChoice");
dojo.require("dijit._editor.plugins.LinkDialog");
dojo.require("dijit._editor.plugins.TextColor");
dojo.require("dijit._editor.plugins.FullScreen");
dojo.require("dijit._editor.plugins.ViewSource");
dojo.require("dijit._editor.plugins.NewPage");
dojo.require("dijit._editor.plugins.Print");
dojo.require("dijit._editor.plugins.ToggleDir");

dojo.require("dojox.form.CheckedMultiSelect");

// dnd:a
dojo.require("dojo.dnd.Source");

// various Form elements
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.FilteringSelect");
dojo.require("dijit.form.Select");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.TimeTextBox");
dojo.require("dijit.form.CurrencyTextBox");
dojo.require("dijit.form.Button");
dojo.require("dijit.form.DropDownButton");
dojo.require("dijit.form.ComboButton");
dojo.require("dijit.form.ToggleButton");
dojo.require("dijit.InlineEditBox");
dojo.require("dijit.form.NumberSpinner");
dojo.require("dijit.form.ValidationTextBox");

dojo.require("dijit.form.VerticalSlider");
dojo.require("dijit.form.VerticalRuleLabels");
dojo.require("dijit.form.VerticalRule");
dojo.require("dijit.form.HorizontalSlider");
dojo.require("dijit.form.HorizontalRuleLabels");
dojo.require("dijit.form.HorizontalRule");

// layouts used in page
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.layout.TabContainer");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.LinkPane");
dojo.require("dijit.Dialog");

dojo.require("dijit.Menu");

// scan page for widgets and instantiate them
dojo.require("dojo.parser");
dojo.require("dojo.store.Memory");
dojo.require("dojox.grid.TreeGrid");
dojo.require("dijit.tree.ObjectStoreModel");
dojo.require("dijit.tree.ForestStoreModel");
dojo.require("dijit.Tree");
dojo.require("dijit.Fieldset");

// humm?
dojo.require("dojo.date.locale");

dojo.require("dojox.grid.DataGrid");
dojo.require("dojox.grid.cells");
dojo.require("dojox.grid.cells.dijit");
dojo.require("dojox.grid.EnhancedGrid");
dojo.require("dojox.grid.enhanced.plugins.Pagination");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.enhanced.plugins.CellMerge");
dojo.require("dojox.grid._CheckBoxSelector");

dojo.require("dojo.data.ObjectStore");
// for the Tree
dojo.require("dojo.data.ItemFileReadStore");

dojo.require("dojox.form.BusyButton");
dojo.require("dijit.form.MultiSelect");

dojo.require("dijit.dijit");
dojo.require("dojox.layout.TableContainer");

dojo.require("dojo._base.array");
dojo.require("dojo._base.lang");

dojo.require("dijit.TooltipDialog");
dojo.require("dijit.registry");
dojo.require("dojo.on");
dojo.require("dijit.popup");


dojo.addOnLoad(function() {
   
        var start = new Date().getTime();
        dojo.parser.parse(dojo.byId('container'));
        console.info("Total parse time: " + (new Date().getTime() - start) + "ms");

//        dojo.byId('loaderInner').innerHTML += " done.";
//        setTimeout(function hideLoader(){
//                var loader = dojo.byId('loader');
//                dojo.fadeOut({ node: loader, duration:500,
//                        onEnd: function(){
//                                loader.style.display = "none";
//                        }
//                }).play();
//        }, 250);    
});

function resetButtom()
{
  if (dijit.byId("saveButtom")) dijit.byId("saveButtom").set('disabled', false);
  // dijit.byId("editButtom").set('disabled', true);
   if (dijit.byId("deleteButtom")) dijit.byId("deleteButtom").set('disabled', true);
   if (dijit.byId("resetButtom")) dijit.byId("resetButtom").set('disabled', true);
}

function priEditButtom()
{
 if (dijit.byId("saveButtom"))  dijit.byId("saveButtom").set('disabled', false);
   //dijit.byId("editButtom").set('disabled', false);
 if (dijit.byId("deleteButtom"))  dijit.byId("deleteButtom").set('disabled', false);
 if (dijit.byId("resetButtom"))  dijit.byId("resetButtom").set('disabled', false);
}


function disnableButtomQueue()
{
   dijit.byId("callQueueButtom").set('disabled', true);
   dijit.byId("missQueueButtom").set('disabled', true);
   dijit.byId("endQueueButtom").set('disabled', true);
}


function disnableTimeout(obj, timeDeplay)
{
   dijit.byId(obj).set('disabled', true);
   setTimeout(function(){ 
          dijit.byId(obj).set('disabled', false);
    }, timeDeplay); 
}


function setDisnable(obj)
{  
   dijit.byId(obj).set('disabled', true);
}

function setUnDisnable(obj)
{  
   dijit.byId(obj).set('disabled', false);
}


function dialogMessage(txtContent)
{
    var thisdialog = new dijit.Dialog({ title: 'The page says:', content: txtContent, style: "width: 400px" });
    dojo.body().appendChild(thisdialog.domNode);
    thisdialog.startup();
    thisdialog.show();
}

function showDialogGeneral(objView) {
       dijit.byId(objView).show();
 }
 function hideDialogGeneral(objView) {
       dijit.byId(objView).hide()
 }
 