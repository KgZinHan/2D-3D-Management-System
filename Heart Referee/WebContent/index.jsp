<!DOCTYPE html>
<html lang="en">
<head>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>Heart Referee</title>
<link rel="stylesheet" href="./cssStyle/styles.css">
<link rel="stylesheet" href="./cssStyle/all.css">
<script src="JavaScript/javaScript.js" defer></script>
</head>
<body>
	<div class="mv-index-bg">
		<div id="click">
			<h3 id="msg00" class="mv-menu-msgBox">${message}</h3>
		</div>
		<div class="mv-display-center">
			<!-- <h3 style="font-family: roboto;color: silver">since 2021<br>founded by S.H.M.K <br> shared by H.M.K.T.Z</h3> -->
			<h1 class="mv-welcomeText animate-charcter">
				HEART REFEREE &nbsp;<i class="fas fa-heart"></i><i
					class="fas fa-user-secret"></i>
			</h1>
			<span style="color: white">Version 4.0.0</span>
			<div>
				<form action="Setting" method="GET" style="text-align: center">
					<div style="width: 100%; margin-bottom: 10px;text-align: center">
						<select id="mode" name="mode" class="mv-list-search-box"
							style="width: 226px; border: 1px solid white; color: white;background: rgba(0,0,0,0.5)">
							<option value="twoD" style="color: black">2D</option>
							<option value="threeD" style="color: black">3D</option>
						</select>
					</div>
					<div style="width: 100%; margin-bottom: 10px;text-align: center">
						<input id="partition" type="text" name="partition"
							placeholder="Enter Site Partition" value="DEFAULT" maxlength="20"
							class="mv-list-search-box"
							style="width: 200px; border: 1px solid white; color: white;background: rgba(0,0,0,0.5)"
							required>
					</div>
					<div style="width: 100%; margin-bottom: 10px;">
					<input id="user" name="userName" placeholder="Enter commission Name" 
							maxlength="20" class="mv-list-search-box"
							style="width: 200px; border: 1px solid white; color: white;background: rgba(0,0,0,0.5)" autofocus
							>
						<!-- <input id="logIn" name="logInName" placeholder="Enter LogIn Name"
							maxlength="20" class="mv-list-search-box"
							style="width: 200px; border: 1px solid white; color: white;background: rgba(0,0,0,0.5)"
							required> -->
					</div>
					
					<div style="width: 100%; margin-bottom: 10px;text-align: center">
						<input id="submitbtn" type="submit" class="enter-button"
							value="Enter" onclick="displayMsgBox()">
					</div>

				</form>
			</div>
		</div>
	</div>
	
</body>

</html>