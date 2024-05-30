<template>
  <t-layout>
    <t-aside>
      <t-menu theme="light" v-model="status" height="700px" width="200px">
        <t-menu-item value="all" @click="search">全部社团</t-menu-item>
        <t-menu-item value="2" @click="search">待审核</t-menu-item>
        <t-menu-item value="1" @click="search">已通过</t-menu-item>
        <t-menu-item value="0" @click="search">未通过</t-menu-item>
      </t-menu>
    </t-aside>
    <t-content>
      <t-header style="height: 100px">
        <t-space direction="vertical">
          <t-radio-group variant="primary-filled" v-model:value="campus" @click="search">
            <t-radio-button value="all">全部校区</t-radio-button>
            <t-radio-button value="南校区">南校区</t-radio-button>
            <t-radio-button value="东校区">东校区</t-radio-button>
            <t-radio-button value="北校区">北校区</t-radio-button>
            <t-radio-button value="深圳校区">深圳校区</t-radio-button>
            <t-radio-button value="珠海校区">珠海校区</t-radio-button>
          </t-radio-group>
          <t-radio-group variant="primary-filled" v-model:value="category" @click="search">
            <t-radio-button value="all">全部类别</t-radio-button>
            <t-radio-button value="学术类">学术类</t-radio-button>
            <t-radio-button value="体育类">体育类</t-radio-button>
            <t-radio-button value="艺术类">艺术类</t-radio-button>
            <t-radio-button value="公益类">公益类</t-radio-button>
            <t-radio-button value="科技类">科技类</t-radio-button>
            <t-radio-button value="其他类">其他类</t-radio-button>
          </t-radio-group>
        </t-space>
      </t-header>
      <t-space>
        <t-table row-key="index" :data="data" :columns="columns" :hide-sort-tips="false" :stripe="stripe"
          :bordered="bordered" :hover="hover" :table-layout="tableLayout ? 'auto' : 'fixed'" :size="size"
          :pagination="pagination" :show-header="showHeader" cell-empty-content="-" resizable="" lazy-load="">
          <template #operation="{ row }">
            <t-button theme="primary" @click="detail(row)">申请详情</t-button>
          </template>
        </t-table>
      </t-space>
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



import { ErrorCircleFilledIcon, CheckCircleFilledIcon, CloseCircleFilledIcon } from 'tdesign-icons-vue-next';
import store from "@/store/index.js";
import eventEmitter from "@/utils/eventEmitter.js";
import { APIEnum, APIEventEnum } from "@/Enum/index.js";
// 审核状态
const status = ref('all');
// 校区
const campus = ref('all');
// 类别
const category = ref('all');

//对话框
const visibleModal = ref(false);
const clubInfo = ref({
  clubName: '',
  establishmentDate: '',
  responsibleDepartment: '',
  mainCompus: "",
  clubDescription: '',
  clubCategory: '',
  adminGuideTeacher: '',
  businessGuideTeacher: '',
  contactPerson: '',
  contactPhone: '',
  clubStatus: '',
  file: "",
});

// 表格
const statusNameListMap = {
  0: { label: '审批失败', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  1: { label: '审批通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
  2: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
};
const clubsData = []
const data = ref([])
function assignment() {
  pagination.value.total = clubsData.value.length
  data.value = clubsData.value
}

if (Object.keys(store.state.clubsData).length === 0) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubsInfo)
} else {
  clubsData.value = store.state.clubsData
  data.value = clubsData.value
}
eventEmitter.on(APIEventEnum.getClubsInfoSuccess, 'getClubsInfoSuccess', (data) => {
  clubsData.value = data
  assignment()
})

const search = () => {
  data.value = clubsData.value.filter((item)=>{
    return (item.status.toString() === status.value || status.value === 'all') &&
        (item.campus === campus.value || campus.value === 'all') &&
        (item.clubCategory === category.value || category.value === 'all');
  })
  pagination.value.total = data.value.length
}

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


const pagination = ref({
  defaultCurrent: 1,
  defaultPageSize: 5,
  total: data.value.length,
});

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