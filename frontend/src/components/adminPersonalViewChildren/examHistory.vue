
<style lang="less">
.component-wrap {
  height: calc(100vh - 50px);
  width: 90%;
  margin:auto;
}

.capsule {
  margin: 0 8px;
  padding: 0 16px;
  height: 30px;
  border-radius: 15px;
  background-color: #f3f3f3;
  padding-left: 32rpx;
  display: flex;
  align-items: center;
  font-size: 14px;
  box-sizing: border-box;
}

.capsule--active {
  background: #fff;
  border: 1px solid #e7e7e7;
}
</style>

<template>
  <div class="component-wrap">
    <t-indexes :index-list="indexList" @change="change">
      <template v-for="item in list" :key="item.index">
        <t-indexes-anchor :index="item.index">
          <div style="width: 200px;" :class="['capsule', item.index === curIndex ? 'capsule--active' : '']">{{ item.index }}</div>
        </t-indexes-anchor>
        <t-cell-group>
          <t-cell v-for="(val, i) in item.children" :key="i" :title="val" />
        </t-cell-group>
      </template>
    </t-indexes>
  </div>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';

var num = 100;
const children = new Array(100).fill('审批历史记录');
for (var i = 0; i < num; i++) {
    children[i] = children[i] + i.toString()
}
const curIndex: Ref<number | string> = ref('');
const list = [
  {
    index: 1,
    children : children.slice(0, 5),
  },
  {
    index: 5,
    children : children.slice(5, 10),
  },
  {
    index: 10,
    children : children.slice(10, 20),
  },
  {
    index: 20,
    children : children.slice(20, 30),
  },
  {
    index: 30,
    children : children.slice(30, 50),
  },
  {
    index: 50,
    children : children.slice(50, 65),
  },
];
const indexList = list.map((item) => item.index);

const change = (index: number | string) => {
  curIndex.value = index;
};
</script>


<!--
<style lang="less" scoped>
.side-bar-wrapper {
  display: flex;
  height: 100vh;
  background-color: var(--bg-color-demo, #fff);

  .content {
    flex: 1;
    overflow-y: scroll;
  }

  .section {
    padding: 16px 0;
  }

  .title {
    padding-left: 20px;
    margin-bottom: 4px;
    line-height: 26px;
  }

  .image {
    width: 48px;
    height: 48px;
  }
}
</style>

<template>
    <div class="side-bar-wrapper">
      <t-side-bar :value="sideBarIndex" @change="onSideBarChange" @click="onSideBarClick">
        <t-side-bar-item
          v-for="(item, index) in data.categories"
          :key="index"
          :value="index"
          :label="item.label"
          :badge-props="item.badgeProps"
        />
      </t-side-bar>
      <div ref="wrapper" class="content" @scroll="onScroll">
        <div v-for="(item, index) in data.categories" :key="index" class="section" :style="contentStyle">
          <div class="title">{{ item.title || item.label }}</div>
          <t-grid :column="1" :border="false">
            <div v-for="(cargo, cargoIndex) in item.items" :key="cargoIndex">
              <t-grid-item :text="cargo.label" :image="cargo.image" :image-props="{ shape: 'round', lazy: true }">
              </t-grid-item>
            </div>
          </t-grid>
        </div>
      </div>
    </div>
  </template>
  <script lang="ts" setup>
  import { reactive, ref, onMounted, computed } from 'vue';
  import { TdSideBarProps, TdSideBarItemProps } from '../type';
  
  const image = 'https://tdesign.gtimg.com/mobile/demos/example2.png';
  const items = new Array(12).fill({ label: '标题文字', image }, 0, 12);
  const sideBarIndex = ref<TdSideBarProps['value']>(1);
  
  const data = reactive({
    categories: [
      {
        label: '最近5条',
        title: '最近5条',
        badgeProps: {},
        items: items.slice(0, 5),
      },
      {
        label: '6-10条',
        title: '6-10条',
        badgeProps: {
          dot: true,
        },
        items: items.slice(0, 5),
      },
      {
        label: '11-20条',
        title: '11-20条',
        badgeProps: {},
        items: items.slice(0, 10),
      },
      {
        label: '21-30条',
        title: '21-30条',
        badgeProps: {
          count: 6,
        },
        items: items.slice(0, 10),
      },
      {
        label: '更早以前',
        title: '更早以前',
        badgeProps: {},
        items: items.slice(0, 15),
      },
    ],
  });
  
  const wrapper = ref<HTMLElement>();
  const offsetTopList = reactive<number[]>([]);
  const contentStyle = ref('');
  const getOffsetTopList = () => {
    if (wrapper.value) {
      const $title = wrapper.value.querySelectorAll<HTMLElement>(`.title`);
      $title.forEach((item) => offsetTopList.push(item.offsetTop));
    }
  };
  
  const moveToActiveSideBar = (index: number) => {
    if (wrapper.value) {
      wrapper.value.scrollTop = offsetTopList[index] - offsetTopList[0];
    }
  };
  
  onMounted(() => {
    getOffsetTopList();
    moveToActiveSideBar(Number(sideBarIndex.value));
  });
  
  const onSideBarClick = (value: TdSideBarProps['value'], label: TdSideBarItemProps['label']) => {
    console.log('=onSideBarClick===', value, label);
  };
  
  const onSideBarChange = (value: TdSideBarProps['value']) => {
    sideBarIndex.value = value;
    moveToActiveSideBar(Number(value));
  };
  
  const onScroll = (e: WheelEvent | Event) => {
    const threshold = offsetTopList[0]; // 下一个标题与顶部的距离
    const { scrollTop } = e.target as HTMLElement;
    if (scrollTop < threshold) {
      sideBarIndex.value = 0;
      return;
    }
    const index = offsetTopList.findIndex((top) => top > scrollTop && top - scrollTop <= threshold);
  
    if (index > -1) {
      sideBarIndex.value = index;
    }
  };
  </script>
-->

 
  