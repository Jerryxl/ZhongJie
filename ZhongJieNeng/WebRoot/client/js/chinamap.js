  var datastore;   
  var firstmenu;
//创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker();//向地图中添加marker
        lodataData();
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("map");//在百度地图容器中创建一个地图
        var point = new BMap.Point(104.828941,37.719518);//定义一个中心点坐标
        map.centerAndZoom(point,5);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	map.addControl(ctrl_sca);
    }
    
	//东北43.761111,122.65
	//华东31.866667, 117.291667
	//华北36.822222, 105
	//西南29.647222,98.145833
	//西北38.616667,91.775
	//中南25.477778,108.120833
    //标注点数组
    var markerArr = [
	      {title:"东北地区",content:"东北三省污染状况信息",url:"#showmessage",point:"122.65|43.761111",isOpen:0,icon:{pic:"images/labels/m1.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"华东地区",content:"华东五省污染状况信息",url:"#showmessage",point:"117.291667|31.866667",isOpen:0,icon:{pic:"images/labels/m3.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"华北地区",content:"华北四省污染状况信息",url:"#showmessage",point:"105.523076|36.822222",isOpen:0,icon:{pic:"images/labels/m2.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"西南地区",content:"西南四省污染状况信息",url:"#showmessage",point:"98.145833|29.647222",isOpen:0,icon:{pic:"images/labels/m3.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"西北地区",content:"西北五省污染状况信息",url:"#showmessage",point:"91.775|39.338036",isOpen:0,icon:{pic:"images/labels/m1.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"中南地区",content:"中南三省污染状况信息",url:"#showmessage",point:"109.649033|25.834834",isOpen:0,icon:{pic:"images/labels/m2.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ];
    //创建marker
    function addMarker(){
        for(var i=0;i<markerArr.length;i++){
            var json = markerArr[i];
            var p0 = json.point.split("|")[0];
            var p1 = json.point.split("|")[1];
            var point = new BMap.Point(p0,p1);
			var iconImg = createIcon(json.icon);
            var marker = new BMap.Marker(point,{icon:iconImg});
			var iw = createInfoWindow(i);
			var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
			marker.setLabel(label);
            map.addOverlay(marker);
            label.setStyle({
                        borderColor:"#fe5214",
                        color:"#333",
						background:"#0AA4CB",
                        cursor:"pointer"
            });
			
			(function(){
				var index = i;
				var _iw = createInfoWindow(i);
				var _marker = marker;
				_marker.addEventListener("click",function(){
				    this.openInfoWindow(_iw);
			    });
			    _iw.addEventListener("open",function(){
				    _marker.getLabel().hide();
			    })
			    _iw.addEventListener("close",function(){
				    _marker.getLabel().show();
			    })
				label.addEventListener("click",function(){
				    _marker.openInfoWindow(_iw);
			    })
				if(!!json.isOpen){
					label.hide();
					_marker.openInfoWindow(_iw);
				}
			})()
        }
    }
    //创建InfoWindow，即点击Label时弹出的框
    function createInfoWindow(i){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='title' title='" + json.title + "'>" + json.title + "</b><div class='entry-body'>"+json.content+"<p><span class='data' > 详细信息<a href='#' onClick="+"showDetailtop('"+json.url+"')"+">点击查看</a></span></p></div>");
        return iw;
    }
    //创建一个Icon
    function createIcon(json){
	var myIcon = new BMap.Icon(json.pic, new BMap.Size(35, 30), {    
	// 指定定位位置。   
	// 当标注显示在地图上时，其所指向的地理位置距离图标左上    
	// 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
   // 图标中央下端的尖角位置。    
    offset: new BMap.Size(10, 25)   
   // 设置图片偏移。   
   // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
   // 需要指定大图的偏移位置，此做法与css sprites技术类似。    
   //imageOffset: new BMap.Size(0, 0 - index * 25)   // 设置图片偏移    
 });
 return myIcon;
    }
    initMap();//创建和初始化地图

	/***
	  显示具体信息
	  @param flag flag=1表示点击的是图片标题链接
	              flag=2表示点击的是详述按钮
	  @param  urladdress 是信息的url 通过这个url来获取信息
	*/
	function showDetail(locationAddress){
		//先要根据给定的url获取指定的消息
		//message=getMessage(urladdress);
		var message =$("#showmessage").html();//这里暂时
		var org=message;
		message="<p> 当前位置："+locationAddress+"</p>"+message;
		$("#showmessage").html(message);
		$.fancybox({
        href: '#showmessage',
        title : '区域问题展示',
		onClosed:closeAndClear(org)//退出时，原来的内容还原
    });
	}
	
	function showDetailtop(locationAddress){
		var message =firstmenu;//这里暂时
		var org=message;
		$("#showmessage").html(message);
		$.fancybox({
        href: '#showmessage',
        title : '区域问题展示',
		onClosed:closeAndClear(org)//退出时，原来的内容还原
    });
	}
	
	
	function closeAndClear(message){
		$("#showmessage").html(message);
	}
	
	
	function initShow(){
		var txt="<h3 class=\"widget-title\">东北地区环境保护整体解决方案</h3><ul>";
		var fg1;
		var fg2;
		var fg3;
		for(var i=0;i<datastore.length;i++){
		    if(datastore[i].infotype=='1'){
			  fg1='1';
		    }
			 if(datastore[i].infotype=='2'){
			  fg2='1';
		    }
			 if(datastore[i].infotype=='3'){
			  fg3='1';
		    } 
		}                      
		if(fg1=='1'){
		   txt=txt+"<li><a href='#' onClick=\"showfirstlevel('1')\">"+"环境污染现状"+"</a></li>";
		}
		if(fg2=='1'){
			txt=txt+"<li><a href='#' onClick=\"showfirstlevel('2')\">"+"主要问题描述"+"</a></li>";
		}
		if(fg3=='1'){
			txt=txt+"<li><a href='#' onClick=\"showfirstlevel('3')\">"+"治理技术介绍"+"</a></li>";
		}
		txt=txt+"</u>";
		$("#showmessage").html(txt);
		firstmenu=txt;
		//showDetail('a')
	}

	//
	function showfirstlevel(level){
		//水气声渣
		if(level=='1'){
				 var txt="<h3 class=\"widget-title\">环境污染现状</h3><ul>";
				 txt=txt+"<li><a href='#' onClick=\"showsecondlevel('water','northeast')\">水环境</a></li>";    
				 txt=txt+"<li><a href='#' onClick=\"showsecondlevel('air')\">大气环境</a></li>";
				 txt=txt+"<li><a href='#' onClick=\"showsecondlevel('soil')\">固体废弃物与土壤</a></li>";
				 txt=txt+"<li><a href='#' onClick=\"showsecondlevel('noise')\">噪声</a></li>";
				 txt=txt+"</u>";
				 $("#showmessage").html(txt);
				 showDetail('a');
		}else if(level=='2'){
				 var txt="<h3 class=\"widget-title\">主要问题描述</h3><ul>";
				   for(var i=0;i<datastore.length;i++){
					   if(datastore[i].infotype=='2'){
						   txt=txt+"<li><a href='#' onClick=\"showproblem('"+datastore[i].number+"')\">"+datastore[i].title+"</a></li>";
					   }	
				   }
				 txt=txt+"</u>";
				 $("#showmessage").html(txt);
				 showDetail('a');			
		}else if(level=='3'){
				 var txt="<h3 class=\"widget-title\">治理技术介绍</h3><ul>";
				   for(var i=0;i<datastore.length;i++){
					   if(datastore[i].infotype=='3'){
						   txt=txt+"<li><a href='#' onClick=\"showproblem('"+datastore[i].number+"')\">"+datastore[i].title+"</a></li>";
					   }	
				   }
				 txt=txt+"</u>";
				 $("#showmessage").html(txt);
				 showDetail('a');		
		}
	}

	//最底层的展示
	function showproblem(number){
			for(var i=0;i<datastore.length;i++){
				if(datastore[i].number==number){
					 $("#showmessage").html(datastore[i].detailmessage);
					  showDetail('a');	
				}
			}
	}

	function showsecondlevel(type,area){
				var map = {'northeast':{'hei':'黑龙江','ji':'吉林','liao':'辽宁'}};
				var name='';
				if(type=='water'){name='水环境';}
				if(type=='air'){name='大气环境';}
				if(type=='soil'){name='固体废弃物与土壤';}
				if(type=='noise'){name='噪声';}
	            var txt="<h3 class=\"widget-title\">"+name+"</h3><ul>";
	            for(var p in map[area]){
	            	txt=txt+"<li><a href='#' onClick=\"showplace('"+type+"','"+p+"')\">"+map[area][p]+"</a></li>";   
	            }
//				txt=txt+"<li><a href='#' onClick=\"showplace('"+type+"','hei')\">黑龙江</a></li>";   
//				txt=txt+"<li><a href='#' onClick=\"showplace('"+type+"','ji')\">吉林</a></li>";   
//				txt=txt+"<li><a href='#' onClick=\"showplace('"+type+"','liao')\">辽宁</a></li>";   
				txt=txt+"</u>";
				$("#showmessage").html(txt);
				showDetail('a');
	}
	function lodataData(){
		$.ajax({
			url : '../typenagement/loadAreaProblems',
			type : 'post', //POST方式发送数据  
			async : false,
			datatype : 'json',
			success : function(data) {
				datastore = data.data;
				//alert(datastore);
				initShow();
			}
		});
	}
	
	function showplace(type,place){
		var name='';
		if(type=='water'){name='水环境';}
		if(type=='air'){name='大气环境';}
		if(type=='soil'){name='固体废弃物与土壤';}
		if(type=='noise'){name='噪声';}
		var area='';
		if(place=='hei'){area='黑龙江省';}
		if(place=='ji'){area='吉林省';}
		if(place=='liao'){area='辽宁省';}
		if(place=='jangsu'){area='江苏省';}
		var txt="<h3 class=\"widget-title\">"+area+name+"问题</h3><ul>";
		for(var i=0;i<datastore.length;i++){
			if(datastore[i].envtype==type&&datastore[i].areaid==place){
				  txt=txt+"<li><a href='#' onClick=\"showproblem('"+datastore[i].number+"')\">"+datastore[i].title+"</a></li>";
			}
		}
		txt=txt+"</u>";
		$("#showmessage").html(txt);
		showDetail('a');
				
	}