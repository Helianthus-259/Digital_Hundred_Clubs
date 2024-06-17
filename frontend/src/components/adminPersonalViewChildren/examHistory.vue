<template>
  <t-layout>
    <t-aside>
      <t-menu theme="light" v-model="status" height="700px" width="200px">
        <t-menu-item value="all" @click="search">全部社团</t-menu-item>
        <t-menu-item :value="null" @click="search">待审核</t-menu-item>
        <t-menu-item :value="1" @click="search">已通过</t-menu-item>
        <t-menu-item :value="0" @click="search">未通过</t-menu-item>
      </t-menu>
    </t-aside>
    <t-content>
      <t-header style="height: 100px">
        <t-space direction="vertical">
          <t-radio-group variant="primary-filled" v-model:value="campus" @click="search">
            <t-radio-button value="all">全部校区</t-radio-button>
            <t-radio-button value="广州南校园">广州南校园</t-radio-button>
            <t-radio-button value="广州东校园">广州东校园</t-radio-button>
            <t-radio-button value="广州北校园">广州北校园</t-radio-button>
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
            <t-radio-button value="其它类">其他类</t-radio-button>
          </t-radio-group>
        </t-space>
      </t-header>
      <t-space>
        <t-table row-key="index" :data="data" :columns="columns" :hide-sort-tips="false" :stripe="stripe"
                 :bordered="bordered" :hover="hover" :table-layout="tableLayout ? 'auto' : 'fixed'" :size="size"
                 :pagination="pagination" :show-header="showHeader" cell-empty-content="-" resizable="" lazy-load="">
          <template #operation="{ row }">
            <t-button theme="primary" :disabled="row.clubStatus !== null" @click="detail(row)">申请详情</t-button>
          </template>
        </t-table>
      </t-space>
    </t-content>
  </t-layout>
  <t-dialog v-model:visible="visibleModal" width="60%" top="20px" destroy-on-close="" :confirm-btn="null"
            :cancel-btn="null" :closeOnEscKeydown="false">
    <t-layout>
      <t-content>
        <div class="clubEvaluationContainer">
          <div class="titleContainer">学生社团申请表</div>
          <div class="tableContainer">
            <t-row id="border">
              <t-row>
                <t-col :span="2">
                  <div class="text">学生社团名称</div>
                </t-col>
                <t-col :span="2" id="table">
                  {{ clubInfo.clubName }}
                </t-col>
                <t-col :span="2" id="table">
                  <div class="text">发起人数</div>
                </t-col>
                <t-col :span="2" id="table">
                  1
                </t-col>
                <t-col :span="2" id="table">
                  <div class="text">所属校园(区)</div>
                </t-col>
                <t-col :span="2" id="table">
                  {{ clubInfo.mainCompus }}
                </t-col>
              </t-row>
              <t-row id="table">
                <t-col :span="2">
                  <div class="text">所属类别</div>
                </t-col>
                <t-col :span="10" id="table">
                  {{ clubInfo.clubCategory }}
                </t-col>
              </t-row>
              <t-row id="table">
                <t-col :span="2">
                  <div class="text" id="table">业务指导单位</div>
                </t-col>
                <t-col :span="2" id="table">
                  {{ clubInfo.responsibleDepartment }}
                </t-col>
                <t-col :span="2" id="table">
                  <div class="text">行政指导教师</div>
                </t-col>
                <t-col :span="2" id="table">
                  {{ clubInfo.adminGuideTeacher }}
                </t-col>
                <t-col :span="2" id="table">
                  <div class="text">业务指导教师</div>
                </t-col>
                <t-col :span="2" id="table">
                  {{ clubInfo.businessGuideTeacher }}
                </t-col>
              </t-row>
              <t-row id="table">
                <t-col :span="2">
                  <div class="text">联系人信息</div>
                </t-col>
                <t-col :span="1" id="table">
                  <div class="text">姓名</div>
                </t-col>
                <t-col :span="1" id="table">
                  {{ clubInfo.contactPerson }}
                </t-col>
                <t-col :span="2" id="table">
                  <div class="text">类别</div>
                </t-col>
                <t-col :span="2" id="table">
                  {{ clubInfo.contactPerson }}
                </t-col>
                <t-col :span="2" id="table">
                  <div class="text" id="table">手机</div>
                </t-col>
                <t-col :span="2" id="table">
                  {{ clubInfo.contactPhone }}
                </t-col>
              </t-row>
              <t-row id="table">
                <t-col :span="2">
                  <div class="text">学生社团简介</div>
                </t-col>
                <t-col v-html="clubInfo.clubDescription ? clubInfo.clubDescription[0].content : ''" :span="10" id="table"></t-col>
              </t-row>
              <t-row id="table">
                <t-col :span="2">
                  <div class="text">附件</div>
                </t-col>
                <t-col :span="5" id="table">
                  <t-link theme="primary" @click="getFile(clubInfo.attachmentUrl)">{{ clubInfo.attachmentUrl }}</t-link>
                </t-col>
                <t-col :span="5" id="table">
                  <t-link theme="primary" @click="getFile(clubInfo.advisorResumeAttachmentUrl)">{{ clubInfo.advisorResumeAttachmentUrl }}</t-link>
                </t-col>
              </t-row>
              <t-row id="table">
                <t-col :span="2">
                  <div class="text">意见</div>
                </t-col>
                <t-col :span="10" id="table">
                  <t-textarea v-model="collegeReviewOpinion" placeholder="请输入意见" name="description"
                              :autosize="true" />
                </t-col>
              </t-row>
              <t-row id="table">
                <t-col :span="6">
                  <t-button size="large" theme="success" @click="passClubApproval">通过</t-button>
                </t-col>
                <t-col :span="6">
                  <t-button size="large" theme="danger" @click="unPassClubApproval">驳回</t-button>
                </t-col>
              </t-row>
            </t-row>
          </div>
        </div>
      </t-content>
    </t-layout>
  </t-dialog>
