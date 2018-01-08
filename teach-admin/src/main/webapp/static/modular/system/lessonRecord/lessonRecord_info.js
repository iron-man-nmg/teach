/**
 * 初始化详情对话框
 */
var LessonRecordInfoDlg = {
    lessonRecordInfoData : {}
};

/**
 * 清除数据
 */
LessonRecordInfoDlg.clearData = function() {
    this.lessonRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LessonRecordInfoDlg.set = function(key, val) {
    this.lessonRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LessonRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LessonRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.LessonRecord.layerIndex);
}

/**
 * 收集数据
 */
LessonRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('studentId')
    .set('clazzId')
    .set('lessonOfDay')
    .set('teacherId')
    .set('createTime')
    ;
}

/**
 * 提交添加
 */
LessonRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lessonRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.LessonRecord.table.refresh();
        LessonRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lessonRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LessonRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lessonRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.LessonRecord.table.refresh();
        LessonRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lessonRecordInfoData);
    ajax.start();
}

$(function() {

});
