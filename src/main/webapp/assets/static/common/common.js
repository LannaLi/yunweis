/**
 * 该处为公共方法
 * @returns
 */

function createForm(config){
	if (config == null) {
		throw "传递过来的参数为空";
	}
	if (typeof config == undefined) {
		throw "传递过来的参数未定义";
	}
	var form = $('<form class="form-horizontal" role="form"></form>');
	return form;
}

/**
 * 验证唯一性
 * @param param 参数 格式为{'url':url,'username':username}
 * @returns
 * @author Lanna
 */
function validUniq(param){
	
}


/**
 * 验证合法性
 * @param
 * @returns
 * @author Lanna
 */
function validLega(s){
	
}

