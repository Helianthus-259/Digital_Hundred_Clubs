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

</style>

<template>
    <!-- 固定顶部标签栏 -->
    <fixedLabelBar>
        <div class="labels">
            <t-tabs :value="routerNames" @change="tabsChange">
                <t-tab-panel value="admin">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="admin" size="large" />
                            <span>首页</span>
                        </div>
                    </template>
                </t-tab-panel>
                <t-tab-panel value="examine">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="examine" size="large" />
                            <span>社团/活动</span>
                        </div>
                    </template>
                </t-tab-panel>
                <t-tab-panel value="review">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="review" size="large" />
                            <span>年审/评优</span>
                        </div>
                    </template>
                </t-tab-panel>
                <t-tab-panel value="adminPersonal">
                    <template #label>
                        <div class="label-content">
                            <icon-font name="adminPersonal" size="large" />
                            <span>管理员信息</span>
                        </div>
                    </template>
                </t-tab-panel>
            </t-tabs>
        </div>
        <div class="loginBox">
            <t-button v-if="!isLogin" theme="primary" shape="square" variant="outline" style="width: 50px" @click="goLogin">登录</t-button>
            <t-button v-else theme="primary" shape="square" variant="outline" style="width: 50%" @click="logOut">退出登录</t-button>
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
import {MessagePlugin} from "tdesign-vue-next";

const routerNames = ref(store.state.routeTabs.adminTabs)

// 控制管理员的首页/审核/管理/管理员信息之间的界面跳转
const tabsChange = (value) => {
    if (value === 'admin') {
        const selfRoute = store.state.parentRoute.admin + store.state.routeTabs.adminFirstPageTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    } else if(value === 'examine'){
        if(store.state.userInfo.sort === 0){
            MessagePlugin.warning("您没有权限访问,请联系管理员!")
            value = 'admin'
            return;
        }
        const selfRoute = store.state.parentRoute.admin + store.state.routeTabs.examineTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    } else if(value === 'review'){
        if(store.state.userInfo.sort === 0){
          MessagePlugin.warning("您没有权限访问,请联系管理员!")
          value = 'admin'
          return;
        }
        const selfRoute = store.state.parentRoute.admin + store.state.routeTabs.adminManageTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    } else {
        const selfRoute = store.state.parentRoute.adminPersonal + store.state.routeTabs.adminPersonalTabs
        eventEmitter.emit(RouterEventEnum.push, selfRoute)
    }
    routerNames.value = value;
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'adminTabs', value: value })
};

const isLogin = computed(() => {
    if (store.state.token) {
      return true
    } else {
      return false
    }
})

const goLogin = () => {
  eventEmitter.emit(RouterEventEnum.push, '/adminLogin')
};

const logOut = () => {
  eventEmitter.emit(StoreEventEnum.set, StoreEnum.reset)
  eventEmitter.emit(RouterEventEnum.push, '/adminFirstPage')
};

</script>