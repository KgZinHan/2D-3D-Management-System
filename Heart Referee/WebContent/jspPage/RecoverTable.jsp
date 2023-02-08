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
<body class="mv-list-bg">
	<!-- For Msg box -->
    <div id="msgbox01" class="mv-menu-msgBox mv-animate-noti-drop" onclick="displayNone('msgbox01')">${message}</div>
	<div class="mv-tab-bar">
		<a href="Table" class="recover-button hover-effect margin-left" style="color:">
			<h3>Home</h3>
		</a>
		<a href="WaitingTable?m=default" class="waiting-button hover-effect margin-left">
			<h3>Waiting Table</h3>
		</a>
		<a href="Recover?limit=1500" class="ftbl-button hover-effect margin-left" style="color:${recoverCheck}">
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
		<p style="margin-left: 50px">total money - ${totalMoney} ks</p>
		<p style="margin-left: 50px">total recover - ${userTotalMoney} ks</p>
		<p style="margin-left: 50px">approximate recover - ${totalRecover} ks</p>
	</div>
	<div class="mv-display-middle">
		<div class="mv-waiting-panel" style="width: 1000px;justify-content: flex-start;">
			<form action="Recover" method="get">
				&nbsp;<i class="fas fa-edit">&nbsp;</i><input type="number" name="limit" class="mv-list-search-box" min="1000" step="500" style="width: 300px" placeholder="Change Recover Limit..." required>
			</form>
			<h3 class="recover-limit-caption">recover limit &nbsp;-&nbsp; ${limit} x80 ks</h3>
			<a href="Recover?limit=${limit}">
				<button class="refresh-button hover-effect">
					<i class="fas fa-sync"></i>
				</button>
			</a>
		</div>
		<div id="tb02" class="mv-table-style" style="height: 600px">
			<table class="mv-waiting-table">
				<tr class="mv-list-table-head">
					<th>
						<h4>Number</h4>
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
					<form>
					<tr id="${twoD.number}" class="mv-waiting-table-data">
						<td><h3>${twoD.number}</h3></td>
						<td><h3>${twoD.money}</h3></td>
						<td><h3>R</h3></td>
						<td><h3>${twoD.rNumber}</h3></td>
						<td style="cursor: pointer" onclick="clickfunction(${twoD.number})"><i class="fa fa-check"></i></td>
						<td style="cursor: pointer"><a href="addRecover?number=${twoD.number}&money=${twoD.money}&rMoney=${twoD.rNumber}&limit=${limit}">
							<i class="fa fa-plus"></i></a></td>
					</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>