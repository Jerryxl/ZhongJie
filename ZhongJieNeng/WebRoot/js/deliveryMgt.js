// JavaScript Document
function initDatatable(){
		$('#ordertable').dataTable( {
			"oLanguage" : {
			"sLengthMenu": "每页显示 _MENU_ 条记录",
			"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条数据",
			"sInfoEmpty": "没有数据",
			"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
			"sZeroRecords": "无相关数据",
		    "sProcessing": "正在加载中...",
			//"sSearch": "搜索",
			"oPaginate": {
			"sFirst": "首页",
			"sPrevious": "前一页",
			"sNext": "后一页",
			"sLast": "尾页"
			}				
		},
			"bAutoWidth": "false",
			"aLengthMenu": [10, 20],
		"aoColumns": [   
			{ "sTitle": "选择 ",
			"fnRender":function(obj,value){
				var sReturn;
				sReturn = '<input type="checkbox" value="" />';
				return sReturn;}}, 
		    { "sTitle": "车牌号 "}, 
         	{ "sTitle": "车型 "},   
         	{ "sTitle": "目的地" },   
         	{ "sTitle": "当前载重(kg)"},
         	{ "sTitle": "当前剩余空间(m3)"}, 
         	{ "sTitle": "操作",
			"fnRender":function(obj, value){
				var sReturn;
				sReturn = '<a href="#.html">发车</a>';
				return sReturn;}
				}
         ]
		});
	$('.dataTables_length select').addClass('span4');
    $('.dataTables_filter input').addClass('span9 search-query');
	}