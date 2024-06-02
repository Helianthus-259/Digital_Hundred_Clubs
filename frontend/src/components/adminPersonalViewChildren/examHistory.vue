<style scoped>
.mainBox {
    display: flex;
    width: 100%;
    height: 600px;
}

/* 左边部分 */
.leftSide {
    width: 80%;
    padding: 5px;
    margin-right: 2%;
    margin-left: 2%;
}

/* 左边部分内容 */
.leftSideContent {
    background-color: #d8eeff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
    overflow-y: auto;
}

/*滚动条样式*/
.leftSideContent::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.leftSideContent::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.leftSideContent::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.leftSideContent::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

/* 右边部分 */
.rightSide {
    width: 14%;
    padding: 5px;
    margin-left: 2%;
}

/* 右边部分内容 */
.rightSideContent {
    background-color: #fff;
    border-radius: 5px;
    height: 100%;
    display: flex;
    justify-content: center;
}

.ribbon {
    width: 100%;
    height: 100%;
    padding: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    /* 按钮样式 */
    .t-button {
        margin: 5px;
        width: 65%;
        height: 40px;
        font-size: 16px;
        overflow: hidden;
    }
}
</style>

<template>
    <div class="mainBox">
        <div class="rightSide">
            <div class="rightSideContent">
              <t-space style="margin-left:10%; margin-top: 20%;" direction="vertical">
                <t-checkbox style="" :checked="checkAll" :indeterminate="indeterminate" :on-change="handleSelectAll">展示全部记录</t-checkbox>
                <t-checkbox-group style="" v-model="value1" :options="options1" @change="onChange1" />
              </t-space>
            </div>
        </div>

        <div class="leftSide">
            <div class="leftSideContent">
                <t-space direction="vertical" style="background-color:#ffffff; border-radius: 25px;">

                    <!-- 当数据为空需要占位时，会显示 cellEmptyContent -->
                    <t-table
                    row-key="index"
                    :data="showedData"
                    :columns="columns"
                    :fields="value1"
                    :stripe="stripe"
                    :bordered="bordered"
                    :hover="hover"
                    table-layout="fixed"
                    :size="size"
                    :pagination="pagination"
                    :show-header="showHeader"
                    cell-empty-content="-"
                    resizable
                    lazy-load
                    @row-click="handleRowClick"
                    cellEmptyContent="暂无数据"
                    >
                    </t-table>
                    
                </t-space>
            </div>
        </div>

        
    </div>
</template>

<script setup lang="jsx">
import { ref, computed } from 'vue';
import 'jspdf-autotable';
import '@/utils/simhei-normal'
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum } from '@/Enum';
import { MessagePlugin } from 'tdesign-vue-next';
/////////////////////////以下部分为左边多选框设计代码//////////////////////////////////
const options1 = [
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  { value: '申请通过', label: '申请通过' },
  { value: '申请失败', label: '申请失败' },
  { value: '申请退回', label: '申请退回' },
  { value: '申请中', label: '申请中' },
];
const value1 = ref(['申请通过','申请失败','申请退回','申请中',]);
//全选
const checkAll = computed(() => options1.length === value1.value.length);
//半选indeterminate
const indeterminate = computed(() => !!(options1.length > value1.value.length && value1.value.length));

const handleSelectAll = (checked) => {
  value1.value = checked ? ['申请通过','申请失败','申请退回','申请中'] : [];
  showedData=[];
  for(let i=0;i<data.length;i++)
  {
    let flag=false;

    for(let j=0;j<value1.value.length;j++)
      if(statusNameListMap[data[i].status].label===value1.value[j])
        flag=true;

    if(flag)
      showedData.push(data[i])
  }
  total=showedData.length;
};

const onChange1 = (val) => {
  console.log(value1.value, val);
  showedData=[]
  for(let i=0;i<data.length;i++) {
    let flag=false

    for(let j=0;j<value1.value.length;j++)
      if(statusNameListMap[data[i].status].label===value1.value[j])
        flag=true

    if(flag)
      showedData.push(data[i])
  }
  total=showedData.length;
};

