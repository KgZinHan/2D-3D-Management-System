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
             		<p class="mv-username">Change Commission</p>
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
				<div class="mv-display-middle" style="height:100px;margin-top: 30px;width: 90%">
					<button class="f-pg-enter-no hover-effect" style="width: 150px;cursor: pointer" onclick="toggleSwitch('tb02','tb03');toggleSwitch('c01','c02')"><i class="fas fa-sync"></i>&nbsp;Change Report</button>
					<span class="user-name" style="cursor: default;color: black"><b>Number = ${number}</b></span>
					<span id="c01" class="user-name" style="cursor: default;color: green"><b>Recover Total = ${finalTotalRecover} ks</b></span>
					<span class="user-name" style="cursor: default;color:${recoverTotalColor}">All Total = ${allTotal} ks </span>
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
								<h4>Com %</h4>
							</th>
							<th>				
								<h4>Com Money</h4>
							</th>
							<th>
								<h4>P</h4>
							</th>
							<th>
								<h4>P Money</h4>
							</th>
							<th>				
								<h4>Total</h4>
							</th>
						</tr>
						<c:forEach items="${temp2DList}" var="temp2D">
							<tr class="mv-waiting-table-data" style="line-height: 0px;font-size: 18px">
								<td style="width: 30px"><p>${temp2D.count}</p></td>
								<td style="width: 100px"><p>${temp2D.username}</p></td>
								<td style="width: 200px;text-align: right"><p>${temp2D.totalMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 100px;text-align: right"><p>${temp2D.comPercent} %&nbsp;&nbsp;</p></td>
								<td style="width:200px;text-align: right"><p>${temp2D.comMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 80px;text-align: right"><b>${temp2D.p}&nbsp;&nbsp;</b></td>
								<td style="width:220px;text-align: right"><p>${temp2D.pMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 300px;text-align: right;color:${temp2D.color}"><p>${temp2D.total} ks&nbsp;&nbsp;</p></td>
							</tr>
						</c:forEach>
						<c:forEach items="${totalTemp2DList}" var="tTemp2D">
							<tr class="mv-waiting-table-data" style="line-height: 0px;font-size: 18px;background-color: silver">
								<td style="width: 30px"><p>-</p></td>
								<td style="width: 200px"><p>Total</p></td>
								<td style="width: 200px;text-align: right"><p>${tTemp2D.totalMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 100px"><p>-</p></td>
								<td style="width:200px;text-align: right"><p>${tTemp2D.comMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 80px;text-align: right"><b>${tTemp2D.p}&nbsp;&nbsp;</b></td>
								<td style="width:220px;text-align: right"><p>${tTemp2D.pMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 300px;text-align: right;color:${tTemp2D.color}"><p>${tTemp2D.total} ks&nbsp;&nbsp;</p></td>							
							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="tb03" class="mv-report-table-style" style="display: none">
					<table class="mv-waiting-table" style="margin-right: 50px">
						<tr class="mv-list-table-head" style="background-color: green">
							<th>
								<h4>#</h4>
							</th>
							<th>				
								<h4>Seller Name</h4>
							</th>
							<th>
								<h4>Recover Money</h4>
							</th>
							<th>
								<h4>Recover Com</h4>
							</th>
							<th>
								<h4>Recover P</h4>
							</th>
							<th>
								<h4>Recover Plus</h4>
							</th>
							<th>				
								<h4>Recover Total</h4>
							</th>
						</tr>
						<c:forEach items="${tempRecover2DList}" var="tempRec2D">
							<tr class="mv-waiting-table-data" style="line-height: 0px;font-size: 18px;text-align: right">
								<td style="width: 50px;text-align: center"><p>${tempRec2D.count}</p></td>
								<td style="width: 200px;text-align: center"><p>${tempRec2D.sellerName}</p></td>
								<td style="width: 200px"><p>${tempRec2D.sellerMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 200px"><p>${tempRec2D.recoverCom} ks&nbsp;&nbsp;</p></td>
								<td style="width:100px"><b>${tempRec2D.recoverP}&nbsp;&nbsp;</b></td>
								<td style="width: 200px"><p>${tempRec2D.recoverPlus} ks&nbsp;&nbsp;</p></td>
								<td style="width: 300px;color:${tempRec2D.color}"><p>${tempRec2D.totalRecover} ks&nbsp;&nbsp;</p></td>
							</tr>
						</c:forEach>
						<c:forEach items="${totalTempRecover2DList}" var="tTempRec2D">
							<tr class="mv-waiting-table-data" style="line-height: 0px;font-size: 18px;background-color: silver;text-align: right">
								<td style="width: 50px;text-align: center"><p>-</p></td>
								<td style="width: 200px;text-align: center"><p>Total</p></td>
								<td style="width: 200px"><p>${tTempRec2D.sellerMoney} ks&nbsp;&nbsp;</p></td>
								<td style="width: 200px"><p>${tTempRec2D.recoverCom} ks&nbsp;&nbsp;</p></td>
								<td style="width:100px"><b>${tTempRec2D.recoverP}&nbsp;&nbsp;</b></td>
								<td style="width: 200px"><p>${tTempRec2D.recoverPlus} ks&nbsp;&nbsp;</p></td>
								<td style="width: 300px;color:${tTempRec2D.color}"><p>${tTempRec2D.totalRecover} ks&nbsp;&nbsp;</p></td>					
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<div style="margin-left: 20px;margin-top: 10px">
					<form action="Delete" method="get" >				
						<input type="hidden" name="id" value="99999"/>
						<input type="hidden" name="number" value="${number}">
						<label style="font-family: robom">Date :</label>&emsp;<input type="date" name="date" class="f-pg-enter-no-2" min="1" max="12" step="1" style="width: 120px" required/>
						
						<label style="font-family: robom">Time :</label>&emsp;<select name="time" class="f-pg-enter-no-2" style="width: 100px">
									<option value="AM" label="morning"/>
									<option value="PM" label="evening"/>
								</select>
												
						<input type="checkbox" name="recoverFlag" value="add" checked>
						<label style="font-family: robom">Add Recover</label>&emsp;
						<input type="number" class="f-pg-enter-no-2" value="${finalTotalRecover}" readonly style="width: 100px">
						<label style="font-family: robom;margin-left: 10px;">Extra</label>&emsp;
						<input id="inputRecoverId"  type="number" name="extraMoney" value="0" class="f-pg-enter-no-2" step="500" style="width: 80px" placeholder="Extra money" required>
						<button class="f-pg-enter-no hover-effect" style="width:auto;cursor: pointer;float: right" onclick="if(!(confirm('Are you sure you want to save this to ledger and delete tables?'))) return false;">
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
				<a href="WaitingTable?m=money" class="tab-bar-button hover-effect margin-left">Waiting Table</a>
			</h3>
			<h3>
				<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left" style="color:${recoverCheck}">Recover Check</a>
			</h3>
			<h3>
				<a href="RecoverPageController?sellerName=Default" class="tab-bar-button hover-effect margin-left">Recover Note</a>
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
					<input id="numberId" type="number" name="number" class="f-pg-enter-no" value="${number}" placeholder="Enter P Number" required>
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
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.8.1 &nbsp; Design by N0iSyLuvie</footer>
	</body>
</html>