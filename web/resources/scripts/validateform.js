/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function passcheck()
{

var pass = formreg.password.value;
var repass = formreg.password1.value;

if (pass != repass)
    {
        alert("Your Passwords dont match");
        return false;
    }

}

function requiredcheck()
{
    var fname = formreg.firstname.value;
    var lname = formreg.lastname.value;
    var email = formreg.email.value;
    var uname = formreg.username.value;
    
    if (fname==null || lname==null || email==null || uname==null || fname=="" || lname=="" || email=="" || uname=="")
        {
            alert("Please fill all the required fields");
            return false;
        }
    
   return passcheck();
}