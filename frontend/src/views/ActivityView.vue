<style scoped>
.activityContainer {
    max-width: 800px;
    margin: 0 auto;
    padding: 30px;
    border: 1px solid #ddd;
    border-radius: 10px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    background-color: #fff;
}

.activityTitle {
    font-size: 32px;
    font-weight: bold;
    color: #333;
    text-align: center;
    margin-bottom: 20px;
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

.activityContent {
    font-size: 18px;
    line-height: 1.8;
    margin-bottom: 30px;
    color: #555;
}

.activityContent :deep(p) {
    font-family: Georgia, serif;
    font-size: 18px;
    line-height: 1.8;
    margin-bottom: 25px;
    color: #444;
    text-indent: 2em;
    /* 添加首行缩进 */
}

.activityContent :deep(img) {
    max-width: 100%;
    height: auto;
    display: block;
    margin: 30px auto;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.activityContent :deep(ul) {
    list-style: none;
    padding: 0;
}

.activityContent :deep(li) {
    position: relative;
    padding-left: 35px;
    margin-bottom: 15px;
    font-family: Arial, sans-serif;
    color: #666;
}

.activityContent :deep(li:before) {
    content: "✦";
    position: absolute;
    left: 0;
    color: #8bc34a;
    font-size: 24px;
    line-height: 1;
}

.activityFooter {
    font-size: 16px;
    color: #777;
    text-align: center;
}

.activityFooter p {
    margin: 8px 0;
}

.common-button {
    position: absolute;
    top: 20px;
    right: 100px;
}
</style>

<template>
    <t-button theme="primary" variant="outline" size="large" class="common-button" @click="openDialog">参加活动</t-button>
    <div class="activityContainer">
        <div class="activityTitle">{{ activity.activityName }}</div>
        <div class="activityContent" v-html="activity.activityIntroduction"></div>
        <div class="activityFooter">
            <p>活动开始时间: {{ activity.activityStartTime }}</p>
            <p>活动截止时间: {{ activity.activityEndTime }}</p>
            <p>活动地点: {{ activity.activityLocation }}</p>
        </div>
    </div>

    <myDialog ref="dialogRef">
        <template #header>
            <div
                style="font-size: 24px; font-weight: bold; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: #333">
                {{ activity.activityName }}报名表</div>
        </template>
        <t-form style="margin-top: 30px;" colon>
            <t-form-item label="姓名">
                <t-input style="width: 90%;" v-model="userInnfo.stName" placeholder="请输入姓名" />
            </t-form-item>
            <t-form-item label="学号">
                <t-input style="width: 90%;" v-model="userInnfo.studentNumber" placeholder="请输入学号" />
            </t-form-item>
        </t-form>
        <template #footer>
            <t-button style="margin: 0 10px;" theme="primary" variant="outline" @click="sendRequest">发送</t-button>
            <t-button style="margin: 0 10px;" theme="default" variant="outline" @click="closeDialog">关闭</t-button>
        </template>
    </myDialog>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import { onMounted, onUnmounted, ref } from 'vue';
import eventEmitter from '@/utils/eventEmitter';
import { useRoute } from 'vue-router';
import myDialog from '@/components/myDialog.vue';
import { MessagePlugin } from 'tdesign-vue-next';

const route = useRoute();
const activityId = route.params.aid;
const activity = ref({})
const dialogRef = ref(null)
const userInnfo = ref({
    stName: '',
    studentNumber: ''
})
const openDialog = () => {
    dialogRef.value.openDialog()
}

const closeDialog = () => {
    dialogRef.value.closeDialog()
}

const sendRequest = () => {
    if (userInnfo.value.stName === '' || userInnfo.value.studentNumber === '') {
        return MessagePlugin.warning('请输入姓名和学号')
    }
    eventEmitter.emit(APIEventEnum.request, APIEnum.postJoinActivity, { activityId, studentNumber: userInnfo.value.studentNumber })
}


onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getActivityInfo, { activityId: activityId })
    eventEmitter.on(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess', (data) => {
        activity.value = data
    })
    eventEmitter.on(APIEventEnum.postJoinActivitySuccess, 'postJoinActivitySuccess', () => {
        closeDialog()
        MessagePlugin.success('报名成功')
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess')
    eventEmitter.off(APIEventEnum.postJoinActivitySuccess, 'postJoinActivitySuccess')
})
</script>