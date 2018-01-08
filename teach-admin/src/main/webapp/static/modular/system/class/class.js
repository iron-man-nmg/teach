/**
 * 管理初始化
 */
var Class = {
    id: "ClassTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Class.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '班级名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Class.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Class.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Class.openAddClass = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/class/class_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Class.openClassDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/class/class_update/' + Class.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Class.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/class/delete", function (data) {
            Feng.success("删除成功!");
            Class.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("classId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Class.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Class.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Class.initColumn();
    var table = new BSTable(Class.id, "/class/list", defaultColunms);
    table.setPaginationType("client");
    Class.table = table.init();
});
