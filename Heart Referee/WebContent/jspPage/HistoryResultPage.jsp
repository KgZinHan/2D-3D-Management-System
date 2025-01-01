 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
             		<a href="HResult?username=12345">
	            		<h5 class="user-choose" style="text-decoration: underline;" >All Commissions</h5>
	            		</a>
	            	<c:forEach items="${userList}" var="user">
	            		<a href="HResult?username=${user.username}">
		            		<h5 class="user-choose" >
		             			${user.username}
		             		</h5>
	             		</a>
	             	</c:forEach>
             	</div>
             	
	            
	            
			</div>
        </div>
		<div class="mv-basic-bg">
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
				class="tab-bar-button hover-effect margin-left" ><i
				class="fas fa-pen-square"></i> R Note</a>
		</h3>
		<h3>
			<a href="FullTableController"
				class="tab-bar-button hover-effect margin-left" ><i
				class="fas fa-table" ></i> Full</a>
		</h3>
		<h3>
			<a href="Final" class="tab-bar-button hover-effect margin-left" ><i
				class="fas fa-edit" ></i> Report</a>
		</h3>
		<h3>
			<a href="HResult" class="tab-bar-button hover-effect margin-left" style="color: ${ledger}"
				style="color:${ledger}"><i class="fas fa-book"></i> Ledger</a>
		</h3>
		</div>
			<div class="user-total-field" >
				<span class="user-name hover-effect" onclick="displayBlock('modal01')" style="float: left"><b>${userName}</b></span>
				<a href="RecoverResult">
	            	<button class="f-pg-enter-no hover-effect" style="width: 140px;background-color: green"><i class="fas fa-sync"></i>&emsp;Recover</button>
	            </a>
			</div>	
			<div class="mv-display-middle" style="margin-top: 10px">
				<div id="tb02" class="mv-table-style" style="height:auto;width: 100%">
					<table class="mv-waiting-table" style="width: 1200px;border: 2px solid black">
						<tr class="mv-list-table-head" style="font-size: 16px">
							<th style="width: 50px">
								<h4>#</h4>
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
								<td  style="text-align: center"><p>${user2D.time}</td>
								<td style="text-align: center;display:${numberLink}"><p>${user2D.number}</p></td>
								<td><p><fmt:formatNumber
										value="${user2D.totalMoney}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${comPercentLink}"><p>${user2D.comPercent} %&nbsp;&nbsp;</p></td>
								<td><p><fmt:formatNumber
										value="${user2D.comMoney}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td><b><fmt:formatNumber
										value="${user2D.p}" type="number" />&nbsp;&nbsp;</b></td>
								<td><p><fmt:formatNumber
										value="${user2D.pMoney}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${user2D.recover}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${user2D.recoverP}" type="number" />&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${user2D.recoverPlus}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${user2D.extra}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="color:${user2D.color}"><p><fmt:formatNumber
										value="${user2D.total}" type="number" /> ks&nbsp;&nbsp;</p></td>
							</tr>
						</c:forEach>
						<c:forEach items="${totalUser2DList}" var="tUser2D">
							<tr class="mv-waiting-table-data" style="font-size: 16px;background-color: silver;text-align: right">
								<td style="text-align: center"><p>-</p></td>
								<td style="text-align: center"><p>Total</p></td>
								<td style="text-align: center"><p>-</p></td>
								<td><p><fmt:formatNumber
										value="${tUser2D.totalMoney}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${comPercentLink};text-align: center"><p>-</p></td>
								<td><p><fmt:formatNumber
										value="${tUser2D.comMoney}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td><b><fmt:formatNumber
										value="${tUser2D.p}" type="number" />&nbsp;&nbsp;</b></td>
								<td><p><fmt:formatNumber
										value="${tUser2D.pMoney}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${tUser2D.recover}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${tUser2D.recoverP}" type="number" />&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${tUser2D.recoverPlus}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="display:${totalRecoverLink}"><p><fmt:formatNumber
										value="${tUser2D.extra}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td style="color:${tUser2D.color}"><p><fmt:formatNumber
										value="${tUser2D.total}" type="number" /> ks&nbsp;&nbsp;</p></td>							
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 4.0.0 &nbsp; Design by N0iSy</footer>
	</body>
</html>