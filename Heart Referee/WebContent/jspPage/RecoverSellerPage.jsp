<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<div class="mv-recover-bg" >
	<div id="msgbox01" class="mv-menu-msgBox mv-animate-fade-out"
		style="opacity: 0" onclick="displayNone('msgbox01')">${message}</div>
	<div class="mv-tab-bar">
		<h3>
			<a href="Table" class="tab-bar-button hover-effect margin-left"><i
				class="fas fa-home"></i> Home</a>
		</h3>
		<h3>
			<a href="WaitingTable?m=money"
				class="tab-bar-button hover-effect margin-left"><i
				class="fas fa-list-ol"></i> Waiting</a>
		</h3>
		<h3>
			<a href="Recover?limit=1500"
				class="tab-bar-button hover-effect margin-left"><i
				class="fas fa-file-medical"></i> R Check</a>
		</h3>
		<h3>
			<a href="RecoverPageController?sellerName=Default"
				class="tab-bar-button hover-effect margin-left"><i
				class="fas fa-pen-square"></i> R Note</a>
		</h3>
		<h3>
			<a href="FullTableController"
				class="tab-bar-button hover-effect margin-left"><i
				class="fas fa-table"></i> Full</a>
		</h3>
		<h3>
			<a href="Final" class="tab-bar-button hover-effect margin-left"><i
				class="fas fa-edit"></i> Report</a>
		</h3>
		<h3>
			<a href="HResult" class="tab-bar-button hover-effect margin-left"
				style="color:${ledger}"><i class="fas fa-book"></i> Ledger</a>
		</h3>
	</div>
	<div class="user-total-field">Recover Seller Setup</div>
	
		<div class="mv-add-recover-field">
			<div class="mv-display-middle">
				<form action="RecoverSeller" method="get"
					style="display: flex; margin: 30px">
					<input type="hidden" name="mode" value="update" /> <input
						type="text" name="sellerName" class="f-pg-enter-no" min="0"
						max="99" placeholder="Recover Seller Name" style="width: 150px"
						required> <input type="number" name="sellerCom"
						class="f-pg-enter-no" min="0" max="99" placeholder="Comm"
						style="width: 100px" required> <input type="number"
						name="sellerZ" class="f-pg-enter-no" min="0" max="99"
						placeholder="Z" style="width: 100px" required> <input
						type="submit" class="f-pg-enter-no hover-effect" value="Add"
						style="cursor: pointer; background-color: silver;">
				</form>
				<div class="mv-table-style" style="height: 350px">
					<table>
						<tr class="mv-list-table-head">
							<th style="width: 300px">
								<h4>Seller Name</h4>
							</th>
							<th>
								<h4>Seller Com</h4>
							</th>
							<th style="width: 150px">
								<h4>Seller Z</h4>
							</th>
							<th style="width: 50px"></th>
						</tr>
						<c:forEach items="${recoverSellerList}" var="seller">
							<tr class="mv-waiting-table-data"
								style="line-height: 0px; font-size: 18px">
								<td style="width: 200px">${seller.sellerName}</td>
								<td style="width: 150px"><p>${seller.sellerCom}%</p></td>
								<td style="width: 150px"><p>${seller.sellerZ}z</p></td>
								<td><a
									href="RecoverSeller?sellerName=${seller.sellerName}&mode=delete"
									onclick="if(!(confirm('Are you sure you want to delete ${seller.sellerName}?'))) return false;">
										<i class="fas fa-trash"></i>
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<a href="RecoverPageController?sellerName=Default">
				<button id="singlebtn" class="special-button hover-effect">Back</button>
			</a>
		</div>



	</div>
	<footer style="background-color: black; color: white">&copy;
		Heart Referee Version 4.0.0 &nbsp; Design by N0iSy</footer>
</body>
</html>