/**
 * 初始化详情对话框
 */
var PackPackageInfoDlg = {
    packPackageInfoData : {}
};

/**
 * 清除数据
 */
PackPackageInfoDlg.clearData = function() {
    this.packPackageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PackPackageInfoDlg.set = function(key, val) {
    this.packPackageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PackPackageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PackPackageInfoDlg.close = function() {
    parent.layer.close(window.parent.PackPackage.layerIndex);
}

/**
 * 收集数据
 */
PackPackageInfoDlg.collectData = function() {
    this
    .set('id')
    .set('price')
    .set('classHour')
    .set('createTime')
    ;
}

/**
 * 提交添加
 */
PackPackageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/packPackage/add", function(data){
        Feng.success("添加成功!");
        window.parent.PackPackage.table.refresh();
        PackPackageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.packPackageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PackPackageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/packPackage/update", function(data){
        Feng.success("修改成功!");
        window.parent.PackPackage.table.refresh();
        PackPackageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.packPackageInfoData);
    ajax.start();
}

$(function() {

});
