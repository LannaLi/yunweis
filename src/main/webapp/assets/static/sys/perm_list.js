$(document).ready(function(){
	var ztree,
	setting = {
		check:{
			chkboxType : { "Y" : "p", "N" : "s" },
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
		}
	}
	
	$('#permManager').on('click','.btn-createRole',createRole);
	$('#permManager').on('click','.glyphicon-menu',showMenu);
	$('#menuLayer').on('click','.layer-btn',layerHandler);
	$('#permManager').on('click','.label-handler',updateRoleStatus);
	
	$('#rolename_tips').hover(
			function(){
				$('#rolename_tips').tips({
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
	//==========================方法区==================================//
	/**
	 * 修改角色状态
	 */
	function updateRoleStatus(){
		var $this = $(this);
		var symbol = "STATUS";
		var roleid = getId($this,symbol);
		var types;
		if ($this.hasClass('recover')) {
			types = 'RECOVER';
		} else if ($this.hasClass('freezeup')) {
			types = 'FREEZE';
		}
		$.ajax({
			type:'POST',
			url:'role/updateRoleStatus.do',
			dataType:'json',
			data:{'roleid':roleid,'types':types},
			success:function(result){
				if (result.state == 1) {
					$('#page-wrapper').load('perm/permlist.do');
				} else {
					alert(result.msg);
				}
			} 
		});
	};
	
	function showMenu(){
		var $this = $(this);
		var symbol = "IDENTIFY";
		var roleid = getId($this,symbol);
		$('#menuLayer').data('roleid',roleid);
		var param = {'roleid':roleid};
		//判断是否分配过资源了
		$.ajax({
			type:'POST',
			url:'queryPermByRoleId.do',
			dataType:'json',
			data:param,
			success:function(data){
				if (data.state == 1) {
					var treeNode = data.data;
					var el = $('#permTree');
					var url = 'menu/treeData.do';
					if (treeNode == null|| treeNode.length == 0) {
						if ($this.hasClass('plus')) {
							$('#menuLayer').show();
							$('.btn_confirm').text('添加').show();
							$('#menuLayer').data('symbol','INSERT');
							$('#permTitle').text('分配资源');
							setTree(url,el,treeNode,$this);
						} else {
							alert('请先为该角色分配系统资源!');
						}
					} else {
						if ($this.hasClass('plus')) {
							alert('该角色已添加了资源,如需修改或者删除请执行修改或删除操作!');
							return false;
						} else if ($this.hasClass('remove')) {
							//根据角色ID删除所有的资源
							var uri = 'delRolePermsByRoleid.do';
							$.post(uri,param,function(result){
								if (result.state == 1) {
									alert('操作成功!');
									$("#page-wrapper").load("perm/permlist.do");
								}
							});
						} else if ($this.hasClass('pencil')) {
							$('#menuLayer').show();
							$('#menuLayer').data('symbol','MODIFY');
							$('.btn_confirm').text('修改').show();
							$('#permTitle').text("修改权限");
							setTree(url,el,treeNode,$this);
						} else if ($this.hasClass('open')) {
							$('#menuLayer').show();
							$('.btn_confirm').text('').hide();
							$('#permTitle').text("查看权限");
							setTree(url,el,treeNode,$this);
						}
					}
				}
			}
		});
	}
	
	/**
	 * 设置树回显
	 */
	function setTree(url,el,treeNode,clazz){
		var array = clazz.attr('class')
			claz = getLastIndexOf(array);
		if (claz == 'plus') {
			$.getJSON(url,function(data){
				ztree = $.fn.zTree.init(el,setting,data);
			});
		} else if (claz == 'pencil' || claz == 'open') {
			$.getJSON(url,function(data){
				ztree = $.fn.zTree.init(el,setting,data);
				for (var i in treeNode) {
					var nodes = ztree.getNodesByParam("id",treeNode[i].id, null);
					//勾选当前选中的节点
					ztree.checkNode(nodes[0],true,false);
					ztree.expandNode(nodes[0],true,true,true);
				}
			});
		}
	}
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
	 * 点击确定和取消
	 */
	function layerHandler(){
		if ($(this).hasClass('btn_cancle')) {
			$('#menuLayer').hide();
			$('#permTree').empty();
		} else if ($(this).hasClass('btn_confirm')) {
			var symbol = $('#menuLayer').data('symbol');
			var url;
			if (symbol === 'INSERT') {
				url = 'addRolePerm.do';
			} else if (symbol === 'MODIFY') {
				url = 'updateRolePerm.do';
			}
			var nodes = ztree.getCheckedNodes(true);
			if (nodes.length == 0) {
				alert("请授权!");
			}
			var menuIdList = new Array();
			for (var i=0;i<nodes.length;i++) {
				menuIdList.push(nodes[i].id);
			}
			var param = {
				'roleid':$('#menuLayer').data('roleid'),
				'menuIdList':menuIdList
			}
			$.post(url,param,function(data){
				if (data.state == 1) {
					alert("授权成功");
					$('#menuLayer').hide();
					$("#page-wrapper").load("perm/permlist.do");
					
				} else {
					alert(data.msg);
				}
			});
			
		}
	}
	
	/**
	 * 创建角色
	 * @author Lanna
	 */
	function createRole(){
		$('#permDiv').dialog({
			title:'添加角色',
			autoOpen:true,
			width:460,
			height:200,
			open:function(){
				$('.ui-widget-header').css('background','#6495ed');
				$(".ui-dialog-titlebar-close").hide();
				$('#rolename').focus();
			},
			buttons:{
				'确定':function(){
					var rolename = $('#rolename').val();
					if(rolename == "" || rolename.length ==0){
						$('#rolename').tips({
							msg:'不能为空',
							side:1,
							color:'#FFFFCC',
							time:3
						});
						$('#rolename').focus();
						return false;
					}
					var url = 'role/addRole.do',
						param = {'rolename':$('#rolename').val()};
					
					$.post(url,param,function(data){
						if (data.state == 1) {
							alert('成功创建了'+data.data+'个角色');
						}
					});
				},
				'取消':function(){
					//先清楚表单内的数据,再关闭
					$('#rolename').val("");
					$(this).dialog('close');
				}
			}
		})
	};
	
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
	
});