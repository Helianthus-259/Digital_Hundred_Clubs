<template>
  <t-layout>
    <t-aside>
      <t-menu theme="light" v-model="value" height="700px" width="200px">
        <t-menu-item value="item0">全部社团</t-menu-item>
        <t-menu-item value="item1">待审核</t-menu-item>
        <t-menu-item value="item2">已通过</t-menu-item>
        <t-menu-item value="item3">未通过</t-menu-item>
      </t-menu>
    </t-aside>
    <t-content>
      <t-header>
        <t-head-menu default-value="2-1" expand-type="popup">
          <t-submenu value="1" title="校区">
            <t-submenu value="GuangZhou" title="广州校区">
              <t-menu-item value="north"> 北校区 </t-menu-item>
              <t-menu-item value="south"> 南校区 </t-menu-item>
              <t-menu-item value="east"> 东校区 </t-menu-item>
            </t-submenu>
            <t-menu-item value="ZhuHai"> 珠海校区 </t-menu-item>
            <t-menu-item value="ShenZhen"> 深圳校区 </t-menu-item>
          </t-submenu>
          <t-submenu value="2" title="类型">
            <t-menu-item value="2-1"> 体育类 </t-menu-item>
            <t-menu-item value="2-2"> 游戏类 </t-menu-item>
            <t-menu-item value="2-3"> 艺术类 </t-menu-item>
          </t-submenu>
        </t-head-menu>
      </t-header>
      <div>
        <t-table row-key="index" :data="data" :columns="columns" :hide-sort-tips="false" :stripe="stripe"
          :bordered="bordered" :hover="hover" :table-layout="tableLayout ? 'auto' : 'fixed'" :size="size"
          :pagination="pagination" :show-header="showHeader" cell-empty-content="-" resizable="" lazy-load="">
          <template #operation="{ row }">
            <t-button theme="primary" @click="detail(row)">申请详情</t-button>
          </template>
        </t-table>
      </div>
    </t-content>
  </t-layout>
  <t-dialog v-model:visible="visibleModal" width="60%" top="20px" destroy-on-close="" :on-confirm="onConfirm">
    <t-layout>
      <t-header>
        {{ clubInfo.clubName }}
      </t-header>
      <t-content>
        <t-descriptions :column="2" size="large">
          <t-descriptions-item label="社团类别">{{ clubInfo.clubCategory }}</t-descriptions-item>
          <t-descriptions-item label="社团描述">{{ clubInfo.clubDescription }}</t-descriptions-item>
          <t-descriptions-item label="校区">{{ clubInfo.mainCompus }}</t-descriptions-item>
          <t-descriptions-item label="负责部门">{{ clubInfo.responsibleDepartment }}</t-descriptions-item>
          <t-descriptions-item label="业务指导老师">{{ clubInfo.businessGuideTeacher }}</t-descriptions-item>
          <t-descriptions-item label="行政指导老师">{{ clubInfo.adminGuideTeacher }}</t-descriptions-item>
          <t-descriptions-item label="联系人姓名">{{ clubInfo.contactPerson }}</t-descriptions-item>
          <t-descriptions-item label="联系电话">{{ clubInfo.contactPhone }}</t-descriptions-item>
          <t-descriptions-item label="附件">{{ clubInfo.file }}</t-descriptions-item>
          <t-descriptions-item label="申请时间">{{ clubInfo.establishmentDate }}</t-descriptions-item>
        </t-descriptions>
      </t-content>
      <t-footer>

      </t-footer>
    </t-layout>
  </t-dialog>
</template>

<script lang="jsx" setup>
import { onUnmounted, ref } from 'vue';


const value = ref('item1');
import { ErrorCircleFilledIcon, CheckCircleFilledIcon, CloseCircleFilledIcon } from 'tdesign-icons-vue-next';
import store from "@/store/index.js";
import eventEmitter from "@/utils/eventEmitter.js";
import { APIEnum, APIEventEnum } from "@/Enum/index.js";

//对话框
const visibleModal = ref(false);
const clubInfo = ref({
  "clubName": '篮球社',
  "establishmentDate": '2022-05-01',
  "responsibleDepartment": '体育部',
  "mainCompus": "广州校区",
  "clubDescription": '篮球社是一个篮球社团',
  "clubCategory": '体育类',
  "adminGuideTeacher": '张老师',
  "businessGuideTeacher": '李老师',
  "contactPerson": '张三',
  "contactPhone": '123456789',
  "clubStatus": 1,
  "file": "file",
});

// 表格
const statusNameListMap = {
  0: { label: '审批失败', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  1: { label: '审批通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
  2: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
};
const clubsData = []
const data = []
const total = ref(0)
function assignment() {
  total.value = clubsData.value.length
  for (let i = 0; i < total.value; ++i) {
    data.push({
      index: clubsData.value[i].clubId,
      clubName: clubsData.value[i].clubName,
      campus: clubsData.value[i].campus,
      clubCategory: clubsData.value[i].clubCategory,
      createTime: clubsData.value[i].createTime,
      status: clubsData.value[i].status,
    })
  }
}

if (Object.keys(store.state.clubsData).length === 0) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubsInfo)
} else {
  clubsData.value = store.state.clubsData
  assignment()
}
eventEmitter.on(APIEventEnum.getClubsInfoSuccess, 'getClubsInfoSuccess', (data) => {
  clubsData.value = data
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
  { colKey: 'campus', title: '校区' },
  { colKey: 'clubCategory', title: '社团种类', ellipsis: true },
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


const pagination = {
  defaultCurrent: 1,
  defaultPageSize: 5,
  total: total.value,
};

const detail = (value) => {
  visibleModal.value = true;
  // 获取社团信息
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubEvaluateInfo, { value: value.index })

}

eventEmitter.on(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess', (data) => {
  console.log(data)
  clubInfo.value.clubName = data.clubName
  clubInfo.value.clubCategory = data.clubCategory
  clubInfo.value.mainCompus = data.mainCompus
  clubInfo.value.clubDescription = data.clubDescription
  clubInfo.value.file = data.file
  clubInfo.value.adminGuideTeacher = data.administrativeGuideTeacherName
  clubInfo.value.businessGuideTeacher = data.businessGuideTeacherName
  clubInfo.value.establishmentDate = data.establishmentDate
  clubInfo.value.contactPerson = data.contactPerson
  clubInfo.value.contactPhone = data.contactPhone
  clubInfo.value.clubStatus = data.clubStatus
  clubInfo.value.responsibleDepartment = data.responsibleDepartment
})

const onConfirm = (text) => {
  console.log("确定", text);
}


onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getClubsInfoSuccess, 'getClubsInfoSuccess')
  eventEmitter.off(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubEvaluateInfoSuccess')
})
</script>

<style></style>