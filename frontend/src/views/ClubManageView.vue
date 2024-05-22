<style scoped>
.t-layout {
    width: 100%;
}

.t-layout__aside {
    height: 700px;
}

.t-layout__content {
    min-height: 700px;
}

.contentBox {
    margin: 10px;
    height: 100%;
}

.t-layout :deep(.t-layout__footer) {
    padding: 0;
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
                <t-menu-item value="edit">
                    <template #icon>
                        <t-icon name="usergroup" />
                    </template>
                    社团
                </t-menu-item>
                <t-submenu value="publish" title="事务">
                    <template #icon>
                        <t-icon name="server" />
                    </template>
                    <t-menu-item value="publishActivity">
                        <template #icon>
                            <t-icon name="activity" />
                        </template>
                        发布活动
                    </t-menu-item>
                    <t-menu-item value="publishNotice">
                        <template #icon>
                            <t-icon name="notification-add" />
                        </template>
                        发布通知
                    </t-menu-item>
                </t-submenu>
                <t-menu-item value="check">
                    <template #icon>
                        <t-icon name="root-list" />
                    </template>
                    审核
                </t-menu-item>
                <t-submenu value="apply" title="先进">
                    <template #icon>
                        <t-icon name="control-platform" />
                    </template>
                    <t-menu-item value="annualaudit">
                        <template #icon>
                            <t-icon name="chart-line" />
                        </template>
                        年审
                    </t-menu-item>
                    <t-menu-item value="evaluation">
                        <template #icon>
                            <t-icon name="portrait" />
                        </template>
                        评优
                    </t-menu-item>
                </t-submenu>
            </t-menu>
        </t-aside>
        <t-layout>
            <t-content>
                <div class="contentBox">
                    <div v-if="routerNames === 'edit'">
                        <ClubEdit />
                    </div>
                    <div v-else-if="routerNames === 'publishActivity'">
                        <ClubPublishActivity />
                    </div>
                    <div v-else-if="routerNames === 'publishNotice'">
                        <ClubPublishNotice />
                    </div>
                    <div v-else-if="routerNames === 'check'">
                        <ClubCheck />
                    </div>
                    <div v-else-if="routerNames === 'evaluation'">
                        <ClubEvaluation />
                    </div>
                    <div v-else-if="routerNames === 'annualaudit'">
                        <ClubAnnualAudit />
                    </div>
                </div>
            </t-content>
            <!-- 占地的 -->
            <t-footer>
            </t-footer>
        </t-layout>
    </t-layout>
</template>

<script setup>
import ClubEdit from '@/components/clubManageViewChildren/ClubEdit.vue';
import ClubCheck from '@/components/clubManageViewChildren/ClubCheck.vue';
import ClubAnnualAudit from '@/components/clubManageViewChildren/ClubAnnualAudit.vue';
import ClubPublishActivity from '@/components/clubManageViewChildren/ClubPublishActivity.vue';
import ClubPublishNotice from '@/components/clubManageViewChildren/ClubPublishNotice.vue';
import ClubEvaluation from '@/components/clubManageViewChildren/ClubEvaluation.vue';
import { ref } from 'vue';

const routerNames = ref('edit')
const onNextChange = (value) => {
    routerNames.value = value
}

</script>