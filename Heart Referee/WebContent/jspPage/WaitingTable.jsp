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
<!-- For search box -->

<div id="redDetailsId" class="mv-display-center mv-view-details mv-animate-zoom" style="display: none">
	<p style="color: red;font-family:sans-serif;">Red color means the numbers with red color will be minus(-) above 5 lakhs in profit.</p>
</div>
<div id="blackDetailsId" class="mv-display-center mv-view-details mv-animate-zoom" style="display: none">
	<p style="color: white;font-family:sans-serif;">Black color means the numbers with black color will be minus(-) between 0 and 5 lakhs in profit.</p>
</div>
<div id="greenDetailsId" class="mv-display-center mv-view-details mv-animate-zoom" style="display: none">
	<p style="color: green;font-family:sans-serif;">Green color means the numbers with green color will be plus(+) in profit.</p>
</div>
<div id="purpleDetailsId" class="mv-display-center mv-view-details mv-animate-zoom" style="display: none">
	<p style="color: purple;font-family:sans-serif;">Purple color means the numbers with purple color are P(0) and will be significantly plus(+) in profit.</p>
</div>
<body tabindex="-1" style="overflow-x: hidden">
	
    <div id="msgbox01" class="mv-menu-msgBox mv-animate-noti-drop" onclick="displayNone('msgbox01')">${message}</div>
  	<div id="modal01" class="mv-full-view" ondblclick="displayNone('modal01')"> 
        <form action="Search" method="get">
			<input type="number" name="number" class="mv-special-list-search-box mv-display-center"  placeholder="Enter 2D Number..." autofocus required>
		</form>
    </div>
    <div class="mv-basic-bg">
    	<div class="mv-tab-bar">
			<h3>
				<a href="Table" class="tab-bar-button hover-effect margin-left">Home</a>
			</h3>
			<h3>
				<a href="WaitingTable?m=money" class="tab-bar-button hover-effect margin-left" style="color:${waiting}">Waiting Table</a>
			</h3>
			<h3>
				<a href="Recover?limit=1500" class="tab-bar-button hover-effect margin-left">Recover Check</a>
			</h3>
			<h3>
				<a href="RecoverPageController?sellerName=Default" class="tab-bar-button hover-effect margin-left">Recover Note</a>
			</h3>
			<h3>
				<a href="FullTableController" class="tab-bar-button hover-effect margin-left">Full Table</a>
			</h3>
			<h3>
				<a href="Final" class="tab-bar-button hover-effect margin-left">Report</a>
			</h3>
			<h3>
				<a href="HResult" class="tab-bar-button hover-effect margin-left">Ledger</a>
			</h3>
		</div>
		<div id="userfield" class="user-total-field">
			total money&emsp;-&emsp;${totalMoney} ks
			<table style="margin-left: 50px;margin-right: 50px">
				<tr class="mv-count-table">
					<th class="mv-count-table-head" style="color: red" onmouseout="closeDetails('redDetailsId')" onmouseover="showDetails('redDetailsId')" >${count.redCount}%</th>
					<th class="mv-count-table-head" style="color: black" onmouseout="closeDetails('blackDetailsId')" onmouseover="showDetails('blackDetailsId')">${count.blackCount}%</th>
					<th class="mv-count-table-head" style="color: green" onmouseout="closeDetails('greenDetailsId')" onmouseover="showDetails('greenDetailsId')">${count.greenCount}%</th>
					<th class="mv-count-table-head" style="color: purple" onmouseout="closeDetails('purpleDetailsId')" onmouseover="showDetails('purpleDetailsId')">${count.purpleCount}%</th>
				</tr>
			</table>
			total recover&emsp;-&emsp;${totalRecover} ks
			<h3 style="cursor: pointer;margin-left: 100px" onclick="displayNone('userfield')">
				X
			</h3>
		</div>
		<div class="mv-display-middle">
			<div class="mv-waiting-panel">
				<form action="WaitingTable" method="post">
					&nbsp;<i class="fas fa-filter">&nbsp;</i><input type="number" name="start" style="width: 100px" class="mv-list-search-box" placeholder="Filter Start" min="0" max="9" required>
				</form>	
				<h3 class="hide-button"  onclick="displayBlock('modal01')"><i class="fab fa-sistrix"></i></h3>
				<h3 class="hide-button"  onclick="hideCol(4)"><i class="fas fa-ban"></i></h3>
			</div>
			<div id="tb02" class="mv-table-style" style="height: 620px">
				<table id="tblMain" class="mv-waiting-table">
					<tr class="mv-list-table-head">
						<th style="width: 40px">				
							<h4>No.</h4>
						</th>
						<th style="width: 120px;display: ${numberLink}">
							<a href="WaitingTable?m=number">
								<button class="sort-button"  style="background-color: ${numberHColor}">
									<h4>Number<i class="fas fa-sort" style="float: right"></i></h4>
								</button>
							</a>
						</th>
						<th style="width: 120px">
							<a href="WaitingTable?m=money">
								<button class="sort-button"  style="background-color: ${moneyHColor}">
									<h4>P<i class="fas fa-sort" style="float: right"></i></h4>
								</button>
							</a>
						</th>
						<th id="4" style="width: 120px">
							<h4>Recover Money</h4>
						</th>
						<th>
							<h4>Total Net</h4>
						</th>
						<th style="width: 80px">				
							<h4>Summary</h4>
						</th>
					</tr>
					<c:forEach items="${twoDList}" var="twoD">
						<tr id="${twoD.number}" class="mv-waiting-table-data" style="color:${twoD.color}" >
							<td><h3>${twoD.count}</h3></td>
							<td style="display: ${numberLink}"><h3>${twoD.number}</h3></td>
							<td><h3>${twoD.money}</h3></td>
							<td id="4"><h3>${twoD.recoverMoney}</h3></td>
							<td><h3>${twoD.total}</h3></td>
							<td><h3><a href="SummaryTable?number=${twoD.number}" style="color: black">result</a></h3></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.8.0 &nbsp; Design by N0iSyLuvie</footer>
</body>
</html>