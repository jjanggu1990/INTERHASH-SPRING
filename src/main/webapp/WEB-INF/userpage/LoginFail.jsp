<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>

	.test{
		
	}
	
</style>
</head>

<script>

function checkIt(){
	var userinput = eval("document.logininfo");
	
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	
	if(!logininfo.email.value){
		alert("Email을 입력하세요.");
		userinput.email.focus(); 
		return false;
	}
	
	/* if(regex.test(logininfo.email.value) === false) 
	{  
	    alert("잘못된 Email 형식입니다.");  
	    userinput.email.focus(); 
	    return false; 
	} */

	if(!logininfo.password.value){
		alert("비밀번호를 입력하세요.");
		userinput.password.focus(); 
		return false;		
	}

	 return true;
}

function win()
{
	opener.parent.location.href='LoginPro.do'; 
	self.close();
} 

</script>
</head>
<body onload="win()">

<div class="logo1">
	<img src="proimg/interHash.png" width="500" height="130">
</div>

<br><br>

로그인 실패 // 해당 아이디나 비밀번호가 틀립니다.<br><br>

<div class="login">
	<form action="LoginPro.do" method="post" name="logininfo" onSubmit="return checkIt()"><br>
		<span>
			<table>
				<tr>
					<td align="left">ID :: </td>
					<td align="left"><input type="text" name="email"  size="20" /> <br><!--  onfocus="this.value='';"  maxlength="15" --></td>
				</tr>
				
				<tr>
					<td align="left">PASSWORD ::</td>
					<td align="left"> <input type=password name="password"  size="21" /></td>
				</tr>	
			</table>		
		</span>
		<span style="position: fixed; left: 280px; top: 230px;">
			<input type="submit" style="WIDTH: 50pt; HEIGHT: 50pt" value="Login">
					 <!-- onclick="javascript:window.location='../index.jsp'" -->
		</span>
	</form>
</div>
<br>
<br>

<div class="join">		
	<table>
		<tr> 
			<td align="right">아직 회원이 아니신가요?</td>
			<td width="20"/> 
			<td>
				<input type="button" value="Join" style="WIDTH: 40pt; HEIGHT: 20pt" onclick="javascript:window.location='UserInsertForm.do'">
			</td>	
		</tr>
			
		<tr height="10"/>
			
		<tr> 
			<td align="right">비밀번호를 잊어버리셨나요?</td>
			<td width="20"/> 
			<td>
				<input type="button" value="Find" style="WIDTH: 40pt; HEIGHT: 20pt" onclick="javascript:window.location='FindPasswordForm.do'">
			</td>	
		</tr>
	</table>
</div>
<br>

<c:if test="${memId!=null}">
<input type="button" value="로그아웃" onClick="location.href='LogOut.do'" />
</c:if>
<br>

<input type="button" value="이전으로" onclick="history.go(-1)" /> <br>
<%-- <% response.sendRedirect("index.do"); %> --%>

<c:if test="${memId==null }">
memId == null
</c:if>
<br>

</body>
</html>