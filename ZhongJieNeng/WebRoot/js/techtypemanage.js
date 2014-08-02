// JavaScript Document
var typeList;//污染物类别
var techtypeList///工艺类别
function loadType(){
	$.ajax({
		url: 'typenagement/typeloadAction',
		type: 'post',      //POST方式发送数据  
	    async: false,
		datatype : 'json',
		success:function(data){
			typeList=data.data;
			$("#zhtype").empty();
			for (var i = 0; i < typeList.length; i++){
				var option = $('<option>').text(typeList[i].name).val(typeList[i].value);
				$('#zhtype').append(option);
			}
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
			$("#techtype").empty();
			for (var i = 0; i < techtypeList.length; i++){
				var option = $('<option>').text(techtypeList[i].name).val(techtypeList[i].value);
				$('#techtype').append(option);
			}
		}
	});
}