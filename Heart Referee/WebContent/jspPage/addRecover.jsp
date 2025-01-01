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

	
		<div id="userfield" class="user-total-field">
			<h2 style="font-family: robom; color: white">Add Quick Recover</h2>
		</div>
		<div class="mv-add-recover-field">

			<form id="customInput" action="addRecover" method="post">
				
				<div class="multiple-input-tab">
					<input id="CNumber1" type="number" readonly="readonly"
						name="number1" value="${number}" class="multiple-input"
						placeholder="Enter" min="00" max="99" autofocus="autofocus"
						required>
				</div>
				<input type="hidden" name="limit" value="${limit}"> <input
					type="number" name="money" style="right: 200px" value="${money}"
					class="mv-money-drop-button" placeholder="Enter" min="0"
					max="300000" step="50" required>
				<h2 style="color: white; position: absolute; right: 80px">R</h2>
				<input type="number" name="rMoney" value="${rMoney}"
					class="mv-money-drop-button" placeholder="Enter" min="0"
					max="300000" step="50" required> <select name="sellerName"
					class="mv-money-drop-button" style="top: 200px;width: 200px">
					<c:forEach items="${recoverSellerList}" var="seller">
						<option value="${seller.sellerName}">${seller.sellerName}</option>
					</c:forEach>
				</select> <input id="submitbtn" class="mv-submit-btn hover-effect"
					type="submit" value="Add">
			</form>
			<button id="singlebtn" class="special-button hover-effect"
				onclick="goBack()">Back</button>
		</div>
	</div>
	<footer style="background-color: black; color: white">&copy;
		Heart Referee Version 4.0.0 &nbsp; Design by N0iSy</footer>
</body>
</html>