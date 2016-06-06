<HEAD>

<SCRIPT LANGUAGE="JavaScript">

<!-- Begin
function verify() {
var themessage = "You are required to complete the following fields: ";
if (document.form.title.value=="") {
themessage = themessage + " - Title";
}
if (document.form.fname.value=="") {
themessage = themessage + " -  First Name";
}
if (document.form.lname.value=="") {
themessage = themessage + " -  Last Name";
}
if (document.form.emailaddr.value=="") {
themessage = themessage + " -  Email Address";
}
if (document.form.password.value=="") {
themessage = themessage + " -  Password";
}
if (document.form.phone.value=="") {
themessage = themessage + " -  Phone";
}
//alert if fields are empty and cancel form submit
if (themessage == "You are required to complete the following fields: ") {
document.form.submit();
}
else {
alert(themessage);
return false;
   }
}
//  End -->
</script>

</HEAD>

<BODY>

<form  name=form action="subscribe/JavaMysqlInsert" method=POST>
        Select Title: <select name="title">
  <option value="MR">MR</option>
  <option value="MS">MS</option>
  <option value="DR">DR</option>
  <option value="Master">Master</option>
  <option value="Miss">Miss</option>
  <option value="Jr">Jr</option>
</select> <br/>
        Enter Your First Name: <input type="text" name="fname" /> <br/>
        Enter Your Last Name: <input type="text" name="lname" /> <br/>
        Enter Your Email Address: <input type="text" name="emailaddr" /> <br/>
        Enter Your Password: <input type="password" name="password" /> <br/>
        Enter Your Primary Phone: <input type="text" name="phone" /> <br/>
<input type=submit value="Submit Request" onclick="verify();">
<input type=reset value="Clear Form"><br>
</form>
</BODY>
