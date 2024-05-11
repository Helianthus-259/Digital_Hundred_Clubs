<style scoped>
/* 顶部固定标签栏样式 */
.backBox {
    width: 10%;
    justify-content: center;
    align-items: center;
    display: flex;
}

.labels {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
}

.joinBox {
    width: 10%;
    justify-content: center;
    align-items: center;
    display: flex;
}

/* 内容区样式 */
.contentBox {
    margin-top: 50px;
}
</style>

<template>
    <FixedLabelBar>
        <div class="backBox">
            <t-button theme="primary" variant="outline" size="small" @click="back2Home">返回首页</t-button>
        </div>
        <div class="labels">
            <t-tabs :value="routerNames" @change="onNextChange">
                <t-tab-panel v-for="(item, index) in tabPanelsNext" :key="index" :value="item.value"
                    :label="item.label" />
            </t-tabs>
        </div>
        <div class="joinBox">
            <t-button theme="primary" variant="outline" size="small" @click="openDialog">加入我们</t-button>
        </div>
    </FixedLabelBar>
    <div class="contentBox">
        <RouterView />
    </div>

    <!-- 申请加入社团弹窗 -->
    <myDialog ref="dialogRef">
        <template #header>
            加入社团申请表
        </template>
        <template #body>
            <t-cell-group bordered>
                <t-cell title="姓名" :note="name" />
                <t-cell title="性别" :note="gender" />
                <t-cell title="年龄" :note="age" />
                <t-cell title="学号" :note="studentID" />
                <t-cell title="学院" :note="faculty" />
                <t-cell title="邮箱" :note="email" />
                <t-cell title="手机号" :note="phone"/>
                <t-cell title="爱好" :note="hobby"/>
                <t-cell title="特长" :note="specialty"/>
            </t-cell-group>
        </template>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" size="small">发送</t-button>
            <t-button style="margin: 0 10px;" theme="light" size="small" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import { RouterEventEnum, StoreEnum, StoreEventEnum,APIEnum, APIEventEnum } from '@/Enum';
import FixedLabelBar from '@/components/FixedLabelBar.vue';
import myDialog from '@/components/myDialog.vue';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';

const routerNames = ref(store.state.routeTabs.clubTabs);
// 社团标签页
const tabPanelsNext = [
    {
        value: '',
        label: '社团简介',
    },
    {
        value: 'clubActivities',
        label: '社团动态',
    },
    {
        value: 'clubMembers',
        label: '成员组成',
    },
];
const onNextChange = (value) => {
    routerNames.value = value
    const parentRoute = store.state.parentRoute.club
    const selfRoute = parentRoute + value
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'clubTabs', value: value })
};
const phone = ref('')
const hobby = ref('')
const specialty = ref('')
const name = ref('')
const age = ref('')
const gender = ref('')
const studentID = ref('')
const faculty = ref('')
const email = ref('')
const user = ref({})

// 为上面定义的变量赋值
function assignment() {
    phone.value = user.value.phone
    hobby.value = user.value.hobby
    specialty.value = user.value.specialty
    name.value = user.value.name
    age.value = user.value.age + ''
    gender.value = user.value.gender
    studentID.value = user.value.studentID
    faculty.value = user.value.faculty
    email.value = user.value.email
}

function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

if (isEmptyObject(store.state.userInfo)) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getUserInfo, { uid: store.state.uid })
} 
else {
    user.value = store.state.userInfo
    assignment()
}

eventEmitter.on(APIEventEnum.getUserInfoSuccess, (data) => {
    user.value = data
    assignment()
})

// 申请加入社团弹窗
const dialogRef = ref(null);
const openDialog = () => {
    dialogRef.value.openDialog()
};
const closeDialog = () => {
    dialogRef.value.closeDialog()
}

// 返回首页
const back2Home = () => {
    eventEmitter.emit(RouterEventEnum.push, '/')
}
</script>