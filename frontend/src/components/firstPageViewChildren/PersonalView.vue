<style scoped>
/* 标签栏样式 */
.tabsBox {
    margin-top: 50px;
    width: auto;
    margin-left: 10%;
}

/* 总内容 */
.contentContainer {
    width: 100%;
}

.contentBox {
    width: 80%;
    margin: 0 auto;
    background-color: #f5f5f5;
    padding: 5px;
    box-sizing: border-box;
    border-radius: 4px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    min-height: 600px;
}
</style>

<template>
    <div class="tabsBox">
        <t-tabs :value="routerNames" :list="routerlist" :space-evenly="false" @change="onChange" />
    </div>
    <div class="contentContainer">
        <div class="contentBox">
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import { RouterEventEnum, StoreEventEnum, StoreEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';



const routerNames = ref(store.state.routeTabs.personalTabs)

const parentRoute = store.state.parentRoute.personal
// 个人页面的三个子页面
const routerlist = [
    { label: '个人信息', value: '' },
    { label: '参加的社团', value: 'participatedClubs' },
    { label: '参加的活动', value: 'participatedActivities' },
]

const onChange = (value) => {
    routerNames.value = value
    const selfRoute = parentRoute + value
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'personalTabs', value: value })
}

</script>