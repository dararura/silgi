function checkForm(){
	var p_code=document.getElementById("p_code").value;
	if(!p_code){
		alert("제품코드가 입력되지 않았습니다!");
		document.getElementById("p_code").focus();
		return false;
	}
	var p_cnt=document.getElementById("p_cnt").value;
	if(!p_cnt){
		alert("수량이 입력되지 않았습니다!");
		document.getElementById("p_cnt").focus();
		return false;
	}
	var t_date=document.getElementById("t_date").value;
	if(!t_date){
		alert("거래일자가 입력되지 않았습니다!");
		document.getElementById("t_date").focus();
		return false;
	}
	var c_code=document.getElementById("c_code").value;
	if(!c_code){
		alert("거래처를 선택하세요!");
		document.getElementById("c_code").focus();
		return false;
	}
	return true;
}

function reForm(){
	alert("정보를 지우고 처음부터 다시 입력합니다");

	document.getElementById("p_code").value="";
	document.getElementById("p_cnt").value="";
	document.getElementById("t_date").value="";
	document.getElementById("c_code").value="";
	
	document.getElementById("p_code").focus();
	
}