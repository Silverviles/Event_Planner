<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

	<title>Admin Portal</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />

	<!-- vendor css -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/AdminPortal/admin_styles.css">
</head>

<body class="">
	<!-- [ Main Content ] start -->
	<div class="main-container">
		<div class="pcoded-wrapper">
			<div class="pcoded-content">
				<div class="pcoded-inner-content">
					<div class="main-body">
						<div class="page-wrapper">
							<!-- [ breadcrumb ] start -->
							<div class="page-header">
								<div class="page-block">
									<div class="row align-items-center">
										<div class="col-md-12">
											<div class="page-header-title">
												<h1>Admin Dashboard</h1>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- [ breadcrumb ] end -->
							<!-- [ Main Content ] start -->
							<div class="row">

								<!-- User Details Summary -->
								<div class="col-xl-3 col-md-6">
									<div class="card prod-p-card bg-c-red">
										<div class="card-body">
											<div class="row align-items-center m-b-25">
												<div class="col">
													<h6 class="m-b-5 text-white">Total Users</h6>
													<h3 class="m-b-0 text-white">User Count: ${user_count}</h3>
												</div>
												<div class="col-auto">
													<i class="fas fa-solid fa-users text-c-red f-18"></i>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xl-3 col-md-6">
									<div class="card prod-p-card bg-c-blue">
										<div class="card-body">
											<div class="row align-items-center m-b-25">
												<div class="col">
													<h6 class="m-b-5 text-white">Customers</h6>
													<h3 class="m-b-0 text-white">Customer Count: ${customer_count}</h3>
												</div>
												<div class="col-auto">
													<i class="fas fa-solid fa-user f-18"></i>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xl-3 col-md-6">
									<div class="card prod-p-card bg-c-green">
										<div class="card-body">
											<div class="row align-items-center m-b-25">
												<div class="col">
													<h6 class="m-b-5 text-white">Service Providers</h6>
													<h3 class="m-b-0 text-white">Service Provider Count: ${service_provider_count}</h3>
												</div>
												<div class="col-auto">
													<i class="fas fa-dollar-sign text-c-green f-18"></i>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xl-3 col-md-6">
									<div class="card prod-p-card bg-c-yellow">
										<div class="card-body">
											<div class="row align-items-center m-b-25">
												<div class="col">
													<h6 class="m-b-5 text-white">Event Organizers</h6>
													<h3 class="m-b-0 text-white">Event Organizer Count: ${event_organizer_count}</h3>
												</div>
												<div class="col-auto">
													<i class="fas fa-solid fa-user-tie text-c-yellow f-18"></i>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- sessions-section start -->
								<div class="col-xl-8 col-md-6">
									<div class="card table-card">
										<div class="card-header">
											<h5>Site visitors session log</h5>
										</div>
										<div class="card-body px-0 py-0">
											<div class="table-responsive">
												<div class="session-scroll" style="height:478px;position:relative;">
													<div id="users_details" class="details">
                                                        <form method="post" action="ModifyUserServlet">
														    <table>
														        <thead>
														            <tr>
														                <th>User ID</th>
														                <th>Username</th>
														                <th>Password</th>
														                <th>Email</th>
														                <th>Mobile Number</th>
														                <th>Type</th>
														                <th>Action</th>
														                <th>Update</th>
														            </tr>
														        </thead>
														        <tbody>
														            <c:forEach items="${users}" var="user">
																	    <tr>
																	        <td>${user.userid}</td>
																	        <td>${user.username}</td>
																	        <td>${user.password}</td>
																	        <td>${user.email}</td>
																	        <td>${user.mobile_no}</td>
																	        
																	        <td>
																	            <c:choose>
																	                <c:when test="${user.admin}">
																	                    <!-- Display admin text and disable buttons for admins -->
																	                    <td>Admin</td>
																	                    <td>
																	                        <button type="button" disabled>Delete</button>
																	                    </td>
																	                    <td>
																	                        <button type="button" disabled>Update</button>
																	                    </td>
																	                </c:when>
																	                <c:otherwise>
																	                    <!-- Display "Delete" and "Update" buttons for non-admin users -->
																	                    <td>
																				            <select name="userType_${user.userid}" id="userType_${user.userid}">
																				                <option value="Event Organizer" ${user.event_organizer ? 'selected' : ''}>Event Organizer</option>
																				                <option value="Service Provider" ${user.service_provider ? 'selected' : ''}>Service Provider</option>
																				                <option value="Customer" ${!user.event_organizer && !user.service_provider ? 'selected' : ''}>Customer</option>
																				            </select>
																				        </td>
																	                    <td>
																	                        <button type="button" onclick="submitAction(${user.userid})">Delete</button>
																	                    </td>
																	                    <td>
																	                        <button type="button" onclick="submitUpdate(${user.userid})">Update</button>
																	                    </td>
																	                </c:otherwise>
																	            </c:choose>
																	        </td>
																	    </tr>
																	</c:forEach>
														        </tbody>
														    </table>
														    <input type="hidden" id="action" name="action" value="">
														    <input type="hidden" id="userid" name="userid" value="">
														    <input type="hidden" id="newType" name="newType" value="">
														</form>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- sessions-section end -->
								<div class="col-md-6 col-xl-4">
									<div class="card user-card">
										<div class="card-header">
											<h5>Profile</h5>
										</div>
										<div class="card-body  text-center">
											<div class="usre-image">
												<i class="fas fa-solid fa-user f-18"></i>
											</div>
											<h6 class="f-w-600 m-t-25 m-b-10">Alessa Robert</h6>
											<p>Active | Male | Born 23.05.1992</p>
										</div>
									</div>
								</div>
							</div>

							<!-- [ Main Content ] end -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- [ Main Content ] end -->

	<!-- Required Js -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://kit.fontawesome.com/121818005c.js" crossorigin="anonymous"></script>
	<script>
		function submitAction(userid) {
			document.getElementById("action").value = "delete";
			document.getElementById("userid").value = userid;
			document.querySelector('form').submit();
		}
	
		function submitUpdate(id) {
			var typeID = "userType_" + id.toString();
			var selectElement = document.getElementById(typeID);
			var newType = selectElement.value;
			document.getElementById("action").value = "update";
			document.getElementById("userid").value = id;
			document.getElementById("newType").value = newType;
			document.querySelector('form').submit();
		}
	</script>

</body>
</html>