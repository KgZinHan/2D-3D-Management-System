<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<body tabindex="-1" style="overflow-x: hidden">
	<div class="mv-ftbl-bg">
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
					class="tab-bar-button hover-effect margin-left"
					style="color: ${fullTable}"><i class="fas fa-table"></i> Full</a>
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
		<div class="user-total-field">
			total money&emsp;-&emsp;<fmt:formatNumber
										value="${totalMoney}" type="number" /> ks
			<div style="margin-left: 50px; margin-right: 50px">total
				recover&emsp;-&emsp;<fmt:formatNumber
										value="${totalRecover}" type="number" /> ks</div>
			<form action="FullTableController" method="post">
				<input type="hidden" name="mode" value="${buttonName}">
				<button id="btn3" class="f-pg-enter-no hover-effect"
					style="width: 200px; cursor: pointer">
					<i class="fas fa-sync"></i>&emsp;${buttonName}
				</button>
			</form>
		</div>

		<div class="ftbl-row">
			<div class="ftbl-column" style="margin-left: 10px">
				<table class="ftbl-table-style">
					<c:forEach items="${zeroList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber
										value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${oneList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber
										value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${twoList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber
										value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${threeList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${fourList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${fiveList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${sixList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${sevenList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}"
								style="color:${twoD.color}"><fmt:formatNumber value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${eightList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}&money=${twoD.money}"
								style="color:${twoD.color}"><fmt:formatNumber value="${twoD.money}" type="number" /></a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="ftbl-column">
				<table class="ftbl-table-style">
					<c:forEach items="${nineList}" var="twoD">
						<tr>
							<th class="ftbl-th"><a
								href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
							<th class="ftbl-th" style="width: 90%; text-align: right"><a
								href="SummaryTable?number=${twoD.number}&money=${twoD.money}"
								style="color:${twoD.color}"><fmt:formatNumber value="${twoD.money}" type="number" />	</a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<footer style="background-color: black; color: white">&copy;
		Heart Referee Version 4.0.0 &nbsp; Design by N0iSy</footer>
</body>
</html>