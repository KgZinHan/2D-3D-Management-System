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
	<body tabindex="-1" style="overflow-x: hidden">
		<div id="modal01" class="mv-display-center mv-view-image mv-animate-zoom"> 
            <button class="mv-display-topRight mv-logIn-close-button hover-effect" onclick="displayNone('modal01')"><i class="far fa-times-circle"></i></button>
             <div id="radioTab" class="mv-table-input">
             	<div style="text-align: center;margin-top: 50px">
             		<p class="mv-username">Change Recover Seller</p>
             		<a href="RecoverResult?sellerName=12345">
	            		<h5 class="user-choose" style="text-decoration: underline;">All Recover Sellers</h5>
	            	</a>
	            	<c:forEach items="${recoverSellerList}" var="seller">
	            		<a href="RecoverResult?sellerName=${seller.sellerName}">
		            		<h5 class="user-choose" >
		             			${seller.sellerName}
		             		</h5>
	             		</a>
	             	</c:forEach>
             	</div>
             	
	            
			</div>
        </div>
		<div class="mv-basic-bg">
			 <div class="mv-tab-bar">
				<h3>
					<a href="Table" class="tab-bar-button hover-effect margin-left"><i class="fas fa-home"></i> Home</a>
				</h3>
				<h3>
					<a href="WaitingTable?m=money" class="tab-bar-button hover-effect margin-left"><i class="fas fa-list-ol"></i> Waiting</a>
				</h3>
				<h3>
					<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left"><i class="fas fa-file-medical"></i> R Check</a>
				</h3>
				<h3>
					<a href="RecoverPageController?sellerName=Default" class="tab-bar-button hover-effect margin-left" ><i class="fas fa-pen-square"></i> R Note</a>
				</h3>
				<h3>
					<a href="FullTableController" class="tab-bar-button hover-effect margin-left"><i class="fas fa-table"></i> Full</a>
				</h3>
				<h3>
					<a href="Final" class="tab-bar-button hover-effect margin-left"><i class="fas fa-edit"></i> Report</a>
				</h3>
				<h3>
					<a href="HResult" class="tab-bar-button hover-effect margin-left" style="color:${ledger}"><i class="fas fa-book"></i> Ledger</a>
				</h3>
			</div>	
			<div class="user-total-field">
				<span class="user-name hover-effect" onclick="displayBlock('modal01')">${sellerName}</span>
				<a href="HResult">
	            	<button class="f-pg-enter-no hover-effect" style="width: 140px;"><i class="fas fa-sync"></i>&emsp;Commission</button>
	            </a>
			</div>	
			<div class="mv-display-middle" style="margin-top: 10px">
				<div id="tb02" class="mv-table-style" style="max-height: 550px;width: 100%">
					<table class="mv-waiting-table" style="width: 1200px">
						<tr class="mv-list-table-head" style="font-size: 16px;background-color: green">
							<th style="width: 50px">
								<h4>#</h4>
							</th>
							<th style="width: 200px">				
								<h4>Date</h4>
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
						<c:forEach items="${user2DList}" var="user2D">
							<tr class="mv-waiting-table-data" style="font-size: 16px;text-align: right">
								<td style="text-align: center"><p>${user2D.count}</p></td>
								<td style="text-align: center"><p>${user2D.time}</p></td>
								<td><p><fmt:formatNumber
										value="${user2D.recover}" type="number" />  ks&nbsp;&nbsp;</p></td>
								<td><p><fmt:formatNumber
										value="${user2D.recoverCom}" type="number" />  ks&nbsp;&nbsp;</p></td>
								<td><p><fmt:formatNumber
										value="${user2D.recoverP}" type="number" />&nbsp;&nbsp;</p></td>
								<td><p><fmt:formatNumber
										value="${user2D.recoverPlus}" type="number" />  ks&nbsp;&nbsp;</p></td>
								<td><p style="color:${user2D.color}"><fmt:formatNumber
										value="${user2D.totalMoney}" type="number" />  ks&nbsp;&nbsp;</p></td>
							</tr>
						</c:forEach>
						<c:forEach items="${totalUser2DList}" var="tUser2D">
							<tr class="mv-waiting-table-data" style="font-size: 16px;background-color: silver;text-align: right">
								<td style="text-align: center"><p>-</p></td>
								<td style="text-align: center"><p>Total</p></td>
								<td><p><fmt:formatNumber
										value="${tUser2D.recover}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td><p><fmt:formatNumber
										value="${tUser2D.recoverCom}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td><p><fmt:formatNumber
										value="${tUser2D.recoverP}" type="number" />&nbsp;&nbsp;</p></td>
								<td><p><fmt:formatNumber
										value="${tUser2D.recoverPlus}" type="number" /> ks&nbsp;&nbsp;</p></td>
								<td><p style="color:${tUser2D.color}"><fmt:formatNumber
										value="${tUser2D.totalMoney}" type="number" /> ks&nbsp;&nbsp;</p></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<footer style="background-color: black;color:white">&copy; Heart Referee Version 4.0.0 &nbsp; Design by N0iSy</footer>
	</body>
</html>