<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<div id="msgbox01" class="mv-menu-msgBox mv-animate-fade-out"
		style="opacity: 0" onclick="displayNone('msgbox01')">${message}</div>
	<div id="msgbox02" class="mv-menu-msgBox mv-animate-fade-out"
		style="opacity: 0; background-color: red; color: black; margin-top: 28px"
		onclick="displayNone('msgbox02')">${alertMessage}</div>
	<!--extra modal for full size scenes on click-->
	<div id="modal01"
		class="mv-display-center mv-view-image mv-animate-zoom">
		<button class="mv-display-topRight mv-logIn-close-button"
			onclick="displayNone('modal01')">
			<i class="far fa-times-circle"></i>
		</button>
		<h2 style="color: white; text-align: center; font-family: robol">
			<i class="fas fa-street-view"></i>&emsp;
			<%
			String partition = (String) session.getAttribute("sessionPartition");
			out.print(partition);
			%>

		</h2>

		<div style="text-align: center">
			<!-- <a href="ClosedNumbers?closedNumber=0&mode=nothing">
					<button class="mv-delete-button hover-effect" ><i class="fas fa-stop"></i>&emsp;Closed Number</button>
				</a><br> -->
			<a href="index.jsp">
				<button class="mv-delete-button hover-effect">
					<i class="fas fa-sync"></i>&emsp;Change site
				</button>
			</a><br> <a href="Delete?id=delete">
				<button class="mv-delete-button hover-effect"
					onclick="if(!(confirm('Are you sure you want to delete user?'))) return false;">
					<i class="fas fa-user-slash"></i>&emsp;Delete User Data
				</button>
			</a><br> <a href="Delete?id=all">
				<button class="mv-delete-button hover-effect"
					onclick="if(!(confirm('Are you sure you want to reset week?'))) return false;">
					<i class="fas fa-ban"></i>&emsp;Reset Site 2D Data
				</button>
			</a><br> <span style="color: red">* Caution: This button has
				a significant risk.</span>

		</div>
		<div
			style="text-align: left; margin-top: 30px; margin-left: 100px; font-family: robom; color: white">
			<h4>ID Count : &emsp;&emsp;${id}</h4>
			<h4>Avg Comm % : &emsp;&emsp;${avgCommPercent} %</h4>
			<h4>Machine Name : &emsp;&emsp;${defMachineName}</h4>
			<h4>Green Limit : &emsp;&emsp;${greenColorLimit} ks</h4>
			<h4>Red Limit : &emsp;&emsp;${redColorLimit} ks</h4>
		</div>

	</div>
	<!---->
	<div id="modal02"
		class="mv-display-center mv-view-image mv-animate-zoom">
		<button class="mv-display-topRight mv-logIn-close-button hover-effect"
			onclick="displayNone('modal02')">
			<i class="far fa-times-circle"></i>
		</button>
		<h2 class="mv-username" style="margin-top: 50px;">
			<i class="fas fa-user"></i>&nbsp;
			<%
			String name = (String) session.getAttribute("userName");
			out.print(name);
			%>
		</h2>
		<div id="radioTab" class="mv-table-input">
			<h5 style="text-align: center">
				<spam style="color: red">red = not save</spam>
				&emsp;|&emsp;
				<spam style="color: green"> green = save</spam>
			</h5>
			<c:forEach items="${userList}" var="user">
				<a href="Setting?userName=${user.user}&partition=done1998&mode=twoD">
					<h5 class="user-choose">
						<span style="color:${user.checked}">${user.user}</span>&emsp;
						<spam style="float: right"> <fmt:formatNumber
							value="${user.money}" type="number" /> ks </spam>
					</h5>
				</a>
			</c:forEach>
		</div>

		<a href="Commission?mode=nothing">
			<button class="log-out-btn hover-effect">
				<i class="fas fa-user-plus"></i>&nbsp;Commissions
			</button>
		</a>

	</div>
	<div class="mv-basic-bg">
		<div class="mv-tab-bar">
			<div class="user-name hover-effect" onclick="displayBlock('modal02')">
				<i class="fas fa-users"></i>&nbsp;<b> <%
 out.print(name);
 %></b>
			</div>
			<h3>
				<a href="Table" class="tab-bar-button hover-effect margin-left"
					style="color:${home}"><i class="fas fa-home"></i> Home</a>
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
					class="tab-bar-button hover-effect margin-left"><i
					class="fas fa-table"></i> Full</a>
			</h3>
			<h3>
				<a href="Final" class="tab-bar-button hover-effect margin-left"><i
					class="fas fa-edit"></i> Report</a>
			</h3>
			<h3>
				<a href="HResult" class="tab-bar-button hover-effect margin-left"><i
					class="fas fa-book"></i> Ledger</a>
			</h3>
			<h3 class="tab-bar-button hover-effect margin-left"
				onclick="displayBlock('modal01')">
				<i class="fa fa-cogs"></i>
			</h3>

		</div>
		<div class="user-total-field">
			<b> <%
 out.print(name);
 %>
			</b>'s total money&emsp;-&emsp;
			<fmt:formatNumber value="${userTotalMoney}" type="number" />
			ks
			<h2 id="time" style="margin-left: 50px; margin-right: 50px"></h2>
			total money&emsp;-&emsp;
			<fmt:formatNumber value="${totalMoney}" type="number" />
			ks
		</div>
		<div class="mv-input-field mv-margin-top">
			<form id="singleInput" action="Table" method="post"
				style="display: none">
				<input id="page" type="hidden" name="pageNumber" value="${pageNo}">
				<input id="number1" type="text" name="number" autofocus="autofocus"
					tabindex="1" class="mv-number-button" placeholder="Enter" min="00"
					max="99"> <input type="number" name="money" tabindex="2"
					class="mv-money-drop-button" placeholder="Enter" min="50"
					max="300000" step="50" required>

				<table class="mv-special-note">
					<tr>
						<td>apuu</td>
						<td>==</td>
						<td>&emsp;**</td>
					</tr>
					<tr>
						<td>natkhat</td>
						<td>==</td>
						<td>&emsp;//</td>
					</tr>
					<tr>
						<td>power</td>
						<td>==</td>
						<td>&emsp;++</td>
					</tr>
					<tr>
						<td>nyiko</td>
						<td>==</td>
						<td>&emsp;/*</td>
					</tr>
					<tr>
						<td>? bk</td>
						<td>==</td>
						<td>&emsp;?+</td>
					</tr>
					<tr>
						<td>? start</td>
						<td>==</td>
						<td>&emsp;?*</td>
					</tr>
					<tr>
						<td>? end</td>
						<td>==</td>
						<td>&emsp;*?</td>
					</tr>
					<tr>
						<td>apar 19</td>
						<td>==</td>
						<td>&emsp;*?*</td>
					</tr>
					<tr>
						<td>apar 20</td>
						<td>==</td>
						<td>&emsp;*?**</td>
					</tr>
					<tr>
						<td>sone sone</td>
						<td>==</td>
						<td>&emsp;+/+</td>
					</tr>
					<tr>
						<td>ma ma</td>
						<td>==</td>
						<td>&emsp;-/-</td>
					</tr>
					<tr>
						<td>sone ma</td>
						<td>==</td>
						<td>&emsp;+/-</td>
					</tr>
					<tr>
						<td>ma sone</td>
						<td>==</td>
						<td>&emsp;-/+</td>
					</tr>
					<tr>
						<td>akhway</td>
						<td>==</td>
						<td>&emsp;???/</td>
					</tr>
					<tr>
						<td>akhwaypuu</td>
						<td>==</td>
						<td>&emsp;???/*</td>
					</tr>
					<tr>
						<td>? sone start</td>
						<td>==</td>
						<td>&emsp;++?</td>
					</tr>
					<tr>
						<td>? sone end</td>
						<td>==</td>
						<td>&emsp;?++</td>
					</tr>
					<tr>
						<td>? ma start</td>
						<td>==</td>
						<td>&emsp;--?</td>
					</tr>
					<tr>
						<td>? ma end</td>
						<td>==</td>
						<td>&emsp;?--</td>
					</tr>
					<tr>
						<td>? sone kat</td>
						<td>==</td>
						<td>&emsp;++?++</td>
					</tr>
					<tr>
						<td>? ma kat</td>
						<td>==</td>
						<td>&emsp;--?--</td>
					</tr>
				</table>
				<input id="submitbtn" class="mv-submit-btn hover-effect"
					type="submit" value="Add">
			</form>

			<form id="multiInput" action="MultiInput" method="post">
				<input id="page" type="hidden" name="pageNumber" value="${pageNo}">

				<div class="multiple-input-tab">
					<i style="color: white">1</i>&emsp;<input id="MNumber1"
						type="number" name="number1" class="multiple-input"
						placeholder="Enter" min="00" max="99" autofocus="autofocus"
						required> <br> <i style="color: white">2</i>&emsp;<input
						id="number" type="number" name="number2" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">3</i>&emsp;<input id="number" type="number"
						name="number3" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">4</i>&emsp;<input
						id="number" type="number" name="number4" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">5</i>&emsp;<input id="number" type="number"
						name="number5" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">6</i>&emsp;<input
						id="number" type="number" name="number6" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">7</i>&emsp;<input id="number" type="number"
						name="number7" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">8</i>&emsp;<input
						id="number" type="number" name="number8" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">9</i>&emsp;<input id="number" type="number"
						name="number9" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">10</i>&emsp;<input
						id="number" style="margin-left: -10px" type="number"
						name="number0" class="multiple-input" placeholder="Enter" min="00"
						max="99">
				</div>
				<div id="radioTab" class="mv-radio-btn">
					<h2 style="color: white">R</h2>
					<label class="container">Yes <input id="rd1" type="radio"
						name="reverse" value="yes" accesskey="r" onclick="myFocus()"
						checked> <span class="checkmark"></span>
					</label> <label class="container">No <input id="rd1" type="radio"
						name="reverse" value="no" accesskey="t" onclick="myFocus()">
						<span class="checkmark"></span>
					</label>
				</div>
				<input type="number" name="money" class="mv-money-drop-button"
					placeholder="Enter" min="50" max="300000" step="50" required>
				<input id="submitbtn" class="mv-submit-btn hover-effect"
					type="submit" value="Add">
			</form>

			<form id="customInput" action="CustomInput" method="post"
				style="display: none">
				<input id="page" type="hidden" name="pageNumber" value="${pageNo}">
				<div class="multiple-input-tab">
					<i style="color: white">1</i>&emsp;<input id="CNumber1"
						type="number" name="number1" class="multiple-input"
						placeholder="Enter" min="00" max="99" autofocus="autofocus"
						required> <br> <i style="color: white">2</i>&emsp;<input
						id="number" type="number" name="number2" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">3</i>&emsp;<input id="number" type="number"
						name="number3" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">4</i>&emsp;<input
						id="number" type="number" name="number4" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">5</i>&emsp;<input id="number" type="number"
						name="number5" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">6</i>&emsp;<input
						id="number" type="number" name="number6" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">7</i>&emsp;<input id="number" type="number"
						name="number7" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">8</i>&emsp;<input
						id="number" type="number" name="number8" class="multiple-input"
						placeholder="Enter" min="00" max="99"> <br> <i
						style="color: white">9</i>&emsp;<input id="number" type="number"
						name="number9" class="multiple-input" placeholder="Enter" min="00"
						max="99"> <br> <i style="color: white">10</i>&emsp;<input
						id="number" style="margin-left: -10px" type="number"
						name="number0" class="multiple-input" placeholder="Enter" min="00"
						max="99">
				</div>
				<input type="number" name="money" style="right: 200px"
					class="mv-money-drop-button" placeholder="Enter" min="50"
					max="300000" step="50" required>
				<h2 style="color: white; position: absolute; right: 80px">R</h2>
				<input type="number" name="rMoney" class="mv-money-drop-button"
					placeholder="Enter" min="50" max="300000" step="50" required>
				<input id="submitbtn" class="mv-submit-btn hover-effect"
					type="submit" value="Add">
			</form>

			<button id="singlebtn" class="special-button hover-effect"
				onclick="switchTab('singleInput')" accesskey="s">Special(S)</button>
			<button id="multibtn" class="special-button hover-effect"
				onclick="switchTab('multiInput')" style="display: none"
				accesskey="z">Multi(Z)</button>
			<button id="multibtn" class="special-button hover-effect"
				style="left: 120px" onclick="switchTab('customInput')" accesskey="c">Custom(C)</button>
		</div>
		<div class="mv-home-panel">
			<button class="mv-drop-down-button hover-effect"
				onmouseenter="displayBlock('drop01')"
				onmouseleave="displayNone('drop01')">
				View&emsp;<i class="fas fa-caret-down" style="float: right"></i>
			</button>
			<div id="drop01" class="mv-drop-down-list"
				onmouseenter="displayBlock('drop01')"
				onmouseleave="displayNone('drop01')">
				<a href="SortByUser?m=money"><i class="fas fa-file-alt"></i>&emsp;Normal</a>
				<a href="History"><i class="fas fa-file-contract"></i>&emsp;History
				</a> <a href="Details"><i class="fas fa-file-invoice"></i>&emsp;Details</a>
			</div>
			<form action="Search" method="post" id="search01">
				&nbsp;<i class="fab fa-sistrix">&nbsp;</i><input type="number"
					name="number" class="mv-list-search-box"
					placeholder="Search Total..." required>
			</form>
			<form action="Details" method="post" id="search02">
				&nbsp;<i class="fas fa-eraser">&nbsp;</i><input type="number"
					name="number" class="mv-list-search-box"
					placeholder="Search Details..." required>
			</form>
		</div>
		<div class="mv-table-style"
			style="margin-top: 45px; max-height: 550px">
			<table class="mv-list-table">
				<tr class="mv-list-table-head">
					<th style="display: ${numberLink}"><a
						href="SortByUser?m=number">
							<button class="sort-button"
								style="background-color: ${numberHColor}">
								<h4>
									Number<i class="fas fa-sort" style="float: right"></i>
								</h4>
							</button>
					</a></th>
					<th style="width: 300px;display: none; display: ${noteLink}"><h4>Note</h4></th>
					<th style="width:40px;display: none;display: ${rLink}"><h4>R</h4></th>
					<th><a href="SortByUser?m=money">
							<button class="sort-button"
								style="background-color: ${moneyHColor}">
								<h4>
									Money<i class="fas fa-sort" style="float: right"></i>
								</h4>
							</button>
					</a></th>
					<th style="display: none;display: ${rNumberLink}"><h4>R
							Money</h4></th>
					<th style="display: none;display: ${totalLink}"><h4>Total</h4></th>
					<th style="display: none;display: ${totalMoneyLink}"><h4>Total</h4></th>
					<th style="width: 80px;display: ${quantityLink}"><a
						href="SortByUser?m=quantity">
							<button class="sort-button"
								style="background-color: ${quantityHColor}">
								<h4>
									Quantity<i class="fas fa-sort" style="float: right"></i>
								</h4>
							</button>
					</a></th>
					<th style="display: none;display: ${deleteLink} "><h4>Time</h4></th>
					<th style="width: 60px;display: none;display: ${deleteLink} "><h4>By</h4></th>
					<th style="width: 60px;display: none;display: ${deleteLink}"><h4>Delete</h4></th>
				</tr>

				<c:forEach items="${twoDList}" var="twoD">
					<tr id="${twoD.number}" class="mv-list-table-data">
						<td style="color: ${twoD.color};display: ${numberLink}"><h3>${twoD.number}</h3></td>
						<td style="display: none;display: ${noteLink}"><h3>${twoD.note}</h3></td>
						<td style="display: none;display: ${rLink}"><h3>${twoD.r}</h3></td>
						<td style="color: ${twoD.color}"><h3>
								<fmt:formatNumber value="${twoD.money}" type="number" />
							</h3></td>
						<td style="display: none;display: ${totalMoneyLink}"><h3>${twoD.quantity}</h3></td>
						<td style="color: ${twoD.color};display: ${quantityLink}"><h3>${twoD.quantity}</h3></td>
						<td style="display: none; display: ${rNumberLink}"><h3>${twoD.rNumber}</h3></td>
						<td style="display: none;display: ${totalLink}"><h3>
								<fmt:formatNumber value="${twoD.total}" type="number" />
							</h3></td>
						<td style="display: none;display: ${deleteLink}"><h3>${twoD.time}</h3></td>
						<td style="display: none;display: ${deleteLink}"><h3>${twoD.by}</h3></td>
						<td style="display: none; display: ${deleteLink}"><a
							href="Delete?id=${twoD.id}&pageNo=${pageNo}"><i
								class="fas fa-trash"></i></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<footer style="background-color: black; color: white">&copy;
		Heart Referee Version 4.0.0 &nbsp; Design by N0iSy</footer>
</body>
</html>