
//jquery获取复选框的值
function setPower(){
	   var power =[];    
	  $('input[name="power"]:checked').each(function(){    
		   power.push($(this).val());    
	  });    
 	if(power.length==0){
		alert("您没有选择任何的内容.");
	}else{
		alert(power);
	}
}
  
  
function power(id){
		//$('input[name="power"]').removeAttr("checked");
		//$('input[name=power]').attr("checked",null);
		//$("[name='power']").removeAttr("checked");//取消全选 
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/authorizationAction_getAuthorization.action',
				params: "id=" + id,
				type:"json",
	    		callback:function(data) {
				//	$('input[name="power"]').removeAttr("checked");//取消全选
					if(data.typeSet!=0){
						$("#typeSet").attr("checked","checked");
					}
					if(data.bookSet!=0){
						$("#bookSet").attr("checked","checked");
					}

					if(data.readerSet!=0){
						$("#readerSet").attr("checked","checked");
					}
					if(data.borrowSet!=0){
						$("#borrowSet").attr("checked","checked");
					}
					if(data.backSet!=0){
						$("#backSet").attr("checked","checked");
					}	
					if(data.sysSet!=0){
						$("#sysSet").attr("checked","checked");
					}		
				}
			}
			   
    	);
	
}

$(function () {
	//重新加载使得复选框的勾选状态正常显示
$('#powerModal').on('hide.bs.modal',function() {//权限选择模糊框隐藏时候触发
       location.reload();  	//刷新当前页面
 });
});

