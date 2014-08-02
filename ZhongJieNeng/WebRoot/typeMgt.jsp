<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <head>
    <meta charset="utf-8">
     <base href="<%=basePath%>">
    <title>类别管理</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">
    <link href="lib/bootstrap/css/datepicker.css" rel="stylesheet" type="text/css">
    <link href="lib/bootstrap/css/DT_bootstra.css" type="text/css" rel="stylesheet">
    
     <!-- 时间控件所需 -->
    <script src="lib/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- datatable控件所需 -->
		<!-- datatables -->
	<script type="text/javascript" src="lib/jquery/jquery.dataTables.js"></script>
    <script type="text/javascript" src="lib/dataTables.bootstrap.js"></script>		
    <!-- datatable控件所需 -->
	<script src="lib/bootstrap/js/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="js/typemanage.js" type="text/javascript"></script>
		
    <script type="text/javascript"  charset="utf-8">
	$(function(){
			loadType();
		});
    </script> 
     <!-- 时间控件所需 -->
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

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
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
                    <li id="fat-menu">
                        <a href="#" role="button"><i class="icon-user"></i> 薛亮</a>
                    </li>
                    <li>
                    <a href="#" role="button">退出登录 </a>
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
           <h1 class="page-title">类别体系管理</h1>
        </div>
        
        <ul class="breadcrumb">
            <li>类别体系管理<span class="divider">/</span></li>
            <li><a href="typeMgt.jsp">类别管理</a><span class="divider">/</span></li>
        </ul>
        <div class="container-fluid">
        <div class="row-fluid">
       <div class="span12">

					  <form  action="<%=basePath%>typenagement/addTypeAction"  method="post">
						<label>上级类别</label>
						<select id="zhtype" name="zhtype"></select>
						<label>类别名称</label>
						<input type="text" id="typeName" name="typeName" /><label id="tiptravel" style=" color:red"></label>
						<label>备注</label>
						<textarea id="typeMemo" name="typeMemo"></textarea>
						<br /> 
 <input type="submit" value="添加" class="btn btn-primary" >
 <input type="button" value="取消" onclick="history.go(-1)" class="btn btn-primary" />
					</form>

				</div>
        </div>
    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
					$("[rel=tooltip]").tooltip();
					$(function() {
						$('.demo-cancel-click').click(function() {
							return false;
						});
					});
				</script>
    
  </body>
</html>
