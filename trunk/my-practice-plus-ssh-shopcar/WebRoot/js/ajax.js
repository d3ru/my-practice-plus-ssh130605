	// user login
   	var request;  
    function loginRequest(proName){
   		//����XMLHttpRequest����
   		try{
    		request=new XMLHttpRequest();
    	}catch(er){
    		try{
    			request=new ActiveXObject("MSXML2.XMLHTTP");
    		}catch(ex){
    			try{
					request=new ActiveXObject("Microsoft.XMLHTTP");
    			}catch(ep){
    				request=null;
    			}
    		}
    	}
    	var username=document.all.username.value;
    	var password=document.all.password.value;
    	var img=document.all.img.value;
    	if(username==""){
    		alert("�û�������Ϊ��");
    		document.all.username.focus(); 
    		return ;
    	}
    	if(password==""){
    		alert("���벻��Ϊ��");
    		document.all.password.focus();
    		return ;
    	}
    	if(img==""){
    		alert("��֤�벻��Ϊ��");
    		document.all.img.focus(); 
    		return ;
    	}
    	request.open("get",proName+"/loginservice.do?username="+username+"&password="+password+"&img="+img);
    	request.onreadystatechange=function(){
   			//ֻ��������ɹ���ʱ��
   			var state=request.readyState;
   			if(state==4){
   				//alert(state);
   				var responseContext;
   				if(request.status==200){
   					window.location.reload();
   				}
   			}
   		};
		//4.��������
		request.send(null);
	}
	function cancel(){
		var username=document.all.username.value;
    	var password=document.all.password.value;
    	var img=document.all.img.value;
    	if(username!=""){
    		document.all.username.value="";
    		return ;
    	}
    	if(password!=""){
    		document.all.password.value="";
    		return ;
    	}
    	if(img!=""){
    		document.all.img.value="";
    		return ;
    	}
	}
	var ajax3;//����Ʒ���ؼ��ֲ�ѯ��Ʒ
   	function showprobytype(type){

   		var url="listproductbytype.jsp?typeid="+type;		
   		window.open(url,"_self");
   	}
   	var ajax4;//�����Ʒ�����ﳵ
   	var winname=null;
   	function showcart(id){
	//����XMLHttpRequest����
		try{
			ajax4=new XMLHttpRequest();
		}catch(e){
			try{
				ajax4=new ActiveXObject("MSXML2.XMLHTTP");
			}catch(e1){
				try{
					ajax4=new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e2){
					return;
				}
			}
		}
		ajax4.open("get","cart.jsp");
		ajax4.onreadystatechange=function(){
			var state=ajax4.readyState;
			if(state==4){
				var sta=ajax4.status;//���������ص���Ӧ��
				alert(sta);
				if(sta==200){
					var url="cart.jsp?id="+id+"&fg="+new Date();
					if(winname==null || winname.closed){ 
			  		winname=window.showModalDialog(url,null,"width=600;height=700");
					winname.name="cart";
	    		}else{	    		
   					var aj;	    		
					try{
	    				aj=new XMLHttpRequest();
	    			}catch(e){
				    	try{
				    		aj=new ActiveXObject("MSXML2.XMLHTTP");
				    	}catch(e1){
				    		try{
				    			aj=new ActiveXObject("Microsoft.XMLHTTP");
				    		}catch(e2){}
				    	}
				    }
					aj.open("post",url);	
					aj.onreadystatechange=function(){ 				   		
	    			if(aj.readyState==4){
	    				if(aj.status==200){
    						var dat=aj.responseText;
    						winname.document.all.shopcart.innerHTML=dat;
    						winname.focus();    					
    					}
    				}
    				};
	    			aj.send(null); 
	    		}
			}
		}
	};
	request.send(null);
	//
	}
	//��ѯ������Ʒ������������������Ϣ�ۼ򵥲�ѯ��
   	var queryproduct;
   	function query(){
   		var url="listproductbytype.jsp?typeid="+document.all.productype.value+
   		"&cond="+document.all.querycondition.value;
   		window.open(url,"_self");
    }
   	function openselfinfo(proName){
		window.showModalDialog(proName+"/userinfo/userinfomodify.jsp", "","dialogWidth=100,dialogHeight=150,center=yes,sroll=no");
}

	