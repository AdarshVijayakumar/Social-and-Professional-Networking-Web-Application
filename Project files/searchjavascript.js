var autoCompleteField;
var autoCompleteTable;
var autoCompleteRow;
var isAC;
var request;


function init() {
  
}


function doCompletion() {
  autoCompleteField = document.getElementById("searchid");
    autoCompleteTable = document.getElementById("complete-table");
    autoCompleteRow = document.getElementById("auto-row");
    
    var url = "AjaxAutoCompleteServlet?action=complete&id=" + escape(autoCompleteField.value);
    request = initautoCompleteRequest();
    request.open("GET", url, true);
    request.onreadystatechange = callbackAutoComplete;
    request.send(null);
}

function initautoCompleteRequest() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
	else {
		return(null);
	}
}

function callbackAutoComplete() {
    clearAutoCompleteTable();

    if (request.readyState == 4) {
        if (request.status == 200) {
            parseACMessages(request.responseXML);
        }
    }
}
function clearAutoCompleteTable() {
	//var loop;
    if (autoCompleteTable.getElementsByTagName("tr").length > 0) {
        autoCompleteTable.style.display = 'none';
        for (loop = autoCompleteTable.childNodes.length -1; loop >= 0 ; loop--) {
            autoCompleteTable.removeChild(autoCompleteTable.childNodes[loop]);
        }
    }
}

function parseACMessages(resXML) {
    
    // no matches returned
    if (resXML == null) {
        return false;
    } else {

        var products = resXML.getElementsByTagName("products")[0];

        if (products.childNodes.length > 0) {
            autoCompleteTable.setAttribute("bordercolor", "black");
            autoCompleteTable.setAttribute("border", "1");
    
            for (loop = 0; loop < products.childNodes.length; loop++) {
                var product = products.childNodes[loop];
                var id = product.getElementsByTagName("id")[0];
                var name = product.getElementsByTagName("name")[0];
                appendProduct(id.childNodes[0].nodeValue,
                    name.childNodes[0].nodeValue);
            }
        }
    }
}
function  appendProduct(id,name)
{
	var row;
    var column;
    var lElement;
    
    
        autoCompleteTable.style.display = 'table';
		autoCompleteTable.style.position = 'absolute';
		autoCompleteTable.style.width = '210px';
		autoCompleteTable.style.align = 'right';
		autoCompleteTable.style.background = '#fff';
        row = document.createElement("tr");
        column = document.createElement("td");
        row.appendChild(column);
        autoCompleteTable.appendChild(row);


    column.className = "popupCell";
    column.setAttribute("style", "background-color:white; height:40px;");

    lElement = document.createElement("a");
    lElement.className = "popupItem";
    lElement.setAttribute("href", "AjaxAutoCompleteServlet?action=lookup&id=" + name);
    lElement.setAttribute("style", "text-decoration:none; font-size:14px; padding-left:15px;");
    lElement.appendChild(document.createTextNode(name));
    column.appendChild(lElement);
}
