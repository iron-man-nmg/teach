/**
 * 初始化详情对话框
 */
var ClazzInfoDlg = {
    clazzInfoData : {}
};

/**
 * 清除数据
 */
ClazzInfoDlg.clearData = function() {
    this.clazzInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClazzInfoDlg.set = function(key, val) {
    this.clazzInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClazzInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClazzInfoDlg.close = function() {
    parent.layer.close(window.parent.Clazz.layerIndex);
}

/**
 * 收集数据
 */
ClazzInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('createTime')
    ;
}

/**
 * 提交添加
 */
ClazzInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clazz/add", function(data){
        Feng.success("添加成功!");
        window.parent.Clazz.table.refresh();
        ClazzInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clazzInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClazzInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clazz/update", function(data){
        Feng.success("修改成功!");
        window.parent.Clazz.table.refresh();
        ClazzInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clazzInfoData);
    ajax.start();
}

$(function() {

});
