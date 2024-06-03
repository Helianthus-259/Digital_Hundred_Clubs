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
                      :stripe="true"
                      :bordered="true"
                      :hover="true"
                      table-layout="fixed"
                      size='medium'
                      :pagination="pagination"
                      :show-header="true"
                      cell-empty-content="暂无数据"
                      resizable
                      lazy-load
                      @row-click="handleRowClick"
                    >
                    </t-table>

                    <!-- <t-pagination
                      style="background-color:#d8eeff; margin-left:25px;"
                      v-model="current"
                      v-model:pageSize="pageSize"
                      :total="total"
                    /> -->
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

/////////////////////////以下部分为左边多选框设计代码//////////////////////////////////
const options1 = [
  { value: '活动结束', label: '活动结束',theme: 'success' },
  { value: '活动进行中', label: '活动进行中',theme: 'primary' },
  { value: '活动取消', label: '活动取消', theme: 'danger' },
  { value: '活动未开始', label: '活动未开始', theme: 'warning' },
];
const value1 = ref(['活动结束','活动进行中','活动取消','活动未开始']);
const checkAll = computed(() => options1.length === value1.value.length);//全选
const indeterminate = computed(() => !!(options1.length > value1.value.length && value1.value.length));//半选indeterminate

//全选按钮对应showedData处理
const handleSelectAll = (checked) => {
  value1.value = checked ? ['活动结束','活动进行中','活动取消','活动未开始'] : [];
  showedData=[]
  for(let i=0;i<data.length;i++)
  {
    let flag=false
    
    for(let j=0;j<value1.value.length;j++) 
      if(options1[data[i].status].label===value1.value[j])
        flag=true
    
    if(flag) {
      showedData.push(data[i])
    }
  }
  total=showedData.length;
};

//单独按钮对应showedData处理
const onChange1 = (val) => {
  console.log(value1.value, val);
  showedData=[]
  for(let i=0;i<data.length;i++)
  {
    let flag=false

    for(let j=0;j<value1.value.length;j++) 
      if(options1[data[i].status].label===value1.value[j])
        flag=true
    
    if(flag) 
      showedData.push(data[i])
  }
  total=showedData.length;
};

/////////////////////////以下部分为表格设计代码//////////////////////////////////

const data = [];
const clubId=store.state.userInfo.clubs.clubId;
let pNumber = 0;
const pSize = 48;
let total = 48;

function getClubActivityData () {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubActivityList, { clubId, pNumber, pSize })
  eventEmitter.on(APIEventEnum.getClubActivityListSuccess, 'getClubActivityListSuccess', (returnData) => {
        let i=0;
        for(i=0;i<pSize;i++)
        {
          const activity = returnData[i];
          data.push({
            index: activity.activityId,
            activityName: activity.activityName,
            status: activity.status,
            activityId: activity.activityId,
            activityPlace: activity.activityPlace,
            activitiesSort: activity.activitiesSort,
          })
        }
    })
}

getClubActivityData();

let showedData=data;
const columns = ref([
  { colKey: 'activityId', title: '活动id' ,},
  { colKey: 'activityName', title: '活动名称',},
  { colKey: 'activityPlace', title: '活动场地',},
  {
    colKey: 'status',
    title: '活动审批状态',
    cell: (h, { row }) => {
      return (
        <t-tag shape="round" theme={options1[row.status].theme} variant="light-outline">
          {options1[row.status].label}
        </t-tag>
      );
    },
  },
  { colKey: 'activitiesSort', title: '活动类别',width:'250'},
  
]);

const handleRowClick = (e) => {
  console.log(e);
};

// const current=1;
const pagination = {
  defaultCurrent: 1,
  defaultPageSize: 10,
  total: total,
  // pageSize: pSize,
  // current: current,
};



</script>