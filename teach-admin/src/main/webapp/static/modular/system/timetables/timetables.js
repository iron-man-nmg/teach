/**
 * 管理初始化
 */
var Timetables = {
    id: "TimetablesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Timetables.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '周次', field: 'dayOfWeek', visible: true, align: 'center', valign: 'middle'},
            {title: '课次', field: 'lessonOfDay', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Timetables.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Timetables.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Timetables.openAddTimetables = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/timetables/timetables_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Timetables.openTimetablesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/timetables/timetables_update/' + Timetables.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Timetables.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/timetables/delete", function (data) {
            Feng.success("删除成功!");
            Timetables.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("timetablesId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Timetables.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Timetables.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Timetables.initColumn();
    var table = new BSTable(Timetables.id, "/timetables/list", defaultColunms);
    table.setPaginationType("client");
    Timetables.table = table.init();
});
