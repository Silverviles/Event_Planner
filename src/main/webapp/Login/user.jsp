<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Event Planning</title>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" href="user.css">
  
</head>
<body>
    <div class="box-form">
        <div class="left">
		    <div class="overlay">
		        <h1>Online Event Planning Platform</h1>
		        <p>A platform where all your events can be planned.</p>
		        <div> <!-- Add a div or another block-level container here -->
		            <p>login with social media</p>
		            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i> Login with Facebook</a>
		            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i> Login with Twitter</a>
		        </div>
		    </div>
		</div>

        <div class="right" id="login-div">
            <h5>Login</h5>
            <p>Don't have an account? <a href="#" id="create-account-link">Create Your Account</a> it takes less than a minute</p>
            <form method="post" action="../LoginServlet">
            	<div class="inputs">
	                <input type="text" placeholder="Username" name="username">
	                <br>
	                <input type="password" placeholder="password" name="password">
	            </div>
	
	            <br><br>
	
	            <div class="remember-me--forget-password">
	                <label>
	                    <input type="checkbox" name="item" checked/>
	                    <span class="text-checkbox">Remember me</span>
	                </label>
	                <p>forget password?</p>
	            </div>
	            
	            <br>
            	<input type="submit" value="Login">
            </form>
        </div>

        <div class="right" style="display: none;" id="signup-div">
            <form method="post" action="../SignupServlet">
            	<h5>Sign Up</h5>
				<p>Already have an account? <a href="#" id="signin-link">Sign In</a></p>
	            <div class="inputs">
	            	<input type="text" placeholder="Username" name="username">
	                <br>
	                <input type="email" placeholder="Email" name="email">
	                <br>
	                <input type="password" placeholder="Password" name="password">
	                <br>
	                <input type="password" placeholder="Confirm Password">
	                <br>
	                <input type="text" placeholder="Mobile Number" name="mobile_no">
	                <br>
	            </div>
	        
	            <br><br>
	        
	            <input type="submit" value="Signup">
            </form>
        </div>
    </div>
	<script src="user.js"></script>
</body>
</html>