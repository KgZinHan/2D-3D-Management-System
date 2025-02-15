<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<body  tabindex="-1" style="overflow-x: hidden">
<div class="mv-basic-bg">
	<!-- For Msg box -->
    <div id="msgbox01" class="mv-menu-msgBox mv-animate-noti-drop" onclick="displayNone('msgbox01')">${message}</div>
	<div class="mv-tab-bar">
		<h3>
			<a href="Table" class="tab-bar-button hover-effect margin-left"><i class="fas fa-home"></i> Home</a>
		</h3>
		<h3>
			<a href="WaitingTable?m=money" class="tab-bar-button hover-effect margin-left"><i class="fas fa-list-ol"></i> Waiting</a>
		</h3>
		<h3>
			<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left" style="color:${recoverCheck}"><i class="fas fa-file-medical"></i> R Check</a>
		</h3>
		<h3>
			<a href="RecoverPageController?sellerName=Default" class="tab-bar-button hover-effect margin-left"><i class="fas fa-pen-square"></i> R Note</a>
		</h3>
		<h3>
			<a href="FullTableController" class="tab-bar-button hover-effect margin-left"><i class="fas fa-table"></i> Full</a>
		</h3>
		<h3>
			<a href="Final" class="tab-bar-button hover-effect margin-left"><i class="fas fa-edit"></i> Report</a>
		</h3>
		<h3>
			<a href="HResult" class="tab-bar-button hover-effect margin-left"><i class="fas fa-book"></i> Ledger</a>
		</h3>
	</div>
	<div id="userfield" class="user-total-field">
		<p style="margin-left: 50px">total money&emsp;-&emsp;<fmt:formatNumber
										value="${totalMoney}" type="number" /> ks</p>
		<p style="margin-left: 50px">total recover&emsp;-&emsp;<fmt:formatNumber
										value="${userTotalMoney}" type="number" /> ks</p>
		<p style="margin-left: 50px">approximate recover&emsp;-&emsp;<fmt:formatNumber
										value="${totalRecover}" type="number" /> ks</p>
	</div>
	<div class="mv-display-middle">
		<div class="mv-waiting-panel" style="width: 1000px;justify-content: flex-start;">
			<form action="Recover" method="get">
				&nbsp;<i class="fas fa-hand-holding-medical">&nbsp;</i><input type="number" name="limit" class="mv-list-search-box" min="1000" step="500" style="width: 300px" placeholder="Change Recover Limit..." autofocus	 required>
			</form>
			<h3 class="recover-limit-caption">recover limit &nbsp;-&nbsp; ${limit} x80 ks</h3>
			<a href="Recover?limit=${limit}">
				<button class="refresh-button hover-effect">
					<i class="fas fa-sync"></i>
				</button>
			</a>
		</div>
		<div id="tb02" class="mv-table-style" style="max-height: 550px">
			<table class="mv-waiting-table">
				<tr class="mv-list-table-head">
					<th>
						<h4>No.</h4>
					</th>
					<th>
						<h4>Money</h4>
					</th>
					<th style="width: 50px">
						<h4>R</h4>
					</th>
					<th>
						<h4>R Money</h4>
					</th>
					<th style="width: 80px">				
						<h4>Check</h4>
					</th>
					<th style="width: 80px">				
						<h4>Add</h4>
					</th>
				</tr>
				<c:forEach items="${twoDList}" var="twoD">
					<tr id="${twoD.number}" class="mv-waiting-table-data">
						<td><h3>${twoD.number}</h3></td>
						<td><h3><fmt:formatNumber
										value="${twoD.money}" type="number" /></h3></td>
						<td><h3>R</h3></td>
						<td><h3><fmt:formatNumber
										value="${twoD.rNumber}" type="number" /></h3></td>
						<td style="cursor: pointer" onclick="clickfunction(${twoD.number})"><i class="fa fa-check"></i></td>
						<td style="cursor: pointer"><a href="addRecover?number=${twoD.number}&money=${twoD.money}&rMoney=${twoD.rNumber}&limit=${limit}">
							<i class="fa fa-plus hover-effect"></i></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</div>
	<footer style="background-color: black;color:white">&copy; Heart Referee Version 4.0.0 &nbsp; Design by N0iSy</footer>
</body>
</html>