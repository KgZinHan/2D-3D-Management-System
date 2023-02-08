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
<body class="mv-list-bg" tabindex="-1" style="overflow-x: hidden" onload="checkNumber('${number}')">
	<div class="mv-tab-bar">
		<a href="Table" class="recover-button hover-effect margin-left">
			<h3>Home</h3>
		</a>
		<a href="WaitingTable?m=default" class="waiting-button hover-effect margin-left" style="color:${waiting}">
			<h3>Waiting Table</h3>
		</a>
		<a href="Recover?limit=1500" class="ftbl-button hover-effect margin-left">
			<h3>Recover Check</h3>
		</a>
		<a href="RecoverPageController" class="recover-button hover-effect margin-left">
			<h3>Recover Note</h3>
		</a>
		<a href="FullTableController" class="ftbl-button hover-effect margin-left">
			<h3>Full Table</h3>
		</a>
	</div>
	<div id="userfield" class="user-total-field">
		<p>total money - ${totalMoney} ks</p>
		<h3 style="margin-right: 50px;margin-left: 50px;font-family: robom">${number} = P (${money})</h3>
		<p>total recover - ${totalRecover} ks</p>
	</div>	
	<div class="mv-margin-top mv-display-middle">
	<button class="back-button hover-effect" style="left: 10px" onclick="goBack()"><i class="fas fa-arrow-up"></i></button>
	<a href="SummaryTable?number=${number-1}&money=${money}">
	<button id="btn1" class="back-button hover-effect" style="float: left"><i class="fas fa-angle-left"></i></button></a>
	<a href="SummaryTable?number=${number+1}&money=${money}">
	<button id="btn2" class="back-button hover-effect" style="float: right"><i class="fas fa-angle-right"></i></button></a>
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