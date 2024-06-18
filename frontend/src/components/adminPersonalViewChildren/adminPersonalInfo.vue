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
.registerBox {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<template>

  <t-row :gutter="[24, 24]">
    <t-col :flex="3">

      <div class="user-left-greeting">
        <div>
          Hi，{{ admin.affiliatedUnit }}管理员{{admin.account}}
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
        <t-descriptions bordered :column="2">
          <t-descriptions-item label="管理员id">
            {{admin.adminId}}
          </t-descriptions-item>
          <t-descriptions-item label="管理员等级">
            {{admin.sort === 0 ? '学院管理员' :'校级管理员'}}
          </t-descriptions-item>
          <t-descriptions-item label="联系方式">
            <t-auto-complete :disabled="readOnly" v-model="admin.contact" />
          </t-descriptions-item>
          <t-descriptions-item label="管理社团数">
            {{clubInfo.length}}
          </t-descriptions-item>
          <t-descriptions-item label="附属单位">
            <t-auto-complete :disabled="readOnly" v-model="admin.affiliatedUnit" />
          </t-descriptions-item>
        </t-descriptions>
      </t-card>
      <!-- 校团委添加学院管理员选项卡 -->
      <t-card v-if="admin.sort === 1" class="user-info-list" title="新增管理员" bordered>
        <t-space direction="vertical" size="large" style="display: flex; text-align: center">
          <t-space direction="horizontal">
            <t-form :data="registerForm" layout="inline" :label-width="50">
              <t-form-item name="departmentName">
                <t-input placeholder="请输入部门名" v-model="registerForm.departmentName">
                  <template #prefix-icon>
                    <usergroup-icon />
                  </template>
                </t-input>
              </t-form-item>
              <t-form-item name="authority">
                <t-select v-model="registerForm.authority" style="width: 200px"
                          :options="authorityList" clearable placeholder="-请选择-">
                  <template #prefixIcon>
                    <GenderFemaleIcon />
                    <GenderMaleIcon />
                  </template>
                </t-select>
              </t-form-item>
              <t-form-item name="contact">
                <t-input placeholder="请输入联系方式" v-model="registerForm.contact">
                  <template #prefix-icon>
                    <usergroup-icon />
                  </template>
                </t-input>
              </t-form-item>
            </t-form>
            <t-form :data="registerForm" layout="inline" :label-width="50">
              <t-form-item name="account">
                <t-input placeholder="请输入账户名" v-model="registerForm.account">
                  <template #prefix-icon>
                    <user-icon />
                  </template>
                </t-input>
              </t-form-item>
              <t-form-item name="password">
                <t-input placeholder="请输入密码" type="password" v-model="registerForm.pwd">
                  <template #prefix-icon>
                    <lock-on-icon />
                  </template>
                </t-input>
              </t-form-item>
              <t-form-item name="confirmPassword">
                <t-input placeholder="请再次输入密码" type="password" v-model="registerForm.confirmPwd"
                         :status="confirmPasswordStatus" :tips="confirmPasswordTips">
                  <template #prefix-icon>
                    <lock-on-icon />
                  </template>
                </t-input>
              </t-form-item>
            </t-form>
          </t-space>
          <t-space>
            <t-button theme="primary" @click="handleRegister">
              <template #icon><add-icon /></template>
              新增管理员
            </t-button>
          </t-space>
        </t-space>
      </t-card>
      <!-- 社团信息卡 -->
      <t-card class="user-info-list" v-for="item in store.state.userInfo.clubs" :title="(''+ item.clubName + clubString)" bordered >
        <t-descriptions bordered :column="3">
          <t-descriptions-item label="社团名称">
            {{item.clubName}}
          </t-descriptions-item>
          <t-descriptions-item label="附属单位">
            {{admin.affiliatedUnit}}
          </t-descriptions-item>
          <t-descriptions-item label="主部所在校区">
            {{ mainCampuses[item.location].name }}
          </t-descriptions-item>
          <t-descriptions-item label="社团类别">
            {{ clubCategories[item.clubSort].name}}
          </t-descriptions-item>
          <t-descriptions-item label="社团简介">
            <p v-html="item.clubIntroduction[0].content"></p>
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
import {computed, onUnmounted, reactive, ref} from 'vue';
import {
  AddIcon,
  GenderFemaleIcon,
  GenderMaleIcon,
  LockOnIcon,
  MailIcon, UserBusinessIcon, UsergroupIcon,
  UserIcon,
  VerifiedIcon,
  VerifyIcon
} from "tdesign-icons-vue-next";
import {MessagePlugin} from "tdesign-vue-next";

const adminInfoString = ref('个人信息')
const clubString = ref('简要信息')
const mainCampuses = JSON.parse(localStorage.getItem('enumList')).mainCampuses
const clubCategories = JSON.parse(localStorage.getItem('enumList')).clubCategories
// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

// 展示个人信息
const admin = ref({
    account:'',
    adminId:'',
    affiliatedUnit:'',
    clubs:[],
    contact:'',
    sort:'',
})

const clubInfo = ref([])
const readOnly = ref(true)
const buttonText=ref('编辑')
const oldAdminInfo = {
    account: '',
    authority: '',
    pwd: '',
    confirmPwd:'',
    contact: '',
    departmentName: '',
}
const registerForm = reactive({
    account: '',
    authority: null,
    pwd: '',
    confirmPwd:'',
    contact: '',
    departmentName: '',
})
const authorityList = ref([
    { label: '校级管理员', value: 1 },
    { label: '学院管理员', value: 0 },
])
const confirmPasswordStatus = computed(() => {
  if (!registerForm.confirmPwd || registerForm.confirmPwd === registerForm.pwd) {
    return ''
  }
  return 'error'
})

const confirmPasswordTips = computed(() => {
  if (!registerForm.confirmPwd || registerForm.confirmPwd === registerForm.pwd) {
    return ''
  }
  return '两次输入的密码不一致'
})


const handleRegister = () => {
  if (registerValidate()) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminRegister, registerForm)
  }
  else {
    MessagePlugin.warning("请将信息填写完整")
    return
  }
}
const registerValidate = () => {
  console.log(registerForm)
  return passwordValidate(registerForm.pwd)
      && registerForm.pwd === registerForm.confirmPwd
      && registerForm.account
      && registerForm.contact
      && (registerForm.authority === 0 || registerForm.authority === 1)
      && registerForm.departmentName
}
const passwordValidate = (data) => {
  if (data.length < 6) {
    return false
  }
  return true
}

