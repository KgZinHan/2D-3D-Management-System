/*
 * 
 * 
 * 
 * Functions 

/*common functions*/

var myVar = setInterval(function () {myTimer()}, 100);
function myTimer() {
    var date = new Date();
    document.getElementById("time").innerHTML = date.toLocaleTimeString();
}

function switchTab(id){
	var singleInputTab = document.getElementById("singleInput");
	var multiInputTab = document.getElementById("multiInput");
	var customInputTab = document.getElementById("customInput");
	var singlebtn = document.getElementById("singlebtn");
	var multibtn = document.getElementById("multibtn");
	var autoFocusNo = document.getElementById("number1");
	var autoFocusMulti = document.getElementById("MNumber1");
	var autoFocusCustom = document.getElementById("CNumber1");
	if(id == "multiInput"){
		singleInputTab.style.display = 'none';
		multiInputTab.style.display = 'block';
		customInputTab.style.display = 'none';
		singlebtn.style.display = 'block';
		multibtn.style.display = 'none';
		autoFocusMulti.focus();
	}
	else if(id == 'singleInput'){
		singleInputTab.style.display = 'block';
		multiInputTab.style.display = 'none';
		customInputTab.style.display = 'none';
		singlebtn.style.display = 'none';
		multibtn.style.display = 'block';
		autoFocusNo.focus();
	}
	else if(id == 'customInput'){
		singleInputTab.style.display = 'none';
		multiInputTab.style.display = 'none';
		customInputTab.style.display = 'block';
		autoFocusCustom.focus();
	}

}

function myFocus(){
	var autoFocusNo = document.getElementById("number1");
	var autoFocusMulti = document.getElementById("MNumber1");
	autoFocusNo.focus();
	autoFocusMulti.focus();
}

//display none
function displayNone(id){
	document.getElementById(id).style.display = 'none';
}


//display switch (toggle)
function clickfunction(id){
	if(document.getElementById(id).style.background == 'deepskyblue'){
		document.getElementById(id).style.background = 'rgba(240,240,240,0.95)';
	}
	else{
		document.getElementById(id).style.background = 'deepskyblue';
	}
}

//display block
function displayBlock(id){
	document.getElementById(id).style.display = 'block';
}


//display switch (toggle)
function displaySwitch(id){
	if(document.getElementById(id).style.display == 'block'){
		displayNone(id);
	}
	else{
		displayBlock(id);
	}
}

function toggleSwitch(id1,id2){
	if(document.getElementById(id1).style.display == 'none'){
		displayNone(id2);
		displayBlock(id1);
	}
	else{
		displayBlock(id2);
		displayNone(id1);
	}
}

//display block
function hideCol(col) {
 
    var tbl = document.getElementById("tblMain");
    col = parseInt(col, 10);
    col = col-1;
    if (tbl != null) {
        for (var i = 0; i < tbl.rows.length; i++) {
            for (var j = 0; j < tbl.rows[i].cells.length; j++) {
                tbl.rows[i].cells[j].style.display = "";
                if (j == col){
                    tbl.rows[i].cells[j].style.display = "none";
                tbl.rows[i].cells[j-1].style.display = "none";
                }
            }
        }
    }
}

//return back
function goBack(){
    window.history.back();
}

function checkNumber(num){
	if(num == 0){
		document.getElementById("btn1").style.display = "none";
	}
	if(num == 99){
		document.getElementById("btn2").style.display = "none";
	}
}


/*****************/
