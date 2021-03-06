     var citydatastore;  
	 var cityfirstmenu;
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
        var point = new BMap.Point(126.657717,45.773224);//定义一个中心点坐标
        map.centerAndZoom(point,11);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
		setTimeout(function(){ getBoundary();}, 10);
    }
    
    function getBoundary(){       
    var bdary = new BMap.Boundary();
    bdary.get("哈尔滨", function(rs){       //获取行政区域
       // map.clearOverlays();        //清除地图覆盖物       
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 3}); //建立多边形覆盖物
            map.addOverlay(ply);  //添加覆盖物
            map.setViewport(ply.getPath());    //调整视野         
        }                
    });   
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
	      {title:"哈尔滨",content:"哈尔滨市环境保护整体解决方案",url:"#showmessage",point:"126.657717|45.773224",isOpen:0,icon:{pic:"images/labels/marker_green.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"北京",content:"北京市环境保护整体解决方案",url:"#showmessage",point:"116.403874|39.914889",isOpen:0,icon:{pic:"images/labels/marker_green.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"上海",content:"上海市环境保护整体解决方案",url:"#showmessage",point:"121.487899|31.249162",isOpen:0,icon:{pic:"images/labels/marker_green.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"西安",content:"西安市环境保护整体解决方案",url:"#showmessage",point:"108.953098|34.2778",isOpen:0,icon:{pic:"images/labels/marker_green.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"重庆",content:"重庆市环境保护整体解决方案",url:"#showmessage",point:"106.530635|29.544606",isOpen:0,icon:{pic:"images/labels/marker_green.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
		 ,{title:"昆明",content:"昆明市环境保护整体解决方案",url:"#showmessage",point:"102.714601|25.049153",isOpen:0,icon:{pic:"images/labels/marker_green.png",w:21,h:21,l:0,t:0,x:6,lb:5}}
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
						background:"#3189f3",
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
        var iw = new BMap.InfoWindow("<b class='title' title='" + json.title + "'>试点城市：" + json.title + "</b><div class='entry-body'>"+json.content+"<p><span class='data' > 详细信息<a href='#' onClick="+"showDetailtop('"+json.url+"')"+">点击查看</a></span></p></div>");
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
        title : '试点问题列表',
		onClosed:closeAndClear(org)//退出时，原来的内容还原
    });
	}
	
	function showDetailtop(locationAddress){
		var message =cityfirstmenu;//这里暂时
		var org=message;
		$("#showmessage").html(message);
		$.fancybox({
        href: '#showmessage',
        title : '试点问题展示',
		onClosed:closeAndClear(org)//退出时，原来的内容还原
    });
	}
	
	function closeAndClear(message){
		$("#showmessage").html(message);
	}

function initShow(){
		var txt="<h3 class=\"widget-title\">哈尔滨市环境保护整体解决方案</h3><ul>";
		var fg1;
		var fg2;
		var fg3;
		for(var i=0;i<citydatastore.length;i++){
		    if(citydatastore[i].infotype=='1'){
			  fg1='1';
		    }
			 if(citydatastore[i].infotype=='2'){
			  fg2='1';
		    }
			 if(citydatastore[i].infotype=='3'){
			  fg3='1';
		    } 
		}                      
		if(fg1=='1'){
		   txt=txt+"<li><a href='#' onClick=\"showfirstlevel('1')\">"+"环境污染现状"+"</a></li>";
		}
		if(fg2=='1'){
			txt=txt+"<li><a href='#' onClick=\"showfirstlevel('2')\">"+"主要污染问题"+"</a></li>";
		}
		if(fg3=='1'){
			txt=txt+"<li><a href='#' onClick=\"showfirstlevel('3')\">"+"综合治理技术示范"+"</a></li>";
		}
		txt=txt+"</u>";
		$("#showmessage").html(txt);
		cityfirstmenu=txt;
	}
	function showfirstlevel(level){
		//水气声渣
		if(level=='1'){
				 var txt="<h3 class=\"widget-title\">环境污染现状</h3><ul>";
				 txt=txt+"<li><a href='#' onClick=\"showplace('water','harbin')\">水环境</a></li>";    
				 txt=txt+"<li><a href='#' onClick=\"showplace('air','harbin')\">大气环境</a></li>";
				 txt=txt+"<li><a href='#' onClick=\"showplace('soil','harbin')\">固体废弃物与土壤</a></li>";
				 txt=txt+"<li><a href='#' onClick=\"showplace('noise','harbin')\">噪声</a></li>";
				 txt=txt+"</u>";
				 $("#showmessage").html(txt);
				 showDetail('a');
		}else if(level=='2'){
				 var txt="<h3 class=\"widget-title\">主要污染问题</h3><ul>";
				   for(var i=0;i<citydatastore.length;i++){
					   if(citydatastore[i].infotype=='2'){
						   txt=txt+"<li><a href='#' onClick=\"showproblem('"+citydatastore[i].number+"')\">"+citydatastore[i].title+"</a></li>";
					   }	
				   }
				 txt=txt+"</u>";
				 $("#showmessage").html(txt);
				 showDetail('a');			
		}else if(level=='3'){
				 var txt="<h3 class=\"widget-title\">综合治理技术示范</h3><ul>";
				   for(var i=0;i<citydatastore.length;i++){
					   if(citydatastore[i].infotype=='3'){
						   txt=txt+"<li><a href='#' onClick=\"showproblem('"+citydatastore[i].number+"')\">"+citydatastore[i].title+"</a></li>";
					   }	
				   }
				 txt=txt+"</u>";
				 $("#showmessage").html(txt);
				 showDetail('a');		
		}
	}

	//最底层的展示
	function showproblem(number){
			for(var i=0;i<citydatastore.length;i++){
				if(citydatastore[i].number==number){
					 $("#showmessage").html(citydatastore[i].detailmessage);
					  showDetail('a');	
				}
			}
	}

		function lodataData(){
		$.ajax({
			url : '../typenagement/loadProblemsByCity',
			type : 'post', //POST方式发送数据  
			async : false,
			datatype : 'json',
			success : function(data) {
				citydatastore = data.data;
				//alert(citydatastore);
				initShow();
			}
		});
	}
		
		function showplace(type,place){
			var name='';
			if(type=='water'){name='水环境'}
			if(type=='air'){name='大气环境'}
			if(type=='soil'){name='固体废弃物与土壤'}
			if(type=='noise'){name='噪声'}
			var area='哈尔滨';
			var txt="<h3 class=\"widget-title\">"+area+name+"问题</h3><ul>";
			for(var i=0;i<citydatastore.length;i++){
				if(citydatastore[i].envtype==type&&citydatastore[i].areaid==place){
					  txt=txt+"<li><a href='#' onClick=\"showproblem('"+citydatastore[i].number+"')\">"+citydatastore[i].title+"</a></li>";
				}
			}
			txt=txt+"</u>";
			$("#showmessage").html(txt);
			showDetail('a');
					
		}
					