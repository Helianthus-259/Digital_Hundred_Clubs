<style scoped>
/* 顶部固定栏 */
.iconBox {
    width: 80%;
    margin-left: 10%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.otherBox {
    width: 10%;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* 背景盒子 */
.bgBox {
    width: 100%;
    height: 100%;
    background: url('../assets/loginBg.jpg') no-repeat center center;
    background-size: 100% 100%;
    position: absolute;
    top: 0;
    left: 0;
    z-index: -1;
    opacity: 0.8;
}

/* 登录盒子 */
.loginBox {
    width: 40%;
    height: 50%;
    position: absolute;
    top: 20%;
    left: 30%;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 0 5px #cfcfcf;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
    box-sizing: border-box;
}
</style>

<template>
    <!-- 固定顶部标签栏 -->
    <fixedLabelBar>
        <div class="iconBox">

        </div>
        <div class="otherBox">
            <t-link theme="primary" href="/login">普通用户？</t-link>
        </div>
    </fixedLabelBar>
    <!-- 背景盒子 -->
    <div class="bgBox"></div>

    <!-- 登录盒子 -->
    <div class="loginBox">
        <t-form>
            <t-form-item label="用户名" name="username">
                <t-input placeholder="请输入管理员用户名" v-model="adminLoginForm.adminId" />
            </t-form-item>
            <t-form-item label="密码" name="password">
                <t-input type="password" placeholder="请输入密码" v-model="adminLoginForm.password"/>
            </t-form-item>
            <div style="width: 100%; display: flex; justify-content:space-around; margin-top: 5%;">
                <t-button theme="primary" type="submit" @click="adminHandleLogin">登录</t-button>
            </div>
        </t-form>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import fixedLabelBar from '../components/FixedLabelBar.vue';
import { reactive, ref, computed } from 'vue';
import eventEmitter from '../utils/eventEmitter.js'
import { APIEnum, APIEventEnum } from '../Enum'
import store from '@/store';

// 为了方便测试，每次都初始化一个正确的管理员账号
const adminLoginForm = reactive({
    adminId: 'administer',
    password: '123456',
})

// 检测账号格式是否为管理员账号
const adminIdValidate = (data) => {
    return /administer$/.test(data);
}

//密码检查
const passwordValidate = (data) => {
    return data.length >= 6;
}

const adminLoginValidate = () => {
    return adminIdValidate(adminLoginForm.adminId) && passwordValidate(adminLoginForm.password)
}

const adminHandleLogin = () => {
    if (adminLoginValidate()) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminLogin, adminLoginForm)
        eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
    }
}


</script>