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
    background: url('@/assets/loginBg.jpg') no-repeat center center;
    background-size: 100% 100%;
    position: absolute;
    top: 0;
    left: 0;
    z-index: -1;
    opacity: 0.8;
}

/* 登录注册盒子 */
.lgnRegBox {
    width: 40%;
    height: 50%;
    position: absolute;
    top: 15%;
    left: 30%;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 0 5px #cfcfcf;
}

.lgnOrRegTitle {
    height: 10%;
}

.lgnOrRegBox {
    height: 90%;
}

/* 登录盒子 */
.loginBox {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

/* 注册盒子 */
.registerBox {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>

<template>
    <!-- 固定顶部标签栏 -->
    <fixedLabelBar>
        <div class="iconBox">

        </div>
        <div class="otherBox">
            <t-link theme="primary" href="/adminLogin">是管理员？</t-link>
        </div>
    </fixedLabelBar>
    <!-- 背景盒子 -->
    <div class="bgBox"></div>

    <!-- 登录注册盒子 -->
    <div class="lgnRegBox">
        <div style="width: 100%; height: 100%;">
            <div class="lgnOrRegTitle">
                <t-tabs :value="currentValue" :list="list" :space-evenly="false" @change="onChange" />
            </div>
            <div class="lgnOrRegBox">
                <div class="loginBox" v-if="currentValue === '1'">
                    <t-form :data="loginForm" :label-width="0">
                        <t-form-item name="email">
                            <t-input clearable placeholder="请输入邮箱" v-model="loginForm.email" :status="loginEmailStatus"
                                :tips="loginEmailTips" autofocus>
                                <template #prefix-icon>
                                    <t-icon name="mail" />
                                </template>
                            </t-input>
                        </t-form-item>
                        <t-form-item name="password">
                            <t-input placeholder="请输入密码" type="password" v-model="loginForm.pwd">
                                <template #prefix-icon>
                                    <t-icon name="lock-on" />
                                </template>
                            </t-input>
                        </t-form-item>
                        <t-form-item name="password">
                            <div
                                style="width: 100%; display: flex; justify-content: space-between; align-items: center;">
                                <t-input style="width: 40%;" placeholder="请输入密码" v-model="loginForm.imageVerifyCode">
                                    <template #prefix-icon>
                                        <t-icon name="check-circle" />
                                    </template>
                                </t-input>
                                <div style="width: 50%;">
                                    <t-popup content="点击刷新" placement="right">
                                        <img style="width: 100%; cursor: pointer;" :src="imageUrl" alt="验证码"
                                            @click="getImageVerifyCode">
                                    </t-popup>
                                </div>
                            </div>
                        </t-form-item>

                        <t-form-item>
                            <t-button style="width: 100%;" theme="primary" type="submit"
                                @click="handleLogin">登录</t-button>
                        </t-form-item>
                    </t-form>
                </div>
                <!-- 注册盒子 -->
                <div class="registerBox" v-else>
                    <t-form :data="registerForm" layout="inline" :label-width="50">
                        <t-form-item name="stName">
                            <t-input placeholder="请输入名字" v-model="registerForm.stName">
                                <template #prefix-icon>
                                    <user-icon />
                                </template>
                            </t-input>
                        </t-form-item>
                        <t-form-item name="politicalStatus">
                            <t-select v-model="registerForm.politicalStatus" style="width: 200px"
                                :options="politicalStatusList" clearable placeholder="-请选择-">
                                <template #prefixIcon>
                                    <building-icon />
                                </template>
                            </t-select>
                        </t-form-item>
                        <t-form-item name="grade">
                            <t-input placeholder="请输入年级" v-model="registerForm.grade">
                                <template #prefix-icon>
                                    <usergroup-icon />
                                </template>
                            </t-input>
                        </t-form-item>
                        <t-form-item name="email">
                            <t-input placeholder="请输入邮箱" v-model="registerForm.email" :status="registerEmailStatus"
                                :tips="registerEmailTips">
                                <template #prefix-icon>
                                    <mail-icon />
                                </template>
                            </t-input>
                        </t-form-item>
                        <t-form-item name="verifyCode" style="position: relative;">
                            <t-input-group style="width: 200px">
                                <div style="width: 60%;">
                                    <t-input placeholder="请输入验证码" v-model="registerForm.verifyCode">
                                        <template #prefix-icon>
                                            <verified-icon />
                                        </template>
                                    </t-input>
                                </div>
                                <div style="width: 40%;">
                                    <t-button :disabled="getVerifyCode" @click="handleGetVerifyCode">
                                        <span v-if="!getVerifyCode">获取验证码</span>
                                        <span v-else>
                                            {{ getVerifyCodeTime }}s后重发
                                        </span>
                                    </t-button>
                                </div>
                            </t-input-group>
                        </t-form-item>
                        <t-form-item name="password">
                            <t-input placeholder="请输入密码" type="password" v-model="registerForm.pwd">
                                <template #prefix-icon>
                                    <lock-on-icon />
                                </template>
                            </t-input>
                        </t-form-item>
                        <t-form-item name="confirmPassword">
                            <t-input placeholder="请再次输入密码" type="password" v-model="registerForm.confirmPwd"
                                :status="confirmPasswordStatus" :tips="confirmPasswordTips">
                                <template #prefix-icon>
                                    <lock-on-icon />
                                </template>
                            </t-input>
                        </t-form-item>
                        <t-form-item>
                            <t-button style="width: 100%;" theme="primary" type="submit"
                                @click="handleRegister">注册并登录</t-button></t-form-item>
                    </t-form>
                </div>
            </div>
        </div>
        <!-- 登录盒子 -->
    </div>
</template>

<script setup>
import fixedLabelBar from '../components/FixedLabelBar.vue';
import { reactive, ref, computed, onUnmounted, onMounted } from 'vue';
import eventEmitter from '../utils/eventEmitter.js'
import { APIEnum, APIEventEnum, RouterEventEnum } from '../Enum'
import { MessagePlugin } from 'tdesign-vue-next';
import { MailIcon, LockOnIcon, UserIcon, UsergroupIcon, VerifiedIcon, BuildingIcon } from 'tdesign-icons-vue-next';
import store from '@/store';
// 登录和注册

// 检测邮箱格式是否符合中大邮箱
const emailValidate = (data) => {
    if (/^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\.)+sysu\.edu\.cn$/.test(data)) {
        return true
    }
    return false
}

const passwordValidate = (data) => {
    if (data.length < 6) {
        return false
    }
    return true
}

// 控制登录和注册的展现
const currentValue = ref('1');
const list = [
    {
        value: '1',
        label: '登录',
    },
    {
        value: '2',
        label: '注册/忘记密码',
    },
]
const onChange = (value) => {
    currentValue.value = value
};

// 登录
const loginForm = reactive({
    email: 'admin@mail2.sysu.edu.cn',
    pwd: '123456',
    imageVerifyCode: 'n8b7'
})

const imageUrl = ref('')
const getImageVerifyCode = () => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getImageVerifyCode)
}

