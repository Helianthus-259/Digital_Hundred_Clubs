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
import { RouterEventEnum, StoreEventEnum, StoreEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';



const routerNames = ref(store.state.routeTabs.examineTabs)

const parentRoute = store.state.parentRoute.examine
// 审批页面的两个子页面
const routerlist = [
  { label: '新增社团审核', value: '' },
  { label: '活动申请审核', value: 'activityApproval' },
]

const onChange = (value) => {
  routerNames.value = value
  const selfRoute = parentRoute + value
  eventEmitter.emit(RouterEventEnum.push, selfRoute)
  eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'examineTabs', value: 'examine/'+value })
}

</script>