</template>

<script lang="jsx" setup>
import { onMounted, onUnmounted, ref } from 'vue';



import { ErrorCircleFilledIcon, CheckCircleFilledIcon, CloseCircleFilledIcon } from 'tdesign-icons-vue-next';
import store from "@/store/index.js";
import eventEmitter from "@/utils/eventEmitter.js";
import { APIEnum, APIEventEnum } from "@/Enum/index.js";
import { NotifyPlugin } from "tdesign-vue-next";
import formatDate from '@/utils';
// 审核状态
const status = ref('all');
// 校区
const campus = ref('all');
// 类别
const category = ref('all');

//对话框
const visibleModal = ref(false);
const clubInfo = ref({
  recordId: '',
  clubId: '',
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
  attachmentUrl: "",
  advisorResumeAttachmentUrl: '',
});

// 表格
const statusNameListMap = {
  null: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
  0: { label: '审批失败', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  1: { label: '审批通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
};
const clubsData = ref([])
const data = ref([])
const collegeReviewOpinion = ref("")
function assignment() {
  pagination.value.total = clubsData.value.length
  data.value = clubsData.value
  console.log(data.value)
}

onMounted(() => {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubsInfo)
  eventEmitter.on(APIEventEnum.getClubsInfoSuccess, 'getClubsInfoSuccess', (data) => {
    console.log(data)
    clubsData.value = data.filter(item => {
      item.mainCampus = JSON.parse(localStorage.getItem('enumList')).mainCampuses[item.mainCampus].name
      item.clubCategory = JSON.parse(localStorage.getItem('enumList')).clubCategories[item.clubCategory].name
      item.establishmentDate = formatDate(new Date(item.establishmentDate), 'yyyy-MM-dd hh:mm:ss')
      return item.responsibleDepartmentId === store.state.userInfo.adminId
    })
    assignment()
  })
})

const search = () => {
  data.value = clubsData.value.filter((item) => {
    return (item.clubStatus === status.value || status.value === 'all') &&
        (item.mainCampus === campus.value || campus.value === 'all') &&
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
  { colKey: 'mainCampus', title: '校区' },
  { colKey: 'clubCategory', title: '社团种类', ellipsis: true },
  { colKey: 'establishmentDate', title: '申请时间' },
  {
    colKey: 'status',
    title: '审批状态',
    cell: (h, { row }) => {
      return (
          <t-tag shape="round" theme={statusNameListMap[row.clubStatus].theme} variant="light-outline">
            {statusNameListMap[row.clubStatus].icon}
            {statusNameListMap[row.clubStatus].label}
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
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubApproval, value.clubId)

}

eventEmitter.on(APIEventEnum.getClubApprovalSuccess, 'getClubApprovalSuccess', (data) => {
  console.log(data)
  clubInfo.value.recordId = data.recordId
  clubInfo.value.clubId = data.clubId
  clubInfo.value.clubName = data.clubName
  clubInfo.value.clubCategory = JSON.parse(localStorage.getItem('enumList')).clubCategories[data.clubCategory].name
  clubInfo.value.mainCompus = JSON.parse(localStorage.getItem('enumList')).mainCampuses[data.mainCampus].name
  clubInfo.value.clubDescription = data.clubDescription
  clubInfo.value.attachmentUrl = data.attachmentUrl
  clubInfo.value.adminGuideTeacher = data.adminGuideTeacher
  clubInfo.value.businessGuideTeacher = data.businessGuideTeacher
  clubInfo.value.establishmentDate = formatDate(new Date(data.establishmentDate), 'yyyy-MM-dd hh:mm:ss')
  clubInfo.value.contactPerson = data.contactPerson
  clubInfo.value.contactPhone = data.contactPhone
  clubInfo.value.clubStatus = data.clubStatus
  clubInfo.value.responsibleDepartment = data.responsibleDepartment
  clubInfo.value.advisorResumeAttachmentUrl = data.advisorResumeAttachmentUrl
})

const passClubApproval = () => {
  eventEmitter.emit(APIEventEnum.request, APIEnum.passCollegeClubApproval, {
    recordId: clubInfo.value.recordId,
    collegeReviewOpinion: collegeReviewOpinion.value
  })
}

const unPassClubApproval = () => {
  eventEmitter.emit(APIEventEnum.request, APIEnum.unPassCollegeClubApproval, {
    recordId: clubInfo.value.recordId,
    collegeReviewOpinion: collegeReviewOpinion.value
  })
}

const getFile = (file) =>{
  console.log(file)
  window.open(file)
}

eventEmitter.on(APIEventEnum.passCollegeClubApprovalSuccess, 'passClubApprovalSuccess', () => {
  console.log("success")
  NotifyPlugin.success({
    title: '操作成功',
    content: '通过社团建立申请成功',
  })
  visibleModal.value = false
})

eventEmitter.on(APIEventEnum.unPassCollegeClubApprovalSuccess, 'unPassClubApprovalSuccess', () => {
  NotifyPlugin.info({
    title: '操作成功',
    content: '驳回社团建立申请成功',
  })
  visibleModal.value = false
})


onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getClubsInfoSuccess, 'getClubsInfoSuccess')
  eventEmitter.off(APIEventEnum.getClubEvaluateInfoSuccess, 'getClubApprovalSuccess')
  eventEmitter.off(APIEventEnum.passCollegeClubApprovalSuccess, 'passClubApprovalSuccess')
  eventEmitter.off(APIEventEnum.unPassCollegeClubApprovalSuccess, 'unPassClubApprovalSuccess')
})



</script>

<style scoped>
.clubEvaluationContainer {
  width: 100%;
  height: 500px;
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