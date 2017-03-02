/**
 * 获取需要修改用户信息
 * @param {Object} username 需要修改的用户名
 */
function updateAdmin(username){
	ajax(
    		  {
			  	method:'POST',
	    		url:'admin/adminManageAction_getAdmin.action',
				params: "username=" + username,
				type:"json",
	    		callback:function(data) {
					$("#name1").val(data.name);
					$("#password1").val(data.pwd);
					$("#phone1").val(data.phone);
								
				}
			}
			   
    	);
			

}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


