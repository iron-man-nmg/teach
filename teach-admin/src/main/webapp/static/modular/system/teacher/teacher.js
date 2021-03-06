/**
 * 管理初始化
 */
var Teacher = {
    id: "TeacherTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Teacher.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '老师姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sexName', visible: true, align: 'center', valign: 'middle',formatter : function (value, row, index) {
                if (row['sex'] === 1) {
                    return '男';
                }
                if (row['sex'] === 2) {
                    return '女';
                }
                return value;
            }},
    ];
};

/**
 * 检查是否选中
 */
Teacher.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Teacher.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Teacher.openAddTeacher = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/teacher/teacher_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Teacher.openTeacherDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teacher/teacher_update/' + Teacher.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Teacher.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/teacher/delete", function (data) {
            Feng.success("删除成功!");
            Teacher.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("teacherId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 点击角色分配
 * @param
 */
Teacher.clazzAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '班级分配',
            area: ['300px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teacher/teacher_assign/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 查询列表
 */
Teacher.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Teacher.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Teacher.initColumn();
    var table = new BSTable(Teacher.id, "/teacher/list", defaultColunms);
    table.setPaginationType("client");
    Teacher.table = table.init();
});
