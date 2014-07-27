// JavaScript Document
var typeList;//污染物类别
var techtypeList;//技术分类的类别
var index=1;
//加载所有站点
function loadType(){
	$.ajax({
		url: 'typenagement/typeloadAction',
		type: 'post',      //POST方式发送数据  
	    async: false,
		datatype : 'json',
		success:function(data){
			typeList=data.data;
			$('#zhtype').attr('onChange','filtertech()');
		}
	});
}

function loadTechType(){
	$.ajax({
		url: 'typenagement/techtypeloadAction',
		type: 'post',      //POST方式发送数据  
	    async: false,
		datatype : 'json',
		success:function(data){
			techtypeList=data.data;
		}
	});
}
//过滤技术类别
function filtertech(){
	var typenumber = $('#zhtype').val();
	//先清空
	$("#techtype").empty();
	var option = $('<option>').text("无").val("00");
	$('#techtype').append(option);
	
	for( var i=0;i<techtypeList.length;i++){
		if(techtypeList[i].value!="00"&&techtypeList[i].value.substr(0,techtypeList[i].value.indexOf("_"))==typenumber){
			var option = $('<option>').text(techtypeList[i].name).val(techtypeList[i].value);
			$('#techtype').append(option);
		}
	}
}