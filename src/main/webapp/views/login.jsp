<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/resources/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/login/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('<%= request.getContextPath() %>/resources/login/images/bg-01.jpg');">
			<div class="wrap-login100">
				<form:form class="login100-form validate-form" action="manager" method="post" modelAttribute="user">
					<span class="login100-form-logo">
						<i class="zmdi zmdi-landscape"></i>
					</span>

					<span class="login100-form-title p-b-34 p-t-27">
						<spring:message code="login"></spring:message>
					</span>
					
					<c:if test="${notExistUser == 0 }">
							<p class="errorLogin">
								<spring:message code="NotExistUser"></spring:message>
							</p>
					</c:if>
					<c:if test="${captcha == 0 }">
							<p class="errorLogin">
								<spring:message code="NotCaptcha"></spring:message>
							</p>
						</c:if>

					<div class="wrap-input100" data-validate = "Enter username">
						<form:input class="input100" type="text" path="username" />
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
						<form:errors path="username" cssClass="errorLogin"></form:errors>
					</div>

					<div class="wrap-input100" data-validate="Enter password">
						<form:input class="input100" type="password" path="password" />
						<span class="focus-input100" data-placeholder="&#xf191;"></span>
						<form:errors path="password" cssClass="errorLogin"></form:errors>
					</div>
					
					<!-- reCAPTCHA -->
					<div class="g-recaptcha" data-sitekey="6LfxAVgUAAAAALzzAeRA1IkfekbUY2FZQlCQRm6P">
					</div>
					
					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1">
							Remember me
						</label>
					</div>

					<div class="container-login100-form-btn">
						<form:button class="login100-form-btn" type="submit"><spring:message code="login"></spring:message></form:button>
					</div>

					<div class="text-center p-t-90">
						<a href="?language=en" style="color:black;">English</a>|
		  				<a href="?language=vi" style="color:black;">Tiếng Việt</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/resources/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/resources/login/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/resources/login/vendor/bootstrap/js/popper.js"></script>
	<script src="<%= request.getContextPath() %>/resources/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/resources/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/resources/login/vendor/daterangepicker/moment.min.js"></script>
	<script src="<%= request.getContextPath() %>/resources/login/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/resources/login/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/resources/login/js/main.js"></script>

	<script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>