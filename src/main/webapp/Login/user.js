document.addEventListener("DOMContentLoaded", function () {
//Functionality to change signup/login divs
    const createAccountLink = document.getElementById("create-account-link");
    const signinLink = document.getElementById("signin-link");
    const loginForm = document.getElementById("login-div");
    const signupForm = document.getElementById("signup-div");

    createAccountLink.addEventListener("click", function (e) {
        e.preventDefault();
        loginForm.style.display = "none";
        signupForm.style.display = "block";
    });

    signinLink.addEventListener("click", function (e) {
        e.preventDefault();
        loginForm.style.display = "block";
        signupForm.style.display = "none";
    });

//--------------------------------------------------------------------------------------------------
	// Functionality to process form inputs
    // Check if the URL contains a status parameter with value -1
	const urlParams = new URLSearchParams(window.location.search);
	if (urlParams.has("status") && urlParams.get("status") === "-1") {
	  alert("User not found");
	  // Clear the URL by replacing it without the 'status' parameter
	  urlParams.delete("status");
	  const newUrl = window.location.pathname + (urlParams.toString() ? `?${urlParams.toString()}` : "");
	  history.replaceState({}, document.title, newUrl);
	}
	else if(urlParams.has("status") && urlParams.get("status") === "0"){
		alert("Invalid Password");
		// Clear the URL by replacing it without the 'status' parameter
		urlParams.delete("status");
		const newUrl = window.location.pathname + (urlParams.toString() ? `?${urlParams.toString()}` : "");
		history.replaceState({}, document.title, newUrl);
	}
});