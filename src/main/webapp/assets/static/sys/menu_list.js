$(document).ready(function(){
	var ztree,
	setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
	};
	
	var Menu = {
		id:'menuTable',
		table:null,
		layerIndex : -1
	};
	
	
	$('#menuManager').on('click','.btn-add',showEditForm);
	$('#menuDiv').on('click','#pid',showLayer);
	$('#menuLayer').on('click','.layer-btn',saveOrCancel);
	
	
	Menu.initColumns = function(){
		var colums = [{
			field:'selectItem',
			checkbox:true
		},{
			title:'菜单ID',
			field:'permid',
			visible:false,
			align:'center',
			valign : 'middle',
		},{
			title:'菜单名称',
			field:'permname',
			align:'center',
			valign : 'middle',
			sortable : true
		},{
			title:'上级菜单',
			field:'parentName',
			align:'center',
			valign : 'middle',
			sortable : true
		},{
			title:'菜单URL',
			field:'permurl',
			align:'center',
			valign : 'middle',
			sortable : true
		},{
			title:'标识号',
			field:'percode',
			align:'center',
			valign : 'middle',
			sortable : true
		},{
			title:'菜单类型',
			field:'types',
			align:'center',
			valign : 'middle',
			sortable : true,
			formatter:function(item,index){
				if (item.types == '菜单') {
					return '<span class="label label-success">菜单</span>';
				}
				if (item.types == '按钮') {
					return '<span class="label label-warning">按钮</span>';
				}
			}
		}]
		return colums;
	};
	loadMenuData();
	/**
	 * 加载系统所有的菜单
	 */
	function loadMenuData(){
		var column = Menu.initColumns();
		var table = new TreeTable(Menu.id,"menu/getMenus.do",column);
		table.setExpandColumn(2);
		table.setIdField("permid");
		table.setCodeField("permid");
		table.setParentCodeField("parentid");
		table.setExpandAll(false);
		table.init();
		Menu.table = table;
	}
	/**
	 * 保存或取消
	 * @author Lanna
	 */
	function saveOrCancel(){
		if($(this).hasClass('btn_confirm')){
			var node = ztree.getSelectedNodes();
			var parentid = node[0].id;
			$('#menuLayer').css('display','none');
			$('#pid').val(node[0].name);
			$('#pid').data('pid',parentid);
		} else if($(this).hasClass('btn_cancle')){
			$('#menuLayer').css('display','none');
		}
	};
	/**
	 * 清楚表单内的数据
	 * @author Lanna
	 */
	function clearFormData(){
		$('#name').val("");
		$('#url').val("");
		$('#pid').val("");
		$('#code').val("");
		$('#icon').val("");
		$('#types').val("");
		$('#sort').val("");
		$('#remark').val("");
	};

	/**
	 * 显示选择树形
	 * @returns
	 */
	function showLayer(){
		$('#menuLayer').css('display','block');
		$.getJSON('menu/treeData.do',function(data){
			ztree = $.fn.zTree.init($('#menuTree'),setting,data);
		});
	};
	/**
	 * 显示添加或编辑的页面
	 * @author Lanna
	 */
	function showEditForm(){
		$('#menuDiv').dialog({
			title:'添加菜单或按钮',
			autoOpen:true,
			width:400,
			modal:true,
			open:function(){
				$('.ui-widget-header').css('background','#6495ed');
				$(".ui-dialog-titlebar-close").hide();
			},
			buttons:{
				'确定':function(){
					var param = getParam();
					$.ajax({
						url:'menu/addMenu.do',
						data:param,
						dataType:'json',
						success:function(data){
							if (data == 1) {
								clearFormData();
							}
						},
						error:function(){}
					});
				},
				'取消':function(){
					//先清楚表单内的数据,再关闭
					clearFormData();
					$(this).dialog('close');
				}
			}
		});
	};
	
	/**
	 * 获取参数
	 */
	function getParam(){
		var param = {};
		var permname = $('#name').val();
		if(permname != '' || permname.length !=0){
			param['permname'] = permname;
		}
		var permurl = $('#url').val();
		if(permurl != '' || permurl.length !=0){
			param['permurl'] = permurl;
		}
		var parentid = $('#pid').val();
		if(parentid != '' || parentid.length !=0){
			param['parentid'] = $('#pid').data('pid');
		}
		var percode = $('#code').val();
		if(percode != '' || percode.length !=0){
			param['percode'] = percode;
		}
		var icon = $('#icon').val();
		if(icon != '' || icon.length !=0){
			param['icon'] = icon;
		}
		var types =$('#types').val();
		if(types != '' || types.length !=0){
			param['types'] = types;
		}
		var sort = $('#sort').val();
		if(sort != '' || sort.length !=0){
			param['sort'] = sort;
		}
		var remark = $('#remark').val()
		if(remark != '' || remark.length !=0){
			param['remark'] = remark;
		}
		return param;
	}
});




