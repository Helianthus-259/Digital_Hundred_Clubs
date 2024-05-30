<template>
  <t-layout>
    <t-aside>
      <t-menu theme="light" v-model="value" height="700px" width="200px">
        <t-menu-item value="item0" @click="getAllActivities">全部活动</t-menu-item>
        <t-menu-item value="item1" @click="getPendingActivities">待审批</t-menu-item>
        <t-menu-item value="item2" @click="getPassedActivities">已通过</t-menu-item>
        <t-menu-item value="item3" @click="getUnPassedActivities">已驳回</t-menu-item>
      </t-menu>
    </t-aside>
    <t-layout>
      <t-header>
        <t-space direction="horizontal">
          <t-input style="width: 500px" placeholder="请输入搜索内容" />
          <t-button theme="primary">
            <template #icon>
              <SearchIcon />
            </template>
            搜索
          </t-button>
        </t-space>
      </t-header>
      <t-content>
        <t-table row-key="index" :data="data" :columns="columns" :hide-sort-tips="false" :stripe="stripe"
          :bordered="bordered" :hover="hover" :table-layout="tableLayout ? 'auto' : 'fixed'" :size="size"
          :pagination="pagination" :show-header="showHeader" cell-empty-content="-" resizable="" lazy-load="">
          <template #operation="{ row }">
            <t-button theme="primary" @click="detail(row)">活动详情</t-button>
          </template>
        </t-table>
      </t-content>
    </t-layout>
  </t-layout>
  <t-layout>
      <t-dialog v-model:visible="visibleModal" width="60%" top="20px" destroy-on-close="" :on-confirm="onConfirm">
        <t-descriptions :title="'活动详情'" :column="2">
          <t-descriptions-item label="活动名称">{{ activity.activityName }}</t-descriptions-item>
          <t-descriptions-item label="活动社团">{{ activity.clubName }}</t-descriptions-item>
          <t-descriptions-item label="申请时间">{{ activity.createTime }}</t-descriptions-item>
          <t-descriptions-item label="活动开始时间">{{ activity.activityStartTime }}</t-descriptions-item>
          <t-descriptions-item label="活动结束时间">{{ activity.activityEndTime }}</t-descriptions-item>
          <t-descriptions-item label="活动地点">{{ activity.activityLocation }}</t-descriptions-item>
          <t-descriptions-item label="活动附件">{{ activity.activityAttachment }}</t-descriptions-item>
          <t-descriptions-item label="活动审核状态">{{ activity.status }}</t-descriptions-item>
        </t-descriptions>
        <t-descriptions>
          <t-descriptions-item label="活动介绍">{{ activity.activityIntroduction }}</t-descriptions-item>
        </t-descriptions>
      </t-dialog>
  </t-layout>
</template>

<script lang="jsx" setup>
import { onUnmounted, ref } from 'vue';


const value = ref('item0');
import { ErrorCircleFilledIcon, CheckCircleFilledIcon, CloseCircleFilledIcon, SearchIcon } from 'tdesign-icons-vue-next';
import eventEmitter from "@/utils/eventEmitter.js";
import { APIEnum, APIEventEnum } from "@/Enum/index.js";

// 表格
const statusNameListMap = {
  0: { label: '申请通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
  1: { label: '申请驳回', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  2: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
};

const activities = []
const data = ref([]);
const visibleModal = ref(false)
const activity = ref({
  activityName: "",
  clubName: "",
  activityLocation: "",
  createTime: "",
  status: '',
  activityStartTime: "",
  activityEndTime: "",
  activityIntroduction: "",
  activityAttachment: "",
})

function assignment() {
  data.value = activities.value
  pagination.value.total = activities.value.length
}
eventEmitter.emit(APIEventEnum.request, APIEnum.getActivitiesInfo)
eventEmitter.on(APIEventEnum.getActivitiesInfoSuccess, 'getActivitiesInfoSuccess', (data) => {
  activities.value = data.data
  assignment()
})



const stripe = ref(true);
const bordered = ref(true);
const hover = ref(false);
const tableLayout = ref(false);
const size = ref('medium');
const showHeader = ref(true);

const columns = ref([
  { colKey: 'clubName', title: '社团名称', width: '100' },
  { colKey: 'activityName', title: '活动名称', width: '100' },
  { colKey: 'activityLocation', title: '活动地点', width: '100' },
  { colKey: 'createTime', title: '申请时间' },
  {
    colKey: 'status',
    title: '审批状态',
    cell: (h, { row }) => {
      return (
        <t-tag shape="round" theme={statusNameListMap[row.status].theme} variant="light-outline">
          {statusNameListMap[row.status].icon}
          {statusNameListMap[row.status].label}
        </t-tag>
      );
    },
  },
  { colKey: 'operation', title: '申请详情' }
]);


const pagination = ref({
  defaultCurrent: 1,
  defaultPageSize: 5,
  total: 5,
});

const detail = (value) => {
  console.log(value)
  eventEmitter.emit(APIEventEnum.request, APIEnum.getActivityInfo, { activityId: value.activityId })
  visibleModal.value = true;
}

const getAllActivities = () => {
  data.value = activities.value
  pagination.value.total = data.value.length
}

const getPassedActivities = () => {
  data.value = activities.value.filter(item => item.status === 1)
  pagination.value.total = data.value.length
}

const getUnPassedActivities = () => {
  data.value = activities.value.filter(item => item.status === 0)
  pagination.value.total = data.value.length
}

const getPendingActivities = () => {
  data.value = activities.value.filter(item => item.status === 1)
  pagination.value.total = data.value.length
}

eventEmitter.on(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess', (data) => {
  console.log(data)
  activity.value.activityName = data.activityName
  activity.value.clubName = data.clubName
  activity.value.activityLocation = data.activityLocation
  activity.value.createTime = data.createTime
  activity.value.status = data.status
  activity.value.activityStartTime = data.activityStartTime
  activity.value.activityEndTime = data.activityEndTime
  activity.value.activityIntroduction = data.activityIntroduction
  activity.value.activityAttachment = data.activityAttachment
})

const onConfirm = (text) => {
  console.log("确定", text);
}

onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getActivitiesInfoSuccess, 'getActivitiesInfoSuccess')
  eventEmitter.off(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess')
})

</script>


<style></style>
