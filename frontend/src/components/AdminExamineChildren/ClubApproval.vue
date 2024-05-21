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
    <t-layout>
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
        <t-table
            row-key="index"
            :data="data"
            :columns="columns"
            :hide-sort-tips="false"
            :stripe="stripe"
            :bordered="bordered"
            :hover="hover"
            :table-layout="tableLayout ? 'auto' : 'fixed'"
            :size="size"
            :pagination="pagination"
            :show-header="showHeader"
            cell-empty-content="-"
            resizable=""
            lazy-load=""
        >
          <template #operation="{ row }">
            <t-button theme="primary" @click="detail(row)">申请详情</t-button>
          </template>
        </t-table>
      </div>
    </t-layout>
  </t-layout>
</template>

<script lang="jsx" setup>
import { ref } from 'vue';


const value = ref('item1');
import { ErrorCircleFilledIcon, CheckCircleFilledIcon, CloseCircleFilledIcon } from 'tdesign-icons-vue-next';
import store from "@/store/index.js";
import eventEmitter from "@/utils/eventEmitter.js";
import {APIEnum, APIEventEnum} from "@/Enum/index.js";

// 表格
const statusNameListMap = {
  0: { label: '审批失败', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  1: { label: '审批通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
  2: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
};
const clubsData = []
const data = []
const total = ref(0)
function assignment(){
  total.value = clubsData.value.length
  for(let i = 0; i < total.value; ++i){
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

if(Object.keys(store.state.clubsData).length === 0){
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubsInfo)
} else{
  clubsData.value = store.state.clubsData
  assignment()
}
eventEmitter.on(APIEventEnum.getClubsInfoSuccess, (data) => {
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
  { colKey: 'createTime', title: '申请时间'},
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
  { colKey: 'operation', title: '申请详情'}
]);


const pagination = {
  defaultCurrent: 1,
  defaultPageSize: 5,
  total:total.value,
};

const detail = (value) => {
  console.log(value)
}
</script>

<style>

</style>
  