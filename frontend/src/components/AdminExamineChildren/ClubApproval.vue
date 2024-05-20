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

// 表格
const statusNameListMap = {
  0: { label: '审批通过', theme: 'success', icon: <CheckCircleFilledIcon /> },
  1: { label: '审批失败', theme: 'danger', icon: <CloseCircleFilledIcon /> },
  2: { label: '待审批', theme: 'warning', icon: <ErrorCircleFilledIcon /> },
};
const data = [];
const total = 28;
for (let i = 0; i < total; i++) {
  data.push({
    index: i + 1,
    clubName: ['飞碟社', '羽毛球社', '围棋社'][i % 3],
    campus: ['北校区', '东校区', '南校区', '珠海校区', '深圳校区'][i % 5],
    type: ['体育类', '游戏类', '艺术类'][i % 3],
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
  { colKey: 'campus', title: '校区' },
  { colKey: 'type', title: '社团种类', ellipsis: true },
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
  