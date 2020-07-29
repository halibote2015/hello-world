$(function () {

    var api = {};
    api.queryNews = function(param, fn){
        $.ajax({
            type: "post",
            url: "/api/getNews",
            data: param,
            contentType: "application/x-www-form-urlencoded",
            success: function (res) {
                console.log(res);
            },
            error: function (res) {

            }
        })
    };
    // js 原生ajax
    function myAjax(param){
        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4){   //返回
                if(xhr.status === 200){ //响应代码正常
                    param.success && param.success(xhr);
                }else{
                    param.error && param.error();
                }
            }
        };
        xhr.open("post", param.url, true);
        xhr.setRequestHeader("Content-Type", param.contentType);
        xhr.send(param.data);
    }
    function getRequestParam(form){

        var data = {};
        var keys = $(form.key).get();
        var vals = $(form.value).get();
        keys.forEach(function (item, index) {
            if(!$.trim(item.value)) return;
            data[$.trim(item.value)] = $.trim(vals[index].value);
        });
        var param = {
            type: form.type.value,
            url: form.url.value,
            data: data,
            contentType: form.contentType.value
        };
        return param;
    }
    // 生成ajax请求
    function sendAjaxRequest(param, success, fail){
        var ajax = ajaxForm.ajax.value;
        switch (ajax) {
            case "$.ajax":

                $.ajax(param).then(success, fail);
                break;
            case "axios":
                axios(param).then(success, fail);
                break;
            case "XMLHttpRequest":
                myAjax(param);
                break;

        }
    }

    var TPL_li = document.getElementById("TPL_PARAMINPUT").innerText;

    function formatJson(json){
        return JSON.stringify(json, null, "\40\40");
    }

    // 添加输入参数dom
    function addParamDom(){
        $("#List_param").append(TPL_li);
    }
    // 删除输入参数dom
    function deleteParamDom(self){
        $(self).closest("li").remove();
    }

    // 重新填充表单内容
    function renderAjaxForm(id){
        var frm = window.localStorage.getItem(id);
        if(!frm) return;
        $(ajaxForm).html(frm);
    }
    // 保存表单
    function saveAjaxForm(id){
        window.localStorage.setItem(id, $(ajaxForm).html());
    }
    // 验证表单输入
    function validateForm(form){
        var eles = form.elements;
        var ele = null;
        for(var i = 0, len = eles.length; i < len; i++){
            ele = eles[i];
            if(ele.hasAttribute("getdisabled")
                || ele.getAttribute("disabled")
                || ele.hasAttribute("readonly")
                || ele.getAttribute("readonly")){
                continue;
            }
            if(ele.hasAttribute("required") || ele.getAttribute("required")){
                switch (ele.type) {
                    case "number":
                    case "date":
                    case "time":
                    case "textarea":
                    case "text":
                        if(!$.trim(ele.value)){
                            layer.tips("不能为空", ele);
                            return false;
                        }
                        break;
                    case "radio":
                        break;
                    case "checkbox":
                        break;
                    default:
                        break;
                }
            }
        }
        return true;
    }

    // 初始化表单
    function initForm(form, eleCache){
        if(!eleCache)return;
        eleCache = JSON.parse(eleCache);
        var ele = null;
        var x = y = 0;
        for(var i = 0, len = eleCache.length; i < len; i++){
            ele = eleCache[i];

            switch (ajaxForm[ele.name].length  ? ajaxForm[ele.name][0].type: ajaxForm[ele.name].type) {
                case "number":
                case "date":
                case "time":
                case "textarea":
                case "text":
                    if(ele.name === "key"){
                        ajaxForm[ele.name][x++].value = ele.value;
                    }else if(ele.name === "value"){
                        ajaxForm[ele.name][y++].value = ele.value;
                    }else{
                        ajaxForm[ele.name].value = ele.value;
                    }
                    break;
                case "radio":
                    break;
                case "checkbox":
                    break;
                default:
                    break;
            }
        }
    }

    var ajaxForm = document.ajaxForm;
    renderAjaxForm(1);
    initForm(ajaxForm, localStorage.getItem("formData"));
    $(document.body).delegate(".btn","click", function (e) {
        var role = $(this).data("role");
        switch (role) {
            case "submit":
                if(!validateForm(ajaxForm)) return ;
                saveAjaxForm(1);
                localStorage.setItem("formData", JSON.stringify($(ajaxForm).serializeArray()));

                var param = getRequestParam(ajaxForm);
                $("#Pre_request").html(formatJson(param));
                sendAjaxRequest(param, function (res) {
                    try{
                        var s = formatJson(typeof res === "string" ? JSON.parse(res): res);
                        $("#Pre_response").html(s);

                    }catch (e) {
                        console.error(e)
                    }
                }, function (res) {
                    var responseText = res.responseText;
                    var s = typeof responseText === "string" ? JSON.parse(responseText): responseText;
                    $("#Pre_response").html(formatJson(s));
                });
                break;
            case "addInput":
                addParamDom();
                break;
            case "delInput":
                deleteParamDom(this);
                break;
            default:
                break;
        }
    })
});