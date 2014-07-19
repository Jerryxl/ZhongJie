var yeardatatable;
var seasondatatable;
var monthdatatable;
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
			"bAutoWidth": "false",
		"aoColumns": [   
         	{ "sTitle": "城市 "},   
         	{ "sTitle": "成功交易单数" },   
         	{ "sTitle": "成功交易收入"},
         	{ "sTitle": "异常交易单数"},   
         	{ "sTitle": "失败交易亏损"},
         	{ "sTitle": "总业务收入"}
         ]
		});
	$('.dataTables_length select').addClass('span4');
    $('.dataTables_filter input').addClass('span9 search-query');
    $('.DTTT_container').addClass('btn-group');
	}
	
function calSeason(){
	var season = $("#season").val();
	//alert(season);
}