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
	<div id="totalId" class="mv-display-center mv-view-details mv-animate-zoom" style="display: none">
		<p style="color: white;font-family:sans-serif;">Total Money = (Money - PMoney - ComMoney) + (Recover + RecoverPlus) + Extra</p>
	</div>
	<div id="recoverPlusId" class="mv-display-center mv-view-details mv-animate-zoom" style="display: none">
		<p style="color: white;font-family:sans-serif;">RecoverPlus = (RecoverP * (75 to 95)) + RecoverCom</p>
	</div>
	<body tabindex="-1" style="overflow-x: hidden">
		<div id="msgbox01" class="mv-menu-msgBox mv-animate-fade-out" style="opacity: 0" onclick="displayNone('msgbox01')">${message}</div>
		<div id="modal01" class="mv-display-center mv-view-image mv-animate-zoom"> 
            <button class="mv-display-topRight mv-logIn-close-button hover-effect" onclick="displayNone('modal01')"><i class="far fa-times-circle"></i></button>
             <div id="radioTab" class="mv-table-input">
             	<div style="text-align: center;margin-top: 50px">
             		<p class="mv-username">Change Commission</p>
	            	<c:forEach items="${userList}" var="user">
	            		<a href="HResult?username=${user.username}">
		            		<h5 class="user-choose" >
		             			${user.username}
		             		</h5>
	             		</a>
	             	</c:forEach>
             	</div>
             	<a href="HResult?username=12345">
	            	<button class="log-out-btn hover-effect" style="width: 100px">Total</button>
	            </a>
	            <a href="RecoverResult">
	            	<button class="log-out-btn hover-effect" style="width: 120px;margin-left: 120px;background-color: green">Recover Ledger</button>
	            </a>
	            
			</div>
        </div>
		<div class="mv-basic-bg">
			 <div class="mv-tab-bar">
				<h3>
					<a href="Table" class="tab-bar-button hover-effect margin-left">Home</a>
				</h3>
				<h3>
					<a href="WaitingTable?m=money" class="tab-bar-button hover-effect margin-left">Waiting Table</a>
				</h3>
				<h3>
					<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left">Recover Check</a>
				</h3>
				<h3>
					<a href="RecoverPageController?sellerName=Default" class="tab-bar-button hover-effect margin-left" >Recover Note</a>
				</h3>
				<h3>
					<a href="FullTableController" class="tab-bar-button hover-effect margin-left">Full Table</a>
				</h3>
				<h3>
					<a href="Final" class="tab-bar-button hover-effect margin-left">Report</a>
				</h3>
				<h3>
					<a href="HResult" class="tab-bar-button hover-effect margin-left" style="color:${ledger}">Ledger</a>
				</h3>
			</div>	
			<div class="user-total-field">
				<span class="user-name hover-effect" onclick="displayBlock('modal01')" style="margin-right: 5px"><b>${userName}</b> ledger</span>
			</div>	
			<div class="mv-display-middle" style="margin-top: 10px">
				<div id="tb02" class="mv-table-style" style="height:auto;width: 100%">
					<table class="mv-waiting-table" style="width: 1200px;border: 2px solid black">
						<tr class="mv-list-table-head" style="font-size: 16px">
							<th style="width: 50px">
								<h4>#</h4>
							</th>
							</th>
							<th style="width: 300px">				
								<h4>Date</h4>
							</th>
							<th style="width: 100px;display:${numberLink}">				
								<h4>Number</h4>
							</th>
							<th>
								<h4>Money</h4>
							</th>
							<th style="width: 80px;display:${comPercentLink}">
								<h4>Com</h4>
							</th>
							<th>				
								<h4>Com Money</h4>
							</th>
							<th style="width: 100px">
								<h4>P</h4>
							</th>
							<th>
								<h4>P Money</h4>
							</th>
							<th style="display:${totalRecoverLink};background-color: green">				
								<h4>Recover</h4>
							</th>
							<th style="width: 100px;display:${totalRecoverLink};background-color: green">				
								<h4>Recover P</h4>
							</th>
							<th style="display:${totalRecoverLink};background-color: green" onmouseout="closeDetails('recoverPlusId')" onmouseover="showDetails('recoverPlusId')">				
								<h4>Recover Plus</h4>
							</th>
							<th style="display:${totalRecoverLink}">				
								<h4>Extra</h4>
							</th>
							<th onmouseout="closeDetails('totalId')" onmouseover="showDetails('totalId')">				
								<h4>Total</h4>
							</th>
						</tr>
						<c:forEach items="${user2DList}" var="user2D">
							<tr class="mv-waiting-table-data" style="font-size: 16px;text-align: right">
								<td style="text-align: center"><p>${user2D.count}</p></td>
								<td  style="text-align: center"><p>${user2D.time}</p></td>
								<td style="text-align: center;display:${numberLink}"><p>${user2D.number}</p></td>
								<td><p>${user2D.totalMoney} ks&nbsp;&nbsp;</p></td>
								<td style="display:${comPercentLink}"><p>${user2D.comPercent} %&nbsp;&nbsp;</p></td>
								<td><p>${user2D.comMoney} ks&nbsp;&nbsp;</p></td>
								<td><b>${user2D.p}&nbsp;&nbsp;</b></td>
								<td><p>${user2D.pMoney} ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${user2D.recover} ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${user2D.recoverP}&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${user2D.recoverPlus} ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${user2D.extra} ks&nbsp;&nbsp;</p></td>
								<td style="color:${user2D.color}"><p>${user2D.total} ks&nbsp;&nbsp;</p></td>
							</tr>
						</c:forEach>
						<c:forEach items="${totalUser2DList}" var="tUser2D">
							<tr class="mv-waiting-table-data" style="font-size: 16px;background-color: silver;text-align: right">
								<td style="text-align: center"><p>Total</p></td>
								<td style="text-align: center"><p>-</p></td>
								<td style="text-align: center"><p>-</p></td>
								<td><p>${tUser2D.totalMoney} ks&nbsp;&nbsp;</p></td>
								<td style="display:${comPercentLink};text-align: center"><p>-</p></td>
								<td><p>${tUser2D.comMoney} ks&nbsp;&nbsp;</p></td>
								<td><b>${tUser2D.p}&nbsp;&nbsp;	</b></td>
								<td><p>${tUser2D.pMoney} ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${tUser2D.recover} ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${tUser2D.recoverP}&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${tUser2D.recoverPlus} ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p>${tUser2D.extra} ks&nbsp;&nbsp;</p></td>
								<td style="color:${tUser2D.color}"><p>${tUser2D.total} ks&nbsp;&nbsp;</p></td>							
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.9.0 &nbsp; Design by N0iSy2099</footer>
	</body>
</html>