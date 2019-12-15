/**
 * 密码验证
 */
var reg =  /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{6,16}$/
function verify() {
    let a = $("input[name = 'password']").val()
    if(reg.test(a)){
        $("#verifyText-1").text("密码校验成功")
        return true
    }else {
        $("#verifyText-1").text("密码校验失败，请重新输入")
        return false
    }
}

/**
 * 确认密码
 */
function affirm() {
    $("input[name = 'password']").val()
    if($("input[name = 'password']").val() == $("input[name = 'affirm']").val() && $("input[name = 'affirm']").val() != "" && $("input[name = 'affirm']").val().length > 0){
        $("#verifyText-2").text("密码确认成功")
        return true;
    }else {
        $("#verifyText-2").text("两次密码不一致。请确认...")
        return false
    }
}


/**
 * 提交
 */
function update() {
    if(verify() && affirm()){
        $.ajax("/admin/updatePassword",{
            dataType:'JSON',
            type:'POST',
            timeout:5000,
            data:{'userPassword':$("input[name = 'password']").val(),
                    'userId':$("#user_id").html()
            },
            success:(data)=>{
                if(data.code == 20000){
                    alert(data.message)
                }else {
                    alert(data.message)
                }
            }
        })
    }else {
        alert("请确认密码一致在提交！^-^")
    }
}