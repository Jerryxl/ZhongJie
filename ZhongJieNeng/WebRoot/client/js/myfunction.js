	
	 var menu;//菜单
	 var showdata;//弹出层中要显示的富文本内容
	 var backup;//备份默认的
	 var dataStore;//多个文本的数组
	 /*
	     多媒体播放函数
	  *  Media helper. Group items, disable animations, hide arrows, enable media and button helpers.
	     用来展示flash 格式是swf
		 param:url flash地址
	  */
		function showVideo(url){
			$(".video-icon").fancybox({
			maxWidth	: 800,
			href        :url,
			//href        :'video/flash2792.swf',//www.adobe.com/jp/events/cs3_web_edition_tour/swfs/perform.swf
			maxHeight	: 600,
			fitToView	: true,
			width		: '70%',
			height		: '70%',
			autoSize	: true,
			closeClick	: true,
			openEffect	: 'none',
			autoPlay    :false,//播放后关闭，true则自动播放
			closeEffect	: 'none'
		});
	}
	
	/***
	  显示简讯，即点击图片或者简述弹出，摘要描述
	  @param flag flag=1表示点击的是图片标题链接
	              flag=2表示点击的是详述按钮
	  @param  urladdress 是信息的url 通过这个url来获取信息
	*/
	function fillMessageDiv(flag,urladdress){
		//先要根据给定的url获取指定的消息
		var message=getMessage(flag,urladdress);
		//var message =$("#showmessage").html();//这里暂时
		$("#showmessage").html(message);
		if(flag=='1'){
			$('.list-meta').fancybox();
		}else if(flag=='2'){
			$('.orange').fancybox();
		}else{
			$('.see').fancybox();
			
			}  		
	}
	
    function getMessage(type,number){
    	if(type=='1'){
    		return dataStore[number].appdesc;
    	}else if(type=='2'){
    		return dataStore[number].shortmessage;
    	}else{
    		return dataStore[number].detail;
    	}
    }
	function loadMenu(){
	$.ajax({
		url: '../typenagement/loadMenuAction',
		type: 'post',      //POST方式发送数据  
	    async: false,
		datatype : 'json',
		success:function(data){
			menu=data.data;
			$("#menucontent").empty();
			$("#menucontent").append(menu);
		}
	});
}
	
	function loadDefaultTech(){
		$.ajax({
			url: '../typenagement/loadDefaultTechAction',
			type: 'post',      //POST方式发送数据  
		    async: false,
			datatype : 'json',
			success:function(data){
				showdata=data.data;
				dataStore=data.array;
				backup=data.data;
				//alert(showdata);
				$("#myshowArea").empty();
				$("#myshowArea").append(showdata);
			}
		});
	}
	
	/**
	 *过滤 type
	 *1 污染物
	 *2 体系
	 *3 工艺
	 * */
	function showmessage(type,message) {
		var data='type='+type+'&number='+message+'&t='+ new Date();
		//alert(data)
		$.ajax({
			url: '../typenagement/FindTechAction',
			type: 'post',      // POST方式发送数据
		    async: false,
			data:data,
			datatype : 'json',
			success:function(data){
				showdata=data.data;
				dataStore=data.array;
				$("#myshowArea").empty();
				$("#myshowArea").append(showdata);
			}
		});
	}
		