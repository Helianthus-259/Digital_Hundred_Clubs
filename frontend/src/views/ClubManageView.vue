<style scoped>
.t-layout {
    width: 100%;
    height: 700px;
}

.contentBox {
    margin: 10px;
    height: 100%;
    background-color: #ffffff;
    overflow-y: auto;
}
</style>

<template>
    <t-layout>
        <t-aside>
            <t-menu theme="light" :value="routerNames" style="margin-right: 50px" height="550px" @change="onNextChange">
                <template #logo>
                    <img width="136" class="logo" src="https://www.tencent.com/img/index/menu_logo_hover.png"
                        alt="logo" />
                </template>
                <t-menu-item v-for="item in tabPanelsNext" :value="item.value">
                    <template #icon>
                        <t-icon :name="item.icon" />
                    </template>
                    {{ item.label }}
                </t-menu-item>
            </t-menu>
        </t-aside>
        <t-layout>
            <t-content>
                <div class="contentBox">
                    <div v-if="routerNames === 'edit'">
                        <ClubEdit />
                    </div>
                    <div v-else-if="routerNames === 'publish'">
                        <ClubPublish />
                    </div>
                    <div v-else-if="routerNames === 'check'">
                        <ClubCheck />
                    </div>
                    <div v-else-if="routerNames === 'apply'">
                        <ClubApply />
                    </div>
                </div>
            </t-content>
        </t-layout>
    </t-layout>
</template>

<script setup>
import ClubEdit from '@/components/clubManageViewChildren/ClubEdit.vue';
import ClubCheck from '@/components/clubManageViewChildren/ClubCheck.vue';
import ClubApply from '@/components/clubManageViewChildren/ClubApply.vue';
import ClubPublish from '@/components/clubManageViewChildren/ClubPublish.vue';
import { ref } from 'vue';

const routerNames = ref('edit')
// 社团标签页
const tabPanelsNext = [
    {
        value: 'edit',
        label: '编辑社团',
        icon: 'dashboard',
    },
    {
        value: 'publish',
        label: '发布活动/通知',
        icon: 'server',
    },
    {
        value: 'check',
        label: '入社审核',
        icon: 'root-list',
    },
    {
        value: 'apply',
        label: '年审/评优',
        icon: 'control-platform',
    },
]
const onNextChange = (value) => {
    routerNames.value = value
}

</script>