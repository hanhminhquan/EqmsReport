var debug=false;
function buildpopup(){
        window.addEvent('domready', function() {
	SqueezeBox.assign($$('a.modal-button'), {
		parse: 'rel'
	});
});
}
function createREQ() {
    var PROGIDS = new Array(
      "Msxml2.XMLHTTP.7.0",
      "Msxml2.XMLHTTP.6.0",
      "Msxml2.XMLHTTP.5.0",
      "Msxml2.XMLHTTP.4.0",
      "MSXML2.XMLHTTP.3.0",
      "MSXML2.XMLHTTP",
      "Microsoft.XMLHTTP"
    );
    var req = null;
      if (window.XMLHttpRequest != null){
        req = new window.XMLHttpRequest();
      }else if (window.ActiveXObject != null){
        var success = false;
        for (i = 0; i < PROGIDS.length && !success; i++)
        {   
          try
          {
            req = new ActiveXObject(PROGIDS[i]);
            success = true;
          }
          catch (ex)
          {}
        }
       }
    return req;
}

function requestGET(url, query, req) {
    myRand=parseInt(Math.random()*99999999);
    req.open("GET",url+'?'+query+'&rand='+myRand,true);
    req.send(null);
}

function requestPOST(url, query, req) {
    req.open("POST", url,true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.send(query);
}
function doCallback(objResult,resultText,functionCallBack) {
   var obj = getObj(objResult);
   if(obj!=null){
    obj.innerHTML = resultText;
    if(functionCallBack!=null && functionCallBack!=''){
      try{eval(functionCallBack);}catch (ex){}
    }
   }
}
function getParams(postForm){
      var obj,name;
      var params = '';
      if(postForm && postForm.elements)
       for(i=0;i<postForm.elements.length;i++){
            obj = postForm.elements[i];
            name=(obj.id!='')? obj.id:obj.name;
            if (obj.type == 'text'  || obj.type == 'textarea' ||  obj.type == 'select-one' || obj.type == 'select-multiple' || 
                obj.type == 'radio' || obj.type == 'checkbox' ||  obj.type == 'password' || obj.type == 'hidden' ) {                        
                        var value = '';
                        if(obj.type=='select-multiple'){						
                                for (j = 0; j < obj.length; j++)
                                {
                                    if (obj.options[j].selected){
                                        if(params!='') params +='&';
                                        params += name + "=" +encodeURIComponent(obj.options[j].value);                                                               
                                    }
                                }                                                 
                        }else {
                            if (obj.type == "select-one") {
                                if (params != '')params += '&';
                                var si = obj.selectedIndex;
                                if (si >= 0) {
                                        value = encodeURIComponent(obj.options[si].value);
                                }else {
                                        value = encodeURIComponent(trim(obj.value));
                                }
                                if(params!='') params +='&';
                                params += name + '=' + value;
                            }else if((obj.type!="checkbox" && obj.type!="radio") || (obj.type=="checkbox" && obj.checked) || (obj.type=="radio" && obj.checked)){                        
                                value = encodeURIComponent(trim(obj.value));
                                if(params!='') params +='&';
                                params += name + '=' + value;
                            }
                        }
            }
        }
        return params;
}
function praseParam(param, params){
    if(param!='' && param!=null){
        var ps=param.split(":");//
        param='';
        if(0==(ps.length%2)){
            for(i=0;i<ps.length/2;i++){
                if(param!='') param += '&';
                param += ps[2*i] + '=' + encodeURIComponent(ps[2*i+1]);
            }
        }
      
        if(param !='') {
            if(params!=''){
                params += '&' + param;
            }else{
                params = param;
            }
        }
    }     
    return params;
}
function postAjax(formName,objResult,param,functionCallBack) {
    var postForm = document.forms[formName];
    var params = praseParam(param,getParams(postForm));    
    doAjax(postForm.action,params,objResult,postForm.method,0,functionCallBack);
}
function doAjax(url,query,objResult,reqtype,getxml,functionCallBack) {
  var myreq = createREQ();
  if (myreq == null) alert("Web browser does not support AJAX.");
        myreq.onreadystatechange = function() 
                            {
                                if(myreq.readyState == 4) { 
                              alert(myreq.responseText);
                                   if(myreq.status == 200) {                                    
                                      var resultText = myreq.responseText;
                                      if(getxml==1) {                                
                                         resultText = myreq.responseXML;                      
                                      }
                                      doCallback(objResult,resultText,functionCallBack);
                                    }
                                  }
                            }
    if(reqtype=='post') {
        requestPOST(url,query,myreq);
    } else {
        requestGET(url,query,myreq);
    }
}
function resultAjax(formName,param) {
    var postForm = document.forms[formName];
    var params = praseParam(param,getParams(postForm));    
    return getAjax(postForm.action,params,postForm.method);
}
function getAjax(url,query,reqtype) {
  var myreq = createREQ();
  if (myreq == null) alert("Web browser does not support AJAX.");
        myreq.onreadystatechange = function() 
            {
                if(myreq.readyState == 4) {      
              alert(myreq.responseText);
                   if(myreq.status == 200) {                                    
                      return myreq.responseText;
                    }
                  }
            }
    if(reqtype=='post') {
        requestPOST(url,query,myreq);
    } else {
        requestGET(url,query,myreq);
    }
}
//------------------------------------------------------Ham move object in screen -------------------------
var  dragobj=null, h1, i1, oLeft, oTop; 
function rel(ob){return getObj(ob);}
var cursor = {x:0, y:0};
function getCursorPos(e) {
    e = e || window.event;
    if (e.pageX || e.pageY) {
        cursor.x = e.pageX;
        cursor.y = e.pageY;
    }
    else {
        var de = document.documentElement;
        var db = document.body;
        cursor.x = e.clientX +
            (de.scrollLeft || db.scrollLeft) - (de.clientLeft || 0);
        cursor.y = e.clientY +
            (de.scrollTop || db.scrollTop) - (de.clientTop || 0);
    }
    return cursor;
}
//********************************************************
  function makeObjectToDrag(obj){
    dragobj =rel(obj);
    document.onmousedown=startMove;
    document.onmouseup = drop;
    document.onmousemove = moving;
   } 
   //********************************************************
  function startMove(e){
    getCursorPos(e);    
    try{
    i1=cursor.x- dragobj.offsetLeft;
    h1=cursor.y- dragobj.offsetTop;
    }catch(ex){
    }
  }
//******************************************************** 
  function drop(){
  
    if(dragobj){  
      dragobj = null;    
    }
  }
  //********************************************************
  function moving(e){
    getCursorPos(e);
    if(dragobj){
      oLeft=cursor.x-i1;
      oTop=cursor.y-h1;
      dragobj.style.left = oLeft + 'px';
      dragobj.style.top  = oTop + 'px';
    }
  }
//********************************************************
function openWindow(formName,param,functionCallBack,winName){   
            if(winName==null || winName=='') winName='winPopup';            
            var win=getObj(winName);
            if(win==null) {
                win = document.createElement('div');
                win.id=winName;
                win.className='popup';
                win.align='center';
                var postForm = document.forms[formName];
                if(postForm){
                    postForm.appendChild(win);
                }else{
                    document.body.appendChild(win);
                }
            }
            postAjax(formName,winName,param,"showWindow('" + winName + "');"+functionCallBack);
}

function showWindow(winName){
    if(winName==null) winName='winPopup';
    var win = getObj(winName);
    if(win==null) win=getObj(winName,self.parent.document);
    try{
        var _maxWidth=0;
        for(i=0;i<win.childNodes.length;i++){
            try{
                if(_maxWidth<win.childNodes[i].width) _maxWidth=win.childNodes[i].width;
            }catch(ex){}
        }
        var ie = document.all && !window.opera;
	var domclientWidth=document.documentElement && parseInt(document.documentElement.clientWidth) || 100000 //Preliminary doc width in non IE browsers
	var body=(document.compatMode=="CSS1Compat")? document.documentElement : document.body;

	var scroll_top=window.pageYOffset? window.pageYOffset:body.scrollTop;
	var scroll_left=window.pageXOffset?window.pageXOffset:body.scrollLeft - 180;
        
	var docwidth=(ie)? body.clientWidth : (/Safari/i.test(navigator.userAgent))? window.innerWidth : Math.min(domclientWidth, window.innerWidth-16)

        win.style.width=_maxWidth + 'px';
        win.style.top = scroll_top + 110 + 'px';
        win.style.left = scroll_left + (docwidth - _maxWidth)/2 + 'px';
    }catch(ex){
    
    }finally{
       win.style.display='block';
    }
}
function closeWindow(winName){
    if(winName==null) winName='winPopup';
    var win = getObj(winName);
    try{
        win.style.display='none';
        win.innerHTML='';
    }catch(ex){
    }
}
