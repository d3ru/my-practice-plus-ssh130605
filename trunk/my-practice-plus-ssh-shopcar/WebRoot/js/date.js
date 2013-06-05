	var date1=new Date();
		var HH=date1.getHours();
		var aa;
	if(HH<6){
				aa="凌晨";
		}else if(HH<12)
			{
					aa="上午";	
				}else if(HH<14)
				{
						aa="中午";
					}else if(HH<18)
						{
								aa="下午";
							}else
								{
									aa="晚上";
									}
		document.writeln(aa+"好，欢迎光临本站！");
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
			KK="星期一";
			break;
			case 2:
			KK="星期二";
			break;
			case 3:
			KK="星期三";
			break;
			case 4:
			KK="星期四";
			break;
			case 5:
			KK="星期五";
			break;
			case 6:
			KK="星期六";
			break;
			case 0:
			KK="星期日";
			break;
			
			}
				document.writeln("&nbsp;&nbsp;"+YY+"年"+MM+"月"+DD+"日 "+KK+" "+HH+"∶"+mm)	;	