/////////////////////////以下部分为表格设计代码//////////////////////////////////

const statusNameListMap = {
  0: { label: '申请通过', theme: 'success'},
  1: { label: '申请失败', theme: 'danger' },
  2: { label: '申请退回', theme: 'warning' },
  3: { label: '申请中', theme: 'primary' },
};
const data = [];
let showedData = [];
const clubId = store.state.userInfo.clubs.clubId;
let pNumber = 0;
const pSize = 48;
let total = 48;

function getClubActivityData () {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActivityList, { clubId, pNumber, pSize })
  eventEmitter.on(APIEventEnum.getClubActivityListSuccess, 'getClubActivityListSuccess', (returnData) => {
        let i=0;
        for(i=0;i<pSize;i++)
        {
          const examItem = returnData[i];
          data.push({
            examName: examItem.activityName,
            status: examItem.status,
            examId: examItem.activityId,
            examdetail: examItem.activityPlace,
            examSort: examItem.activitiesSort,
          })
        }
    })
}

function getMyClubAnnualExamData () {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getMyClubAnnualExamData, { clubId })
  eventEmitter.on(APIEventEnum.getMyClubAnnualExamDataSuccess, 'getMyClubAnnualExamDataSuccess', (returnData) => {
        // console.log("收到数据："+returnData)
        for(let i=0;i<returnData.length;i++){
          const examItem=returnData[i];
          data.push({
            examName: examItem.examName,
            status: examItem.status,
            examId: examItem.examId,
            examdetail: examItem.examdetail,
            examSort: examItem.examSort,
          })
        }
    })
}

function getMyClubBackboneExamData () {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getMyClubBackboneExamData, { clubId })
  eventEmitter.on(APIEventEnum.getMyClubBackboneExamDataSuccess, 'getMyClubBackboneExamDataSuccess', (returnData) => {
        // console.log("收到数据："+returnData)
        for(let i=0;i<returnData.length;i++){
          const examItem=returnData[i];
          data.push({
            examName: examItem.examName,
            status: examItem.status,
            examId: examItem.examId,
            examdetail: examItem.examdetail,
            examSort: examItem.examSort,
          })
        }
    })
}

function getMyClubTeacherExamData () {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getMyClubTeacherExamData, { clubId })
  eventEmitter.on(APIEventEnum.getMyClubTeacherExamDataSuccess, 'getMyClubTeacherExamDataSuccess', (returnData) => {
        // console.log("收到数据："+returnData)
        for(let i=0;i<returnData.length;i++){
          const examItem=returnData[i];
          data.push({
            examName: examItem.examName,
            status: examItem.status,
            examId: examItem.examId,
            examdetail: examItem.examdetail,
            examSort: examItem.examSort,
          })
        }
    })
}

getClubActivityData();
getMyClubAnnualExamData();
getMyClubBackboneExamData ();
getMyClubTeacherExamData ();
showedData=data;
const stripe = ref(true);
const bordered = ref(true);
const hover = ref(true);
const size = ref('medium');
const showHeader = ref(true);

const columns = ref([
  { colKey: 'examId', title: '审批提交时间' ,},
  { colKey: 'examName', title: '审批事项',},
  { colKey: 'examdetail', title: '审批细节',},
  {
    colKey: 'status',
    title: '审批状态',
    cell: (h, { row }) => {
      return (
        <t-tag shape="round" theme={statusNameListMap[row.status].theme} variant="light-outline">
          {statusNameListMap[row.status].label}
        </t-tag>
      );
    },
  },
  {
    colKey: 'examSort',
  },

]);

const handleRowClick = (e) => {
  console.log(e);
  console.log("showedData:"+showedData.length)
};
const pagination = {
  defaultCurrent: 1,
  defaultPageSize: 10,
  total,
};

/////////////////////////以上部分为表格设计代码//////////////////////////////////

</script>