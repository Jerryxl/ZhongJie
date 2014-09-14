<%@ page language="java" import="java.util.*,com.zj.service.*,com.zj.vo.*,com.zj.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String tid=request.getParameter("tid");
GeneralTechService service=new GeneralTechService();
GeneralVO vo=service.getById(tid);
TechTypeService typeservice=new TechTypeService();
TypeService type=new TypeService();
List<TechTypeVO> techlist=typeservice.getAllType();
List<TypeVO> list=type.getTypes();

%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    <title>增加技术工艺</title>
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
     
     
     <script src="js/modify.js" type="text/javascript"></script>
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
		   CKEDITOR.replace("usescope");
		   CKEDITOR.replace("advancedesc");
		   CKEDITOR.replace("appdesc");
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
                  
            <h1 class="page-title">新增技术工艺</h1>
        </div>
        
                <ul class="breadcrumb">
                  <li>修改工艺</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
             
    <!--下面开始工艺原理的上传操作-->
		      <form  action="<%=basePath%>typenagement/modifyPictureAction"  enctype="multipart/form-data" method="post">
		      <input type="hidden" id="tid" name="tid" value="<%=vo.getId()%>"/>
       			<p>
				     <label>污染物类别体系</label>
					 <select id="zhtype" name="zhtype">
					 <% 
					 for(TypeVO tvo:list){
					 if(tvo.getNumber().equals(vo.getZhtype())){
					 %>
					  <option value="<%=tvo.getNumber()%>" selected="selected"><%=tvo.getName() %></option>
					 <%
					 }else{ 
					 %>
					 <option value="<%=tvo.getNumber()%>"><%=tvo.getName() %></option>
					 <%
					 }}
					 %>
					 </select>
				</p>
				<p>
				     <label>工艺技术分类</label>
					 <select id="techtype" name="techtype">
					 <%
					 for(TechTypeVO tvo:techlist){
					 if(vo.getTechtype().equals(tvo.getNumber())){
					 %>
					 <option value="<%=tvo.getNumber()%>" selected="selected"><%=tvo.getName()%></option>
					 <%
					 }
					 else{%>
					  <option value="<%=tvo.getNumber()%>"><%=tvo.getName()%></option>
					 <%
					 
					 }}
					  %>
					 </select>
				</p>
                <p>
                    <label>工艺名称</label>
                     <input type="text" class="span12" id="techname" name="techname" value="<%=vo.getTechname()%>">
               </p>
           
				
				  
						
				   </div>
				   <!-- 
				  <p>
					 <a href="#" id="goonaddimage"class="btn btn-primary" onClick="addSubDiv()">继续添加</a>
				  </p>
				   -->

				  <p>
					<label>请选择工艺原理视频：</label>
					<input type="file"  name="tcflash" class="span12" value=<%=vo.getTcflash() %>/>   

			      </p>

				   <p>
						<label>请选择技术路线视频：</label>
						<input type="file"  name="pathflash" class="span12" value=<%=vo.getPathflash() %>/>   

				    </p>
                <p>
                    <label>工艺名称</label>
                     <input type="text" class="span12" id="techname" name="techname" value=<%=vo.getTechname() %>>
               </p>
			   <p>
                     <label>工艺简介</label>
                     <textarea class="span12" rows="5" id="shortmessage" name="shortmessage"><%=vo.getShortmessage() %></textarea>
               </p>

               <p>
                     <label>适用范围介绍</label>
                     <textarea class="span12" rows="5" id="usescope" name="usescope"><%=vo.getUsescope() %></textarea> 
              </p>
               <p>
                     <label>先进性介绍</label>
                     <textarea class="span12" rows="5" id="advancedesc" name="advancedesc"><%=vo.getAdvancedesc() %></textarea>
               </p>
			    <p>
                     <label>应用情况介绍</label>
                     <textarea class="span12" rows="5" id="appdesc" name="appdesc"><%=vo.getAppdesc() %></textarea>
               </p>
			   <p>
                     <label>工艺详述</label>
                     <textarea class="span12" rows="5" id="detailmessage" name="detailmessage"><%=vo.getDetailmessage() %></textarea>
               </p>
                    <input type="submit" value="保存" class="btn btn-primary"><input type="button" value="取消" class="btn btn-primary" onclick="history.go(-1)">
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
