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
    right: 100px;
}
</style>

<template>
    <t-sticky :offset-top="40">
        <t-button theme="primary" size="large" class="common-button">参加活动</t-button>
    </t-sticky>
    <div class="activityContainer">
        <div class="activityTitle">{{ activity.activityName }}</div>
        <div class="activityContent" v-html="activity.activityIntroduction"></div>
        <div class="activityFooter">
            <p>活动开始时间: {{ activity.activityStartTime }}</p>
            <p>活动截止时间: {{ activity.activityEndTime }}</p>
            <p>活动地点: {{ activity.activityLocation }}</p>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import { onUnmounted, ref } from 'vue';
import eventEmitter from '@/utils/eventEmitter';
import { useRoute } from 'vue-router';

const route = useRoute();
const activityId = route.params.aid;

eventEmitter.emit(APIEventEnum.request, APIEnum.getActivityInfo, { activityId: activityId })
const activity = ref({})
eventEmitter.on(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess', (data) => {
    activity.value = data
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess')
})
</script>