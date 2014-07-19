  /*$(function(){
        $('#map').gmap3({
          map:{
            options:{
              center:[34.586111,105.7],//加载地图时的中心，大概在甘肃这个位置
              zoom: 4
            }
          },
          marker:{
            values:[
              {latLng:[ 39.914149,116.386756], data:"北京 !",options:{html:'Hello 北京',popup:true}},//options:{icon: "http://maps.google.com/mapfiles/marker_green.png"}
			  {latLng:[43.761111,122.65],data:"东北地区",options:{icon:"images/labels/m1.png"}},
			  {latLng:[31.866667, 117.291667],data:"华东地区",options:{icon:"images/labels/m2.png"}},
			  {latLng:[36.822222, 105],data:"华北地区",options:{icon:"images/labels/m3.png"}},
			  {latLng:[29.647222,98.145833],data:"西南地区",options:{icon:"images/labels/m1.png"}},
		      {latLng:[38.616667,91.775],data:"西北地区",options:{icon:"images/labels/m2.png"}},
			  {latLng:[25.477778,108.120833],data:"中南地区",options:{icon:"images/labels/m3.png"}},
			  
			  {latLng:[45.761111,126.65],data:"哈尔滨",options:{icon:"images/labels/marker_green.png"}},
			  {latLng:[43.844444,126.570833],data:"吉林市",options:{icon:"images/labels/marker_green.png"}},
			  {latLng:[41.805556,123.433333],data:"沈阳市",options:{icon:"images/labels/marker_green.png"}},
			  {latLng:[36.658333,117.016667],data:"济南市",options:{icon:"images/labels/marker_green.png"}}  
            ],
            options:{
              draggable: false
            },
            events:{
              mouseover: function(marker, event, context){
                var map = $(this).gmap3("get"),
                  infowindow = $(this).gmap3({get:{name:"infowindow"}});
                if (infowindow){
                  infowindow.open(map, marker);
                  infowindow.setContent(context.data);
                } else {
                  $(this).gmap3({
                    infowindow:{
                      anchor:marker, 
                      options:{content: context.data}
                    }
                  });
                }
              },
              mouseout: function(){
                var infowindow = $(this).gmap3({get:{name:"infowindow"}});
                if (infowindow){
                  infowindow.close();
                }
              },
			 rightclick:function(map, event){
				var location=event.latLng;
				showDetail(location);
              }
            }
          }
        });
		});
		
	*/	
		
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
        title : '区域问题列表',
		onClosed:closeAndClear(org)//退出时，原来的内容还原
    });
	}
	function closeAndClear(message){
		$("#showmessage").html(message);
	}

	//创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker();//向地图中添加marker
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("map");//在百度地图容器中创建一个地图
        var point = new BMap.Point(103.869395,36.039194);//定义一个中心点坐标
        map.centerAndZoom(point,17);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }