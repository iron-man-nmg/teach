/**
 * 管理初始化
 */
var PackPackage = {
    id: "PackPackageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PackPackage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '名字', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '课时', field: 'clazzHour', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'desc', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PackPackage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PackPackage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
PackPackage.openAddPackPackage = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/packPackage/packPackage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
PackPackage.openPackPackageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/packPackage/packPackage_update/' + PackPackage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
PackPackage.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/packPackage/delete", function (data) {
            Feng.success("删除成功!");
            PackPackage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("packPackageId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
PackPackage.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PackPackage.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PackPackage.initColumn();
    var table = new BSTable(PackPackage.id, "/packPackage/list", defaultColunms);
    table.setPaginationType("client");
    PackPackage.table = table.init();
});
