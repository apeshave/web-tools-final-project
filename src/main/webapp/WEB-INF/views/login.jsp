<head>
<link rel="stylesheet" type="text/css" href="resources/style.css"
	media="screen" />
</head>
<div class="main">
	<div class="login">
		<form action="login.htm" method="post">
			<input class="rounded" type="text" name="username"
				placeholder="Username" required><br /> <br /> <input
				class="rounded" type="password" name="password"
				placeholder="Password" required><br /> <br /> <input class="button"
				type="submit" value="Enter"> <br /> <br />
				<font color="red" style="font-size: 22px">${error}</font>
			<div class="newUser">
				Not a user?? <a href="choice.htm">Sign Up</a>
			</div>
		</form>
	</div>
</div>