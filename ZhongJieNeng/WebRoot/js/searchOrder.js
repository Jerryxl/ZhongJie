$(function(){initDate();initDatatable();});
function initDate(){
	    $('#startdate').datepicker({
    format: "yyyy-mm-dd"
    });
	 $('#enddate').datepicker({
    format: "yyyy-mm-dd"
    });
	}
var datatable;
function initDatatable(){
		$('.datatable').dataTable( {
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
		"aoColumns": [   
         	{ "sTitle": "订单号 "},   
         	{ "sTitle": "用户" },   
         	{ "sTitle": "下单时间"},
         	{ "sTitle": "原寄地"},   
         	{ "sTitle": "到达地"},
			{ "sTitle": "订单状态"},
         	{ "sTitle": "订单金额(￥)"},
			{ "sTitle": "操作",
			"sWidth":"10%",
			"fnRender":function(obj, value){
				var sReturn;
				sReturn = '<a href="#.html">查看详情</a>';
				return sReturn;}
				}
         ]
		});
	$('.dataTables_length select').addClass('span4');
    $('.dataTables_filter input').addClass('span9 search-query');
    $('.DTTT_container').addClass('btn-group');
	}
	
//查询订单
function searchOrder(){
	var startdate = $('#startdate').val();
	alert(startdate);
}