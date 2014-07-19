// JavaScript Document
/*
 *加载时更新home页面中frame的高度
*/
function IFrameResize() {
        //var obj = parent.document.getElementById("iresult");  //取得父页面IFrame对象    
        //obj.height = this.document.body.scrollHeight - 61;  //调整父页面中IFrame的高度为此页面的高度  
		var myHeight = this.document.body.scrollHeight - 61;
		$('#iresult',window.parent.document).attr('height',myHeight);
    } 
/*
 *左侧导航栏点击切换
*/
function tabRight(address){
	$('#iresult').attr('src',address);
}