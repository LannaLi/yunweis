$(document).ready(function(){
	var ztree,
	setting = {
		check:{
			enable: true,
			chkStyle: "checkbox"
		},	
		data : {
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		},
		view:{
			showIcon: false,
			showLine: false
		}
	};
	
	
	$('#userManager').on('click','.btn-createUser',createUser);
	$('#userManager').on('click','.glyphicon-role',showRole);
	$('#roleLayer').on('click','.layer-btn',layerHandler);
	$('#userManager').on('click','.label-handle',stateHandler);
	$('#userManager').on('click','.resetPwd',resetPassword);
	$('#username_tips').hover(
			function(){
				$('#username_tips').tips({
					msg:'长度不能超过12,不能以数字开头,不能含有特殊符号!',
					side:4,
					color:'#FFFFCC',
					time:1,
					bg:'orange'
				});
			},
			function(){
				
			}
	);
	
	
	/**
	 * 重置密码
	 */
	function resetPassword(){
		var symbol = "STATUS";
		var userid = getId($(this),symbol);
		$.post('user/updatePwd.do',{'userid':userid},function(data){
			if (data.state == 1) {
				$('#page-wrapper').load('user/userlist.do');
			} else {
				alert("服务器繁忙,请稍后再试!");
			}
		});
	};
	
	/**
	 * 修改状态
	 */
	function stateHandler(){
		var symbol = "STATUS";
		var userid = getId($(this),symbol);
		var type;
		if ($(this).hasClass('recover')) {
			type = "RECOVER";
		}
		if ($(this).hasClass('freezeup')) {
			type = "FREEZE";
		}
		if ($(this).hasClass('stop')) {
			type = "STOP";
		}
		var url = 'user/updateState.do';
		var param = {'userid':userid,'type':type};
		$.post(url,param,function(data){
			if (data.state == 1) {
				$('#page-wrapper').load('user/userlist.do');
			} else {
				alert("服务器繁忙,请稍后再试!");
			}
		});
	};
	
	function layerHandler(){
		if ($(this).hasClass('btn_cancle')) {
			$('#roleLayer').hide();
			$('#roleTree').empty();
		} else if ($(this).hasClass('btn_confirm')) {
			var symbol = $('#roleTree').data('symbol');
			var url;
			if (symbol === 'INSERT') {
				url = 'perm/addUserRole.do';
			} else if (symbol === 'MODIFY') {
				url = 'perm/updateUserRole.do';
			}
			var nodes = ztree.getCheckedNodes(true);
			if (nodes == null || nodes.length == 0) {
				alert('请选择角色');
				return false;
			}
			var roleIdList = new Array();
			for (var i=0;i<nodes.length;i++) {
				roleIdList.push(nodes[i].id);
			}
			var param = {
				'userid':$('#roleTree').data('userid'),
				'roleIdList':roleIdList
			}
			$.post(url,param,function(data){
				if (data.state == 1) {
					alert('操作成功!');
					$('#roleLayer').hide();
					$("#page-wrapper").laod("user/userlist.do");
				} else {
					alert(data.msg);
				}
			});
		}
	};
	
	/**
	 * 给用户添加角色
	 * @author Lanna
	 */
	function showRole(){
		var $this = $(this);
		var symbol = "IDENTIFY";
		//获取用户的ID共用
		var userid = getId($this,symbol);
		//将用户ID绑定到显示的树上,便于确定添加时需要的值
		$('#roleTree').data('userid',userid);
		//判断是否已经添加过了角色
		$.post('perm/queryUR.do',{'userid':userid},function(result){
			if (result.state == 1) {
				var treeNode = result.data;
				var el = $('#roleTree');
				var url = 'role/queryRole.do';
				if (treeNode == null || treeNode.length == 0) {
					if ($this.hasClass('plus')) {
						$('#roleLayer').show();
						$('#roleTree').data('symbol','INSERT');
						$('.btn_confirm').show().text('添加');
						$('#roleTitle').text('添加角色');
						SetTree(url,el,treeNode,$this);
					} else {
						alert('请先为用户添加角色!')
					}
				} else {
					if ($this.hasClass('plus')) {
						alert('该用户已添加了角色,如需修改或者删除请执行修改或删除操作!');
						return false;
					} 
					if ($this.hasClass('remove')) {
						//删除所有的角色
						$.post('perm/delUserRole.do',{'userid':userid},function(result){
							if (result.state == 1) {
								alert("操作成功!");
								$('#page-wrapper').load('user/userlist.do');
							} else {
								alert(result.msg);
							}
						});
					}
					if ($this.hasClass('pencil')) {
						$('#roleLayer').show();
						$('#roleTree').data('symbol','MODIFY');
						$('.btn_confirm').show().text('修改');
						$('#roleTitle').text('修改角色');
						SetTree(url,el,treeNode,$this);
					}
					if ($this.hasClass('eyeopen')) {
						$('.btn_confirm').hide().text('');
						$('#roleLayer').show();
						$('#roleTitle').text('查看角色');
						SetTree(url,el,treeNode,$this);
					}
				}
			} else {
				alert(result.msg);
			}
		});
	}
	
	/**
	 * 获取ID
	 */
	function getId($this,symbol){
		var td;
		if (symbol === 'IDENTIFY') {
			td = $this.parent().parent().parent().children()[0];
		} else if (symbol === 'STATUS') {
			td = $this.parent().parent().parent().parent().children()[0];
		}
		var id = $(td).attr('data-id');
		return id;
	}
	
	
	/**
	 * 加载树
	 */
	function SetTree(url,el,treeNode,clazz){
		var array = clazz.attr('class'),
			claz = getLastIndexOf(array);
		if (claz == 'plus') {
			$.getJSON(url,function(data){
				ztree = $.fn.zTree.init(el,setting,data.data);
			});
		} else if (claz == 'pencil' || claz == 'eyeopen') {
			$.getJSON(url,function(data){
				ztree = $.fn.zTree.init(el,setting,data.data);
				for (var i in treeNode) {
					var nodes = ztree.getNodesByParam("id",treeNode[i].roleid, null);
					//勾选当前选中的节点
					ztree.checkNode(nodes[0],true,true);
				}
			});
		}
	};
	
	/**
	 * 根据字符串获取最后的一个值
	 */
	function getLastIndexOf(str){
		var s = " ";
		var a = [];
		for (var i=0;i<str.length;i++) {
			var strim = str[i];
			if (strim === s) {
				a.push(i);
			}
		}
		var maxIndex = Math.max.apply(null,a);
		return str.substring(maxIndex+1,str.length);
	}
	
	/**
	 * 创建用户
	 * @author Lanna
	 */
	function createUser(){
		$('#userDiv').dialog({
			title:'添加用户',
			autoOpen:true,
			width:460,
			height:200,
			open:function(){
				$('.ui-widget-header').css('background','#6495ed');
				$(".ui-dialog-titlebar-close").hide();
				$('#username').focus();
			},
			buttons:{
				'确定':function(){
					var username = $('#username').val();
					if(username == "" || username.length ==0){
						$('#username').tips({
							msg:'不能为空',
							side:1,
							color:'#FFFFCC',
							time:3
						});
						$('#username').focus();
						return false;
					}
					var url = 'user/addUser.do',
						param = {'username':$('#username').val()};
					$.post(url,param,function(data){
						if (data.state == 1) {
							alert('成功创建了'+data.data+'个普通用户');
							//$('#userDiv').dialog('close');
							$('#page-wrapper').load('user/userlist.do');
						}
					});
				},
				'取消':function(){
					//先清除表单内的数据,再关闭
					$('#username').val("");
					$(this).dialog('close')
				}
			}
		});
	};
});