/**
 * 初始化详情对话框
 */
var TimetablesInfoDlg = {
    timetablesInfoData : {}
};

/**
 * 清除数据
 */
TimetablesInfoDlg.clearData = function() {
    this.timetablesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TimetablesInfoDlg.set = function(key, val) {
    this.timetablesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TimetablesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TimetablesInfoDlg.close = function() {
    parent.layer.close(window.parent.Timetables.layerIndex);
}

/**
 * 收集数据
 */
TimetablesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('dayOfWeek')
    .set('lessonOfDay')
    .set('beginTime')
    .set('endTime')
    .set('createTime')
    ;
}

/**
 * 提交添加
 */
TimetablesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/timetables/add", function(data){
        Feng.success("添加成功!");
        window.parent.Timetables.table.refresh();
        TimetablesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.timetablesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TimetablesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/timetables/update", function(data){
        Feng.success("修改成功!");
        window.parent.Timetables.table.refresh();
        TimetablesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.timetablesInfoData);
    ajax.start();
}

$(function() {

});
