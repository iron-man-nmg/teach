/**
 * 管理初始化
 */
var Clazz = {
    id: "ClazzTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Clazz.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '班级名称', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Clazz.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Clazz.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Clazz.openAddClazz = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '260px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/clazz/clazz_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Clazz.openClazzDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '260px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/clazz/clazz_update/' + Clazz.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Clazz.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/clazz/delete", function (data) {
            Feng.success("删除成功!");
            Clazz.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("clazzId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Clazz.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Clazz.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Clazz.initColumn();
    var table = new BSTable(Clazz.id, "/clazz/list", defaultColunms);
    table.setPaginationType("client");
    Clazz.table = table.init();
});
