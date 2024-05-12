<style scoped>
/* 顶部固定标签栏样式 */
.labels {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    margin-left: 10%;
}

.loginBox {
    width: 10%;
    justify-content: center;
    align-items: center;
    display: flex;
}

.t-button {
    width: 50%;
}
</style>

<template>
    <!-- 固定顶部标签栏 -->
    <fixedLabelBar>
        <div class="labels">
            <t-tabs :value="routerNames" @change="tabsChange">
                <t-tab-panel value="home">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="home" size="large" />
                            <span>首页</span>
                        </div>
                    </template>
                </t-tab-panel>
                <t-tab-panel value="examine">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="examine" size="large" />
                            <span>审批界面</span>
                        </div>
                    </template>
                </t-tab-panel>
                <t-tab-panel value="manage">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="manage" size="large" />
                            <span>管理界面</span>
                        </div>
                    </template>
                </t-tab-panel>
                <t-tab-panel value="personal">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="user" size="large" />
                            <span>管理员信息</span>
                        </div>
                    </template>
                </t-tab-panel>
            </t-tabs>
        </div>
        <div class="loginBox">
            <t-button v-if="!isLogin" theme="primary" shape="square" variant="outline" @click="goLogin">登录</t-button>
            <t-button v-else theme="primary" shape="square" variant="outline" disabled>已登录</t-button>
        </div>
    </fixedLabelBar>

    <RouterView />
</template>

<script setup>
import { computed, ref } from 'vue';
import { IconFont } from 'tdesign-icons-vue-next';
import fixedLabelBar from '../components/FixedLabelBar.vue';
import eventEmitter from '../utils/eventEmitter';
import { RouterEventEnum, StoreEnum, StoreEventEnum } from '../Enum';
import store from '@/store';

const routerNames = ref(store.state.routeTabs.adminFirstPageTabs)

// 控制个人主页和首页之间的跳转
const tabsChange = (value) => {
    if (value === 'home') {
        const selfRoute = store.state.parentRoute.home + store.state.routeTabs.homeTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    } else if(value === 'examine'){
        const selfRoute = store.state.parentRoute.examine + store.state.routeTabs.examineTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    } else if(value === 'manage'){
        const selfRoute = store.state.parentRoute.manage + store.state.routeTabs.manageTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    } else if(value === 'personal'){
        const selfRoute = store.state.parentRoute.personal + store.state.routeTabs.personalTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    }
    routerNames.value = value;
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'firstPageTabs', value: value })
};
//管理员界面一定是登录后才能进入的，所以store.state.token一定非空
// 跳转到登录界面
const goLogin = () => {
    eventEmitter.emit(RouterEventEnum.push, '/login')
};


</script>