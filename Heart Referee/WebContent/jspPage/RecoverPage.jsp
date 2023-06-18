<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html tabindex="-1"> 
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width,initial-scale=1.0">
		<title>Heart Referee</title>
		<link rel="stylesheet" href="./cssStyle/styles.css">
		<link rel="stylesheet" href="./cssStyle/all.css">
        <script src="JavaScript/javaScript.js" defer></script>
	</head>
	<body tabindex="-1" style="overflow-x: hidden">
		<div id="modal03" class="mv-display-center mv-view-image mv-animate-zoom"> 
            <button class="mv-display-topRight mv-logIn-close-button hover-effect" onclick="displayNone('modal03')"><i class="far fa-times-circle"></i></button>
            <h2 class="mv-username"><i class="fas fa-user-tie"></i>&emsp;
               	${sellerName}
   			</h2>
             <div id="radioTab" class="mv-table-input">
            	<c:forEach items="${recoverList}" var="seller">
            		<a href="RecoverPageController?sellerName=${seller.sellerName}">
	            		<h5 class="user-choose">
	             			${seller.sellerName}&emsp;<spam style="float: right">${seller.sellerZ}z </spam>
	             		</h5>
             		</a>
             	</c:forEach>
			</div>
             
            <a href="RecoverSeller?mode=nothing">
            	<button class="log-out-btn hover-effect"><i class="fas fa-user-plus"></i>&nbsp;New</button>
            </a>
        </div>
		<div class="mv-basic-bg">
			 <div class="mv-tab-bar">
			 	<div class="user-name hover-effect" onclick="displayBlock('modal03')">
					<i class="fas fa-users"></i>&nbsp;
					${sellerName}
				</div>
				<h3>
					<a href="Table" class="tab-bar-button hover-effect margin-left">Home</a>
				</h3>
				<h3>
					<a href="WaitingTable?m=money" class="tab-bar-button hover-effect margin-left">Waiting Table</a>
				</h3>
				<h3>
					<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left">Recover Check</a>
				</h3>
				<h3>
					<a href="RecoverPageController?sellerName=Default" class="tab-bar-button hover-effect margin-left" style="color:${recoverNote}">Recover Note</a>
				</h3>
				<h3>
					<a href="FullTableController" class="tab-bar-button hover-effect margin-left">Full Table</a>
				</h3>
				<h3>
					<a href="Final" class="tab-bar-button hover-effect margin-left">Report</a>
				</h3>
				<h3>
					<a href="HResult" class="tab-bar-button hover-effect margin-left">Ledger</a>
				</h3>
			</div>	
			<div class="user-total-field">
				total recover in ${sellerName}&emsp;-&emsp;${totalSellerRecover} ks
				<div style="margin-left: 50px"> 
  					total recover &emsp;-&emsp;${userTotalMoney} ks
				</div>
				<span style="margin-left: 50px">total money&emsp;-&emsp;${totalMoney} ks</span>
			</div>	
			
			<div class="mv-input-field mv-margin-top">
				<form id="singleInput" action="RecoverPageController" method="post" style="display: none">
					<input type="hidden" name="sellerName" value="${sellerName}"/>
                  	<input id="number1" type="text" name="number" autofocus="autofocus" tabindex="1" class="mv-number-button" placeholder="Enter" min="00" max="99">
                   	<input type="number" name="money" tabindex="2" class="mv-money-drop-button" placeholder="Enter" min="50" max="50000" step="50" required>
                 	<h3 class="mv-special-note" style="border-color: red;color: red">
	                 	&emsp;Shortcut Note!
	                 	<br>** &nbsp;&nbsp;=&nbsp; a puu
	                 	<br> // &nbsp;&nbsp;&nbsp;=&nbsp; natkhat 
	                 	<br> ++ &nbsp;=&nbsp; power 
	                 	<br> /* &nbsp;&nbsp;=&nbsp; nyiko
	                 	<br> ?+ &nbsp;=&nbsp; ? bk
	                 	<br> ?* &nbsp;&nbsp;=&nbsp; ? start
	                 	<br> *? &nbsp;&nbsp;=&nbsp; ? end
	                 	<br> *?* &nbsp;=&nbsp; 19 numbers
	                 	<br> *?** =&nbsp; 20 numbers
                 	</h3>
                 	<h3 class="mv-page-field" style="color: red;border-color: red">Recover</h3>
                 	<input id="submitbtn" class="mv-submit-btn hover-effect" type="submit" value="Add">
                </form>

                <form id="multiInput" action="multiInputRecover" method="post" >                	
					<input type="hidden" name="sellerName" value="${sellerName}"/>
					<div class="multiple-input-tab">
						<i style="color:white">1</i>&emsp;<input id="MNumber1" type="number" name="number1" class="multiple-input" placeholder="Enter" min="00" max="99" autofocus="autofocus" required>
						<br><i style="color:white">2</i>&emsp;<input id="number" type="number" name="number2" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">3</i>&emsp;<input id="number" type="number" name="number3" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">4</i>&emsp;<input id="number" type="number" name="number4" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">5</i>&emsp;<input id="number" type="number" name="number5" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">6</i>&emsp;<input id="number" type="number" name="number6" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">7</i>&emsp;<input id="number" type="number" name="number7" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">8</i>&emsp;<input id="number" type="number" name="number8" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">9</i>&emsp;<input id="number" type="number" name="number9" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">10</i>&emsp;<input id="number" style="margin-left:-10px" type="number" name="number0" class="multiple-input" placeholder="Enter" min="00" max="99">
					</div>
					<div id="radioTab" class="mv-radio-btn">
						<h2 style="color: white">R</h2>
						<label class="container">Yes
							<input id="rd1" type="radio" name="reverse" value="yes" accesskey="r" onclick="myFocus()" checked>
							<span class="checkmark"></span>
						</label>
						<label class="container">No
							<input id="rd1" type="radio" name="reverse" value="no" accesskey="t" onclick="myFocus()">
							<span class="checkmark"></span>
						</label>
	            	</div>
                    <input type="number" name="money" class="mv-money-drop-button" placeholder="Enter" min="50" max="50000" step="50" required>
                    <h3 class="mv-page-field" style="color: red;border-color: red">Recover</h3>
                 	<input id="submitbtn" class="mv-submit-btn hover-effect" type="submit" value="Add">
				</form>
				
				<form id="customInput" action="customInputRecover" method="post" style="display: none">
					<input type="hidden" name="sellerName" value="${sellerName}"/>
					<div class="multiple-input-tab">
						<i style="color:white">1</i>&emsp;<input id="CNumber1" type="number" name="number1" class="multiple-input" placeholder="Enter" min="00" max="99" autofocus="autofocus" required>
						<br><i style="color:white">2</i>&emsp;<input id="number" type="number" name="number2" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">3</i>&emsp;<input id="number" type="number" name="number3" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">4</i>&emsp;<input id="number" type="number" name="number4" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">5</i>&emsp;<input id="number" type="number" name="number5" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">6</i>&emsp;<input id="number" type="number" name="number6" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">7</i>&emsp;<input id="number" type="number" name="number7" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">8</i>&emsp;<input id="number" type="number" name="number8" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">9</i>&emsp;<input id="number" type="number" name="number9" class="multiple-input" placeholder="Enter" min="00" max="99">
						<br><i style="color:white">10</i>&emsp;<input id="number" style="margin-left:-10px" type="number" name="number0" class="multiple-input" placeholder="Enter" min="00" max="99">
					</div>
					<input type="number" name="money" style="right: 200px" class="mv-money-drop-button" placeholder="Enter" min="50" max="50000" step="50" required>
                    <h2 style="color: white; position: absolute;right: 80px">R</h2>
                    <input type="number" name="rMoney" class="mv-money-drop-button" placeholder="Enter" min="50" max="50000" step="50" required>
                    <h3 class="mv-page-field" style="color: red;border-color: red">Recover</h3>
                    <input id="submitbtn" class="mv-submit-btn hover-effect" type="submit" value="Add" >
				</form>
				
                <button id="singlebtn" class="special-button hover-effect" onclick="switchTab('singleInput')"  accesskey="s">Special(S)</button>
                <button id="multibtn" class="special-button hover-effect" onclick="switchTab('multiInput')" style="display: none" accesskey="z">Multi(Z)</button>
                <button id="multibtn" class="special-button hover-effect" style="left: 120px" onclick="switchTab('customInput')" accesskey="c">Custom(C)</button>
			</div>
            <div class="mv-home-panel">
	            <button class="mv-drop-down-button hover-effect" onmouseenter="displayBlock('drop01')" onmouseleave="displayNone('drop01')">
	                Table&emsp;<i class="fas fa-caret-down" style="float: right"></i>
	            </button>
	            <div id="drop01" class="mv-drop-down-list" onmouseenter="displayBlock('drop01')" onmouseleave="displayNone('drop01')">
		            <a href="SortRecover?m=money&sellerName=${sellerName}"><i class="fas fa-file"></i>&emsp;Normal Table</a>
		            <a href="RecoverPageController?sellerName=${sellerName}"><i class="fas fa-file-contract"></i>&emsp;History Table</a>
		            <a href="RecoverDetails?sellerName=${sellerName}"><i class="fas fa-file-invoice"></i>&emsp;Details Table</a>
	           	</div>
				<form action="SearchRecover" method="get" id="search01">
					<input type="hidden" name="sellerName" value="${sellerName}">
					&nbsp;<i class="fab fa-sistrix">&nbsp;</i><input type="number" name="number" class="mv-list-search-box"  placeholder="Search Total..." required>
					
				</form>
				<form action="RecoverDetails" method="post" id="search02">
					<input type="hidden" name="sellerName" value="${sellerName}">
					&nbsp;<i class="fas fa-eraser">&nbsp;</i><input type="number" name="number" class="mv-list-search-box"  placeholder="Search Details..." required>
				</form>			
			</div>
			<div class="mv-table-style" style="margin-top: 45px">
				<table class="mv-list-table">
					<tr class="mv-list-table-head">	
						<th style="display: ${numberLink}">
							<a href="SortRecover?m=number&sellerName=${sellerName}">
								<button class="sort-button"  style="background-color: ${numberHColor}">
									<h4>Number<i class="fas fa-sort" style="float: right"></i></h4>
								</button>
							</a>
						</th>				
						<th style="width: 300px;display: none; display: ${noteLink}"><h4>Recover Note</h4></th>					
						<th style="width:40px;display: none;display: ${rLink}"><h4>R</h4></th>				
						<th>
							<a href="SortRecover?m=money&sellerName=${sellerName}">
								<button class="sort-button"  style="background-color: ${moneyHColor}" >
									<h4>Money<i class="fas fa-sort" style="float: right"></i></h4>
								</button>
							</a>
						</th>		
						<th style="display: none;display: ${rNumberLink}"><h4>R Money</h4></th>					
						<th style="display: none;display: ${totalLink}"><h4>Total</h4></th>
						<th style="display: none;display: ${totalMoneyLink}"><h4>Total</h4></th>			
						<th style="width: 60px;display: none;display: ${deleteLink}"><h4>Delete</h4></th>
					</tr>
					
					<c:forEach items="${twoDList}" var="twoD">
						<tr id="${twoD.number}" class="mv-list-table-data" >
							<td style="color: ${twoD.color};display: ${numberLink}"><h3>${twoD.number}</h3></td>
							<td style="display: none;display: ${noteLink}"><h3>${twoD.note}</h3></td>
							<td style="display: none;display: ${rLink}"><h3>${twoD.r}</h3></td>
							<td style="color: ${twoD.color}"><h3>${twoD.money}</h3></td>
							<td style="display: none;display: ${totalMoneyLink}"><h3>${twoD.quantity}</h3></td>
							<td style="display: none; display: ${rNumberLink}"><h3>${twoD.rNumber}</h3></td>				
							<td style="display: none;display: ${totalLink}"><h3>${twoD.total}</h3></td>
							<td style="display: none; display: ${deleteLink}">
							<a href="RecoverDelete?id=${twoD.id}&sellerName=${sellerName}"><i class="fas fa-trash"></i></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.8.0 &nbsp; Design by N0iSyLuvie</footer>
	</body>
</html>