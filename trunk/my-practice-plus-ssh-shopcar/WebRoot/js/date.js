	var date1=new Date();
		var HH=date1.getHours();
		var aa;
	if(HH<6){
				aa="�賿";
		}else if(HH<12)
			{
					aa="����";	
				}else if(HH<14)
				{
						aa="����";
					}else if(HH<18)
						{
								aa="����";
							}else
								{
									aa="����";
									}
		document.writeln(aa+"�ã���ӭ���ٱ�վ��");
		var date=new Date();
		var YY=date.getYear();
		var MM=date.getMonth()+1;
		var DD=date.getDate();
		var KK=date.getDay();
		var HH=date.getHours();
		var mm=date.getMinutes();
		//var SS=date.getSeconds();
		if(MM<10)MM="0"+MM;
		if(DD<10)DD="0"+DD;
		if(HH<10)HH="0"+HH;
		if(mm<10)mm="0"+mm;
		//if(SS<10)SS="0"+SS;
		switch(KK)
		{
			case 1:
			KK="����һ";
			break;
			case 2:
			KK="���ڶ�";
			break;
			case 3:
			KK="������";
			break;
			case 4:
			KK="������";
			break;
			case 5:
			KK="������";
			break;
			case 6:
			KK="������";
			break;
			case 0:
			KK="������";
			break;
			
			}
				document.writeln("&nbsp;&nbsp;"+YY+"��"+MM+"��"+DD+"�� "+KK+" "+HH+"��"+mm)	;	