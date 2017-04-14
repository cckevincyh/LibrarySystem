function addBookNum(id,isbn){
	
	$("#addBookNumId").val(id);
	$("#addBookNumISBN").val(isbn);
}


$(function () {
	

    $('#add_BookNum').click(function () {

	var postdata = "bookId="+$.trim($("#addBookNumId").val())+"&num="+$.trim($("#addBookNum").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/bookManageAction_addBookNum.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#addNumModal").modal("hide");//关闭模糊框		
						showInfo("新增成功");	

	                }else {
						$("#addNumModal").modal("hide");//关闭模糊框
						showInfo("新增失败");
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
