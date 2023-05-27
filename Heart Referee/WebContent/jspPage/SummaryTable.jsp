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
	<div class="mv-tab-bar">
		<h3>
			<a href="Table" class="tab-bar-button hover-effect margin-left">Home</a>
		</h3>
		<h3>
			<a href="WaitingTable?m=default" class="tab-bar-button hover-effect margin-left" style="color:${waiting}">Waiting Table</a>
		</h3>
		<h3>
			<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left">Recover Check</a>
		</h3>
		<h3>
			<a href="RecoverPageController" class="tab-bar-button hover-effect margin-left">Recover Note</a>
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
		<p>total recover - ${totalRecover} ks</p>
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
					<th style="width: 80px"><h4>P x 80</h4></th>
				</tr>
				<c:forEach items="${twoDList}" var="result">
					<tr class="mv-list-table-data" >
						<td><h3>${result.userName}</h3></td>
						<td><h3>${result.userMoney} ks</h3></td>
						<td><h3 style="color: firebrick">${result.money}</h3></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>