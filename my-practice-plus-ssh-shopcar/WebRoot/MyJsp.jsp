<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<SCRIPT>
function fnRandom(iModifier){
return parseInt(Math.random()*iModifier);
}
function fnSetValues(){
var iHeight=oForm.oHeight.options[
oForm.oHeight.selectedIndex].text;
if(iHeight.indexOf("Random")>-1){
iHeight=fnRandom(document.body.clientHeight);
}
var sFeatures="dialogHeight: " + iHeight + "px;";
return sFeatures;
}
function fnOpen(){
var sFeatures=fnSetValues();
window.showModalDialog("showModalDialog_target.htm", "",
sFeatures)
}
</SCRIPT>
<FORM NAME=oForm>
Dialog Height <SELECT NAME="oHeight">
<OPTION>-- Random --
<OPTION>150
<OPTION>200
<OPTION>250
<OPTION>300
</SELECT>
Create Modal Dialog Box
<INPUT TYPE="button" VALUE="Push To Create"
onclick="fnOpen()">
