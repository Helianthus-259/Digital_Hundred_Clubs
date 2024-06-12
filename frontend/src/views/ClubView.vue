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
        <div class="joinBox" :style="{ display: store.state.studentId>0 ?'':'none'}"><!--管理员不允许加入社团-->
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
        <t-form style="width: 600px;" colon>
            <t-form-item label="姓名">
                {{ user.stName }}
            </t-form-item>
            <t-form-item label="学号">
                {{ user.studentNumber }}
            </t-form-item>
            <t-form-item label="学院">
                {{ user.college }}
            </t-form-item>
            <t-form-item label="年级">
                {{ user.grade }}
            </t-form-item>
            <t-form-item label="年级">
                {{ user.grade }}
            </t-form-item>
            <t-form-item label="邮箱">
                {{ user.email }}
            </t-form-item>
            <t-form-item label="手机号">
                {{ user.contact }}
            </t-form-item>
            <t-form-item label="特长">
                {{ user.specialty }}
            </t-form-item>
            <t-form-item requiredMark label="申请理由">
                <t-textarea style="width: 80%;" v-model="reason" placeholder="请输入申请理由"
                    :autosize="{ minRows: 3, maxRows: 5 }" />
            </t-form-item>
        </t-form>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="sendRequest">发送</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import { RouterEventEnum, StoreEnum, StoreEventEnum, APIEnum, APIEventEnum } from '@/Enum';
import FixedLabelBar from '@/components/FixedLabelBar.vue';
import myDialog from '@/components/myDialog.vue';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { MessagePlugin } from 'tdesign-vue-next';
import { onMounted, onUnmounted, ref } from 'vue';

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
const user = ref({})
const reason = ref('')

function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 申请加入社团弹窗
const dialogRef = ref(null);
const openDialog = () => {
    dialogRef.value.openDialog()
};
const closeDialog = () => {
    dialogRef.value.closeDialog()
}
const sendRequest = () => {
    if (reason.value === '') {
        return MessagePlugin.warning('申请理由不能为空')
    }
    eventEmitter.emit(APIEventEnum.request, APIEnum.postJoinClub, { studentId: store.state.studentId, clubId: store.state.clubId, reason: reason.value })
}

// 返回首页
const back2Home = () => {
    if(store.state.studentId>0)
        eventEmitter.emit(RouterEventEnum.push, '/')
    else
        eventEmitter.emit(RouterEventEnum.push, '/adminFirstPage')//若为管理员，则返回管理员首页
    
}

onMounted(() => {
    if (isEmptyObject(store.state.userInfo)) {
        eventEmitter.emit(APIEventEnum.request, APIEnum.getUserInfo, { uid: store.state.uid })
    }
    else {
        user.value = store.state.userInfo
        console.log(user.value);
    }

    eventEmitter.on(APIEventEnum.getUserInfoSuccess, 'getUserInfoSuccess', (data) => {
        console.log(data);
        user.value = data
    })
    eventEmitter.on(APIEventEnum.postJoinClubSuccess, 'postJoinClubSuccess', () => {
        reason.value = ''
        MessagePlugin.success('申请成功')
        closeDialog()
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getUserInfoSuccess, 'getUserInfoSuccess')
})
</script>