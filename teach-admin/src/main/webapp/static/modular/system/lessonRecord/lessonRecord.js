/**
 * 管理初始化
 */
var LessonRecord = {
    id: "LessonRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LessonRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '学生id，关联到less_student表的id', field: 'studentId', visible: true, align: 'center', valign: 'middle'},
            {title: '班级id，关联到less_clazz表的id', field: 'clazzId', visible: true, align: 'center', valign: 'middle'},
            {title: '课次', field: 'lessonOfDay', visible: true, align: 'center', valign: 'middle'},
            {title: '老师id，可以关联到less_teacher的id', field: 'teacherId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LessonRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LessonRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
LessonRecord.openAddLessonRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lessonRecord/lessonRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
LessonRecord.openLessonRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/lessonRecord/lessonRecord_update/' + LessonRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
LessonRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/lessonRecord/delete", function (data) {
            Feng.success("删除成功!");
            LessonRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("lessonRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
LessonRecord.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    LessonRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LessonRecord.initColumn();
    var table = new BSTable(LessonRecord.id, "/lessonRecord/list", defaultColunms);
    table.setPaginationType("client");
    LessonRecord.table = table.init();
});
