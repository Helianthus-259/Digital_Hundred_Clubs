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

/* 登录注册盒子 */
.lgnRegBox {
    width: 40%;
    height: 50%;
    position: absolute;
    top: 20%;
    left: 30%;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 0 5px #cfcfcf;
}

.loginOrRegister {
    position: fixed;
    width: 40%;
    height: 10%;
    top: 20%;
    left: 30%;
    z-index: 998;
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

.formItemBox {
    width: 100%;
    height: 70%;
    margin-top: 10%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column
}

.buttonBox {
    width: 100%;
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
        <!-- 登录注册标签栏 -->
        <div class="loginOrRegister">
            <t-tabs :value="currentValue" :list="list" :space-evenly="false" @change="onChange" />
        </div>
        <!-- 登录盒子 -->
        <div class="loginBox" v-if="currentValue === '1'">
            <t-form style="height: 100%;">
                <div class="formItemBox">
                    <t-form-item label="邮箱" name="email">
                        <t-input placeholder="请输入邮箱" v-model="loginForm.email" :status="loginEmailStatus"
                            :tips="loginEmailTips" autofocus />
                    </t-form-item>
                    <t-form-item label="密码" name="password">
                        <t-input placeholder="请输入密码" type="password" v-model="loginForm.password" />
                    </t-form-item>
                </div>
                <div class="buttonBox">
                    <t-button style="width: 100%;" theme="primary" type="submit" @click="handleLogin">登录</t-button>
                </div>
            </t-form>
        </div>
        <!-- 注册盒子 -->
        <div class="registerBox" v-else>
            <t-form style="height: 100%;">
                <div class="formItemBox">
                    <t-form-item label="邮箱" name="email">
                        <t-input placeholder="请输入邮箱" v-model="registerForm.email" :status="registerEmailStatus"
                            :tips="registerEmailTips" autofocus />
                    </t-form-item>
                    <t-form-item label="验证码" name="verifyCode" style="position: relative;">
                        <t-input placeholder="请输入验证码" v-model="registerForm.verifyCode" />
                        <span v-if="!getVerifyCode"
                            style="position: absolute; right: 0; top: 0; color: #1890ff; cursor: pointer;"
                            @click="handleGetVerifyCode">获取验证码</span>
                        <span v-else style="position: absolute; right: 0; top: 0; color: #1890ff;">
                            {{ getVerifyCodeTime }}s后重发
                        </span>
                    </t-form-item>
                    <t-form-item label="密码" name="password">
                        <t-input placeholder="请输入密码" type="password" v-model="registerForm.password" />
                    </t-form-item>
                    <t-form-item label="确认密码" name="confirmPassword">
                        <t-input placeholder="请再次输入密码" type="password" v-model="registerForm.confirmPassword"
                            :status="confirmPasswordStatus" :tips="confirmPasswordTips" />
                    </t-form-item>
                </div>
                <div class="buttonBox">
                    <t-button style="width: 100%;" theme="primary" type="submit"
                        @click="handleRegister">注册并登录</t-button>
                </div>
            </t-form>
        </div>
    </div>
</template>

<script setup>
import fixedLabelBar from '../components/FixedLabelBar.vue';
import { reactive, ref, computed } from 'vue';
import eventEmitter from '../utils/eventEmitter.js'
import { APIEnum, APIEventEnum } from '../Enum'

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
    password: '123456',
})

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
// 登录逻辑
const loginValidate = () => {
    return emailValidate(loginForm.email) && passwordValidate(loginForm.password)
}

const handleLogin = () => {
    if (loginValidate()) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.postLogin, loginForm)
    } else {
        return
    }
}

// 监听登录失败事件
eventEmitter.on(APIEventEnum.incorrectInput, (msg) => {
    alert(msg)
})

// 注册
const registerForm = reactive({
    email: '',
    verifyCode: '',
    password: '',
    confirmPassword: '',
})

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
    if (!registerForm.confirmPassword || registerForm.confirmPassword === registerForm.password) {
        return ''
    }
    return 'error'
})

const confirmPasswordTips = computed(() => {
    if (!registerForm.confirmPassword || registerForm.confirmPassword === registerForm.password) {
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
    return emailValidate(registerForm.email) && passwordValidate(registerForm.password)
        && registerForm.password === registerForm.confirmPassword
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

// 监听注册失败事件
eventEmitter.on(APIEventEnum.registerError, (msg) => {
    alert(msg)
})

</script>