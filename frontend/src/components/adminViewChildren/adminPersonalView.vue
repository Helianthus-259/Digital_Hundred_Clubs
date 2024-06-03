<style scoped>
/* 标签栏样式 */
.tabsBox {
    width: 40%;
    margin-left: 10%;
    margin-top: 3%;
    background-color: #f0f8fe;
}

/* 总内容 */
.contentContainer {
    margin-top: 10px;
}

.contentBox {
    width: 80%;
    margin: 0 auto;
    background-color: #d8eeff;
    padding: 5px;
    box-sizing: border-box;
    border-radius: 4px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    min-height: 600px;
}
</style>

<template>
    <p>123</p>
    <t-sticky :offset-top="50">
        <div class="tabsBox">
            <t-tabs :value="routerNames" :list="routerlist" :space-evenly="false" @change="onChange" />
        </div>
    </t-sticky>
    <div class="contentContainer">
        <div class="contentBox">
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import { RouterEventEnum, APIEventEnum, APIEnum, StoreEventEnum, StoreEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';

const routerNames = ref(store.state.routeTabs.adminPersonalTabs)

const parentRoute = store.state.parentRoute.adminPersonal
// 个人页面的四个子页面
const routerlist = [
    { label: '管理员信息', value: 'adminPersonalInfo' },
    { label: '管理的社团', value: 'managedClub' },
    { label: '活动历史', value: 'clubActivitiesHistory' },
    { label: '审批记录', value: 'examHistory' },
]

const onChange = (value) => {
    routerNames.value = value
    const selfRoute = parentRoute + value
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'adminPersonalTabs', value: value })
}
</script>