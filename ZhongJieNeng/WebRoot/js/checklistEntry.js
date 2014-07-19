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
			{ "sTitle": "订单编号 "}, 
		    { "sTitle": "始发站 "}, 
         	{ "sTitle": "终点站 "}, 
         	{ "sTitle": "货物体积(m3)"},
			{ "sTitle": "状态)"},
			{ "sTitle": "操作",
			"fnRender":function(obj, value){
				var sReturn;
				if (value == 1)
				sReturn = '<a href="#.html">异常</a>';
				else 
				sReturn = '';
				return sReturn;}
				}
         ]
		});
	$('.dataTables_length select').addClass('span4');
    $('.dataTables_filter input').addClass('span9 search-query');
	}// JavaScript Document