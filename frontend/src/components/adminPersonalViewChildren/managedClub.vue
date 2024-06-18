<style lang="less" scoped>
  
.t-descriptions {
  margin-top: 24px;
}


.user-info-list{
  padding: 5px;
  margin-bottom: 10px; 
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
}

.user-left-greeting {
  padding: 10px 20px;
  font: var(--td-font-title-large);
  background: var(--td-bg-color-container);
  color: var(--td-text-color-primary);
  text-align: left;
  border-radius: var(--td-radius-medium);
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px; 
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
  .regular {
    margin-left: var(--td-comp-margin-xl);
    font: var(--td-font-body-medium);
  }

  .logo {
    width: 168px;
  }
}
</style>

<template>

  <t-row :gutter="[24, 24]">
    <t-col :flex="3">

      <div class="user-left-greeting">
        <div>
          Hi，{{ admin.affiliatedUnit }}管理员{{ admin.account }}
          <span class="regular">欢迎使用数字百团！</span>
        </div>
        <img src="@/assets/数字百团logo.png" class="logo" />
      </div>


      <!-- 社团信息卡 -->
      <t-card class="user-info-list" v-for="item in clubInfo" :title="clubString" bordered>
        <template #actions>
          <t-button theme="primary" shape="square" variant="base" @click="whileClick">
            {{ buttonText }}
          </t-button>
        </template>

        <t-descriptions bordered :column="2">

          <t-descriptions-item label="社团名称">
            <!-- <t-auto-complete borderless readonly="true" v-model="clubName" />   -->
            {{item.clubName}}
          </t-descriptions-item>

          <t-descriptions-item label="附属单位">
            {{ admin.affiliatedUnit }}
          </t-descriptions-item>

          <t-descriptions-item label="社团总人数">
            {{item.totalMembership}}
          </t-descriptions-item>

          <t-descriptions-item label="成立日期">
            {{formatDate(new Date(item.establishedTime), 'yyyy-MM-dd hh:mm:ss')}}
          </t-descriptions-item>

          <t-descriptions-item label="社团类别">
            {{clubCategories[item.clubSort].name}}
          </t-descriptions-item>

          <t-descriptions-item label="主部所在校区">
            <t-select showArrow v-model="mainCampuses[item.location].name" :readonly="readOnly">
              <t-option key="广州南校" label="广州南校" value="广州南校" />
              <t-option key="广州北校" label="广州北校" value="广州北校" />
              <t-option key="广州东校" label="广州东校" value="广州东校" />
              <t-option key="深圳校区" label="深圳校区" value="深圳校区" />
              <t-option key="珠海校区" label="珠海校区" value="珠海校区" />
            </t-select>
          </t-descriptions-item>

          <t-descriptions-item label="业务指导老师姓名">
            <t-auto-complete :borderless="readOnly" :readonly="readOnly" v-model="item.businessAdvisorName" />  
          </t-descriptions-item>

          <t-descriptions-item label="行政指导老师姓名">
            <t-auto-complete :borderless="readOnly" :readonly="readOnly" v-model="item.administrativeAdvisorName" />  
          </t-descriptions-item>

          <t-descriptions-item label="成员财务是否公开">
            <t-select showArrow v-model="item.financePublicity" :readonly="readOnly">
              <t-option key="公开" label="公开" value="公开" />
              <t-option key="不公开" label="不公开" value="不公开" />
            </t-select>
          </t-descriptions-item>
        </t-descriptions>
      </t-card>
    </t-col>
  </t-row>
</template>

<script setup lang="ts">
import { APIEnum, APIEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { reactive, ref } from 'vue';
import formatDate from '@/utils'

const clubString = ref('社团信息')
const mainCampuses = JSON.parse(localStorage.getItem('enumList')).mainCampuses
const clubCategories = JSON.parse(localStorage.getItem('enumList')).clubCategories
// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

const admin = ref({})
let clubInfo = []


if (isEmptyObject(store.state.userInfo)) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
} else {
  console.log("userInfo已经获取信息！")
  admin.value = store.state.userInfo;
  clubInfo=admin.value.clubs;
}

//初次加载界面时借此获取信息
eventEmitter.on(APIEventEnum.getAdminInfoSuccess,'getAdminInfoSuccess', (data) => {
    admin.value = data;
})

function save() {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminInfo, {
        adminId: store.state.adminId,
    })
    admin.value.clubs.businessAdvisorName=businessAdvisorName.value
    admin.value.clubs.administrativeAdvisorName=administrativeAdvisorName.value
    admin.value.clubs.location=location.value
    admin.value.clubs.financePublicity=financePublicity.value
    admin.value.clubs.clubEmail=clubEmail.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubsData, admin.value)
}

const readOnly = ref(true)
const buttonText=ref('编辑')

function whileClick() {
  //此处管理可编辑状态，实现保存功能
  readOnly.value = !readOnly.value
  buttonText.value = (readOnly.value) ? '编辑' : '保存'
  if( readOnly.value ) {
    save()
  }
}

</script>
  
