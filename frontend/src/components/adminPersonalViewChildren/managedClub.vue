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
      <t-card class="user-info-list" v-for="item in clubInfo" :title="item.clubName + clubString" bordered>
        <template #actions>
          <t-button theme="primary" shape="square" variant="base" :disabled="choose !== -1 && choose !== item.clubId" @click="whileClick(item)">
            {{ item.clubId === choose ? '保存': '编辑' }}
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
            <t-select showArrow v-model="item.location" :readonly="item.clubId!==choose">
              <t-option key="广州南校" label="广州南校" :value="0" />
              <t-option key="广州北校" label="广州北校" :value="1" />
              <t-option key="广州东校" label="广州东校" :value="2" />
              <t-option key="深圳校区" label="深圳校区" :value="3" />
              <t-option key="珠海校区" label="珠海校区" :value="4" />
            </t-select>
          </t-descriptions-item>

          <t-descriptions-item label="业务指导老师姓名">
            <t-auto-complete :borderless="item.clubId!==choose" :readonly="item.clubId!==choose" v-model="item.businessAdvisorName" />
          </t-descriptions-item>

          <t-descriptions-item label="行政指导老师姓名">
            <t-auto-complete :borderless="item.clubId!==choose" :readonly="item.clubId!==choose" v-model="item.administrativeAdvisorName" />
          </t-descriptions-item>

          <t-descriptions-item label="成员财务是否公开">
            <t-select showArrow v-model="item.financePublicity" :readonly="item.clubId!==choose">
              <t-option key="公开" label="公开" :value="1" />
              <t-option key="不公开" label="不公开" :value="0" />
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
import {onMounted, onUnmounted, reactive, ref} from 'vue';
import formatDate from '@/utils'
import {MessagePlugin} from "tdesign-vue-next";

const clubString = ref('社团信息')
const mainCampuses = JSON.parse(localStorage.getItem('enumList')).mainCampuses
const clubCategories = JSON.parse(localStorage.getItem('enumList')).clubCategories
// 判断空对象的函数
function isEmptyObject(obj) {
    return Object.keys(obj).length === 0;
}

const admin = ref({
    adminId:'',
    affiliatedUnit:'',
    clubs:[],
    account:'',
    contact:'',
})
const clubInfo = ref([
  {
    clubId:'',
    clubName:'',
    totalMembership:'',
    establishedTime:'',
    clubSort:0,
    location:'',
    businessAdvisorName:'',
    administrativeAdvisorName:'',
    financePublicity:'',
    clubStatus:'',
  }
])

onMounted(()=>{
  if (isEmptyObject(store.state.userInfo)) {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getAdminInfo, { adminId: store.state.adminId })
  } else {
    admin.value = store.state.userInfo;
    clubInfo.value = admin.value.clubs.filter(item=>{
      return item.clubStatus === 1;
    });
  }
  //初次加载界面时借此获取信息
  eventEmitter.on(APIEventEnum.getAdminInfoSuccess,'getAdminInfoSuccess', (data) => {
      admin.value = data;
  })
})

const readOnly = ref(true)
const buttonText=ref('编辑')
const oldClubInfo = {
  clubId:null,
  clubName: '',
  establishmentDate: '',
  clubCategory: '',
  responsibleDepartment: '',
  administrativeGuideTeacherName: '',
  businessGuideTeacherName: '',
  mainCampus: '',
  totalMembers: '',
  isFinancialInformationPublic: '',
  imageUrl: '',
}

const choose = ref(-1)
function whileClick(club) {
  //此处管理可编辑状态，实现保存功能
  readOnly.value = !readOnly.value
  buttonText.value = (readOnly.value) ? '编辑' : '保存'
  if( readOnly.value && (
      club.location !== oldClubInfo.mainCampus
      || club.administrativeAdvisorName !== oldClubInfo.administrativeGuideTeacherName
      || club.businessAdvisorName !== oldClubInfo.businessGuideTeacherName
      || club.financePublicity !== oldClubInfo.isFinancialInformationPublic
  )) {
    save(club)
    choose.value = -1
  }else if(!readOnly.value){
      oldClubInfo.clubId = club.clubId
      oldClubInfo.mainCampus = club.location
      oldClubInfo.administrativeGuideTeacherName = club.administrativeAdvisorName
      oldClubInfo.businessGuideTeacherName = club.businessAdvisorName
      oldClubInfo.isFinancialInformationPublic = club.financePublicity
      choose.value = club.clubId
  } else{
      MessagePlugin.success('更新社团信息成功')
      choose.value = -1
  }
}
function save(club) {
  eventEmitter.emit(APIEventEnum.request, APIEnum.postUpdateClubInfo, {
    clubId: club.clubId,
    clubName: club.clubName,
    establishmentDate: new Date(club.establishedTime),
    clubCategory: club.clubSort,
    responsibleDepartment: club.affiliatedUnitId,
    administrativeGuideTeacherName: club.administrativeAdvisorName,
    businessGuideTeacherName: club.businessAdvisorName,
    mainCompus: club.location,
    isFinancialInformationPublic: club.financePublicity,
    imageUrl: null,
  })
  eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubsData, admin.value)
}

eventEmitter.on(APIEventEnum.postUpdateClubInfoSuccess, 'postUpdateClubInfoSuccess', ()=>{
    MessagePlugin.success('更新社团信息成功')
})

onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getAdminInfoSuccess,'getAdminInfoSuccess')
  eventEmitter.off(APIEventEnum.postUpdateClubInfoSuccess,'postUpdateClubInfoSuccess')
})


</script>
  
