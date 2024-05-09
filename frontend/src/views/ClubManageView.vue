<style scoped>
.labels {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.contentBox {
    margin-top: 50px;
}
</style>

<template>
    <FixedLabelBar>
        <div class="labels">
            <t-tabs :value="routerNames" @change="onNextChange">
                <t-tab-panel v-for="(item, index) in tabPanelsNext" :key="index" :value="item.value"
                    :label="item.label" />
            </t-tabs>
        </div>
    </FixedLabelBar>

    <div class="contentBox">
        <RouterView />
    </div>
</template>

<script setup>
import { RouterEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import FixedLabelBar from '@/components/FixedLabelBar.vue';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';

const routerNames = ref(store.state.routeTabs.clubManageTabs)
// 社团标签页
const tabPanelsNext = [
    {
        value: '',
        label: '编辑社团',
    },
    {
        value: 'publish',
        label: '发布活动/通知',
    },
    {
        value: 'check',
        label: '入社审核',
    },
    {
        value: 'apply',
        label: '年审/评优'
    },
]
const onNextChange = (value) => {
    routerNames.value = value
    const parentRoute = store.state.parentRoute.clubManage
    const selfRoute = parentRoute + value
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'clubManageTabs', value: value })
}

</script>