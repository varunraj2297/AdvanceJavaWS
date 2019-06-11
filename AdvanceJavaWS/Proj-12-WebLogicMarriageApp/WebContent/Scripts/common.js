function validate(frm) {
		let fname=frm.name.value;
		let fage=frm.age.value;
		frm.vflag.value="yes";
		document.getElementById("errName").innerHTML="";
		document.getElementById("errAge").innerHTML="";		
		alert("Client side validations are taking place..");
		if(fname==""){
			document.getElementById("errName").innerHTML="Please enter the name";
			frm.name.focus();
			return false;
		}
		else if(fage==""){
			document.getElementById("errAge").innerHTML="Please enter the age";
			frm.age.focus();
			return false;
		}
		else if (isNaN(fage)) {
			document.getElementById("errAge").innerHTML="age must be numerical";
			frm.age.focus();
			return false;
		}
		else if(fage<=0||fage>125){
			document.getElementById("errAge").innerHTML="age must be in b/w 1 to 125";
			frm.age.focus();
			return false;
		}
		
		return true;
	}