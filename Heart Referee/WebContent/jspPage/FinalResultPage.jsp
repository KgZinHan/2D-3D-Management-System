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
		<div id="modal01" class="mv-display-center mv-view-image mv-animate-zoom"> 
            <button class="mv-display-topRight mv-logIn-close-button hover-effect" onclick="displayNone('modal01')"><i class="far fa-times-circle"></i></button>
             <div id="radioTab" class="mv-table-input">
             	<div style="text-align: center;margin-top: 50px">
             		<h5 style="text-align: center"><spam style="color: red">red = not save</spam>&emsp;|&emsp;<spam style="color: green"> green = save</spam></h5>  
             		<p class="mv-username">Change Name</p>
	            	<c:forEach items="${userList}" var="user">
	            		<a href="Final?username=${user.user}">
		            		<h5 class="user-choose" >
		             			<span style="color:${user.checked}">${user.user}</span>
		             		</h5>
	             		</a>
	             	</c:forEach>
             	</div>
			</div>
        </div>
        <div id="modal02" class="mv-display-center mv-view-table">
        	<button class="mv-display-topRight mv-logIn-close-button hover-effect" style="color: black" onclick="displayNone('modal02')"><i class="far fa-times-circle"></i></button>
				<div class="mv-display-middle" style="height:100px;margin-top: 30px">
					<span class="user-name" style="cursor: default;color: black">Number = ${number}</span>
				</div>
				<div id="tb02" class="mv-report-table-style">
					<table class="mv-waiting-table" style="margin-right: 50px">
						<tr class="mv-list-table-head">
							<th>
								<h4>#</h4>
							</th>
							<th>				
								<h4>Name</h4>
							</th>
							<th>
								<h4>Money</h4>
							</th>
							<th>
								<h4>P</h4>
							</th>
							<th>
								<h4>P Money</h4>
							</th>
							<th>
								<h4>Com</h4>
							</th>
							<th>				
								<h4>Com Money</h4>
							</th>
							<th>				
								<h4>Total</h4>
							</th>
						</tr>
						<c:forEach items="${temp2DList}" var="temp2D">
							<tr class="mv-waiting-table-data" style="line-height: 0px;font-size: 18px">
								<td style="width: 50px"><p>${temp2D.count}</p></td>
								<td style="width: 100px"><p>${temp2D.username}</p></td>
								<td style="width: 200px"><p>${temp2D.totalMoney} ks</p></td>
								<td style="width: 80px"><p>${temp2D.p}</p></td>
								<td style="width:200px"><p>${temp2D.pMoney} ks</p></td>
								<td style="width: 100px"><p>${temp2D.comPercent} %</p></td>
								<td style="width:200px"><p>${temp2D.comMoney} ks</p></td>
								<td style="width: 300px;color:${temp2D.color}"><p>${temp2D.total} ks</p></td>
							</tr>
						</c:forEach>
						<c:forEach items="${totalTemp2DList}" var="tTemp2D">
							<tr class="mv-waiting-table-data" style="line-height: 0px;font-size: 18px;background-color: silver">
								<td style="width: 50px"><p>-</p></td>
								<td style="width: 200px"><p>Total</p></td>
								<td style="width: 200px"><p>${tTemp2D.totalMoney} ks</p></td>
								<td style="width: 80px"><p>${tTemp2D.p}</p></td>
								<td style="width:200px"><p>${tTemp2D.pMoney} ks</p></td>
								<td style="width: 100px"><p>-</p></td>
								<td style="width:200px"><p>${tTemp2D.comMoney} ks</p></td>
								<td style="width: 200px;color:${tTemp2D.color}"><p>${tTemp2D.total} ks</p></td>							
							</tr>
						</c:forEach>
					</table>
				</div>
				<div style="margin-left: 50px;margin-top: 10px">
					<form action="Delete" method="get" >				
						<input type="hidden" name="id" value="99999"/>
						<label style="font-family: robom">Day :</label>&emsp;<input type="number" name="day" class="f-pg-enter-no-2" min="1" max="31" step="1" required/>
						<label style="font-family: robom">Month :</label>&emsp;<input type="number" name="month" class="f-pg-enter-no-2" min="1" max="12" step="1" required/>
						<label style="font-family: robom">Year :</label>&emsp;<select name="year" class="f-pg-enter-no-2">
									<option value="2023" label="2023"/>
									<option value="2024" label="2024"/>
									<option value="2025" label="2025"/>
									<option value="2026" label="2026"/>
								</select>
						<label style="font-family: robom">Time :</label>&emsp;<select name="time" class="f-pg-enter-no-2">
									<option value="A" label="morning"/>
									<option value="B" label="evening"/>
								</select>
						<button class="f-pg-enter-no hover-effect" style="width:auto;cursor: pointer;float: right" onclick="if(!(confirm('Are you sure you want to save this to ledger and delete this table?'))) return false;">
							<i class="fa fa-save" ></i>&emsp;Save to ledger
						</button>
					</form>
				</div>
			</div>
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
		
		<div class="mv-basic-bg">
			<div class="user-total-field">
				<span class="user-name hover-effect" onclick="displayBlock('modal01')" style="margin-right: 5px"><b>${userName}</b> report</span>
			</div>	
			<div class="mv-add-recover-field">
				<div style="margin: 15px;height: 40px">
					<button class="f-pg-enter-no hover-effect" style="width: auto;cursor: pointer;float: right;background-color: silver" onclick="displayBlock('modal02')">
					<i class="fa fa-table" ></i>&emsp;Show all</button>
				</div>
				<form action="FinalResultController" method="post" style="display: flex;margin-left: 15px">
					<input type="hidden" name="username" value="${userName}">
					<input type="number" name="number" class="f-pg-enter-no" value="${number}" min="0" max="99" placeholder="Enter P Number" required>
					<input type="number" name="comPercent" class="f-pg-enter-no" min="0" value="${comPercent}" max="99" placeholder="Enter Commission %" required>
					<input type="submit" class="f-pg-enter-no hover-effect" value="Calculate" style="cursor: pointer;background-color: silver">
				</form>
				<div style="display: ${divDisplay};">
					<div style="margin: 5px">
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
						<input type="submit" class="f-pg-enter-no hover-effect" value="Save to Database" style="cursor: pointer;background-color: #2196F3">
					</form>
				</div>
				
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.7.0 &nbsp; Design by N0iSyLuvie</footer>
	</body>
</html>