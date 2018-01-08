/**
 * 管理初始化
 */
var TeacherClass = {
    id: "TeacherClassTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TeacherClass.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '班级id，关联less_class表的id', field: 'classId', visible: true, align: 'center', valign: 'middle'},
            {title: '老师id，关联less_teacher表的id', field: 'teacherId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TeacherClass.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TeacherClass.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
TeacherClass.openAddTeacherClass = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/teacherClass/teacherClass_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
TeacherClass.openTeacherClassDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teacherClass/teacherClass_update/' + TeacherClass.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
TeacherClass.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/teacherClass/delete", function (data) {
            Feng.success("删除成功!");
            TeacherClass.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("teacherClassId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
TeacherClass.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TeacherClass.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TeacherClass.initColumn();
    var table = new BSTable(TeacherClass.id, "/teacherClass/list", defaultColunms);
    table.setPaginationType("client");
    TeacherClass.table = table.init();
});
