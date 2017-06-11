$(function () {
	

    $('#batchAdd').click(function () {
    	
    	 if (!validBatchAddReader()) {
    	        return;
    	    }
			var postdata = "fileName="+$.trim($("#excel").val());
			ajax(
		    		  {
					  	method:'POST',
			    		url:'admin/readerManageAction_batchAddReader.action',
						params: postdata,
			    		callback:function(data) {
							if (data == 1) {
								$("#batchAddModal").modal("hide");//关闭模糊框		
								showInfo("批量添加成功");	
		
			                }else {
								$("#batchAddModal").modal("hide");//关闭模糊框
								showInfo("批量添加失败");
							}
										
						}
					}
					   
		    	);
					
				
			});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function validBatchAddReader() {
    var flag = true;
    var upload = $.trim($("#upload").val());
    if (upload == "") {
       alert("请选择excel文件");
        flag = false;
    } 
    
    
    return flag;
}


function checkFileExt(filename)
{
	 var flag = false; //状态
	 var arr = ["xls"];
	 //取出上传文件的扩展名
	 var index = filename.lastIndexOf(".");
	 var ext = filename.substr(index+1);
	 //循环比较
	 for(var i=0;i<arr.length;i++)
	 {
		 if(ext == arr[i])
		 {
			  flag = true; //一旦找到合适的，立即退出循环
			  break;
		 }
	 }
	return flag;
}


function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


