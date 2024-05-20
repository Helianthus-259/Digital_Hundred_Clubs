<style scoped>
/* 标签栏样式 */
.tabsBox {
  width: 20%;
  margin-left: 10%;
}

/* 总内容 */
.contentContainer {
  margin-top: 50px;
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
  <div class="contentContainer">
    <div class="contentBox">
      <t-space direction="vertical" size="large">
        <t-tabs v-model="tabName">
          <t-tab-panel :value="'clubApproval'" label="社团审核" :destroy-on-hide="true">
            <p><ClubApproval></ClubApproval></p>
          </t-tab-panel>
          <t-tab-panel :value="'activityApproval'" label="活动审核" :destroy-on-hide="true">
            <p><ActivityApproval></ActivityApproval></p>
          </t-tab-panel>
        </t-tabs>
      </t-space>
    </div>
  </div>

<!--  <br>-->
<!--  <div class="contentContainer">-->
<!--    <div class="contentBox">-->
<!--      <t-layout>-->
<!--        <t-header style="margin-top: 10px">-->
<!--          <t-head-menu v-model="tabName" theme="light" @change="onChange">-->
<!--            <t-menu-item value=""> 社团审核 </t-menu-item>-->
<!--            <t-menu-item value="activityApproval"> 活动审核 </t-menu-item>-->
<!--          </t-head-menu>-->
<!--        </t-header>-->
<!--      </t-layout>-->
<!--      <br>-->
<!--      <div style="margin-top: 10px">-->
<!--        <RouterView />-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
</template>

<script setup>
import { RouterEventEnum, StoreEventEnum, StoreEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';
import FixedLabelBar from "@/components/FixedLabelBar.vue";
import ClubApproval from "@/components/AdminExamineChildren/ClubApproval.vue";
import ActivityApproval from "@/components/AdminExamineChildren/ActivityApproval.vue";

const tabName = ref("clubApproval")

const routerNames = ref(store.state.routeTabs.examineTabs)

const parentRoute = store.state.parentRoute.examine
// 审批页面的两个子页面

// const onChange = (value) => {
//   if(value === "clubApproval"){
//     routerNames.value = ''
//   }else if(value === "activityApproval"){
//     routerNames.value = 'activityApproval'
//   }
//   const selfRoute = parentRoute + value
//   eventEmitter.emit(RouterEventEnum.push, selfRoute)
//   eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'examineTabs', value: 'examine/'+value })
// }

</script>