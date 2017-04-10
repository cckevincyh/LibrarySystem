
/**
 * ajax提交修改管理员的信息
 * @param {Object} '#updateReader'
 */
$(function () {
	

    $('#updateReader').click(function () {

	var postdata = "readerId="+$.trim($("#updateReaderID").val())+"&readerType="+$.trim($("#updateReaderType").val())
	+"&name="+$.trim($("#updateName").val())+"&phone="+ $.trim($("#updatePhone").val())+"&pwd="+ $.trim($("#updatePwd").val())+"&email="+ $.trim($("#updateEmail").val())
	+"&paperNO="+ $.trim($("#updatePaperNO").val());
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/readerManageAction_updateReader.action',
				params: postdata,
	    		callback:function(data) {
					if (data == 1) {
						$("#updateModal").modal("hide");//关闭模糊框		
						showInfo("修改成功");	

	                }else {
						$("#updateinfo").modal("hide");//关闭模糊框
	                    showInfo("修改失败");
	                }
								
				}
			}
			   
    	);
			
		
	});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});




/**
 * 显示修改前的数据在输入框中
 * @param {Object} id
 */
function updateReader(id){
		$("#updateReaderType option[value!=-1]").remove();//移除先前的选项
	ajax(
		  {
	    		url:"admin/readerTypeManageAction_getAllReaderTypes.action",
	    		type:"json",
	    		callback:function(data) {
					// 循环遍历每个读者分类，每个名称生成一个option对象，添加到<select>中
					for(var index in data) {
						var op = document.createElement("option");//创建一个指名名称元素
						op.value = data[index].readerTypeId;//设置op的实际值为当前的读者分类编号
						var textNode = document.createTextNode(data[index].readerTypeName);//创建文本节点
						op.appendChild(textNode);//把文本子节点添加到op元素中，指定其显示值
						
						document.getElementById("updateReaderType").appendChild(op);
					}
						ajax(
				    			  {
								  	method:'POST',
						    		url:'admin/readerManageAction_getReader.action',
									params: "readerId=" + id,
									type:"json",
						    		callback:function(data) {
										$("#updateReaderID").val(data.readerId);
										$("#updatePaperNO").val(data.paperNO);
										$("#updateName").val(data.name);
										$("#updateEmail").val(data.email);
										$("#updatePhone").val(data.phone);
										$("#updatePwd").val(data.pwd);
										$("#updateReaderType").val(data.readerType.readerTypeId);
									}
								}
					   
		    			);
				}
				
			
				
				
		   }
	);
	
			

}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


