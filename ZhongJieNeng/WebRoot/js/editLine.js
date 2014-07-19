// JavaScript Document
var lineIndex = 3;
var stationCount = 2;
function addStation(){
	//alert('add');
	$('#plusStation').append('<div id="mydiv'+lineIndex+'"><label>下一站</label><select id="station'+lineIndex+'" name="station'+lineIndex+'"><option> 沈阳 </option>'
	+'<option> 吉林 </option></select> &nbsp;<a href="#" onClick="deleteStation(this)"><i class="icon-remove"></i></a></div>');
	lineIndex++;
	}
	
function deleteStation(obj){
	$(obj).parent().remove();
	}

function addLine(){
	var stationList = $('select[id^=station]');
	var mtt='';
	for(i=0;i<stationList.length;i++)
	{
		mtt += $(stationList[i]).text() + " = " ;
	}
	}