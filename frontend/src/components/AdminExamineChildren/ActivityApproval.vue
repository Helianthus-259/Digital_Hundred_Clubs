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
      <t-dialog
          v-model:visible="visibleModal"
          width="60%"
          top="20px"
          destroy-on-close=""
          :confirm-btn="null"
          :cancel-btn="null"
          :closeOnEscKeydown="false"
      >
        <t-layout>
          <t-content>
            <div class="clubEvaluationContainer">
              <div class="titleContainer">学生社团活动申请表</div>
              <div class="tableContainer">
                <t-row id="border">
                  <t-row>
                    <t-col :span="3">
                      <div class="text">学生社团名称</div>
                    </t-col>
                    <t-col :span="9" id="table">
                      {{ activity.clubName }}
                    </t-col>
                  </t-row>
                  <t-row id="table">
                    <t-col :span="3">
                      <div class="text">活动负责人</div>
                    </t-col>
                    <t-col :span="3" id="table">
                      {{activity.contactPerson}}
                    </t-col>
                    <t-col :span="3" id="table">
                      <div class="text">联系电话</div>
                    </t-col>
                    <t-col :span="3" id="table">
                      {{activity.contactPhone}}
                    </t-col>
                  </t-row>
                  <t-row id="table">
                    <t-col :span="3">
                      <div class="text">活动名称</div>
                    </t-col>
                    <t-col :span="9" id="table">
                      {{activity.activityName}}
                    </t-col>
                  </t-row>
                  <t-row id="table">
                    <t-col :span="3">
                      <div class="text">活动时间</div>
                    </t-col>
                    <t-col :span="3" id="table">
                      {{ activity.activityStartTime }} - {{activity.activityEndTime}}
                    </t-col>
                    <t-col :span="3" id="table">
                      <div class="text">活动开展地点</div>
                    </t-col>
                    <t-col :span="3" id="table">
                      {{ activity.activityLocation }}
                    </t-col>
                  </t-row>
                  <t-row id="table">
                    <t-col :span="3">
                      <div class="text">活动简介</div>
                    </t-col>
                    <t-col :span="9" id="table">
                      {{activity.activityIntroduction}}
                    </t-col>
                  </t-row>
                  <t-row id="table">
                    <t-col :span="3">
                      <div class="text">活动附件</div>
                    </t-col>
                    <t-col :span="9" id="table">
                      {{activity.activityAttachment}}
                    </t-col>
                  </t-row>
                  <t-row id="table">
                    <t-col :span="3">
                      <div class="text" id="table">意见</div>
                    </t-col>
                    <t-col :span="9" id="table">
                      <t-textarea
                          v-model="opinion"
                          placeholder="请输入意见"
                          name="description"
                          :autosize="true"
                      />
                    </t-col>
                  </t-row>
                  <t-row id="table">
                    <t-col :span="6">
                      <t-button size="large" theme="success" @click="passActivityApproval">通过</t-button>
                    </t-col>
                    <t-col :span="6">
                      <t-button size="large" theme="danger" @click="unPassActivityApproval">驳回</t-button>
                    </t-col>
                  </t-row>
                </t-row>
              </div>
            </div>
          </t-content>
        </t-layout>
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
  null: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
  0: { label: '申请驳回', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  1: { label: '申请通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
};

const opinion = ref("")
const activities = []
const data = ref([]);
const visibleModal = ref(false)
const activity = ref({
  activityId:'',
  activityName: "",
  clubName: "",
  activityLocation: "",
  createTime: "",
  status: '',
  activityStartTime: "",
  activityEndTime: "",
  activityIntroduction: "",
  activityAttachment: "",
  contactPerson:'',
  contactPhone:'',
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
  data.value = activities.value.filter(item => item.status === null)
  pagination.value.total = data.value.length
}

eventEmitter.on(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess', (data) => {
  activity.value.activityId = data.activityId
  activity.value.activityName = data.activityName
  activity.value.clubName = data.clubName
  activity.value.activityLocation = data.activityLocation
  activity.value.createTime = data.createTime
  activity.value.status = data.status
  activity.value.activityStartTime = data.activityStartTime
  activity.value.activityEndTime = data.activityEndTime
  activity.value.activityIntroduction = data.activityIntroduction
  activity.value.activityAttachment = data.activityAttachment
  activity.value.contactPerson =  data.contactPerson
  activity.value.contactPhone = data.contactPhone
  console.log(activity.value)
})
const passActivityApproval = () => {
  eventEmitter.emit(APIEventEnum.request, APIEnum.passActivityApproval, {
    recordId:activity.value.activityId,
    opinion: opinion.value
  })
}

const unPassActivityApproval = () => {
  eventEmitter.emit(APIEventEnum.request, APIEnum.unPassActivityApproval, {
    recordId:activity.value.activityId,
    opinion: opinion.value
  })
}

eventEmitter.on(APIEventEnum.passActivityApprovalSuccess, 'passActivityApprovalSuccess', ()=>{
  console.log("通过成功")
  visibleModal.value = false
})

eventEmitter.on(APIEventEnum.unPassActivityApprovalSuccess, 'unPassActivityApprovalSuccess', ()=>{
  console.log("驳回成功")
  visibleModal.value = false
})



onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getActivitiesInfoSuccess, 'getActivitiesInfoSuccess')
  eventEmitter.off(APIEventEnum.getActivityInfoSuccess, 'getActivityInfoSuccess')
  eventEmitter.off(APIEventEnum.passActivityApprovalSuccess, 'passActivityApprovalSuccess')
  eventEmitter.off(APIEventEnum.unPassActivityApprovalSuccess, 'unPassActivityApprovalSuccess')
})

</script>


<style scoped>
.clubEvaluationContainer {
  width: 100%;
  height: 600px;
  background: #ffffff;
  border-radius: 10px;
  overflow-y: auto;
}

/*滚动条样式*/
.clubEvaluationContainer::-webkit-scrollbar {
  width: 8px;
  /* 滚动条宽度 */
}

.clubEvaluationContainer::-webkit-scrollbar-track {
  background-color: #f1f1f1;
  /* 滚动条轨道背景色 */
}

.clubEvaluationContainer::-webkit-scrollbar-thumb {
  background-color: #888;
  /* 滚动条滑块颜色 */
  border-radius: 4px;
  /* 滚动条滑块圆角 */
}

.clubEvaluationContainer::-webkit-scrollbar-thumb:hover {
  background-color: #555;
  /* 鼠标hover时滑块颜色 */
}

.titleContainer {
  height: 60px;
  font-size: 24px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
}

.tableContainer {
  width: 100%;
  margin: 0 auto;
}

.t-row {
  width: 100%;
  align-items: center;
}

.t-row#border {
  border: 2px solid #000;
}

.t-row#table {
  border-top: 2px solid #000;
}

.t-col {
  min-height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.t-col#table {
  border-left: 2px solid #000;
}

.text {
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
}
</style>
