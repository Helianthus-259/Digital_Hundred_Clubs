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
          <t-checkbox style="" :checked="checkAll" :indeterminate="indeterminate"
            :on-change="handleSelectAll">展示全部记录</t-checkbox>
          <t-checkbox-group style="" v-model="value1" :options="options1" @change="onChange1" />
        </t-space>
      </div>
    </div>

    <div class="leftSide">
      <div class="leftSideContent">
        <t-space direction="vertical" style="background-color:#ffffff; border-radius: 25px;">
          <!-- 当数据为空需要占位时，会显示 cellEmptyContent -->
          <t-table row-key="index" :data="showedData" :columns="columns" :fields="value1" :stripe="true"
            :bordered="true" :hover="true" table-layout="fixed" size='medium' :pagination="pagination"
            :show-header="true" cell-empty-content="暂无数据" resizable lazy-load @row-click="handleRowClick">
          </t-table>
        </t-space>
      </div>
    </div>
  </div>
</template>

<script setup lang="jsx">
import {ref, computed, onMounted,onUnmounted} from 'vue';
import 'jspdf-autotable';
import '@/utils/simhei-normal'
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum } from '@/Enum';
import formatDate from "@/utils/index.js";

//先确定要获取的数据量
const clubsIdList = ref([]);

onMounted(() => {
  const user = ref(store.state.userInfo);
  const clubsInfo = ref(user.value.clubs);
  clubsInfo.value.forEach(element => {
      clubsIdList.value.push(element.clubId)
  })
  console.log(clubsIdList.value)
  eventEmitter.emit(APIEventEnum.request, APIEnum.getActivitiesInfo)
  eventEmitter.on(APIEventEnum.getActivitiesInfoSuccess, 'getActivitiesInfoSuccess', (data) => {
    showedData.value = data.data.filter(item => {
      item.activityPublishTime = formatDate(new Date(item.activityPublishTime), 'yyyy-MM-dd hh:mm:ss');
      item.activityStartTime = formatDate(new Date(item.activityStartTime), 'yyyy-MM-dd hh:mm:ss');
      item.activityEndTime = formatDate(new Date(item.activityEndTime), 'yyyy-MM-dd hh:mm:ss');
      if(item.activityStartTime  > formatDate(new Date(), 'yyyy-MM-dd hh:mm:ss')){
        item.status = 0
      }else if(item.activityEndTime  < formatDate(new Date(), 'yyyy-MM-dd hh:mm:ss')){
        item.status = 1
      }else{
        item.status = 2
      }
      return item.clubId in clubsIdList.value;
    })
    console.log(showedData.value)
    assignment()
  })
})
onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getActivitiesInfoSuccess, 'getActivitiesInfoSuccess')
})
function assignment() {
  data.value = showedData.value
  pagination.value.total = showedData.value.length
}

const pagination = ref({
  defaultCurrent: 1,
  defaultPageSize: 10,
  total: 0,
})

/////////////////////////以下部分为左边多选框设计代码//////////////////////////////////
const options1 = [
  { value: '活动结束', label: '活动结束', theme: 'success' },
  { value: '活动进行中', label: '活动进行中', theme: 'primary' },
  { value: '活动未开始', label: '活动未开始', theme: 'warning' },
];
const value1 = ref(['活动结束', '活动进行中', '活动未开始']);
const checkAll = computed(() => options1.length === value1.value.length);//全选
const indeterminate = computed(() => !!(options1.length > value1.value.length && value1.value.length));//半选indeterminate

//全选按钮对应showedData处理
const handleSelectAll = (checked) => {
  value1.value = checked ? ['活动结束','活动进行中','活动未开始'] : [];
  showedData.value = checked ? data.value : [];
  pagination.total=showedData.value.length;
};

//单独按钮对应showedData处理
const onChange1 = (val) => {
    showedData.value = data.value.filter(item => {
        for(let i=0;i<value1.value.length;i++){
            if(options1[item.status].value === value1.value[i]) return true
        }
    })
};
/////////////////////////以下部分为表格设计代码//////////////////////////////////

const data = ref([]);
const showedData = ref([]);

//获取学院管理员本人所管理的所有社团：


const columns = ref([
  { colKey: 'activityId', title: '活动id' ,},
  { colKey: 'clubName', title: '社团名称' ,},
  { colKey: 'activityName', title: '活动名称',},
  { colKey: 'activityLocation', title: '活动场地',width:'200'},
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
  { colKey: 'activityEffect', title: '活动成效',width:'200'},
  
]);

const handleRowClick = (e) => {
  console.log(e);
};

</script>