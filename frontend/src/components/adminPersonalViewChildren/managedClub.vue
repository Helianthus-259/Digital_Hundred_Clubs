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
          Hi，{{ affiliatedUnit }}管理员{{adminId}}
          <span class="regular">欢迎使用数字百团！</span>
        </div>
        <img src="@/assets/数字百团logo.png" class="logo" />
      </div>


      <!-- 社团信息卡 -->
      <t-card class="user-info-list" :title="clubString" bordered>
        <template #actions>
          <t-button theme="primary" shape="square" variant="base" @click="whileClick">
            {{ buttonText }}
          </t-button>
        </template>

        <t-descriptions bordered :column="3">

          <t-descriptions-item label="社团名称">
            <!-- <t-auto-complete borderless readonly="true" v-model="clubName" />   -->
            {{clubName}}
          </t-descriptions-item>

          <t-descriptions-item label="附属单位">
            {{affiliatedUnit}}
          </t-descriptions-item>

          <t-descriptions-item label="社团总人数">
            {{totalMembership}}
          </t-descriptions-item>

          <t-descriptions-item label="成立日期">
            {{establishedTime}}
          </t-descriptions-item>

          <t-descriptions-item label="社团类别">
            {{clubSort}}
          </t-descriptions-item>

          <t-descriptions-item label="主部所在校区">
            <t-select showArrow v-model="location" :readonly="readOnly">
              <t-option key="广州南校" label="广州南校" value="广州南校" />
              <t-option key="广州北校" label="广州北校" value="广州北校" />
              <t-option key="广州东校" label="广州东校" value="广州东校" />
              <t-option key="深圳校区" label="深圳校区" value="深圳校区" />
              <t-option key="珠海校区" label="珠海校区" value="珠海校区" />
            </t-select>
          </t-descriptions-item>

          <t-descriptions-item label="业务指导老师姓名">
            <t-auto-complete borderless :readonly="readOnly" v-model="businessAdvisorName" />  
          </t-descriptions-item>

          <t-descriptions-item label="行政指导老师姓名">
            <t-auto-complete :borderless="readOnly" :readonly="readOnly" v-model="administrativeAdvisorName" />  
          </t-descriptions-item>

          <t-descriptions-item label="成员财务是否公开">
            <t-select showArrow v-model="financePublicity" :readonly="readOnly">
              <t-option key="公开" label="公开" value="公开" />
              <t-option key="不公开" label="不公开" value="不公开" />
            </t-select>
          </t-descriptions-item>

          <t-descriptions-item label="社团邮箱">
            <t-auto-complete borderless :readonly="readOnly" v-model="clubEmail" />  
          </t-descriptions-item>
        </t-descriptions>

      </t-card>
      
      <t-card class="content-container" :bordered="false" style="margin-bottom:10px; margin-top: 10px;margin-left:10px;margin-right:10px;">
        <t-tabs value="second">

          <t-tab-panel value="first" label="社团概况">
          </t-tab-panel>

          <t-tab-panel value="second" label="社团数据">

            <t-card :bordered="false" class="card-padding-no" title="社团活动数据" describe="（次）">
              <template #actions>
                <t-date-range-picker
                  class="card-date-picker-container"
                  theme="primary"
                  mode="date"
                />
              </template>
              
            </t-card>

          </t-tab-panel>

          <t-tab-panel value="third" label="社团成就">
          </t-tab-panel>

        </t-tabs>
      </t-card>

    </t-col>
  </t-row>
</template>

<script setup lang="ts">
import { APIEnum, APIEventEnum, StoreEnum, StoreEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { reactive, ref } from 'vue';

const clubString = ref('社团信息')
// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 展示个人信息
const affiliatedUnit = ref('')
const affiliatedUnitId = ref('')
const totalMembership = ref('')
const clubName = ref('')
const establishedTime = ref('')
const clubSort =ref('')
const businessAdvisorName = ref('')
const administrativeAdvisorName = ref('')
const location = ref('')
const financePublicity = ref('')
const clubEmail = ref('')
const adminId = ref('')

const user = ref({})
const clubInfo=ref({})

// 为上面定义的变量赋值
function assignment() {
    adminId.value = user.value.adminId
    affiliatedUnit.value = user.value.affiliatedUnit
    clubInfo.value = user.value.clubs
    affiliatedUnitId.value = clubInfo.value.affiliatedUnitId
    establishedTime.value=clubInfo.value.establishedTime
    clubSort.value=clubInfo.value.clubSort
    totalMembership.value = clubInfo.value.totalMembership
    clubName.value = clubInfo.value.clubName
    financePublicity.value = clubInfo.value.financePublicity
    location.value = clubInfo.value.location
    administrativeAdvisorName.value = clubInfo.value.administrativeAdvisorName
    businessAdvisorName.value=clubInfo.value.businessAdvisorName
    clubEmail.value = clubInfo.value.clubEmail
}

if (isEmptyObject(store.state.userInfo)) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
} else {
  console.log("else已经获取信息！准备assignment")
  user.value = store.state.userInfo
  assignment()
}

//初次加载界面时借此获取信息
eventEmitter.on(APIEventEnum.getAdminInfoSuccess, (data) => {
    user.value = data
    assignment()
})

function save() {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminInfo, {
        adminId: store.state.adminId,
    })
    user.value.clubs.businessAdvisorName=businessAdvisorName.value
    user.value.clubs.administrativeAdvisorName=administrativeAdvisorName.value
    user.value.clubs.location=location.value
    user.value.clubs.financePublicity=financePublicity.value
    user.value.clubs.clubEmail=clubEmail.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubsData, user.value)
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
  
