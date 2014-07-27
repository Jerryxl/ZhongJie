<%@ page language="java" import="java.util.*,com.zj.service.GeneralTechService,com.zj.vo.GeneralVO" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    <title>技术工艺列表</title>
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
     
     
     <script src="js/addLine.js" type="text/javascript"></script>
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
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    
    <script type="text/javascript">
    function deleteInfo(tid){
    var data="tid="+tid;
      $.ajax({
			url : 'typenagement/deleteTechAction',
			type : 'post', //POST方式发送数据  
			async : false,
			data:data,
			datatype : 'json',
			success : function(data) {
			if(data.result=='1'){
			window.location.reload();
			alert('Delete Succese');
			}
			}
		});
    }
    </script>
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
            <li><a hre="list.jsp">工艺列表(修改、删除)</a></li>
            <li ><a href="quyu.jsp">区域信息录入</a></li>
            <li ><a href="typeManage.html">类别系管理</a></li>
            
        </u>
    </div>
    
    

    
    <div class="content">
        <div class="header">      
                 <div class="well">
   <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover table-condensed datatable" id="example">
      <thead>
        <tr>
          <th>工艺名称</th>
          <th style="width: 56px;">操作</th>
        </tr>
      </thead>
      <tbody>
      <%
      GeneralTechService service=new GeneralTechService(); 
      List<GeneralVO> list=service.getVOs();
      if(list.size()>0){
      for(GeneralVO vo:list){
      String id=vo.getId();
      %>
        <tr>
        <td><%=vo.getTechname() %></td>
          <td>
              <a href="modify.jsp?tid=<%=id%>"><i class="icon-pencil"></i></a>
              <a href="#" onClick="deleteInfo('<%=id%>')"  data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
        <%}} %>
      </tbody>
    </table>
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
