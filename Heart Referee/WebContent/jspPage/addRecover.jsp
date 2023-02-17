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
		<div class="mv-tab-bar">
			<h3>
				<a href="Table" class="recover-button hover-effect margin-left">Home</a>
			</h3>
			<h3>
				<a href="WaitingTable?m=default" class="waiting-button hover-effect margin-left">Waiting Table</a>
			</h3>
			<h3>
				<a href="Recover?limit=1500" class="ftbl-button hover-effect margin-left" style="color:${recoverCheck}">Recover Check</a>
			</h3>
			<h3>
				<a href="RecoverPageController" class="recover-button hover-effect margin-left">Recover Note</a>
			</h3>
			<h3>
				<a href="FullTableController" class="ftbl-button hover-effect margin-left">Full Table</a>
			</h3>
		</div>
		<div class="mv-recover-bg" style="background: rgb(50,50,50)">
			<div class="mv-add-recover-field">
				<form id="customInput" action="addRecover" method="post">
					<div class="multiple-input-tab">
						<input id="CNumber1" type="number" readonly="readonly" name="number1" value="${number}" class="multiple-input" placeholder="Enter" min="00" max="99" autofocus="autofocus" required>
					</div>
					<input type="hidden" name="limit" value="${limit}">
					<input type="number" name="money" style="right: 200px" value="${money}" class="mv-money-drop-button" placeholder="Enter" min="0" max="50000" step="50" required>
	                <h2 style="color: white; position: absolute;right: 80px">R</h2>
	                <input type="number" name="rMoney" value="${rMoney}" class="mv-money-drop-button" placeholder="Enter" min="0" max="50000" step="50" required>
	                <input id="submitbtn" class="mv-submit-btn hover-effect" type="submit" value="Add" >
				</form>
			 <button id="singlebtn" class="special-button hover-effect" onclick="goBack()">Back</button>
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.5.0 &nbsp; Design by N0iSyLuvie</footer>
	</body>
</html>