<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    <title>区域问题</title>
     <!--<meta charset="utf-8">  -->
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>
    
    <!-- datatable控件所需 -->
		<!-- datatables -->
	<script type="text/javascript" src="lib/jquery.dataTables.js"></script>
    <script type="text/javascript" src="lib/dataTables.bootstrap.js"></script>		
    <!-- datatable控件所需 -->
	<script src="lib/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
     
     <!--富文本编辑器-->
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>
    <script>
    
	
	  $(function(){
	       CKEDITOR.replace("shortmessage");
		   CKEDITOR.replace("detailmessage");
		   loadType();
		   loadTechType();
		  })	 
				</script>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
   <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> 薛亮
                            <i class="icon-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">我的账户</a></li>
                            <li class="divider"></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="sign-in.html">退出</a></li>
                        </ul>
                    </li>
                    
                </ul>
               <a class="brand" href="#"><img src="images/logo_water.jpg" width="100" height="50" alt="Water Res">&nbsp;<span class="first">WaterRes</span> </a>
        </div>
    </div>
    
    
    <div class="sidebar-nav">
       &nbsp;
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>内容管理<i class="icon-chevron-up"></i></a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
             <li><a href="modifyInfo.jsp">工艺信息录入</a></li>
            <li><a href="list.jsp">工艺列表(修改、删除)</a></li>
            <li ><a href="quyu.jsp">区域信息录入</a></li>
             <li ><a href="typeMgt.jsp">污染物类别体系管理</a></li>
            <li ><a href="techtypeMgt.jsp">工艺类别体系管理</a></li>
        </u>
    </div>
    

    
    <div class="content">
        
        <div class="header">
            
            <h1 class="page-title">区域问题管理</h1>
        </div>
        
                <ul class="breadcrumb">
                  <li>区域环境信息管理</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
    <!--下面开始工艺原理的上传操作-->
		      <form  action="<%=basePath%>typenagement/addAreaProblemAction"  method="post" id="upmediaform">
       			<p>
				     <label>信息类别</label>
					 <select id="infotype" name="infotype">
					 
					    <option value="1">环境污染现状描述</option>
						<option value="2">省份主要问题描述</option>
						<option value="3">治理技术介绍</option>
					 </select>
				</p>
				<p>
				     <label>环境问题省份</label>
					 <select id="province" name="areaid">
					      <option value="00">东北区</option>
						 <option value="hei">黑龙江省</option>
						 <option value="ji">吉林省</option>
						 <option value="liao">辽宁省</option>
						 <option value="ha">哈尔滨</option>
						 <option value="01">华东区</option>
						 <option value="jiangsu">江苏省</option>
						 <option value="zhejiang">浙江省</option>
						 <option value="anhui">安徽省</option>
						 <option value="fujian">福建省</option>
						 <option value="shandong">山东省</option>
						 <option value="shanhai">上海市</option>
						 <option value="02">华南区</option>
						 <option value="guangdong">广东省</option>
						 <option value="guangxi">广西省</option>
						 <option value="hainan">海南省</option>
						 <option value="03">华中区</option>
						 <option value="hubei">湖北省</option>
						 <option value="hunan">湖南省</option>
						 <option value="henan">河南省</option>
						 <option value="jiangxi">江西省</option>
						 <option value="04">华北区</option>
						 <option value="beijing">北京市</option>
						 <option value="tianjin">天津市</option>
						 <option value="hebei">河北省</option>
						 <option value="shanxi">山西省</option>
						 <option value="neimenggu">内蒙古省</option>
						 <option value="05">西北区</option>
						 <option value="ningxia">宁夏省</option>
						 <option value="xinjiang">新疆省</option>
						 <option value="qinghai">青海省</option>
						 <option value="shanxi2">陕西省</option>
						 <option value="gansu">甘肃省</option>
						 <option value="06">西南区</option>
						 <option value="sichuan">四川省</option>
						 <option value="yunnan">云南省</option>
						 <option value="guizhou">贵州省</option>
						 <option value="xizang">西藏省</option>
						 <option value="chongqing">重庆市</option>
						 
					</select>
				</p>
				<p>
				     <label>对应环境类型</label>
					 <select id="envtype" name="envtype">
						 <option value="water">水环境</option>
						 <option value="air">大气环境</option>
						 <option value="soil">固体废弃物与土壤</option>
						 <option value="noise"> 噪声</option>
					 </select>
				</p>
                <p>
                    <label>标题</label>
                     <input type="text" class="span12" id="title" name="title">
               </p>
			    <p>
                     <label>简介</label>
                     <textarea class="span12" rows="5" id="shortmessage" name="shortmessage"></textarea>
               </p>
			   <p>
                     <label>详述</label>
                     <textarea class="span12" rows="5" id="detailmessage" name="detailmessage"></textarea>
               </p>
                    <input type="submit" value="保存" class="btn btn-primary">
				    
			   </form>
            </div>
<div id="userstable">                    
<div class="btn-toolbar">
  <div class="btn-group">
  </div>
</div>

</div>
       
                 
                    
            </div>
        </div>
    
    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>
