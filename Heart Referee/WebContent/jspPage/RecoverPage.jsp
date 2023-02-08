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
		<div class="mv-recover-bg">
			 <div class="mv-tab-bar">
				<a href="Table" class="recover-button hover-effect margin-left" style="color:">
					<h3>Home</h3>
				</a>
				<a href="WaitingTable?m=default" class="waiting-button hover-effect margin-left">
					<h3>Waiting Table</h3>
				</a>
				<a href="Recover?limit=1500" class="ftbl-button hover-effect margin-left">
					<h3>Recover Check</h3>
				</a>
				<a href="RecoverPageController" class="recover-button hover-effect margin-left" style="color:${recoverNote}">
					<h3>Recover Note</h3>
				</a>
				<a href="FullTableController" class="ftbl-button hover-effect margin-left">
					<h3>Full Table</h3>
				</a>
			</div>		
			<div class="user-total-field">
				total money&emsp;-&emsp;${totalMoney} ks
				<div style="margin-left: 100px"> 
  					total recover money&emsp;-&emsp;${userTotalMoney} ks
				</div>
			</div>	
			
			<div class="mv-input-field mv-margin-top">
				<form id="singleInput" action="RecoverPageController" method="post" style="display: none">
                  	<input id="number1" type="text" name="number" autofocus="autofocus" tabindex="1" class="mv-number-button" placeholder="Enter" min="00" max="99">
                   	<input type="number" name="money" tabindex="2" class="mv-money-drop-button" placeholder="Enter" min="50" max="50000" step="50" required>
                 	<h3 class="mv-special-note">Note !<br>** &nbsp;&nbsp;=&nbsp; a puu<br> // &nbsp;&nbsp;&nbsp;=&nbsp; natkhat <br> ++ &nbsp;=&nbsp; power <br> /* &nbsp;&nbsp;=&nbsp; nyiko</h3>
                 	<h3 class="mv-page-field" style="color: red;border-color: red">Recover</h3>
                 	<input id="submitbtn" class="mv-submit-btn hover-effect" type="submit" value="Add">
                </form>

                <form id="multiInput" action="multiInputRecover" method="post" >                	
					<div class="multiple-input-tab">
						<i style="color:white">1</i>&nbsp;<input id="MNumber1" type="number" name="number1" class="multiple-input" placeholder="Enter" min="00" max="99" autofocus="autofocus" required>
						<i style="color:white">2</i>&nbsp;<input id="number" type="number" name="number2" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">3</i>&nbsp;<input id="number" type="number" name="number3" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">4</i>&nbsp;<input id="number" type="number" name="number4" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">5</i>&nbsp;<input id="number" type="number" name="number5" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">6</i>&nbsp;<input id="number" type="number" name="number6" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">7</i>&nbsp;<input id="number" type="number" name="number7" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">8</i>&nbsp;<input id="number" type="number" name="number8" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">9</i>&nbsp;<input id="number" type="number" name="number9" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">10</i>&nbsp;<input id="number" style="margin-left:-10px" type="number" name="number0" class="multiple-input" placeholder="Enter" min="00" max="99">
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
					<div class="multiple-input-tab">
						<i style="color:white">1</i>&nbsp;<input id="CNumber1" type="number" name="number1" class="multiple-input" placeholder="Enter" min="00" max="99" autofocus="autofocus" required>
						<i style="color:white">2</i>&nbsp;<input id="number" type="number" name="number2" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">3</i>&nbsp;<input id="number" type="number" name="number3" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">4</i>&nbsp;<input id="number" type="number" name="number4" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">5</i>&nbsp;<input id="number" type="number" name="number5" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">6</i>&nbsp;<input id="number" type="number" name="number6" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">7</i>&nbsp;<input id="number" type="number" name="number7" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">8</i>&nbsp;<input id="number" type="number" name="number8" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">9</i>&nbsp;<input id="number" type="number" name="number9" class="multiple-input" placeholder="Enter" min="00" max="99">
						<i style="color:white">10</i>&nbsp;<input id="number" style="margin-left:-10px" type="number" name="number0" class="multiple-input" placeholder="Enter" min="00" max="99">
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
		            <a href="SortRecover?m=money"><i class="fas fa-file"></i>&emsp;Normal Table</a>
		            <a href="RecoverPageController"><i class="fas fa-file-contract"></i>&emsp;History Table</a>
		            <a href="RecoverDetails"><i class="fas fa-file-invoice"></i>&emsp;Details Table</a>
	           	</div>
				<form action="SearchRecover" method="get" id="search01">
					&nbsp;<i class="fab fa-sistrix">&nbsp;</i><input type="number" name="number" class="mv-list-search-box"  placeholder="Search Total..." required>
					
				</form>
				<form action="RecoverDetails" method="post" id="search02">
					&nbsp;<i class="fas fa-eraser">&nbsp;</i><input type="number" name="number" class="mv-list-search-box"  placeholder="Search Details..." required>
				</form>			
			</div>
			<div class="mv-table-style" style="margin-top: 55px">
				<table class="mv-list-table">
					<tr class="mv-list-table-head">	
						<th style="display: ${numberLink}">
							<a href="SortRecover?m=number">
								<button class="sort-button"  style="background-color: ${numberHColor}">
									<h4>Number<i class="fas fa-sort" style="float: right"></i></h4>
								</button>
							</a>
						</th>				
						<th style="width: 300px;display: none; display: ${noteLink}"><h4>Recover Note</h4></th>					
						<th style="width:40px;display: none;display: ${rLink}"><h4>R</h4></th>				
						<th>
							<a href="SortRecover?m=money">
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
							<a href="RecoverDelete?id=${twoD.id}"><i class="fas fa-trash"></i></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.5.0 &nbsp; Design by N0iSyLuvie</footer>
	</body>
</html>