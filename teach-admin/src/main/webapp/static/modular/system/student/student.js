/**
 * 管理初始化
 */
var Student = {
    id: "StudentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Student.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '用户id，可关联到sys_user表的id',visible: false, field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '联系方式', field: 'contactPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '照片', field: 'photoUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Student.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Student.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Student.openAddStudent = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/student/student_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Student.openStudentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/student/student_update/' + Student.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Student.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/student/delete", function (data) {
            Feng.success("删除成功!");
            Student.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("studentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Student.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Student.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Student.initColumn();
    var table = new BSTable(Student.id, "/student/list", defaultColunms);
    table.setPaginationType("client");
    Student.table = table.init();
});
