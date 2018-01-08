/**
 * 初始化详情对话框
 */
var TeacherClassInfoDlg = {
    teacherClassInfoData : {}
};

/**
 * 清除数据
 */
TeacherClassInfoDlg.clearData = function() {
    this.teacherClassInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherClassInfoDlg.set = function(key, val) {
    this.teacherClassInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherClassInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TeacherClassInfoDlg.close = function() {
    parent.layer.close(window.parent.TeacherClass.layerIndex);
}

/**
 * 收集数据
 */
TeacherClassInfoDlg.collectData = function() {
    this
    .set('id')
    .set('classId')
    .set('teacherId')
    .set('createTime')
    ;
}

/**
 * 提交添加
 */
TeacherClassInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacherClass/add", function(data){
        Feng.success("添加成功!");
        window.parent.TeacherClass.table.refresh();
        TeacherClassInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherClassInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TeacherClassInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacherClass/update", function(data){
        Feng.success("修改成功!");
        window.parent.TeacherClass.table.refresh();
        TeacherClassInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherClassInfoData);
    ajax.start();
}

$(function() {

});
