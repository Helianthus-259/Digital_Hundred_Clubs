<template>
  <t-layout>
    <t-aside>
      <t-menu theme="light" v-model="value" height="700px" width="200px">
        <t-menu-item value="item0">全部活动</t-menu-item>
        <t-menu-item value="item1">待审批</t-menu-item>
        <t-menu-item value="item2">已通过</t-menu-item>
        <t-menu-item value="item3">已驳回</t-menu-item>
      </t-menu>
    </t-aside>
    <t-layout>
      <t-header>
        <t-space direction="horizontal">
          <SearchIcon />
          <t-input style="margin-left: 10px;  width: 500px"></t-input>
        </t-space>
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
            <t-button theme="primary" @click="detail(row)">活动详情</t-button>
          </template>
        </t-table>
      </div>
    </t-layout>
  </t-layout>
</template>

<script lang="jsx" setup>
import { ref } from 'vue';


const value = ref('item1');
import {ErrorCircleFilledIcon, CheckCircleFilledIcon, CloseCircleFilledIcon, SearchIcon} from 'tdesign-icons-vue-next';
import eventEmitter from "@/utils/eventEmitter.js";
import {RouterEventEnum, StoreEnum, StoreEventEnum} from "@/Enum/index.js";

// 表格
const statusNameListMap = {
  0: { label: '申请通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
  1: { label: '申请驳回', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  2: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
};
const data = [];
const total = 28;
for (let i = 0; i < total; i++) {
  data.push({
    index: i + 1,
    clubName: ['飞碟社', '羽毛球社', '围棋社'][i % 3],
    activityName: ['羽毛球赛', '围棋赛', '飞碟赛'][i % 3],
    address: ['北校区', '东校区', '南校区', '珠海校区', '深圳校区'][i % 5],
    createTime: ['2022-01-01', '2022-02-01', '2022-03-01', '2022-04-01', '2022-05-01'][i % 4],
    status: i % 3,
  });
}

const stripe = ref(true);
const bordered = ref(true);
const hover = ref(false);
const tableLayout = ref(false);
const size = ref('medium');
const showHeader = ref(true);

const columns = ref([
  { colKey: 'clubName', title: '社团名称', width: '100' },
  { colKey: 'activityName', title: '活动名称', width: '100' },
  { colKey: 'address', title: '活动地点', width: '100' },
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
  total,
};

const detail = (value) => {
  console.log(value)
}
</script>

<style>

</style>
