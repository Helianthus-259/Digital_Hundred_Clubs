<style scoped>
.mainBox {
    display: flex;
    width: 100%;
    height: 600px;
}

/* 左边部分 */
.leftSide {
    width: 80%;
    padding: 5px;
}

/* 左边部分内容 */
.leftSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
}

.personalInfo {
    width: 60%;
}

/* 右边部分 */
.rightSide {
    width: 20%;
    padding: 5px;
}

/* 右边部分内容 */
.rightSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
}

.ribbon {
    width: 100%;
    height: 100%;
    padding: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    /* 按钮样式 */
    .t-button {
        margin: 5px;
        width: 65%;
        height: 40px;
        font-size: 16px;
        overflow: hidden;
    }
}
</style>

<template>
    <div class="mainBox">
        <div class="leftSide">
            <div class="leftSideContent">
                <div class="personalInfo">
                    <t-cell-group bordered>
                        <t-cell title="管理员id" :note="adminId" />
                        <t-cell title="管理员账号" :note="account" />
                        <t-cell title="管理员类别" :note="sort" />
                        <t-cell title="登录密码" :note="password" />
                        <t-cell title="联系方式" hover style="height: 56px">
                            <template #note>
                                <t-input :maxcharacter="11" v-model="contact" placeholder="请输入联系方式" align="right"
                                    :readonly="readOnly" borderless />
                            </template>
                        </t-cell>
                        <t-cell title="附属单位" :note="affiliatedUnit" />
                    </t-cell-group>
                    <div style="display: flex; justify-content: center; padding: 10px">
                        <t-button v-show="!readOnly" @click="save">保存</t-button>
                    </div>
                </div>
            </div>
        </div>

        <div class="rightSide">
            <div class="rightSideContent">
                <div class="ribbon">
                    <t-button theme="primary" @click="readOnly = !readOnly">编辑信息</t-button>
                </div>
            </div>
        </div>
    </div>

</template>

<script setup>
import { APIEnum, APIEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { onUnmounted, reactive, ref } from 'vue';

// 弹窗
const dialogRef = ref(null);

const openDialog = () => {
    dialogRef.value.openDialog();
};

const closeDialog = () => {
    dialogRef.value.closeDialog();
};

// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 展示个人信息
const adminId = ref('')
const account = ref('')
const sort = ref('')
const password = ref('')
const contact = ref('')
const affiliatedUnit = ref('')

const user = ref({})

// 为上面定义的变量赋值
function assignment() {
    adminId.value = user.value.adminId
    account.value = user.value.account
    sort.value = transferSort(user.value.sort)
    password.value = user.value.password
    contact.value = user.value.contact
    affiliatedUnit.value = user.value.affiliatedUnit
}

function transferSort(sortNumber) {
    if (sortNumber === 0) {
        return '学院管理员'
    }
    else {
        return '校级管理员'
    }
}

if (isEmptyObject(store.state.userInfo)) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
} else {
    user.value = store.state.userInfo
    assignment()
}

eventEmitter.on(APIEventEnum.getAdminInfoSuccess, 'getAdminInfoSuccess', (data) => {
    user.value = data
    assignment()
})


// 判断是否为只读模式
const readOnly = ref(true)

// 保存修改后的信息
function save() {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminInfo, {
        adminId: store.state.adminId,
        contact: contact.value,
    })
    user.value.contact = contact.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setAdminInfo, user.value)
    readOnly.value = !readOnly.value
}

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getAdminInfoSuccess, 'getAdminInfoSuccess')
})
</script>