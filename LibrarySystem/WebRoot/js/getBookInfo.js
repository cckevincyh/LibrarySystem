
$(function () {
	

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function getBookInfo(id){
		
	ajax(
		  {
		  	method:'POST',
    		url:'admin/bookManageAction_getBook.action',
			params: "bookId=" + id,
			type:"json",
    		callback:function(data) {
				$("#findBookId").val(data.bookId);
				$("#findBookName").val(data.bookName);
				$("#findBookType").val(data.bookType.typeName);
				$("#findAutho").val(data.autho);
				$("#findPress").val(data.press);
				$("#findPrice").val(data.price);
				$("#findDescription").val(data.description);
				$("#findNum").val(data.num);
				$("#findCurrentNum").val(data.currentNum);
			}
		}
										   
							    
						
	);	
	
	
	
	
	
			

}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


