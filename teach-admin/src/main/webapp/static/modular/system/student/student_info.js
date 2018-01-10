/**
 * 初始化详情对话框
 */
var StudentInfoDlg = {
    studentInfoData: {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        contactPhone: {
            validators: {
                notEmpty: {
                    message: '联系方式不能为空'
                }
            }
        },
        sex: {
            validators: {
                notEmpty: {
                    message: '性别不能为空'
                }
            }
        },
        packSel: {
            validators: {
                notEmpty: {
                    message: '套餐不能为空'
                }
            }
        },
        clazzSel: {
            validators: {
                notEmpty: {
                    message: '班级不能为空'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
StudentInfoDlg.clearData = function () {
    this.studentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentInfoDlg.set = function (key, val) {
    this.studentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StudentInfoDlg.close = function () {
    parent.layer.close(window.parent.Student.layerIndex);
}

/**
 * 收集数据
 */
StudentInfoDlg.collectData = function () {
    this
        .set('id')
        .set('userId')
        .set('name')
        .set('contactPhone')
        .set('sex')
        .set('photoUrl')
        .set('clazzId')
        .set('packId')
    ;
}


/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
StudentInfoDlg.onClickClazz = function (e, treeId, treeNode) {
    $("#clazzSel").attr("value", instanceClazz.getSelectedVal());
    $("#clazzId").attr("value", treeNode.id);
};
/**
 * 点击角色input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
StudentInfoDlg.onClickPack = function (e, treeId, treeNode) {
    $("#packSel").attr("value", instancePack.getSelectedVal());
    $("#packId").attr("value", treeNode.id);
};
/**
 * 显示班级选择的树
 *
 * @returns
 */
StudentInfoDlg.showClazzSelectTree = function () {
    var cityObj = $("#clazzSel");
    var cityOffset = $("#clazzSel").offset();
    $("#menuClazzContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onClazzDown);
};
/**
 * 隐藏班级选择的树
 */
StudentInfoDlg.hideClazzSelectTree = function () {
    $("#menuClazzContent").fadeOut("fast");
    $("body").unbind("mousedown", onClazzDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 隐藏角色选择的树
 */
StudentInfoDlg.hidePackSelectTree = function () {
    $("#menuPackContent").fadeOut("fast");
    $("body").unbind("mousedown", onPackDown);// mousedown当鼠标按下就可以触发，不用弹起
};
/**
 * 显示套餐选择的树
 *
 * @returns
 */
StudentInfoDlg.showPackSelectTree = function () {
    var cityObj = $("#packSel");
    var cityOffset = $("#packSel").offset();
    $("#menuPackContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onPackDown);
};
/**
 * 提交添加
 */
StudentInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/student/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Student.table.refresh();
        StudentInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentInfoData);
    ajax.start();
}
function onPackDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuPackContent").length > 0)) {
        StudentInfoDlg.hidePackSelectTree();
    }
}

function onClazzDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuClazzContent").length > 0)) {
        StudentInfoDlg.hideClazzSelectTree();
    }
}
/**
 * 提交修改
 */
StudentInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/student/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Student.table.refresh();
        StudentInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentInfoData);
    ajax.start();
}

$(function () {
    var ztreePack = new $ZTree("treePack", "/packPackage/tree");
    ztreePack.bindOnClick(StudentInfoDlg.onClickPack);
    ztreePack.init();
    instancePack = ztreePack;

    var ztreeClazz = new $ZTree("treeClazz", "/clazz/treeList");
    ztreeClazz.bindOnClick(StudentInfoDlg.onClickClazz);
    ztreeClazz.init();
    instanceClazz = ztreeClazz;
    //初始化性别选项

    $("#sex").val($("#sexValue").val());
});
