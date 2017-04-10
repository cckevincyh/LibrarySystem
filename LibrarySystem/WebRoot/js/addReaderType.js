
$(function () {
	

    $('#addReaderType').click(function () {

	var postdata = "readerTypeName="+$.trim($("#addType").val())+"&maxNum="+$.trim($("#addMaxNum").val())+"&bday="+ $.trim($("#addBday").val())+"&penalty="+ $.trim($("#addPenalty").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/readerTypeManageAction_addReaderType.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("添加成功");	

	                }else {
						$("#addModal").modal("hide");//关闭模糊框
						showInfo("添加失败");
					}
								
				}
			}
			   
    	);
			
		
	});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});










function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


