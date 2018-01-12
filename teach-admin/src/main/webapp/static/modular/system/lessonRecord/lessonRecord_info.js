/**
 * 管理初始化
 */
var LessonStudent = {
    id: "LessonStudentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LessonStudent.initColumn = function () {
    return [
        {field: 'selectItem', check: true},
        {title: '姓名', field: 'studentName', visible: true, align: 'center', valign: 'middle'},
        {title: '套餐', field: 'packName', visible: true, align: 'center', valign: 'middle'},
        {title: '剩余课时', field: 'remainHour', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LessonStudent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LessonStudent.seItem = selected[0];
        return true;
    }
};
LessonStudent.onClickClazz = function (e, treeId, treeNode) {
    LessonStudent.clazzId = treeNode.id;
    LessonStudent.search();
};
/**
 * 查询列表
 */
LessonStudent.search = function () {
    var queryData = {};
    queryData['clazzId'] = $("#clazzId").val();
    LessonStudent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LessonStudent.initColumn();
    var table = new BSTable(LessonStudent.id, "/listStudent/list", defaultColunms);
    table.setPaginationType("client");
    LessonStudent.table = table.init();

    var ztreeClazz = new $ZTree("clazzTree", "/clazz/treeListByTeachId?teachId="+LessonStudent.get("teachId"));
    ztreeClazz.bindOnClick(LessonStudent.onClickClazz);
    ztreeClazz.init();
});
