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

        <t-descriptions bordered :column="4">

          <t-descriptions-item label="管理员id">
            {{adminId}}
          </t-descriptions-item>
          
          <t-descriptions-item label="管理员等级">
            {{sort}}
          </t-descriptions-item>

          <t-descriptions-item label="联系方式">
            <t-auto-complete :borderless="readOnly" :readonly="readOnly" v-model="contact" />  
          </t-descriptions-item>

          <t-descriptions-item label="管理社团数">
            {{clubInfo.length}}
          </t-descriptions-item>
        </t-descriptions>
      </t-card>

      <!-- 社团信息卡 -->
      <t-card class="user-info-list" v-for="item in clubInfo" :title="(''+ item.clubName + clubString)" bordered >
        <t-descriptions bordered :column="3">
          <t-descriptions-item label="社团名称">
            {{item.clubName}}
          </t-descriptions-item>
          <t-descriptions-item label="附属单位">
            {{affiliatedUnit}}
          </t-descriptions-item>
          <t-descriptions-item label="主部所在校区">
            {{item.location}}
          </t-descriptions-item>
          <t-descriptions-item label="社团类别">
            {{item.clubSort}}
          </t-descriptions-item>
          <t-descriptions-item label="社团简介">
            {{item.clubIntroduction}}
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
const clubString = ref('简要信息')
// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 展示个人信息
const adminId = ref('默认adminId')
const sort = ref('')
const contact = ref('')
const affiliatedUnit=ref('')

const user = ref({})
let clubInfo = []

const readOnly = ref(true)
const buttonText=ref('编辑')

// 为上面定义的变量赋值
function assignment() {
    adminId.value = user.value.adminId
    sort.value = transferSort(user.value.sort)
    contact.value = user.value.contact
    affiliatedUnit.value = user.value.affiliatedUnit

    clubInfo = user.value.clubs
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
  
