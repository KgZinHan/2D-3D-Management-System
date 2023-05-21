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
<body  tabindex="-1" style="overflow-x: hidden">
	<div class="mv-ftbl-bg">
		<div class="mv-tab-bar">
			<h3>
				<a href="Table" class="recover-button hover-effect margin-left">Home</a>
			</h3>
			<h3>
				<a href="WaitingTable?m=default" class="waiting-button hover-effect margin-left">Waiting Table</a>
			</h3>
			<h3>
				<a href="Recover?limit=1500" class="ftbl-button hover-effect margin-left">Recover Check</a>
			</h3>
			<h3>
				<a href="RecoverPageController" class="recover-button hover-effect margin-left">Recover Note</a>
			</h3>
			<h3>
				<a href="FullTableController" class="ftbl-button hover-effect margin-left" style="color: ${fullTable}">Full Table</a>
			</h3>
			<h3>
				<a href="Final" class="ftbl-button hover-effect margin-left">Final</a>
			</h3>
		</div>
		<div class="user-total-field">
				total money&emsp;-&emsp;${totalMoney} ks
				<div style="margin-left: 100px"> 
  					total recover&emsp;-&emsp;${totalRecover} ks
				</div>
			</div>
		
		<div class="ftbl-row">
	  		<div class="ftbl-column" style="margin-left: 10px">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${zeroList}" var="twoD">  		
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th> 
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>	 
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${oneList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${twoList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${threeList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${fourList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${fiveList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				         <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${sixList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${sevenList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${eightList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}&money=${twoD.money}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
	  		<div class="ftbl-column">
			    <table class="ftbl-table-style">
			    	<c:forEach items="${nineList}" var="twoD">
				      <tr>
				        <th class="ftbl-th"><a href="SummaryTable?number=${twoD.number}">${twoD.number}</a></th>
				        <th class="ftbl-th" style="width: 90%"> <a href="SummaryTable?number=${twoD.number}&money=${twoD.money}" style="color:${twoD.color}">${twoD.money}</a></th>
				      </tr>
			      </c:forEach>
			    </table>
	  		</div>
		</div>
	</div>
	<footer style="background-color: black;color:white">&copy; Heart Referee Version 2.7.0 &nbsp; Design by N0iSyLuvie</footer>
</body>
</html>