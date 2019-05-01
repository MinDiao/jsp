// 获取input标签
var empinput = document.getElementsByClassName("empAddUpdateInput");
var error = document.getElementsByClassName("error");
// 定义提交验证开关
var onoff = false;
// 循环
for (var i = 0; i < empinput.length; i++) {
    //  设置失去焦点的索引
    empinput[i].index = i;
    // 失去焦点事件
    empinput[i].onblur = function(){
        // 定义当前对象的值
        var val = this.value;
        // 循环是第几个输入框
        switch (this.index) {
            case 0:
                check(this);
                break;
            case 1:
                check(this);
                break;
            case 2:
                check(this);
                break;
        }

        // 封装
        function check(obj) {
            // 判空
            if (!val) {
                error[obj.index].innerHTML = "不能为空";
                error[obj.index].style.color = "red";
                onoff = false;
            } else {
                // 验证全部通过
                error[obj.index].innerHTML = "输入合法";
                error[obj.index].style.color = "transparent";
                onoff = true;
            }
        }

    }
}

function runSubmit(){
    return onoff;
}