const loginEmailStatus = computed(() => {
    if (emailValidate(loginForm.email)) {
        return ''
    } else if (!loginForm.email) {
        return ''
    }
    return 'error'
})

const loginEmailTips = computed(() => {
    if (emailValidate(loginForm.email)) {
        return ''
    } else if (!loginForm.email) {
        return ''
    }
    return '请输入中大邮箱'
})

// 获取图形验证码
eventEmitter.emit(APIEventEnum.request, APIEnum.getImageVerifyCode)

// 登录逻辑
const loginValidate = () => {
    return emailValidate(loginForm.email) && passwordValidate(loginForm.pwd) && loginForm.imageVerifyCode !== ''
}

const handleLogin = () => {
    if (loginValidate()) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postLogin, loginForm)
    } else {
        return
    }
}


// 注册
const registerForm = reactive({
    email: '',
    verifyCode: '',
    pwd: '',
    confirmPwd: '',
    stName: '',
    college: '',
    grade: '',
    politicalStatus: ''
})

const politicalStatusList = ref([
    { label: '共青团员', value: '共青团员' },
    { label: '中共党员', value: '中共党员' },
    { label: '群众', value: '群众' },
])

const registerEmailStatus = computed(() => {
    if (emailValidate(registerForm.email)) {
        return ''
    } else if (!registerForm.email) {
        return ''
    }
    return 'error'
})

const registerEmailTips = computed(() => {
    if (emailValidate(registerForm.email)) {
        return ''
    } else if (!registerForm.email) {
        return ''
    }
    return '请输入中大邮箱'
})

const confirmPasswordStatus = computed(() => {
    if (!registerForm.confirmPwd || registerForm.confirmPwd === registerForm.pwd) {
        return ''
    }
    return 'error'
})

const confirmPasswordTips = computed(() => {
    if (!registerForm.confirmPwd || registerForm.confirmPwd === registerForm.pwd) {
        return ''
    }
    return '两次输入的密码不一致'
})

// 获取验证码
const getVerifyCode = ref(false)
const getVerifyCodeTime = ref(60)

const handleGetVerifyCode = () => {
    if (emailValidate(registerForm.email)) {
        getVerifyCode.value = true
        let timer = setInterval(() => {
            getVerifyCodeTime.value--
            if (getVerifyCodeTime.value <= 0) {
                clearInterval(timer)
                getVerifyCode.value = false
                getVerifyCodeTime.value = 60
            }
        }, 1000)
        eventEmitter.emit(APIEventEnum.request, APIEnum.getVerifyCode, registerForm.email)
    }
}


// 注册逻辑
const registerValidate = () => {
    return emailValidate(registerForm.email) && passwordValidate(registerForm.pwd)
        && registerForm.pwd === registerForm.confirmPwd
        && registerForm.verifyCode
}

const handleRegister = () => {
    if (registerValidate()) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postRegister, registerForm)
    }
    else {
        return
    }
}

onMounted(() => {
    // 监听登录成功事件
    eventEmitter.on(APIEventEnum.loginSuccess, 'loginSuccess', () => {
        MessagePlugin.success('登录成功')
        eventEmitter.emit(RouterEventEnum.push, '/')
    })
    // 监听登录失败事件
    eventEmitter.on(APIEventEnum.incorrectInput, 'incorrectInput', (msg) => {
        MessagePlugin.error(msg)
    })
    // 监听注册失败事件
    eventEmitter.on(APIEventEnum.registerError, 'registerError', (msg) => {
        MessagePlugin.error(msg)
    })
    eventEmitter.on(APIEventEnum.getImageVerifyCodeSuccess, 'getImageVerifyCodeSuccess', (data) => {
        imageUrl.value = data
    })
    // 获取枚举列表
    if (localStorage.getItem('enumList')) {

    } else {
        eventEmitter.emit(APIEventEnum.request, APIEnum.getEnumList)
    }
})


onUnmounted(() => {
    eventEmitter.off(APIEventEnum.loginSuccess, 'loginSuccess')
    eventEmitter.off(APIEventEnum.incorrectInput, 'incorrectInput')
    eventEmitter.off(APIEventEnum.registerError, 'registerError')
    eventEmitter.off(APIEventEnum.getImageVerifyCodeSuccess, 'getImageVerifyCodeSuccess')
})
</script>