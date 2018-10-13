<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<style>           
.blue-button{
	background: #25A6E1;
	filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#25A6E1',endColorstr='#188BC0',GradientType=0);
	padding:3px 5px;
	color:#fff;
	font-family:'Helvetica Neue',sans-serif;
	font-size:12px;
	border-radius:2px;
	-moz-border-radius:2px;
	-webkit-border-radius:4px;
	border:1px solid #1A87B9
}     
table {
  font-family: "Helvetica Neue", Helvetica, sans-serif;
   width: 50%;
}
th {
  background: SteelBlue;
  color: white;
}
 td,th{
                border: 1px solid gray;
                width: 25%;
                text-align: left;
                padding: 5px 10px;
            }
</style>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
        
        
</script>
</head>
<body>
<c:url var="validateUrl" value="/api/login" />
<form id="user_form" action="${validateUrl}" method="POST">
<table>
<tr>
	<th colspan="2">Please Login</th>
</tr>
<tr>
   <td>Username:</td>
   <td><input type="text" id="name" name="username" placeholder="enter username"></td>
</tr>
<tr>
   <td>Password:</td>
   <td><input type="password" id="pwd" name="password" placeholder="enter password"></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" class="blue-button"/></td>
</tr>
</table>
</form>

<c:url var="register" value="/register.jsp" />
		<a href = "${register}">Click here to Register if you are here for the first time</a>  
</body>
</html>

