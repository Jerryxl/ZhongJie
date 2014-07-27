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
			$("#zhtype").empty();
			for (var i = 0; i < typeList.length; i++){
				var option = $('<option>').text(typeList[i].name).val(typeList[i].value);
				$('#zhtype').append(option);
			}
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
				var option = $('<option>').text("无").val("00");
				$('#techtype').append(option);
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
function addSubDiv(){
	alert('assss');
	var newP = $('<p>');
	index=index+1;
	newP.append('<label>请选择示范工程图片'+index+'：</label>');
	newP.append('<input type="file"  name="image" class="span12"/>');
	newP.append('<label>图片简要描述：</label>');
	newP.append('<input type="text" name="imagedescribtion" value="图片'+index+'简要说明" class="span12"/>');
	newP.append('<p>');
	$("#firstimagediv").append(newP);
	alert($("#firstimagediv").html());
}
function submitUp(){
	$("#upmediaform").submit();
}