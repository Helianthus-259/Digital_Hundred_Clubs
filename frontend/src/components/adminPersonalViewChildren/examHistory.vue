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

const clubsIdList=ref([]);
const clubsInfoMap=new Map();

function getAllManagedClubs(){
  const user = ref(store.state.userInfo);
  const clubsInfo = ref(user.value.clubs);
  let tempClubsIdList=[];
  clubsInfo.value.forEach(element => {
    tempClubsIdList.push(element.clubId);
    clubsInfoMap.set(element.clubId,element.clubName);
  });
  clubsIdList.value=tempClubsIdList;
}
getAllManagedClubs()

const pSize = 20;
const pagination = {
  defaultCurrent: 1,
  defaultPageSize: 10,
  total: pSize*clubsInfoMap.size,
};

/////////////////////////以下部分为左边多选框设计代码//////////////////////////////////
const options1 = [
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
  for(let i=0;i<data.value.length;i++)
    for(let j=0;j<value1.value.length;j++) 
      if(options1[data.value[i].status].label===value1.value[j])
      {
        showedData.push(data.value[i])
        break;
      }
  pagination.total=showedData.length;
};

const onChange1 = (val) => {
  showedData=[]
  for(let i=0;i<data.value.length;i++)
    for(let j=0;j<value1.value.length;j++) 
      if(options1[data.value[i].status].label===value1.value[j])
      {
        showedData.push(data.value[i])
        break;
      }
  pagination.total=showedData.length;
};

/////////////////////////以下部分为表格设计代码//////////////////////////////////

const statusNameListMap = {
  0: { label: '申请通过', theme: 'success'},
  1: { label: '申请失败', theme: 'danger' },
  2: { label: '申请退回', theme: 'warning' },
  3: { label: '申请中', theme: 'primary' },
};
const data = ref([]);
let showedData = [];
let pNumber = 0;


let activityClubNameQueue=[];
function getClubActivityData (clubId) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActivityList, { clubId, pNumber, pSize })
  activityClubNameQueue.push(clubsInfoMap.get(clubId))
  eventEmitter.on(APIEventEnum.getClubActivityListSuccess, 'getClubActivityListSuccess', (returnData) => {
    const tempClubName=activityClubNameQueue[0];
    if(activityClubNameQueue.length>1)
      activityClubNameQueue=activityClubNameQueue.slice(1);
    returnData.forEach(element => {
      element.clubName=tempClubName;
      element.examId=element.activityId;
      element.examName=element.activityName;
      element.examdetail=element.activityEffect;
    });

    if(data.value.length===0)
      data.value = returnData;
    else
      data.value.push(...returnData);
  })
}

function getMyClubAnnualExamData (clubId) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getMyClubAnnualExamData, { clubId })
  eventEmitter.on(APIEventEnum.getMyClubAnnualExamDataSuccess, 'getMyClubAnnualExamDataSuccess', (returnData) => {
    // console.log("收到数据："+returnData)
    for(let i=0;i<returnData.length;i++){
      const examItem=returnData[i];
      let year=examItem.declarationYear;
      let yearString=''+year;
      data.value.push({
        clubName: examItem.clubName,
        examName: yearString +'年度审核',
        status: examItem.status,
        examId: examItem.declarationId,
        examdetail: ( (examItem.reviewOpinion===null) ? '暂无意见' : examItem.reviewOpinion ),
      })
      pagination.total++;
    }
  })
}

let backboneClubNameQueue=[];
function getMyClubBackboneExamData (clubId) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getMyClubBackboneExamData, { clubId })
  backboneClubNameQueue.push(clubsInfoMap.get(clubId))
  eventEmitter.on(APIEventEnum.getMyClubBackboneExamDataSuccess, 'getMyClubBackboneExamDataSuccess', (returnData) => {
    const tempClubName=backboneClubNameQueue[0];
    if(backboneClubNameQueue.length>1)
      backboneClubNameQueue=backboneClubNameQueue.slice(1);
    for(let i=0;i<returnData.length;i++){
      const examItem=returnData[i];
      let year=examItem.declarationYear;
      let yearString=''+year;
      data.value.push({
        clubName: tempClubName,
        examName: examItem.stName+'同学'+ yearString +'年骨干评优',
        status: examItem.status,
        examId: examItem.recordId,
        examdetail: ( (examItem.reviewOpinion===null) ? '暂无意见' : examItem.reviewOpinion ),
      })
      pagination.total++;
    }
  })
}

let teacherClubNameQueue=[];
function getMyClubTeacherExamData (clubId) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getMyClubTeacherExamData, { clubId })
  teacherClubNameQueue.push(clubsInfoMap.get(clubId))
  eventEmitter.on(APIEventEnum.getMyClubTeacherExamDataSuccess, 'getMyClubTeacherExamDataSuccess', (returnData) => {
    const tempClubName=teacherClubNameQueue[0];
    if(teacherClubNameQueue.length>1)
      teacherClubNameQueue=teacherClubNameQueue.slice(1);
    for(let i=0;i<returnData.length;i++){
      const examItem=returnData[i];
      let year=examItem.declarationYear;
      let yearString=''+year;
      data.value.push({
        clubName: tempClubName,
        examName: examItem.teacherName+'指导教师'+ yearString +'评优',
        status: examItem.status,
        examId: examItem.declarationId,
        examdetail: ( (examItem.reviewOpinion===null) ? '暂无意见' : examItem.reviewOpinion ),
      })
      pagination.total++;
    }
  })
}

clubsInfoMap.keys().forEach(clubId=>{
  getClubActivityData(clubId);
  getMyClubAnnualExamData(clubId);
  getMyClubBackboneExamData (clubId);
  getMyClubTeacherExamData (clubId);
})

const stripe = ref(true);
const bordered = ref(true);
const hover = ref(true);
const size = ref('medium');
const showHeader = ref(true);

const columns = ref([
  { colKey: 'clubName', title: '社团名' ,},
  { colKey: 'examId', title: '活动Id/审批记录Id' ,},
  { colKey: 'examName', title: '审批事项',width:'250'},
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
  { colKey: 'examdetail', title:'备注',},

]);

const handleRowClick = (e) => {
  console.log(e);
};
showedData=data;
/////////////////////////以上部分为表格设计代码//////////////////////////////////

</script>