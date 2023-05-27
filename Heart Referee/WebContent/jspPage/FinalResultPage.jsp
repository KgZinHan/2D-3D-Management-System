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
		<div id="msgbox01" class="mv-menu-msgBox mv-animate-fade-out" style="opacity: 0" onclick="displayNone('msgbox01')">${message}</div>
		<div class="mv-tab-bar">
			<h3>
				<a href="Table" class="tab-bar-button hover-effect margin-left">Home</a>
			</h3>
			<h3>
				<a href="WaitingTable?m=default" class="tab-bar-button hover-effect margin-left">Waiting Table</a>
			</h3>
			<h3>
				<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left" style="color:${recoverCheck}">Recover Check</a>
			</h3>
			<h3>
				<a href="RecoverPageController" class="tab-bar-button hover-effect margin-left">Recover Note</a>
			</h3>
			<h3>
				<a href="FullTableController" class="tab-bar-button hover-effect margin-left">Full Table</a>
			</h3>
			<h3>
				<a href="Final" class="tab-bar-button hover-effect margin-left" style="color: ${finalResult}">Report</a>
			</h3>
			<h3>
				<a href="HResult" class="tab-bar-button hover-effect margin-left">Ledger</a>
			</h3>
		</div>
		
		<div class="mv-recover-bg" style="background: rgb(50,50,50)">
			
			<div class="mv-add-recover-field">
				
				<div class="f-pg-caption">
					<h4><b class="user-name" style="color: silver;cursor:default;margin-right: 10px">${userName}</b>Final Report</h4>
				</div>
				<form action="FinalResultController" method="post" style="display: flex;margin-top: 30px">
					&nbsp;<i class="fas fa-edit">&nbsp;</i><input type="number" name="number" class="f-pg-enter-no" min="0" max="99" placeholder="Enter P Number" required>
					<input type="number" name="comPercent" class="f-pg-enter-no" min="0" max="99" placeholder="Enter Commission %" required>
					<input type="submit" class="f-pg-enter-no hover-effect" value="Calculate" style="cursor: pointer;background-color: silver">
				</form>
				<div style="display: ${divDisplay}">
					<div style="margin: 15px">
						<div style="display: flex">
							<p class="f-pg-text-style">P Number</p>
							<p class="f-pg-text-style" style="width: 20px">=</p>
							<p class="f-pg-text-style"><b>${number}</b></p>
						</div>
						<div style="display: flex">
							<p class="f-pg-text-style">Total Money</p>
							<p class="f-pg-text-style" style="width: 20px">=</p>
							<p class="f-pg-text-style">${totalMoney} ks</p>
						</div>
						<div style="display: flex">
							<p class="f-pg-text-style">P (<b>${p}</b>)</p>
							<p class="f-pg-text-style" style="width: 20px">=</p>
							<p class="f-pg-text-style">${pMoney} ks</p>
						</div>
						<div style="display: flex">
							<p class="f-pg-text-style">Commission (${comPercent}%)</p>
							<p class="f-pg-text-style" style="width: 20px">=</p>
							<p class="f-pg-text-style">${commission} ks</p>
						</div>
						<div style="display: flex">
							<p class="f-pg-text-style" style="background-color: silver">Total</p>
							<p class="f-pg-text-style" style="width: 20px;background-color: silver">=</p>
							<p class="f-pg-text-style" style="background-color: silver;color:${totalColor}">${total} ks</p>
						</div>
					</div>
					<form action="HResult" method="post">
						<input type="hidden" name="username" value="${userName}" >
						<input type="hidden" name="number" value="${number}" >
						<input type="hidden" name="totalMoney" value="${totalMoney}" >
						<input type="hidden" name="p" value="${p}" >
						<input type="hidden" name="pMoney" value="${pMoney}" >
						<input type="hidden" name="comPercent" value="${comPercent}" >
						<input type="hidden" name="comMoney" value="${commission}" >
						<input type="hidden" name="total" value="${total}" >
						<input type="submit" class="f-pg-enter-no hover-effect" value="Save to Database" style="cursor: pointer;background-color: silver">
					</form>
				</div>
				
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.7.0 &nbsp; Design by N0iSyLuvie</footer>
	</body>
</html>