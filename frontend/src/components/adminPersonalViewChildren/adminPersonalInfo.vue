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

      <!-- 管理员信息卡 -->
      <t-card class="user-info-list" :title="adminInfoString" bordered >

        <!-- 编辑/保存按钮 -->
        <template #actions>
          <t-button theme="primary" shape="square" variant="base" @click="whileClick">
            {{buttonText}}
          </t-button>
        </template>

        <t-descriptions bordered :column="3">

          <t-descriptions-item label="管理员id">
            {{adminId}}
          </t-descriptions-item>
          
          <t-descriptions-item label="管理员类别">
            {{sort}}
          </t-descriptions-item>

          <t-descriptions-item label="联系方式">
            <t-auto-complete borderless :readonly="readOnly" v-model="contact" />  
          </t-descriptions-item>

          <t-descriptions-item label="管理员邮箱">
            <t-auto-complete borderless :readonly="readOnly" v-model="email" />  
          </t-descriptions-item>
        </t-descriptions>
      </t-card>

      <!-- 社团信息卡 -->
      <t-card class="user-info-list" :title="clubString" bordered >
        <t-descriptions bordered :column="3">
          <t-descriptions-item label="社团名称">
            {{clubName}}
          </t-descriptions-item>
          <t-descriptions-item label="附属单位">
            {{affiliatedUnit}}
          </t-descriptions-item>
          <t-descriptions-item label="主部所在校区">
            {{location}}
          </t-descriptions-item>
          <t-descriptions-item label="社团邮箱">
            <!-- <t-auto-complete borderless readonly="true" v-model="clubEmail" />   -->
            {{clubEmail}}
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

const adminInfoString = ref('个人信息')
const clubString = ref('社团简要信息')
// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 展示个人信息
const adminId = ref('默认adminId')
const account = ref('')
const sort = ref('')
const email = ref('')
const contact = ref('')
const affiliatedUnit = ref('')
const clubName = ref('')
const clubSort =ref('')
const schoolLocation = ref('')
const clubEmail = ref('')

const user = ref({})
const clubInfo=ref({})

const readOnly = ref(true)
const buttonText=ref('编辑')

// 为上面定义的变量赋值
function assignment() {
    adminId.value = user.value.adminId
    email.value = user.value.email
    account.value = user.value.account
    sort.value = transferSort(user.value.sort)
    contact.value = user.value.contact
    affiliatedUnit.value = user.value.affiliatedUnit

    clubInfo.value = user.value.clubs
    clubSort.value=clubInfo.value.clubSort
    clubName.value = clubInfo.value.clubName
    schoolLocation.value = clubInfo.value.location
    clubEmail.value = clubInfo.value.clubEmail
}

function transferSort(sortNumber) {
  return sortNumber===0 ? '学院管理员' :'校级管理员'
}

function whileClick() {
  //此处管理可编辑状态，实现保存功能
  readOnly.value = !readOnly.value
  buttonText.value = (readOnly.value) ? '编辑' : '保存'
  if( readOnly.value ) {
    save()
  }
}

if (isEmptyObject(store.state.userInfo)) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
} else {
  user.value = store.state.userInfo;
  assignment();
}


//初次加载界面时借此获取信息
eventEmitter.on(APIEventEnum.getAdminInfoSuccess, (data) => {
    user.value = data
    assignment()
})

function save() {
    console.log("准备保存数据……")
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminInfo, {
        adminId: store.state.adminId,
        contact: contact.value,
    })
    user.value.contact = contact.value
    user.value.email = email.value
    user.value.clubs.businessAdvisorName=businessAdvisorName.value
    user.value.clubs.administrativeAdvisorName=administrativeAdvisorName.value
    user.value.clubs.location=schoolLocation.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setAdminInfo, user.value)
}
</script>
  