function whileClick() {
  //此处管理可编辑状态，实现保存功能
  readOnly.value = !readOnly.value
  buttonText.value = (readOnly.value) ? '编辑' : '保存'
  if( readOnly.value && (admin.value.affiliatedUnit !== oldAdminInfo.departmentName || admin.value.contact !== oldAdminInfo.contact) ) {
      save()
  }else{
    oldAdminInfo.contact = admin.value.contact
    oldAdminInfo.departmentName = admin.value.affiliatedUnit
  }
}

if (isEmptyObject(store.state.userInfo)) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
} else {
  admin.value = store.state.userInfo;
}


//初次加载界面时借此获取信息
eventEmitter.on(APIEventEnum.getAdminInfoSuccess, 'getAdminInfoSuccess',(data) => {
    admin.value = data
})
eventEmitter.on(APIEventEnum.postAdminRegisterSuccess,'postAdminRegisterSuccess',()=>{
    MessagePlugin.success("新增管理员成功")
})

function save() {
    eventEmitter.emit(APIEventEnum.request, APIEnum.postAdminInfo, {
        adminId: store.state.adminId,
        contact: admin.value.contact,
        departmentName: admin.value.affiliatedUnit
    })
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setUserInfo, admin.value)
}

onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getAdminInfoSuccess, 'getAdminInfoSuccess')
  eventEmitter.off(APIEventEnum.postAdminRegisterSuccess, 'postAdminRegisterSuccess')
})
</script>
  
