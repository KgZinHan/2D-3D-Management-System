<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<title>Heart Referee</title>
	<link rel="stylesheet" href="./cssStyle/styles.css">
	<link rel="stylesheet" href="./cssStyle/all.css">
    <script src="JavaScript/javaScript.js" defer></script>
</head>
<body class="mv-basic-bg" tabindex="-1" style="overflow-x: hidden" onload="checkNumber('${number}')">
	<div id="msgbox01" class="mv-menu-msgBox mv-animate-fade-out" style="opacity: 0" onclick="displayNone('msgbox01')">${message}</div>
	<div class="mv-tab-bar">
		<h3>
			<a href="Table" class="tab-bar-button hover-effect margin-left">Home</a>
		</h3>
		<h3>
			<a href="WaitingTable?m=money" class="tab-bar-button hover-effect margin-left" style="color:${waiting}">Waiting Table</a>
		</h3>
		<h3>
			<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left">Recover Check</a>
		</h3>
		<h3>
			<a href="RecoverPageController?sellerName=Default" class="tab-bar-button hover-effect margin-left">Recover Note</a>
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
	<div id="userfield" class="user-total-field">
		<p>total money - ${totalMoney} ks</p>
		<h3 style="margin-right: 50px;margin-left: 50px;font-family: robom">${number} = P (${money})</h3>
		<p style="margin-right: 50px">total recover - ${totalRecover} ks</p>
		<a href="AutoReport?number=${number}">
			<button id="btn3" class="f-pg-enter-no hover-effect" style="width: 100px;cursor: pointer">Auto Report</button>	
		</a>
	</div>	
	<div class="mv-margin-top mv-display-middle">
		<button class="back-button hover-effect" onclick="goBack()"><i class="fas fa-arrow-up fa-2x"></i></button>
		<a href="SummaryTable?number=${number-1}&money=${money}">
		<button id="btn1" class="back-button hover-effect" style="float: left"><i class="fas fa-angle-left fa-2x"></i></button></a>
		<a href="SummaryTable?number=${number+1}&money=${money}">
		<button id="btn2" class="back-button hover-effect" style="float: right;margin-right: 30px"><i class="fas fa-angle-right fa-2x"></i></button></a>
		
		<div id="tb02" class="mv-table-style" style="height: 600px">
			<table class="mv-waiting-table" style="width: 600px">
				<tr class="mv-list-table-head" style="font-size: 20px">
					<th><h4>Name</h4></th>
					<th style="width: 150px"><h4>Total Money</h4></th>
					<th style="width: 80px"><h4>P</h4></th>
					<th style="width: 100px"><h4>Extra</h4></th>
				</tr>
				<c:forEach items="${twoDList}" var="result">
					<tr class="mv-list-table-data" >
						<td><h3>${result.userName}</h3></td>
						<td><h3>${result.userMoney} ks</h3></td>
						<td><h3 style="color: firebrick">${result.money}</h3></td>
						<td><a href="Final?username=${result.userName}&number=${number}" style="font-style: normal"><h3>report</h3></a></td>
					</tr>
				</c:forEach>
				<c:forEach items="${recoverList}" var="recover">
					<tr class="mv-list-table-data" style="color: green">
						<td><h3>${recover.sellerName}</h3></td>
						<td><h3>${recover.totalRecover} ks</h3></td>
						<td><h3>${recover.sellerMoney}</h3></td>
						<td>-</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>