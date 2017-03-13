
/**
 * ajax提交添加图书分类的信息
 * @param {Object} '#addBookType'
 */
$(function () {
	

    $('#addBookType').click(function () {

	var postdata = "typeName="+$.trim($("#addBookTypeName").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/bookTypeManageAction_addBookType.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("添加成功");	

	                }else if (data == -1) {
						$("#addModal").modal("hide");//关闭模糊框		
						showInfo("该图书分类存在");	
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


